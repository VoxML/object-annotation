package field1;

import buttons.AddButton;
import buttons.RemoveButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FieldList<E extends AnnotationField1> extends AnnotationField1
{
    protected LinkedList<E> list;
    public LinkedList<RemoveButton> remove;
    public AddButton add;
    protected LinkedList<TextField> indices;
    protected String value;
    public ArrayList<String> valueStrings;
    protected boolean removeBool;
    protected boolean indexBool;
    protected int max;
    protected int min;
    protected int size = 0;

    public FieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                      HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev, AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(set,key,bounds);
        this.set = set;
        set.add(this);
        this.list = new LinkedList<E>();
        this.bounds = bounds;
        this.removeBool = removeBool;
        this.indexBool = indexBool;
        this.prev = prev;
        this.next = next;
        if(prev != null)
            prev.next = this;
        if(next != null)
            next.prev = this;
        this.max = max;
        this.min = min;
        this.size = min;
        this.panel = panel;
        this.map = map;
        if(add != null)
            add.setList(this);
        this.add = add;
        this.removeBool = removeBool;
        this.indexBool = indexBool;
        updateLocation();
    }
    public void updateLocation() {
        super.updateLocation();
        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).updateLocation();
            }
        }
    }
    public void add() {
        System.out.println("add method: To be implemented in subclasses.");
    }
    public void remove(int index)
    {
        System.out.println("remove method: To be implemented in subclasses.");
    }
    public int getSize() {return size; }
}