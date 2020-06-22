package org.ibs.cds.gode.entity.manager;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.entity.relationship.RelationshipType;
import org.ibs.cds.gode.entity.type.Relationship;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.RelationshipView;
import org.ibs.cds.gode.exception.KnownException;

import java.io.Serializable;
import java.util.Optional;

public interface RelationshipManager<A extends TypicalEntity<aid>,B extends TypicalEntity<bid>,RelationView extends RelationshipView<A,B>,Relation extends Relationship<aid,bid>,aid extends Serializable,bid extends Serializable> {

    RelationView transformRelationshipFields(Relation relation);
    Relation transformRelationshipFields(RelationView relationship);

    Pair<A,B> findAsideAndBside(Relation relation);

    default Pair<aid, bid> findAsideAndBside(RelationView view) {
        return Pair.of(view.getAside().getId(), view.getBside().getId());
    }

    ValidationStatus relationshipValidation(RelationView view);

    default RelationView transformRelation(Relation relation){
        RelationView view = transformRelationshipFields(relation);
        view.setActive(relation.isActive());
        view.setRelationshipId(relation.getRelationshipId());
        view.setAppId(relation.getAppId());
        view.setCreatedOn(relation.getCreatedOn());
        view.setUpdatedOn(relation.getUpdatedOn());
        view.setValidated(relation.isValidated());
        var pair =findAsideAndBside(relation);
        view.setAside(pair.getLeft());
        view.setBside(pair.getRight());
        return view;
    }

    default Relation transformRelationView(RelationView view){
        Relation relationship = transformRelationshipFields(view);
        relationship.setActive(view.isActive());
        relationship.setRelationshipId(view.getRelationshipId());
        relationship.setAppId(view.getAppId());
        relationship.setCreatedOn(view.getCreatedOn());
        relationship.setUpdatedOn(view.getUpdatedOn());
        relationship.setValidated(view.isValidated());
        try {
            var pair = findAsideAndBside(view);
            relationship.setAid(pair.getLeft());
            relationship.setBid(pair.getRight());
            return relationship;
        }catch (Exception e){
            throw KnownException.RELATIVE_NOT_FOUND_EXCEPTION.provide(e, "Relative entity id cannot be empty");
        }

    }

    default Optional<RelationView> transformRelation(Optional<Relation> relationOptional) {
        return relationOptional
                .map(this::transformRelation)
                .filter(view->view.getAside() != null & view.getBside() !=null);
    }


    default Optional<Relation> transformRelationView(Optional<RelationView> relationViewOptional) {
        return relationViewOptional.map(this::transformRelationView);
    }

    RelationshipType type();
}
