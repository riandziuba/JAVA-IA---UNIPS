package rdziuba.dev.menu;

import java.util.List;

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
        System.out.println(database.getById(5L));
        System.out.println("------------");
        System.out.println(database.getById(3L));

    }
}
