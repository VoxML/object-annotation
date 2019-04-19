package buttons;

import field1.AnnotationComponent;
import field1.FieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class AddButton extends Button{

    protected FieldList list;

    public AddButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, FieldList list, JPanel panel, ArrayList<AnnotationComponent> set)
    {
        super("add", prev, next, bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I added to the list. Now the list size is " + list.getSize());
                list.add();
            }
        }, set);
        this.set = set;
        set.add(this);
        this.list = list;
        button = createButton(bounds);
        System.out.println("button has AL? " + (button.getActionListeners().length>0));
    }

    public AddButton(Rectangle bounds, FieldList list, JPanel panel, ArrayList<AnnotationComponent> set)
    {
        super("add", bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I added to the list.");
                list.add();
            }
        }, set);
        this.set = set;
        set.add(this);
        this.list = list;
        button = createButton(bounds);
    }

    protected JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I added to the list.");
                list.add();
            }
        };
        button = super.createButton("add", buttonBounds, AL);
        System.out.println("AL?" + AL.equals(button.getActionListeners()[0]));
        return button;
    }

    public FieldList getList()
    {
        return list;
    }
    public void setList(FieldList list)
    {
        this.list = list;
    }
}