package field;

public class MapRestrictedKeysField extends MapField {
    private String[] keyOptions;

    public MapRestrictedKeysField(String key, int mapSize, String[] options) {
        super(key, mapSize);
        this.keyOptions = options;
    }

    public MapRestrictedKeysField(String key, String[] options) {
        super(key);
        this.keyOptions = options;
    }
}
