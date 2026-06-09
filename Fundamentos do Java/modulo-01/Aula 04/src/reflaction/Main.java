package reflaction;

public class Main {
    static void main(String[] args) throws Exception {
         Product product = new Product(1, "computer", 1000.0);
         ClassExplorer.explorerMetadata(product);
    }
}
