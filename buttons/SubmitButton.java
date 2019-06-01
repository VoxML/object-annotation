package buttons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SubmitButton implements ActionListener, KeyListener {

    JTextField input;
    field.TextField textField;

    public SubmitButton(field.TextField textField) {
        this.textField = textField;
        this.input = textField.textfield;
    }

    @Override
    public void actionPerformed(ActionEvent submitClicked) {
        submit();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            submit();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    private void submit(){
        String value = (String) input.getText();
        textField.valueStrings.clear();
        textField.valueStrings.add(value);
        textField.map.put(textField.key, textField.valueStrings);
        if(textField.key.equals("Pred"))
        {
            if(textField.frame != null) {
               textField.frame.setTitle(input.getText());
            }
        }
    }
}