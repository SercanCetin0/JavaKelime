package VeriTabani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categories implements ICategories{

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCateName() {
		return CateName;
	}
	public void setCateName(String cateName) {
		CateName = cateName;
	}
	private int id;
    private String CateName;
  
   
	@Override
	public Boolean Create(Categories item) {
		try (Connection conn = new ConnectDB().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Categories (CateName) VALUES (?)")) {
	            pstmt.setString(1, item.CateName);
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
		
	}
	@Override
	public Boolean Update(Categories item) {
		try (Connection conn = new ConnectDB().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE Categories SET CateName = ? WHERE id = ?")) {
	            pstmt.setString(1, item.CateName);
	            pstmt.setInt(2, item.id);
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
		
	}
	@Override
	public Boolean Delete(int id) {
		try (Connection conn = new ConnectDB().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Categories WHERE id = ?")) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }		
	}
	@Override
	public List<Categories> Select() {
	    List<Categories> CatList = new ArrayList<>();
		
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Categories";
           
            pstmt = conn.prepareStatement(sql);

           

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	Categories category = new Categories();
            	category.setId(rs.getInt("id"));
            	category.setCateName(rs.getString("CateName"));
            	CatList.add(category);
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

        return CatList;
	}
	@Override
	public Categories ListSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
