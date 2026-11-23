package br.com.unipds.javify.recommendations.domain;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public record Artist (

        @Id String id,

        String name

) { }
