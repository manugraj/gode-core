package org.ibs.cds.gode.entity.manager;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.status.BinaryStatus;

import java.io.Serializable;
import java.util.function.Function;

@Slf4j
public abstract class StateEntityManager<View extends EntityView<Id>,Entity extends TypicalEntity<Id>,Id extends Serializable,R extends Repo<Entity,Id>> implements
        StateEntityManagerOperation<View,Entity,Id> {
    protected final R repo;
    protected static  final String SAVE = "save";
    protected static  final String BFR_SAVE = "beforeSave";
    protected static  final String AFR_SAVE = "afterSave";
    protected static  final String DO_SAVE = "doSave";
    protected static  final String FIND_BY_ID = "findById";
    protected static  final String FIND_BY_APPID = "findByAppId";
    protected static  final String VALIDATE = "validate";
    protected static  final String FIND_ALL= "findAll";
    protected static  final String FIND_BY_PREDICATE = "findByPredicate";
    protected static  final String DEACTIVATE = "deactivate";
    protected static  final String LOG_TEMPLATE = "Action : {} | Arguments: {}";

    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }

    public StateEntityManager(R repo) {
        this.repo = repo;
    }

    public Entity doSave(Entity entity){
        log.debug(LOG_TEMPLATE, DO_SAVE, entity);
        return this.repo.save(entity);
    }

    @Override
    public View findByAppId(Long appId){
        log.debug(LOG_TEMPLATE, FIND_BY_APPID, appId);
        return transformEntity(this.repo.findByAppId(appId));
    }

    public Entity beforeSave(Entity entity){
        log.debug(LOG_TEMPLATE, BFR_SAVE, entity);
        invokeValidation(entity, this::validate);
        return entity;
    }

    public Entity afterSave(Entity entity){
        log.debug(LOG_TEMPLATE, AFR_SAVE, entity);
        return entity;
    }

    @Override
    public View save(View view){
        invokeValidation(view, this::validate);
        return transformEntity(afterSave(doSave(beforeSave(transformView(view)))));
    }

    @Override
    public View find(Id id){
        log.debug(LOG_TEMPLATE, FIND_BY_ID, id);
        return this.repo.findById(id).map(this::transformEntity).orElse(null);
    }

    @Override
    public boolean deactivate(Id id){
        log.debug(LOG_TEMPLATE, DEACTIVATE, id);
        return repo.deactivate(id);
    }

    protected <T extends TypicalEntity<?>>void invokeValidation(T entity, Function<T,ValidationStatus> validationFunction) {
        log.debug(LOG_TEMPLATE, VALIDATE,entity);
        if(!entity.isValidated()){
            ValidationStatus viewValidation = validationFunction.apply(entity);
            if(viewValidation.getStatus() == BinaryStatus.FAILURE){
                throw KnownException.ENTITY_VALIDATIONS_FAILED.provide(viewValidation.getErrors());
            }
            entity.setValidated(true);
        }
    }
}
