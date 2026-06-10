package rdziuba.dev.menu;
import java.math.BigDecimal;

public record MenuItem(Long id, String name, String description, MenuCategory category, BigDecimal price,
                       BigDecimal priceWithDiscount) {

    public enum MenuCategory {
        APPETIZER, MAIN_COURSE, DRINKS, DESSERT
    }

}
