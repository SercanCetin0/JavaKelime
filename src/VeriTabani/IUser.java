package VeriTabani;

public interface IUser extends Crud<User> {

	
	public User ListSelectById(int id);
	public UserLoginResult UserLoginControl();
	
}
