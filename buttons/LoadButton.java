package buttons;

import Main.Main;
import field.*;
import field.TextField;
import lists.ComponentsList;
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
import java.io.File;
import java.io.IOException;
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
            for (int i = 0; i < nList1.getLength(); i++) {
                Node nNode1 = nList1.item(i);
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
                    TypesAdd.button.doClick();
                for(int i = 0; i < readTypes.size(); i++)
                    Types.getList().get(i).dropdown.setSelectedItem(readTypes.get(i));
                Types.getValueStrings();
            }
            if(entityType.equals("Object")) {
                TextField Head_index = (TextField) searchField("Head_index");
                if (Head_index != null) {
                    Head_index.textfield.setText(readHeadIndex);
                    Head_index.pressEnter();
                }
                ArrayList<String> readConcaveInds = new ArrayList<String>();
                ArrayList<String> readComponents = new ArrayList<String>();
                ArrayList<String> readCompInds = new ArrayList<String>();
                ComponentsList Components = (ComponentsList) searchField("Components");
                AddButton ComponentsAdd = (AddButton) searchButton("Components_add");

                for (int j = 0; j < nList1.getLength(); j++) {
                    Node nNode1 = nList1.item(j);
                    if (nNode1.getNodeType() == Node.ELEMENT_NODE && !(nNode1.getNodeName().equals("Components"))) {
                        if (nNode1.getNodeName().equals("Concavity")) {
                            String readConcavityUnparsed = nNode1.getTextContent();
                            int numConcave = countChar(readConcavityUnparsed, '[');
                            for (int i = 0; i < numConcave; i++) {
                                int ind = readConcavityUnparsed.indexOf("[");
                                readConcaveInds.add(readConcavityUnparsed.substring(ind + 1, ind + 2));
                                readConcavityUnparsed = readConcavityUnparsed.substring(ind + 2, readConcavityUnparsed.length());
                            }
                            if (Components != null) {
                                while (Components.getSize() < readComponents.size()) {
                                    ComponentsAdd.button.doClick();
                                }
                                for (int i = 0; i < readComponents.size(); i++) {
                                    Components.getList().get(i).textfield.setText(readComponents.get(i));
                                    Components.getList().get(i).pressEnter();
                                    Components.getIndices().get(i).textfield.setText(readCompInds.get(i));
                                    Components.getIndices().get(i).pressEnter();
                                    if (readConcaveInds.contains(readCompInds.get(i))) {
                                        Components.getConcavity().get(i).checkbox.setSelected(true);
                                    }
                                }
                                Components.getValueStrings();
                                Components.getConcavityStrings();
                            }
                        }
                        if (nNode1.getNodeName().equals("RotatSym")) {
                            String readRotatSymUnparsed = nNode1.getTextContent();
                            if (searchField("RotatSym[0]") != null) {
                                if (countChar(readRotatSymUnparsed, 'X') > 0) {
                                    ((CheckBox) searchField("RotatSym[0]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("RotatSym[0]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("RotatSym[0]")).checkbox.doClick();
                                ((CheckBox) searchField("RotatSym[0]")).checkbox.doClick();
                            }
                            if (searchField("RotatSym[1]") != null) {
                                if (countChar(readRotatSymUnparsed, 'Y') > 0) {
                                    ((CheckBox) searchField("RotatSym[1]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("RotatSym[1]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("RotatSym[1]")).checkbox.doClick();
                                ((CheckBox) searchField("RotatSym[1]")).checkbox.doClick();
                            }
                            if (searchField("RotatSym[2]") != null) {
                                if (countChar(readRotatSymUnparsed, 'Z') > 0) {
                                    ((CheckBox) searchField("RotatSym[2]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("RotatSym[2]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("RotatSym[2]")).checkbox.doClick();
                                ((CheckBox) searchField("RotatSym[2]")).checkbox.doClick();
                            }
                        }
                        if (nNode1.getNodeName().equals("ReflSym")) {
                            String readReflSymUnparsed = nNode1.getTextContent();
                            if (searchField("ReflSym[0]") != null) {
                                if (readReflSymUnparsed.indexOf("XY") > -1 || readReflSymUnparsed.indexOf("YX") > -1) {
                                    ((CheckBox) searchField("ReflSym[0]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("ReflSym[0]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("ReflSym[0]")).checkbox.doClick();
                                ((CheckBox) searchField("ReflSym[0]")).checkbox.doClick();
                            }
                            if (searchField("ReflSym[1]") != null) {
                                if (readReflSymUnparsed.indexOf("YZ") > -1 || readReflSymUnparsed.indexOf("ZY") > -1) {
                                    ((CheckBox) searchField("ReflSym[1]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("ReflSym[1]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("ReflSym[1]")).checkbox.doClick();
                                ((CheckBox) searchField("ReflSym[1]")).checkbox.doClick();
                            }
                            if (searchField("ReflSym[2]") != null) {
                                if (readReflSymUnparsed.indexOf("XZ") > -1 || readReflSymUnparsed.indexOf("ZX") > -1) {
                                    ((CheckBox) searchField("ReflSym[2]")).checkbox.setSelected(true);
                                } else {
                                    ((CheckBox) searchField("ReflSym[2]")).checkbox.setSelected(false);
                                }
                                ((CheckBox) searchField("ReflSym[2]")).checkbox.doClick();
                                ((CheckBox) searchField("ReflSym[2]")).checkbox.doClick();
                            }
                        }

                    } else if (nNode1.getNodeType() == Node.ELEMENT_NODE && nNode1.getNodeName().equals("Components")) {
                        NodeList compList = (NodeList) nNode1;
                        for (int i = 0; i < compList.getLength(); i++) {
                            Node nnNode1 = compList.item(i);
                            if (nnNode1.getNodeType() == Node.ELEMENT_NODE) {
                                String unparsed = nnNode1.getAttributes().getNamedItem("Value").getNodeValue();
                                String name = unparsed.substring(0, unparsed.length() - 3);
                                String ind = unparsed.substring(unparsed.length() - 2, unparsed.length() - 1);
                                readComponents.add(name);
                                readCompInds.add(ind);
                            }
                        }
                        if (Components != null) {
                            while (Components.getSize() < readComponents.size()) {
                                ComponentsAdd.button.doClick();
                            }
                            for (int i = 0; i < readComponents.size(); i++) {
                                Components.getList().get(i).textfield.setText(readComponents.get(i));
                                Components.getList().get(i).pressEnter();
                                Components.getIndices().get(i).textfield.setText(readCompInds.get(i));
                                Components.getIndices().get(i).pressEnter();
                                if (readConcaveInds.contains(readCompInds.get(i))) {
                                    Components.getConcavity().get(i).checkbox.setSelected(true);
                                    Components.getConcavity().get(i).checkbox.doClick();
                                    Components.getConcavity().get(i).checkbox.doClick();
                                }
                            }
                            Components.getValueStrings();
                            Components.getConcavityStrings();
                        }
                    }
                }

                ArrayList<String> readIntrinsicNames = new ArrayList<String>();
                ArrayList<String> readIntrinsicValues = new ArrayList<String>();
                ArrayList<String> readExtrinsicNames = new ArrayList<String>();
                ArrayList<String> readExtrinsicValues = new ArrayList<String>();

                NodeList nList2 = (NodeList) doc.getElementsByTagName("Habitat").item(0);
                for (int j = 0; j < nList2.getLength(); j++) {
                    Node nNode2 = nList2.item(j);
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

                TextFieldList Intrinsic = (TextFieldList)searchField("Intrinsic");
                AddButton IntrinsicAdd = (AddButton)searchButton("Intrinsic_add");
                TextFieldList Extrinsic = (TextFieldList)searchField("Extrinsic");
                AddButton ExtrinsicAdd = (AddButton)searchButton("Extrinsic_add");

                if (Intrinsic != null) {
                    while (Intrinsic.getSize() < readIntrinsicNames.size()) {
                        IntrinsicAdd.button.doClick();
                    }
                    for (int i = 0; i < readIntrinsicNames.size(); i++) {
                        Intrinsic.getList().get(i).textfield.setText(readIntrinsicNames.get(i));
                        Intrinsic.getList().get(i).pressEnter();
                        Intrinsic.getIndices().get(i).textfield.setText(readIntrinsicValues.get(i));
                        Intrinsic.getIndices().get(i).pressEnter();
                    }
                    Intrinsic.getValueStrings();
                }
                if (Extrinsic != null) {
                    while (Extrinsic.getSize() < readExtrinsicNames.size()) {
                        ExtrinsicAdd.button.doClick();
                    }
                    for (int i = 0; i < readExtrinsicNames.size(); i++) {
                        Extrinsic.getList().get(i).textfield.setText(readExtrinsicNames.get(i));
                        Extrinsic.getList().get(i).pressEnter();
                        Extrinsic.getIndices().get(i).textfield.setText(readExtrinsicNames.get(i));
                        Extrinsic.getIndices().get(i).pressEnter();
                    }
                    Extrinsic.getValueStrings();
                }

                ArrayList<String> readAffords = new ArrayList<String>();

                NodeList nList3 = (NodeList) doc.getElementsByTagName("Afford_Str").item(0);
                for (int j = 0; j < nList3.getLength(); j++) {
                    Node nNode3 = nList3.item(j);
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

                TextFieldList Affordances = (TextFieldList)searchField("Affordances");
                AddButton AffordancesAdd = (AddButton)searchButton("Affordances_add");

                if (Affordances != null) {
                    while (Affordances.getSize() < readAffords.size()) {
                        AffordancesAdd.button.doClick();
                    }
                    for (int i = 0; i < readAffords.size(); i++) {
                        Affordances.getList().get(i).textfield.setText(readAffords.get(i));
                        Affordances.getList().get(i).pressEnter();
                    }
                    Affordances.getValueStrings();
                }

                String readScale = "";
                String readMovable = "";
                NodeList nList4 = (NodeList) doc.getElementsByTagName("Embodiment").item(0);
                for (int i = 0; i < nList4.getLength(); i++) {
                    Node nNode4 = nList4.item(i);
                    if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                        if (nNode4.getNodeName().equals("Scale")) {
                            readScale = nNode4.getTextContent();
                        } else if (nNode4.getNodeName().equals("Movable")) {
                            readMovable = nNode4.getTextContent();
                        }
                    }
                }
                DropDown Scale = (DropDown)searchField("Scale");
                if(Scale != null)
                    Scale.dropdown.setSelectedItem(readScale);
                CheckBox Movable = (CheckBox)searchField("Movable");
                if(Movable != null)
                {
                    if(readMovable.toLowerCase().equals("true"))
                        Movable.checkbox.setSelected(true);
                    else
                        Movable.checkbox.setSelected(false);
                    Movable.checkbox.doClick();
                    Movable.checkbox.doClick();
                }
            }
            else
            {
                ArrayList<String> readArgs = new ArrayList<String>();
                ArrayList<String> readBody = new ArrayList<String>();
                for (int j = 0; j < nList1.getLength(); j++) {
                    Node nNode1 = nList1.item(j);
                    if (nNode1.getNodeType() == Node.ELEMENT_NODE && nNode1.getNodeName().equals("Args")) {
                        NodeList argsList = (NodeList) nNode1;
                        for (int i = 0; i < argsList.getLength(); i++) {
                            Node nnNode1E = argsList.item(i);
                            if (nnNode1E.getNodeType() == Node.ELEMENT_NODE) {
                                String unparsed = nnNode1E.getAttributes().getNamedItem("Value").getNodeValue();
                                readArgs.add(unparsed);
                            }
                        }
                    } else if (nNode1.getNodeType() == Node.ELEMENT_NODE && nNode1.getNodeName().equals("Body")) {
                        NodeList bodyList = (NodeList) nNode1;
                        for (int i = 0; i < bodyList.getLength(); i++) {
                            Node nnNode1E = bodyList.item(i);
                            if (nnNode1E.getNodeType() == Node.ELEMENT_NODE) {
                                String unparsed = nnNode1E.getAttributes().getNamedItem("Value").getNodeValue();
                                readBody.add(unparsed);
                            }
                        }
                    }
                }
                TextFieldList Args = (TextFieldList)searchField("Args");
                AddButton ArgsAdd = (AddButton)searchButton("Args_add");

                if (Args != null) {
                    while (Args.getSize() < readArgs.size()) {
                        ArgsAdd.button.doClick();
                    }
                    for (int i = 0; i < readArgs.size(); i++) {
                        Args.getList().get(i).textfield.setText(readArgs.get(i));
                        Args.getList().get(i).pressEnter();
                    }
                    Args.getValueStrings();
                }

                TextFieldList Body = (TextFieldList)searchField("Body");
                AddButton BodyAdd = (AddButton)searchButton("Body_add");

                if (Body != null) {
                    while (Body.getSize() < readBody.size()) {
                        BodyAdd.button.doClick();
                    }
                    for (int i = 0; i < readBody.size(); i++) {
                        Body.getList().get(i).textfield.setText(readBody.get(i));
                        Body.getList().get(i).pressEnter();
                    }
                    Body.getValueStrings();
                }


                TextField EmbeddingSpace = (TextField)searchField("embeddingspace");


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