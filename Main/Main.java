package Main;

public class Main {
    public static void main(String[] args)
    {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "VoxML Annotation Tool");
        System.setProperty("apple.awt.application.name","VoxML Annotation Tool");
        new VoxML_Annotation_Tool().setVisible(true);
    }
}
