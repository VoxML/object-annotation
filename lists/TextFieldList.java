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
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                         AnnotationComponent next, HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, next, set);
        this.set = set;
        if(set != null&& !set.contains(this))
            set.add(this);
        this.list = new LinkedList<field.TextField>();
        LinkedList<field.TextField> indices = new LinkedList<field.TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public TextFieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, JPanel panel,
                        HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                         AnnotationComponent next, HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, next, set);
        this.set = set;
        if(set != null && !set.contains(this))
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
            this.bounds = list.getLast().textfield.getBounds();
            list.get(0).updateLocation();
            if (list.get(0).prev != null)
                list.get(0).prev.updateLocation();
        }
        if(map != null)
            map.put(key,getValueStrings());
    }

    public void add() {
        if(size < max) {
            if (size > 0) {
                list.add(new field.TextField(key + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), list.get(size - 1), null, panel, map, set));
                if (indexBool)
                    indices.add(new field.TextField(key + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), (AnnotationComponent) list.get(size - 1), null, panel, map, set));
                if (removeBool) {
                    if (!indexBool)
                        remove.add(new RemoveButton((AnnotationComponent)list.get(size - 1), null, new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                    else
                        remove.add(new RemoveButton((AnnotationComponent)list.get(size - 1), null, new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                }
            } else {
                list.add(new field.TextField(key + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), prev, null, panel, map, set));
                if (indexBool)
                {
                    if(indices==null)
                        indices = new LinkedList<field.TextField>();
                    indices.add(new field.TextField(key + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), prev, null, panel, map, set));
                }
                if (removeBool) {
                    if(remove==null)
                        remove = new LinkedList<RemoveButton>();
                    if (!indexBool)
                        remove.add(new RemoveButton(prev, null, new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                    else
                        remove.add(new RemoveButton(prev, null, new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                }
            }
            size++;
            if(list != null && list.size() > 0) {
                this.bounds = list.getLast().bounds;
            }
            if(list != null && list.size() > 0) {
                list.getLast().textfield.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
            if(indices != null && indices.size() > 0)
            {
                ((TextField)indices.getLast()).textfield.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getValueStrings();
                    }
                });
            }
            updateLocation();
            if(removeBool && remove != null && remove.size() > 0)
                ((RemoveButton)remove.get(0)).updateLocation();
            if(indexBool && indices != null && indices.size() > 0)
                ((field.TextField)indices.get(0)).updateLocation();
        }
    }

    public void remove(int index)
    {
        if(size > 0 && size > min) {
            field.TextField field = list.get(index);
            field.textfield.setVisible(false);
            panel.remove(field.textfield);
            list.remove(index);
            for(int i = 0; i < list.size(); i++)
            {
                if(i > 0)
                    list.get(i).setPrev(list.get(i-1));
                else
                    list.get(i).setPrev(this.prev);
                list.get(i).updateLocation();
            }
            if (indexBool) {
                field.TextField indexField = (field.TextField) indices.get(index);
                indexField.textfield.setVisible(false);
                panel.remove(indexField.textfield);
                indices.remove(index);
                for (int i = 0; i < indices.size(); i++) {
                    if (i > 0)
                        ((field.TextField) (indices.get(i))).setPrev((field.TextField) indices.get(i - 1));
                    else
                        ((field.TextField) (indices.get(i))).setPrev(this.prev);
                    ((field.TextField) indices.get(i)).updateLocation();
                }
            }
            if (removeBool) {
                RemoveButton removeButton = (RemoveButton) remove.get(index);
                removeButton.button.setVisible(false);
                panel.remove(removeButton.button);
                remove.remove(index);
                for (int i = 0; i < remove.size(); i++) {
                    if (i > 0)
                        ((RemoveButton) (remove.get(i))).setPrev((RemoveButton) remove.get(i - 1));
                    else
                        ((RemoveButton) (remove.get(i))).setPrev(this.prev);
                    ((RemoveButton) (remove.get(i))).setIndex(i);
                    ((RemoveButton) remove.get(i)).updateLocation();
                }
            }
            if(list.size()>0)
                this.bounds = list.getLast().bounds;
            size--;
            updateLocation();
        }
    }

    public LinkedList<field.TextField> getList() { return list; }

    public LinkedList<field.TextField> getIndices() {return indices; }

    public void setAddButton(AddButton add) {this.add = add; }

    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = (String) list.get(i).getValueStrings().get(0);
                if(indexBool && ((TextField)(indices.get(i))).getValueStrings().size() > 0)
                    result_i += "[" + (String) ((TextField)(indices.get(i))).getValueStrings().get(0) + "]";
                result.add(result_i);
            }
        }
        if(map != null)
            map.put(key, result);
        return result;
    }

    public String toString()
    {
        return "Text field list " + key + " of size " + size;
    }
}
