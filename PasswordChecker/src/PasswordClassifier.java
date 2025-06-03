/**
 * StrongPassword.java
 * Subclass of Password that checks strength and classifies it.
 */
class PasswordClassifier extends Password {

    public PasswordClassifier(String value) {
        super(value);
    }

    /**
     * Calculates a score based on password rules.
     */
    public int getStrengthScore() {
        int score = 0;

        if (value.length() >= 8) {
            score++;
        }
        if (value.matches(".*[A-Z].*")) {
            score++;
        }
        if (value.matches(".*[a-z].*")) {
            score++;
        }
        if (value.matches(".*[0-9].*")) {
            score++;
        }
        if (value.matches(".*[!@#$%^&*()].*")) {
            score++;
        }

        return score;
    }

    /**
     * Classifies password based on strength score.
     */
    public String classify() {
        int score = getStrengthScore();

        if (score >= 4) {
            return "Strong";
        } else if (score >= 2) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }
} 