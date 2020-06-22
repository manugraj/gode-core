package org.ibs.cds.gode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipEssential {
    private JavaArtifact manager;
    private JavaArtifact repo;
    private JavaArtifact repository;
    private JavaArtifact controller;
    private JavaArtifact entityType;
}
