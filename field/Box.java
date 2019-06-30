package field;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashSet;

public class Box extends AnnotationComponent {
    private javax.swing.Box box;
    private HashSet<Box> boxSet;
    private String name;
    private AnnotationComponent left;
    private AnnotationComponent right;
    private AnnotationComponent top;
    private AnnotationComponent bottom;

    public Box(HashSet<AnnotationComponent> set, String name, Rectangle bounds, JPanel panel,
               AnnotationComponent top, AnnotationComponent bottom, HashSet<Box> boxSet)
    {
        super(set);
        set.add(this);
        this.boxSet = boxSet;
        boxSet.add(this);
        this.setSet(set);
        this.top = top;
        this.bottom = bottom;
        this.name = name;
        this.bounds = bounds;
        this.setPanel(panel);
        createBox(bounds, name);
    }

    public void updateBoxLocation()
    {
        //super.updateLocation();
        for(Box box1 : boxSet) {
            if (box1.bounds != null) {
                box1.bounds.y = (int) box1.top.getBounds().getY() - 20;
                if (!(box1.bottom instanceof lists.FieldList))
                    box1.bounds.height = (int) box1.bottom.getBounds().getY() + (int) box1.bottom.getBounds().getHeight() + 10 - box1.bounds.y;
                else if (((lists.FieldList) box1.bottom).getList().size() > 0)
                    box1.bounds.height = (int) ((AnnotationComponent) (((lists.FieldList) box1.bottom).getList().getLast())).getBounds().getY() +
                            (int) ((lists.FieldList) box1.bottom).getLabel().getBounds().getHeight() + 10 - box1.bounds.y;
                else
                    box1.bounds.height = (int) ((AnnotationComponent) (((lists.FieldList) box1.bottom).getList().getLast())).getBounds().getY() +
                            (int) ((lists.FieldList) box1.bottom).getLabel().getBounds().getHeight() + 10 - box1.bounds.y;

            }
            if (box1.box != null && box1.bounds != null) {
                box1.box.setBounds(box1.bounds);
                box1.box.revalidate();
                box1.box.repaint();
                box1.box.setVisible(true);
                box1.panel.add(box1.box);
            }
        }
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getBox() != null && bounds!=null)
            getBox().setBounds(bounds);
    }


    public javax.swing.Box createBox(Rectangle bounds, String name)
    {
        javax.swing.Box result = new javax.swing.Box(0);
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.black,Color.white);
        Border titledBorder = new TitledBorder(raisedetched,name,2,0);
        result.setBorder(titledBorder);
        result.setBounds(bounds);
        result.setVisible(true);
        getPanel().add(result);
        box = result;
        updateBoxLocation();
        this.component = result;
        return getBox();
    }

    public Rectangle getBounds() {return bounds;}

    public String getName() {return name;}

    public String toString()
    {
        return "Box with name " + getName();
    }

    public String getKey() {return getName() + "_box"; }

    public javax.swing.Box getBox() {
        return box;
    }
}
