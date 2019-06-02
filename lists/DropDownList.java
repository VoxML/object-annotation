package lists;

import buttons.*;
import field.AnnotationComponent;
import field.DropDown;
import field.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DropDownList extends FieldList {

    protected LinkedList<DropDown> list;
    protected String[] options;

    public DropDownList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min,
                        JPanel panel, HashMap<String, ArrayList<String>> map, AddButton add, String[] options,
                        AnnotationComponent prev, HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, set);
        this.setSet(set);
        set.add(this);
        this.list = new LinkedList<DropDown>();
        this.options = options;
        LinkedList<field.TextField> indices = new LinkedList<field.TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public DropDownList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, JPanel panel,
                        HashMap<String, ArrayList<String>> map, AddButton add, String[] options,
                        AnnotationComponent prev, HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, set);
        this.setSet(set);
        set.add(this);
        this.list = new LinkedList<DropDown>();
        this.options = options;
        LinkedList<field.TextField> indices = new LinkedList<field.TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public void updateLocation() {
        super.updateLocation();
        if (getList() != null && getList().size() > 0) {
            this.bounds = getList().getLast().getDropdown().getBounds();
            getList().get(0).updateLocation();
            if (getList().get(0).getPrev() != null)
                getList().get(0).getPrev().updateLocation();
        }
        if(getMap() != null)
            getMap().put(getKey(),getValueStrings());
    }

    public int getSize()
    {
        if(getList() !=null)
            return getList().size();
        return -1;
    }

    public void add() {
        if(size < max) {
            if (size > 0) {
                getList().add(new DropDown(getKey() + "[" + size + "]", getOptions(), new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), getList().get(size - 1), getPanel(), getMap(), getSet()));
                if (indexBool)
                    getIndices().add(new field.TextField(getKey() + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), getList().get(size - 1), getPanel(), getMap(), getSet()));
                if (removeBool) {
                    if (!indexBool)
                        getRemove().add(new RemoveButton(getList().get(size - 1), new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                    else
                        getRemove().add(new RemoveButton(getList().get(size - 1), new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                }
            } else {
                getList().add(new DropDown(getKey() + "[" + size + "]", getOptions(), new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), getPrev(), getPanel(), getMap(), getSet()));
                if (indexBool)
                {
                    if(getIndices() ==null)
                        indices = new LinkedList<field.TextField>();
                    getIndices().add(new field.TextField(getKey() + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), getPrev(), getPanel(), getMap(), getSet()));
                }
                if (removeBool) {
                    if(getRemove() ==null)
                        remove = new LinkedList<RemoveButton>();
                    if (!indexBool)
                        getRemove().add(new RemoveButton(getPrev(), new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                    else
                        getRemove().add(new RemoveButton(getPrev(), new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                }
            }
            size++;
            if(getList() != null && getList().size() > 0)
                this.bounds = getList().getLast().getBounds();
            updateLocation();
            if(removeBool && getRemove() != null && getRemove().size() > 0)
                ((RemoveButton) getRemove().get(0)).updateLocation();
            if(indexBool && getIndices() != null && getIndices().size() > 0)
                ((field.TextField) getIndices().get(0)).updateLocation();
            if(getList() != null && getList().size() > 0) {
                this.bounds = getList().getLast().getBounds();
            }
            if(getList() != null && getList().size() > 0) {
                getList().getLast().getDropdown().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
            if(getIndices() != null && getIndices().size() > 0)
            {
                ((TextField) getIndices().getLast()).getTextfield().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
        }
    }

    public void remove(int index)
    {
        if(size > index && size > min) {
            DropDown toRemove = getList().get(index);
            for(int i = index+1; i < getList().size(); i++)
            {
                getList().get(i).setKey(getKey() + "[" + (i-1) + "]");
                if(removeBool)
                    ((RemoveButton)(getRemove().get(i))).setIndex(i-1);
            }
            if(getList().size()>index+1) {
                getList().get(index + 1).setPrev(toRemove.getPrev());
                if(indexBool)
                    ((field.TextField) getIndices().get(index+1)).setPrev(toRemove.getPrev());
                if(removeBool)
                    ((RemoveButton) getRemove().get(index+1)).setPrev(toRemove.getPrev());
            }
            toRemove.getDropdown().setVisible(false);
            getPanel().remove(toRemove.getDropdown());
            getList().remove(index);
            getSet().remove(toRemove);
            if (indexBool) {
                field.TextField indexToRemove = (field.TextField) getIndices().get(index);
                indexToRemove.getTextfield().setVisible(false);
                getPanel().remove(indexToRemove.getTextfield());
                getIndices().remove(index);
            }
            if (removeBool) {
                RemoveButton removeButton = (RemoveButton) getRemove().get(index);
                removeButton.getButton().setVisible(false);
                getPanel().remove(removeButton.getButton());
                getRemove().remove(index);
            }
            size--;
            updateLocation();
        }
    }

    public LinkedList<DropDown> getList() { return list; }

    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(getList().get(i).getValueStrings() != null && getList().get(i).getValueStrings().size()>0 && !getList().get(i).getValueStrings().get(0).equals("")) {
                String result_i = getList().get(i).getValueStrings().get(0);
                if(indexBool && ((TextField)(getIndices().get(i))).getValueStrings().size() > 0)
                    result_i += "[" + ((TextField)(getIndices().get(i))).getValueStrings().get(0) + "]s";
                result.add(result_i);
            }
        }
        if(getMap() != null)
            getMap().put(getKey(), result);
        return result;
    }

    public String toString()
    {
        return "Drop down list " + getKey() + " of size " + size;
    }

    public String[] getOptions() {
        return options;
    }
}
