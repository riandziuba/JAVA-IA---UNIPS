package rdziuba.dev.menu;

import com.google.gson.Gson;

import java.math.BigDecimal;

public class Main {
    static void main(String[] args) {
        MenuItem chavesDrink = new MenuItem(1L, "Refresco do Chaves", """
                Suco de Limão, que parece tamarindo, mas tem gosto de groselha
                """, MenuItem.MenuCategory.DRINKS, new BigDecimal("2.99"), null);

        Gson gson = new Gson();
        String json = gson.toJson(chavesDrink);

        System.out.println(json);
    }
}
