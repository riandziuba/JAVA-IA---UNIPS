package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPhoneRepository extends JpaRepository<UserPhone, Integer> {

    List<UserPhone> findByUserId(Long userId);
}
