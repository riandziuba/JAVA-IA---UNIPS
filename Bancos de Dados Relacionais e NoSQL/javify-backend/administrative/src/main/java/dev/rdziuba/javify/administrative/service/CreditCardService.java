package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.CreditCard;
import dev.rdziuba.javify.administrative.dto.CreditCardRequest;
import dev.rdziuba.javify.administrative.dto.CreditCardResponse;
import dev.rdziuba.javify.administrative.repository.SignatureRepository;
import dev.rdziuba.javify.administrative.repository.CreditCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final SignatureRepository signatureRepository;

    public CreditCardService(CreditCardRepository creditCardRepository, SignatureRepository signatureRepository) {
        this.creditCardRepository = creditCardRepository;
        this.signatureRepository = signatureRepository;
    }

    public List<CreditCardResponse> findAll() {
        return creditCardRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CreditCardResponse findById(Integer id) {
        return creditCardRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found: " + id));
    }

    public List<CreditCardResponse> findBySignature(Integer signatureId) {
        return creditCardRepository.findBySignatureId(signatureId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public CreditCardResponse create(CreditCardRequest request) {
        return create(request.signatureId(), request);
    }

    @Transactional
    public CreditCardResponse create(Integer signatureId, CreditCardRequest request) {
        var signature = signatureRepository.findById(signatureId)
                .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + signatureId));
        var creditCard = new CreditCard();
        creditCard.setSignature(signature);
        creditCard.setCardholderName(request.cardholderName());
        creditCard.setLastFourDigits(request.lastFourDigits());
        creditCard.setGatewayToken(request.gatewayToken());
        creditCard.setExpirationDate(request.expirationDate());
        return toResponse(creditCardRepository.save(creditCard));
    }

    @Transactional
    public CreditCardResponse update(Integer id, CreditCardRequest request) {
        var creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found: " + id));
        var signature = signatureRepository.findById(request.signatureId())
                .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + request.signatureId()));
        creditCard.setSignature(signature);
        creditCard.setCardholderName(request.cardholderName());
        creditCard.setLastFourDigits(request.lastFourDigits());
        creditCard.setGatewayToken(request.gatewayToken());
        creditCard.setExpirationDate(request.expirationDate());
        return toResponse(creditCardRepository.save(creditCard));
    }

    @Transactional
    public void delete(Integer id) {
        if (!creditCardRepository.existsById(id)) {
            throw new EntityNotFoundException("Credit card not found: " + id);
        }
        creditCardRepository.deleteById(id);
    }

    private CreditCardResponse toResponse(CreditCard creditCard) {
        return new CreditCardResponse(
                creditCard.getId(),
                creditCard.getSignature().getId(),
                creditCard.getCardholderName(),
                creditCard.getLastFourDigits(),
                creditCard.getGatewayToken(),
                creditCard.getExpirationDate()
        );
    }
}
