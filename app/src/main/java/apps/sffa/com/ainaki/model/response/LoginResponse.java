package apps.sffa.com.ainaki.model.response;

/**
 * Created by Diako on 26/05/2018.
 */

public class LoginResponse {

    private boolean error;
    private String  message;
    private String  apiToken;


    public boolean hasError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
