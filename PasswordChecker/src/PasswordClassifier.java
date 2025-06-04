/**
 * PasswordClassifier.java
 * Subclass of Password that checks strength score and classifies it (weak, moderate, strong, breached)
 */
public class PasswordClassifier extends Password{
    
    /**
     * Constructs a PasswordClassifier object
     * @param password string to be classified
     */
    public PasswordClassifier(String password){
        super(password);
    }
    
    /**
     * Calculates strength score based on the criteria-->
     * (length, uppercase, lowercase, numbers, special characters)
     * @return the strength score (0-5)
     */
    public int getStrengthScore(){
        int score = 0;
        
        if(password.length() > 8.0){
        score++;
        }
        
        // The .matches() is from stackoverflow I 
        if(password.matches(".*[A-Z].")){
            score++;
        }
        if(password.matches(".*[a-z].*")){
            score++;
        }
        if(password.matches(".*[0-9].*")){
            score++;
        }
        if (password.matches(".*[!@#$%^&*()].*")){
            score++;
        }
        return score;
    }
    
    /** 
     * Classifies passwords from password file based on strength score
     * @return a String: "Weak", "Moderate","Strong"
     */
     public String classify(){
         int score = getStrengthScore();
         
         if(score >= 4){
            return "Strong";
         }
         else if(score >= 2){
            return "Moderate";
         }
         else{
            return "Weak";
         }
     }
     
}