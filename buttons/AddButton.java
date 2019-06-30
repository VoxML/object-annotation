package buttons;

import field.AnnotationComponent;
import lists.FieldList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class AddButton extends Button{

    private FieldList list;

    public AddButton(AnnotationComponent prev, Rectangle bounds, FieldList list, JPanel panel, HashSet<AnnotationComponent> set)
    {
        this.setName("add");
        this.setPrev(prev);
        this.bounds = bounds;
        this.list = list;
        this.setPanel(panel);
        this.setSet(set);
        if(list!=null && list.getAdd() == null) {
            this.setSet(set);
            set.add(this);
            this.list = list;
            this.setButton(createButton(bounds));
        }
    }

    public String getKey()
    {
        if(list != null)
            return list.getKey() + "_add";
        return "add";
    }

    protected JButton createButton(Rectangle buttonBounds) {
        if(list!=null && list.getAdd() == null) {
            ActionListener AL = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    list.add();
                }
            };
            setButton(super.createButton("add", buttonBounds, AL));
            list.setAdd(this);
        }
        this.component = getButton();
        return getButton();
    }

    protected FieldList getList()
    {
        return list;
    }
    public void setList(FieldList list)
    {
        this.list = list;
    }

}