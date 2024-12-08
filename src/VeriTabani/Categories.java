package VeriTabani;

import java.util.List;

public class Categories implements ICategories{
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
	public boolean isActivated() {
		return isActivated;
	}
	public void setActivated(boolean isActivated) {

		
		this.isActivated = isActivated;
	}

	
	private int id;
    private String firstNameLastName;
    private String email;
    private String password;
    private boolean isActivated;
   
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
	public List<Categories> Select(int userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Categories ListSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
