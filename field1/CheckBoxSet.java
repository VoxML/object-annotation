package field1;

import buttons.AddButton;
import buttons.RemoveButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CheckBoxSet extends FieldList {
    protected LinkedList<CheckBox> list;

    public CheckBoxSet(String[] names, String key, Rectangle bounds, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AnnotationComponent prev,
                         AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, false, false, Integer.MAX_VALUE, 0, panel, map, null, prev, next, set);
        this.set = set;
        set.add(this);
        this.list = new LinkedList<CheckBox>();
        Rectangle currBounds = bounds;
        for(int i = 0; i < names.length; i++) {
            if(i>0)
                currBounds.x += (bounds.width+10);
            list.add(new CheckBox(key, names[i], currBounds, prev, next, panel, map, set));
        }
    }

    public void updateLocation() {
        super.updateLocation();
        if(list != null && list.size() > 0)
        {
            for(int i = 0; i < list.size(); i++)
                list.get(i).updateLocation();
        }
    }

    public LinkedList<CheckBox> getList() { return list; };

    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                result.add((String) list.get(i).getValueStrings().get(0));
            }
        }
        map.put(key, result);
        return result;
    }

}