package buttons;

import field1.AnnotationComponent;
import field1.FieldList;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveButton extends Button {

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

    public SaveButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel,
                      HashMap<String,ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super("save", prev, next, bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                //try {
                save();
                /* { (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } */
            }
        };
        this.map = map;
        this.componentSet = set;
        button = createButton(bounds);
    }

    public SaveButton(Rectangle bounds, FieldList list, JPanel panel, HashMap<String,ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super("load", bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                //try {
                    save();
                /* { (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } */
            }
        };
        this.map = map;
        this.componentSet = set;
        button = createButton(bounds);
    }

    protected JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                //try {
                save();
                /* { (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } */
            }
        };
        button = super.createButton("save", buttonBounds, AL);
        return button;
    }

    public String getPath()
    {
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

    public void save() {
        /*
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
           String xmlFilePath2 = path;
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

        */
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