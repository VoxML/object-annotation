package field;

import buttons.SubmitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TextField extends AnnotationField<String> {
    public JTextField textfield;
    public String key;
    public JFrame frame;
    public boolean justCreated = true;

    public TextField(String key, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        if(map != null)
        {
            valueStrings.clear();
            valueStrings.add("");
            map.put(key, valueStrings);
        }
        this.prev = prev;
        createTextField(bounds);
    }

    public TextField(String key, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createTextField(bounds);
    }

    public String getKey()
    {
        return key;
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(textfield != null && bounds!=null) {
            textfield.setBounds(bounds);
        }
        justCreated = false;
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(textfield != null && bounds!=null)
            textfield.setBounds(bounds);
    }

    public JTextField createTextField(Rectangle textfieldBounds)
    {
        JTextField result = new JTextField("");
        result.setBounds(textfieldBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value = (String) result.getText();
                        valueStrings.clear();
                        valueStrings.add(value);
                        map.put(key, valueStrings);
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        textfield = result;
        updateLocation();
        return textfield;
    }

    public String toString()
    {
        return "Text field with key " + key;
    }

    public void pressEnter() {
        SubmitButton submitButton = new SubmitButton(this);
        JButton submit = new JButton("Submit");
        this.textfield.addActionListener(submitButton);
        submit.addActionListener(submitButton);
        submit.addKeyListener(submitButton);
        KeyEvent key = new KeyEvent(this.textfield, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER);
        this.textfield.dispatchEvent(key);
        submit.doClick();
        getValueStrings();
    }

    public ArrayList<String> getValueStrings() {
        String value = (String) textfield.getText();
        valueStrings.clear();
        valueStrings.add(value);
        map.put(key, valueStrings);
        return valueStrings;
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }
}