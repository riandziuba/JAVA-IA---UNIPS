package optionalAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>() {{
            add(new Product(1, "computer", 1000.0));
            add(new Product(2, "mouse", 50.0));
            add(new Product(3, "keyboard", 100.0));
        }};
    }

    public Optional<Product> findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) return Optional.of(product);
        }
        return Optional.empty();
    }
}
