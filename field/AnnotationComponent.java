package field;

import lists.FieldList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AnnotationComponent {
    //includes buttons as well as fields
    public Rectangle bounds;
    public AnnotationComponent prev; //vertically previous component
    public AnnotationComponent next; //vertically next component
    public JPanel panel;
    public ArrayList<AnnotationComponent> set;
    public boolean moved;
    public JScrollBar verticalBar;

    public AnnotationComponent()
    {    }

    public AnnotationComponent(ArrayList<AnnotationComponent> set)
    {
        this.set = set;
        if(set != null) {
            if(!set.contains(this))
               set.add(this);
        }
        moved = false;
    }

    public void updateLocation(int gapSize, boolean fixLists, Queue<AnnotationComponent> q)
    {
        if(set != null)
        {
            if(fixLists) {
                for (AnnotationComponent comp : set) {
                    if (comp instanceof FieldList) {
                        if (((FieldList) comp).getList() != null && ((FieldList) comp).getList().size() > 0) {
                            comp.setHeight((int) (((AnnotationComponent) ((FieldList) (comp)).getList().getLast()).bounds.getY()));
                        }
                    }
                }
            }

            if (!this.moved) {
                if ((!(this instanceof FieldList)) && this.prev != null && this.prev.bounds != null) {
                    this.setHeight((int) this.prev.bounds.getY() + this.prev.bounds.height + gapSize);
                }

                for (AnnotationComponent comp : set) {
                    if (!comp.equals(this)) {
                        if (comp.prev != null && comp.prev.equals(this) && this.bounds != null) {
                            q.add(comp);
                        }
                    }
                }
                this.moved = true;
            }
        }
        if(set != null && panel != null) {
            AnnotationComponent last = this;
            int maxHeight = 0;
            for (AnnotationComponent comp : set) {
                if (comp.bounds != null) {
                    if (comp.bounds.y > maxHeight) {
                        maxHeight = comp.bounds.y;
                        last = comp;
                    }
                }
            }
            if (last != null) {
                if (last.bounds != null) {
                    panel.setBounds(0, 0, 600, last.bounds.y + last.bounds.height + 20);
                    panel.setPreferredSize(new Dimension(600, last.bounds.y + last.bounds.height + 20));
                    panel.validate();
                }
            }
        }
    }

    public void updateLocation() {
        Rectangle rect = null;
        if(panel != null)
            rect = panel.getVisibleRect();
        Rectangle rect1 = rect;
        Queue<AnnotationComponent> q = new LinkedList<AnnotationComponent>();
        q.add(this);
        while(!q.isEmpty())
        {
            q.remove().updateLocation(10,true,q);
        }
        if(set != null) {
            for (AnnotationComponent comp : set) {
                comp.moved = false;
            }
        }
        if(panel != null) {
            panel.scrollRectToVisible(rect1);
        }
    }

    public void setVerticalBar(JScrollBar bar)
    {
        this.verticalBar = bar;
        updateLocation();
    }

    protected void setHeight(int newHeight)
    {
        Rectangle b = this.bounds;
        b.y = newHeight;
        this.bounds = b;
    }

    public AnnotationComponent getPrev() {return prev; }

    public void setPrev(AnnotationComponent prev) {
        this.prev = prev;
        updateLocation();
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}