package org.ibs.cds.gode.entity.manager.operation;

import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.validation.ValidationStatus;

import java.io.Serializable;

public interface PureStateEntityManagerOperation<Entity extends TypicalEntity<Id>, Id extends Serializable> extends ViewEntityManagerOperation<Entity, Id> {

    Entity save(Entity view);

    Entity findByAppId(Long appId);

    Entity find(Id id);

    default ValidationStatus validateEntity(Entity entity){
        return ValidationStatus.ok();
    }

    boolean deactivate(Id id);
}
