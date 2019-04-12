package buttons;

import field1.AnnotationComponent;
import field1.AnnotationField1;
import field1.FieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButton extends Button {
    protected FieldList list;
    protected int index;
    protected AnnotationField1 toRemove;

    public RemoveButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, FieldList list, int index, JPanel panel)
    {
        super("remove", prev, next, bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        });
        this.list = list;
        this.index = index;
    }

    public RemoveButton(Rectangle bounds, FieldList list, int index, JPanel panel)
    {
        super("remove", bounds, panel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        });
        this.list = list;
        this.index = index;
    }

    public void updateLocation()
    {
        super.updateLocation();
    }

    public JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(index);
            }
        };
        JButton result = super.createButton("add", buttonBounds, AL);
        return result;
    }

    public void setIndex(int index) {this.index = index; }
    public int getIndex() {return index; }
}