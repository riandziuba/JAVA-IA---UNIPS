package br.com.unipds.javify.playhistory.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
@PrimaryKeyClass
public record PlayHistoryKey(
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED) Long userId,
        @PrimaryKeyColumn(name = "played_time", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)LocalDateTime played_time
) implements Serializable {
}
