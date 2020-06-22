package org.ibs.cds.gode.utils;

import org.ibs.cds.gode.entity.relationship.RelationshipType;
import org.ibs.cds.gode.entity.store.StoreType;
import org.ibs.cds.gode.exception.KnownException;

import static org.ibs.cds.gode.utils.JavaArtifact.of;

public class RelationshipUtils {

    public static RelationshipEssential essential(StoreType storeType, RelationshipType relationshipType){
        switch (relationshipType){
            case ONE_TO_ONE: default:   return oneToOne(storeType);
            case ONE_TO_MANY: return oneToMany(storeType);
            case MANY_TO_ANY: return manyToAny(storeType);
        }
    }

    private static RelationshipEssential oneToOne(StoreType storeType){
        switch (storeType){
            case MONGODB: return new RelationshipEssential(
                    of("OneToOneRManager","org.ibs.cds.gode.entity.manager"),
                    of("OneToOneRelationshipMongoRepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToOneRelationshipMongoRepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToOneREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("OnetoOneRMongoEntity","org.ibs.cds.gode.entity.type")
            );
            case JPA: return new RelationshipEssential(
                    of("OneToOneRManager","org.ibs.cds.gode.entity.manager"),
                    of("OneToOneRelationshipJPARepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToOneRelationshipJPARepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToOneREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("OnetoOneRJPAEntity","org.ibs.cds.gode.entity.type")
            );
        }
        throw KnownException.SYSTEM_FAILURE.provide("Store not found in implemented types");
    }

    private static RelationshipEssential oneToMany(StoreType storeType){
        switch (storeType){
            case MONGODB: return new RelationshipEssential(
                    of("OneToManyRManager","org.ibs.cds.gode.entity.manager"),
                    of("OneToManyRelationshipMongoRepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToManyRelationshipMongoRepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToManyREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("OnetoManyRMongoEntity","org.ibs.cds.gode.entity.type")
            );
            case JPA: return new RelationshipEssential(
                    of("OneToManyRManager","org.ibs.cds.gode.entity.manager"),
                    of("OneToManyRelationshipJPARepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToManyRelationshipJPARepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("OneToManyREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("OnetoManyRJPAEntity","org.ibs.cds.gode.entity.type")
            );
        }
        throw KnownException.SYSTEM_FAILURE.provide("Store not found in implemented types");
    }

    private static RelationshipEssential manyToAny(StoreType storeType){
        switch (storeType){
            case MONGODB: return new RelationshipEssential(
                    of("ManyToAnyRManager","org.ibs.cds.gode.entity.manager"),
                    of("ManyToAnyRelationshipMongoRepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("ManyToAnyRelationshipMongoRepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("ManyToAnyREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("ManytoAnyRMongoEntity","org.ibs.cds.gode.entity.type")
            );
            case JPA: return new RelationshipEssential(
                    of("ManyToAnyRManager","org.ibs.cds.gode.entity.manager"),
                    of("ManyToAnyRelationshipJPARepo", "org.ibs.cds.gode.entity.store.repo"),
                    of("ManyToAnyRelationshipJPARepository", "org.ibs.cds.gode.entity.store.repo"),
                    of("ManyToAnyREndpoint","org.ibs.cds.gode.entity.controller"),
                    of("ManytoAnyRJPAEntity","org.ibs.cds.gode.entity.type")
            );
        }
        throw KnownException.SYSTEM_FAILURE.provide("Store not found in implemented types");
    }
}
