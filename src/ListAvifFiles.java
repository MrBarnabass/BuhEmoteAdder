import java.io.File;

public class ListAvifFiles {
    public static String[] DoIt() {
        String currentDirectory = System.getProperty("user.dir");
        File folder = new File(currentDirectory);
        File[] files = folder.listFiles();

        int i = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".avif")) {
                    i++;
                }
            }
        }

        int j = 0;
        String[] out = new String[i+1];
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".avif")) {
                    out[j] = file.getName();
                    j++;
                }
            }
        }
        return out;
    }
}
