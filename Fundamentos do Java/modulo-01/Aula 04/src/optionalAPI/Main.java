package optionalAPI;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

        Optional<Product> optProduct = productRepository.findById(1);
        optProduct.ifPresent(p -> System.out.println("Nome do produto:" + p.getName()));

        Optional<Product> optProduct2 = productRepository.findById(10);
        optProduct2.ifPresent(p -> System.out.println("Nome do produto:" + p.getName()));

        Product product = productRepository.findById(10).orElse(new Product(-1, "Produto inexistente", 0));
        System.out.println("Nome do produto:" + product.getName());

        Product product2 = productRepository.findById(10).orElseThrow(() -> new RuntimeException("Produto inexistente"));
        System.out.println("Nome do produto:" + product2.getName());
    }
}
