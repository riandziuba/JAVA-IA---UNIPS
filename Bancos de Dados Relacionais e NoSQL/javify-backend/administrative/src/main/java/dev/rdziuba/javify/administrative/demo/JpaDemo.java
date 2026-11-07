package dev.rdziuba.javify.administrative.demo;

import dev.rdziuba.javify.administrative.domain.Signature;
import dev.rdziuba.javify.administrative.domain.Address;
import dev.rdziuba.javify.administrative.domain.User;
import dev.rdziuba.javify.administrative.repository.SignatureRepository;
import dev.rdziuba.javify.administrative.repository.AddressRepository;
import dev.rdziuba.javify.administrative.repository.UserRepository;
import dev.rdziuba.javify.administrative.repository.projection.UserSummary;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaDemo {

    private final AddressRepository addressRepository;
    private final SignatureRepository signatureRepository;
    private final UserRepository userRepository;

    public JpaDemo(AddressRepository addressRepository, SignatureRepository signatureRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.signatureRepository = signatureRepository;
        this.userRepository = userRepository;
    }

    public void searchAddress(){
        System.out.println("Address with JPA: " + addressRepository.findByPostalCode("01508000"));
    }

    public void registerAddress() {
        var address = new Address();
        address.setPostalCode("25950000");
        address.setStreet("Rua Amaral");
        address.setNeighborhood("Tijuca");
        addressRepository.save(address);
    }

    @Transactional
    public void updateAddress() {
        var address = addressRepository.findByPostalCode("25950000");
        System.out.println("Before: " + address);
        address.setStreet("Não Rua Amaral Não Sincronizado");
        System.out.println("After: " + address);
        System.out.println("---");
    }

    @Transactional
    public void findSignatureWithPlan(){
        List<Signature> signatures = signatureRepository.findAllWithPlan();

        for (Signature signature : signatures) {
            System.out.printf("%s -  %s  \n", signature.getId(), signature.getPlan().getName() );
        }
    }

    @Transactional
    public void removeExpiredCreditCard() {
        var signature = signatureRepository.findById(3);
        System.out.println("Credit cards: " + signature.get().getCreditCards());
        if (!signature.get().getCreditCards().isEmpty()) {
            signature.get().getCreditCards().remove(0);
            System.out.println("Credit cards: " + signature.get().getCreditCards());
        }
    }

    public void testQueries() {
        for (User u : userRepository.findByHolderTrue()) {
            System.out.println("Holder: " + u.getName() + " (" + u.getEmail() + ")");
        }

        for (User u : userRepository.searchByName("ei")) {
            System.out.println("User found by name: " + u.getName() + " (" + u.getEmail() + ")");
        }

        long total = userRepository.countActiveHolders();
        System.out.println("Total active holders: " + total);

        for (UserSummary u : userRepository.findAllHolderSummaries()) {
            System.out.println("Summary: " + u.getName() + " (" + u.getEmail() + ")");
        }
    }

}
