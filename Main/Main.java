package Main;

import buttons.*;
import field.AnnotationComponent;
import field.TextField;
import lists.ComponentsList;
import lists.DropDownList;
import lists.TextFieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main extends JFrame {

    static String[] possibleHeads = {"", "prismatoid", "pyramid", "wedge", "parallelepiped", "cupola", "frustum",
            "cylindroid", "ellipsoid", "hemiellipsoid", "bipyramid", "rectangular prism", "toroid", "sheet"};
    static String[] possibleTypes = {"","physobj","artifact","human"};
    static String[] possibleScales = {"<agent","=agent",">agent"};
    static String[] possibleEventHeads = {"","process","transition_event"};
    static String[] possibleEventTypes = {"","state","process","transition assignment","test"};

    static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    static HashSet<AnnotationComponent> set1 = new HashSet<AnnotationComponent>(); //to update location when things shift for object view
    static HashSet<AnnotationComponent> set2 = new HashSet<AnnotationComponent>(); //to update location when things shift for event view

    static JTabbedPane tabs;

    public Main() {
        init();
        setSize(new Dimension(640,770)); //set default frame size
        setLocationRelativeTo(null); //starts at the middle of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init() {
        tabs = new JTabbedPane();
        JScrollPane objectScrollPane = (JScrollPane)createObjectView();
        tabs.add(objectScrollPane, "Object");
        JScrollPane eventScrollPane = (JScrollPane)createEventView();
        tabs.add(eventScrollPane, "Event");
        add(tabs);
    }

    public JComponent createObjectView() {
        JComponent objectPanel = new JPanel();
        objectPanel.setLayout(new GroupLayout(objectPanel));
        JScrollPane objectScrollPane = new JScrollPane(objectPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollBar bar = objectScrollPane.getVerticalScrollBar();
        set1.clear();

        field.Label NameLabel = new field.Label(set1,"Name: ", new Rectangle(15, 20, 85, 25),
                null, null, (JPanel) objectPanel);
        field.TextField Name = new field.TextField("Pred", new Rectangle(110, 20, 120, 25),
                null, null, (JPanel) objectPanel, map, set1);
        addNameFunctionality(Name);
        field.Label HeadLabel = new field.Label(set1,"Head: ", new Rectangle(15, 20, 85, 25),
                NameLabel, null, (JPanel) objectPanel);
        field.DropDown Head = new field.DropDown("Head", possibleHeads, new Rectangle(110,20,120,
                25), Name, null, (JPanel)objectPanel, map, set1);
        field.TextField HeadIndex = new field.TextField("Head_index",new Rectangle(240,20,50,25),NameLabel,null,
                (JPanel)objectPanel,map,set1);
        field.Label TypeLabel = new field.Label(set1, "Type: ", new Rectangle(15, 20, 85, 25),
                HeadLabel, null, (JPanel) objectPanel);
        DropDownList Types = new DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)objectPanel,map,null,possibleTypes,Head,null, set1);
        AddButton addType = new AddButton(Head, null, new Rectangle(350,20,100,25),Types,(JPanel)objectPanel,set1);
        field.Label ComponentsLabel = new field.Label(set1,"Components: ", new Rectangle(15, 20, 100, 25),
                Types,null,(JPanel)objectPanel);
        ComponentsList Components = new ComponentsList("Components", new Rectangle(50, 20, 100, 25), true, true,
                (JPanel)objectPanel,map,null,ComponentsLabel,null,set1);
        AddButton addComponent = new AddButton(Types,null,new Rectangle(350,20,100,25),Components,(JPanel)objectPanel,set1);
        Components.getConcavityStrings();
        field.Label RotSymLabel = new field.Label(set1,"Rotational symmetry: ", new Rectangle(15, 20, 200, 25),
                Components,null,(JPanel)objectPanel);
        field.CheckBox rotationalSymmetryX = new field.CheckBox("RotatSym[0]","X",new Rectangle(30,20,35,25),RotSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.CheckBox rotationalSymmetryY = new field.CheckBox("RotatSym[1]","Y",new Rectangle(100,20,35,25),RotSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.CheckBox rotationalSymmetryZ = new field.CheckBox("RotatSym[2]","Z",new Rectangle(170,20,35,25),RotSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.Label ReflSymLabel = new field.Label(set1,"Reflection symmetry: ", new Rectangle(15, 20, 200, 25),
                rotationalSymmetryX,null,(JPanel)objectPanel);
        field.CheckBox reflectionSymmetryXY = new field.CheckBox("ReflSym[0]","XY",new Rectangle(30,20,45,25),ReflSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.CheckBox reflectionSymmetryYZ = new field.CheckBox("ReflSym[1]","YZ",new Rectangle(110,20,45,25),ReflSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.CheckBox reflectionSymmetryXZ = new field.CheckBox("ReflSym[2]","XZ",new Rectangle(190,20,45,25),ReflSymLabel,
                null,(JPanel)objectPanel,map,set1);
        field.Label HabitatsLabel = new field.Label(set1,"Habitats: ", new Rectangle(15, 20, 100, 25),
                reflectionSymmetryXY,null,(JPanel)objectPanel);
        field.Label IntrinsicNameLabel = new field.Label(set1,"name: ", new Rectangle(110, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        field.Label IntrinsicValueLavel = new field.Label(set1,"value: ", new Rectangle(195, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        field.Label IntrinsicLabel = new field.Label(set1,"Intrinsic: ", new Rectangle(30, 20, 100, 25),
                HabitatsLabel,null,(JPanel)objectPanel);
        TextFieldList Intrinsic = new TextFieldList("Intrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,IntrinsicLabel,null,set1);
        AddButton addIntrinsic = new AddButton(HabitatsLabel,null,new Rectangle(350,20,100,25),Intrinsic,(JPanel)objectPanel,set1);
        field.Label ExtrinsicNameLabel = new field.Label(set1,"name: ", new Rectangle(110, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        field.Label ExtrinsicValueLabel = new field.Label(set1,"value: ", new Rectangle(195, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        field.Label ExtrinsicLabel = new field.Label(set1,"Extrinsic: ", new Rectangle(30, 20, 100, 25),
                Intrinsic,null,(JPanel)objectPanel);
        TextFieldList Extrinsic = new TextFieldList("Extrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,ExtrinsicLabel,null,set1);
        AddButton addExtrinsic = new AddButton(Intrinsic,null,new Rectangle(350,20,100,25),Extrinsic,(JPanel)objectPanel,set1);
        field.Label AffordancesLabel = new field.Label(set1,"Affordances: ", new Rectangle(15, 20, 120, 25),
                Extrinsic,null,(JPanel)objectPanel);
        TextFieldList Affordances = new TextFieldList("Affordances", new Rectangle(15,20,200,25),true,false,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,AffordancesLabel,null,set1);
        AddButton addAffordance = new AddButton(Extrinsic,null,new Rectangle(350,20,100,25),Affordances,(JPanel)objectPanel,set1);
        field.Label ScaleLabel = new field.Label(set1,"Scale: ", new Rectangle(15, 20, 70, 25),
                Affordances,null,(JPanel)objectPanel);
        field.DropDown Scale = new field.DropDown("Scale",possibleScales, new Rectangle(90,20,100,25),Affordances,null,
                (JPanel)objectPanel,map,set1);
        field.Label MovableLabel = new field.Label(set1,"Movable? ", new Rectangle(15, 20, 70, 25),
                Scale,null,(JPanel)objectPanel);
        field.CheckBox Movable = new field.CheckBox("Movable","",new Rectangle(90,20,22,25),Scale,null,
                (JPanel)objectPanel,map,set1);
        LoadButton Load = new LoadButton(null,null,new Rectangle(350,20,100,25),(JPanel)objectPanel,map,set1,
                "Object", this, tabs);
        SaveButton Save = new SaveButton(null,null,new Rectangle(460,20,100,25),(JPanel)objectPanel,map,set1, "Object");

        buttons.Button temp = new buttons.Button("map", null, null, new Rectangle(500, 400, 90, 40), (JPanel) objectPanel,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("MAP:");
                        if(map != null)
                        {
                            for(String key : map.keySet())
                            {
                                System.out.println(key + ": " + map.get(key));
                            }
                        }
                    }
                }, null);

        while(bar.getAdjustmentListeners().length>0)
            bar.removeAdjustmentListener(bar.getAdjustmentListeners()[0]);
        for(AnnotationComponent comp : set1)
            comp.setVerticalBar(bar);
        objectPanel.setBackground(lightPurple);
        objectPanel.setBounds(0,0,600, Movable.getBounds().y + Movable.getBounds().height + 20);
        objectPanel.setPreferredSize(new Dimension(600, Movable.getBounds().y + Movable.getBounds().height + 20));
        return objectScrollPane;
    }

    public JComponent createEventView() {
        JComponent eventPanel = new JPanel();
        eventPanel.setLayout(new GroupLayout(eventPanel));
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollBar bar = eventScrollPane.getVerticalScrollBar();
        set2.clear();

        field.Label NameLabel = new field.Label(set2,"Name: ", new Rectangle(15, 20, 85, 25),
                null, null, (JPanel) eventPanel);
        field.TextField Name = new field.TextField("Pred", new Rectangle(110, 20, 120, 25),
                null, null, (JPanel) eventPanel, map, set2);
        addNameFunctionality(Name);
        field.Label HeadLabel = new field.Label(set2,"Head: ", new Rectangle(15, 20, 85, 25),
                NameLabel, null, (JPanel) eventPanel);
        field.DropDown Head = new field.DropDown("Head", possibleEventHeads, new Rectangle(110,20,120,
                25), Name, null, (JPanel)eventPanel, map, set2);
        field.Label TypeLabel = new field.Label(set2, "Type: ", new Rectangle(15, 20, 85, 25),
                HeadLabel, null, (JPanel) eventPanel);
        DropDownList Types = new DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)eventPanel,map,null,possibleEventTypes,Head,null, set2);
        AddButton addType = new AddButton(Head, null, new Rectangle(350,20,100,25),Types,(JPanel)eventPanel,set2);
        field.Label ArgsLabel = new field.Label(set2,"Args: ", new Rectangle(15, 20, 100, 25),
                Types,null,(JPanel)eventPanel);
        TextFieldList Args = new TextFieldList("Args",new Rectangle(50, 20, 150, 25),true,false,Integer.MAX_VALUE,
            0,(JPanel)eventPanel,map,null,ArgsLabel,null,set2);
        AddButton addArg = new AddButton(Types,null,new Rectangle(350,20,100,25),Args,(JPanel)eventPanel,set2);
        field.Label BodyLabel = new field.Label(set2,"Body: ", new Rectangle(15, 20, 100, 25),
                Args,null,(JPanel)eventPanel);
        TextFieldList Body = new TextFieldList("Body",new Rectangle(50, 20, 150, 25),true,false,Integer.MAX_VALUE,
                0,(JPanel)eventPanel,map,null,BodyLabel,null,set2);
        AddButton addBody = new AddButton(Args,null,new Rectangle(350,20,100,25),Body,(JPanel)eventPanel,set2);
        field.Label EmbeddingSpaceLabel = new field.Label(set2,"Embedding Space: ", new Rectangle(15,20,200,25),Body,
                null,(JPanel)eventPanel);
        field.TextField EmbeddingSpace = new field.TextField("embeddingSpace",new Rectangle(30,20,100,25),EmbeddingSpaceLabel,null,
                (JPanel)eventPanel,map,set2);
        LoadButton Load = new LoadButton(null,null,new Rectangle(350,20,100,25),(JPanel)eventPanel,map,set2,"Program",
                this, tabs);
        SaveButton Save = new SaveButton(null,null,new Rectangle(460,20,100,25),(JPanel)eventPanel,map,set2,"Program");

        eventPanel.setBackground(lightGreen);
        eventPanel.setBounds(0,0,600, EmbeddingSpace.getBounds().y + EmbeddingSpace.getBounds().height + 20);
        eventPanel.setPreferredSize(new Dimension(600, EmbeddingSpace.getBounds().y + EmbeddingSpace.getBounds().height + 20));
        while(bar.getAdjustmentListeners().length>0)
            bar.removeAdjustmentListener(bar.getAdjustmentListeners()[0]);
        for(AnnotationComponent comp : set1)
            comp.setVerticalBar(bar);

        return eventScrollPane;
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    private void addNameFunctionality(TextField Name)
    {
        Name.setFrame(this);
        Name.textfield.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setTitle(Name.textfield.getText());
                    }
                }
        );
    }

    public HashSet<AnnotationComponent> getSet1()
    {
        return set1;
    }
    public HashSet<AnnotationComponent> getSet2()
    {
        return set2;
    }

    Color lightPurple = new Color(200, 150, 250);
    Color lightGreen = new Color(150, 250, 150);
}