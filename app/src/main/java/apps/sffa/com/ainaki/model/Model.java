package apps.sffa.com.ainaki.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr-code on 6/26/2018.
 */

public class Model {

    private int id;

    private Map<String, ?> attrs;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setAttrs(HashMap<String, ?> attrs) {
        this.attrs = attrs;
    }

    public Map<String, ?> getAttrs() {
        return attrs;
    }


    public Model(int id) {
        this.setId(id);
    }

    @Override
    public String toString() {
        return this.getId() + " :: " + Arrays.toString(getAttrs().entrySet().toArray());
    }
}
