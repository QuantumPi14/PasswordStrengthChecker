/**
 * PasswordCheckerApp.java
 * Reads passwords from passwords.txt file, classifies them, 
 * sorts them by strength and prints the output in a new .txt file "checked_passwords.txt"
 */
import java.io.*;
import java.util.*;
import java.awt.Desktop;

public class PasswordCheckerApp{
    /**
     * Main method for the password classifying program
     * @param args command-line arguments
     * @throws IOException if file operations don't work
     */
    public static void main(String[] args) throws IOException{
        //File paths 
        String inputFile = "C:\\Users\\silve\\OneDrive\\Desktop\\PasswordChecker\\PasswordChecker\\src\\password.txt";
        String breachedFile = "C:\\Users\\silve\\OneDrive\\Desktop\\PasswordChecker\\PasswordChecker\\src\\breached.txt";
        String outputFile = "checked_passwords.txt";
        
        Scanner inputScanner = new Scanner(new File(inputFile));
        Scanner breachedScanner = new Scanner(new File(breachedFile));
        FileWriter writer = new FileWriter(outputFile);
        
        // Adds all breached passwords into a new breachedList array
        List<String> breachedList = new ArrayList<>();
        while(breachedScanner.hasNextLine()){
            breachedList.add(breachedScanner.nextLine().trim()); 
        }
        // Turns password strings from txt file into PasswordClassifier objects 
        // Stored in an ArrayList 
        List<PasswordClassifier> passwordList = new ArrayList<>();
        while(inputScanner.hasNextLine()){
            String pwd = inputScanner.nextLine().trim();//Found trim on W3schools it helps not count empty space in evaluating strength
            passwordList.add(new PasswordClassifier(pwd));
        }
        
        // Sorts passwords using selection sort based off of their strength score
        for(int i = 0; i < passwordList.size(); i++){
            int minIndex = i;
            for (int j = i +1; j < passwordList.size(); j++){
                if(passwordList.get(j).getStrengthScore() < passwordList.get(minIndex).getStrengthScore()){
                    minIndex = j;
                }
            }
            PasswordClassifier temp = passwordList.get(i);
            passwordList.set(i, passwordList.get(minIndex));
            passwordList.set(minIndex, temp);
        }
        
        // Iterates through PasswordClassifier objects to classify each password
        for(PasswordClassifier password : passwordList){
            String pwd = password.getPassword(); 
            String result = pwd + " - ";
            if(breachedList.contains(pwd)){
                result += "Breached";
            }
            else{
                result += password.classify();
            }
            writer.write(result +"\n");
        }
        
        writer.close();
        inputScanner.close();
        breachedScanner.close();
        
        //Open the new output file 
        File file = new File(outputFile);
        if (Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }
        
    }
}