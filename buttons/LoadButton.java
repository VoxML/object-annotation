package buttons;

import Main.Main;
import field.AnnotationComponent;
import field.AnnotationField;
import field.DropDown;
import field.TextField;
import lists.DropDownList;
import lists.FieldList;
import lists.TextFieldList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LoadButton extends Button {

    private HashMap<String,ArrayList<String>> map;
    private HashSet<AnnotationComponent> componentSet;
    private String path;
    private String entityType;
    private Main Main;
    private JTabbedPane tabs;

    public LoadButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel,
                      HashMap<String,ArrayList<String>> map, HashSet<AnnotationComponent> set,
                      String entityType, Main Main, JTabbedPane tabs)
    {
        super("load", prev, next, bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                reset();
                read();
            }
        };
        this.map = map;
        this.componentSet = set;
        for(int i = 0; i < this.button.getActionListeners().length; i++)
            this.button.removeActionListener(this.button.getActionListeners()[i]);
        this.button.addActionListener(AL);
        this.entityType = entityType;
        this.Main = Main;
        this.tabs = tabs;
    }

    public LoadButton(Rectangle bounds, FieldList list, JPanel panel, HashMap<String,ArrayList<String>> map, ArrayList<AnnotationComponent> set,
                      String entityType, Main Main, JTabbedPane tabs)
    {
        super("load", bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                reset();
                read();
            }
        };
        for(int i = 0; i < this.button.getActionListeners().length; i++)
            this.button.removeActionListener(this.button.getActionListeners()[i]);
        this.button.addActionListener(AL);
        this.entityType = entityType;
        this.Main = Main;
        this.tabs = tabs;
    }

    protected JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                reset();
                read();
            }
        };
        button = super.createButton("load", buttonBounds, AL);
        return button;
    }

    public String getPath()

    {
        Component frame = new JFrame();
        JOptionPane.showMessageDialog(frame , "Please select an XML file to load.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int rVal = fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedFile() != null)
            path = fileChooser.getSelectedFile().toString();
        else
            path = "";
        return path;
    }

    public void reset()
    {
        if(entityType.equals("Object")) {
            this.tabs.remove(0);
            JScrollPane objectScrollPane = (JScrollPane)Main.createObjectView();
            tabs.add(objectScrollPane, "Object",0);
            tabs.setSelectedIndex(0);
            this.componentSet = Main.getSet1();
        }
        else {
            this.tabs.remove(1);
            JScrollPane eventScrollPanel = (JScrollPane)Main.createEventView();
            tabs.add(eventScrollPanel, "Event",1);
            tabs.setSelectedIndex(1);
            this.componentSet = Main.getSet2();
        }
        this.button.setVisible(false);
        this.panel.remove(this.button);
    }

    public void read() {
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            String readName  = "";
            String readHead = "";
            String readHeadIndex = "";
            ArrayList<String> readTypes = new ArrayList<String>();
            NodeList nList = doc.getElementsByTagName("Lex");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    readName = eElement.getElementsByTagName("Pred").item(0).getTextContent();
                    String readTypesUnparsed = eElement.getElementsByTagName("Type").item(0).getTextContent();
                    int numTypes = countChar(readTypesUnparsed,'*') + 1;
                    for(int j = 0; j < numTypes; j++)
                    {
                        int indJ = readTypesUnparsed.indexOf("*");
                        if(indJ >= 0)
                        {
                            readTypes.add(readTypesUnparsed.substring(0,indJ));
                            readTypesUnparsed = readTypesUnparsed.substring(indJ+1);
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
            for (int temp = 0; temp < nList1.getLength(); temp++) {
                Node nNode1 = nList1.item(temp);
                if (nNode1.getNodeName().equals("Head")) {
                    String readHeadUnparsed = nNode1.getTextContent();
                    if(entityType.equals("Object") && readHeadUnparsed.length() > 2 &&
                            readHeadUnparsed.substring(readHeadUnparsed.length() - 3, readHeadUnparsed.length() - 2).equals("[") &&
                            readHeadUnparsed.substring(readHeadUnparsed.length() - 1).equals("]")) {
                        readHead = readHeadUnparsed.substring(0, readHeadUnparsed.length() - 3);
                        readHeadIndex = readHeadUnparsed.substring(readHeadUnparsed.length() - 2, readHeadUnparsed.length() - 1);
                    }
                    else
                    {
                        readHead = readHeadUnparsed;
                    }
                }
            }

            TextField Name = (TextField)searchField("Pred");
            if(Name != null)
            {
                Name.textfield.setText(readName);
                Name.pressEnter();
            }
            DropDown Head = (DropDown)searchField("Head");
            if(Head != null)
            {
                Head.dropdown.setSelectedItem(readHead);
            }
            DropDownList Types = (DropDownList)searchField("Type");
            AddButton TypesAdd = (AddButton)searchButton("Type_add");
            if(Types != null)
            {
                for(int i = 0; i < readTypes.size()-1; i++)
                {
                    TypesAdd.button.doClick();
                }
                for(int i = 0; i < readTypes.size(); i++)
                {
                    Types.getList().get(i).dropdown.setSelectedItem(readTypes.get(i));
                }
            }

            if(entityType.equals("Object"))
            {
                TextField Head_index = (TextField)searchField("Head_index");
                if(Head_index != null)
                {
                    Head_index.textfield.setText(readHeadIndex);
                    Head_index.pressEnter();
                }
                //components
                //rotsym
                //reflsym
                //intrinsic
                //extrinsic
                //affordances
                //scale
                //movable
            }
            else
            {
                //args
                //body
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public AnnotationField searchField(String key)
    {
        for(AnnotationComponent comp : componentSet)
        {
            if(comp instanceof AnnotationField && ((AnnotationField)comp).getKey() != null && ((AnnotationField)comp).getKey().equals(key))
                return (AnnotationField)comp;
        }
        return null;
    }
    public Button searchButton(String key)
    {
        for(AnnotationComponent comp : componentSet)
        {
            if(comp instanceof Button && ((Button)comp).getKey() != null && ((Button)comp).getKey().equals(key))
                return (Button)comp;
        }
        return null;
    }

    public static int countChar(String str, char c)
    {
        int count = 0;
        for(int i=0; i < str.length(); i++)
        { if(str.charAt(i) == c) {count++;} }
        return count;
    }
}