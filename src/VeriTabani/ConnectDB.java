package VeriTabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String URL = "jdbc:mysql://localhost:3306/kelimesayar"; // veritabanı isminizi yazın
    private static final String USER = "root"; // MySQL kullanıcı adınızı yazın
    private static final String PASSWORD = ""; // MySQL şifrenizi yazın

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // MySQL sürücüsünü yükleyin
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Veritabanına bağlanın
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            //System.out.println("Bağlantı başarılı!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver bulunamadı!");
            e.printStackTrace();
        } catch (SQLException e) {
           // System.out.println("Bağlantı hatası!");
            e.printStackTrace();
        } finally {
          //  closeConnection(conn); // Bağlantıyı kapat
        }
        return conn; // Bağlantıyı döndürme
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
              //  System.out.println("Bağlantı kapatıldı.");
            } catch (SQLException e) {
                System.out.println("Bağlantı kapatılamadı!");
                e.printStackTrace();
            }
        }
    }
}
