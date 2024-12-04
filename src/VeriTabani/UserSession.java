package VeriTabani;

public class UserSession {
    private static UserSession instance; // Singleton örneği
    private String userId;
    private String userRol;
    public String getUserRol() {
		return userRol;
	}

	public void setUserRol(String userRol) {
		this.userRol = userRol;
	}

	// Private constructor to prevent instantiation
    private UserSession() {}

    // Singleton getInstance metodu
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // UserID'yi al ve set et
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
