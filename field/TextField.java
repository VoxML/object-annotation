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
    private JTextField textfield;
    private String key;
    private JFrame frame;
    private boolean hasBeenChanged = false;
    private SubmitButton submitButton;
    private JButton submit;

    public TextField(String key, Rectangle bounds, AnnotationComponent prev, JPanel panel,
                     HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.setSet(set);
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.setPanel(panel);
        this.map = map;
        if(map != null)
        {
            super.getValueStrings().clear();
            super.getValueStrings().add("");
            map.put(key, super.getValueStrings());
        }
        this.setPrev(prev);
        createTextField(bounds);
    }

    public String getKey()
    {
        return key;
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(getTextfield() != null && bounds!=null) {
            getTextfield().setBounds(bounds);
        }
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getTextfield() != null && bounds!=null)
            getTextfield().setBounds(bounds);
    }

    public JTextField createTextField(Rectangle textfieldBounds)
    {
        JTextField result = new JTextField("");
        result.setBounds(textfieldBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value = result.getText();
                        getValueStrings().clear();
                        getValueStrings().add(value);
                        getMap().put(getKey(), getValueStrings());
                        hasBeenChanged = true;
                    }
                }
        );
        result.setVisible(true);
        getPanel().add(result);
        textfield = result;
        updateLocation();
        return getTextfield();
    }

    public String toString()
    {
        return "Text field with key " + getKey();
    }

    public void pressEnter() {
        submitButton = new SubmitButton(this);
        submit = new JButton("Submit");
        this.getTextfield().addActionListener(submitButton);
        submit.addActionListener(submitButton);
        submit.addKeyListener(submitButton);
        KeyEvent key = new KeyEvent(this.getTextfield(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER);
        this.getTextfield().dispatchEvent(key);
        submit.doClick();
        getValueStrings();
    }

    public ArrayList<String> getValueStrings() {
        String value = getTextfield().getText();
        super.getValueStrings().clear();
        super.getValueStrings().add(value);
        getMap().put(getKey(), super.getValueStrings());
        return super.getValueStrings();
    }

    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }

    public JTextField getTextfield() {
        return textfield;
    }

    public JFrame getFrame() {
        return frame;
    }

    public boolean isHasBeenChanged() {
        return hasBeenChanged;
    }
}