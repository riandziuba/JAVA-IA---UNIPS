import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Product> productsList = new ArrayList<>();

        productsList.add(new Product(1, "Computer", 1500.0));
        productsList.add(new Product(2, "Mouse", 50.0));
        productsList.add(new Product(3, "Keyboard", 100));
        productsList.add(new Product(1, "Computer", 1500.0));
        productsList.add(new Product(1, "Computer", 1500.0));

        System.out.println(productsList);

        Set<Product> productSet = new HashSet<>();

        productSet.add(new Product(1, "Computer", 1500.0));
        productSet.add(new Product(1, "Computer", 1500.0));

        System.out.println(productSet);

        Map<Integer, Product> productMap = new HashMap<>();

        productMap.put(1, new Product(1, "Computer", 1500.0));
        productMap.put(2, new Product(2, "Mouse", 50.0));
        productMap.put(3, new Product(3, "Keyboard", 100));
        productMap.put(1, new Product(1, "Table", 200));

        System.out.println(productMap);

        listBenchmark(1000);
        listBenchmark(10000);
        listBenchmark(100000);

        mapBenchmark(1000);
        mapBenchmark(10000);
        mapBenchmark(100000);
    }

    public static void listBenchmark(int size) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            products.add(new Product(i+1, "Produto " + (i + 1), (i + 1) * 10));
        }

        int index = size - 1;
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            for (Product product : products) {
                if(index == product.getId()) break;
            }
        }
        end = System.currentTimeMillis();

        System.out.println("Demorou " + (end - start) + "ms para buscar na lista de " + size + " elementos");
    }

    public static void mapBenchmark(int size) {
        Map<Integer, Product> products = new HashMap<>();

        for (int i = 0; i < size; i++) {
            products.put(i+1, new Product(i+1, "Produto " + (i + 1), (i + 1) * 10));
        }

        int index = size - 1;
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (products.get(i) != null) {

            }
        }
        end = System.currentTimeMillis();

        System.out.println("Demorou " + (end - start) + "ms para buscar no mapa " + size + " elementos");
    }
}
