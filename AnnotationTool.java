package com.srk.pkg;

import java.awt.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;

public class AnnotationTool extends JFrame {
	
	private JButton add1, add2, remove2, remove3, save, load, addComponent;
	private ArrayList<JButton> addTypes = new ArrayList<JButton>();
	private ArrayList<JComboBox<String>> typeChoices = new ArrayList<JComboBox<String>>();
	private JTextField chooseName, head;
	private ArrayList<JTextField> chooseComponents = new ArrayList<JTextField>();
	private ArrayList<JLabel> componentNumbers = new ArrayList<JLabel>();
	private ArrayList<JButton> removeComponents = new ArrayList<JButton>();
	private ArrayList<JCheckBox> concavity = new ArrayList<JCheckBox>();
	private JLabel nameLabel,typeLabel,headLabel,componentsLabel;
	private JComboBox<String> chooseType1, chooseType2, chooseType3;
	private String name, typesString, headString;
	private Rectangle prev;
	private JPanel panel, panel1;
	private String[] categories = {"Name:","Type:","Head:","Components:","Concavity:","Rotational Symmetry:","Reflection Symmetry:","Habitats:","Intrinsic:","Extrinsic:","Affordances:","Scale:","Movable:"};	
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private int numComp, numIntrHab, numExtrHab, numAfford;
	/*
	for(int i = 0; i < categories.length; i++)
	
	{
		JLabel label = new JLabel(categories[i]);
		Rectangle prev;
		label.setVisible(true);
		labels.add(label);
	}
	*/
	private ArrayList<String> types = new ArrayList<String>();
	
	public AnnotationTool() {
		createView();
		pack();
		setLocationRelativeTo(null); //starts at the middle of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		//set up frame here
		setSize(new Dimension(300,400)); //set default frame size
		//setResizable(false);
	}

	private void createView() {
		Color lightPurple = new Color(200, 150, 250);
		Color lightGreen = new Color(150, 250, 150);
		Color black = new Color(0, 0, 0);
		panel = new JPanel();
		panel.setBackground(lightPurple);
		panel.setLayout(null);
		
		JScrollPane panel1 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(panel1);
		
		//NAME
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(20,20,40,25);
		nameLabel.setVisible(true);
		chooseName = new JTextField();
		chooseName.setBounds(65,20,80,25);
		chooseName.setVisible(true);
		chooseName.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						name = chooseName.getText();
						updateTitle();
					}
				}
		);        
		panel.add(nameLabel);
		panel.add(chooseName);
				
		//TYPE
		typeLabel = new JLabel("Type:");
		typeLabel.setVisible(true);
		typeLabel.setBounds(20,50,40,25);
		panel.add(typeLabel);
		String[] possibleTypes = {"Physical object","Artifact","Human",""};
		chooseType1 = new JComboBox<String>(possibleTypes);
		chooseType1.setBounds(40,80,150,25);
		chooseType2 = new JComboBox<String>(possibleTypes);
		chooseType2.setBounds(40,110,150,25);
		chooseType3 = new JComboBox<String>(possibleTypes);
		chooseType2.setBounds(40,140,150,25);
		chooseType1.setSelectedItem("");
		chooseType2.setSelectedItem("");
		chooseType3.setSelectedItem("");
	    chooseType1.setVisible(true);
	    chooseType2.setVisible(false);
	    chooseType3.setVisible(false);
	    ArrayList<JComboBox<String>> buttons = new ArrayList<JComboBox<String>>(); 
	    save = new JButton("save to XML");
	    save.setBounds(300,20,120,25);
	    save.setVisible(true);	    add1 = new JButton("add");
	    add1.setBounds(195,80,100,25);
	    add1.setVisible(true);
	    add2 = new JButton("add");
	    add2.setBounds(195,110,100,25);
	    add2.setVisible(false);
	    remove2 = new JButton("remove");
	    remove2.setBounds(300,110,100,25);
	    remove2.setVisible(false);
	    remove3 = new JButton("remove");
	    remove3.setBounds(300,140,100,25);
	    remove3.setVisible(false);
	    
	    //
	    load = new JButton("load from XML");
	    load.setBounds(440, 20, 120, 25);
	    load.setVisible(true);
	    panel.add(load);
	         
	    JTextField Path = new JTextField();
	    Path.setBounds(350, 50, 200, 21);
	    panel.add(Path);
	    Path.setColumns(10);

	    load.addActionListener(new ActionListener() {
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
	      }
	    });

	    numComp = 1;
	    numIntrHab = 1;
	    numExtrHab = 1;
	    numAfford = 1;
	    
	    //
	    panel.add(chooseType1); panel.add(chooseType2); panel.add(chooseType3); panel.add(add1); panel.add(add2); panel.add(remove2); panel.add(remove3); panel.add(save); panel.add(load);
	    chooseType1.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added choice box 1");
						updateTypes();
					}
				}
		);
	    chooseType2.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added choice box 2");
						updateTypes();
					}
				}
		);
	    chooseType3.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added choice box 3");
						updateTypes();
					}
				}
		);
	    add1.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added button add 1");
						addType2();
						updateTypes();
					}
				}
		);
	    add2.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added button add 2");
						addType3();
						updateTypes();
						}
				}
		);
	    remove2.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added button remove 2");
						removeType2();
						updateTypes();
					}
				}
		);
	    remove3.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Added button remove 3");
						removeType3();
						updateTypes();
					}
				}
		);
	    
	    //Head
	    if(chooseType3.isVisible())
	    	prev = chooseType3.getBounds();
	    else if(chooseType2.isVisible())
	    	prev = chooseType2.getBounds();
	    else
	    	prev = chooseType1.getBounds();
	    headLabel = new JLabel("Head:");
	    headLabel.setBounds(place(prev,80,25,false,true,false,false));
	    Rectangle pl = headLabel.getBounds();
	    pl.setBounds(typeLabel.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
	    headLabel.setBounds(pl);
	    headLabel.setVisible(true);
	    panel.add(headLabel);
	    
	    prev = headLabel.getBounds();
	    head = new JTextField();
		head.setBounds(place(prev,chooseName.getWidth(),chooseName.getHeight(),false,true,false,false));
		pl = head.getBounds();
	    pl.setBounds(chooseType1.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
	    head.setBounds(pl);
		head.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateHeads();
					}
				}
		);        
		panel.add(head);
		updateHeads();
		head.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateHeads();
					}
				}
				);		
		head.setVisible(true);
		
		//Components
		prev = head.getBounds();
	    componentsLabel = new JLabel("Components:");
	    componentsLabel.setBounds(place(prev,80,25,false,true,false,false));
	    pl = componentsLabel.getBounds();
	    pl.setBounds(typeLabel.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
	    componentsLabel.setBounds(pl);
	    componentsLabel.setVisible(true);
	    panel.add(componentsLabel);

	    prev = componentsLabel.getBounds();
	    JTextField component1 = new JTextField();
	    component1.setBounds(place(prev,chooseName.getWidth(),chooseName.getHeight(),false,true,false,false));
		pl = component1.getBounds();
	    pl.setBounds(chooseType1.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
	    component1.setBounds(pl);

	    panel.add(component1);
	    component1.setVisible(true);
	    JLabel num = new JLabel("[1]");
	    num.setBounds(place(component1.getBounds(),40,25,false,false,true,false));
	    num.setVisible(true);
		panel.add(num);
		chooseComponents.add(component1);
		addComponent = new JButton("add");
		addComponent.setBounds(place(component1.getBounds(),80,25,false,false,true,false,60));		
		addComponent.setVisible(true);
		panel.add(addComponent);
		JCheckBox concave = new JCheckBox("Concave");
		prev = component1.getBounds();
		concave.setBounds(place(prev,100,25,false,false,true,false,31));
		concavity.add(concave);
		concavity.get(concavity.size()-1).setVisible(true);		
		panel.add(concavity.get(concavity.size()-1));

		addComponent.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JTextField component = new JTextField(" ");
						component.setText("");
						prev = chooseComponents.get(chooseComponents.size()-1).getBounds();
						component.setBounds(place(prev,80,25,false,true,false,false));
						component.setVisible(true);
						chooseComponents.add(component);
						System.out.println(chooseComponents.size());
						int i = chooseComponents.size()-1;
						panel.add(chooseComponents.get(i));
						JButton remove = new JButton("Remove");
						remove.setBounds(place(prev,100,25,false,true,true,false,8));
						remove.setVisible(true);
						JLabel num = new JLabel("["+(i+1)+"]");
						num.setBounds(place(component.getBounds(),40,25,false,false,true,false));
						num.setVisible(true);
						panel.add(num);
						JCheckBox concave = new JCheckBox("Concave");
						prev = remove.getBounds();
						concave.setBounds(place(prev,100,25,false,false,true,false,2));
						concavity.add(concave);
						concavity.get(concavity.size()-1).setVisible(true);		
						panel.add(concavity.get(concavity.size()-1));
						panel.add(num);
						remove.addActionListener(
								new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										int i1 = chooseComponents.size();
										System.out.println(i1);
										if (i < i1)
										{
											chooseComponents.get(i).setVisible(false);
											panel.remove(chooseComponents.get(i));
											chooseComponents.remove(i);
										}
										else
										{
											chooseComponents.get(i1-1).setVisible(false);
											panel.remove(chooseComponents.get(i1-1));
											chooseComponents.remove(i1-1);
										}
										remove.setVisible(false);
										panel.remove(remove);
										panel.remove(num);
										//updateComponents();
									}
									}
								);
						panel.add(remove);
						System.out.println(chooseComponents.size());
						numComp = chooseComponents.size();
						//updateComponents();
					}
					
				}
		);
		
	    
	    //XML
	    save.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Saved to XML");
						writeXML();
					}
				}
		);
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
			
			// set an attribute to staff element
			//Attr attr = document.createAttribute("id");
			//attr.setValue("10");
			//Lex.setAttributeNode(attr);
			//you can also use staff.setAttribute("id", "1") for this
		
			// Pred element
			Element Pred = document.createElement("Pred");
			Pred.appendChild(document.createTextNode(name));
			Lex.appendChild(Pred);
			// Type element
			Element Type = document.createElement("Type");
			Type.appendChild(document.createTextNode(typesString));
			Lex.appendChild(Type);
			// Head element
			Element Head = document.createElement("Head");
			Head.appendChild(document.createTextNode(headString));
			Type1.appendChild(Head);
			// Components element
			Element Components = document.createElement("Components");
			for(int i = 0; i < numComp; i++)
			{
				Element Component = document.createElement("Component");
				Attr Value = document.createAttribute("Value");
				Value.setValue(chooseComponents.get(i).getText() + "[" + (i+1) + "]");
				Component.setAttributeNode(Value);
				Components.appendChild(Component);
			}
			Type1.appendChild(Components);
			// More elements
			Element Concavity = document.createElement("Concavity");
			String concavityString = "";
			for(int i = 0; i < numComp; i++)
			{
				if(concavity.get(i).isSelected())
					concavityString = concavityString + "Concave[" + (i+1) + "],";
			}
			concavityString = concavityString.substring(0,concavityString.length()-1);
			System.out.println(concavityString);
			Concavity.appendChild(document.createTextNode(concavityString));
			Type1.appendChild(Concavity);
			Element RotatSym = document.createElement("RotatSym");
			RotatSym.appendChild(document.createTextNode("Y,Z - EXAMPLE"));
			Type1.appendChild(RotatSym);
			Element ReflSym = document.createElement("ReflSym");			
			ReflSym.appendChild(document.createTextNode("XY, YZ - EXAMPLE"));
			Type1.appendChild(ReflSym);
			Element Habitat = document.createElement("Habitat");
			root.appendChild(Habitat);
			Element Intrinsic = document.createElement("Intrinsic");
			Habitat.appendChild(Intrinsic);
			Element Extrinsic = document.createElement("Extrinsic");
			Habitat.appendChild(Extrinsic);
			for(int i = 0; i < numIntrHab; i++)
			{
				Element Intr = document.createElement("Intr");
				Attr Value = document.createAttribute("Value");
				Value.setValue("value i");
				Attr Name = document.createAttribute("Name");
				Name.setValue("name i");
				Intr.setAttributeNode(Value);
				Intr.setAttributeNode(Name);
				Intrinsic.appendChild(Intr);
			}
			for(int i = 0; i < numExtrHab; i++)
			{
				Element Extr = document.createElement("Extr");
				Attr Value = document.createAttribute("Value");
				Value.setValue("value i");
				Attr Name = document.createAttribute("Name");
				Name.setValue("name i");
				Extr.setAttributeNode(Value);
				Extr.setAttributeNode(Name);
				Extrinsic.appendChild(Extr);
			}
			Element Afford_Str = document.createElement("Afford_Str");
			root.appendChild(Afford_Str);
			Element Affordances = document.createElement("Affordances");
			Afford_Str.appendChild(Affordances);
			for(int i = 0; i < numAfford; i++)
			{
				Element Affordance = document.createElement("Affordance");
				Attr Formula = document.createAttribute("Formula");
				Formula.setValue("EXAMPLE FORMULA");
				Affordance.setAttributeNode(Formula);
				Affordances.appendChild(Affordance);
			}
			Element Embodiment = document.createElement("Embodiment");
			root.appendChild(Embodiment);
			Element Scale = document.createElement("Scale");
			Scale.appendChild(document.createTextNode("EXAMPLE"));
			Embodiment.appendChild(Scale);
			Element Movable = document.createElement("Movable");
			Movable.appendChild(document.createTextNode("EXAMPLE"));
			Embodiment.appendChild(Movable);
			
			
			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			String xmlFilePath = "c:\\Users\\ruros\\Desktop\\Rutia\\Brandeis\\CL Project\\XML Files\\" + name + ".xml";
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
	
	private void addType2()
	{
		chooseType2.setBounds(40,110,150,25);
		chooseType2.setVisible(true);
		add1.setVisible(false);
	    add2.setBounds(195,110,100,25);
	    if(!chooseType3.isVisible())
	    	add2.setVisible(true);
		remove2.setBounds(300,110,100,25);
	    remove2.setVisible(true);
	}
	private void removeType2()
	{
		chooseType2.setVisible(false);
		chooseType2.setSelectedItem("");
		add1.setVisible(true);
		add2.setVisible(false);
	    remove2.setVisible(false);
	}
	private void addType3()
	{
		chooseType3.setBounds(40,140,150,25);
		chooseType3.setVisible(true);
	    remove3.setBounds(300,140,100,25);
	    remove3.setVisible(true);
	    add2.setVisible(false);
	}
	private void removeType3()
	{
		chooseType3.setVisible(false);
		chooseType3.setSelectedItem("");
	    remove3.setVisible(false);
	    if(chooseType2.isVisible())
	    	{
	    		add2.setVisible(true);
	    		remove2.setVisible(true);
	    	}
	}
	private void updateTypes()
	{
		types = new ArrayList<String>();
		if(((String)(chooseType1.getSelectedItem())).equals("Physical object") || ((String)(chooseType1.getSelectedItem())).equals("Artifact")
				|| ((String)(chooseType1.getSelectedItem())).equals("Human"))
		{types.add((String) chooseType1.getSelectedItem());}
		if((((String)(chooseType2.getSelectedItem())).equals("Physical object") || ((String)(chooseType2.getSelectedItem())).equals("Artifact")
				|| ((String)(chooseType2.getSelectedItem())).equals("Human")) && (!((String)(chooseType2.getSelectedItem())).equals((String)(chooseType1.getSelectedItem())))) 
		{types.add((String) chooseType2.getSelectedItem());}
		if((((String)(chooseType3.getSelectedItem())).equals("Physical object") || ((String)(chooseType3.getSelectedItem())).equals("Artifact")
				|| ((String)(chooseType3.getSelectedItem())).equals("Human")) && (!((String)(chooseType3.getSelectedItem())).equals((String)(chooseType2.getSelectedItem()))) 
				&& (!((String)(chooseType3.getSelectedItem())).equals((String)(chooseType1.getSelectedItem()))))
		{types.add((String) chooseType3.getSelectedItem());}

		if(types.size() > 0)
		{
			typesString = types.get(0);
			for(int i = 1; i < types.size(); i++)
			{
				typesString = typesString + "*" + types.get(i);
			}
		}
		else
			typesString = "";
		updateHeadLocation();
	}
	private void updateHeads()
	{
		headString = head.getText();
	}
	private void updateHeadLocation()
	{
		 if(chooseType3.isVisible())
		 	prev = chooseType3.getBounds();
		 else if(chooseType2.isVisible())
		 	prev = chooseType2.getBounds();
		 else
		  	prev = chooseType1.getBounds();
		 headLabel.setBounds(place(prev,80,25,false,true,false,false));
		 Rectangle pl = headLabel.getBounds();
		 pl.setBounds(typeLabel.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
		 headLabel.setBounds(pl);
		 prev = headLabel.getBounds();
		 head.setBounds(place(prev,chooseName.getWidth(),chooseName.getHeight(),false,true,false,false));
		 pl = head.getBounds();
		 pl.setBounds(chooseType1.getX(),(int)pl.getY(),(int)pl.getWidth(),(int)pl.getHeight());
		 head.setBounds(pl);
	}
	private void updateTitle()
	{
		setTitle(name);
	}
	
	/*private void updateComponents()
	{
		JLabel num [] = new JLabel[numComp]; 
		for(int j = 0; j < numComp; j++)
		{
			num[j] = new JLabel("[" + (j+1) + "]");
		    num[j].setBounds(place(chooseComponents.get(j).getBounds(),20,25,false,false,true,false));
		    System.out.println("made label");
			panel.add(num[j]);
		    num[j].setVisible(true);
		    System.out.println(num[j].getBounds());
		    System.out.println(num[j].getText());
		    this.validate();
		}
	}*/
	private static Rectangle place(Rectangle previous,double width,double height,boolean top,boolean bottom,boolean right,boolean left)
	{
		double x = previous.getX();
		double y = previous.getY();
		double width_old = previous.getWidth();
		double height_old = previous.getHeight();
		Rectangle place = new Rectangle();
		if (top)
			y = y - 5 - height;
		if (bottom)
			y = y + height_old + 5;
		if (right)
			x = x + width_old + 5;
		if (left)
			x = x - 5 - width;
		place.setBounds((int)x,(int)y,(int)width,(int)height);
		return place;
	}
	private static Rectangle place(Rectangle previous,double width,double height,boolean top,boolean bottom,boolean right,boolean left,int indent)
	{
		double x = previous.getX();
		double y = previous.getY();
		double width_old = previous.getWidth();
		double height_old = previous.getHeight();
		Rectangle place = new Rectangle();
		if (top)
			y = y - 5 - height;
		if (bottom)
			y = y + height_old + 5;
		if (right)
			x = x + width_old + 5 + indent*5;
		if (left)
			x = x - 5 - width;
		place.setBounds((int)x,(int)y,(int)width,(int)height);
		return place;
	}
	public static void main(String[] args) {
		new AnnotationTool().setVisible(true);
	}
}
