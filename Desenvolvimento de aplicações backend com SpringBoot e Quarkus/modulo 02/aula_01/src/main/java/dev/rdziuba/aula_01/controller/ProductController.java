package dev.rdziuba.aula_01.controller;

import dev.rdziuba.aula_01.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class ProductController {

    private List<Product> database;

    public ProductController() {
        this.database = new ArrayList<>() {{
           add(new Product(1, "Computador", 1500.0));
           add(new Product(2, "Mouse", 50.0));
           add(new Product(3, "Teclado", 100.0));
           add(new Product(4, "Monitor", 500.0));
           add(new Product(5, "Impressora", 350.0));
        }};
    }

    @GetMapping("/products")
    public List<Product> allProducts() {
        return database;
    }

    @GetMapping("/products/sort")
    public ResponseEntity<List<Product>>  orderedBy(@RequestParam(required = false) String order) {
        if(order == null) {
            return ResponseEntity.ok(database);
        } else if (order.equals("asc")) {
            return ResponseEntity.ok(database.stream().sorted(Comparator.comparing(Product::getPrice)).toList());
        } else if (order.equals("desc")) {
            return ResponseEntity.ok(database.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productPerId(@PathVariable int id) {
        Product product = database.stream().filter(productDB -> productDB.getId() == id).findFirst().orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product) {
        this.database.add(product);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> UpdateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        Product productDB = database.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
        if (productDB == null) return ResponseEntity.notFound().build();

        database.remove(productDB);
        database.add(productRequest);
        return ResponseEntity.ok(productRequest);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        Product productDB = database.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
        if (productDB == null) return ResponseEntity.notFound().build();

        database.remove(productDB);
        return ResponseEntity.ok(productDB);
    }


}
