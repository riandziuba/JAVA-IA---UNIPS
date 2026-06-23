package db;

import entities.MenuItem;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryDatabase implements IDatabase {

    private final List<MenuItem> database;
    private final Map<MenuItem, BigDecimal> priceHistory = new IdentityHashMap<>();

    public InMemoryDatabase() {
        this.database = new CopyOnWriteArrayList<>();

        MenuItem chavesDrink = new MenuItem(1L, "Refresco do Chaves", """
                Suco de Limão, que parece tamarindo, mas tem gosto de groselha
                """, MenuItem.MenuCategory.DRINKS, new BigDecimal("2.99"), null);
        database.add(chavesDrink);
        MenuItem chavesSandwich = new MenuItem(2L, "Sanduiche de presunto do Chaves", "Sanduiche de presunto simples, mas feito com muito amor", MenuItem.MenuCategory.MAIN_COURSE, new BigDecimal("3.50"), new BigDecimal("2.99"));
        database.add(chavesSandwich);
        MenuItem donaFlorindaPie = new MenuItem(5L, "Torta de Frango da Dona Florinda", "Torta de frnago com recheio cremoso e massa crocante", MenuItem.MenuCategory.MAIN_COURSE, new BigDecimal("12.99"), new BigDecimal("10.99"));
        database.add(donaFlorindaPie);
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(this.database);
    }

    @Override
    public int totalOfItems() {
        return this.getMenuItems().size();
    }

    @Override
    public void add(MenuItem menuItem) {
        this.database.add(menuItem);
    }

    @Override
    public Optional<MenuItem> getById(Long id) {
        return this.database.stream().filter(menuItem -> menuItem.id().equals(id)).findFirst();
    }

    @Override
    public boolean removeById(Long id) {
        try {
            Optional<MenuItem> menuItemOptional = this.getById(id);
            if (menuItemOptional.isEmpty()) return false;
            this.database.remove(menuItemOptional.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePriceById(Long id, BigDecimal newPrice) {
            Optional<MenuItem> menuItemOptional = this.getById(id);
            if (menuItemOptional.isEmpty()) return false;
            MenuItem menuItem = menuItemOptional.get();
            this.database.remove(menuItem);
            MenuItem newMenuItem = new MenuItem(menuItem.id(), menuItem.name(), menuItem.description(), menuItem.category(), newPrice, menuItem.priceWithDiscount());
            this.database.add(newMenuItem);
            priceHistory.put(menuItem, newPrice);
            return true;
    }

    public void printPriceHistory() {
        System.out.println("Histórico de preços:");
        this.priceHistory.forEach((item, price) -> {
            System.out.printf("%s: %s => %s \n", item.name(), item.price(), price);
        });
    }

}
