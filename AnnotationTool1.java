import java.awt.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AnnotationTool1 extends JFrame {

	//OBJECT
 private JLabel NameLabel, TypeLabel, HeadLabel, CompLabel, CompNameLabel, CompIndexLabel, RotSymLabel, ReflSymLabel;
 private JLabel HabitatsLabel, HabitatsNameLabel, HabitatsValueLabel, IntrinsicLabel, ExtrinsicLabel, AffordancesLabel;
 private JLabel ScaleLabel, MovableLabel;
 private JButton Save, Load, TypeAdd, CompAdd, IntrinsicAdd, ExtrinsicAdd, AffordancesAdd;
 private JButton object, event, object1, event1;
 private JTextField Path, Name, HeadIndex;
 private JComboBox<String> Head, Scale;
 private JCheckBox Movable, RotSymX, RotSymY, RotSymZ, ReflSymXY, ReflSymYZ, ReflSymXZ;
 private ArrayList<JCheckBox> Concavity = new ArrayList<JCheckBox>();
 private ArrayList<JTextField> CompNames = new ArrayList<JTextField>();
 private ArrayList<JTextField> CompInds = new ArrayList<JTextField>();
 private ArrayList<JTextField> IntrinsicNames = new ArrayList<JTextField>();
 private ArrayList<JTextField> IntrinsicValues = new ArrayList<JTextField>();
 private ArrayList<JTextField> ExtrinsicNames = new ArrayList<JTextField>();
 private ArrayList<JTextField> ExtrinsicValues = new ArrayList<JTextField>();
 private ArrayList<JTextField> Affordances = new ArrayList<JTextField>();
 private ArrayList<JComboBox<String>> Types = new ArrayList<JComboBox<String>>();
 private ArrayList<JComboBox<String>> RotSym = new ArrayList<JComboBox<String>>();
 private ArrayList<JComboBox<String>> ReflSym = new ArrayList<JComboBox<String>>();
 private ArrayList<JButton> TypeRemove = new ArrayList<JButton>();
 private ArrayList<JButton> CompRemove = new ArrayList<JButton>();
 private ArrayList<JButton> RotSymRemove = new ArrayList<JButton>();
 private ArrayList<JButton> ReflSymRemove = new ArrayList<JButton>();
 private ArrayList<JButton> IntrinsicRemove = new ArrayList<JButton>();
 private ArrayList<JButton> ExtrinsicRemove = new ArrayList<JButton>();
 private ArrayList<JButton> AffordancesRemove = new ArrayList<JButton>();
 private int n, n1, n2, n3, n4, n5, n6, n7, indicator;
 private String typesString, headString, concavityString, rotSymString, reflSymString;
 private ArrayList<String> componentsStrings = new ArrayList<String>();
 private ArrayList<String> intrinsicNameStrings = new ArrayList<String>();
 private ArrayList<String> intrinsicValueStrings = new ArrayList<String>();
 private ArrayList<String> extrinsicNameStrings = new ArrayList<String>();
 private ArrayList<String> extrinsicValueStrings = new ArrayList<String>();
 private ArrayList<String> affordanceStrings = new ArrayList<String>();
 private JPanel panel,eventPanel; 
 private JScrollPane scroller,panel1;
 private String[] possibleTypes = {"","physobj","artifact","human"};
 private String path;
 private String readName, readHead, readHeadIndex, readScale, readMovable;
 private ArrayList<String> readTypes = new ArrayList<String>();
 private ArrayList<String> readComponents = new ArrayList<String>();
 private ArrayList<String> readCompInds = new ArrayList<String>();
 private ArrayList<String> readConcaveInds = new ArrayList<String>();
 private ArrayList<String> readRotatSym = new ArrayList<String>();
 private ArrayList<String> readReflSym = new ArrayList<String>();
 private ArrayList<String> readIntrinsicNames = new ArrayList<String>();
 private ArrayList<String> readIntrinsicValues = new ArrayList<String>();
 private ArrayList<String> readExtrinsicNames = new ArrayList<String>();
 private ArrayList<String> readExtrinsicValues = new ArrayList<String>();
 private ArrayList<String> readAffords = new ArrayList<String>(); 

 //EVENT
 private JLabel NameLabel1, TypeLabel1, HeadLabel1, ArgsLabel, ArgsNameLabel, ArgsIndexLabel;
 private JLabel BodyLabel, BodyNameLabel, BodyIndexLabel, EmbeddingSpaceLabel;
 private ArrayList<JTextField> ArgNames = new ArrayList<JTextField>();
 private ArrayList<JTextField> ArgInds = new ArrayList<JTextField>();
 private ArrayList<JTextField> BodyNames = new ArrayList<JTextField>();
 private ArrayList<JTextField> BodyInds = new ArrayList<JTextField>();
 private JButton TypeAdd1, ArgAdd, BodyAdd, Save1, Load1;
 private ArrayList<JButton> TypeRemove1 = new ArrayList<JButton>();
 private JTextField Name1, HeadIndex1, EmbeddingSpace;
 private ArrayList<JComboBox<String>> Types1 = new ArrayList<JComboBox<String>>();
 private String[] possibleTypes1 = {"","process","transition_event"};
 private int nE, n1E, n2E, n3E;
 private String typesString1, headString1, path1;
 private JComboBox<String> Head1;
 private ArrayList<String> argsStrings = new ArrayList<String>();
 private ArrayList<JButton> ArgRemove = new ArrayList<JButton>();
 private ArrayList<String> bodyStrings = new ArrayList<String>();
 private ArrayList<JButton> BodyRemove = new ArrayList<JButton>();
 private String readName1, readHead1;
 private ArrayList<String> readTypes1 = new ArrayList<String>();
 private ArrayList<String> readArgs1 = new ArrayList<String>();
 private ArrayList<String> readBody1 = new ArrayList<String>();


 
 public AnnotationTool1() {
  createObjectView();
  createEventView();
  pack();
  setSize(new Dimension(640,770)); //set default frame size
  setLocationRelativeTo(null); //starts at the middle of the screen
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true); 
 }
 private void createObjectView() {
  indicator = 0;
  Color lightPurple = new Color(200, 150, 250);
  Color lightGreen = new Color(150, 250, 150);
  Color black = new Color(0, 0, 0);
  panel = new JPanel();
  panel.setBackground(lightPurple);
  panel.setLayout(null);
  eventPanel = new JPanel();
  
  object = new JButton("Object Annotation");
  event = new JButton("Event Annotation");
  object.setBounds(20,10,150,25);
  event.setBounds(175,10,150,25);
  object.setVisible(true);
  event.setVisible(true);
 
  panel.add(object);
  panel.add(event);
  
  event.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      panel.setVisible(false);
      remove(panel);
      remove(scroller);
      add(eventPanel);
      eventPanel.setVisible(true);
     }
    }
  );
  
  //NAME
  NameLabel = new JLabel("Name:");
  NameLabel.setBounds(20,20,40,25);
  NameLabel.setVisible(true);
  Name = new JTextField();
  Name.setBounds(65,20,80,25);
  Name.setVisible(true);
  Name.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      setTitle(Name.getText());
     }
    }
  );        
  panel.add(NameLabel);
  panel.add(Name);
    
  //TYPE
  TypeLabel = new JLabel("Type:");
  TypeLabel.setVisible(true);
  TypeLabel.setBounds(20,50,40,25);
  panel.add(TypeLabel);
  JComboBox<String> type = new JComboBox<String>(possibleTypes);
  type.setBounds(40,80,150,25);
  type.setVisible(true);
  Types.add(type);
  JButton remove2 = new JButton("remove");
  remove2.setBounds(195,80,80,25);;
  remove2.setVisible(true);
  remove2.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      Types.get(0).setEnabled(false);
        TypeRemove.get(0).setEnabled(false);
        Types.get(0).setVisible(false);
        TypeRemove.get(0).setVisible(false);
      panel.remove(Types.get(0));
         panel.remove(TypeRemove.get(0));          
         panel.revalidate();
         panel.repaint();
         Types.remove(0);
         TypeRemove.remove(0);          
         updateTypes();
         n1 = Types.size();
         for(int k = 0; k < n1; k++)
         {
          Types.get(k).setBounds(40,80+30*k,150,25);
         }
         if(n1<3)
          TypeAdd.setVisible(true);
         updateLocation(); 
     }
    }
  );
  TypeRemove.add(remove2);
  panel.add(TypeRemove.get(0));
  panel.add(Types.get(0));
  TypeAdd = new JButton("add");
  TypeAdd.setBounds(300,50,80,25);
  TypeAdd.setVisible(true);
  panel.add(TypeAdd);
     TypeAdd.addActionListener(
     new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      n1 = Types.size();
      JComboBox<String> type = new JComboBox<String>(possibleTypes);
      type.setBounds(40,80+30*n1,150,25);
      type.setVisible(true);
      Types.add(type);
      panel.add(Types.get(Types.size()-1));
      JButton remove2 = new JButton("remove");
      remove2.setBounds(195,80+30*n1,80,25);
      remove2.setVisible(true);
      TypeRemove.add(remove2);
      panel.add(TypeRemove.get(TypeRemove.size()-1));
      n1 = Types.size();
      if (n1==3)
       TypeAdd.setVisible(false);
      updateTypes();
      updateLocation();
     }
    }
  );
     updateTypes();
     //LOAD
     Load = new JButton("Load from XML");
     Load.setBounds(440, 20, 120, 25);
     Load.setVisible(true);
     panel.add(Load);
          
     Path = new JTextField();
     Path.setVisible(true);
     panel.add(Path);

     Load.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         JFileChooser fileChooser = new JFileChooser();
         // For Directory
         // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         // For File
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         fileChooser.setAcceptAllFileFilterUsed(false);
         int rVal = fileChooser.showOpenDialog(null);
         if (rVal == JFileChooser.APPROVE_OPTION) {
           Path.setText(fileChooser.getSelectedFile().toString());
         }
         path = fileChooser.getSelectedFile().toString();
         //System.out.println(path);
         readXML();
       }
     });
     
     n1 = Types.size();
     
     //HEAD
     HeadLabel = new JLabel("Head:");
     HeadLabel.setBounds(20,80+30*n1,40,25);
     HeadLabel.setVisible(true);
     panel.add(HeadLabel);
     String[] possibleHeads = {"", "prismatoid", "pyramid", "wedge", "parallelepiped", "cupola", "frustum", "cylindroid", "ellipsoid", "hemiellipsoid", "bipyramid", "rectangular prism", "toroid", "sheet"};
     Head = new JComboBox<String>(possibleHeads);
     Head.setBounds(65,80+30*n1,150,25);
     Head.setVisible(true);
     panel.add(Head);
     HeadIndex = new JTextField();
     HeadIndex.setBounds(220,80+30*n1,40,25);
     HeadIndex.setVisible(true);
     panel.add(HeadIndex);
     Head.addActionListener(
     new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      headString = (String) Head.getSelectedItem() + "[" + HeadIndex.getText() + "]";
      }
    }
  );
  HeadIndex.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      headString = (String) Head.getSelectedItem() + "[" + HeadIndex.getText() + "]";
     }
    }
  );
      
  //COMPONENTS
  CompLabel = new JLabel("Components:");
  CompLabel.setBounds(20,120+30*n1,100,25);
  CompLabel.setVisible(true);
  panel.add(CompLabel);
  CompNameLabel = new JLabel("name:");
  CompNameLabel.setBounds(40,160+30*n1,60,25);
  CompNameLabel.setVisible(true);
  panel.add(CompNameLabel);
  CompIndexLabel = new JLabel("index:");
  CompIndexLabel.setBounds(105,160+30*n1,60,25);
  CompIndexLabel.setVisible(true);
  panel.add(CompIndexLabel);
  JTextField componentName = new JTextField();
  JTextField componentInd = new JTextField();
  componentName.setBounds(40,190+30*n1,60,25);
  componentInd.setBounds(105,190+30*n1,60,25);
  componentName.setVisible(true);
  componentInd.setVisible(true);
  CompNames.add(componentName);
  CompInds.add(componentInd);
  JButton remove1 = new JButton("remove");
  JCheckBox concave = new JCheckBox("Concave");  
  concave.setBounds(255,190+30*n1,80,25);
  concave.setVisible(true);
  Concavity.add(concave);
  panel.add(Concavity.get(Concavity.size()-1));

  remove1.setBounds(170,190+30*n1,80,25);
  remove1.setVisible(true);
  remove1.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      CompNames.get(0).setEnabled(false);
      CompInds.get(0).setEnabled(false);
      CompRemove.get(0).setEnabled(false);
      Concavity.get(0).setEnabled(false);
      CompNames.get(0).setVisible(false);
      CompInds.get(0).setVisible(false);
      CompRemove.get(0).setVisible(false);
      Concavity.get(0).setVisible(false);
      panel.remove(CompNames.get(0));
      panel.remove(CompInds.get(0));
         panel.remove(CompRemove.get(0));  
         panel.remove(Concavity.get(0));
         panel.revalidate();
         panel.repaint();
         CompNames.remove(0);
         CompInds.remove(0);
         CompRemove.remove(0); 
         Concavity.remove(0); 
         updateComponents();
         n2 = CompNames.size();
         for(int k = 0; k < n2; k++)
         {
          CompNames.get(k).setBounds(40,190+30*n1+30*n2,60,25);
          CompInds.get(k).setBounds(105,190+30*n1+30*n2,60,25);
          CompRemove.get(k).setBounds(170,190+30*n1+30*n2,80,25);
          Concavity.get(k).setBounds(255,190+30*n1,80,25);
         }
         updateLocation(); 
     }
    }
  );
  CompRemove.add(remove1);
  panel.add(CompRemove.get(0));
  panel.add(CompNames.get(0));
  panel.add(CompInds.get(0));
  CompAdd = new JButton("add");
  CompAdd.setBounds(300,130+30*n1,80,25);
  CompAdd.setVisible(true);
  panel.add(CompAdd);
  CompAdd.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      n2 = CompNames.size();
      JTextField componentName = new JTextField();
      JTextField componentInd = new JTextField();
      JCheckBox concave = new JCheckBox("Concave");
      componentName.setBounds(40,190+30*n1+30*n2,60,25);
      componentInd.setBounds(105,190+30*n1+30*n2,60,25);
      concave.setBounds(255,190+30*n1+30*n2,60,25);
      componentName.setVisible(true);
      componentInd.setVisible(true);
      concave.setVisible(true);
      CompNames.add(componentName);
      CompInds.add(componentInd);
      Concavity.add(concave);
      panel.add(CompNames.get(CompNames.size()-1));
      panel.add(CompInds.get(CompInds.size()-1));
      panel.add(Concavity.get(Concavity.size()-1));
      JButton remove1 = new JButton("remove");
      remove1.setBounds(170,190+30*n1+30*n2,80,25);
      remove1.setVisible(true);
      CompRemove.add(remove1);
      panel.add(CompRemove.get(CompRemove.size()-1));
      n2 = CompNames.size();
      updateComponents();
      updateLocation(); 
     }
    }
  );
     updateComponents();
  n2 = CompNames.size();
 
  //ROTSYM
  RotSymLabel = new JLabel("Rotational Symmetry:");
  RotSymLabel.setVisible(true);
  RotSymLabel.setBounds(20,190+30*n1+30*n2,200,25);
  panel.add(RotSymLabel);
  
  RotSymX = new JCheckBox("X");
  RotSymX.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateRotSym();}});
  RotSymY = new JCheckBox("Y");
  RotSymY.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateRotSym();}});
  RotSymZ = new JCheckBox("Z");
  RotSymZ.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateRotSym();}});
  RotSymX.setBounds(40,220+30*n1+30*n2,50,25);
  RotSymY.setBounds(145,220+30*n1+30*n2,50,25);
  RotSymZ.setBounds(250,220+30*n1+30*n2,50,25);
  RotSymX.setVisible(true);
  RotSymY.setVisible(true);
  RotSymZ.setVisible(true);
  panel.add(RotSymX);
  panel.add(RotSymY);
  panel.add(RotSymZ);  
   
   //REFLSYM
  
  ReflSymLabel = new JLabel("Reflection Symmetry:");
  ReflSymLabel.setVisible(true);
  ReflSymLabel.setBounds(20,280+30*n1+30*n2,200,25);
  panel.add(ReflSymLabel);
  
  ReflSymXY = new JCheckBox("XY");
  ReflSymXY.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateReflSym();}});
  ReflSymYZ = new JCheckBox("YZ");
  ReflSymYZ.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateReflSym();}});
  ReflSymXZ = new JCheckBox("XZ");
  ReflSymXZ.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateReflSym();}});
  ReflSymXY.setBounds(40,280+30*n1+30*n2,50,25);
  ReflSymYZ.setBounds(145,280+30*n1+30*n2,50,25);
  ReflSymXZ.setBounds(250,280+30*n1+30*n2,50,25);
  ReflSymXY.setVisible(true);
  ReflSymYZ.setVisible(true);
  ReflSymXZ.setVisible(true);
  panel.add(ReflSymXY);
  panel.add(ReflSymYZ);
  panel.add(ReflSymXZ);
          
    //Habitats
   HabitatsLabel = new JLabel("Habitats:");
   HabitatsLabel.setBounds(20,260+30*n1+30*n2,100,25);
   HabitatsLabel.setVisible(true);
   panel.add(HabitatsLabel);
   HabitatsNameLabel = new JLabel("name:");
   HabitatsNameLabel.setBounds(60,290+30*n1+30*n2,60,25);
   HabitatsNameLabel.setVisible(true);
   panel.add(HabitatsNameLabel);
   HabitatsValueLabel = new JLabel("value:");
   HabitatsValueLabel.setBounds(125,290+30*n1+30*n2,60,25);
   HabitatsValueLabel.setVisible(true);
   panel.add(HabitatsValueLabel);
   IntrinsicLabel = new JLabel("Intrinsic");
   IntrinsicLabel.setBounds(20,320+30*n1+30*n2,60,25);
   IntrinsicLabel.setVisible(true);
   panel.add(IntrinsicLabel);
   JTextField IntrName = new JTextField();
   JTextField IntrValue = new JTextField();
   IntrName.setBounds(60,350+30*n1+30*n2,60,25);
   IntrValue.setBounds(125,350+30*n1+30*n2,60,25);
   IntrName.setVisible(true);
   IntrValue.setVisible(true);
   IntrinsicNames.add(IntrName);
   IntrinsicValues.add(IntrValue);
   JButton remove5 = new JButton("remove");
   remove5.setBounds(190,350+30*n1+30*n2,80,25);
   remove5.setVisible(true);
   remove5.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       if(IntrinsicNames.size()>0)
       {
        IntrinsicNames.get(0).setEnabled(false);
        IntrinsicValues.get(0).setEnabled(false);
        IntrinsicRemove.get(0).setEnabled(false);
        IntrinsicNames.get(0).setVisible(false);
        IntrinsicValues.get(0).setVisible(false);
        IntrinsicRemove.get(0).setVisible(false);
        panel.remove(IntrinsicNames.get(0));
        panel.remove(IntrinsicValues.get(0));
           panel.remove(IntrinsicRemove.get(0));  
           panel.revalidate();
           panel.repaint();
           IntrinsicNames.remove(0);
           IntrinsicValues.remove(0);
           IntrinsicRemove.remove(0); 
       }
          updateHabitats();
          n5 = IntrinsicNames.size();
          for(int k = 0; k < n5; k++)
          {
           IntrinsicNames.get(k).setBounds(60,350+30*n1+30*n2+30*n5,60,25);
           IntrinsicValues.get(k).setBounds(125,350+30*n1+30*n2+30*n5,60,25);
           IntrinsicRemove.get(k).setBounds(190,350+30*n1+30*n2+30*n5,80,25);
          }
          updateLocation(); 
      }
     }
   );
   IntrinsicRemove.add(remove5);
   panel.add(IntrinsicRemove.get(0));
   panel.add(IntrinsicNames.get(0));
   panel.add(IntrinsicValues.get(0));
   IntrinsicAdd = new JButton("add");
   IntrinsicAdd.setBounds(300,320+30*n1+30*n2,80,25);
   IntrinsicAdd.setVisible(true);
   panel.add(IntrinsicAdd);
   IntrinsicAdd.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       n5 = IntrinsicNames.size();
       JTextField intrName = new JTextField();
       JTextField intrValue = new JTextField();
       intrName.setBounds(60,290+30*n1+30*n2+30*n5,60,25);
       intrValue.setBounds(125,290+30*n1+30*n2+30*n5,60,25);
       intrName.setVisible(true);
       intrValue.setVisible(true);
       IntrinsicNames.add(intrName);
       IntrinsicValues.add(intrValue);
       panel.add(IntrinsicNames.get(IntrinsicNames.size()-1));
       panel.add(IntrinsicValues.get(IntrinsicValues.size()-1));
       JButton remove5 = new JButton("remove");
       remove5.setBounds(190,290+30*n1+30*n2+30*n5,80,25);
       remove5.setVisible(true);
       IntrinsicRemove.add(remove5);
       panel.add(IntrinsicRemove.get(IntrinsicRemove.size()-1));
       n5 = IntrinsicNames.size();
       updateHabitats();
       updateLocation(); 
      }
     }
   );
      updateHabitats();
   n5 = IntrinsicNames.size();

   ExtrinsicLabel = new JLabel("Extrinsic");
   ExtrinsicLabel.setBounds(20,350+30*n1+30*n2+30*n5,60,25);
   ExtrinsicLabel.setVisible(true);
   panel.add(ExtrinsicLabel);
   JTextField ExtrName = new JTextField();
   JTextField ExtrValue = new JTextField();
   ExtrName.setBounds(60,380+30*n1+30*n2+30*n5,60,25);
   ExtrValue.setBounds(125,380+30*n1+30*n2+30*n5,60,25);
   ExtrName.setVisible(true);
   ExtrValue.setVisible(true);
   ExtrinsicNames.add(ExtrName);
   ExtrinsicValues.add(ExtrValue);
   JButton remove6 = new JButton("remove");
   remove6.setBounds(190,380+30*n1+30*n2+30*n5,80,25);
   remove6.setVisible(true);
   remove6.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       if(ExtrinsicNames.size()>0)
       {
        ExtrinsicNames.get(0).setEnabled(false);
        ExtrinsicValues.get(0).setEnabled(false);
        ExtrinsicRemove.get(0).setEnabled(false);
        ExtrinsicNames.get(0).setVisible(false);
        ExtrinsicValues.get(0).setVisible(false);
        ExtrinsicRemove.get(0).setVisible(false);
        panel.remove(ExtrinsicNames.get(0));
        panel.remove(ExtrinsicValues.get(0));
           panel.remove(ExtrinsicRemove.get(0));  
           panel.revalidate();
           panel.repaint();
           ExtrinsicNames.remove(0);
           ExtrinsicValues.remove(0);
           ExtrinsicRemove.remove(0); 
       }
          updateHabitats();
          n6 = ExtrinsicNames.size();
          for(int k = 0; k < n6; k++)
          {
           ExtrinsicNames.get(k).setBounds(60,380+30*n1+30*n2+30*n5+30*n6,60,25);
           ExtrinsicValues.get(k).setBounds(125,380+30*n1+30*n2+30*n5+30*n6,60,25);
           ExtrinsicRemove.get(k).setBounds(190,380+30*n1+30*n2+30*n5+30*n6,80,25);
          }
          updateLocation(); 
      }
     }
   );
   ExtrinsicRemove.add(remove6);
   panel.add(ExtrinsicRemove.get(0));
   panel.add(ExtrinsicNames.get(0));
   panel.add(ExtrinsicValues.get(0));
   ExtrinsicAdd = new JButton("add");
   ExtrinsicAdd.setBounds(300,350+30*n1+30*n2+30*n5,80,25);
   ExtrinsicAdd.setVisible(true);
   panel.add(ExtrinsicAdd);
   ExtrinsicAdd.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       n6 = ExtrinsicNames.size();
       JTextField extrName = new JTextField();
       JTextField extrValue = new JTextField();
       extrName.setBounds(60,320+30*n1+30*n2+30*n5+30*n6,60,25);
       extrValue.setBounds(125,320+30*n1+30*n2+30*n5+30*n6,60,25);
       extrName.setVisible(true);
       extrValue.setVisible(true);
       ExtrinsicNames.add(extrName);
       ExtrinsicValues.add(extrValue);
       panel.add(ExtrinsicNames.get(ExtrinsicNames.size()-1));
       panel.add(ExtrinsicValues.get(ExtrinsicValues.size()-1));
       JButton remove6 = new JButton("remove");
       remove6.setBounds(190,320+30*n1+30*n2+30*n5+30*n6,80,25);
       remove6.setVisible(true);
       ExtrinsicRemove.add(remove6);
       panel.add(ExtrinsicRemove.get(ExtrinsicRemove.size()-1));
       n6 = ExtrinsicNames.size();
       updateHabitats();
       updateLocation(); 
      }
     }
   );
      updateHabitats();
   n6 = ExtrinsicNames.size();
   
   
   //AFFORDANCES
   AffordancesLabel = new JLabel("Affordances:");
   AffordancesLabel.setBounds(20,380+30*n1+30*n2+30*n5+30*n6,100,25);
   AffordancesLabel.setVisible(true);
   panel.add(AffordancesLabel);
   JTextField afford = new JTextField();
   afford.setBounds(40,410+30*n1+30*n2+30*n5+30*n6,120,25);
   afford.setVisible(true);
   Affordances.add(afford);
   JButton remove7 = new JButton("remove");
   remove7.setBounds(170,410+30*n1+30*n2+30*n5+30*n6,80,25);
   remove7.setVisible(true);
   remove7.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       Affordances.get(0).setEnabled(false);
       AffordancesRemove.get(0).setEnabled(false);
       Affordances.get(0).setVisible(false);
       AffordancesRemove.get(0).setVisible(false);
       panel.remove(Affordances.get(0));
          panel.remove(AffordancesRemove.get(0));  
          panel.revalidate();
          panel.repaint();
          Affordances.remove(0);
          AffordancesRemove.remove(0); 
          updateAffordances();
          n7 = IntrinsicNames.size();
          if(n7 > 0)
           {for(int k = 0; k < n7; k++)
           {
            Affordances.get(k).setBounds(40,410+30*n1+30*n2+30*n5+30*n6+30*n7,120,25);
            AffordancesRemove.get(k).setBounds(170,410+30*n1+30*n2+30*n5+30*n6+30*n7,80,25);
           }
           updateLocation(); 
          }
      }
     }
   );
   AffordancesRemove.add(remove7);
   panel.add(AffordancesRemove.get(0));
   panel.add(Affordances.get(0));
   AffordancesAdd = new JButton("add");
   AffordancesAdd.setBounds(300,380+30*n1+30*n2+30*n5+30*n6,80,25);
   AffordancesAdd.setVisible(true);
   panel.add(AffordancesAdd);
   AffordancesAdd.addActionListener(
     new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       n7 = Affordances.size();
       JTextField afford = new JTextField();
       afford.setBounds(40,410+30*n1+30*n2+30*n5+30*n6+30*n7,120,25);
       afford.setVisible(true);
       Affordances.add(afford);
       panel.add(Affordances.get(Affordances.size()-1));
       JButton remove7 = new JButton("remove");
       remove7.setBounds(170,410+30*n1+30*n2+30*n5+30*n6+30*n7,80,25);
       remove7.setVisible(true);
       AffordancesRemove.add(remove7);
       panel.add(AffordancesRemove.get(AffordancesRemove.size()-1));
       n7 = Affordances.size();
       updateAffordances();
       updateLocation(); 
      }
     }
   );
   updateAffordances();
   n7 = Affordances.size();
   
   //SCALE AND MOVABLE
   ScaleLabel = new JLabel("Scale:");
   ScaleLabel.setBounds(20,470+30*n1+30*n2+30*n5+30*n6+30*n7,80,25);
   ScaleLabel.setVisible(true);
   panel.add(ScaleLabel);
   String[] possibleScales = {"","<agent","=agent",">agent"};
   Scale = new JComboBox<String>(possibleScales);
   Scale.setBounds(105,470+30*n1+30*n2+30*n5+30*n6+30*n7,100,25);
   Scale.setVisible(true);
   panel.add(Scale);
   
   MovableLabel = new JLabel("Movable?");
   MovableLabel.setBounds(20,500+30*n1+30*n2+30*n5+30*n6+30*n7,80,25);
   MovableLabel.setVisible(true);
   panel.add(MovableLabel);
   Movable = new JCheckBox();
   Movable.setBounds(105,500+30*n1+30*n2+30*n5+30*n6+30*n7,20,25);
   Movable.setVisible(true);
   panel.add(Movable);
   
     //XML
  Save = new JButton("Save to XML");
  Save.setBounds(300,20,120,25);
  Save.setVisible(true);
  panel.add(Save);
     Save.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      writeXML();
      System.out.println("Saved to XML");
     }
    }
  );
  //updateLocation();
  
  //JScrollPane JScrollPane = new JScrollPane(panel);
  //JScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  //JScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  //JScrollPane.setPreferredSize(new Dimension(450, 110));
  //getContentPane().add(JScrollPane, BorderLayout.CENTER);
  updateLocation();
  updateRotSym();
  updateReflSym();
  add(panel);
  getContentPane().add(panel);
  eventPanel.setVisible(false);
  panel.setVisible(true);
  scroller = new JScrollPane(panel);
  scroller.getVerticalScrollBar().setPreferredSize(new Dimension(15,20));
  scroller.getVerticalScrollBar().setBlockIncrement(20);
  getContentPane().add(scroller, BorderLayout.CENTER);
  scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
  JButton scrollTest = new JButton ("scroll"); 
  scrollTest.setBounds(400,200,200,30);
  scrollTest.setVisible(true);
  panel.add(scrollTest);
  scrollTest.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      System.out.println(scroller.getVerticalScrollBar().getVisibleAmount());
      System.out.println(scroller.getVerticalScrollBar().getValue());
      System.out.println(panel.getBounds());
      //System.out.println(panel.getMaximumSize().getHeight());
      scroller.getVerticalScrollBar().setVisibleAmount((int)((scroller.getVerticalScrollBar().getVisibleAmount())/(2)));
      System.out.println(scroller.getVerticalScrollBar().getVisibleAmount());
      System.out.println(Movable.getBounds().getY());
      //scroller.getVerticalScrollBar().scrollRectToVisible(new Rectangle(200,0,568,160));
      //scroller.getVerticalScrollBar().setValue(500);
     }
    }
  );
  //scroller.getVerticalScrollBar().addContainerListener(new ContainerListener() {});

 }
 private void createEventView() {
	  indicator = 0;
	  Color lightPurple = new Color(200, 150, 250);
	  Color lightGreen = new Color(150, 250, 150);
	  Color black = new Color(0, 0, 0);
	  eventPanel.setBackground(lightGreen);
	  eventPanel.setLayout(null);

	  object1 = new JButton("Object Annotation");
	  event1 = new JButton("Event Annotation");
	  object.setBounds(20,10,150,25);
	  event.setBounds(175,10,150,25);
	  object.setVisible(true);
	  event.setVisible(true);
	 
	  eventPanel.add(object1);
	  eventPanel.add(event1);
	 
	  object1.addActionListener(
			    new ActionListener() {
			     @Override
			     public void actionPerformed(ActionEvent e) {
			      eventPanel.setVisible(false);
			      remove(eventPanel);
			      add(panel);
			      scroller = new JScrollPane(panel);
			      add(scroller);
			      scroller.setBounds(20,20,20,20);
			      scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			      scroller.getVerticalScrollBar().setEnabled(true);
			      scroller.getVerticalScrollBar().validate();
			      System.out.println(scroller.getVerticalScrollBar());
			      panel.setVisible(true);
			      System.out.println(getContentPane().getComponentCount());
			     }
			    }
			  );
	  
	  //NAME
	  NameLabel1 = new JLabel("Name:");
	  NameLabel1.setBounds(20,20,40,25);
	  NameLabel1.setVisible(true);
	  Name1 = new JTextField();
	  Name1.setBounds(65,20,80,25);
	  Name1.setVisible(true);
	  Name1.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      setTitle(Name1.getText());
	     }
	    }
	  );        
	  eventPanel.add(NameLabel1);
	  eventPanel.add(Name1);
	    
	  //TYPE
	  TypeLabel1 = new JLabel("Type:");
	  TypeLabel1.setVisible(true);
	  TypeLabel1.setBounds(20,50,40,25);
	  eventPanel.add(TypeLabel1);
	  JComboBox<String> type1 = new JComboBox<String>(possibleTypes1);
	  type1.setBounds(40,80,150,25);
	  type1.setVisible(true);
	  Types1.add(type1);
	  JButton removeE2 = new JButton("remove");
	  removeE2.setBounds(195,80,80,25);;
	  removeE2.setVisible(true);
	  removeE2.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	    	 Types1.get(0).setEnabled(false);
	        TypeRemove1.get(0).setEnabled(false);
	        Types1.get(0).setVisible(false);
	        TypeRemove1.get(0).setVisible(false);
	        eventPanel.remove(Types1.get(0));
	        eventPanel.remove(TypeRemove1.get(0));          
	        eventPanel.revalidate();
	        eventPanel.repaint();
	         Types1.remove(0);
	         TypeRemove1.remove(0);          
	         updateTypes1();
	         n1E = Types1.size();
	         for(int k = 0; k < n1E; k++)
	         {
	          Types1.get(k).setBounds(40,80+30*k,150,25);
	         }
	         if(n1E<2)
	          TypeAdd1.setVisible(true);
	         updateLocation1(); 
	     }
	    }
	  );
	  TypeRemove1.add(removeE2);
	  eventPanel.add(TypeRemove1.get(0));
	  eventPanel.add(Types1.get(0));
	  TypeAdd1 = new JButton("add");
	  TypeAdd1.setBounds(300,50,80,25);
	  TypeAdd1.setVisible(true);
	  eventPanel.add(TypeAdd1);
	     TypeAdd1.addActionListener(
	     new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      n1E = Types.size();
	      JComboBox<String> type1 = new JComboBox<String>(possibleTypes1);
	      type1.setBounds(40,80+30*n1,150,25);
	      type1.setVisible(true);
	      Types1.add(type1);
	      eventPanel.add(Types1.get(Types1.size()-1));
	      JButton remove2E = new JButton("remove");
	      remove2E.setBounds(195,80+30*n1,80,25);
	      remove2E.setVisible(true);
	      TypeRemove1.add(remove2E);
	      eventPanel.add(TypeRemove1.get(TypeRemove1.size()-1));
	      n1E = Types1.size();
	      if (n1E==2)
	       TypeAdd1.setVisible(false);
	      updateTypes1();
	      updateLocation1();
	     }
	    }
	  );
	     updateTypes1();
	     
	     //LOAD
	     Load1 = new JButton("Load from XML");
	     Load1.setBounds(440, 55, 120, 25);
	     Load1.setVisible(true);
	     eventPanel.add(Load1);
	          
	     //Path = new JTextField();
	     //Path.setVisible(true);
	     //panel.add(Path);

	     Load1.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	         JFileChooser fileChooser = new JFileChooser();
	         // For Directory
	         // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	         // For File
	         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	         fileChooser.setAcceptAllFileFilterUsed(false);
	         int rVal = fileChooser.showOpenDialog(null);
	         if (rVal == JFileChooser.APPROVE_OPTION) {
	           Path.setText(fileChooser.getSelectedFile().toString());
	         }
	         path1 = fileChooser.getSelectedFile().toString();
	         readXML1();
	       }
	     });
	     
	     n1E = Types1.size();
	     
	     //HEAD
	     HeadLabel1 = new JLabel("Head:");
	     HeadLabel1.setBounds(20,80+30*n1E,40,25);
	     HeadLabel1.setVisible(true);
	     eventPanel.add(HeadLabel1);
	     String[] possibleHeads1 = {"", "state", "process", "transition assignment", "test"};
	     Head1 = new JComboBox<String>(possibleHeads1);
	     Head1.setBounds(65,80+30*n1E,150,25);
	     Head1.setVisible(true);
	     eventPanel.add(Head1);
	     HeadIndex1 = new JTextField();
	     HeadIndex1.setBounds(220,80+30*n1E,40,25);
	     HeadIndex1.setVisible(true);
	     eventPanel.add(HeadIndex1);
	     Head1.addActionListener(
	    	new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	    	 if(HeadIndex1.getText()==null || HeadIndex1.getText().equals(null) || HeadIndex1.getText().equals(""))
	    		 headString1 = (String) Head1.getSelectedItem();
	    	 else
	    		 headString1 = (String) Head1.getSelectedItem() + "[" + HeadIndex1.getText() + "]";
	     }
	    }
	  );
	  HeadIndex1.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	    	 if(HeadIndex1.getText()==null || HeadIndex1.getText().equals(null) || HeadIndex1.getText().equals(""))
	    		 headString1 = (String) Head1.getSelectedItem();
	    	 else
	    		 headString1 = (String) Head1.getSelectedItem() + "[" + HeadIndex1.getText() + "]";	     }
	    }
	  );
	  
	  //ARGS
	  ArgsLabel = new JLabel("Args:");
	  ArgsLabel.setBounds(20,120+30*n1E,100,25);
	  ArgsLabel.setVisible(true);
	  eventPanel.add(ArgsLabel);
	  ArgsNameLabel = new JLabel("name:");
	  ArgsNameLabel.setBounds(40,160+30*n1E,60,25);
	  ArgsNameLabel.setVisible(true);
	  eventPanel.add(ArgsNameLabel);
	  ArgsIndexLabel = new JLabel("index:");
	  ArgsIndexLabel.setBounds(105,160+30*n1E,60,25);
	  ArgsIndexLabel.setVisible(true);
	  eventPanel.add(ArgsIndexLabel);
	  JTextField argName = new JTextField();
	  JTextField argIndex = new JTextField();
	  argName.setBounds(40,190+30*n1E,60,25);
	  argIndex.setBounds(105,190+30*n1E,60,25);
	  argName.setVisible(true);
	  argIndex.setVisible(true);
	  ArgNames.add(argName);
	  ArgInds.add(argIndex);
	  JButton remove1E = new JButton("remove");
	  
	  remove1E.setBounds(170,190+30*n1E,80,25);
	  remove1E.setVisible(true);
	  remove1E.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      ArgNames.get(0).setEnabled(false);
	      ArgInds.get(0).setEnabled(false);
	      ArgRemove.get(0).setEnabled(false);
	      ArgNames.get(0).setVisible(false);
	      ArgInds.get(0).setVisible(false);
	      ArgRemove.get(0).setVisible(false);
	      eventPanel.remove(ArgNames.get(0));
	      eventPanel.remove(ArgInds.get(0));
	      eventPanel.remove(ArgRemove.get(0));  
	      eventPanel.revalidate();
	      eventPanel.repaint();
	         ArgNames.remove(0);
	         ArgInds.remove(0);
	         ArgRemove.remove(0); 
	         updateArgs();
	         n2E = ArgNames.size();
	         for(int k = 0; k < n2E; k++)
	         {
	        	 ArgNames.get(k).setBounds(40,190+30*n1E+30*n2E,60,25);
	        	 ArgInds.get(k).setBounds(105,190+30*n1E+30*n2E,60,25);
	        	 ArgRemove.get(k).setBounds(170,190+30*n1E+30*n2E,80,25);
	         }
	         updateLocation1(); 
	     }
	    }
	  );
	  ArgRemove.add(remove1E);
	  eventPanel.add(ArgRemove.get(0));
	  eventPanel.add(ArgNames.get(0));
	  eventPanel.add(ArgInds.get(0));
	  eventPanel.validate();
	  ArgAdd = new JButton("add");
	  ArgAdd.setBounds(300,130+30*n1E,80,25);
	  ArgAdd.setVisible(true);
	  eventPanel.add(ArgAdd);
	  ArgAdd.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      n2E = ArgNames.size();
	      JTextField argName = new JTextField();
	      JTextField argInd = new JTextField();
	      argName.setBounds(40,190+30*n1E+30*n2E,60,25);
	      argInd.setBounds(105,190+30*n1E+30*n2E,60,25);
	      argName.setVisible(true);
	      argInd.setVisible(true);
	      ArgNames.add(argName);
	      ArgInds.add(argInd);
	      eventPanel.add(ArgNames.get(ArgNames.size()-1));
	      eventPanel.add(ArgInds.get(ArgInds.size()-1));
	      JButton remove1E = new JButton("remove");
	      remove1E.setBounds(170,190+30*n1E+30*n2E,80,25);
	      remove1E.setVisible(true);
	      ArgRemove.add(remove1E);
	      eventPanel.add(ArgRemove.get(ArgRemove.size()-1));
	      n2E = ArgNames.size();
	      updateArgs();
	      updateLocation1(); 
	     }
	    }
	  );
	     updateArgs();
	  n2E = ArgNames.size();
	 
	//BODY
	  BodyLabel = new JLabel("Body:");
	  BodyLabel.setBounds(20,150+30*n1E+30*n2E,100,25);
	  BodyLabel.setVisible(true);
	  eventPanel.add(BodyLabel);
	  BodyNameLabel = new JLabel("name:");
	  BodyNameLabel.setBounds(40,180+30*n1E+30*n2E,60,25);
	  BodyNameLabel.setVisible(true);
	  eventPanel.add(BodyNameLabel);
	  BodyIndexLabel = new JLabel("index:");
	  BodyIndexLabel.setBounds(105,180+30*n1E+30*n2E,60,25);
	  BodyIndexLabel.setVisible(true);
	  eventPanel.add(BodyIndexLabel);
	  JTextField bodyName = new JTextField();
	  JTextField bodyIndex = new JTextField();
	  bodyName.setBounds(40,220+30*n1E+30*n2E,60,25);
	  bodyIndex.setBounds(105,220+30*n1E+30*n2E,60,25);
	  bodyName.setVisible(true);
	  bodyIndex.setVisible(true);
	  BodyNames.add(bodyName);
	  BodyInds.add(bodyIndex);
	  JButton remove2E = new JButton("remove");
	  
	  remove2E.setBounds(170,220+30*n1E+30*n2E,80,25);
	  remove2E.setVisible(true);
	  remove2E.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      BodyNames.get(0).setEnabled(false);
	      BodyInds.get(0).setEnabled(false);
	      BodyRemove.get(0).setEnabled(false);
	      BodyNames.get(0).setVisible(false);
	      BodyInds.get(0).setVisible(false);
	      BodyRemove.get(0).setVisible(false);
	      eventPanel.remove(BodyNames.get(0));
	      eventPanel.remove(BodyInds.get(0));
	      eventPanel.remove(BodyRemove.get(0));  
	      eventPanel.revalidate();
	      eventPanel.repaint();
	      BodyNames.remove(0);
	         BodyInds.remove(0);
	         BodyRemove.remove(0); 
	         updateBody();
	         n3E = BodyNames.size();
	         for(int k = 0; k < n3E; k++)
	         {
	        	 BodyNames.get(k).setBounds(40,220+30*n1E+30*n2E+30*n3E,60,25);
	        	 BodyInds.get(k).setBounds(105,220+30*n1E+30*n2E+30*n3E,60,25);
	        	 BodyRemove.get(k).setBounds(170,220+30*n1E+30*n2E+30*n3E,80,25);
	         }
	         updateLocation1(); 
	     }
	    }
	  );
	  BodyRemove.add(remove2E);
	  eventPanel.add(BodyRemove.get(0));
	  eventPanel.add(BodyNames.get(0));
	  eventPanel.add(BodyInds.get(0));
	  eventPanel.validate();
	  BodyAdd = new JButton("add");
	  BodyAdd.setBounds(300,160+30*n1E+30*n2E,80,25);
	  BodyAdd.setVisible(true);
	  eventPanel.add(BodyAdd);
	  BodyAdd.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      n3E = BodyNames.size();
	      JTextField bodyName = new JTextField();
	      JTextField bodyInd = new JTextField();
	      bodyName.setBounds(40,220+30*n1E+30*n2E+30*n3E,60,25);
	      bodyInd.setBounds(105,220+30*n1E+30*n2E+30*n3E,60,25);
	      bodyName.setVisible(true);
	      bodyInd.setVisible(true);
	      BodyNames.add(bodyName);
	      BodyInds.add(bodyInd);
	      eventPanel.add(BodyNames.get(BodyNames.size()-1));
	      eventPanel.add(BodyInds.get(BodyInds.size()-1));
	      JButton remove2E = new JButton("remove");
	      remove2E.setBounds(170,220+30*n1E+30*n2E+30*n3E,80,25);
	      remove2E.setVisible(true);
	      BodyRemove.add(remove2E);
	      eventPanel.add(BodyRemove.get(BodyRemove.size()-1));
	      n3E = BodyNames.size();
	      updateBody();
	      updateLocation1(); 
	     }
	    }
	  );
	     updateBody();
	  n3E = BodyNames.size();
	  
	  EmbeddingSpaceLabel = new JLabel("Embedding Space:");
	  EmbeddingSpaceLabel.setBounds(20,270+30*n1E+30*n2E+30*n3E,160,25);
	  EmbeddingSpaceLabel.setVisible(true);
	  eventPanel.add(EmbeddingSpaceLabel);
	  EmbeddingSpace = new JTextField();
	  EmbeddingSpace.setBounds(40,300+30*n1E+30*n2E+30*n3E,80,25);
	   EmbeddingSpace.setVisible(true);
	   eventPanel.add(EmbeddingSpace);
	    
	     //XML
	  Save1 = new JButton("Save to XML");
	  Save1.setBounds(300,55,120,25);
	  Save1.setVisible(true);
	  eventPanel.add(Save1);
	     Save1.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      writeXML1();
	      System.out.println("Saved to XML");
	     }
	    }
	  );

	     /*
	  //JScrollPane JScrollPane = new JScrollPane(panel);
	  //JScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  //JScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  //JScrollPane.setPreferredSize(new Dimension(450, 110));
	  //getContentPane().add(JScrollPane, BorderLayout.CENTER);
	  */
	  updateLocation1();
	  add(eventPanel);
	  getContentPane().add(panel);
	  eventPanel.setVisible(false);
	  panel.setVisible(true);
	  /*scroller = new JScrollPane(panel);
	  scroller.getVerticalScrollBar().setPreferredSize(new Dimension(15,20));
	  scroller.getVerticalScrollBar().setBlockIncrement(20);
	  getContentPane().add(scroller, BorderLayout.CENTER);
	  scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
	  JButton scrollTest = new JButton ("scroll"); 
	  scrollTest.setBounds(400,200,200,30);
	  scrollTest.setVisible(true);
	  panel.add(scrollTest);
	  scrollTest.addActionListener(
	    new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	      System.out.println(scroller.getVerticalScrollBar().getVisibleAmount());
	      System.out.println(scroller.getVerticalScrollBar().getValue());
	      System.out.println(panel.getBounds());
	      //System.out.println(panel.getMaximumSize().getHeight());
	      scroller.getVerticalScrollBar().setVisibleAmount((int)((scroller.getVerticalScrollBar().getVisibleAmount())/(2)));
	      System.out.println(scroller.getVerticalScrollBar().getVisibleAmount());
	      System.out.println(Movable.getBounds().getY());
	      //scroller.getVerticalScrollBar().scrollRectToVisible(new Rectangle(200,0,568,160));
	      //scroller.getVerticalScrollBar().setValue(500);
	     }
	    }
	  );
	  //scroller.getVerticalScrollBar().addContainerListener(new ContainerListener() {});

	  */
	 }
 private void readXML()
 {
  try {
   File fXmlFile = new File(path);
   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
   Document doc = dBuilder.parse(fXmlFile);
   doc.getDocumentElement().normalize();
   NodeList nList = doc.getElementsByTagName("Lex");
   for (int temp = 0; temp < nList.getLength(); temp++) {
    Node nNode = nList.item(temp);     
    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
     Element eElement = (Element) nNode;
     readName = eElement.getElementsByTagName("Pred").item(0).getTextContent();
     String readTypesUnparsed = eElement.getElementsByTagName("Type").item(0).getTextContent();
     int numTypes = countChar(readTypesUnparsed,'*');
     for(int j = 0; j <= numTypes; j++)
     {
      int indJ = readTypesUnparsed.indexOf("*");
      if(indJ >= 0)
      {
       readTypes.add(readTypesUnparsed.substring(0,indJ));
       readTypesUnparsed = readTypesUnparsed.substring(indJ+1, readTypesUnparsed.length());
      }
      else
      {
       readTypes.add(readTypesUnparsed);
      }
     }
    }
   }  
   NodeList nList1 = (NodeList) doc.getElementsByTagName("Type").item(1);
   readHead = "";
   readHeadIndex = "";
   for (int temp = 0; temp < nList1.getLength(); temp++) {
    Node nNode1 = nList1.item(temp); 
    if(nNode1.getNodeType() == Node.ELEMENT_NODE && !(nNode1.getNodeName().equals("Components"))) {
     
     if(nNode1.getNodeName().equals("Head")) {
      String readHeadUnparsed = nNode1.getTextContent();
      readHead = readHeadUnparsed.substring(0, readHeadUnparsed.length()-3);
      readHeadIndex = readHeadUnparsed.substring(readHeadUnparsed.length()-2, readHeadUnparsed.length()-1);
     }
     if(nNode1.getNodeName().equals("Concavity")) {
      String readConcavityUnparsed = nNode1.getTextContent();
      int numConcave = countChar(readConcavityUnparsed,'[');
      for(int i = 0; i < numConcave; i++)
      {
       int ind = readConcavityUnparsed.indexOf("[");
       readConcaveInds.add(readConcavityUnparsed.substring(ind+1,ind+2));
       readConcavityUnparsed = readConcavityUnparsed.substring(ind+2,readConcavityUnparsed.length());
      }
     }
     if(nNode1.getNodeName().equals("RotatSym")) {
      String readRotatSymUnparsed = nNode1.getTextContent();
      if(countChar(readRotatSymUnparsed,'X') > 0)
       RotSymX.setSelected(true);
      else
       RotSymX.setSelected(false);
      if(countChar(readRotatSymUnparsed,'Y') > 0)
       RotSymY.setSelected(true);
      else
       RotSymY.setSelected(false);
      if(countChar(readRotatSymUnparsed,'Z') > 0)
       RotSymZ.setSelected(true);
      else
       RotSymZ.setSelected(false);
      /*
      int numRotatSym = countChar(readRotatSymUnparsed,',');
      for(int i = 0; i <= numRotatSym; i++)
      {
       numRotatSym = countChar(readRotatSymUnparsed,',');
       if(numRotatSym > 0)
       {       
        int ind = readRotatSymUnparsed.indexOf(",");
        readRotatSym.add(readRotatSymUnparsed.substring(0,ind));
        readRotatSymUnparsed = readRotatSymUnparsed.substring(ind+1,readRotatSymUnparsed.length());
       }
       else if(readRotatSymUnparsed.length() > 0)
        readRotatSym.add(readRotatSymUnparsed);
      }*/
     }
     if(nNode1.getNodeName().equals("ReflSym")) {
      String readReflSymUnparsed = nNode1.getTextContent();
      if(countString(readReflSymUnparsed,"XY") > 0 || countString(readReflSymUnparsed,"YX") > 0)
       RotSymX.setSelected(true);
      else
       RotSymX.setSelected(false);
      if(countString(readReflSymUnparsed,"YZ") > 0 || countString(readReflSymUnparsed,"ZY") > 0)
       RotSymY.setSelected(true);
      else
       RotSymY.setSelected(false);
      if(countString(readReflSymUnparsed,"ZX") > 0 || countString(readReflSymUnparsed,"XZ") > 0)
       RotSymZ.setSelected(true);
      else
       RotSymZ.setSelected(false);
     }
     
    }
    else if(nNode1.getNodeType() == Node.ELEMENT_NODE && nNode1.getNodeName().equals("Components")) {
     NodeList compList = (NodeList) nNode1;
     for(int i = 0; i < compList.getLength(); i++)
     {
      Node nnNode1 = compList.item(i);
      if(nnNode1.getNodeType() == Node.ELEMENT_NODE)
      {
       String unparsed = nnNode1.getAttributes().getNamedItem("Value").getNodeValue();
       String name = unparsed.substring(0, unparsed.length()-3);
       String ind = unparsed.substring(unparsed.length()-2, unparsed.length()-1);
       readComponents.add(name);
       readCompInds.add(ind);
      }
     }
    }
    
   }

   
   NodeList nList2 = (NodeList) doc.getElementsByTagName("Habitat").item(0);
   for (int temp = 0; temp < nList2.getLength(); temp++) {
    Node nNode2 = nList2.item(temp); 
    if(nNode2.getNodeType() == Node.ELEMENT_NODE) {
     NodeList intrANDextr = (NodeList) nNode2;
     for(int i = 0; i < intrANDextr.getLength(); i++)
     {
      Node Hab = intrANDextr.item(i);
      if(Hab.getNodeType() == Node.ELEMENT_NODE) {
       String name = Hab.getAttributes().getNamedItem("Name").getNodeValue();
       String value = Hab.getAttributes().getNamedItem("Value").getNodeValue();
       if(Hab.getNodeName().equals("Intr"))
       {     
        readIntrinsicNames.add(name);
        readIntrinsicValues.add(value);
       }
       else if(Hab.getNodeName().equals("Extr"))
       {
        readExtrinsicNames.add(name);
        readExtrinsicValues.add(value);
       }
      }
     }
    }
   }
   

   NodeList nList3 = (NodeList) doc.getElementsByTagName("Afford_Str").item(0);
   for (int temp = 0; temp < nList3.getLength(); temp++) {
    Node nNode3 = nList3.item(temp); 
    if(nNode3.getNodeType() == Node.ELEMENT_NODE) {
     NodeList affordList = (NodeList) nNode3;
     for(int i = 0; i < affordList.getLength(); i++)
     {
      Node afford = affordList.item(i);
      if(afford.getNodeType() == Node.ELEMENT_NODE) {
       readAffords.add(afford.getAttributes().getNamedItem("Formula").getNodeValue());
      }
     }
    }
   }
   
   readScale = "";
   readMovable = "";
   
   NodeList nList4 = (NodeList) doc.getElementsByTagName("Embodiment").item(0);
   for (int temp = 0; temp < nList4.getLength(); temp++) {
    Node nNode4 = nList4.item(temp); 
    if(nNode4.getNodeType() == Node.ELEMENT_NODE) {
     if(nNode4.getNodeName().equals("Scale")) {
      readScale = nNode4.getTextContent();
     }
     else if(nNode4.getNodeName().equals("Movable")) {
      readMovable = nNode4.getTextContent();
     }
    }
   } 
      }
  catch (Exception e) {
      e.printStackTrace();
     }
  reset();
 }

 private void readXML1()
 {
  try {
   File fXmlFile = new File(path1);
   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
   Document doc = dBuilder.parse(fXmlFile);
   doc.getDocumentElement().normalize();
   NodeList nList = doc.getElementsByTagName("Lex");
   for (int temp = 0; temp < nList.getLength(); temp++) {
    Node nNode = nList.item(temp);     
    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
     Element eElement = (Element) nNode;
     readName1 = eElement.getElementsByTagName("Pred").item(0).getTextContent();
     String readTypesUnparsed1 = eElement.getElementsByTagName("Type").item(0).getTextContent();
     int numTypes1 = countChar(readTypesUnparsed1,'*');
     for(int j = 0; j <= numTypes1; j++)
     {
      int indJ = readTypesUnparsed1.indexOf("*");
      if(indJ >= 0)
      {
       readTypes1.add(readTypesUnparsed1.substring(0,indJ));
       readTypesUnparsed1 = readTypesUnparsed1.substring(indJ+1, readTypesUnparsed1.length());
      }
      else
      {
       readTypes1.add(readTypesUnparsed1);
      }
     }
    }
   }  
   NodeList nList1E = (NodeList) doc.getElementsByTagName("Type").item(1);
   readHead1 = "";
   //readHeadIndex1 = "";
   for (int temp = 0; temp < nList1E.getLength(); temp++) {
    Node nNode1E = nList1E.item(temp); 
    if(nNode1E.getNodeType() == Node.ELEMENT_NODE && !(nNode1E.getNodeName().equals("Args")) && !(nNode1E.getNodeName().equals("Body"))) {
     
     if(nNode1E.getNodeName().equals("Head")) {
    	 readHead1 = nNode1E.getTextContent();
      /*String readHeadUnparsed = nNode1.getTextContent();
      readHead = readHeadUnparsed.substring(0, readHeadUnparsed.length()-3);
      readHeadIndex = readHeadUnparsed.substring(readHeadUnparsed.length()-2, readHeadUnparsed.length()-1); */
     }
         
    }
    else if(nNode1E.getNodeType() == Node.ELEMENT_NODE && nNode1E.getNodeName().equals("Args")) {
     NodeList argsList = (NodeList) nNode1E;
     for(int i = 0; i < argsList.getLength(); i++)
     {
      Node nnNode1E = argsList.item(i);
      if(nnNode1E.getNodeType() == Node.ELEMENT_NODE)
      {
       String unparsed = nnNode1E.getAttributes().getNamedItem("Value").getNodeValue();
       //String name = unparsed.substring(0, unparsed.length()-3);
       //String ind = unparsed.substring(unparsed.length()-2, unparsed.length()-1);
       readArgs1.add(unparsed);
       //readCompInds.add(ind);
      }
     }
    }
    else if(nNode1E.getNodeType() == Node.ELEMENT_NODE && nNode1E.getNodeName().equals("Body"))
    {
    	NodeList bodyList = (NodeList) nNode1E;
        for(int i = 0; i < bodyList.getLength(); i++)
        {
         Node nnNode1E = bodyList.item(i);
         if(nnNode1E.getNodeType() == Node.ELEMENT_NODE)
         {
          String unparsed = nnNode1E.getAttributes().getNamedItem("Value").getNodeValue();
          //String name = unparsed.substring(0, unparsed.length()-3);
          //String ind = unparsed.substring(unparsed.length()-2, unparsed.length()-1);
          readBody1.add(unparsed);
          //readCompInds.add(ind);
         }
        }
    }
    
   }

     /*NodeList nList3E = (NodeList) doc.getElementsByTagName("Body").item(0);
   for (int temp = 0; temp < nList3E.getLength(); temp++) {
    Node nNode3E = nList3E.item(temp); 
    if(nNode3E.getNodeType() == Node.ELEMENT_NODE) {
     NodeList bodyList = (NodeList) nNode3E;
     for(int i = 0; i < bodyList.getLength(); i++)
     {
      Node subevent = bodyList.item(i);
      if(subevent.getNodeType() == Node.ELEMENT_NODE) {
       readBody1.add(subevent.getAttributes().getNamedItem("Value").getNodeValue());
      }
     }
    }
   } */
   
      }
  catch (Exception e) {
      e.printStackTrace();
     }
  reset1();
 } 

 private void reset()
 {
  panel.setVisible(false);
  int n1_goal = readTypes.size();
  int n2_goal = readComponents.size();
  int n5_goal = readIntrinsicNames.size();
  int n6_goal = readExtrinsicNames.size();
  int n7_goal = readAffords.size();
  
  Name.setText(readName);
  Head.setSelectedItem(readHead);
  HeadIndex.setText(readHeadIndex);
  Scale.setSelectedItem(readScale);
  if(readMovable.equals("true"))
   Movable.setSelected(true);
  else
   Movable.setSelected(false);
  
  while(n1 > 0)
  {
   TypeRemove.get(TypeRemove.size()-1).doClick();
   n1 = Types.size();
  }
  while(n2 > 0)
  {
   CompRemove.get(CompRemove.size()-1).doClick();
   n2 = CompNames.size();
  }
  while(n5 > 0)
  {
   IntrinsicRemove.get(IntrinsicRemove.size()-1).doClick();
   n5 = IntrinsicNames.size();
  }
  while(n6 > 0)
  {
   ExtrinsicRemove.get(ExtrinsicRemove.size()-1).doClick();
   n6 = ExtrinsicNames.size();
  }
  while(n7 > 0)
  {
   AffordancesRemove.get(AffordancesRemove.size()-1).doClick();
   n7 = Affordances.size();
  }
  
   HeadIndex.getText();
   
  while(n1 < n1_goal)
  {
   TypeAdd.doClick();
   n1 = Types.size();
  }
  while(n2 < n2_goal)
  {
   CompAdd.doClick();
   n2 = CompNames.size();
  }
  while(n5 < n5_goal)
  {
   IntrinsicAdd.doClick();
   n5 = IntrinsicNames.size();
  }
  while(n6 < n6_goal)
  {
   ExtrinsicAdd.doClick();
   n6 = ExtrinsicNames.size();
  }
  while(n7 < n7_goal)
  {
   AffordancesAdd.doClick();
   n7 = Affordances.size();
  }
  for(int i = 0; i < n1; i++)
  {
   Types.get(i).setSelectedItem(readTypes.get(i));
  }
  for(int i = 0; i < n2; i++)
  {
   CompNames.get(i).setText(readComponents.get(i));
   CompNames.get(i).validate();
   CompInds.get(i).setText(readCompInds.get(i));
   CompInds.get(i).validate();
   if(readConcaveInds.contains(readCompInds.get(i)))
    Concavity.get(i).setSelected(true);
   else
    Concavity.get(i).setSelected(false);
  }

  for(int i = 0; i < n5; i++)
  {
   IntrinsicNames.get(i).setText(readIntrinsicNames.get(i));
   IntrinsicValues.get(i).setText(readIntrinsicValues.get(i));
  }
  for(int i = 0; i < n6; i++)
  {
   ExtrinsicNames.get(i).setText(readExtrinsicNames.get(i));
   ExtrinsicValues.get(i).setText(readExtrinsicValues.get(i));
  }
  for(int i = 0; i < n7; i++)
  {
   Affordances.get(i).setText(readAffords.get(i));
  }
  updateLocation();
  panel.setVisible(true);
 }
 private void reset1()
 {
  eventPanel.setVisible(false);
  int n1_goal = readTypes1.size();
  int n2_goal = readArgs1.size();
  int n3_goal = readBody1.size();
  
  Name1.setText(readName1);
  Head1.setSelectedItem(readHead1);
  HeadIndex1.setText("");
   
  while(n1E > 0)
  {
   TypeRemove1.get(TypeRemove1.size()-1).doClick();
   n1E = Types1.size();
  }
  while(n2E > 0)
  {
   ArgRemove.get(ArgRemove.size()-1).doClick();
   n2E = ArgNames.size();
  }
  while(n3E > 0)
  {
   BodyRemove.get(BodyRemove.size()-1).doClick();
   n3E = BodyNames.size();
  }
   
  while(n1E < n1_goal)
  {
   TypeAdd1.doClick();
   n1E = Types1.size();
  }
  while(n2E < n2_goal)
  {
   ArgAdd.doClick();
   n2E = ArgNames.size();
  }
  while(n3E < n3_goal)
  {
   BodyAdd.doClick();
   n3E = BodyNames.size();
  }
  

  for(int i = 0; i < n1E; i++)
  {
   Types1.get(i).setSelectedItem(readTypes1.get(i));
  }
  for(int i = 0; i < n2E; i++)
  {
   ArgNames.get(i).setText(readArgs1.get(i));
   ArgNames.get(i).validate();
   //ArgInds.get(i).setText(readArgInds1.get(i));
   ArgInds.get(i).validate();
  }

  for(int i = 0; i < n3E; i++)
  {
   BodyNames.get(i).setText(readBody1.get(i));
   //BodyInds.get(i).setText(readBodyValues1.get(i));
  }
  
  updateLocation1();
  eventPanel.setVisible(true);
 }

 private void checkNullNodes(Node v)
 {
	 if(v.equals(null))
		 System.out.print("");
	 else if(v.getNodeValue()==null) {
		 v.setNodeValue("");
	 }
	 if(!v.hasChildNodes())
		 System.out.print("");
	 else
	 {
		 for(int i = 0; i < v.getChildNodes().getLength(); i++)
			 checkNullNodes(v.getChildNodes().item(i));
	 }
 }
 private void writeXML()
 {
  try {
	DocumentBuilderFactory documentFactory2 = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder2 = documentFactory2.newDocumentBuilder();
	Document document2 = documentBuilder2.newDocument();

   // root element
   Element root2 = document2.createElement("VoxML");
   document2.appendChild(root2);
   //Entity type element
   Element Entity2 = document2.createElement("Entity");
   Attr EntityType2 = document2.createAttribute("Type");
   EntityType2.setValue("Object");
   Entity2.setAttributeNode(EntityType2);
   root2.appendChild(Entity2);
   // Lex element
   Element Lex2 = document2.createElement("Lex");
   root2.appendChild(Lex2);
   // Type Element
   Element Type2a = document2.createElement("Type");
   root2.appendChild(Type2a);
   // Pred element
   Element Pred2 = document2.createElement("Pred");
   Pred2.appendChild(document2.createTextNode(Name.getText()));
   Lex2.appendChild(Pred2);
   // Type element
   Element Type2b = document2.createElement("Type");
   Type2b.appendChild(document2.createTextNode(typesString));
   Lex2.appendChild(Type2b);
   // Head element
   Element Head2 = document2.createElement("Head");
   Head2.appendChild(document2.createTextNode(headString));
   Type2a.appendChild(Head2);
   // Components element
   Element Components2 = document2.createElement("Components");
   for(int i = 0; i < n2; i++)
   {
    Element ComponentA = document2.createElement("Component");
    Attr Value = document2.createAttribute("Value");
    Value.setValue(componentsStrings.get(i));
    ComponentA.setAttributeNode(Value);
    Components2.appendChild(ComponentA);
   }
   Type2a.appendChild(Components2);
   // More elements
   Element Concavity2 = document2.createElement("Concavity");
   String concavityString = "";
   for(int i = 0; i < n2; i++)
   {
    if(Concavity.get(i).isSelected())
     concavityString = concavityString + "Concave[" + CompInds.get(i).getText() + "],";
   }
   if(concavityString.length()>0)
    concavityString = concavityString.substring(0,concavityString.length()-1);
   Concavity2.appendChild(document2.createTextNode(concavityString));
   Type2a.appendChild(Concavity2);
   Element RotatSym2 = document2.createElement("RotatSym");
   RotatSym2.appendChild(document2.createTextNode(rotSymString));
   Type2a.appendChild(RotatSym2);
   Element ReflSym2 = document2.createElement("ReflSym");   
   ReflSym2.appendChild(document2.createTextNode(reflSymString));
   Type2a.appendChild(ReflSym2);
   Element Habitat2 = document2.createElement("Habitat");
   root2.appendChild(Habitat2);
   Element Intrinsic2 = document2.createElement("Intrinsic");
   Habitat2.appendChild(Intrinsic2);
   Element Extrinsic2 = document2.createElement("Extrinsic");
   Habitat2.appendChild(Extrinsic2);
   for(int i = 0; i < n5; i++)
   {
    Element Intr = document2.createElement("Intr");
    Attr Value = document2.createAttribute("Value");
    Value.setValue(IntrinsicValues.get(i).getText());
    Attr Name = document2.createAttribute("Name");
    Name.setValue(IntrinsicNames.get(i).getText());
    Intr.setAttributeNode(Name);
    Intr.setAttributeNode(Value);
    Intrinsic2.appendChild(Intr);
   }
   for(int i = 0; i < n6; i++)
   {
    Element Extr = document2.createElement("Extr");
    Attr Value = document2.createAttribute("Value");
    Value.setValue(IntrinsicValues.get(i).getText());
    Attr Name = document2.createAttribute("Name");
    Name.setValue(IntrinsicNames.get(i).getText());
    Extr.setAttributeNode(Name);
    Extr.setAttributeNode(Value);
    Extrinsic2.appendChild(Extr);
   }
   Element Afford_Str2 = document2.createElement("Afford_Str");
   root2.appendChild(Afford_Str2);
   Element AffordancesElement2 = document2.createElement("Affordances");
   Afford_Str2.appendChild(AffordancesElement2);
   for(int i = 0; i < n7; i++)
   {
    Element Affordance = document2.createElement("Affordance");
    Attr Formula = document2.createAttribute("Formula");
    Formula.setValue(Affordances.get(i).getText());
    Affordance.setAttributeNode(Formula);
    AffordancesElement2.appendChild(Affordance);
   }
   Element Embodiment2 = document2.createElement("Embodiment");
   root2.appendChild(Embodiment2);
   Element ScaleElement2 = document2.createElement("Scale");
   ScaleElement2.appendChild(document2.createTextNode((String) Scale.getSelectedItem()));
   Embodiment2.appendChild(ScaleElement2);
   Element MovableElement2 = document2.createElement("Movable");
   String MovableString;
   if(Movable.isSelected())
    MovableString = "true";
   else
    MovableString = "false";   
   MovableElement2.appendChild(document2.createTextNode(MovableString));
   Embodiment2.appendChild(MovableElement2);
   
   // create the xml file
   //transform the DOM Object to an XML File
   TransformerFactory transformerFactory2 = TransformerFactory.newInstance();
   Transformer transformer2 = transformerFactory2.newTransformer();
   DOMSource domSource2 = new DOMSource(document2);
   String xmlFilePath2 = "c:\\Users\\ruros\\Desktop\\Rutia\\Brandeis\\CL Project\\XML Files\\" + Name.getText() + ".xml";
   File file2 = new File(xmlFilePath2);
   StreamResult streamResult = new StreamResult(file2);
   transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
   transformer2.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
   transformer2.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
   checkNullNodes(document2);
   transformer2.transform(domSource2, streamResult);
  } catch (ParserConfigurationException pce) {
   pce.printStackTrace();
  } catch (TransformerException tfe) {
   tfe.printStackTrace();
  }
 }
 private void writeXML1()
 {
  try {
   DocumentBuilderFactory documentFactory1 = DocumentBuilderFactory.newInstance();
   DocumentBuilder documentBuilder1 = documentFactory1.newDocumentBuilder();
   Document document1 = documentBuilder1.newDocument();

   // root element
   Element root = document1.createElement("VoxML");
   document1.appendChild(root);
   //Entity type element
   Element Entity = document1.createElement("Entity");
   Attr EntityType = document1.createAttribute("Type");
   EntityType.setValue("Event");
   Entity.setAttributeNode(EntityType);
   root.appendChild(Entity);
   // Lex element
   Element Lex = document1.createElement("Lex");
   root.appendChild(Lex);
   // Type Element
   Element Type1 = document1.createElement("Type");
   root.appendChild(Type1);
   // Pred element
   Element Pred = document1.createElement("Pred");
   Pred.appendChild(document1.createTextNode(Name1.getText()));
   Lex.appendChild(Pred);
   // Type element
   Element Type = document1.createElement("Type");
   Type.appendChild(document1.createTextNode(typesString1));
   Lex.appendChild(Type);
   // Head element
   Element Head = document1.createElement("Head");
   Head.appendChild(document1.createTextNode(headString1));
   Type1.appendChild(Head);
   // Args element
   Element Args = document1.createElement("Args");
   for(int i = 0; i < n2E; i++)
   {
    Element Arg = document1.createElement("Arg");
    Attr Value = document1.createAttribute("Value");
    Value.setValue(argsStrings.get(i));
    Arg.setAttributeNode(Value);
    Args.appendChild(Arg);
   }
   Type1.appendChild(Args);
   // Body element
   Element Body = document1.createElement("Body");
   for(int i = 0; i < n3E; i++)
   {
    Element Subevent = document1.createElement("Subevent");
    Attr Value = document1.createAttribute("Value");
    Value.setValue(bodyStrings.get(i));
    Subevent.setAttributeNode(Value);
    Body.appendChild(Subevent);
   }
   Type1.appendChild(Body);
   System.out.println("BODY STRINGS:" + bodyStrings.toString());
   // create the xml file
   //transform the DOM Object to an XML File
   TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
   Transformer transformer1 = transformerFactory1.newTransformer();
   DOMSource domSource1 = new DOMSource(document1);
   String xmlFilePath = "c:\\Users\\ruros\\Desktop\\Rutia\\Brandeis\\CL Project\\XML Files\\" + Name1.getText() + ".xml";
   File file = new File(xmlFilePath);
   checkNullNodes(document1);
   StreamResult streamResult = new StreamResult(file);
   transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
   transformer1.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
   transformer1.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
   transformer1.transform(domSource1, streamResult);
  } catch (ParserConfigurationException pce) {
   pce.printStackTrace();
  } catch (TransformerException tfe) {
   tfe.printStackTrace();
  }
  
 } 

 private void updateLocation()
 {
  if(!panel.isVisible() && indicator == 0)
  {
   object1.doClick();
   indicator = 1;
  }
  int n0 = 35;
  NameLabel.setBounds(20,n0+20,40,25);
  Name.setBounds(65,n0+20,80,25);
  Save.setBounds(300,n0+20,120,25);
  Load.setBounds(440,n0+20,120,25);
  TypeLabel.setBounds(20,n0+50,40,25);
  TypeAdd.setBounds(300,n0+50,80,25);
  n1 = Types.size();
  for(int k = 0; k < n1; k++)
     {
      Types.get(k).setBounds(40,n0+80+30*k,150,25);
      TypeRemove.get(k).setBounds(195,n0+80+30*k,80,25);
        }
     HeadLabel.setBounds(20,n0+85+30*n1,40,25);
     Head.setBounds(65,n0+85+30*n1,150,25);
     HeadIndex.setBounds(220,n0+85+30*n1,40,25);
  CompLabel.setBounds(20,n0+120+30*n1,100,25);
  CompAdd.setBounds(300,n0+120+30*n1,80,25);
  CompNameLabel.setBounds(40,n0+150+30*n1,60,25);
  CompIndexLabel.setBounds(105,n0+150+30*n1,60,25);
  n2 = CompNames.size();
  for(int k = 0; k < n2; k++)
     {
      CompNames.get(k).setBounds(40,n0+170+30*n1+30*k,60,25);
      CompInds.get(k).setBounds(105,n0+170+30*n1+30*k,60,25);
      CompRemove.get(k).setBounds(170,n0+170+30*n1+30*k,80,25);
      Concavity.get(k).setBounds(255,n0+170+30*n1+30*k,80,25);
     }
  RotSymLabel.setBounds(20,n0+190+30*n1+30*n2,200,25);
  n3 = 1;
  RotSymX.setBounds(40,n0+220+30*n1+30*n2,50,25);
  RotSymY.setBounds(145,n0+220+30*n1+30*n2,50,25);
  RotSymZ.setBounds(250,n0+220+30*n1+30*n2,50,25);
  ReflSymLabel.setBounds(20,n0+250+30*n1+30*n2,200,25);
  n4 = 1;
  ReflSymXY.setBounds(40,n0+280+30*n1+30*n2,50,25);
  ReflSymYZ.setBounds(145,n0+280+30*n1+30*n2,50,25);
  ReflSymXZ.setBounds(250,n0+280+30*n1+30*n2,50,25);
  HabitatsLabel.setBounds(20,n0+260+30*n1+30*n2+30*n3+30*n4,100,25);
  HabitatsNameLabel.setBounds(60,n0+290+30*n1+30*n2+30*n3+30*n4,60,25);
  HabitatsValueLabel.setBounds(125,n0+290+30*n1+30*n2+30*n3+30*n4,60,25);
  IntrinsicLabel.setBounds(20,n0+320+30*n1+30*n2+30*n3+30*n4,60,25);
  IntrinsicAdd.setBounds(300,n0+320+30*n1+30*n2+30*n3+30*n4,80,25);
  ExtrinsicLabel.setBounds(20,n0+350+30*n1+30*n2+30*n3+30*n4+30*n5,60,25); 
  ExtrinsicAdd.setBounds(300,n0+350+30*n1+30*n2+30*n3+30*n4+30*n5,80,25);
  n5 = IntrinsicNames.size();
  n6 = ExtrinsicNames.size();
  for(int k = 0; k < n5; k++)
  {   
	  IntrinsicNames.get(k).setBounds(60,n0+350+30*n1+30*n2+30*n3+30*n4+30*k,60,25);
      IntrinsicValues.get(k).setBounds(125,n0+350+30*n1+30*n2+30*n3+30*n4+30*k,60,25);
      IntrinsicRemove.get(k).setBounds(190,n0+350+30*n1+30*n2+30*n3+30*n4+30*k,80,25);
  }
  for(int k = 0; k < n6; k++)
  {
	  ExtrinsicNames.get(k).setBounds(60,n0+380+30*n1+30*n2+30*n3+30*n4+30*n5+30*k,60,25);
      ExtrinsicValues.get(k).setBounds(125,n0+380+30*n1+30*n2+30*n3+30*n4+30*n5+30*k,60,25);
      ExtrinsicRemove.get(k).setBounds(190,n0+380+30*n1+30*n2+30*n3+30*n4+30*n5+30*k,80,25); 
  }
  
  AffordancesLabel.setBounds(20,n0+380+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6,100,25);
  AffordancesAdd.setBounds(300,n0+380+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6,80,25);
  n7 = Affordances.size();
  for(int k = 0; k < n7; k++)
  {
	  Affordances.get(k).setBounds(40,n0+410+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*k,120,25);
      AffordancesRemove.get(k).setBounds(170,n0+410+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*k,80,25);
  }
  
  ScaleLabel.setBounds(20,n0+410+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*n7,80,25);
  Scale.setBounds(105,n0+410+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*n7,100,25);
  MovableLabel.setBounds(20,n0+440+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*n7,80,25);
  Movable.setBounds(105,n0+440+30*n1+30*n2+30*n3+30*n4+30*n5+30*n6+30*n7,20,25);
  
  if(object.isVisible())
    object.setBounds(20,10,150,25);
  if(event.isVisible())
    event.setBounds(175,10,150,25);  
 } 

 private void updateLocation1()
 {
	  int n0E = 35;
	  NameLabel1.setBounds(20,n0E+20,40,25);
	  Name1.setBounds(65,n0E+20,80,25);
	  //Save.setBounds(300,n0+20,120,25);
	  //Load.setBounds(440,n0+20,120,25);
	  TypeLabel1.setBounds(20,n0E+50,40,25);
	  TypeAdd1.setBounds(300,n0E+50,80,25);
	  n1E = Types1.size();
	  for(int k = 0; k < n1E; k++)
	     {
	      Types1.get(k).setBounds(40,n0E+80+30*k,150,25);
	      TypeRemove1.get(k).setBounds(195,n0E+80+30*k,80,25);
	        }
	    
	  if(object1.isVisible())
	    object1.setBounds(20,10,150,25);
	  if(event1.isVisible())
	    event1.setBounds(175,10,150,25);
	  HeadLabel1.setBounds(20,n0E+85+30*n1E,40,25);
	  Head1.setBounds(65,n0E+85+30*n1E,150,25);
	  HeadIndex1.setBounds(220,n0E+85+30*n1E,40,25);
	  ArgsLabel.setBounds(20,n0E+120+30*n1E,100,25);
	  ArgAdd.setBounds(300,n0E+120+30*n1E,80,25);
	  ArgsNameLabel.setBounds(40,n0E+150+30*n1E,60,25);
	  ArgsIndexLabel.setBounds(105,n0E+150+30*n1E,60,25);
	  n2E = ArgNames.size();
	  for(int k = 0; k < n2E; k++)
	     {
	      ArgNames.get(k).setBounds(40,n0E+170+30*n1E+30*k,60,25);
	      ArgInds.get(k).setBounds(105,n0E+170+30*n1E+30*k,60,25);
	      ArgRemove.get(k).setBounds(170,n0E+170+30*n1E+30*k,80,25);
	     }
	  BodyLabel.setBounds(20,n0E+180+30*n1E+30*n2E,100,25);
	  BodyAdd.setBounds(300,n0E+210+30*n1E+30*n2E,80,25);
	  BodyNameLabel.setBounds(40,n0E+210+30*n1E+30*n2E,60,25);
	  BodyIndexLabel.setBounds(105,n0E+210+30*n1E+30*n2E,60,25);
	  n3E = BodyNames.size();
	  for(int k = 0; k < n3E; k++)
	     {
	      BodyNames.get(k).setBounds(40,n0E+230+30*n1E+30*n2E+30*k,60,25);
	      BodyInds.get(k).setBounds(105,n0E+230+30*n1E+30*n2E+30*k,60,25);
	      BodyRemove.get(k).setBounds(170,n0E+230+30*n1E+30*n2E+30*k,80,25);
	     }
	  EmbeddingSpaceLabel.setBounds(20,270+30*n1E+30*n2E+30*n3E,160,25);
	  EmbeddingSpace.setBounds(40,300+30*n1E+30*n2E+30*n3E,80,25);	  
 }
 
 private void updateTypes(){
  if(n1>=1)
  {
      for(int i = 0; i < n1; i++)
      {
       if(i<n1)
       {
        n = i;
        if(n < Types.size() && Types.size() > 0)
        {
         Types.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(n1 > 0)
          {
           typesString = (String) Types.get(0).getSelectedItem();
           for(int j = 1; j < n1; j++)
           {
            typesString = typesString + "*" + (String) Types.get(j).getSelectedItem();
           }
          }
          else
           typesString = "";
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
           TypeRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(Types.size() > 0 && n < Types.size())
          {
             Types.get(n).setEnabled(false);
             TypeRemove.get(n).setEnabled(false);
             Types.get(n).setVisible(false);
             TypeRemove.get(n).setVisible(false);
           panel.remove(Types.get(n));
              panel.remove(TypeRemove.get(n));          
              panel.revalidate();
              panel.repaint();
              Types.remove(n);
              TypeRemove.remove(n);          
              updateTypes();
              n1 = Types.size();
              for(int k = 0; k < n1; k++)
              {
               Types.get(k).setBounds(40,80+30*k,150,25);
              }
              if(n1<3)
               TypeAdd.setVisible(true);
              updateLocation();
          }
         }
           }
      );
        }
       }
       n1 = Types.size();
       if(i>=n1-1)
        break;
      }
  }
    }
 
 private void updateTypes1()
 {
	 if(n1E>=1)
	  {
	      for(int i = 0; i < n1E; i++)
	      {
	       if(i<n1E)
	       {
	        nE = i;
	        if(nE < Types1.size() && Types1.size() > 0)
	        {
	         Types1.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(n1E > 0)
	          {
	           typesString1 = (String) Types1.get(0).getSelectedItem();
	           for(int j = 1; j < n1E; j++)
	           {
	            typesString1 = typesString1 + "*" + (String) Types1.get(j).getSelectedItem();
	           }
	          }
	          else
	           typesString1 = "";
	          updateLocation1(); 
	          getContentPane().validate();
	         }
	           }
	      );
	           TypeRemove1.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(Types1.size() > 0 && nE < Types1.size())
	          {
	             Types1.get(nE).setEnabled(false);
	             TypeRemove1.get(nE).setEnabled(false);
	             Types1.get(nE).setVisible(false);
	             TypeRemove1.get(nE).setVisible(false);
	             eventPanel.remove(Types1.get(nE));
	             eventPanel.remove(TypeRemove1.get(nE));          
	             eventPanel.revalidate();
	             eventPanel.repaint();
	              Types1.remove(nE);
	              TypeRemove1.remove(nE);          
	              updateTypes1();
	              n1E = Types1.size();
	              for(int k = 0; k < n1E; k++)
	              {
	               Types1.get(k).setBounds(40,80+30*k,150,25);
	              }
	              if(n1E<2)
	               TypeAdd1.setVisible(true);
	              updateLocation1();
	          }
	         }
	           }
	      );
	        }
	       }
	       n1E = Types1.size();
	       if(i>=n1E-1)
	        break;
	      }
	  }
 }

 private void updateRotSym(){
  rotSymString = "";
  if(RotSymX.isSelected())
  {
   if(RotSymY.isSelected())
   {
    if(RotSymZ.isSelected())
     rotSymString = "X,Y,Z";
    else
     rotSymString = "X,Y";
   }
   else if(RotSymZ.isSelected())
    rotSymString = "X,Z";
   else
    rotSymString = "X";
  }
  else if(RotSymY.isSelected())
  {
   if(RotSymZ.isSelected())
    rotSymString = "Y,Z";
   else
    rotSymString = "Y";
  }
  else if(RotSymZ.isSelected())
   rotSymString = "Z";
  else
   rotSymString = "";
  updateLocation();
    }
 
 private void updateReflSym(){
  reflSymString = "";
  if(ReflSymXY.isSelected())
  {
   if(ReflSymYZ.isSelected())
   {
    if(ReflSymXZ.isSelected())
     reflSymString = "XY,YZ,XZ";
    else
     reflSymString = "XY,YZ";
   }
   else if(ReflSymXZ.isSelected())
    reflSymString = "XY,XZ";
   else
    reflSymString = "XY";
  }
  else if(ReflSymYZ.isSelected())
  {
   if(ReflSymXZ.isSelected())
    reflSymString = "YZ,XZ";
   else
    reflSymString = "YZ";
  }
  else if(ReflSymXZ.isSelected())
   reflSymString = "XZ";
  else
   reflSymString = "";
  updateLocation();
 }

 private void updateComponents(){
  componentsStrings = new ArrayList<String>();
  for(int j = 0; j < CompNames.size(); j++)
  {
   String componentString = CompNames.get(j).getText() + "[" + CompInds.get(j).getText() + "]";
   componentsStrings.add(componentString);
  }
  if(n2>=1)
  {
      for(int i = 0; i < n2; i++)
      {
       if(i<n2)
       {
        n = i;
        if(n < CompNames.size() && CompNames.size() > 0)
        {
         CompNames.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateComponents();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         CompInds.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateComponents();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         CompRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(CompNames.size() > 0 && n < CompNames.size())
          {
           CompNames.get(n).setEnabled(false);
           CompInds.get(n).setEnabled(false);
           CompRemove.get(n).setEnabled(false);
           Concavity.get(n).setEnabled(false);

           CompNames.get(n).setVisible(false);
           CompInds.get(n).setVisible(false);
           CompRemove.get(n).setVisible(false);
           Concavity.get(n).setVisible(false);

           panel.remove(CompNames.get(n));
           panel.remove(CompInds.get(n));
              panel.remove(CompRemove.get(n)); 
              panel.remove(Concavity.get(n));          

              panel.revalidate();
              panel.repaint();
              CompNames.remove(n);
              CompInds.remove(n);
              CompRemove.remove(n);  
              Concavity.remove(n);          

              updateComponents();
              n2 = CompNames.size();
              updateLocation();           }
         }
           }
      );
        }
       }
       n2 = CompNames.size();
       if(i>=n2-1)
        break;
      }
  }
    }

 private void updateArgs(){
	  argsStrings = new ArrayList<String>();
	  String argString;
	  for(int j = 0; j < ArgNames.size(); j++)
	  {
		  if(ArgInds.get(j).getText()==null || ArgInds.get(j).getText().equals("") || ArgInds.get(j).getText().equals(null))
			  argString = ArgNames.get(j).getText() + "[" + ArgInds.get(j).getText() + "]";
		  else
			  argString = ArgNames.get(j).getText();
		  argsStrings.add(argString);
	  }
	  if(n2E>=1)
	  {
	      for(int i = 0; i < n2E; i++)
	      {
	       if(i<n2E)
	       {
	        nE = i;
	        if(nE < ArgNames.size() && ArgNames.size() > 0)
	        {
	        	ArgNames.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          updateArgs();
	          updateLocation1(); 
	          getContentPane().validate();
	         }
	           }
	      );
	         ArgInds.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          updateArgs();
	          updateLocation1(); 
	          getContentPane().validate();
	         }
	           }
	      );
	         ArgRemove.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(ArgNames.size() > 0 && nE < ArgNames.size())
	          {
	           ArgNames.get(nE).setEnabled(false);
	           ArgInds.get(nE).setEnabled(false);
	           ArgRemove.get(nE).setEnabled(false);

	           ArgNames.get(nE).setVisible(false);
	           ArgInds.get(nE).setVisible(false);
	           ArgRemove.get(nE).setVisible(false);

	           eventPanel.remove(ArgNames.get(nE));
	           eventPanel.remove(ArgInds.get(nE));
	           eventPanel.remove(ArgRemove.get(nE)); 

	           eventPanel.revalidate();
	           eventPanel.repaint();
	           ArgNames.remove(nE);
	           ArgInds.remove(nE);
	           ArgRemove.remove(nE);  

	              updateArgs();
	              n2E = ArgNames.size();
	              updateLocation1();           }
	         }
	           }
	      );
	        }
	       }
	       n2E = ArgNames.size();
	       if(i>=n2E-1)
	        break;
	      }
	  }
	    }

 private void updateBody(){
	  bodyStrings = new ArrayList<String>();
	  String bodyString;
	  for(int j = 0; j < BodyNames.size(); j++)
	  {
		 if(BodyInds.get(j).getText()==null || BodyInds.get(j).getText().equals("") || BodyInds.get(j).getText().equals(null))
			 bodyString = BodyNames.get(j).getText() + "[" + BodyInds.get(j).getText() + "]";
		 else
			 bodyString = BodyNames.get(j).getText();
	   bodyStrings.add(bodyString);
	  }
	  if(n3E>=1)
	  {
	      for(int i = 0; i < n3E; i++)
	      {
	       if(i<n3E)
	       {
	        nE = i;
	        if(nE < BodyNames.size() && BodyNames.size() > 0)
	        {
	        	BodyNames.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          updateBody();
	          updateLocation1(); 
	          getContentPane().validate();
	         }
	           }
	      );
	        	BodyInds.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          updateBody();
	          updateLocation1(); 
	          getContentPane().validate();
	         }
	           }
	      );
	        	BodyRemove.get(i).addActionListener(
	           new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(BodyNames.size() > 0 && nE < BodyNames.size())
	          {
	        	  BodyNames.get(nE).setEnabled(false);
	        	  BodyInds.get(nE).setEnabled(false);
	        	  BodyRemove.get(nE).setEnabled(false);

	        	  BodyNames.get(nE).setVisible(false);
	        	  BodyInds.get(nE).setVisible(false);
	        	  BodyRemove.get(nE).setVisible(false);

	           eventPanel.remove(BodyNames.get(nE));
	           eventPanel.remove(BodyInds.get(nE));
	           eventPanel.remove(BodyRemove.get(nE)); 

	           eventPanel.revalidate();
	           eventPanel.repaint();
	           BodyNames.remove(nE);
	           BodyInds.remove(nE);
	           BodyRemove.remove(nE);  

	              updateBody();
	              n3E = BodyNames.size();
	              updateLocation1();           }
	         }
	           }
	      );
	        }
	       }
	       n3E = BodyNames.size();
	       if(i>=n3E-1)
	        break;
	      }
	  }
	    } 

 private void updateAffordances(){
  affordanceStrings = new ArrayList<String>();
  for(int j = 0; j < Affordances.size(); j++)
  {
   affordanceStrings.add(Affordances.get(j).getText());
  }
  if(n7>=1)
  {
      for(int i = 0; i < n7; i++)
      {
       if(i<n7)
       {
        n = i;
        if(n < Affordances.size() && Affordances.size() > 0)
        {
         Affordances.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateAffordances();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         AffordancesRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(Affordances.size() > 0 && n < Affordances.size())
          {
           Affordances.get(n).setEnabled(false);
           AffordancesRemove.get(n).setEnabled(false);

           Affordances.get(n).setVisible(false);
           AffordancesRemove.get(n).setVisible(false);

           panel.remove(Affordances.get(n));
              panel.remove(AffordancesRemove.get(n)); 

              panel.revalidate();
              panel.repaint();
              Affordances.remove(n);
              AffordancesRemove.remove(n);  

              updateAffordances();
              n7 = Affordances.size();
              updateLocation();           }
         }
           }
      );
        }
       }
       n7 = Affordances.size();
       if(i>=n7-1)
        break;
      }
  }
    }

 private void updateHabitats(){
  intrinsicNameStrings = new ArrayList<String>();
  intrinsicValueStrings = new ArrayList<String>();
  extrinsicNameStrings = new ArrayList<String>();
  extrinsicValueStrings = new ArrayList<String>();
  
  for(int j = 0; j < intrinsicNameStrings.size(); j++)
  {
   intrinsicNameStrings.add(IntrinsicNames.get(j).getText());
   intrinsicValueStrings.add(IntrinsicValues.get(j).getText());
  }
  if(n5>=1)
  {
      for(int i = 0; i < n5; i++)
      {
       if(i<n5)
       {
        n = i;
        if(n < IntrinsicNames.size() && IntrinsicNames.size() > 0)
        {
         IntrinsicNames.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateHabitats();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         IntrinsicValues.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateHabitats();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         IntrinsicRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(IntrinsicNames.size() > 0 && n < IntrinsicNames.size())
          {
           IntrinsicNames.get(n).setEnabled(false);
           IntrinsicValues.get(n).setEnabled(false);
           IntrinsicRemove.get(n).setEnabled(false);

           IntrinsicNames.get(n).setVisible(false);
           IntrinsicValues.get(n).setVisible(false);
           IntrinsicRemove.get(n).setVisible(false);

           panel.remove(IntrinsicNames.get(n));
           panel.remove(IntrinsicValues.get(n));
              panel.remove(IntrinsicRemove.get(n)); 

              panel.revalidate();
              panel.repaint();
              IntrinsicNames.remove(n);
              IntrinsicValues.remove(n);
              IntrinsicRemove.remove(n);  

              updateHabitats();
              n5 = IntrinsicNames.size();
              updateLocation();           }
         }
           }
      );
        }
       }
       n5 = IntrinsicNames.size();
       if(i>=n5-1)
        break;
      }
  }
  
  for(int j = 0; j < extrinsicNameStrings.size(); j++)
  {
   extrinsicNameStrings.add(ExtrinsicNames.get(j).getText());
   extrinsicValueStrings.add(ExtrinsicValues.get(j).getText());
  }
  if(n6>=1)
  {
      for(int i = 0; i < n6; i++)
      {
       if(i<n6)
       {
        n = i;
        if(n < ExtrinsicNames.size() && ExtrinsicNames.size() > 0)
        {
         ExtrinsicNames.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateHabitats();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         ExtrinsicValues.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          updateHabitats();
          updateLocation(); 
          getContentPane().validate();
         }
           }
      );
         ExtrinsicRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(ExtrinsicNames.size() > 0 && n < ExtrinsicNames.size())
          {
           ExtrinsicNames.get(n).setEnabled(false);
           ExtrinsicValues.get(n).setEnabled(false);
           ExtrinsicRemove.get(n).setEnabled(false);

           ExtrinsicNames.get(n).setVisible(false);
           ExtrinsicValues.get(n).setVisible(false);
           ExtrinsicRemove.get(n).setVisible(false);

           panel.remove(ExtrinsicNames.get(n));
           panel.remove(ExtrinsicValues.get(n));
              panel.remove(ExtrinsicRemove.get(n)); 

              panel.revalidate();
              panel.repaint();
              ExtrinsicNames.remove(n);
              ExtrinsicValues.remove(n);
              ExtrinsicRemove.remove(n);  

              updateHabitats();
              n6 = ExtrinsicNames.size();
              updateLocation();           }
         }
           }
      );
        }
       }
       n6 = ExtrinsicNames.size();
       if(i>=n6-1)
        break;   
      }
  }
  
    }

 public static void main(String[] args) {
  new AnnotationTool1().setVisible(true);
 }
 
 public static int countChar(String str, char c)
 {
     int count = 0;
     for(int i=0; i < str.length(); i++)
     { if(str.charAt(i) == c) {count++;} }
     return count;
 }
 
 private static int countString(String str1, String str2) {
  int count = 0;
  while(str1.indexOf(str2)>-1)
  {
   str1 = str1.substring(str1.indexOf(str2)+1);
   count++;
  }
  return count;
 }

}