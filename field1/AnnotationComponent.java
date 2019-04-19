package field1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class AnnotationComponent {
    //includes buttons as well as fields
    protected Rectangle bounds;
    protected AnnotationComponent prev; //vertically previous component
    protected AnnotationComponent next; //vertically next component
    protected JPanel panel;
    protected ArrayList<AnnotationComponent> set;

    public AnnotationComponent(ArrayList<AnnotationComponent> set)
    {
        this.set = set;
        if(set != null)
            set.add(this);
    }

    public void updateLocation() {
        updateLocation(10);
    }

    private void updateLocation(int gapSize) {
        if(set != null) {
            if (!set.contains(this)) {
                set.add(this);
            }
            for (int i = 0; i < set.size(); i++) {
                if (set.get(i).prev != null && set.get(i).prev.equals(this)) {
                    set.get(i).setHeight(set.get(i).prev.bounds.y + set.get(i).prev.bounds.height + gapSize);
                    set.get(i).updateLocation();
                } else if (set.get(i).prev != null)
                    set.get(i).setHeight(set.get(i).prev.bounds.y + set.get(i).prev.bounds.height + gapSize);
            }
        }
        if(prev != null)
            setHeight(this.prev.bounds.y + this.prev.bounds.height + gapSize);
    }

    private void setHeight(int newHeight)
    {
        Rectangle b = this.bounds;
        b.y = newHeight;
        this.bounds = b;
    }

    public AnnotationComponent getPrev() {return prev; }
    public void setPrev(AnnotationComponent prev) {
        this.prev = prev;
        if(prev != null)
            this.prev.next = this;
        updateLocation();
    }
    public AnnotationComponent getNext() {return next; }
    public void setNext(AnnotationComponent next) {
        this.next = next;
        if(next != null)
            this.next.prev = this;
        updateLocation();
    }
}