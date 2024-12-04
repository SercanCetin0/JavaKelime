package VeriTabani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Word implements Crud<Word>{
	 	private int categoryId;  // Kategori ID'si
	    public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTr() {
			return tr;
		}

		public void setTr(String tr) {
			this.tr = tr;
		}

		public String getEn() {
			return en;
		}

		public void setEn(String en) {
			this.en = en;
		}

		public String getWordImage() {
			return wordImage;
		}

		public void setWordImage(String wordImage) {
			this.wordImage = wordImage;
		}

		private int id;          // ID
	    private String tr;       // Türkçe
	    private String en;       // İngilizce
	    private String wordImage; // Kelime Görseli
	@Override
	public Boolean Create(Word item) {
return true;		
	}

	@Override
	public Boolean Update(Word item) {
		return true;
		
	}

	@Override
	public Boolean Delete(int id) {
		return true;
	}

	@Override
	public List<Word> Select(int i) {
		List<Word> wordList = new ArrayList<>();
	    ConnectDB db = new ConnectDB();
	    Connection conn = db.getConnection();
	    
	    String sql = "SELECT * FROM Words"; // Assuming you're fetching from Users table.

	    try {
	        PreparedStatement statement = conn.prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Word word = new Word(); // Assuming you have a User class with a default constructor.
	            word.setId(resultSet.getInt("id")); // Adjust the field names based on your table structure.
	            word.setTr(resultSet.getString("tr"));
	            word.setEn(resultSet.getString("en"));

	            word.setWordImage(resultSet.getString("wordImage"));
	            // Set other user properties as needed.

	            wordList.add(word);
	        }

	        return wordList.isEmpty() ? null : wordList; // Return null if no users found, otherwise return the list.
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle exception appropriately in production code.
	        return null;
	    } finally {
	        db.closeConnection(conn);
	    }
	}

	@Override
	public Word ListSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
