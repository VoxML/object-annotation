package lists;

import buttons.AddButton;
import buttons.RemoveButton;
import field.AnnotationComponent;
import field.AnnotationField;
import field.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FieldList<E extends AnnotationField> extends AnnotationField
{
    public LinkedList<E> list;
    public LinkedList<RemoveButton> remove;
    public AddButton add;
    protected LinkedList<TextField> indices;
    protected String value;
    public ArrayList<String> valueStrings;
    protected boolean removeBool;
    protected boolean indexBool;
    protected int max;
    protected int min;
    protected int size;

    public FieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                     HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev, AnnotationComponent next,
                     HashSet<AnnotationComponent> set)
    {
        super(set,key,bounds);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.list = new LinkedList<E>();
        this.bounds = bounds;
        this.removeBool = removeBool;
        this.indexBool = indexBool;
        this.prev = prev;
        this.max = max;
        this.min = min;
        this.size = min;
        this.panel = panel;
        this.map = map;
        if(add != null)
            add.setList(this);
        this.add = add;
        updateLocation();
    }
    public void updateLocation() {
        super.updateLocation();
        if (list != null && list.size() > 0) {
            list.get(0).updateLocation();
            if (list.get(0).prev != null)
                list.get(0).prev.updateLocation();
        }
        if(map != null)
            map.put(key,getValueStrings());
    }
    public void add() {
        System.out.println("add method: To be implemented in subclasses.");
    }
    public void remove(int index)
    {
        System.out.println("remove method: To be implemented in subclasses.");
    }
    public int getSize() {return size; }

    public LinkedList<E> getList()
    {
        return list;
        //to be implemented in subclasses
    }

    public ArrayList<String> getValueStrings()
    {
        return valueStrings;
    }
}