package org.ibs.cds.gode.system;

import org.springframework.boot.SpringApplication;

import java.time.LocalDateTime;

public class GodeApp {

    public static void start(Class<?> runner,String... args){
        GodeAppEnvt.ofApp(SpringApplication.run(runner, args));
        System.out.println("A̳ᴡ̳ᴀ̳ᴋ̳ᴇ̳ @̳"+ LocalDateTime.now());
    }

}
