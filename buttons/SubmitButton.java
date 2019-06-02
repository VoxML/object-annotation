package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class SubmitButton implements ActionListener, KeyListener {

    private JTextField input;
    private field.TextField textField;

    public SubmitButton(field.TextField textField) {
        this.textField = textField;
        this.input = textField.getTextfield();
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
        String value = input.getText();
        textField.getValueStrings().clear();
        textField.getValueStrings().add(value);
        textField.getMap().put(textField.getKey(), textField.getValueStrings());
        if(textField.getKey().equals("Pred"))
        {
            if(textField.getFrame() != null) {
               textField.getFrame().setTitle(input.getText());
            }
        }
    }

    public JTextField getInput() { return input; }
    public field.TextField getTextField() { return textField; }
}