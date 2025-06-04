/**
 * Password.java
 * Base class that stores a password string
 */
public class Password {
    public String password;

    /**
     * Constructs a Password object
     * @param is the password string
     */ 
    public Password(String password){
        this.password = password;
    }
    
    /**
     * Gets the password string
     * @return the password
     */
    public String getPassword(){
        return password;
    }
    
}