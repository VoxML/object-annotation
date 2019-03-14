package field;

import java.util.List;

public class StringRestrictedListField extends StringListField {

    private String[] valueOptions;

    public StringRestrictedListField(String key, int listSize, String[] options) {
        super(key, listSize);
        this.valueOptions = options;
    }
}
