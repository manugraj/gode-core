package org.ibs.cds.gode.utils;

import lombok.Data;

@Data

public class JavaArtifact {
    private String name;
    private String packageName;

    public JavaArtifact(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public String fqn(){
        return packageName != null ?
                packageName.concat(".").concat(name) :
                name;
    }


    public static JavaArtifact of(String name, String packageName){
        return new JavaArtifact(name, packageName);
    }
}
