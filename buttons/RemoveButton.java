package buttons;

import field.AnnotationComponent;
import lists.FieldList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class RemoveButton extends Button {
    protected FieldList list;
    protected int index;

    public RemoveButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, FieldList list, int index, JPanel panel,
                        HashSet<AnnotationComponent> set)
    {
        super("remove", prev, next, bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        }, set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.list = list;
        this.index = index;
    }

    public void updateLocation()
    {
        super.updateLocation();
    }

    public JButton createButton(Rectangle buttonBounds) {
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = getIndex();
                list.remove(ind);
            }
        };
        JButton result = super.createButton("remove", buttonBounds, this.AL);
        this.button = result;
        return result;
    }

    public void upadteActionListener()
    {
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = getIndex();
                list.remove(ind);
            }
        };
        while(button.getActionListeners().length>0)
            button.removeActionListener(button.getActionListeners()[0]);
        button.addActionListener(this.AL);

    }

    public void setIndex(int index) {this.index = index; upadteActionListener(); }
    public int getIndex() {return index; }
}