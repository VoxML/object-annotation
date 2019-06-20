package lists;

import buttons.AddButton;
import field.AnnotationComponent;
import field.CheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ComponentsList extends TextFieldList {

    protected LinkedList<CheckBox> concavity;

    public ComponentsList(String key, Rectangle bounds, boolean removeBool, boolean indexBool, JPanel panel,
                         HashMap<String, ArrayList<String>> map, AddButton add, AnnotationComponent prev,HashSet<AnnotationComponent> set)
    {
        super(key, bounds, removeBool, indexBool, Integer.MAX_VALUE, 0, panel, map, add, prev, set);
        this.setSet(set);
        if(set != null)
            set.add(this);
    }

    public void updateLocation() {
        super.updateLocation();
        if(concavity != null && concavity.size() > 0) {
            (this.concavity).get(0).updateLocation();
        }
        if(getMap() != null)
            getMap().put(getKey(),getValueStrings());
        if(getMap() != null && concavity != null)
            getConcavityStrings();
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
                concavity.add(new CheckBox(getKey() + "_concave[" + concavity.size() + "]", "concave",
                        new Rectangle(xBound, bounds.y + concavity.size() * (bounds.height + 5),
                        75, bounds.height), list.get(list.size() - 1).getPrev(), getPanel(), getMap(), getSet()));
            } else {
                concavity.add(new CheckBox(getKey() + "_concave[" + concavity.size() + "]", "concave",
                        new Rectangle(xBound, bounds.y + concavity.size() * (bounds.height + 5),
                        75, bounds.height), getPrev(), getPanel(), getMap(), getSet()));
            }
            concavity.get(concavity.size() - 1).getCheckbox().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getConcavityStrings();
                }
            });
            concavity.get(0).updateLocation();
        }
        if(concavity != null && concavity.size() > 0) {
            concavity.getLast().getCheckbox().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getConcavityStrings();
                    getValueStrings();
                }
            });
        }
    }

    public LinkedList<CheckBox> getConcavity() {return concavity; }

    public void remove(int index)
    {
        super.remove(index);
        if(concavity.size() > index) {
            field.CheckBox checkBox = concavity.get(index);
            checkBox.getCheckbox().setVisible(false);
            getPanel().remove(checkBox.getCheckbox());
            concavity.remove(index);
            for (int i = 0; i < list.size(); i++) {
                if (i > 0)
                    concavity.get(i).setPrev(concavity.get(i - 1));
                else
                    concavity.get(i).setPrev(this.getPrev());
                concavity.get(i).updateLocation();
            }
        }
    }
    public ArrayList<String> getValueStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < size; i++)
        {
            if(list.size() > i && list.get(i).getValueStrings() != null && list.get(i).getValueStrings().size()>0 && !list.get(i).getValueStrings().get(0).equals("")) {
                String result_i = list.get(i).getValueStrings().get(0);
                if(indexBool && super.getIndices().get(i).getValueStrings().size() > 0)
                    result_i += "[" + super.getIndices().get(i).getValueStrings().get(0) + "]";
                result.add(result_i);
            }
        }
        if(getMap() != null)
            getMap().put(getKey(), result);
        return result;
    }

    public ArrayList<String> getConcavityStrings(){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < concavity.size(); i++)
        {
            if(super.getIndices() != null && super.getIndices().size() > i && super.getIndices().get(i) != null &&
                    super.getIndices().get(i).getTextfield() != null && concavity.get(i).getCheckbox().isSelected())
            {
                result.add("Concave[" + super.getIndices().get(i).getTextfield().getText() + "]");
            }
        }
        getMap().put("Concavity", result);
        return result;
    }

    public String toString()
    {
        return "components list of size " + size;
    }
}