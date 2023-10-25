package week_8.day_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
Simple Text File Transformation

Objective: Use character streams to read data from a text file, apply a simple transformation, and write the transformed data to a new text file.

Task:

Read the content of an input text file named "input.txt."
Transform the text in the following way:
Convert all text to uppercase.
Replace all occurrences of "JAVA" with "Python."
Write the transformed text to an output text file named "output.txt.”
 */
public class TextFileTransformation {
    public static void main(String[] args) {
        String inputFilePath = "src/main/java/week_8/day_2/input.txt";
        String outputFilePath = "src/main/java/week_8/day_2/output.txt";

        StringBuilder textToOutput = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(inputFilePath);
             FileWriter fileWriter = new FileWriter(outputFilePath)) {
            int character;
            while ((character = fileInputStream.read()) != -1) {
                char c = (char) character;
                textToOutput.append(c);
            }
            String finalOutput = textToOutput.toString().toUpperCase();
            fileWriter.write(finalOutput.replace("JAVA", "PYTHON"));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
        }
    }
}
