package field;

import java.awt.*;
import java.util.*;

public class AnnotationField<T> extends AnnotationComponent {
    public String key;
    public ArrayList<String> valueStrings = new ArrayList<String>();
    public HashMap<String, ArrayList<String>> map;

    public AnnotationField(ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = null;
        this.bounds = null;
        this.prev = null;
        this.panel = null;
        this.map = null;
        updateLocation();
    }

    public AnnotationField(ArrayList<AnnotationComponent> set, String key, Rectangle bounds)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        updateLocation();
    }

    public void updateLocation()
    {
        super.updateLocation();
        /*
        if(map != null) {
            if (map.containsKey("Intrinsic"))
                map.put("Intr", map.get("Intrinsic"));
            if (map.containsKey("Extrinsic"))
                map.put("Extr", map.get("Extrinsic"));
            map.put("Habitat", null);
        }
        */
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

}
