package field.impl;

import field.MapRestrictedKeysField;

public class Head extends MapRestrictedKeysField {
    static String key = "head";
    public Head() {

        super(key, new String[]{
                "",
                "prismatoid",
                "pyramid",
                "wedge",
                "parallelepiped",
                "cupola",
                "frustum",
                "cylindroid",
                "ellipsoid",
                "hemiellipsoid",
                "bipyramid",
                "rectangular prism",
                "toroid",
                "sheet"});
        this.setSize(1);
    }
}
