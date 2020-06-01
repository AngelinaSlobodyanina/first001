import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try { // create our mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3307/fruit?useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "Raketa4");
            try {
                viewTable(conn);
            } catch (SQLException ex) {
                System.err.println("SQL Exception 1");
            } finally {
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Class Not Found Exception ");
        } catch (SQLException ex) {
            System.err.println("SQL Exception 2");
        }
    }

    public static void viewTable(Connection con)
            throws SQLException {
        Statement stmt = null;
        String query = "select id, name, color, numb from fruit";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            try {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    String color = rs.getString("color");
                    double numb = rs.getFloat("numb");

                    System.out.println(id + "\t" + name +
                            "\t" + color + "\t" + numb);
                }
            } catch (SQLException ee) {
                System.out.println(ee.getStackTrace());
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}