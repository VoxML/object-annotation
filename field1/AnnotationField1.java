package field1;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AnnotationField1<T> extends AnnotationComponent {
    protected String key;
    protected ArrayList<String> valueStrings = new ArrayList<String>();
    protected HashMap<String, ArrayList<String>> map;

    public AnnotationField1()
    {
        this.key = null;
        this.bounds = null;
        this.prev = null;
        this.next = null;
        this.panel = null;
        this.map = null;
        updateLocation();
    }

    public AnnotationField1(String key, Rectangle bounds)
    {
        this.key = key;
        this.bounds = bounds;
        if(prev != null)
            this.prev.next = this;
        if(next != null)
            this.next.prev = this;
        updateLocation();
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
    public void setValueStrinsg(ArrayList<String> valueStrings)
    {
        this.valueStrings = valueStrings;
    } //to be overridden by each field

}
