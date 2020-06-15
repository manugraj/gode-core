package org.ibs.cds.gode.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class MediaMetadata extends TypicalMedia {

    private String mimeType;

    private Date stashedDate;

    private String remarks;

    @JsonIgnore
    public String getStoredFileName() {
        return storedFileName;
    }

    @JsonIgnore
    public String getUri() {
        return uri;
    }

    private String storedFileName;

    private String uri;

}
