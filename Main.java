import field.*;
import buttons.*;
import field1.AnnotationComponent;
import field1.TextField;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class Main extends JFrame {

    static String[] possibleHeads = {"", "prismatoid", "pyramid", "wedge", "parallelepiped", "cupola", "frustum",
            "cylindroid", "ellipsoid", "hemiellipsoid", "bipyramid", "rectangular prism", "toroid", "sheet"};
    static String[] possibleTypes = {"","physobj","artifact","human"};
    static String[] possibleRotSym = {"X","Y","Z"};
    static String[] possibleReflSym = {"XY","YZ","XZ"};
    static String[] possibleScales = {"<agent","=agent",">agent"};
    static String[] possibleEventHeads = {"","process","transition_event"};
    static String[] possibleEventTypes = {"","state","process","transition assignment","test"};

    static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    static ArrayList<AnnotationComponent> set = new ArrayList<AnnotationComponent>(); //to update location when things shift

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

        field1.Label NameLabel = new field1.Label(set,"Name: ", new Rectangle(15, 20, 85, 25),
                null, null, (JPanel) objectPanel);
        field1.TextField Name = new field1.TextField("Pred", new Rectangle(110, 20, 120, 25),
                null, null, (JPanel) objectPanel, map, set);
        addNameFunctionality(Name);
        field1.Label HeadLabel = new field1.Label(set,"Head: ", new Rectangle(15, 20, 85, 25),
                NameLabel, null, (JPanel) objectPanel);
        field1.DropDown Head = new field1.DropDown("Head", possibleHeads, new Rectangle(110,20,120,
                25), Name, null, (JPanel)objectPanel, map, set);
        field1.Label TypeLabel = new field1.Label(set, "Type: ", new Rectangle(15, 20, 85, 25),
                HeadLabel, null, (JPanel) objectPanel);
        field1.DropDownList Types = new field1.DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)objectPanel,map,null,possibleTypes,Head,null, set);
        AddButton addType = new AddButton(Head, null, new Rectangle(350,20,100,25),Types,(JPanel)objectPanel,set);
        field1.Label ComponentsLabel = new field1.Label(set,"Components: ", new Rectangle(15, 20, 100, 25),
                Types,null,(JPanel)objectPanel);
        field1.ComponentsList Components = new field1.ComponentsList("Components", new Rectangle(50, 20, 100, 25), true, true,
                (JPanel)objectPanel,map,null,ComponentsLabel,null,set);
        AddButton addComponent = new AddButton(Types,null,new Rectangle(350,20,100,25),Components,(JPanel)objectPanel,set);
        Components.getConcavityStrings();
        field1.Label RotSymLabel = new field1.Label(set,"Rotational symmetry: ", new Rectangle(15, 20, 200, 25),
                Components,null,(JPanel)objectPanel);
        field1.CheckBoxSet rotationalSymmetry = new field1.CheckBoxSet(possibleRotSym, "RotatSym", new Rectangle(15, 20, 70, 25),
                (JPanel)objectPanel, map, RotSymLabel,null,set);
        field1.Label ReflSymLabel = new field1.Label(set,"Reflection symmetry: ", new Rectangle(15, 20, 200, 25),
                rotationalSymmetry,null,(JPanel)objectPanel);
        field1.CheckBoxSet reflectionSymmetry = new field1.CheckBoxSet(possibleReflSym, "ReflSym", new Rectangle(15, 20, 70, 25),
                (JPanel)objectPanel, map, ReflSymLabel,null,set);
        field1.Label HabitatsLabel = new field1.Label(set,"Habitats: ", new Rectangle(15, 20, 100, 25),
                reflectionSymmetry,null,(JPanel)objectPanel);
        field1.Label IntrinsicNameLabel = new field1.Label(set,"name: ", new Rectangle(110, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        field1.Label IntrinsicValueLavel = new field1.Label(set,"value: ", new Rectangle(195, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        field1.Label IntrinsicLabel = new field1.Label(set,"Intrinsic: ", new Rectangle(30, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        field1.TextFieldList Intrinsic = new field1.TextFieldList("Intrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,IntrinsicLabel,null,set);
        AddButton addIntrinsic = new AddButton(HabitatsLabel,null,new Rectangle(350,20,100,25),Intrinsic,(JPanel)objectPanel,set);
        field1.Label ExtrinsicNameLabel = new field1.Label(set,"name: ", new Rectangle(110, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        field1.Label ExtrinsicValueLabel = new field1.Label(set,"value: ", new Rectangle(195, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        field1.Label ExtrinsicLabel = new field1.Label(set,"Extrinsic: ", new Rectangle(30, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        field1.TextFieldList Extrinsic = new field1.TextFieldList("Extrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,ExtrinsicLabel,null,set);
        AddButton addExtrinsic = new AddButton(Intrinsic,null,new Rectangle(350,20,100,25),Extrinsic,(JPanel)objectPanel,set);
        field1.Label AffordancesLabel = new field1.Label(set,"Affordances: ", new Rectangle(15, 20, 120, 25),
                Extrinsic,null,(JPanel)objectPanel);
        field1.TextFieldList Affordances = new field1.TextFieldList("Affordances", new Rectangle(15,20,80,25),true,false,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,AffordancesLabel,null,set);
        AddButton addAffordance = new AddButton(Extrinsic,null,new Rectangle(350,20,100,25),Affordances,(JPanel)objectPanel,set);
        field1.Label ScaleLabel = new field1.Label(set,"Scale: ", new Rectangle(15, 20, 70, 25),
                Affordances,null,(JPanel)objectPanel);
        field1.DropDown Scale = new field1.DropDown("Scale",possibleScales, new Rectangle(90,20,100,25),Affordances,null,
                (JPanel)objectPanel,map,set);
        field1.Label MovableLabel = new field1.Label(set,"Movable? ", new Rectangle(15, 20, 70, 25),
                Scale,null,(JPanel)objectPanel);
        field1.CheckBox Movable = new field1.CheckBox("Movable","",new Rectangle(90,20,50,25),Scale,null,
                (JPanel)objectPanel,map,set);
        LoadButton Load = new LoadButton(null,null,new Rectangle(350,20,100,25),(JPanel)objectPanel,map,set);
        SaveButton Save = new SaveButton(null,null,new Rectangle(460,20,100,25),(JPanel)objectPanel,map,set);

        map.put("Intr",map.get("Intrinsic"));
        map.put("Extr",map.get("Extrinsic"));
        map.put("Habitat",null);

        return objectPanel;
    }

    private JComponent createEventView() {
        JComponent eventPanel = new JPanel();
        eventPanel.setLayout(null);

        field1.Label NameLabel = new field1.Label(set,"Name: ", new Rectangle(15, 20, 85, 25),
                null, null, (JPanel) eventPanel);
        field1.TextField Name = new field1.TextField("Pred", new Rectangle(110, 20, 120, 25),
                null, null, (JPanel) eventPanel, map, set);
        addNameFunctionality(Name);
        field1.Label HeadLabel = new field1.Label(set,"Head: ", new Rectangle(15, 20, 85, 25),
                NameLabel, null, (JPanel) eventPanel);
        field1.DropDown Head = new field1.DropDown("Head", possibleEventHeads, new Rectangle(110,20,120,
                25), Name, null, (JPanel)eventPanel, map, set);
        field1.Label TypeLabel = new field1.Label(set, "Type: ", new Rectangle(15, 20, 85, 25),
                HeadLabel, null, (JPanel) eventPanel);
        field1.DropDownList Types = new field1.DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)eventPanel,map,null,possibleEventTypes,Head,null, set);
        AddButton addType = new AddButton(Head, null, new Rectangle(350,20,100,25),Types,(JPanel)eventPanel,set);
        field1.Label ArgsLabel = new field1.Label(set,"Args: ", new Rectangle(15, 20, 100, 25),
                Types,null,(JPanel)eventPanel);
        field1.TextFieldList Args = new field1.TextFieldList("Args",new Rectangle(50, 20, 100, 25),true,true,Integer.MAX_VALUE,
            0,(JPanel)eventPanel,map,null,ArgsLabel,null,set);
        AddButton addArg = new AddButton(Types,null,new Rectangle(350,20,100,25),Args,(JPanel)eventPanel,set);
        field1.Label BodyLabel = new field1.Label(set,"Body: ", new Rectangle(15, 20, 100, 25),
                Args,null,(JPanel)eventPanel);
        field1.TextFieldList Body = new field1.TextFieldList("Body",new Rectangle(50, 20, 100, 25),true,true,Integer.MAX_VALUE,
                0,(JPanel)eventPanel,map,null,BodyLabel,null,set);
        AddButton addBody = new AddButton(Args,null,new Rectangle(350,20,100,25),Body,(JPanel)eventPanel,set);
        field1.Label EmbeddingSpaceLabel = new field1.Label(set,"Embedding Space: ", new Rectangle(15,20,200,25),Body,
                null,(JPanel)eventPanel);
        field1.TextField EmbeddingSpace = new field1.TextField("embeddingSpace",new Rectangle(30,20,100,25),EmbeddingSpaceLabel,null,
                (JPanel)eventPanel,map,set);
        LoadButton Load = new LoadButton(null,null,new Rectangle(350,20,100,25),(JPanel)eventPanel,map,set);
        SaveButton Save = new SaveButton(null,null,new Rectangle(460,20,100,25),(JPanel)eventPanel,map,set);
        return eventPanel;
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    private void addNameFunctionality(TextField Name)
    {
        Name.textfield.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setTitle(Name.textfield.getText());
                    }
                }
        );
    }
}