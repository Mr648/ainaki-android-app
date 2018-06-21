package apps.sffa.com.ainaki.model.response;

/**
 * Created by Diako on 19/06/2018.
 */

public class GeneralResponse {

    private boolean error;
    private String message;




    public boolean hasError() {
        return this.error;
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



}
