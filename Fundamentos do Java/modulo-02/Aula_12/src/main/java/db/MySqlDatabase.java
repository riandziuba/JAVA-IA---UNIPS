package db;

import entities.MenuItem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlDatabase implements IDatabase{
    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT id, name, description, category, price, price_with_discount FROM menu_item";
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu", "root", "pass");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String categoryStr = resultSet.getString("category");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal priceWithDiscount = resultSet.getBigDecimal("price_with_discount");
                MenuItem.MenuCategory category = MenuItem.MenuCategory.valueOf(categoryStr);

                MenuItem menuItem = new MenuItem(id, name, description, category, price, priceWithDiscount);
                items.add(menuItem);
            }

            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int totalOfItems() {
        String sql = "SELECT COUNT(*) FROM menu_item";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu", "root", "pass");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(MenuItem menuItem) {
        String sql = "INSERT INTO menu_item (id, name, description, category, price, price_with_discount) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu", "root", "pass");
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, menuItem.id());
            preparedStatement.setString(2, menuItem.name());
            preparedStatement.setString(3, menuItem.description());
            preparedStatement.setString(4, menuItem.category().name());
            preparedStatement.setBigDecimal(5, menuItem.price());
            preparedStatement.setBigDecimal(6, menuItem.priceWithDiscount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MenuItem> getById(Long id) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean removeById(Long id) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean updatePriceById(Long id, BigDecimal newPrice) {
        throw new UnsupportedOperationException("TODO");
    }
}
