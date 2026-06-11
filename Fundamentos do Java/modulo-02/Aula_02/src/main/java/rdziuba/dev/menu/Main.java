package rdziuba.dev.menu;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    static void main(String[] args) {
        Database database = new Database();
        List<MenuItem> menuItems = database.getMenuItems();

        for (MenuItem item : menuItems) {
            System.out.println(item);
        }

        MenuItem menuItem = menuItems.get(2);
        System.out.println(menuItem.name());

        System.out.println(menuItems.size());
        menuItems.remove(1);
        System.out.println(menuItems.size());

        menuItems.forEach(System.out::println);

        System.out.println("------------");
        Optional<MenuItem> item = database.getById(5L);
        String responseText = item.map(MenuItem::toString).orElse("Item não encontrado");
        System.out.println(responseText);
        System.out.println("------------");
        Optional<MenuItem> item1 = database.getById(3L);
        responseText = item1.map(MenuItem::toString).orElse("Item não encontrado");
        System.out.println(responseText);

        Set<MenuItem.MenuCategory> itemsOnSale = EnumSet.of(MenuItem.MenuCategory.DESSERT, MenuItem.MenuCategory.APPETIZER);
        itemsOnSale.add(MenuItem.MenuCategory.MAIN_COURSE);
        itemsOnSale.forEach(System.out::println);

        System.out.println("-----");

        Map<MenuItem.MenuCategory, String> sales = new EnumMap<>(MenuItem.MenuCategory.class);
        sales.put(MenuItem.MenuCategory.DESSERT, "O doce perfeito para você!");
        sales.put(MenuItem.MenuCategory.APPETIZER, "Começe sua refeição com um toque de sabor!");
        System.out.println(sales);

        System.out.println(database.getMenuItems());
        System.out.println("-------");
        long idForRemove = 1L;
        boolean isRemoved = database.removeById(idForRemove);
        System.out.println(isRemoved ? "Removido com sucesso" : "Não foi removido");
        System.out.println(database.getMenuItems());
        // Weakhashmap has weak relation that can be clear with the gc
        database.updatePriceById(2L, new BigDecimal("3.99"));
        database.updatePriceById(2L, new BigDecimal("3.50"));
        database.updatePriceById(2L, new BigDecimal("4.99"));
        database.printPriceHistory();
    }
}
