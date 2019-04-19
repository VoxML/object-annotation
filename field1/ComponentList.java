package field1;

import java.util.*;

public class ComponentList extends AnnotationComponent {

    public ComponentList(ArrayList<AnnotationComponent> set){
        super(set);
        set.add(this);
    }
    LinkedList<AnnotationComponent> list;
}