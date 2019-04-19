package field1;

import buttons.*;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TextFieldList extends FieldList {

    protected LinkedList<TextField> list;

    public TextFieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                         AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, next, set);
        this.set = set;
        set.add(this);
        this.list = new LinkedList<TextField>();
        LinkedList<TextField> indices = new LinkedList<TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public TextFieldList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, JPanel panel,
                        HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                         AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, next, set);
        this.set = set;
        set.add(this);
        this.list = new LinkedList<TextField>();
        LinkedList<TextField> indices = new LinkedList<TextField>();
        for(int i = 0; i < Math.max(1,min); i++) {
            add();
        }
    }

    public void updateLocation() {
        super.updateLocation();
        if(list != null && list.size() > 0) {
            this.bounds = list.getLast().textfield.getBounds();
            for(int i = 0; i < list.size(); i++) {
                list.get(i).updateLocation();
            }
            if(remove != null) {
                for (int i = 0; i < remove.size(); i++) {
                    ((RemoveButton) (this.remove).get(i)).updateLocation();
                }
            }
            if(indices != null) {
                for (int i = 0; i < indices.size(); i++) {
                    ((TextField) (this.indices).get(i)).updateLocation();
                }
            }
        }
    }

    public void add() {
        if(size < max) {
            if (size > 0) {
                list.add(new TextField(key + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), list.get(size - 1), null, panel, map, set));
                if (indexBool)
                    indices.add(new TextField(key + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), (AnnotationComponent) indices.get(size - 1), null, panel, map, set));
                if (removeBool) {
                    if (!indexBool)
                        remove.add(new RemoveButton((AnnotationComponent)remove.get(size - 1), null, new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                    else
                        remove.add(new RemoveButton((AnnotationComponent)remove.get(size - 1), null, new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                }
            } else {
                list.add(new TextField(key + "[" + size + "]", new Rectangle(bounds.x, bounds.y + size * (bounds.height + 5),
                        bounds.width, bounds.height), prev, null, panel, map, set));
                if (indexBool)
                {
                    if(indices==null)
                        indices = new LinkedList<TextField>();
                    indices.add(new TextField(key + "_index" + "[" + size + "]", new Rectangle(bounds.x + bounds.width + 5,
                            bounds.y + size * (bounds.height + 5), 60, bounds.height), prev, null, panel, map, set));
                }
                if (removeBool) {
                    if(remove==null)
                        remove = new LinkedList<TextField>();
                    if (!indexBool)
                        remove.add(new RemoveButton(prev, null, new Rectangle(bounds.x + bounds.width + 5,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                    else
                        remove.add(new RemoveButton(prev, null, new Rectangle(bounds.x + bounds.width + 60 + 10,
                                bounds.y + size * (bounds.height + 5), 80, bounds.height), this, size, panel, set));
                }
            }
            size++;
            updateLocation();
            if(removeBool)
                ((RemoveButton)remove.get(size-1)).updateLocation();
            if(indexBool)
                ((TextField)indices.get(size-1)).updateLocation();
            this.bounds = list.getLast().bounds;
        }
    }
    public void remove(int index)
    {
        if(size > 0 && size > min) {
            TextField field = list.get(index);
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
                TextField indexField = (TextField) indices.get(index);
                indexField.textfield.setVisible(false);
                panel.remove(indexField.textfield);
                indices.remove(index);
                for (int i = 0; i < indices.size(); i++) {
                    if (i > 0)
                        ((TextField) (indices.get(i))).setPrev((TextField) indices.get(i - 1));
                    else
                        ((TextField) (indices.get(i))).setPrev(this.prev);
                    ((TextField) indices.get(i)).updateLocation();
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
            this.bounds = list.getLast().bounds;
        }
        size--;
        updateLocation();
    }
    public LinkedList<TextField> getList() { return list; };
    public void setAddButton(AddButton add) {this.add = add; }

    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = (String) list.get(i).getValueStrings().get(0);
                if(indexBool)
                    result_i += (String) ((TextField)(indices.get(i))).getValueStrings().get(0);
                result.add(result_i);
            }
        }
        map.put(key, result);
        return result;
    }
}
