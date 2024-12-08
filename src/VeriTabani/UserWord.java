package VeriTabani;

import java.util.List;

public class UserWord implements IUserWord {
	 private int id;       // Tam Metin ID
	    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
		private int userId;   // Kullanıcı ID
	    private int wordId;   // Kelime ID
		@Override
		public Boolean Create(UserWord item) {
					return true;
			
		}
		@Override
		public Boolean Update(UserWord item) {
					return true;
			
		}
		@Override
		public Boolean Delete(int id) {
					return true;
			
		}
		@Override
		public List<UserWord> Select() {
			return null;
		}
		@Override
		public UserWord ListSelectById(int id) {
					
			return null;
		}

}
