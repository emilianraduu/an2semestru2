package controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionController {
    private String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "system";
    private String password = "oracle";

    public ConnectionController() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            java.sql.Connection con = DriverManager.getConnection(URL,user,password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from members");
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
