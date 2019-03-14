package field;

import java.util.ArrayList;
import java.util.List;

public class StringListField extends AnnotationField<List<String>> {

    private int listSize;

    public int getSize() {
        return listSize;
    }

    public void setSize(int listSize) {
        this.listSize = listSize;
    }

    public StringListField(String key, int listSize) {
        super(key, new ArrayList<>());
        this.listSize = listSize;
    }

    public StringListField(String key) {
        super(key, new ArrayList<>());
    }

    public StringListField(String key, List<String> value) {
        super(key, value);
    }

}
