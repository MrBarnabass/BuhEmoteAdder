import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WEBPTextGenerator {
    static void ThirdPart(String[] fileList, String outputText) {
        for (String s : fileList) {
            if (s != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputText, true))) {
                    writer.write("\t[\"" + s.substring(0, s.length() - 5) + "\"] = \"" + s.substring(0, s.length() - 5) + "\",");
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    static void SecondPart(String[] fileList, String outputText) {
        for (int i = 0; i < fileList.length; i++){
            if (fileList[i] != null){
                String frameSize = getAnimatedFrameSize(fileList[i]);
                if (frameSize != null){
                    String fps = getFPS(fileList[i]);
                    String noFrames = getNoFrames(fileList[i]);
                    String[] frameSizeParts = frameSize.split(" ");

                    int imageHeight = Integer.parseInt(noFrames) * Integer.parseInt(frameSizeParts[1]);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputText, true))) {
                        writer.write("TwitchEmotes_animation_metadata[basePath .. \"" + fileList[i].substring(0, fileList[i].length() - 5) +
                                ".tga\"] = {[\"nFrames\"] = " + noFrames +
                                ", [\"frameWidth\"] = " + frameSizeParts[0] +
                                ", [\"frameHeight\"] = " + frameSizeParts[1] +
                                ", [\"imageWidth\"]= " +frameSizeParts[0] +
                                ", [\"imageHeight\"]= " + imageHeight +
                                ", [\"framerate\"] = " + fps + "}" );
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static String getAnimatedFrameSize(String fileList) {
        String command = "webpinfo " + fileList + " | grep \"Canvas size\" | awk '{print $3 \" \" $5}'";
        return RunCommand.returnProcessBuilder(command);
    }

    private static String getNoFrames(String fileList) {
        String command = "webpmux -info " + fileList + " | grep \"Number of frames:\" | awk '{print $4}'";
        return RunCommand.returnProcessBuilder(command);
    }

    private static String getFPS(String fileList) {
        String command = "webpmux -info " + fileList + " | grep \"lossy\" | awk '{sum += $7} END {print int(sum / NR)}'\n";
        return RunCommand.returnProcessBuilder(command);
    }

    static void FirstPart(String[] fileList, String outputText) {
        for (String s : fileList) {
            if (s != null) {
                String[] frameSize = getAnimatedFrameSize(s).split(" ");
                int x = Integer.parseInt(frameSize[0]);
                int y = Integer.parseInt(frameSize[1]);
                int standardHeight = 28;
                double ratio = (double) x / (double) y;
                int width = (int) (standardHeight * ratio);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputText, true))) {
                    writer.write("\t[\"" + s.substring(0, s.length() - 5) + "\"] = basePath .. \"" + s.substring(0, s.length() - 5) + ".tga:" + standardHeight + ":" + width +"\",");
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
