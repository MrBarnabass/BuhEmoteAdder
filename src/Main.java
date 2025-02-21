public class Main {
    public static void main(String[] args) {
        String[] avifFileList = ListAvifFiles.DoIt();
        String[] webpFileList = ListWebpFiles.DoIt();
        String outputText = "copy.txt";

        convertAVIFtoTGA.DoIt(avifFileList);
        convertWEBPtoTGA.DoIt(webpFileList);

        AVIFTextGenerator.FirstPart(avifFileList, outputText);
        WEBPTextGenerator.FirstPart(webpFileList, outputText);

        AVIFTextGenerator.SecondPart(avifFileList, outputText);
        WEBPTextGenerator.SecondPart(webpFileList, outputText);

        AVIFTextGenerator.ThirdPart(avifFileList, outputText);
        WEBPTextGenerator.ThirdPart(webpFileList, outputText);
    }
}
