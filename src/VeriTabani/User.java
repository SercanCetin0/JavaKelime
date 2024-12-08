package VeriTabani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements IUser {
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
	public Boolean Update(User user) {
	    boolean isUpdated = false;

	    ConnectDB db = new ConnectDB();
	    Connection conn = db.getConnection();
	    PreparedStatement pstmt = null;

	    try {
	        String sql = "UPDATE Users SET FirstName_LastName = ?, email = ?, Status = ? WHERE id = ?";

	        pstmt = conn.prepareStatement(sql);

	        // Parametreleri ayarla
	        pstmt.setString(1, user.getFirstNameLastName());
	        pstmt.setString(2, user.getEmail());
	        pstmt.setString(3, user.getStatus());
	        pstmt.setInt(4, user.getId());

	        // Sorguyu çalıştır
	        int affectedRows = pstmt.executeUpdate();

	        // Eğer herhangi bir satır güncellendiyse başarılı
	        isUpdated = affectedRows > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return isUpdated;
	}

	@Override
	public Boolean Delete(int id) {
		  boolean isDeleted = false;

		    ConnectDB db = new ConnectDB();
		    Connection conn = db.getConnection();
		    PreparedStatement pstmt = null;

		    try {
		        String sql = "DELETE FROM Users WHERE id = ?";

		        pstmt = conn.prepareStatement(sql);

		        // Parametreyi ayarla
		        pstmt.setInt(1, id);

		        // Sorguyu çalıştır
		        int affectedRows = pstmt.executeUpdate();

		        // Eğer herhangi bir satır silindiyse başarılı
		        isDeleted = affectedRows > 0;

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmt != null) pstmt.close();
		            if (conn != null) conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    return isDeleted;
		
	}
	@Override
	public List<User> Select() {
        List<User> userList = new ArrayList<>();
        					
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Users";
           
            pstmt = conn.prepareStatement(sql);

           

            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstNameLastName(rs.getString("FirstName_LastName"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getString("Status"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return userList;
    }

	
	
	
	@Override
	public User ListSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserLoginResult UserLoginControl() {
	    ConnectDB db = new ConnectDB();
	    Connection conn = db.getConnection();

	    String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, this.email);
	        statement.setString(2, this.password);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String status = resultSet.getString("Status");
	            return new UserLoginResult(id, status);
	        } else {
	            return null; // No user found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle exception appropriately in production code.
	        return null;
	    } finally {
	       
	    }
	}

	
	
}
