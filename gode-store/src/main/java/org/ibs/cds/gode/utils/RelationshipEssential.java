package org.ibs.cds.gode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipEssential {
    private JavaArtifact repo;
    private JavaArtifact repository;
    private JavaArtifact managerType;
    private JavaArtifact endPointType;
}
