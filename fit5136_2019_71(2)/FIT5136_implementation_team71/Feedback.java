import java.lang.*;
import java.util.*;
/**
 * Feedback class has getter setter methods to set and get feedback details into controller class.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class Feedback
{
    // instance variables - replace the example below with your own
    private String customerName;
    private String feedback;
    private String hallName;
    private int rating;

    /**
     * Default Constructor for objects of class Feedback
     */
    public Feedback()
    {
        customerName = "";
        feedback = "";
        hallName = "";
        rating = 0;
    }
    
    /**
     * Parameterized Constructor for objects of class Feedback
     */
    public Feedback(String newCustomer, String newFeedback, String newHall, int newRating)
    { 
        customerName = newCustomer;
        feedback = newFeedback;
        hallName = newHall;
        rating = newRating;
    }

    /**
     * This method is used to get customer name.  
     */
    public String getCustomerName()
    {
        return customerName;
    }
    
    /**
     * This method is used to get customer feedback.  
     */
    public String getFeedback()
    {
        return feedback;
    }
    
    /**
     * This method is used to get hall name.  
     */
    public String getHallName()
    {
        return hallName;
    }
    
    /**
     * This method is used to get rating.  
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * This method is used to set customer name.  
     */
    public void setCustomerName(String newCustomer)
    {
        customerName = newCustomer;
    }
    
    /**
     * This method is used to set feedback name.  
     */
    public void setFeedback(String newFeedback)
    {
        feedback = newFeedback;
    }
    
    /**
     * This method is used to set hall name.  
     */
    public void setHallName(String newHall)
    {
        hallName = newHall;
    }
    
    /**
     * This method is used to set rating.  
     */
    public void setRating(int newRating)
    {
        rating = newRating;
    }
}
