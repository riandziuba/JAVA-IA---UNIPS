package br.com.unipds.javify.catalog.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class    AlbumReportService {

    private final MongoTemplate mongoTemplate;

    public AlbumReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public GenreDurationReportResult calculateTotalDurationByGenre(String genre) {
        var pipeline = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("genre").regex(genre, "i")),
                Aggregation.unwind("tracks"),
                Aggregation.group()
                        .sum("tracks.durationSeconds").as("totalDurationSeconds")
                        .count().as("totalTracks")
        );

        AggregationResults<GenreDurationReportResult> results = mongoTemplate.aggregate(pipeline, "albums", GenreDurationReportResult.class);
        return results.getUniqueMappedResult();
    }

    public record GenreDurationReportResult(Long totalDurationSeconds, Long totalTracks) {}

}
