package field;

import java.util.HashMap;
import java.util.Map;

public class MapField extends AnnotationField<Map> {

    public int getSize() {
        return mapSize;
    }

    public void setSize(int mapSize) {
        this.mapSize = mapSize;
    }

    private int mapSize;

    public MapField(String key, int mapSize) {
        super(key, new HashMap());
        this.mapSize = mapSize;
    }

    public MapField(String key) {
        super(key, new HashMap());
    }

    public MapField(String key, Map value) {
        super(key, value);
    }
}
