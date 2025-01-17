public class Main {
    public static void main(String[] args) {
        String[] fileList = ListAvifFiles.DoIt();
        String outputText = "copy.txt";

        convertAVIFtoTGA.DoIt(fileList);
        TextGenerator.FirstPart(fileList, outputText);
        TextGenerator.SecondPart(fileList, outputText);
        TextGenerator.ThirdPart(fileList, outputText);
    }
}
