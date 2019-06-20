package lists;

import buttons.*;
import field.AnnotationComponent;
import field.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TextFieldList extends FieldList {

    protected LinkedList<field.TextField> list;

    public TextFieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev, HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, set);
        this.setSet(set);
        if(set != null)
            set.add(this);
        this.list = new LinkedList<field.TextField>();
        LinkedList<field.TextField> indices = new LinkedList<field.TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public void updateLocation() {
        super.updateLocation();
        if (list != null && list.size() > 0) {
            this.bounds = list.getLast().getTextfield().getBounds();
            list.get(0).updateLocation();
            if (list.get(0).getPrev() != null)
                list.get(0).getPrev().updateLocation();
        }
        if(getMap() != null)
            getMap().put(getKey(),getValueStrings());
}

    public void add() {
        if(size < max) {
            if (size > 0) {
                list.add(new field.TextField(getKey() + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), list.get(size - 1), getPanel(), getMap(), getSet()));
                if (indexBool)
                    super.getIndices().add(new field.TextField(getKey() + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), list.get(size - 1), getPanel(), getMap(), getSet()));
                if (removeBool) {
                    if (!indexBool)
                        getRemove().add(new RemoveButton(list.get(size - 1), new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                    else
                        getRemove().add(new RemoveButton(list.get(size - 1), new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, getPanel(), getSet()));
                }
            } else {
                list.add(new field.TextField(getKey() + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), getPrev(), getPanel(), getMap(), getSet()));
                if (indexBool)
                {
                    if(super.getIndices() ==null)
                        indices = new LinkedList<field.TextField>();
                    super.getIndices().add(new field.TextField(getKey() + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
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
            if(list != null && list.size() > 0) {
                this.bounds = list.getLast().getBounds();
            }
            if(list != null && list.size() > 0) {
                list.getLast().getTextfield().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
            if(super.getIndices() != null && super.getIndices().size() > 0)
            {
                ((TextField) super.getIndices().getLast()).getTextfield().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
            updateLocation();
            if(removeBool && getRemove() != null && getRemove().size() > 0)
                ((RemoveButton) getRemove().get(0)).updateLocation();
            if(indexBool && super.getIndices() != null && super.getIndices().size() > 0)
                ((field.TextField) super.getIndices().get(0)).updateLocation();
        }
    }

    public void remove(int index)
    {
        if(size > 0 && size > min) {
            field.TextField field = list.get(index);
            field.getTextfield().setVisible(false);
            getPanel().remove(field.getTextfield());
            list.remove(index);
            for(int i = 0; i < list.size(); i++)
            {
                if(i > 0)
                    list.get(i).setPrev(list.get(i-1));
                else
                    list.get(i).setPrev(this.getPrev());
                list.get(i).updateLocation();
            }
            if (indexBool) {
                field.TextField indexField = (field.TextField) super.getIndices().get(index);
                indexField.getTextfield().setVisible(false);
                getPanel().remove(indexField.getTextfield());
                super.getIndices().remove(index);
                for (int i = 0; i < super.getIndices().size(); i++) {
                    if (i > 0)
                        ((field.TextField) (super.getIndices().get(i))).setPrev((field.TextField) super.getIndices().get(i - 1));
                    else
                        ((field.TextField) (super.getIndices().get(i))).setPrev(this.getPrev());
                    ((field.TextField) super.getIndices().get(i)).updateLocation();
                }
            }
            if (removeBool) {
                RemoveButton removeButton = (RemoveButton) getRemove().get(index);
                removeButton.getButton().setVisible(false);
                getPanel().remove(removeButton.getButton());
                getRemove().remove(index);
                for (int i = 0; i < getRemove().size(); i++) {
                    if (i > 0)
                        ((RemoveButton) (getRemove().get(i))).setPrev((RemoveButton) getRemove().get(i - 1));
                    else
                        ((RemoveButton) (getRemove().get(i))).setPrev(this.getPrev());
                    ((RemoveButton) (getRemove().get(i))).setIndex(i);
                    ((RemoveButton) getRemove().get(i)).updateLocation();
                }
            }
            if(list.size()>0)
                this.bounds = list.getLast().getBounds();
            size--;
            updateLocation();
        }
    }

    public LinkedList<field.TextField> getList() { return list; }

    public LinkedList<field.TextField> getIndices() {return super.getIndices(); }

    public void setAddButton(AddButton add) {this.add = add; }

    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.size() > i && list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = list.get(i).getValueStrings().get(0);
                if(indexBool && ((TextField)(super.getIndices().get(i))).getValueStrings().size() > 0)
                    result_i += "[" + ((TextField)(super.getIndices().get(i))).getValueStrings().get(0) + "]";
                result.add(result_i);
            }
        }
        if(getMap() != null)
            getMap().put(getKey(), result);
        return result;
    }

    public String toString()
    {
        return "Text field list " + getKey() + " of size " + size;
    }
}
