import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DictionaryTest {

    @Test
    void main() {
        //Tests the functionality as a whole
        //Test case 1, file with "perfect" data
        //Test case 2, file with "random" data
        //Test case 3, empty file
    }

    @Test
    void calcPercentage() {
        //Test case 1, and the only one, as the size of the frequency array is equal due to class constants.
        //Checks the math behind calcPercentage(), with generated example values
        int[] testFrequencies = new int[Dictionary.LATIN_LETTER_COUNT];
        double[] expectedPercentages = new double[Dictionary.LATIN_LETTER_COUNT];
        double frequencyTotalValue = ((double)Dictionary.LATIN_LETTER_COUNT/2)*(Dictionary.LATIN_LETTER_COUNT-1);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            testFrequencies[i] = i;
            expectedPercentages[i] = (i/frequencyTotalValue)*100;
        }
        double[] calculatedPercentages = Dictionary.calcPercentage(testFrequencies);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            assertEquals(expectedPercentages[i], calculatedPercentages[i], 0.001);
        }
    }

    @Test
    void calcFrequency() {
        //Test case 1, equal frquencies
        String testString = "abcdefghijklmnopqrstuvwxyz";
        int[] expectedFrequencies = new int[Dictionary.LATIN_LETTER_COUNT];
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            expectedFrequencies[i] = 1;
        }
        int[] calculatedFrequencies = Dictionary.calcFrequency(testString);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            assertEquals(expectedFrequencies[i], calculatedFrequencies[i]);
        }

        //Test case 2, unequal frquencies
        testString = "bdfhjlnprtvxz";
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            expectedFrequencies[i] = i%2;
        }
        calculatedFrequencies = Dictionary.calcFrequency(testString);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            assertEquals(expectedFrequencies[i], calculatedFrequencies[i]);
        }

        //Test case 3, random values between letters
        testString = "a!b@c#d$e%f^g&h*i(j)k{l}m[n]o;p'q.r,stuvwxyz";
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            expectedFrequencies[i] = 1;
        }
        calculatedFrequencies = Dictionary.calcFrequency(testString);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            assertEquals(expectedFrequencies[i], calculatedFrequencies[i]);
        }

        //Test case 4, capital letters in the text
        testString = "AbCdEfGhIjkLmNoPqRsTuVwXyZ";
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            expectedFrequencies[i] = 1;
        }
        calculatedFrequencies = Dictionary.calcFrequency(testString);
        for (int i = 0; i < Dictionary.LATIN_LETTER_COUNT; i++) {
            assertEquals(expectedFrequencies[i], calculatedFrequencies[i]);
        }
    }

    @Test
    void readFile() {
        //Test case 1, file with contents
        String fileName = "src/test/resources/example_test_file_1.txt";
        String expectedString = "abcdefghijklmnopqrstuvwxyz";
        String retrievedFileContents = Dictionary.readFile(fileName);
        assertEquals(expectedString,retrievedFileContents);

        //Test case 2, file with no contents
        fileName = "src/test/resources/example_test_file_2.txt";
        expectedString = "";
        retrievedFileContents = Dictionary.readFile(fileName);
        assertEquals(expectedString,retrievedFileContents);
    }
}