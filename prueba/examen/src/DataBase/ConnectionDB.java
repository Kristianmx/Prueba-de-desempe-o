package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://b9vcqk173yhmvlp7pkrj-mysql.services.clever-cloud.com:3306/b9vcqk173yhmvlp7pkrj";
            String user = "ufqigzxe3jqahysv";
            String password = "TKlYzokhTQrDSkrW196s";
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");

        }catch (ClassNotFoundException error) {
            System.out.println("ERROR de Driver: " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR: " + error.getMessage());
        }
        return objConnection;
    }
    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Conexión Cerrada");
            }
        } catch (SQLException error) {
            System.out.println("ERROR:" + error.getMessage());

        }
    }
}
