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
    protected LinkedList<E> list;
    protected LinkedList<RemoveButton> remove;
    protected AddButton add;
    protected LinkedList<TextField> indices;
    protected ArrayList<String> valueStrings;
    protected boolean removeBool;
    protected boolean indexBool;
    protected int max;
    protected int min;
    protected int size;
    protected AnnotationComponent label;

    public FieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                     HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev, HashSet<AnnotationComponent> set)
    {
        super(set,key,bounds);
        this.setSet(set);
        set.add(this);
        this.list = new LinkedList<E>();
        this.bounds = bounds;
        this.removeBool = removeBool;
        this.indexBool = indexBool;
        this.setPrev(prev);
        this.max = max;
        this.min = min;
        this.size = min;
        this.setPanel(panel);
        this.map = map;
        if(add != null)
            add.setList(this);
        this.add = add;
        updateLocation();
    }
    public void updateLocation() {
        super.updateLocation();
        if (getList() != null && getList().size() > 0) {
            getList().get(0).updateLocation();
            if (getList().get(0).getPrev() != null)
                getList().get(0).getPrev().updateLocation();
        }
        if (getIndices() != null && getIndices().size() >  0) {
            getIndices().get(0).updateLocation();
            if (getIndices().get(0).getPrev() != null)
                getIndices().get(0).getPrev().updateLocation();
        }
        if (getRemove() != null && getRemove().size() >  0) {
            getRemove().get(0).updateLocation();
            if (getRemove().get(0).getPrev() != null)
                getRemove().get(0).getPrev().updateLocation();
        }
        if(getMap() != null)
            getMap().put(getKey(),getValueStrings());
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

    public LinkedList<RemoveButton> getRemove() {
        return remove;
    }

    public AddButton getAdd() {
        return add;
    }

    public void setAdd(AddButton add) { this.add = add; }

    public LinkedList<TextField> getIndices() {
        return indices;
    }

    public void setLabel(AnnotationComponent label)
    {
        this.label = label;
    }

    public AnnotationComponent getLabel()
    {
        return label;
    }

}