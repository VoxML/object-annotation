package lists;

import field.AnnotationComponent;

import java.util.*;

public class ComponentList extends AnnotationComponent {

    public ComponentList(ArrayList<AnnotationComponent> set){
        super(set);
        if(!set.contains(this))
            set.add(this);
    }
    LinkedList<AnnotationComponent> list;
}