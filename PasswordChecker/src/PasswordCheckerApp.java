/**
 * PasswordClassifier.java
 * Reads passwords from a file, classifies them, sorts them by strength, and writes output.
 */
import java.io.*;
import java.util.*;
import java.awt.Desktop;

public class PasswordCheckerApp {
    public static void main(String[] args) throws IOException {
        // File paths
        String inputFile = "C:\\Users\\silve\\OneDrive\\Desktop\\PasswordChecker\\PasswordChecker\\src\\password.txt";
        String breachedFile = "C:\\Users\\silve\\OneDrive\\Desktop\\PasswordChecker\\PasswordChecker\\src\\breached.txt";
        String outputFile = "checked_passwords.txt";

        Scanner inputScanner = new Scanner(new File(inputFile));
        Scanner breachedScanner = new Scanner(new File(breachedFile));
        FileWriter writer = new FileWriter(outputFile);

        List<String> breachedList = new ArrayList<>();
        while (breachedScanner.hasNextLine()) {
            breachedList.add(breachedScanner.nextLine().trim());
        }

        List<PasswordClassifier> passwordList = new ArrayList<>();
        while (inputScanner.hasNextLine()) {
            String pwd = inputScanner.nextLine().trim();
            passwordList.add(new PasswordClassifier(pwd));
        }

        // Sort passwords using selection sort based on strength score
        for (int i = 0; i < passwordList.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < passwordList.size(); j++) {
                if (passwordList.get(j).getStrengthScore() < passwordList.get(minIndex).getStrengthScore()) {
                    minIndex = j;
                }
            }
            PasswordClassifier temp = passwordList.get(i);
            passwordList.set(i, passwordList.get(minIndex));
            passwordList.set(minIndex, temp);
        }

        // Write sorted, classified passwords to file
        for (PasswordClassifier password : passwordList) {
            String pwd = password.getValue();
            String result = pwd + " - ";
            if (breachedList.contains(pwd)) {
                result += "Breached";
            } else {
                result += password.classify();
            }
            writer.write(result + "\n");
        }

        writer.close();
        inputScanner.close();
        breachedScanner.close();

        // Open the new file in the default text editor
        File file = new File(outputFile);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    }
}
