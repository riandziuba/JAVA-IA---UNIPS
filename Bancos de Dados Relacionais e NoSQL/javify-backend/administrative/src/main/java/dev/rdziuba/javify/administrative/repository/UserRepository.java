package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.User;
import dev.rdziuba.javify.administrative.repository.projection.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByHolderTrue();

    @Query("SELECT u from User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :term, '%')) Order by u.name")
    List<User> searchByName(@Param("term") String term);

    @Query(value = "SELECT COUNT(*) FROM \"user\" WHERE is_holder = true", nativeQuery = true)
    long countActiveHolders();

    @Query("SELECT u.name AS name, u.email AS email FROM User u WHERE u.holder = true ORDER BY u.name")
    List<UserSummary> findAllHolderSummaries();
}
