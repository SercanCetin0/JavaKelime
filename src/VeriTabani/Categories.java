package VeriTabani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		return true;
		
	}
	@Override
	public Boolean Update(Categories item) {
		return true;
		
	}
	@Override
	public Boolean Delete(int id) {
		return true;		
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
