package com.example.app;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDILookupExample {

    public Connection getDatabaseConnection() throws NamingException, SQLException {
        // JNDI lookup for datasource
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mydb");
        return dataSource.getConnection();
    }
    
    public Connection getAlternativeDatabaseConnection() throws NamingException, SQLException {
        // Another JNDI lookup with different pattern
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/secondaryDb");
        
        // Implement retry logic for cloud resilience
        int retries = 3;
        SQLException lastException = null;
        
        for (int i = 0; i < retries; i++) {
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                lastException = e;
                try {
                    Thread.sleep(1000 * (i + 1));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }
        }
        
        throw lastException;
    }
} 