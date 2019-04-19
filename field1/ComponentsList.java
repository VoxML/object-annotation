package field1;

import buttons.AddButton;
import buttons.RemoveButton;
import field1.AnnotationComponent;
import field1.TextField;
import field1.TextFieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ComponentsList extends TextFieldList {

    LinkedList<CheckBox> concavity;

    public ComponentsList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, int max, int min, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                         AnnotationComponent next, ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, max, min, panel, map, add, prev, next, set);
        this.set = set;
        set.add(this);
        this.concavity = new LinkedList<CheckBox>();
        for(int i = 0; i <= min; i++)
            add();
    }

    public ComponentsList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,
                          AnnotationComponent next,ArrayList<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, next, set);
        this.set = set;
        set.add(this);
        this.concavity = new LinkedList<CheckBox>();
    }

    public void updateLocation() {
        super.updateLocation();
        if(concavity != null && concavity.size() > 0)
            (this.concavity).get(0).updateLocation();
    }

    public void add() {
        super.add();
        addConcavityCheckBox();
    }

    public void addConcavityCheckBox() {
        if(concavity==null)
            this.concavity = new LinkedList<CheckBox>();
        int xBound = this.bounds.x + this.bounds.width + 5;
        if(removeBool)
            xBound += 85;
        if(indexBool)
            xBound += 65;
        if (concavity.size() < max) {
            if (concavity.size() > 0) {
                concavity.add(new CheckBox(key + "_[" + concavity.size() + "]", "concave", new Rectangle(xBound, bounds.y + concavity.size() * (bounds.height + 5),
                        bounds.width, bounds.height), list.get(size-1).prev, null, panel, map, set));
            } else {
                concavity.add(new CheckBox(key + "_[" + concavity.size() + "]", "concave", new Rectangle(xBound, bounds.y + concavity.size() * (bounds.height + 5),
                        bounds.width, bounds.height), prev, null, panel, map, set));
            }
            concavity.get(concavity.size()-1).checkbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getConcavityStrings();
                }
            });
            concavity.get(concavity.size()-1).updateLocation();
        }
    }

    public void remove(int index)
    {
        super.remove(index);
        if(concavity.size() > index) {
            CheckBox checkBox = (CheckBox) concavity.get(index);
            checkBox.checkbox.setVisible(false);
            panel.remove(checkBox.checkbox);
            concavity.remove(index);
            for (int i = 0; i < concavity.size(); i++) {
                if (i > 0)
                    ((CheckBox) (concavity.get(i))).setPrev((CheckBox) concavity.get(i - 1));
                else
                    ((CheckBox) (concavity.get(i))).setPrev(this.prev);
                ((CheckBox) concavity.get(i)).updateLocation();
            }
        }
    }
    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = (String) list.get(i).getValueStrings().get(0);
                if(indexBool)
                    result_i += (String) ((TextField)(indices.get(i))).getValueStrings().get(0);
                result.add((String) list.get(i).getValueStrings().get(0));
            }
        }
        map.put(key, result);
        return result;
    }

    public ArrayList<String> getConcavityStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < concavity.size(); i++)
        {
            if(concavity.get(i).checkbox.isSelected())
            {
                result.add("Concave[" + indices.get(i) + "]");
            }
        }
        map.put("Concavity", result);
        return result;
    }

}