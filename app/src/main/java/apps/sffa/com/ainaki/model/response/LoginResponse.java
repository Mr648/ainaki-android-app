package apps.sffa.com.ainaki.model.response;

/**
 * Created by Diako on 26/05/2018.
 */

public class LoginResponse extends GeneralResponse {


    private boolean isAuthResponse;
    private String authKey;
    private String csrfToken;


    @Override
    public String toString() {
        return hasError() + "\t::" + getMessage();
    }



    public boolean isAuthResponse() {
        return isAuthResponse;
    }

    public void setAuthResponse(boolean authResponse) {
        isAuthResponse = authResponse;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

}
