package db;

import entities.MenuItem;
import org.json.JSONArray;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IDatabase {
    List<MenuItem> getMenuItems();

    int totalOfItems();

    void add(MenuItem menuItem);

    default JSONArray menuItemsToJson() {
        JSONArray itemsJson = new JSONArray();
        for (MenuItem menuItem : this.getMenuItems()) {
            itemsJson.put(menuItem.toJson());
        }
        return itemsJson;
    }

    Optional<MenuItem> getById(Long id);

    boolean removeById(Long id);

    boolean updatePriceById(Long id, BigDecimal newPrice);
}
