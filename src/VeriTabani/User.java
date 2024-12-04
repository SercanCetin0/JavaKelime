package VeriTabani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User implements Crud<User> {
	private int id;                // Kullanıcı ID
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstNameLastName() {
		return firstNameLastName;
	}
	public void setFirstNameLastName(String firstNameLastName) {
		this.firstNameLastName = firstNameLastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsActivated() {
		return isActivated;
	}
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
	
	private String firstNameLastName; // Kullanıcı adı ve soyadı
    private String email;          // Kullanıcı e-posta adresi
    private String password;       // Kullanıcı şifresi
    private boolean isActivated;   // Kullanıcının etkin olup olmadığı
	private String Status;
    
    public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
    public Boolean Create(User item) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sql = "INSERT INTO Users (Email, Password, isActivated, FirstName_LastName,Status) VALUES (?, ?, ?, ?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, item.getEmail()); // Use item properties
            statement.setString(2, item.getPassword());
            statement.setBoolean(3, item.getIsActivated());
            statement.setString(4, item.getFirstNameLastName());
            statement.setString(5, item.getStatus());
            int rowsAffected = statement.executeUpdate(); // Use executeUpdate for insert

            return rowsAffected > 0; // Return true if a row was inserted
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately in production code.
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }
	@Override
	public Boolean Update(User item) {
return true;		
	}
	@Override
	public Boolean Delete(int id) {
		return true;		

		
	}
	@Override
	public List<User> Select(int userid) {

		
		
		return null;
	}
	@Override
	public User ListSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public int UserLoginControl() {
	    ConnectDB db = new ConnectDB();
	    Connection conn = db.getConnection();

	    String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?"; 

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, this.email);
	        statement.setString(2, this.password); 

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getInt("id");
	        } else {
	        	return 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle exception appropriately in production code.
	        return 0;
	    } 
	    finally {
			db.closeConnection(conn);
		}
	}
	
	
}
