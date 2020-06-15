package org.ibs.cds.gode.media.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.app.AppEndpoint;
import org.ibs.cds.gode.entity.operation.Processor;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.media.Media;
import org.ibs.cds.gode.media.manager.MediaManager;
import org.ibs.cds.gode.system.GodeConstant;
import org.ibs.cds.gode.util.ImageUtils;
import org.ibs.cds.gode.util.StringUtils;
import org.ibs.cds.gode.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@AppEndpoint
@Api(tags = {"Media endpoint"})
public class MediaController {
    private static final String GET_IMAGE = "/image/{fileName}";
    private static final String GET_FILE = "/file/{fileName}";
    private static final String FILE_NAME_PREFIX = "{fileName}";
    private static final String SAVE = "/save";
    private MediaManager mediaManager;

    @Autowired
    public MediaController(MediaManager mediaManager) {
        this.mediaManager = mediaManager;
    }


    @PostMapping(value = SAVE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "Save a file to the system")
    public Response<String> uploadNow(@RequestParam MultipartFile file) throws IOException {
        Media savedFileDoc = mediaManager.save(file);
        String fileCtx = ImageUtils.isImageFile(file.getOriginalFilename()) ?
                StringUtils.replace(GET_IMAGE, FILE_NAME_PREFIX, savedFileDoc.getMetadata().getStoredFileName()) :
                StringUtils.replace(GET_FILE, FILE_NAME_PREFIX, savedFileDoc.getMetadata().getStoredFileName());

        return Processor.successResponse(
                ServletUriComponentsBuilder.fromCurrentServletMapping().build().toUriString().concat(GodeConstant.GODE_API_BASE).concat(fileCtx),
                "save ".concat(file.getOriginalFilename())
                ,SAVE
        );
    }

    @GetMapping(value = GET_IMAGE, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    @ApiOperation(value = "Read image with custom resolution")
    public @ResponseBody
    byte[] image(@RequestParam(required = false) String size, @PathVariable String fileName) throws IOException {
        Optional<Pair<Path, InputStream>> option = this.mediaManager.findDoc(fileName);
        if (option.isPresent()) {
            InputStream inputStream = option.get().getRight();
            if (size != null) {
                String[] widthHeight = size.split("x");
                if (ArrayUtils.getLength(widthHeight) == 2) {
                    int width = Integer.parseInt(widthHeight[0]);
                    int height = Integer.parseInt(widthHeight[1]);
                    if (validWidthHeight(width, height)) {
                        return ImageUtils.resizeImage(width, height, inputStream);
                    }
                }
            }
            return IOUtils.toByteArray(inputStream);
        }
        return null;
    }

    private boolean validWidthHeight(int width, int height) {
        return width > 0 && height > 0;
    }

    @GetMapping(value = GET_FILE, produces = {MediaType.ALL_VALUE})
    @ApiOperation(value = "Retrieve file")
    public @ResponseBody
    ResponseEntity file0(@PathVariable String fileName) throws IOException {
        return this.mediaManager.findDoc(fileName).map(file -> {
            try {
                Resource resource = new InputStreamResource(file.getRight());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(Files.probeContentType(file.getKey())))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getLeft().getFileName() + "\"")
                        .body(resource);
            } catch (IOException e) {
                throw KnownException.SYSTEM_FAILURE.provide(e);
            }
        }).orElse(null);
    }
}
