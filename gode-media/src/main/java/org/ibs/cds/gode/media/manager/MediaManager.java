package org.ibs.cds.gode.media.manager;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.media.DefaultMediaStore;
import org.ibs.cds.gode.media.Media;
import org.ibs.cds.gode.media.MediaMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaManager {

    private final DefaultStore<Media> fileStoreLocation;

    private static final String FILE_STORE = "fs";

    @Autowired
    public MediaManager(DefaultMediaStore storeManager){
        fileStoreLocation = storeManager.createStore(FILE_STORE);
    }

    public Media save(MultipartFile file) throws IOException {
        return saveActualData(file);
    }

    public Optional<Path> findPath(String fileName) {
       return fileStoreLocation.find(fileName);
    }

    public Optional<Pair<Path, InputStream>> findDoc(String fileName) throws IOException {
        return fileStoreLocation.findFile(fileName);
    }

    private Media saveActualData(MultipartFile file) throws IOException {
        Media doc = new Media();
        doc.setFile(file);
        doc.setType(FilenameUtils.getExtension(file.getOriginalFilename()));
        doc.setId(UUID.randomUUID().getLeastSignificantBits());
        MediaMetadata metadata = new MediaMetadata();
        metadata.setMimeType(file.getContentType());
        metadata.setStashedDate(new Date());
        Path savedPath = fileStoreLocation.save(doc);
        doc.setMetadata(metadata);
        metadata.setUri(savedPath.toUri().toURL().toExternalForm());
        metadata.setStoredFileName(savedPath.getFileName().toString());
        metadata.setId(doc.getId());
        return doc;
    }
}
