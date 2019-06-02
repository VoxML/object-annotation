package field;

import java.awt.*;
import java.util.*;

public class AnnotationField<T> extends AnnotationComponent {
    protected String key;
    protected ArrayList<String> valueStrings = new ArrayList<String>();
    protected HashMap<String, ArrayList<String>> map;

    public AnnotationField(HashSet<AnnotationComponent> set)
    {
        super(set);
        this.setSet(set);
        set.add(this);
        this.key = null;
        this.bounds = null;
        this.setPrev(null);
        this.setPanel(null);
        this.map = null;
        updateLocation();
    }

    public AnnotationField(HashSet<AnnotationComponent> set, String key, Rectangle bounds)
    {
        super(set);
        this.setSet(set);
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        updateLocation();
    }

    public void updateLocation()
    {
        super.updateLocation();
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public ArrayList<String> getValueStrings()
    {
        return valueStrings;
    }

    public void setValueStrings(ArrayList<String> valueStrings)
    {
        this.valueStrings = valueStrings;
    } //to be overridden by each field

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }
}
