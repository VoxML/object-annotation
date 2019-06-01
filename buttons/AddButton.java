package buttons;

import field.AnnotationComponent;
import lists.FieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class AddButton extends Button{

    protected FieldList list;

    public AddButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, FieldList list, JPanel panel, HashSet<AnnotationComponent> set)
    {
        this.name = "add";
        this.prev = prev;
        this.bounds = bounds;
        this.list = list;
        this.panel = panel;
        this.set = set;
        if(list!=null && list.add == null) {
            this.set = set;
            if (!set.contains(this))
                set.add(this);
            this.list = list;
            button = createButton(bounds);
        }
    }

    public AddButton(Rectangle bounds, FieldList list, JPanel panel, HashSet<AnnotationComponent> set)
    {
        super("add", bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.add();
            }
        }, set);
        if(list!=null && list.add == null) {
            this.set = set;
            if (!set.contains(this))
                set.add(this);
            this.list = list;
            button = createButton(bounds);
        }
    }

    public String getKey()
    {
        if(list != null)
            return list.key + "_add";
        return "add";
    }

    protected JButton createButton(Rectangle buttonBounds) {
        if(list!=null && list.add == null) {
            ActionListener AL = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    list.add();
                }
            };
            button = super.createButton("add", buttonBounds, AL);
            list.add = this;
        }
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