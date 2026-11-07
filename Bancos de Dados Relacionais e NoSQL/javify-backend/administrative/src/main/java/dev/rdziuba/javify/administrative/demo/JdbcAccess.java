package dev.rdziuba.javify.administrative.demo;

import dev.rdziuba.javify.administrative.domain.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class JdbcAccess {
    @Value("${spring.datasource.url}") String url;
    @Value("${spring.datasource.username}") String user;
    @Value("${spring.datasource.password}") String pass;


    public void execute() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);


            ps = conn.prepareStatement("SELECT * FROM adress WHERE postal_code = ?");
            ps.setString(1, "01508000");
            rs = ps.executeQuery();


            Address address = null;
            if (rs.next()) {
                address = new Address();
                address.setPostalCode(rs.getString("postal_code"));
                address.setStreet(rs.getString("street"));
                address.setNeighborhood(rs.getString("neighborhood"));
                System.out.printf("Address with JDBC: %s \n ", address);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
