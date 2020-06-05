package org.ibs.cds.gode.entity.manager.operation;

import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;
import java.util.stream.Collectors;

public interface StateEntityManagerOperation<View extends EntityView<Id>, Entity extends TypicalEntity<Id>, Id extends Serializable> extends ViewEntityManagerOperation<View, Id> {

    View save(View view);

    View findByAppId(Long appId);

    View find(Id id);

    View transformEntity(Entity entity);

    Entity transformView(View entity);

    default ValidationStatus validateEntity(Entity entity){
        return ValidationStatus.ok();
    }

    boolean deactivate(Id id);

    default PagedData<View> transformEntityPage(PagedData<Entity> pagedData) {
        return new PagedData(pagedData.stream().map(this::transformEntity).collect(Collectors.toList()), pagedData.getContext());
    }

    default PagedData<Entity> transformViewPage(PagedData<View> pagedData) {
        return new PagedData(pagedData.stream().map(this::transformView).collect(Collectors.toList()), pagedData.getContext());
    }

}
