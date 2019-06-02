package buttons;

import field.AnnotationComponent;
import lists.FieldList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class RemoveButton extends Button {
    private FieldList list;
    private int index;

    public RemoveButton(AnnotationComponent prev, Rectangle bounds, FieldList list, int index, JPanel panel,
                        HashSet<AnnotationComponent> set)
    {
        super("remove", prev, bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        }, set);
        this.setSet(set);
        set.add(this);
        this.setList(list);
        this.index = index;
    }

    public void updateLocation()
    {
        super.updateLocation();
    }

    public void upadteActionListener()
    {
        this.setAL(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = getIndex();
                getList().remove(ind);
            }
        });
        while(getButton().getActionListeners().length>0)
            getButton().removeActionListener(getButton().getActionListeners()[0]);
        getButton().addActionListener(this.getAL());

    }

    public void setIndex(int index) {this.index = index; upadteActionListener(); }
    public int getIndex() {return index; }
    public FieldList getList() {
        return list;
    }
    public void setList(FieldList list) {
        this.list = list;
    }
}