import field.*;
import buttons.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Main extends JFrame {

    static String[] possibleHeads = {"", "prismatoid", "pyramid", "wedge", "parallelepiped", "cupola", "frustum",
            "cylindroid", "ellipsoid", "hemiellipsoid", "bipyramid", "rectangular prism", "toroid", "sheet"};
    static String[] possibleTypes = {"","physobj","artifact","human"};

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
        objectPanel.setLayout(null);

        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        field1.Label NameLabel = new field1.Label("Name: ", new Rectangle(15, 20, 85, 25),
                null, null, (JPanel) objectPanel);
        field1.TextField Name = new field1.TextField("name", new Rectangle(110, 20, 120, 25),
                null, null, (JPanel) objectPanel, map);
        field1.Label HeadLabel = new field1.Label("Head: ", new Rectangle(15, 20, 85, 25),
                NameLabel, null, (JPanel) objectPanel);
        field1.DropDown Head = new field1.DropDown("head", possibleHeads, new Rectangle(110,20,120,
                25), Name, null, (JPanel)objectPanel, map);
        field1.Label TypeLabel = new field1.Label("Type: ", new Rectangle(15, 20, 85, 25),
                HeadLabel, null, (JPanel) objectPanel);
        field1.DropDownList Types = new field1.DropDownList("type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)objectPanel,map,null,possibleTypes,Head,null);
        AddButton addType = new AddButton(Head, null, new Rectangle(350,20,100,25),Types,(JPanel)objectPanel);
        Types.setAddButton(addType);


        field1.Label ComponentLabel = new field1.Label("Components: ", new Rectangle(15, 20, 85, 25),
                Types.getList().getLast(), null, (JPanel) objectPanel);
        field1.TextFieldList Components = new field1.TextFieldList("component", new Rectangle(110,20,120,25),
                true, true, (JPanel)objectPanel, map, null, Types.getList().getLast(), null);
        AddButton addComponent = new AddButton(Types.getList().getLast(),null,new Rectangle(380,20,100,25),Components,(JPanel)objectPanel);
        Components.setAddButton(addComponent);

        field1.Label RotSymLabel = new field1.Label("Refl. Sym: ", new Rectangle(15, 220, 85, 25),
                Types.getList().getLast(), null, (JPanel) objectPanel);
                JCheckBox x = new JCheckBox("X"); x.setBounds(RotSymLabel.getBounds().x, RotSymLabel.getBounds().y+30,
                   RotSymLabel.getBounds().width,RotSymLabel.getBounds().height);
                JCheckBox y = new JCheckBox("Y"); y.setBounds(RotSymLabel.getBounds().x + 30, RotSymLabel.getBounds().y+30,
                    RotSymLabel.getBounds().width,RotSymLabel.getBounds().height);
                  JCheckBox z = new JCheckBox("Z"); z.setBounds(RotSymLabel.getBounds().x + 60, RotSymLabel.getBounds().y+30,
                    RotSymLabel.getBounds().width,RotSymLabel.getBounds().height);


        field1.Label ReflSymLabel = new field1.Label("Refl. Sym: ", new Rectangle(15, 250, 85, 25),
                null, null, (JPanel) objectPanel);
        JCheckBox xy = new JCheckBox("XY"); xy.setBounds(ReflSymLabel.getBounds().x, ReflSymLabel.getBounds().y+30,
                ReflSymLabel.getBounds().width,ReflSymLabel.getBounds().height);
        JCheckBox yz = new JCheckBox("YZ"); yz.setBounds(ReflSymLabel.getBounds().x + 70, ReflSymLabel.getBounds().y+30,
                ReflSymLabel.getBounds().width,ReflSymLabel.getBounds().height);
        JCheckBox xz = new JCheckBox("XZ"); xz.setBounds(ReflSymLabel.getBounds().x + 140, ReflSymLabel.getBounds().y+30,
                ReflSymLabel.getBounds().width,ReflSymLabel.getBounds().height);

        xy.setVisible(true);
        xz.setVisible(true);
        yz.setVisible(true);
        x.setVisible(true);
        y.setVisible(true);
        z.setVisible(true);
        objectPanel.add(x);
        objectPanel.add(y);
        objectPanel.add(z);
        objectPanel.add(xy);
        objectPanel.add(xz);
        objectPanel.add(yz);

        JCheckBox movable = new JCheckBox("movable"); xz.setBounds(ReflSymLabel.getBounds().x + 140, ReflSymLabel.getBounds().y+100,
                ReflSymLabel.getBounds().width,ReflSymLabel.getBounds().height);

        movable.setVisible(true);
        objectPanel.add(movable );
        return objectPanel;
    }

    private JComponent createEventView() {
        JComponent eventPanel = new JPanel();
        eventPanel.setLayout(null);

        return eventPanel;
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}