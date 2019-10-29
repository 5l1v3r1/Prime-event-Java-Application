
/**
 * Customer class is getting customer details and have all the getter setter methods.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class Customer extends User
{
    // instance variables - replace the example below with your own
    
    private String contactNumber;
    /**
     * Default Constructor for objects of class Customer
     */
    public Customer()
    {
        // initialise instance variables
        userID=1;
        fname="";
        sname="";
        userEmail = "";
        userPassword = "";
        userSecurityQuestion = "";
        userSecurityAnswer = "";
        String contactNumber ="" ;
    }

    /**
     * Parameterized Constructor for objects of class Customer
     */
    public Customer(int newID, String userEmail1,String userPassword1,String newFname, String newSname,String userSecurityQuestion1,String userSecurityAnswer1, String contactNumber1)
    {
        // initialise instance variables
        userID = newID;
        fname= newFname;
        sname= newSname;
        userEmail = userEmail1;
        userPassword = userPassword1;
        userSecurityQuestion = userSecurityQuestion1;
        userSecurityAnswer = userSecurityAnswer1;
        contactNumber = contactNumber1;
    }
    
    /**
     * This method is used to get Customer Email.  
     */
    public String getCustomerEmail()
    {
        return super.getUserEmail();
    }
    
    /**
     * This method is used to set Customer First name.  
     */
    public void setCustomerFname(String newFname)
    {
        super.setUserFname(newFname);
    }

    /**
     * This method is used to set Customer Second name.  
     */
    public void setCustomerSname(String newSname)
    {
        super.setUserSname(newSname);
    }

    /**
     * This method is used to get Customer ID.  
     */
    public int getCustomerID()
    {
        return super.getUserID();
    }
    
    /**
     * This method is used to get Customer First name.  
     */
     public String getCustomerFname()
    {
        return super.getFname();
    }
    
    /**
     * This method is used to get Customer Second name.  
     */
     public String getCustomerSname()
    {
        return super.getSname();
    }
    
    /**
     * This method is used to get Customer Password.  
     */
    public String getCustomerPassword()
    {
        return super.getUserPassword();
    }
    
    /**
     * This method is used to get Customer Security Question.  
     */
    public String getCustomerSecurityQuestion()
    {
        return super.getUserSecurityQuestion();
    }
    
    /**
     * This method is used to get Customer Security Answer.  
     */
    public String getCustomerSecurityAnswer()
    {
        return super.getUserSecurityAnswer();
    }
    
    /**
     * This method is used to set Customer ID.  
     */
    public void setCustomerID(int newUserID)
    {
         super.setUserID(newUserID);
    }
    
    /**
     * This method is used to set Customer Email.  
     */
    public void setCustomerEmail(String newUserEmail)
    {
         super.setUserEmail(newUserEmail);
    }
    
    /**
     * This method is used to set Customer Password.  
     */
    public void setCustomerPassword(String newUserPassword)
    {
         super.setUserPassword(newUserPassword);
    }
    
    /**
     * This method is used to set Customer Security Question.  
     */
    public void setCustomerSecurityQuestion(String newUserSecurityQuestion)
    {
         super.setUserSecurityQuestion( newUserSecurityQuestion);
    }
    
    /**
     * This method is used to set Customer Security Answer.  
     */
    public void setCustomerSecurityAnswer(String newUserSecurityAnswer)
    {
         super.setUserSecurityAnswer( newUserSecurityAnswer);
    }

    /**
     * This method is used to get Contact Number.  
     */
    public String getContactNumber()
    {
        return contactNumber;
    }
    
    /**
     * This method is used to set Contact Number.  
     */
    public void setContactnumber(String contactNumber1)
    {
        contactNumber = contactNumber1;
    }
    
}
