package org.ibs.cds.gode.media.manager;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.media.TypicalMedia;
import org.ibs.cds.gode.util.FileCypherUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

public class DefaultStore<T extends TypicalMedia> {

    private final File location;

    public DefaultStore(String location){
        this.location = new File(location);
        if(!this.location.exists()) this.location.mkdirs();
    }

    public Path save(T doc) throws IOException {
        if(doc.getId() == null) doc.setId(UUID.randomUUID().getLeastSignificantBits());
        File saveFile = new File(this.location.getAbsolutePath().concat(File.separator).concat(doc.getId().toString()).concat(".").concat(doc.getType()));
        if(saveFile.exists()) saveFile.delete();
        FileCypherUtil.encrypt(doc.getFile().getBytes(), saveFile);
        return saveFile.toPath();
    }

    public Optional<Path> find(String docId) {
        File file = new File(this.location.getAbsolutePath().concat(File.separator).concat(docId));
        if(!file.exists()) return Optional.empty();
        return Optional.of(file.toPath());
    }

    public Optional<Pair<Path,InputStream>> findFile(String docId) throws IOException {
        File file = new File(this.location.getAbsolutePath().concat(File.separator).concat(docId));
        if(!file.exists()) return Optional.empty();
        return Optional.of(Pair.of(file.toPath(),FileCypherUtil.decrypt(file)));
    }
}
