package field1;

import field.AnnotationField;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AnnotationField1<T> extends AnnotationComponent {
    protected String key;
    protected ArrayList<String> valueStrings = new ArrayList<String>();
    protected HashMap<String, ArrayList<String>> map;

    public AnnotationField1(ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.key = null;
        this.bounds = null;
        this.prev = null;
        this.next = null;
        this.panel = null;
        this.map = null;
        updateLocation();
    }

    public AnnotationField1(ArrayList<AnnotationComponent> set, String key, Rectangle bounds)
    {
        super(set);
        this.set = set;
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        if(prev != null)
            this.prev.next = this;
        if(next != null)
            this.next.prev = this;
        updateLocation();
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(map != null) {
            if (map.containsKey("Intrinsic"))
                map.put("Intr", map.get("Intrinsic"));
            if (map.containsKey("Extrinsic"))
                map.put("Extr", map.get("Extrinsic"));
            map.put("Habitat", null);
        }
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
