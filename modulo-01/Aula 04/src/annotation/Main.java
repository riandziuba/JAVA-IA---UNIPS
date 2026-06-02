package annotation;

public class Main {
    static void main(String[] args) throws Exception{
        Product product = new Product(1, "computer", 1000.0);
        ClassExplorer.explorerMetadata(product);

        Customer customer = new Customer(1, "Isidro", "123.456.789-00", "isidro@gmail.com");
        ClassExplorer.explorerMetadata(customer);
    }
}
