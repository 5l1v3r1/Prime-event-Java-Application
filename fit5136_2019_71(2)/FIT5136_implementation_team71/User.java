
/**
 * User class is getting user details and have all the getter setter methods
 * It has 3 child classes.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class User
{
    // instance variables - replace the example below with your own
    protected int userID;
    protected String fname;
    protected String sname;
    protected String userEmail;
    protected String userPassword;
    protected String userSecurityQuestion;
    protected String userSecurityAnswer;

    /**
     * Default Constructor for objects of class User
     */
    public User()
    {
        fname="";
        sname="";
        userID = 0;
        userEmail = "";
        userPassword = "";
        userSecurityQuestion = "";
        userSecurityAnswer = "";
    }

    /**
     * Parameterized Constructor for objects of class User
     */
    public User(String newFname, String newSname, String newUserEmail,String newUserPassword, String userSecurityQuestion1, String userSecurityAnswer1)
    {
        //userID = userID1;
        userID++;
        fname= newFname;
        sname= newSname;
        userEmail= newUserEmail;
        userPassword= newUserPassword;
        userSecurityQuestion = userSecurityQuestion1;
        userSecurityAnswer = userSecurityAnswer1;
    }

    /**
     * This method is used to get Second Number.  
     */
    public String getSname()
    {
        return sname;
    }

    /**
     * This method is used to get First Number.  
     */
    public String getFname()
    {
        return fname;
    }

    /**
     * This method is used to get User Email.  
     */
    public String getUserEmail()
    {
        return userEmail;
    }

    /**
     * This method is used to get User ID.  
     */
    public int getUserID()
    {
        return userID;
    }

    /**
     * This method is used to get User password.  
     */
    public String getUserPassword()
    {
        return userPassword;
    }

    /**
     * This method is used to get user security question.  
     */
    public String getUserSecurityQuestion()
    {
        return userSecurityQuestion;
    }

    /**
     * This method is used to get User security answer.  
     */
    public String getUserSecurityAnswer()
    {
        return userSecurityAnswer;
    }

    /**
     * This method is used to set User Id.  
     */
    public void setUserID(int newUserID)
    {
        userID= newUserID;
    }

    /**
     * This method is used to set User Email.  
     */
    public void setUserEmail(String newUserEmail)
    {
        userEmail= newUserEmail;
    }

    /**
     * This method is used to set User First name.  
     */
    public void setUserFname(String newFname)
    {
        fname= newFname;
    }

    /**
     * This method is used to set User Second name.  
     */
    public void setUserSname(String newSname)
    {
        sname= newSname;
    }

    /**
     * This method is used to set User password.  
     */
    public void setUserPassword(String newUserPassword)
    {
        userPassword= newUserPassword;
    }

    /**
     * This method is used to set User Security question.  
     */
    public void setUserSecurityQuestion(String newUserSecurityQuestion)
    {
        userSecurityQuestion= newUserSecurityQuestion;
    }

    /**
     * This method is used to set User Security Answer.  
     */
    public void setUserSecurityAnswer(String newUserSecurityAnswer)
    {
        userSecurityAnswer= newUserSecurityAnswer;
    }
}
