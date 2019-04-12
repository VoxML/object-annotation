package field.impl;

import field.StringRestrictedListField;

public class Type extends StringRestrictedListField {
    static String key = "type";
    public Type() {
        super(key, 3, new String[]{"","physobj","artifact","human"});
    }
}
