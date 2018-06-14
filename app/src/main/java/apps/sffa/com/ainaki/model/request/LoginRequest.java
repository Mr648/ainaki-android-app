package apps.sffa.com.ainaki.model.request;

/**
 * Created by Diako on 26/05/2018.
 */

public class LoginRequest {

    private String phone;

    public LoginRequest(String phone) {
        this.setPhone(phone);
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}