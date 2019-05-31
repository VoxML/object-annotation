package lists;

import buttons.*;
import field.AnnotationComponent;
import field.DropDown;
import field.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DropDownList extends FieldList {

    LinkedList<DropDown> list;
    String[] options;

    public DropDownList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min,
                        JPanel panel, HashMap<String, ArrayList<String>> map, AddButton add, String[] options,
                        AnnotationComponent prev, AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, next, set);
        this.set = set;
        if(!set.contains(this))
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
                        AnnotationComponent prev, AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, next, set);
        this.set = set;
        if(!set.contains(this))
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
        if (list != null && list.size() > 0) {
            this.bounds = list.getLast().dropdown.getBounds();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).updateLocation();
            }
        }
        if (remove != null) {
            for (int i = 0; i < remove.size(); i++) {
                ((RemoveButton) (this.remove).get(i)).updateLocation();
            }
        }
        if (indices!= null) {
            for (int i = 0; i < indices.size(); i++) {
                ((RemoveButton) (this.indices).get(i)).updateLocation();
            }
        }
    }

    public int getSize()
    {
        if(list!=null)
            return list.size();
        return -1;
    }

    public void add() {
        if(size < max) {
            if (size > 0) {
                list.add(new DropDown(key + "[" + size + "]", options, new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
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
                list.add(new DropDown(key + "[" + size + "]", options, new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
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
            if(list != null && list.size() > 0)
                this.bounds = list.getLast().bounds;
            updateLocation();
            if(removeBool && remove != null && remove.size() > 0)
                ((RemoveButton)remove.get(0)).updateLocation();
            if(indexBool && indices != null && indices.size() > 0)
                ((field.TextField)indices.get(0)).updateLocation();
        }
    }

    public void remove(int index)
    {
        if(size > index && size > min) {
            DropDown toRemove = list.get(index);
            for(int i = index+1; i < list.size(); i++)
            {
                list.get(i).setKey(key + "[" + (i-1) + "]");
                if(removeBool)
                    ((RemoveButton)(remove.get(i))).setIndex(i-1);
            }
            if(list.size()>index+1) {
                list.get(index + 1).setPrev(toRemove.prev);
                if(indexBool)
                    ((field.TextField)indices.get(index+1)).setPrev(toRemove.prev);
                if(removeBool)
                    ((RemoveButton)remove.get(index+1)).setPrev(toRemove.prev);
            }
            toRemove.dropdown.setVisible(false);
            panel.remove(toRemove.dropdown);
            list.remove(index);
            set.remove(toRemove);
            if (indexBool) {
                field.TextField indexToRemove = (field.TextField) indices.get(index);
                indexToRemove.textfield.setVisible(false);
                panel.remove(indexToRemove.textfield);
                indices.remove(index);
            }
            if (removeBool) {
                RemoveButton removeButton = (RemoveButton) remove.get(index);
                removeButton.button.setVisible(false);
                panel.remove(removeButton.button);
                remove.remove(index);
            }
            size--;
            updateLocation();
        }
    }

    public LinkedList<DropDown> getList() { return list; };
    public void setAddButton(AddButton add) {this.add = add; }
    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = (String) list.get(i).getValueStrings().get(0);
                if(indexBool)
                    result_i += "[" + (String) ((TextField)(indices.get(i))).getValueStrings().get(0) + "]s";
                result.add(result_i);
            }
        }
        map.put(key, result);
        return result;
    }

    public String toString()
    {
        return "Drop down list " + key + " of size " + size;
    }
}
