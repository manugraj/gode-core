package org.ibs.cds.gode.media;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public abstract class TypicalMedia {
    private Long id;
    private MultipartFile file;
    private String type;
}
