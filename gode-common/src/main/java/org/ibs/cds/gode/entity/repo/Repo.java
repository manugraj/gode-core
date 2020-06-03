package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.TypicalEntity;

import java.io.Serializable;

public interface Repo<Entity extends TypicalEntity<Id>, Id extends Serializable> {
}
