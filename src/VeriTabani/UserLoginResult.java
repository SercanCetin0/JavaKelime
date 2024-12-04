package VeriTabani;

public class UserLoginResult {
    private int id;
    private String status;

    public UserLoginResult(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
