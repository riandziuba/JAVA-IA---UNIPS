package entities;

import org.json.JSONObject;

import java.math.BigDecimal;

public record MenuItem(Long id, String name, String description, MenuCategory category, BigDecimal price,
                       BigDecimal priceWithDiscount) {

    public JSONObject toJson() {
        return new JSONObject()
                .put("id", id)
                .put("name", name)
                .put("description", description)
                .put("category", category.name())
                .put("price", price)
                .put("priceWithDiscount", priceWithDiscount == null ? JSONObject.NULL : priceWithDiscount);
    }

    public static MenuItem fromJson(JSONObject itemJson) {
        BigDecimal priceWithDiscount = itemJson.isNull("priceWithDiscount")
                ? null
                : new BigDecimal(itemJson.get("priceWithDiscount").toString());

        return new MenuItem(
                itemJson.getLong("id"),
                itemJson.getString("name"),
                itemJson.getString("description"),
                MenuCategory.valueOf(itemJson.getString("category")),
                new BigDecimal(itemJson.get("price").toString()),
                priceWithDiscount
        );
    }

    public enum MenuCategory {
        APPETIZER, MAIN_COURSE, DRINKS, DESSERT
    }

}
