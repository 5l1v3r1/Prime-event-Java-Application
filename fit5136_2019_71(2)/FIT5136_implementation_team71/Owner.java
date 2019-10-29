import java.util.ArrayList;
/**
 * Write a description of class Owner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Owner extends User
{
    // instance variables - replace the example below with your own
    private ArrayList<Hall> halls;
    private ArrayList<Discount> discounts;
    private String contactNumber;
    /**
     * Constructor for objects of class Owner
     */
    public Owner()
    {
        super();
        halls = new ArrayList<Hall>();
    }

    public Owner(int newID,String userEmail1,String userPassword1,String newFname, String newSname,String userSecurityQuestion1,String userSecurityAnswer1, String contactNumber1)
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
    
    public String getOwnerName()
    {
        return super.getFname() + super.getSname();
    }

    public ArrayList<Hall> getHalls()
    {
        return halls;
    }

    public void setHalls(ArrayList<Hall> newHalls)
    {
        halls = newHalls;
    }

    public void setOwnerFname(String newFname)
    {
        super.setUserFname(newFname);
    }

    public void setOwnerSname(String newSname)
    {
        super.setUserSname(newSname);
    }

    public String getOwnerEmail()
    {
        return super.getUserEmail();
    }
    
     public String getOwnerFname()
    {
        return super.getFname();
    }
     public String getOwnerSname()
    {
        return super.getSname();
    }
    
    
    public int getOwnerID()
    {
        return super.getUserID();
    }
    
    public String getOwnerPassword()
    {
        return super.getUserPassword();
    }
    
    public String getOwnerSecurityQuestion()
    {
        return super.getUserSecurityQuestion();
    }
    
    public String getOwnerSecurityAnswer()
    {
        return super.getUserSecurityAnswer();
    }
    
    public void setOwnerID(int newUserID)
    {
         super.setUserID(newUserID);
    }
    
    public void setOwnerEmail(String newUserEmail)
    {
         super.setUserEmail(newUserEmail);
    }
    
    public void setOwnerPassword(String newUserPassword)
    {
         super.setUserPassword(newUserPassword);
    }
    
    public void setOwnerSecurityQuestion(String newUserSecurityQuestion)
    {
         super.setUserSecurityQuestion( newUserSecurityQuestion);
    }
    
    public void setOwnerSecurityAnswer(String newUserSecurityAnswer)
    {
         super.setUserSecurityAnswer( newUserSecurityAnswer);
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactnumber(String contactNumber1)
    {
        contactNumber = contactNumber1;
    }
}
