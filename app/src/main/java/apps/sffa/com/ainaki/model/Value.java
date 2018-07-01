package apps.sffa.com.ainaki.model;

/**
 * Created by mr-code on 6/27/2018.
 */

public class Value {


    private Object value;
    private TYPE type;

    public void setType(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Value(Object value) {
        this.setValue(value);
    }

    private enum TYPE {
        BOOLEAN,
        BYTE,
        INTEGER,
        FLOAT,
        DOUBLE,
        CHAR,
        STRING,
        OBJECT,
        ARRAY,
    }
}
