package br.com.unipds.javify.playhistory.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("play_history")
public record PlayHistory(
        @PrimaryKey PlayHistoryKey key,
        @Column("track_id") String trackId,
        @Column("track_name") String trackName
) { }
