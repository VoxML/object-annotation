package view;

import field.StringField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleFieldView<T extends StringField> extends JPanel {

    public SimpleFieldView(T t) {
        super(new FlowLayout(FlowLayout.LEFT), true);
        JLabel label = new JLabel(t.getKey());
        label.setBounds(Dimensions.SINGLE_STRING_KEY_DIM);
        JTextField input = new JTextField("", 20);
        input.setText(t.getValue());
        input.setBounds(Dimensions.SINGLE_STRING_VAL_DIM);
        input.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        t.setValue(input.getText());
                                    }
                                }
        );
        add(label);
        add(input);
    }
}
