package rdziuba.dev.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();

        MenuItem chavesDrink = new MenuItem(1L, "Refresco do Chaves", """
                Suco de Limão, que parece tamarindo, mas tem gosto de groselha
                """, MenuItem.MenuCategory.DRINKS, new BigDecimal("2.99"), null);
        items.add(chavesDrink);
        MenuItem chavesSandwich = new MenuItem(2L, "Sanduiche de presunto do Chaves", "Sanduiche de presunto simples, mas feito com muito amor", MenuItem.MenuCategory.MAIN_COURSE, new BigDecimal("3.50"), new BigDecimal("2.99"));
        items.add(chavesSandwich);
        MenuItem donaFlorindaPie = new MenuItem(5L, "Torta de Frango da Dona Florinda", "Torta de frnago com recheio cremoso e massa crocante", MenuItem.MenuCategory.MAIN_COURSE, new BigDecimal("12.99"), new BigDecimal("10.99"));
        items.add(donaFlorindaPie);

        return items;
    }

    public Optional<MenuItem> getById(Long id) {
        List<MenuItem> menuItems = this.getMenuItems();
        return menuItems.stream().filter(menuItem -> menuItem.id().equals(id)).findFirst();
    }

}
