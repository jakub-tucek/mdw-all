package cvut.fit.mdw;

import java.sql.*;

public class Main {

    public static void init(Connection conn) throws SQLException {

        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "FIRSTTABLE", null);
        if (rs.next()) {
            System.out.println("Table " +  rs.getString(3) + " exists");
        } else {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE FIRSTTABLE (ID INT PRIMARY KEY, NAME VARCHAR(12))";
            stmt.executeUpdate(query);
            stmt.close();

            stmt = conn.createStatement();
            query = "INSERT INTO FIRSTTABLE VALUES (10,'TEN'),(20,'TWENTY'),(30,'THIRTY')";
            stmt.executeUpdate(query);
            stmt.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();
        String protocol = "jdbc:derby:";
        Connection conn = DriverManager.getConnection(protocol + "derbyDB;create=true");
        init(conn);

        Statement stmt = conn.createStatement();
        String query = "SELECT ID, NAME FROM FIRSTTABLE";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println("ID" + rs.getInt("ID") + " NAME: " + rs.getString("NAME") );
        }
        stmt.close();
        conn.close();
    }
}
