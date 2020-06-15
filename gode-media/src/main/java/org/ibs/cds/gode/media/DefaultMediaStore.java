package org.ibs.cds.gode.media;

import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.media.manager.DefaultStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DefaultMediaStore {
   private static final String BASE_STORE = "gode.media.store.location";

   private final String baseStore;

   @Autowired
   public DefaultMediaStore(Environment env){
      baseStore = env.getRequiredProperty(BASE_STORE);
      File baseStoreFile = new File(baseStore);
      if(baseStoreFile.exists()){
         if(baseStoreFile.isFile()) throw KnownException.FAILED_TO_START.provide("Media store location should be a directory");
      }else{
         baseStoreFile.mkdirs();
      }
   }

   public <T extends TypicalMedia> DefaultStore<T> createStore(String path){
      return new DefaultStore<>(baseStore.concat(File.separator).concat(path));
   }
}
