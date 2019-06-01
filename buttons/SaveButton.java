package buttons;

import field.AnnotationComponent;
import lists.FieldList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SaveButton extends Button {

    protected HashMap<String, ArrayList<String>> map;
    protected HashSet<AnnotationComponent> componentSet;
    protected String path;
    private String fileName;
    private String entityType;

    public SaveButton(AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel,
                      HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set, String entityType) {
        super("save", prev, next, bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focus();
                path = getPath();
                save();
            }
        };
        this.button.addActionListener(this.AL);
        this.map = map;
        this.componentSet = set;
        this.name = "";
        this.entityType = entityType;
    }

    public SaveButton(Rectangle bounds, FieldList list, JPanel panel, HashMap<String, ArrayList<String>> map,
                      HashSet<AnnotationComponent> set, String entityType)
            throws ParserConfigurationException {
        super("load", bounds, panel, null, null);
        this.AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focus();
                path = getPath();
                save();
            }
        };
        this.map = map;
        this.componentSet = set;
        button = createButton(bounds);
        this.name = "";
        this.entityType = entityType;
    }

    protected JButton createButton(Rectangle buttonBounds) {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = getPath();
                save();
            }
        };
        button = super.createButton("save", buttonBounds, AL);
        return button;
    }

    public String getPath() {
        Component frame = new JFrame();
        JOptionPane.showMessageDialog(frame , "Please select an folder to save the XML file.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int rVal = fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedFile() != null)
            path = fileChooser.getSelectedFile().toString();
        else
            path = "";
        if(map != null && map.containsKey("Pred") && map.get("Pred").size()>0)
            fileName = map.get("Pred").get(0);
        else
            fileName = "blank";
        return path;
    }

    public void focus() {
        field.TextField Name = (field.TextField)searchComponent("Pred");
        System.out.println(Name);
        if(Name != null) {
            Name.pressEnter();
            Name.getValueStrings();
        }
        field.DropDown Head = (field.DropDown)searchComponent("Head");
        System.out.println(Head);
        if(Head != null)
            Head.getValueStrings();
        lists.DropDownList Type = (lists.DropDownList)searchComponent("Type");
        System.out.println(Type);
        if(Type != null)
            Type.getValueStrings();
    }

    public void save() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("VoxML");
            Attr XMLNS_XSI = document.createAttribute("xmlns:xsi");
            Attr XMLNS_XSD = document.createAttribute("xmlns:xsd");
            XMLNS_XSI.setValue("http://www.w3.org/2001/XMLSchema-instance");
            XMLNS_XSD.setValue("http://www.w3.org/2001/XMLSchema");
            root.setAttributeNode(XMLNS_XSD);
            root.setAttributeNode(XMLNS_XSI);
            document.appendChild(root);
            Element Entity = document.createElement("Entity");
            Attr EntityType = document.createAttribute("Type");
            EntityType.setValue(entityType);
            Entity.setAttributeNode(EntityType);
            root.appendChild(Entity);
            Element Lex = document.createElement("Lex");
            root.appendChild(Lex);
            Element TypeA = document.createElement("Type");
            root.appendChild(TypeA);
            Element Pred = document.createElement("Pred");
            if(map.containsKey("Pred") && map.get("Pred").size()>0)
                Pred.appendChild(document.createTextNode(map.get("Pred").get(0)));
            else
                Pred.appendChild(document.createTextNode(""));
            Lex.appendChild(Pred);
            Element TypeB = document.createElement("Type");
            String typesString = "";
            ArrayList<String> readTypes = new ArrayList<String>();
            if(map.containsKey("Type"))
                readTypes = map.get("Type");
            for(int i = 0; i < readTypes.size()-1; i++)
                typesString += readTypes.get(i) + "*";
            if(readTypes.size() > 0)
                typesString += readTypes.get(readTypes.size()-1);
            TypeB.appendChild(document.createTextNode(typesString));
            Lex.appendChild(TypeB);
            String headString = "";
            Element Head = document.createElement("Head");
            if(map.containsKey("Head") && map.get("Head").size()>0)
                headString += map.get("Head").get(0);
            if(entityType.equals("Object") && map.containsKey("Head_index") && map.get("Head_index").size()>0 && !map.get("Head_index").get(0).equals(""))
                headString += "[" + map.get("Head_index").get(0) + "]";
            Head.appendChild(document.createTextNode(headString));
            TypeA.appendChild(Head);
            Element Components = document.createElement("Components");
            if(entityType.equals("Object") && map.containsKey("Components")) {
                ArrayList<String> readComps = map.get("Components");
                for (int i = 0; i < readComps.size(); i++) {
                    Element currComp = document.createElement("Component");
                    Attr Value = document.createAttribute("Value");
                    if(!map.get("Components_index["+i+"]").get(0).equals(""))
                        Value.setValue(map.get("Components["+i+"]").get(0) + "[" + map.get("Components_index["+i+"]").get(0) + "]");
                    else
                        Value.setValue(map.get("Components["+i+"]").get(0));
                    currComp.setAttributeNode(Value);
                    Components.appendChild(currComp);
                }
            }
            TypeA.appendChild(Components);
            Element Concavity = document.createElement("Concavity");
            String concavityString = "";
            if(entityType.equals("Object") && map.containsKey("Concavity")) {
                for (int i = 0; i < map.get("Concavity").size() - 1; i++) {
                    concavityString += map.get("Concavity").get(i) + "*";
                }
                if (map.get("Concavity").size() > 0)
                    concavityString += map.get("Concavity").get(map.get("Concavity").size() - 1);
                Concavity.appendChild(document.createTextNode(concavityString));
            }
            TypeA.appendChild(Concavity);
            Element RotatSym = document.createElement("RotatSym");
            if(entityType.equals("Object")) {
                String rotSymString = "";
                String[] rotSyms = {"X", "Y", "Z"};
                for (int i = 0; i < rotSyms.length; i++) {
                    if (map.containsKey("RotatSym[" + i + "]") && map.get("RotatSym[" + i + "]").size() > 0 && map.get("RotatSym[" + i + "]").get(0).equals("true"))
                        rotSymString += rotSyms[i] + ",";
                }
                if (rotSymString.length() > 0)
                    rotSymString = rotSymString.substring(0, rotSymString.length() - 1);
                RotatSym.appendChild(document.createTextNode(rotSymString));
            }
            TypeA.appendChild(RotatSym);
            Element ReflSym = document.createElement("ReflSym");
            if(entityType.equals("Object")) {
                String reflSymString = "";
                String[] reflSyms = {"XY", "YZ", "XZ"};
                for (int i = 0; i < reflSyms.length; i++) {
                    if (map.containsKey("ReflSym[" + i + "]") && map.get("ReflSym[" + i + "]").size() > 0 && map.get("ReflSym[" + i + "]").get(0).equals("true"))
                        reflSymString += reflSyms[i] + ",";
                }
                if (reflSymString.length() > 0)
                    reflSymString = reflSymString.substring(0, reflSymString.length() - 1);
                ReflSym.appendChild(document.createTextNode(reflSymString));
            }
            TypeA.appendChild(ReflSym);
            Element Habitat = document.createElement("Habitat");
            root.appendChild(Habitat);
            Element Intrinsic = document.createElement("Intrinsic");
            Habitat.appendChild(Intrinsic);
            Element Extrinsic = document.createElement("Extrinsic");
            Habitat.appendChild(Extrinsic);
            if(entityType.equals("Object") && map.containsKey("Intrinsic")) {
                for (int i = 0; i < map.get("Intrinsic").size(); i++) {
                    Element Intr = document.createElement("Intr");
                    Attr Value = document.createAttribute("Value");
                    Value.setValue(map.get("Intrinsic_index["+i+"]").get(0));
                    Attr Name = document.createAttribute("Name");
                    Name.setValue(map.get("Intrinsic["+i+"]").get(0));
                    Intr.setAttributeNode(Name);
                    Intr.setAttributeNode(Value);
                    Intrinsic.appendChild(Intr);
                }
            }
            if(entityType.equals("Object") && map.containsKey("Extrinsic")) {
                for (int i = 0; i < map.get("Extrinsic").size(); i++) {
                    Element Extr = document.createElement("Extr");
                    Attr Value = document.createAttribute("Value");
                    Value.setValue(map.get("Extrinsic_index["+i+"]").get(0));
                    Attr Name = document.createAttribute("Name");
                    Name.setValue(map.get("Extrinsic["+i+"]").get(0));
                    Extr.setAttributeNode(Name);
                    Extr.setAttributeNode(Value);
                    Extrinsic.appendChild(Extr);
                }
            }
            Element Afford_Str = document.createElement("Afford_Str");
            Element AffordancesElement = document.createElement("Affordances");
            if(entityType.equals("Object") && map.containsKey("Affordances")) {
                for (int i = 0; i < map.get("Affordances").size(); i++) {
                    Element Affordance = document.createElement("Affordance");
                    Attr Formula = document.createAttribute("Formula");
                    Formula.setValue(map.get("Affordances").get(i));
                    Affordance.setAttributeNode(Formula);
                    AffordancesElement.appendChild(Affordance);
                }
            }
            Afford_Str.appendChild(AffordancesElement);
            root.appendChild(Afford_Str);
            Element Embodiment = document.createElement("Embodiment");
            root.appendChild(Embodiment);
            Element ScaleElement = document.createElement("Scale");
            if(entityType.equals("Object") && map.containsKey("Scale")) {
                ScaleElement.appendChild(document.createTextNode(map.get("Scale").get(0)));
            }
            Embodiment.appendChild(ScaleElement);
            Element MovableElement = document.createElement("Movable");
            if(entityType.equals("Object") && map.containsKey("Movable")) {
                MovableElement.appendChild(document.createTextNode(map.get("Movable").get(0)));
            }
            else if(entityType.equals("Program"))
            {
                MovableElement.appendChild(document.createTextNode("false"));
            }
            Embodiment.appendChild(MovableElement);
            Element Args = document.createElement("Args");
            if(entityType.equals("Program") && map.containsKey("Args")) {
                for (int i = 0; i < map.get("Args").size(); i++) {
                    Element Arg = document.createElement("Arg");
                    Attr Value = document.createAttribute("Value");
                    if(map.containsKey("Args_indsx["+i+"]") && !map.get("Args_index["+i+"]").get(0).equals(""))
                        Value.setValue(map.get("Args["+i+"]").get(0) + "[" + map.get("Args_index["+i+"]").get(0) + "]");
                    else
                        Value.setValue(map.get("Args["+i+"]").get(0));
                    Arg.setAttributeNode(Value);
                    Args.appendChild(Arg);
                }
            }
            TypeA.appendChild(Args);
            Element Body = document.createElement("Body");
            if(entityType.equals("Program") && map.containsKey("Body")) {
                for (int i = 0; i < map.get("Body").size(); i++) {
                    Element Subevent = document.createElement("Subevent");
                    Attr Value = document.createAttribute("Value");
                    if(map.containsKey("Body_indsx["+i+"]") && !map.get("Body_index["+i+"]").get(0).equals(""))
                        Value.setValue(map.get("Body["+i+"]").get(0) + "[" + map.get("Body_index["+i+"]").get(0) + "]");
                    else
                        Value.setValue(map.get("Body["+i+"]").get(0));
                    Subevent.setAttributeNode(Value);
                    Body.appendChild(Subevent);
                }
            }
            TypeA.appendChild(Body);
            Element Scale1 = document.createElement("Scale");
            Element Arity = document.createElement("Arity");
            Element Class = document.createElement("Class");
            Element Value = document.createElement("Value");
            Element Constr = document.createElement("Constr");
            TypeA.appendChild(Scale1);
            TypeA.appendChild(Arity);
            TypeA.appendChild(Class);
            TypeA.appendChild(Value);
            TypeA.appendChild(Constr);
            Element Attributes = document.createElement("Attributes");
            Element Attrs = document.createElement("Attrs");
            Attributes.appendChild(Attrs);
            root.appendChild(Attributes);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File(path + "\\" + fileName + ".xml");
            StreamResult streamResult = new StreamResult(file);
            document.setXmlVersion("1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "us-ascii");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public AnnotationComponent searchComponent(String key)
    {
        for(AnnotationComponent comp : componentSet)
        {
            if(comp.getKey() != null && comp.getKey().equals(key))
                return comp;
        }
        return null;
    }
}