package field1;

import javax.swing.*;
import java.awt.*;

public class AnnotationComponent {
    //includes buttons as well as fields
    protected Rectangle bounds;
    protected AnnotationComponent prev; //vertically previous component
    protected AnnotationComponent next; //vertically next component
    protected JPanel panel;

    public void updateLocation() {
        updateLocation(5);
    }

    private void updateLocation(int gapSize) {
        if(prev != null && bounds != null) {
            bounds.y = prev.bounds.y + prev.bounds.height + gapSize;
        }
        if(next != null)
            next.updateLocation(gapSize);
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