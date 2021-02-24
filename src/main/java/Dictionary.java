import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {

    public static final int ASCII_LOWER_BOUND = 97;
    public static final int ASCII_UPPER_BOUND = 122;
    public static final int LATIN_LETTER_COUNT = 26;
    public static final String WORKING_RES_DIR = "src/main/resources/";
    public static final String WORKING_OUT_DIR = "src/main/results/";

    public static void main(String[] args) {
        String textContent = readFile(WORKING_RES_DIR + args[0]);
        if(!textContent.isBlank()){
            int[] frequencies = calcFrequency(textContent);
            double[] percentages = calcPercentage(frequencies);
            outputToFile(WORKING_OUT_DIR + "output.txt", frequencies, percentages);
        } else {
            System.out.println("Specified file not found or is empty!");
        }
    }

    public static double[] calcPercentage(int[] frequencies){
        int totalNumberOfLetters = 0;
        double[] percentages = new double[LATIN_LETTER_COUNT];
        for (int element: frequencies) {
            totalNumberOfLetters = totalNumberOfLetters + element;
        }
        for (int i = 0; i < LATIN_LETTER_COUNT; i++) {
            percentages[i] = ((double)frequencies[i] / totalNumberOfLetters) * 100;
        }
        return percentages;
    }

    public static int[] calcFrequency(String givenText) {
        String preparedText = givenText.trim().toLowerCase();
        int[] letterFrequency = new int[LATIN_LETTER_COUNT];
        for (char letter: preparedText.toCharArray()) {
            int asciiValue = letter;
            if ((asciiValue > ASCII_UPPER_BOUND)||(asciiValue < ASCII_LOWER_BOUND)){
                continue;
            } else {
                letterFrequency[asciiValue - ASCII_LOWER_BOUND]++;
            }
        }
        return letterFrequency;
    }

    public static String readFile(String fileName) {
        String textChars = "";
        try {
            File myObj = new File(fileName);
            Scanner textReader = new Scanner(myObj);
            while (textReader.hasNextLine()) {
                String data = textReader.nextLine();
                textChars = textChars.concat(data);
            }
            textReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred. File specified could not be found!");
            e.printStackTrace();
        }
        return textChars;
    }

    public static void outputToFile(String fileName, int[] frequencies, double[] percentages){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            for (int i = 0; i < LATIN_LETTER_COUNT; i++) {
                char fromAsciiToLetter = (char) (i + ASCII_LOWER_BOUND);
                fileWriter.write("Letter: " + fromAsciiToLetter);
                fileWriter.write(" Percentage: " + Double.parseDouble(String.format("%.2f", percentages[i])) + "%");
                fileWriter.write(" Frequency: " + frequencies[i]);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred. Could not be written to file!");
            e.printStackTrace();
        }
    }

}

