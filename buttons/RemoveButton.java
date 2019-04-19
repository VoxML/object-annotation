package buttons;

import field1.AnnotationComponent;
import field1.AnnotationField1;
import field1.FieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class RemoveButton extends Button {
    protected FieldList list;
    protected int index;
    protected AnnotationField1 toRemove;

    public RemoveButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, FieldList list, int index, JPanel panel, ArrayList<AnnotationComponent> set)
    {
        super("remove", prev, next, bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        }, set);
        this.set = set;
        set.add(this);
        this.list = list;
        this.index = index;
    }

    public RemoveButton(Rectangle bounds, FieldList list, int ind, JPanel panel, ArrayList<AnnotationComponent> set)
    {
        super("remove", bounds, panel, null, set);
        this.set = set;
        set.add(this);
        this.index = ind;
        this.list = list;
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
        JButton result = super.createButton("add", buttonBounds, this.AL);
        this.button = result;
        return result;
    }

    public void upadteActionListener()
    {
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = getIndex();
                System.out.println("MY INDEX IS " + getIndex());
                System.out.println("I AM CALLING REMOVE WITH INDEX" + ind);
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