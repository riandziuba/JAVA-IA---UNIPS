import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>() {{
            add(new Vehicle("Corsa", "Cinza", 25000, 1998, 160));
            add(new Vehicle("Corolla", "Prata", 70000, 2015, 200));
            add(new Vehicle("Corolla", "Preto", 100000, 2025, 210));
            add(new Vehicle("X1", "Branco", 250000, 2023, 250));
            add(new Vehicle("GLA200", "Prata", 300000, 2025, 280));
        }};

        System.out.println("Lista Original:");
        vehicles.forEach(
                System.out::println
        );

        System.out.println("Lista ordenada por preço:");
        List<Vehicle> vehiclesPriceOrdered = vehicles.stream().sorted(Comparator.comparing(Vehicle::getModel).reversed()).collect(Collectors.toList());
        vehiclesPriceOrdered.forEach(System.out::println);

        vehiclesPriceOrdered.add(new Vehicle("Gol", "Azul", 18000, 1995, 190));

        System.out.println("Corollas: ");
        List<Vehicle> corollas = vehicles.stream().filter(vehicle -> vehicle.getModel().equalsIgnoreCase("corolla")).toList();
        corollas.forEach(System.out::println);

        double averagePrice = vehicles.stream().mapToDouble(Vehicle::getPrice).average().orElse(0.0);
        System.out.println("Preço médio: " + averagePrice);
        double maxPrice = vehicles.stream().mapToDouble(Vehicle::getPrice).max().orElse(0.0);
        System.out.println("Maior preço: " + maxPrice);
        double minPrice = vehicles.stream().mapToDouble(Vehicle::getPrice).min().orElse(0.0);
        System.out.println("Menor preco: " + minPrice);

        double averagePriceCorollas = vehicles.stream().filter(vehicle -> vehicle.getModel().equalsIgnoreCase("corolla")).mapToDouble(Vehicle::getPrice).average().orElse(0.0);
        System.out.println("Preço médio dos Corollas: " + averagePriceCorollas);

        System.out.println("Veiculos em maiúsculo: ");
        List<Vehicle> upperCasedVehicles = vehicles.stream().map(Main::StringsToUpperCase).toList();
        upperCasedVehicles.forEach(System.out::println);
    }

    public static Vehicle StringsToUpperCase(Vehicle vehicle) {
        return new Vehicle(vehicle.getModel().toUpperCase(), vehicle.getColor().toUpperCase(), vehicle.getPrice(), vehicle.getYear(), vehicle.getMaxSpeed());
    }
}
