
/**
 * Hall class is getting hall details and have all the getter setter methods
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class Hall
{
    // instance variables - replace the example below with your own
    private String hallDescription;
    private String hallLocation;
    private String hallName;
    private String hallOwner;
    private double hallPrice;
    private int hallID;

    /**
     * Default Constructor for objects of class Hall
     */
    public Hall()
    {
        hallDescription = "";
        hallLocation = "";
        hallName = "";
        hallOwner = "";
        hallID = 0;
    }

    /**
     * Parameterized Constructor for objects of class Hall
     */
    public Hall(String newDescription,String newLocation,String newName,String newOwner,int newID)
    {
        hallDescription= newDescription;
        hallLocation= newLocation;
        hallName= newName;
        hallOwner= newOwner;
        hallID = newID;
    }

    /**
     * This method is used to get Hall description.  
     */
    public String getHallDescription()
    {
        return hallDescription;
    }

    /**
     * This method is used to get Hall ID.  
     */
    public int getHallID()
    {
        return hallID;
    }

    /**
     * This method is used to get Hall Location.  
     */
    public String getHallLocation()
    {
        return hallLocation;
    }

    /**
     * This method is used to get Hall Name.  
     */
    public String getHallName()
    {
        return hallName ;
    }

    /**
     * This method is used to get Hall Owner.  
     */
    public String getHallOwner()
    {
        return hallOwner ;
    }

    /**
     * This method is used to set Hall description.  
     */
    public void setHallDescription(String newDescription)
    {
        hallDescription= newDescription;
    }

    /**
     * This method is used to set Hall ID.  
     */
    public void setHallID(int newID)
    {
        hallID = newID;
    }
    
    /**
     * This method is used to set Hall Location.  
     */
    public void setHallLocation(String newLocation)
    {
        hallLocation= newLocation;
    }

    /**
     * This method is used to set Hall Name.  
     */
    public void setHallName(String newName)
    {
        hallName= newName;
    }

    /**
     * This method is used to set Hall Owner.  
     */
    public void setHallOwner(String newOwner)
    {
        hallOwner= newOwner;
    }

}
