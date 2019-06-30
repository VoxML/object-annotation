package field;

import lists.FieldList;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class AnnotationComponent {
    //includes buttons as well as fields
    private HashSet<Box> boxSet = null;
    protected Rectangle bounds;
    protected AnnotationComponent prev; //vertically previous component
    protected JPanel panel;
    protected HashSet<AnnotationComponent> set;
    protected boolean moved;
    protected JScrollBar verticalBar;
    protected int indicator;
    protected AnnotationComponent extraPrev;
    public JComponent component = null;
    protected boolean boxtop = false;

    public AnnotationComponent()
    {    }

    public AnnotationComponent(HashSet<AnnotationComponent> set)
    {
        this.setSet(set);
        if(set != null) {
            set.add(this);
        }
        setMoved(false);
    }

    public void updateLocation(int gapSize, boolean fixLists, Queue<AnnotationComponent> q)
    {
        if(getSet() != null)
        {
            if(fixLists) {
                for (AnnotationComponent comp : getSet()) {
                    if (comp instanceof FieldList) {
                        if (((FieldList) comp).getList() != null && ((FieldList) comp).getList().size() > 0) {
                            for (AnnotationComponent comp1 : getSet()) {
                                if(comp1.extraPrev != null && comp1.extraPrev.equals(comp)) {
                                    comp1.extraPrev = ((FieldList)comp).getLabel();
                                    comp1.prev = comp;
                                }
                            }
                            comp.setHeight((int) (((AnnotationComponent) ((FieldList) (comp)).getList().getLast()).bounds.getY()));
                        }
                        if (((FieldList) comp).getList() != null && ((FieldList) comp).getList().size() == 0) {
                            for (AnnotationComponent comp1 : getSet()) {
                                if(comp1.prev != null && comp1.prev.equals(comp)) {
                                    comp1.extraPrev = comp;
                                    comp1.prev = ((FieldList)comp).getLabel();
                                }
                            }
                        }
                    }
                }
            }

            if (!this.isMoved()) {
                if ((!(this instanceof FieldList)) && this.getPrev() != null && this.getPrev().bounds != null && !(this instanceof Box)) {
                    this.setHeight((int) this.getPrev().bounds.getY() + this.getPrev().bounds.height + gapSize);
                }
                else if(this instanceof Box) {
                    ((Box) this).updateBoxLocation();
                }

                for (AnnotationComponent comp : getSet()) {
                    if (!comp.equals(this)) {
                        if (comp.getPrev() != null && comp.getPrev().equals(this) && this.bounds != null) {
                            q.add(comp);
                        }
                    }
                }
                this.setMoved(true);
            }
        }
        if(getSet() != null && getPanel() != null) {
            AnnotationComponent last = this;
            int maxHeight = 0;
            for (AnnotationComponent comp : getSet()) {
                if (comp.bounds != null) {
                    if (comp.bounds.y > maxHeight) {
                        maxHeight = comp.bounds.y;
                        last = comp;
                    }
                }
            }
            if (last != null) {
                if (last.bounds != null) {
                    getPanel().setBounds(0, 0, 600, last.bounds.y + last.bounds.height + 20);
                    getPanel().setPreferredSize(new Dimension(600, last.bounds.y + last.bounds.height + 20));
                    getPanel().validate();
                }
            }
        }
    }

    public void updateLocationPrev() {
        this.setIndicator(0);
        Rectangle rect = null;
        if(getPanel() != null)
            rect = getPanel().getVisibleRect();
        Rectangle rect1 = rect;
        Queue<AnnotationComponent> q = new LinkedList<AnnotationComponent>();
        q.add(this);
        while(!q.isEmpty())
        {
            if(q.peek().getIndicator() == 0 && q.peek() instanceof TextField && ((TextField) q.peek()).getTextfield() != null &&
                    (!(((TextField) (q.peek())).getTextfield().getText().equals("")) || ((TextField) q.peek()).isHasBeenChanged()))
                ((TextField) q.peek()).pressEnter();
            if(q.peek().boxtop)
                q.remove().updateLocation(45,true,q);
            else
                q.remove().updateLocation(10,true,q);
        }
        if(getSet() != null) {
            for (AnnotationComponent comp : getSet()) {
                comp.setMoved(false);
            }
        }
        if(getPanel() != null) {
            getPanel().scrollRectToVisible(rect1);
        }
        this.setIndicator(1);
    }

    public void updateLocation()
    {
        if(getPrev() != null)
            getPrev().updateLocationPrev();
        else
            this.updateLocationPrev();
    }

    public void setVerticalBar(JScrollBar bar)
    {
        this.verticalBar = bar;
    }

    protected void setHeight(int newHeight)
    {
        Rectangle b = this.bounds;
        b.y = newHeight;
        this.bounds = b;
    }

    public String getKey()
    {
        return "";
    }

    public AnnotationComponent getPrev() {return prev; }

    public void setPrev(AnnotationComponent prev) {
        this.prev = prev;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public HashSet<AnnotationComponent> getSet() {
        return set;
    }

    public void setSet(HashSet<AnnotationComponent> set) {
        this.set = set;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public JScrollBar getVerticalBar() {
        return verticalBar;
    }

    public int getIndicator() {
        return indicator;
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    public void setBoxtop(boolean boxtop) { this.boxtop = boxtop; updateLocation(); }

}