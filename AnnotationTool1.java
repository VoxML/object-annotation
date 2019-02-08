package com.srk.pkg;

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

 private JLabel NameLabel, TypeLabel, HeadLabel, CompLabel, CompNameLabel, CompIndexLabel, RotSymLabel, ReflSymLabel;
 private JLabel HabitatsLabel, HabitatsNameLabel, HabitatsValueLabel, IntrinsicLabel, ExtrinsicLabel, AffordancesLabel;
 private JLabel ScaleLabel, MovableLabel;
 private JButton Save, Load, TypeAdd, CompAdd, RotSymAdd, ReflSymAdd, IntrinsicAdd, ExtrinsicAdd, AffordancesAdd;
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
 private String readName /*V*/, readHead /*V*/, readHeadIndex /*V*/, readScale /*V*/, readMovable /*V*/;
 private ArrayList<String> readTypes /*V*/ = new ArrayList<String>();
 private ArrayList<String> readComponents /*V*/ = new ArrayList<String>();
 private ArrayList<String> readCompInds /*V*/ = new ArrayList<String>();
 private ArrayList<String> readConcaveInds /*V*/ = new ArrayList<String>();
 private ArrayList<String> readRotatSym /*V*/ = new ArrayList<String>();
 private ArrayList<String> readReflSym /*V*/ = new ArrayList<String>();
 private ArrayList<String> readIntrinsicNames /*V*/ = new ArrayList<String>();
 private ArrayList<String> readIntrinsicValues /*V*/ = new ArrayList<String>();
 private ArrayList<String> readExtrinsicNames /*V*/ = new ArrayList<String>();
 private ArrayList<String> readExtrinsicValues /*V*/ = new ArrayList<String>();
 private ArrayList<String> readAffords /*V*/ = new ArrayList<String>(); 

 
 public AnnotationTool1() {
  createView();
  pack();
  setSize(new Dimension(640,770)); //set default frame size
  setLocationRelativeTo(null); //starts at the middle of the screen
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true); 
  //set up frame here
  //setResizable(false);
 }

 private void createView() {
  indicator = 0;
  Color lightPurple = new Color(200, 150, 250);
  Color lightGreen = new Color(150, 250, 150);
  Color black = new Color(0, 0, 0);
  panel = new JPanel();
  panel.setBackground(lightPurple);
  panel.setLayout(null);
  eventPanel = new JPanel();
  eventPanel.setBackground(lightGreen);
  eventPanel.setLayout(null);
  //panel = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  //Scrollbar ranger = new Scrollbar(Scrollbar.VERTICAL, 0, 60, 0, 300);
  //ranger.setVisible(true);
  //panel.add(ranger);

  object = new JButton("Object Annotation");
  event = new JButton("Event Annotation");
  object.setBounds(20,10,150,25);
  event.setBounds(175,10,150,25);
  object.setVisible(true);
  event.setVisible(true);
  
  event.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      object1.setBounds(20,10,150,25);
      event1.setBounds(175,10,150,25);
      panel.setVisible(false);
      //add(eventPanel);
      eventPanel.setVisible(true);
      object1.setVisible(true);
      event1.setVisible(true);
      System.out.println("object button bounds: " + object1.getBounds());
      System.out.println("event button bounds: " + event1.getBounds());
      eventPanel.add(object1);
      eventPanel.add(event1);
      getContentPane().add(eventPanel);
     }
    }
  );
  panel.add(object);
  panel.add(event);
  
  object1 = new JButton("Object Annotation");
  event1 = new JButton("Event Annotation");
  object1.setBounds(20,10,150,25);
  event1.setBounds(175,10,150,25);
  //object1.setVisible(true);
  //event1.setVisible(true);
  object1.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      add(panel);
      getContentPane().add(panel);
      panel.setVisible(true);
      eventPanel.setVisible(false);
      object.setVisible(true);
      event.setVisible(true);
      panel.add(object);
      panel.add(event);
     }
    }
  );
  eventPanel.add(object1);
  eventPanel.add(event1);
  
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
     Load = new JButton("load from XML");
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
      System.out.println(headString);
     }
    }
  );
  HeadIndex.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      headString = (String) Head.getSelectedItem() + "[" + HeadIndex.getText() + "]";
      System.out.println(headString);
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
  /*
  String[] possibleRotSym = {"","X","Y","Z"};
  JComboBox<String> rotSym = new JComboBox<String>(possibleRotSym);
  rotSym.setBounds(40,220+30*n1+30*n2,150,25);
  rotSym.setVisible(true);
  RotSym.add(rotSym);
  JButton remove3 = new JButton("remove");
  remove3.setBounds(195,220+30*n1+30*n2,80,25);;
  remove3.setVisible(true);
  remove3.addActionListener(
    new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
      if(RotSym.size() > 0)
       {
       RotSym.get(0).setEnabled(false);
       RotSymRemove.get(0).setEnabled(false);
         RotSym.get(0).setVisible(false);
         RotSymRemove.get(0).setVisible(false);
         panel.remove(RotSym.get(0));
         panel.remove(RotSymRemove.get(0));          
         panel.revalidate();
          panel.repaint();
          RotSym.remove(0);
          RotSymRemove.remove(0);          
          updateRotSym();
          n3 = RotSym.size();
          for(int k = 0; k < n3; k++)
          {
           RotSym.get(k).setBounds(40,220+30*n1+30*n2,150,25);
          }
          if(n3<3)
           RotSymAdd.setVisible(true);
      }
         updateLocation();
        }
    }
  );
  RotSymRemove.add(remove3);
  panel.add(RotSymRemove.get(0));
  panel.add(RotSym.get(0));
  RotSymAdd = new JButton("add");
  RotSymAdd.setBounds(300,190+30*n1+30*n2,80,25);
  RotSymAdd.setVisible(true);
  panel.add(RotSymAdd);
  RotSymAdd.addActionListener(
   new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     n1 = Types.size();
     n2 = CompNames.size();
     n3 = RotSym.size();
     System.out.println(n3);
     JComboBox<String> rotSym = new JComboBox<String>(possibleRotSym);
     rotSym.setBounds(40,220+30*n1+30*n2+30*n3,150,25);
     rotSym.setVisible(true);
     RotSym.add(rotSym);
     panel.add(RotSym.get(RotSym.size()-1));
     JButton remove3 = new JButton("remove");
     remove3.setBounds(195,220+30*n1+30*n2+30*n3,80,25);
     remove3.setVisible(true);
     RotSymRemove.add(remove3);
     panel.add(RotSymRemove.get(RotSymRemove.size()-1));
     n3 = RotSym.size();
     if (n3==3)
      RotSymAdd.setVisible(false);
     updateRotSym();
     updateLocation(); 
    }
   }
  ); */
  
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
  
  /*
     ReflSymLabel = new JLabel("Reflection Symmetry:");
     ReflSymLabel.setVisible(true);
     ReflSymLabel.setBounds(20,250+30*n1+30*n2+30*n3,200,25);
     panel.add(ReflSymLabel);
     String[] possibleReflSym = {"","XY","YZ","XZ"};
     JComboBox<String> reflSym = new JComboBox<String>(possibleReflSym);
     reflSym.setBounds(40,280+30*n1+30*n2+30*n3,150,25);
     reflSym.setVisible(true);
     ReflSym.add(reflSym);
     JButton remove4 = new JButton("remove");
     remove4.setBounds(195,280+30*n1+30*n2+30*n3,80,25);;
     remove4.setVisible(true);
     remove4.addActionListener(
       new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         if(ReflSym.size() > 0)
          {
          ReflSym.get(0).setEnabled(false);
          ReflSymRemove.get(0).setEnabled(false);
            ReflSym.get(0).setVisible(false);
            ReflSymRemove.get(0).setVisible(false);
            panel.remove(ReflSym.get(0));
            panel.remove(ReflSymRemove.get(0));          
            panel.revalidate();
             panel.repaint();
             ReflSym.remove(0);
             ReflSymRemove.remove(0);          
             updateTypes();
             n4 = ReflSym.size();
             for(int k = 0; k < n4; k++)
             {
              ReflSym.get(k).setBounds(40,280+30*n1+30*n2+30*n3,150,25);
             }
             if(n4<3)
              ReflSymAdd.setVisible(true);
         }
            updateLocation(); 
        }
       }
     );
     ReflSymRemove.add(remove4);
     panel.add(ReflSymRemove.get(0));
     panel.add(ReflSym.get(0));
     ReflSymAdd = new JButton("add");
     ReflSymAdd.setBounds(300,250+30*n1+30*n2+30*n3,80,25);
     ReflSymAdd.setVisible(true);
     panel.add(ReflSymAdd);
     ReflSymAdd.addActionListener(
      new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
        n1 = Types.size();
        n2 = CompNames.size();
        n4 = ReflSym.size();
        System.out.println(n4);
        JComboBox<String> reflSym = new JComboBox<String>(possibleReflSym);
        reflSym.setBounds(40,250+30*n1+30*n2,150,25);
        reflSym.setVisible(true);
        ReflSym.add(reflSym);
        panel.add(ReflSym.get(ReflSym.size()-1));
        JButton remove4 = new JButton("remove");
        remove4.setBounds(195,250+30*n1+30*n2,80,25);
        remove4.setVisible(true);
        ReflSymRemove.add(remove4);
        panel.add(ReflSymRemove.get(ReflSymRemove.size()-1));
        n4 = ReflSym.size();
        if (n4==3)
         ReflSymAdd.setVisible(false);
        updateReflSym();
        updateLocation();
       }
      }
     ); */
   //updateLocation();
        //updateReflSym();
        
        
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
      System.out.println("Saved to XML");
      writeXML();
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
 
 private void readXML()
 {
  try {
   File fXmlFile = new File(path);
   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
   Document doc = dBuilder.parse(fXmlFile);
   doc.getDocumentElement().normalize();
   //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
   NodeList nList = doc.getElementsByTagName("Lex");
   for (int temp = 0; temp < nList.getLength(); temp++) {
    Node nNode = nList.item(temp);     
    //System.out.println("Current Element: " + nNode.getNodeName());
    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
     Element eElement = (Element) nNode;
     //System.out.println("Pred: " + eElement.getElementsByTagName("Pred").item(0).getTextContent());
     //System.out.println("Type: " + eElement.getElementsByTagName("Type").item(0).getTextContent());
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
     //System.out.println(readTypes);
    }
   }  
   NodeList nList1 = (NodeList) doc.getElementsByTagName("Type").item(1);
   readHead = "";
   readHeadIndex = "";
   for (int temp = 0; temp < nList1.getLength(); temp++) {
    Node nNode1 = nList1.item(temp); 
    //System.out.println("\nCurrent Element: " + nNode1.getNodeName());
    if(nNode1.getNodeType() == Node.ELEMENT_NODE && !(nNode1.getNodeName().equals("Components"))) {
     //System.out.println(nNode1.getNodeName() + ": " + nNode1.getTextContent());
     //System.out.println(nNode1.getNodeType());
     
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
      
      /*String readReflSymUnparsed = nNode1.getTextContent();
      int numReflSym = countChar(readReflSymUnparsed,',');
      for(int i = 0; i <= numReflSym; i++)
      {
       numReflSym = countChar(readReflSymUnparsed,',');
       if(numReflSym > 0)
       {       
        int ind = readReflSymUnparsed.indexOf(",");
        readReflSym.add(readReflSymUnparsed.substring(0,ind));
        readReflSymUnparsed = readReflSymUnparsed.substring(ind+1,readReflSymUnparsed.length());
       }
       else if(readReflSymUnparsed.length() > 0)
        readReflSym.add(readReflSymUnparsed);
      }  */   
     }
     
    }
    else if(nNode1.getNodeType() == Node.ELEMENT_NODE && nNode1.getNodeName().equals("Components")) {
     //System.out.println(nNode1.getNodeName() + ": ");
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
    //System.out.println("\nCurrent Element: " + nNode1.getNodeName());
    if(nNode2.getNodeType() == Node.ELEMENT_NODE) {
     //System.out.println(nNode2.getNodeName() + ": ");
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
    //System.out.println("\nCurrent Element: " + nNode1.getNodeName());
    if(nNode3.getNodeType() == Node.ELEMENT_NODE) {
     //System.out.println(nNode3.getNodeName() + ": ");
     NodeList affordList = (NodeList) nNode3;
     for(int i = 0; i < affordList.getLength(); i++)
     {
      Node afford = affordList.item(i);
      if(afford.getNodeType() == Node.ELEMENT_NODE) {
       //System.out.print(afford.getNodeName() + ": ");
       //System.out.print("Formula: " + afford.getAttributes().getNamedItem("Formula").getNodeValue());
       //System.out.println();
       readAffords.add(afford.getAttributes().getNamedItem("Formula").getNodeValue());
      }
     }
    }
   }
   //System.out.println(readAffords);
   
   readScale = "";
   readMovable = "";
   
   NodeList nList4 = (NodeList) doc.getElementsByTagName("Embodiment").item(0);
   for (int temp = 0; temp < nList4.getLength(); temp++) {
    Node nNode4 = nList4.item(temp); 
    //System.out.println("\nCurrent Element: " + nNode1.getNodeName());
    if(nNode4.getNodeType() == Node.ELEMENT_NODE) {
     //System.out.println(nNode4.getNodeName() + ": " + nNode4.getTextContent());
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

 private void reset()
 {
  panel.setVisible(false);
  int n1_goal = readTypes.size();
  int n2_goal = readComponents.size();
  int n3_goal = readRotatSym.size();
  int n4_goal = readReflSym.size();
  int n5_goal = readIntrinsicNames.size();
  int n6_goal = readExtrinsicNames.size();
  int n7_goal = readAffords.size();
  
  Name.setText(readName);
  Head.setSelectedItem(readHead);
  HeadIndex.setText(readHeadIndex);
  System.out.println(readScale);
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
  /*while(n3 > 0)
  {
   RotSymRemove.get(RotSymRemove.size()-1).doClick();
   n3 = RotSym.size();
  }
  while(n4 > 0)
  {
   ReflSymRemove.get(ReflSymRemove.size()-1).doClick();
   n4 = ReflSym.size();
  }*/
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
  /*while(n3 < n3_goal)
  {
   RotSymAdd.doClick();
   n3 = RotSym.size();
  }
  while(n4 < n4_goal)
  {
   ReflSymAdd.doClick();
   n4 = ReflSym.size();
  }*/
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
  /*for(int i = 0; i < n3; i++)
  {
   RotSym.get(i).setSelectedItem(readRotatSym.get(i));
  }
  for(int i = 0; i < n4; i++)
  {
   ReflSym.get(i).setSelectedItem(readReflSym.get(i));
  }*/
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
 
 private void writeXML()
 {
  try {
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
   DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
   Document document = documentBuilder.newDocument();

   // root element
   Element root = document.createElement("VoxML");
   document.appendChild(root);
   //Entity type element
   Element Entity = document.createElement("Entity");
   Attr EntityType = document.createAttribute("Type");
   EntityType.setValue("Object");
   Entity.setAttributeNode(EntityType);
   root.appendChild(Entity);
   // Lex element
   Element Lex = document.createElement("Lex");
   root.appendChild(Lex);
   // Type Element
   Element Type1 = document.createElement("Type");
   root.appendChild(Type1);
   // Pred element
   Element Pred = document.createElement("Pred");
   Pred.appendChild(document.createTextNode(Name.getText()));
   Lex.appendChild(Pred);
   // Type element
   Element Type = document.createElement("Type");
   Type.appendChild(document.createTextNode(typesString));
   Lex.appendChild(Type);
   // Head element
   System.out.println(headString);
   Element Head = document.createElement("Head");
   Head.appendChild(document.createTextNode(headString));
   Type1.appendChild(Head);
   // Components element
   Element Components = document.createElement("Components");
   System.out.println(componentsStrings);
   for(int i = 0; i < n2; i++)
   {
    Element Component = document.createElement("Component");
    Attr Value = document.createAttribute("Value");
    Value.setValue(componentsStrings.get(i));
    Component.setAttributeNode(Value);
    Components.appendChild(Component);
   }
   Type1.appendChild(Components);
   // More elements
   Element Concavity1 = document.createElement("Concavity");
   String concavityString = "";
   for(int i = 0; i < n2; i++)
   {
    if(Concavity.get(i).isSelected())
     concavityString = concavityString + "Concave[" + CompInds.get(i).getText() + "],";
   }
   if(concavityString.length()>0)
    concavityString = concavityString.substring(0,concavityString.length()-1);
   //System.out.println(concavityString);
   Concavity1.appendChild(document.createTextNode(concavityString));
   Type1.appendChild(Concavity1);
   Element RotatSym = document.createElement("RotatSym");
   RotatSym.appendChild(document.createTextNode(rotSymString));
   Type1.appendChild(RotatSym);
   Element ReflSym = document.createElement("ReflSym");   
   ReflSym.appendChild(document.createTextNode(reflSymString));
   Type1.appendChild(ReflSym);
   Element Habitat = document.createElement("Habitat");
   root.appendChild(Habitat);
   Element Intrinsic = document.createElement("Intrinsic");
   Habitat.appendChild(Intrinsic);
   Element Extrinsic = document.createElement("Extrinsic");
   Habitat.appendChild(Extrinsic);
   for(int i = 0; i < n5; i++)
   {
    Element Intr = document.createElement("Intr");
    Attr Value = document.createAttribute("Value");
    Value.setValue(IntrinsicValues.get(i).getText());
    Attr Name = document.createAttribute("Name");
    Name.setValue(IntrinsicNames.get(i).getText());
    Intr.setAttributeNode(Name);
    Intr.setAttributeNode(Value);
    Intrinsic.appendChild(Intr);
   }
   for(int i = 0; i < n6; i++)
   {
    Element Extr = document.createElement("Extr");
    Attr Value = document.createAttribute("Value");
    Value.setValue(IntrinsicValues.get(i).getText());
    Attr Name = document.createAttribute("Name");
    Name.setValue(IntrinsicNames.get(i).getText());
    Extr.setAttributeNode(Name);
    Extr.setAttributeNode(Value);
    Extrinsic.appendChild(Extr);
   }
   Element Afford_Str = document.createElement("Afford_Str");
   root.appendChild(Afford_Str);
   Element AffordancesElement = document.createElement("Affordances");
   Afford_Str.appendChild(AffordancesElement);
   for(int i = 0; i < n7; i++)
   {
    Element Affordance = document.createElement("Affordance");
    Attr Formula = document.createAttribute("Formula");
    Formula.setValue(Affordances.get(i).getText());
    Affordance.setAttributeNode(Formula);
    AffordancesElement.appendChild(Affordance);
   }
   Element Embodiment = document.createElement("Embodiment");
   root.appendChild(Embodiment);
   Element ScaleElement = document.createElement("Scale");
   ScaleElement.appendChild(document.createTextNode((String) Scale.getSelectedItem()));
   Embodiment.appendChild(ScaleElement);
   Element MovableElement = document.createElement("Movable");
   String MovableString;
   if(Movable.isSelected())
    MovableString = "true";
   else
    MovableString = "false";   
   MovableElement.appendChild(document.createTextNode(MovableString));
   Embodiment.appendChild(MovableElement);
   
   // create the xml file
   //transform the DOM Object to an XML File
   TransformerFactory transformerFactory = TransformerFactory.newInstance();
   Transformer transformer = transformerFactory.newTransformer();
   DOMSource domSource = new DOMSource(document);
   String xmlFilePath = "c:\\Users\\ruros\\Desktop\\Rutia\\Brandeis\\CL Project\\XML Files\\" + Name.getText() + ".xml";
   StreamResult streamResult = new StreamResult(new File(xmlFilePath ));
   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
   transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
   transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
   transformer.transform(domSource, streamResult);
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
  //RotSymAdd.setBounds(300,190+30*n1+30*n2,80,25);
  n3 = 1;
  /*for(int k = 0; k < n3; k++)
     {
      RotSym.get(k).setBounds(40,220+30*n1+30*n2+30*k,150,25);
      RotSymRemove.get(k).setBounds(195,220+30*n1+30*n2+30*k,80,25);  
     }*/
  RotSymX.setBounds(40,n0+220+30*n1+30*n2,50,25);
  RotSymY.setBounds(145,n0+220+30*n1+30*n2,50,25);
  RotSymZ.setBounds(250,n0+220+30*n1+30*n2,50,25);
  ReflSymLabel.setBounds(20,n0+250+30*n1+30*n2,200,25);
  //ReflSymAdd.setBounds(300,220+30*n1+30*n2,80,25);
  n4 = 1;
  /*for(int k = 0; k < n4; k++)
     /{
      ReflSym.get(k).setBounds(40,250+30*n1+30*n2+30*n3+30*k,150,25);
      ReflSymRemove.get(k).setBounds(195,250+30*n1+30*n2+30*n3+30*k,80,25);  
     }*/
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
  if(object1.isVisible())
    object1.setBounds(20,10,150,25);
  if(event1.isVisible())
    event1.setBounds(175,10,150,25);
  
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
             //System.out.println(typesString);
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
  System.out.println("ROT SYM: " + rotSymString);
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
  System.out.println("REFL SYM: " + reflSymString);
  /*if(n4>=1)
  {
      for(int i = 0; i < n4; i++)
      {
       if(i<n4)
       {
        int n = i;
        if(n < ReflSym.size() && ReflSym.size() > 0)
        {
         ReflSym.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(n4 > 0)
          {
           reflSymString = (String) ReflSym.get(0).getSelectedItem();
           for(int j = 1; j < n4; j++)
           {
            reflSymString = reflSymString + "," + (String) ReflSym.get(j).getSelectedItem();
           }
          }
          else
           reflSymString = "";
          updateLocation(); 
          getContentPane().validate();
             System.out.println(reflSymString);
         }
           }
      );
           ReflSymRemove.get(i).addActionListener(
           new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          if(ReflSym.size() > 0 && n < ReflSym.size())
          {
           ReflSym.get(n).setEnabled(false);
             ReflSymRemove.get(n).setEnabled(false);
             ReflSym.get(n).setVisible(false);
             ReflSymRemove.get(n).setVisible(false);
           panel.remove(ReflSym.get(n));
              panel.remove(ReflSymRemove.get(n));          
              panel.revalidate();
              panel.repaint();
              ReflSym.remove(n);
              ReflSymRemove.remove(n);          
              updateReflSym();
              n4 = ReflSym.size();
              if(n4<3)
               ReflSymAdd.setVisible(true);
              updateLocation();
          }
         }
           }
      );
        }
       }
       n4 = ReflSym.size();
       if(i>=n4-1)
        break;
      }
  }*/
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