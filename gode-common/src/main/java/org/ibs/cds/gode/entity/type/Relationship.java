package org.ibs.cds.gode.entity.type;

public interface Relationship<aid,bid> extends StateEntity<Long>{

    Long getRelationshipId();
    void setRelationshipId(Long relationshipId);
    void setAid(aid aid);
    void setBid(bid bid);
    aid getAid();
    bid getBid();
}
