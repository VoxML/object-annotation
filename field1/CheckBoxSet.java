/*package field1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CheckBoxSet extends AnnotationField1 {
    protected LinkedList<JCheckBox> checkboxes;
    protected String key;
    protected LinkedList<String> value;
    protected int size;
    protected String[] options;
    String toString;

    public CheckBoxSet(String key, AnnotationField1 prev, AnnotationField1 next, Rectangle bounds, int size, String[] options)
    {
        super(key, prev, next, bounds);
        this.size = size;
        this.options = options;
        int gapSize = 30;
        for(int i = 0; i < size; i++)
            checkboxes.add(createCheckbox(this.bounds,i,options[i],gapSize));
    }

    public CheckBoxSet(String key, Rectangle bounds, int size, String[] options)
    {
        super(key, null,null,bounds);
        this.size = size;
        this.options = options;
        int gapSize = 30;
        for(int i = 0; i < size; i++)
            checkboxes.add(createCheckbox(this.bounds,i,options[i],gapSize));
    }

    public JCheckBox createCheckbox(Rectangle checkboxBounds, int i, String option, int gapSize)
    {
        JCheckBox result = new JCheckBox(option);
        if(i>1)
            checkboxBounds.x = checkboxes.get(i-1).getBounds().width + checkboxes.get(i-1).getBounds().width + gapSize;
        result.setBounds(checkboxBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateValue();
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        return result;
    }

    public void updateValue()
    {
        for(int i = 0; i < size; i++)
        {
            if(checkboxes.get(i).isSelected())
            {
                value.add(checkboxes.get(i).getName());
                toString += checkboxes.get(i).getName() + "*";
            }
        }
        if(toString.length() > 0)
            toString = toString.substring(0,toString.length()-1);
    }

    public String toString()
    {
        return toString;
    }

    public LinkedList<String> getValue()
    {
        return value;
    }


} */