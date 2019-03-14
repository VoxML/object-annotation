import field.impl.Name;
import view.SimpleFieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    public Main() {

        init();
        setSize(new Dimension(640,770)); //set default frame size
        setLocationRelativeTo(null); //starts at the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void init() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Object Annotation", null, createObjectView());
        tabs.setMnemonicAt(0, KeyEvent.VK_O);
        tabs.addTab("Event Annotation", null, createEventView());
        tabs.setMnemonicAt(1, KeyEvent.VK_E);
        add(tabs);

    }

    private JComponent createObjectView() {
        JComponent objectPanel = new JPanel();
        JComponent name = new SimpleFieldView<>(new Name());
        objectPanel.add(name);

        return objectPanel;

    }

    private JComponent createEventView() {
        JComponent eventPanel = new JPanel();

        return eventPanel;

    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

}
