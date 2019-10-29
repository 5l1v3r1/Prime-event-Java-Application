
/**
 * Booking class is getting Booking details and have all the getter setter methods
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class Booking
{
    // instance variables - replace the example below with your own
    private String bookHallName;
    private String bookDate;
    private String userName;
    private String ownerName;
    //private int amount;
    private boolean bookingStatus;
    
    /**
     * Default Constructor for objects of class Booking
     */
    public Booking()
    {
        // initialise instance variables
        bookHallName = "";
        bookDate = "";
        userName = null;
        //amount = 0;
    }

    /**
     * Parameterized Constructor for objects of class Booking
     */
    public Booking(String bookHallName1, String bookDate1, String newUserName, String newOwner)
    {
        // initialise instance variables
        bookHallName = bookHallName1;
        bookDate = bookDate1;
        userName = newUserName;
        ownerName = newOwner;
        bookingStatus = false;
        //customer = ;
        //amount = amount1;
    }
    
    /**
     * This method is used to get Booking status.  
     */
    public boolean getBookingStatus(String hallName)
    {
        return bookingStatus;
    }
    
    /**
     * This method is used to get Booking hall name.  
     */
    public String getBookHallName()
    {
        return bookHallName;
    }
    
    /**
     * This method is used to get Booking date.  
     */
    public String getBookDate()
    {
        return bookDate;
    }
    
    // public int getAmount()
    // {
        // return amount;
    // }
    
    /**
     * This method is used to set Booking status.  
     */
    public void setBookingStatus(Customer customer,String hallName)
    {
        bookingStatus = true;
    }
    
    /**
     * This method is used to set Booking hall name.  
     */
    public void setBookHallName(String bookHallName1)
    {
        bookHallName = bookHallName1;
    }
    
    /**
     * This method is used to set Booking date.  
     */
    public void setBookDate(String bookDate1)
    {
        bookDate = bookDate1;
    }
    
    // public void setAmount(int amount1)
    // {
        // amount = amount1;
    // }
}
