package buttons;

import field.AnnotationComponent;
import lists.FieldList;
import org.w3c.dom.Document;
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

public class LoadButton extends Button {

    protected HashMap<String,ArrayList<String>> map;
    protected ArrayList<AnnotationComponent> componentSet;
    protected String path;
    private String readName, readHead, readHeadIndex, readRotSym, readReflSym, readScale, readMovable, readEmbeddingSpace;
    private HashMap<String, ArrayList<String>> readMap = initiateReadMap();
    private ArrayList<String> readTypes = new ArrayList<String>();
    private ArrayList<String> readComps = new ArrayList<String>();
    private ArrayList<String> readCompInds = new ArrayList<String>();
    private ArrayList<String> readConcavity = new ArrayList<String>();
    private ArrayList<String> readIntrinsicNames = new ArrayList<String>();
    private ArrayList<String> readIntrinsicValues = new ArrayList<String>();
    private ArrayList<String> readExtrinsicNames = new ArrayList<String>();
    private ArrayList<String> readExtrinsicValues = new ArrayList<String>();
    private ArrayList<String> readAffordances = new ArrayList<String>();
    private ArrayList<String> readArgs = new ArrayList<String>();
    private ArrayList<String> readArgInds = new ArrayList<String>();
    private ArrayList<String> readBody = new ArrayList<String>();
    private ArrayList<String> readBodyInds = new ArrayList<String>();

    public LoadButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel,
                      HashMap<String,ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super("load", prev, next, bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                try {
                    read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        };
        this.map = map;
        this.componentSet = set;
        button = createButton(bounds);
        for(int i = 0; i < this.button.getActionListeners().length; i++)
            this.button.removeActionListener(this.button.getActionListeners()[i]);
        this.button.addActionListener(AL);
    }

    public LoadButton(Rectangle bounds, FieldList list, JPanel panel, HashMap<String,ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super("load", bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                try {
                    read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        };
        for(int i = 0; i < this.button.getActionListeners().length; i++)
            this.button.removeActionListener(this.button.getActionListeners()[i]);
        this.button.addActionListener(AL);
    }

    protected JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                try {
                    read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        };
        button = super.createButton("load", buttonBounds, AL);
        return button;
    }

    public String getPath()
    {
        System.out.println("Choose a file!");
        JFileChooser fileChooser = new JFileChooser();
        // For Directory
        // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // For File
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int rVal = fileChooser.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("PATH = " + fileChooser.getSelectedFile().toString());
        }
        path = fileChooser.getSelectedFile().toString();
        return path;
    }

    public void reset()
    {
        for(AnnotationComponent ac : set)
        {
            if(ac instanceof FieldList && readMap.containsKey(((FieldList) ac).getKey())) {
                while (((FieldList) ac).getSize() > readMap.get(((FieldList) ac).getKey()).size())
                    ((RemoveButton) ((FieldList) ac).remove.get(((FieldList) ac).remove.size() - 1)).button.doClick();
                while (((FieldList) ac).getSize() < readMap.get(((FieldList) ac).getKey()).size()) {
                    ((AddButton) ((FieldList) ac).add).button.doClick();
                }
            }
        }
    }

    public void load() {

        /*
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
        */
    }

    public void read() throws IOException, SAXException, ParserConfigurationException
    {
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList allNodes = doc.getElementsByTagName("VoxML");
            for(String key : map.keySet()) {
                Node curr = searchNode(allNodes, key);
                if(curr != null) {
                    if(key=="Pred")
                    {
                        readName = curr.getTextContent();
                    }
                    else if(key=="Head")
                    {
                        String readHeadUnparsed = curr.getTextContent();
                        readHead = readHeadUnparsed.substring(0, readHeadUnparsed.length() - 3);
                        readHeadIndex = readHeadUnparsed.substring(readHeadUnparsed.length() - 2, readHeadUnparsed.length() - 1);
                    }
                    else if(key=="Type")
                    {
                        String readTypesUnparsed = curr.getTextContent();
                        int numTypes = countChar(readTypesUnparsed, '*');
                        for (int j = 0; j <= numTypes; j++) {
                            int indJ = readTypesUnparsed.indexOf("*");
                            if (indJ >= 0) {
                                readTypes.add(readTypesUnparsed.substring(0, indJ));
                                readTypesUnparsed = readTypesUnparsed.substring(indJ + 1, readTypesUnparsed.length());
                            } else {
                                readTypes.add(readTypesUnparsed);
                            }
                        }
                    }
                    else if(key=="Components")
                    {
                        for (int i = 0; i < ((NodeList)curr).getLength(); i++) {
                            Node nnNode1 = ((NodeList)curr).item(i);
                            if (nnNode1.getNodeType() == Node.ELEMENT_NODE) {
                                String unparsed = nnNode1.getAttributes().getNamedItem("Value").getNodeValue();
                                String name = unparsed.substring(0, unparsed.length() - 3);
                                String ind = unparsed.substring(unparsed.length() - 2, unparsed.length() - 1);
                                readComps.add(name);
                                readCompInds.add(ind);
                            }
                        }
                    }
                    else if(key=="Concavity")
                    {
                        String readConcavityUnparsed = curr.getTextContent();
                        int numConcave = countChar(readConcavityUnparsed, '[');
                        for (int i = 0; i < numConcave; i++) {
                            int ind = readConcavityUnparsed.indexOf("[");
                            readConcavity.add(readConcavityUnparsed.substring(ind + 1, ind + 2));
                            readConcavityUnparsed = readConcavityUnparsed.substring(ind + 2, readConcavityUnparsed.length());
                        }
                    }
                    else if(key=="RotatSym")
                    {
                        readRotSym = curr.getTextContent();
                    }
                    else if(key=="ReflSym")
                    {
                        readReflSym = curr.getTextContent();
                    }
                    else if(key=="Args")
                    {
                        String unparsed = curr.getAttributes().getNamedItem("Value").getNodeValue();
                        readArgs.add(unparsed);
                    }
                    else if(key=="Body")
                    {
                        String unparsed = curr.getAttributes().getNamedItem("Value").getNodeValue();
                        readBody.add(unparsed);
                    }
                    else if(key=="Scale")
                    {
                        readScale = curr.getTextContent();
                    }
                    else if(key=="Intrinsic" || key=="Intr")
                    {
                        for (int i = 0; i < ((NodeList)curr).getLength(); i++) {
                            Node Hab = ((NodeList)curr).item(i);
                            String name = Hab.getAttributes().getNamedItem("Name").getNodeValue();
                            String value = Hab.getAttributes().getNamedItem("Value").getNodeValue();
                            readIntrinsicNames.add(name);
                            readIntrinsicValues.add(value);
                        }
                    }
                    else if(key=="Extrinsic" || key=="Extr")
                    {
                        for (int i = 0; i < ((NodeList)curr).getLength(); i++) {
                            Node Hab = ((NodeList) curr).item(i);
                            String name = Hab.getAttributes().getNamedItem("Name").getNodeValue();
                            String value = Hab.getAttributes().getNamedItem("Value").getNodeValue();
                            readExtrinsicNames.add(name);
                            readExtrinsicNames.add(value);
                        }
                    }
                    else if(key == "Habitat")
                    {
                        /*TODO*/
                    }
                    else if(key=="Affordances")
                    {
                        for (int i = 0; i < ((NodeList)curr).getLength(); i++) {
                            Node afford = ((NodeList)curr).item(i);
                            if (afford.getNodeType() == Node.ELEMENT_NODE) {
                                readAffordances.add(afford.getAttributes().getNamedItem("Formula").getNodeValue());
                            }
                        }
                    }
                    else if(key=="Movable")
                    {
                        readMovable = curr.getTextContent();
                    }
                }
            }
            reset();
            load();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public Node searchNode(NodeList list, String key)
    {
        for(int i = 0; i < list.getLength(); i++)
        {
            if(list.item(i).getNodeName().equals(key) &&
                    ((list.item(i).getNodeType() == Node.ELEMENT_NODE && !list.item(i).getTextContent().equals(""))
                    || list.item(i).hasChildNodes()))
                return list.item(i);
            if(list.item(i).hasChildNodes())
                return searchNode((NodeList)list.item(i),key);
        }
        return null;
    }

    public int countChar(String str, char c)
    {
        int count = 0;
        for(int i=0; i < str.length(); i++)
        { if(str.charAt(i) == c) {count++;} }
        return count;
    }

    public HashMap<String, ArrayList<String>> initiateReadMap() {
        HashMap<String, ArrayList<String>> readMap = new HashMap<String, ArrayList<String>>();
        readMap.put("Type", readTypes);
        readMap.put("Components", readComps); //
        readMap.put("ComponentsInds", readCompInds); //
        readMap.put("ComponentsConcavity", readConcavity); //
        readMap.put("Intrinsic", readIntrinsicNames); //
        readMap.put("IntrinsicInds", readIntrinsicValues); //
        readMap.put("Extrinsic", readExtrinsicNames); //
        readMap.put("ExtrinsicInds", readExtrinsicValues); //
        readMap.put("Affordances", readAffordances); //
        readMap.put("Args", readArgs); //
        readMap.put("ArgsInds", readArgInds); //
        readMap.put("Body", readBody); //
        readMap.put("BodyInds", readBodyInds); //
        return readMap;
    }
}