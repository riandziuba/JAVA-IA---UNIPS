package rdziuba.dev.menu;

import java.util.List;
import java.util.Optional;

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

    }
}
