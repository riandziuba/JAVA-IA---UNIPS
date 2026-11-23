package br.com.unipds.javify.playhistory.repository;

import br.com.unipds.javify.playhistory.domain.PlayHistory;
import br.com.unipds.javify.playhistory.domain.PlayHistoryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface PlayHistoryRepository extends CassandraRepository<PlayHistory, PlayHistoryKey> {

    List<PlayHistory> findByKeyUserId(Long userId);
}
