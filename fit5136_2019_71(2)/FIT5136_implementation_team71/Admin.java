import java.util.ArrayList;
/**
 * Write a description of class Admin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Admin extends User
{
    // instance variables - replace the example below with your own
    private ArrayList<Discount> discounts;
    /**
     * Constructor for objects of class Admin
     */
    public Admin()
    {
        // initialise instance variables
        userEmail = "";
        userPassword = "";
        userSecurityQuestion = "";
        userSecurityAnswer = "";
    }

    public Admin(String userEmail1,String userPassword1,String userSecurityQuestion1,String userSecurityAnswer1)
    {
        // initialise instance variables
        //userName =userName1;
        userEmail = userEmail1;
        userPassword = userPassword1;
        userSecurityQuestion = userSecurityQuestion1;
        userSecurityAnswer = userSecurityAnswer1;
    }
    
    public String getEmail()
    {
        return super.getUserEmail();
    }
    
    public String getPassword()
    {
        return super.getUserPassword();
    }
    
    public String getSecurityQuestion()
    {
        return super.getUserSecurityQuestion();
    }
    
    public String getSecurityAnswer()
    {
        return super.getUserSecurityAnswer();
    }
    
    public void setEmail(String newEmail)
    {
        super.setUserEmail(newEmail);
    }
    
    public void setPassword(String newUserPassword)
    {
         super.setUserPassword(newUserPassword);
    }
    
    public void setSecurityQuestion(String newUserSecurityQuestion)
    {
         super.setUserSecurityQuestion( newUserSecurityQuestion);
    }
    
    public void setSecurityAnswer(String newUserSecurityAnswer)
    {
         super.setUserSecurityAnswer( newUserSecurityAnswer);
    }
}
