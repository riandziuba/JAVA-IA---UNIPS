package br.com.unipds.javify.recommendations.domain;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public record User (
    @Id Long id,

    String name,

    String email,

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    List<User> following,

    @Relationship(type = "LISTENS_TO", direction = Relationship.Direction.OUTGOING)
    List<ListensTo> listenedArtists

) { }
