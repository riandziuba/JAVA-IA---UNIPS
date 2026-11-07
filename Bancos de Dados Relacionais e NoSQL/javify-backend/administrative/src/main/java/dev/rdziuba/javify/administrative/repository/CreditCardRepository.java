package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    List<CreditCard> findBySignatureId(Integer signatureId);
}
