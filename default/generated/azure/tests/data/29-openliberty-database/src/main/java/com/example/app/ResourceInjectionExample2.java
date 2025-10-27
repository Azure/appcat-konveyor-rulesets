package com.example.app;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
import javax.ejb.Stateless;

@Stateless
public class ResourceInjectionExample2 {

    // DataSource injection using @Resource annotation
    @Resource(name = "jdbc/mydb")
    private DataSource dataSource;
    
    // Multiple DataSource injections
    @Resource(name = "jdbc/secondaryDb")
    private DataSource secondaryDataSource;
    
    public void doSomethingWithDB() {
        try (var connection = dataSource.getConnection()) {
            // Database operations
            var stmt = connection.prepareStatement("SELECT * FROM users");
            var rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Process results
                String name = rs.getString("name");
                // Do something with the data
            }
        } catch (Exception e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }
} 