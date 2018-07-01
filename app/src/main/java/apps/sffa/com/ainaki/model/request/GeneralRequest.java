package apps.sffa.com.ainaki.model.request;

/**
 * Created by mr-code on 6/26/2018.
 */


public class GeneralRequest {


    protected String authKey;

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getAuthKey() {
        return authKey;
    }

    public GeneralRequest(String authKey) {
        this.setAuthKey(authKey);
    }
}
