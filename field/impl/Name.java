package field.impl;

import field.StringField;

public class Name extends StringField {

    static String key = "name";

    public Name() {
        super(key);
    }

    public Name(String value) {
        super(key, value);
    }
}
