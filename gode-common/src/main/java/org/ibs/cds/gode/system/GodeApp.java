package org.ibs.cds.gode.system;

import org.springframework.boot.SpringApplication;

public class GodeApp {

    public static void start(Class<?> runner,String... args){
        GodeAppEnvt.ofApp(SpringApplication.run(runner, args));
    }

}
