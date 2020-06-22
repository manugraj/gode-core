package org.ibs.cds.gode.entity.controller;

import org.ibs.cds.gode.entity.generic.AB;
import org.ibs.cds.gode.entity.manager.OneToOneRManager;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.type.Relationship;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.entity.view.RelationshipView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public abstract class OneToOneREndpoint<View extends RelationshipView<A,B>, Entity extends Relationship<aid,bid>, Manager extends OneToOneRManager<View, Entity, A,B,aid,bid>, A extends EntityView<aid>, B extends EntityView<bid>,aid extends Serializable, bid extends Serializable>
 extends EntityStateEndPoint<View, Entity,Manager,Long>{

    private OneToOneRManager<View, Entity, A,B,aid,bid> manager;
    public OneToOneREndpoint(Manager manager) {
        super(manager);
        this.manager = manager;
    }

    public Response<View> findRelationshipTo(@RequestBody Request<A> asideRequest){
        return Executor.run((Request<A> a)->(OneToOneRManager<View, Entity, A,B,aid,bid> m)->m.findRelationshipFrom(a.getData()), asideRequest ,manager, KnownException.QUERY_FAILED, "/from");
    }

    public Response<View> findRelationshipFrom(@RequestBody Request<B> bsideRequest){
        return Executor.run((Request<B> b)->(OneToOneRManager<View, Entity, A,B,aid,bid> m)->m.findRelationshipTo(b.getData()), bsideRequest ,manager, KnownException.QUERY_FAILED, "/to");
    }

    public Response<View> findRelationship(@RequestBody Request<AB<A,B>> absideRequest){
        return Executor.run((Request<AB<A,B>> a)->(OneToOneRManager<View, Entity, A,B,aid,bid> m)->m.findRelationship(a.getData().getA(), a.getData().getB()), absideRequest ,manager, KnownException.QUERY_FAILED, "/relation");
    }

    public Response<View> findAnyRelationship(@RequestBody Request<AB<A,B>> absideRequest){
        return Executor.run((Request<AB<A,B>> a)->(OneToOneRManager<View, Entity, A,B,aid,bid> m)->m.findAnyRelationship(a.getData().getA(), a.getData().getB()), absideRequest ,manager, KnownException.QUERY_FAILED, "/any");
    }
}
