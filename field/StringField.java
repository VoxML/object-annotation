package field;

public class StringField extends AnnotationField<String>  {

    public StringField(String key) {
        super(key, "");
    }

    public StringField(String key, String value) {
        super(key, value);
    }

}


