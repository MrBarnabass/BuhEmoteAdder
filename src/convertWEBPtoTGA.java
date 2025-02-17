import java.io.IOException;

public class convertWEBPtoTGA {
    static void DoIt(String[] fileList) {
        for (int i = 0; i < fileList.length; i++){
            if (fileList[i] != null){
                try {
                    createFrames(fileList[i]);
                    mergeFrames(fileList[i]);
                } catch (Exception e) {
                    staticConversion(fileList[i]);
                }
            }
        }
    }

    private static void createFrames(String fileList) throws IOException, InterruptedException {
        String command =
                "magick " +
                fileList+
                " -coalesce" + " -quality" + " 100 " +
                fileList.substring(0, fileList.length()-5) + "%04d.png"
        ;
        RunCommand.magickProcessBuilder(command);
    }

    private static void mergeFrames(String fileList) throws IOException, InterruptedException {
        String command =
                "magick " +
                        fileList.substring(0, fileList.length()-5) + "????.png " +
                        "-append " +
                        fileList.substring(0, fileList.length()-5) + ".tga " +
                        "&& " + "rm " +
                        fileList.substring(0, fileList.length()-5) + "????.png";
        RunCommand.magickProcessBuilder(command);
    }

    private static void staticConversion(String fileList) {
        try {
            String[] command = {
                    "ffmpeg",
                    "-i", fileList,
                    "-filter_complex", "[0:0][0:1]alphamerge", "-pix_fmt", "rgba",
                    fileList.substring(0, fileList.length()-5) + ".tga"
            };
            RunCommand.ffmpegProcessBuilder(command);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}