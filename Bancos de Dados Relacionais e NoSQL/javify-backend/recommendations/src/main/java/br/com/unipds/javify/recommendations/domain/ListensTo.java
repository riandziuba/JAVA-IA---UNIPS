package br.com.unipds.javify.recommendations.domain;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public record ListensTo(
        @RelationshipId String id,
        @TargetNode Artist artist,
        int timesPlayed
) {
}
