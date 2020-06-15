package org.ibs.cds.gode.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Media extends TypicalMedia {

    @JsonIgnore
    public MultipartFile getFile() {
        return file;
    }

    private MultipartFile file;
    private MediaMetadata metadata;
}
