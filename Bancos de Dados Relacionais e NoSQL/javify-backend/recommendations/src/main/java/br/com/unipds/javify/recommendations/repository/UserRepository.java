package br.com.unipds.javify.recommendations.repository;

import br.com.unipds.javify.recommendations.domain.User;
import br.com.unipds.javify.recommendations.dto.ArtistRecommendation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserRepository extends Neo4jRepository<User, Long> {

    @Query("""
            MATCH (user:User {id: $userId})-[:FOLLOWS]->(friend:User)-[:LISTENS_TO]->(artist:Artist)
            WHERE NOT (user)-[:LISTENS_TO]->(artist)
            RETURN artist.name as name, COUNT(friend) as recommendationStrength
            ORDER BY recommendationStrength DESC
            LIMIT 5;
            """)
    List<ArtistRecommendation> recommendations(@Param("userId") Long userId);


}
