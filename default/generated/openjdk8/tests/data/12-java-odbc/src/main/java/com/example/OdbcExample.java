import java.sql.*;

public class OdbcExample {
    public static void main(String[] args) throws Exception {
        Class.forName("sun.JdbcOdbcDriver"); // removed in JDK 8
        Class.forName("sun.jdbc.odbc"); 
        Connection conn = DriverManager.getConnection("jdbc:odbc:MyDSN");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");

        while (rs.next()) {
            System.out.println("Column 1: " + rs.getString(1));
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}

