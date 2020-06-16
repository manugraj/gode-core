package org.ibs.cds.gode.entity.manager;

import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.repo.RepoType;
import org.ibs.cds.gode.entity.type.TypicalEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class EntityRepository<Entity extends TypicalEntity<Id>, Id extends Serializable> {

    protected Map<RepoType, Repo<Entity, Id>> repoContainer;

    public EntityRepository() {
        this.repoContainer = new EnumMap(RepoType.class);
    }

    public <T extends Repo<Entity,Id>> void add(T repo){
        if(repo != null){
            this.repoContainer.put(repo.type(), repo);
        }
    }

    public <T extends Repo<Entity,Id>> T get(RepoType type){
        return (T) this.repoContainer.get(type);
    }

    public <T extends Repo<Entity,Id>> T get(){
        Collection<? extends Repo<Entity, Id>> values = this.repoContainer.values();
        if(CollectionUtils.isNotEmpty(values)){
            return (T) values.stream().findFirst().orElse(null);
        }
       return null;
    }

    public Collection<? extends Repo<Entity, Id>> values(){
        return repoContainer.values();
    }
}
