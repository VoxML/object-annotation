package Main;

import buttons.*;
import field.AnnotationComponent;
import field.Box;
import field.TextField;
import lists.ComponentsList;
import lists.DropDownList;
import lists.TextFieldList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VoxML_Annotation_Tool extends JFrame {

    private static String[] possibleHeads = {"", "prismatoid", "pyramid", "wedge", "parallelepiped", "cupola", "frustum",
            "cylindroid", "ellipsoid", "hemiellipsoid", "bipyramid", "rectangular_prism", "toroid", "sheet"};
    private static String[] possibleTypes = {"","physobj","artifact","human"};
    private static String[] possibleScales = {"<agent","=agent",">agent"};
    private static String[] possibleEventHeads = {"","process","transition_event"};
    private static String[] possibleEventTypes = {"","state","process","transition_assignment","test"};

    private static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    private static HashSet<AnnotationComponent> set1 = new HashSet<AnnotationComponent>(); //to update location when things shift for object view
    private static HashSet<AnnotationComponent> set2 = new HashSet<AnnotationComponent>(); //to update location when things shift for event view

    private static JTabbedPane tabs;

    public VoxML_Annotation_Tool() {
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
        set1.clear();
        objectPanel.setBackground(lightPurple);

        field.Label NameLabel = new field.Label(set1,"Name: ", new Rectangle(20, 35, 85, 25),
                null, (JPanel) objectPanel);
        NameLabel.setBoxtop(true);
        field.TextField Name = new field.TextField("Pred", new Rectangle(110, 35, 120, 25),
                null, (JPanel) objectPanel, map, set1);
        Name.setBoxtop(true);
        addNameFunctionality(Name);
        field.Label HeadLabel = new field.Label(set1,"Head: ", new Rectangle(20, 20, 85, 25),
                NameLabel, (JPanel) objectPanel);
        field.DropDown Head = new field.DropDown("Head", possibleHeads, new Rectangle(110,20,120,
                25), Name, (JPanel)objectPanel, map, set1);
        field.TextField HeadIndex = new field.TextField("Head_index",new Rectangle(240,20,50,25),NameLabel,
                (JPanel)objectPanel,map,set1);
        field.Label TypeLabel = new field.Label(set1, "Type: ", new Rectangle(20, 20, 85, 25),
                HeadLabel, (JPanel) objectPanel);
        DropDownList Types = new DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)objectPanel,map,null,possibleTypes, Head, set1);
        Types.setLabel(TypeLabel);
        AddButton addType = new AddButton(Head, new Rectangle(350,20,100,25),Types,(JPanel)objectPanel,set1);
        field.Label ComponentsLabel = new field.Label(set1,"Components: ", new Rectangle(20, 20, 100, 25),
                Types,(JPanel)objectPanel);
        ComponentsLabel.setBoxtop(true);
        ComponentsList Components = new ComponentsList("Components", new Rectangle(50, 20, 100, 25), true, true,
                (JPanel)objectPanel,map,null,ComponentsLabel,set1);
        Components.setLabel(ComponentsLabel);
        AddButton addComponent = new AddButton(Types,new Rectangle(350,20,100,25),Components,(JPanel)objectPanel,set1);
        addComponent.setBoxtop(true);
        Components.getConcavityStrings();
        field.Label RotSymLabel = new field.Label(set1,"Rotational symmetry: ", new Rectangle(20, 20, 200, 25),
                Components,(JPanel)objectPanel);
        field.CheckBox rotationalSymmetryX = new field.CheckBox("RotatSym[0]","X",new Rectangle(30,20,45,25),RotSymLabel,
                (JPanel)objectPanel,map,set1);
        field.CheckBox rotationalSymmetryY = new field.CheckBox("RotatSym[1]","Y",new Rectangle(110,20,45,25),RotSymLabel,
                (JPanel)objectPanel,map,set1);
        field.CheckBox rotationalSymmetryZ = new field.CheckBox("RotatSym[2]","Z",new Rectangle(190,20,45,25),RotSymLabel,
                (JPanel)objectPanel,map,set1);
        field.Label ReflSymLabel = new field.Label(set1,"Reflection symmetry: ", new Rectangle(20, 20, 200, 25),
                rotationalSymmetryX,(JPanel)objectPanel);
        field.CheckBox reflectionSymmetryXY = new field.CheckBox("ReflSym[0]","XY",new Rectangle(30,20,55,25),ReflSymLabel,
                (JPanel)objectPanel,map,set1);
        field.CheckBox reflectionSymmetryYZ = new field.CheckBox("ReflSym[1]","YZ",new Rectangle(120,20,55,25),ReflSymLabel,
                (JPanel)objectPanel,map,set1);
        field.CheckBox reflectionSymmetryXZ = new field.CheckBox("ReflSym[2]","XZ",new Rectangle(210,20,55,25),ReflSymLabel,
                (JPanel)objectPanel,map,set1);
        //field.Label HabitatsLabel = new field.Label(set1,"Habitats: ", new Rectangle(20, 20, 100, 25),
                //reflectionSymmetryXY,(JPanel)objectPanel);
        field.Label IntrinsicNameLabel = new field.Label(set1,"name: ", new Rectangle(110, 20, 100, 25),
                reflectionSymmetryXZ,(JPanel)objectPanel);
        field.Label IntrinsicValueLabel = new field.Label(set1,"value: ", new Rectangle(195, 20, 100, 25),
                reflectionSymmetryXZ,(JPanel)objectPanel);
        field.Label IntrinsicLabel = new field.Label(set1,"Intrinsic: ", new Rectangle(30, 20, 100, 25),
                reflectionSymmetryXZ,(JPanel)objectPanel);
        TextFieldList Intrinsic = new TextFieldList("Intrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,IntrinsicLabel,set1);
        Intrinsic.setLabel(IntrinsicLabel);
        AddButton addIntrinsic = new AddButton(reflectionSymmetryXZ,new Rectangle(350,20,100,25),Intrinsic,(JPanel)objectPanel,set1);
        field.Label ExtrinsicNameLabel = new field.Label(set1,"name: ", new Rectangle(110, 20, 100, 25),
                Intrinsic,(JPanel)objectPanel);
        field.Label ExtrinsicValueLabel = new field.Label(set1,"value: ", new Rectangle(195, 20, 100, 25),
                Intrinsic,(JPanel)objectPanel);
        field.Label ExtrinsicLabel = new field.Label(set1,"Extrinsic: ", new Rectangle(30, 20, 100, 25),
                Intrinsic,(JPanel)objectPanel);
        TextFieldList Extrinsic = new TextFieldList("Extrinsic", new Rectangle(110,20,80,25),true,true,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,ExtrinsicLabel,set1);
        Extrinsic.setLabel(ExtrinsicLabel);
        AddButton addExtrinsic = new AddButton(Intrinsic,new Rectangle(350,20,100,25),Extrinsic,(JPanel)objectPanel,set1);
        IntrinsicLabel.setBoxtop(true);
        IntrinsicNameLabel.setBoxtop(true);
        IntrinsicValueLabel.setBoxtop(true);
        addIntrinsic.setBoxtop(true);
        TextFieldList Affordances = new TextFieldList("Affordances", new Rectangle(20,20,200,25),true,false,
                Integer.MAX_VALUE,0,(JPanel)objectPanel,map,null,Extrinsic,set1);
        Affordances.setBoxtop(true);
        Affordances.setLabel(null);
        AddButton addAffordance = new AddButton(Extrinsic, new Rectangle(350,20,100,25),Affordances,(JPanel)objectPanel,set1);
        addAffordance.setBoxtop(true);
        field.Label ScaleLabel = new field.Label(set1,"Scale: ", new Rectangle(20, 20, 70, 25),
                Affordances,(JPanel)objectPanel);
        ScaleLabel.setBoxtop(true);
        field.DropDown Scale = new field.DropDown("Scale",possibleScales, new Rectangle(90,20,100,25),Affordances,
                (JPanel)objectPanel,map,set1);
        Scale.setBoxtop(true);
        field.Label MovableLabel = new field.Label(set1,"Movable? ", new Rectangle(20, 20, 70, 25),
                Scale,(JPanel)objectPanel);
        field.CheckBox Movable = new field.CheckBox("Movable","",new Rectangle(90,20,22,25),Scale,
                (JPanel)objectPanel,map,set1);
        LoadButton Load = new LoadButton(null, new Rectangle(480,35,100,25),(JPanel)objectPanel,map,set1,
                "Object", this, tabs);
        SaveButton Save = new SaveButton(null, new Rectangle(480,70,100,25),(JPanel)objectPanel,map,set1, "Object");

        HashSet<Box> boxSet1 = new HashSet<Box>();
        field.Box Lex = new field.Box(set1,"Lex",new Rectangle(10,10,450,20),(JPanel)objectPanel,
                NameLabel,Types,boxSet1);
        Lex.setPrev(Movable);
        field.Box Type = new field.Box(set1,"Type",new Rectangle(10,10,450,20),(JPanel)objectPanel,
                ComponentsLabel,reflectionSymmetryXZ,boxSet1);
        Type.setPrev(Movable);
        field.Box HabitatsBox = new field.Box(set1,"Habitats",new Rectangle(10,10,450,20),(JPanel)objectPanel,
                IntrinsicLabel,Extrinsic,boxSet1);
        HabitatsBox.setPrev(Movable);
        field.Box AffordancesBox = new field.Box(set1,"Affordances",new Rectangle(10,10,450,20),(JPanel)objectPanel,
                Affordances,Affordances,boxSet1);
        AffordancesBox.setPrev(Movable);
        field.Box Embodiment = new field.Box(set1,"Embodiment",new Rectangle(10,10,450,20),(JPanel)objectPanel,
                ScaleLabel,MovableLabel,boxSet1);
        Embodiment.setPrev(Movable);

        JScrollPane objectScrollPane = new JScrollPane(objectPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollBar bar = objectScrollPane.getVerticalScrollBar();

        objectPanel.setBounds(0,0,600, Movable.getBounds().y + Movable.getBounds().height + 20);
        objectPanel.setPreferredSize(new Dimension(600, Movable.getBounds().y + Movable.getBounds().height + 20));
        while(bar.getAdjustmentListeners().length>0)
            bar.removeAdjustmentListener(bar.getAdjustmentListeners()[0]);
        for(AnnotationComponent comp : set1)
            comp.setVerticalBar(bar);

        return objectScrollPane;
    }

    public JComponent createEventView() {
        JComponent eventPanel = new JPanel();
        eventPanel.setLayout(new GroupLayout(eventPanel));
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollBar bar = eventScrollPane.getVerticalScrollBar();
        set2.clear();
        eventPanel.setBackground(lightGreen);

        field.Label NameLabel = new field.Label(set2,"Name: ", new Rectangle(20, 35, 85, 25),
                null, (JPanel) eventPanel);
        NameLabel.setBoxtop(true);
        field.TextField Name = new field.TextField("Pred", new Rectangle(110, 35, 120, 25),
                null, (JPanel) eventPanel, map, set2);
        addNameFunctionality(Name);
        Name.setBoxtop(true);
        field.Label HeadLabel = new field.Label(set2,"Head: ", new Rectangle(20, 20, 85, 25),
                NameLabel, (JPanel) eventPanel);
        field.DropDown Head = new field.DropDown("Head", possibleEventHeads, new Rectangle(110,20,120,
                25), Name, (JPanel)eventPanel, map, set2);
        field.Label TypeLabel = new field.Label(set2, "Type: ", new Rectangle(20, 20, 85, 25),
                HeadLabel, (JPanel) eventPanel);
        DropDownList Types = new DropDownList("Type", new Rectangle(110,20,100,25),true,false,
                3,0,(JPanel)eventPanel,map,null,possibleEventTypes,Head, set2);
        Types.setLabel(TypeLabel);
        AddButton addType = new AddButton(Head, new Rectangle(350,20,100,25),Types,(JPanel)eventPanel,set2);
        field.Label ArgsLabel = new field.Label(set2,"Args: ", new Rectangle(20, 20, 100, 25),
                Types,(JPanel)eventPanel);
        ArgsLabel.setBoxtop(true);
        TextFieldList Args = new TextFieldList("Args",new Rectangle(50, 20, 150, 25),true,false,
                Integer.MAX_VALUE,0,(JPanel)eventPanel,map,null,ArgsLabel,set2);
        Args.setLabel(ArgsLabel);
        AddButton addArg = new AddButton(Types, new Rectangle(350,20,100,25),Args,(JPanel)eventPanel,set2);
        addArg.setBoxtop(true);
        field.Label BodyLabel = new field.Label(set2,"Body: ", new Rectangle(20, 20, 100, 25),
                Args,(JPanel)eventPanel);
        TextFieldList Body = new TextFieldList("Body",new Rectangle(50, 20, 150, 25),true,false,Integer.MAX_VALUE,
                0,(JPanel)eventPanel,map,null,BodyLabel,set2);
        Body.setLabel(BodyLabel);
        AddButton addBody = new AddButton(Args, new Rectangle(350,20,100,25),Body,(JPanel)eventPanel,set2);
        field.Label EmbeddingSpaceLabel = new field.Label(set2,"Embedding Space: ", new Rectangle(20,20,200,25),Body,
                (JPanel)eventPanel);
        EmbeddingSpaceLabel.setBoxtop(true);
        field.TextField EmbeddingSpace = new field.TextField("embeddingSpace",new Rectangle(30,20,100,25),EmbeddingSpaceLabel,
                (JPanel)eventPanel,map,set2);
        LoadButton Load = new LoadButton(null, new Rectangle(480,35,100,25),(JPanel)eventPanel,map,set2,"Program",
                this, tabs);
        SaveButton Save = new SaveButton(null, new Rectangle(480,70,100,25),(JPanel)eventPanel,map,set2,"Program");

        HashSet<Box> boxSet2 = new HashSet<Box>();
        field.Box Lex = new field.Box(set2,"Lex",new Rectangle(10,10,450,20),(JPanel)eventPanel,
                NameLabel,Types,boxSet2);
        Lex.setPrev(EmbeddingSpace);
        field.Box Type = new field.Box(set2,"Type",new Rectangle(10,10,450,20),(JPanel)eventPanel,
                ArgsLabel,Body,boxSet2);
        Type.setPrev(EmbeddingSpace);
        field.Box EmbeddingSpaceBox = new field.Box(set2,"Embedding Space",new Rectangle(10,10,450,20),(JPanel)eventPanel,
                EmbeddingSpaceLabel,EmbeddingSpace,boxSet2);
        EmbeddingSpaceBox.setPrev(EmbeddingSpace);

        eventPanel.setBounds(0,0,600, EmbeddingSpace.getBounds().y + EmbeddingSpace.getBounds().height + 20);
        eventPanel.setPreferredSize(new Dimension(600, EmbeddingSpace.getBounds().y + EmbeddingSpace.getBounds().height + 20));
        while(bar.getAdjustmentListeners().length>0)
            bar.removeAdjustmentListener(bar.getAdjustmentListeners()[0]);
        for(AnnotationComponent comp : set1)
            comp.setVerticalBar(bar);

        return eventScrollPane;
    }

    private void addNameFunctionality(TextField Name)
    {
        Name.setFrame(this);
        Name.getTextfield().addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setTitle(Name.getTextfield().getText());
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
    public HashMap<String, ArrayList<String>> getMap() {return map; }
    public JTabbedPane getTabs() {return tabs; }

    Color lightPurple = new Color(200, 150, 250);
    Color lightGreen = new Color(150, 250, 150);
}