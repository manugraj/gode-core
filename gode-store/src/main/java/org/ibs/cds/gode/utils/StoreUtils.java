package org.ibs.cds.gode.utils;

import org.ibs.cds.gode.entity.store.StoreType;

public class StoreUtils {

    private static final String REPO_PATH = "org.ibs.cds.gode.entity.store.repo";
    private static final String ENTITY_PATH = "org.ibs.cds.gode.entity.type";

    public static StoreEssential essential(StoreType type){
        switch (type){
            case JPA:
                return new StoreEssential(
                 new JavaArtifact("Entity", "javax.persistence"),
                 new JavaArtifact("JPAEntity", ENTITY_PATH),
                 new JavaArtifact("JPAEntityRepo", REPO_PATH),
                        new JavaArtifact("JPAEntityRepository",REPO_PATH),
                        new JavaArtifact("Id","javax.persistence"),
                        type
                );
            case MONGODB:
                return new StoreEssential(
                        new JavaArtifact("Document", "org.springframework.data.mongodb.core.mapping"),
                        new JavaArtifact("MongoEntity", ENTITY_PATH),
                        new JavaArtifact("MongoEntityRepo", REPO_PATH),
                        new JavaArtifact("MongoEntityRepository",REPO_PATH),
                        new JavaArtifact("Id","org.springframework.data.annotation"),
                        type
                );
        }
        return  null;
    }
}
