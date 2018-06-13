package apps.sffa.com.ainaki.model.request;

/**
 * Created by Diako on 26/05/2018.
 */

public class LoginRequest {

    private String userPhone;

    public LoginRequest(String userPhone) {
        this.setUserPhone(userPhone);
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}