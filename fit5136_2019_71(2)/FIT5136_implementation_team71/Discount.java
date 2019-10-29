
/**
 * Discount class is getting Discount details and have all the getter setter methods
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class Discount
{
    // instance variables - replace the example below with your own
    protected String discountDescription;
    protected String discountName;
    protected double discountValue;
    
    
    /**
     * Default Constructor for objects of class Discount
     */
    public Discount()
    {
        // initialise instance variables
        discountDescription = "";
        discountName = "";
        discountValue = 0;
    }

    /**
     * Parameterized Constructor for objects of class Discount
     */
    public Discount(String discountDescription1,String discountName1,double discountValue1)
    {
        discountDescription = discountDescription1;
        discountName = discountName1;
        discountValue = discountValue1;
    }
    
    /**
     * This method is used to get Discount description.  
     */
    public String getDiscountDescription()
    {
        return discountDescription;
    }
    
    /**
     * This method is used to get Discount name.  
     */
    public String getDiscountName()
    {
        return discountName;
    }
    
    /**
     * This method is used to get discount value.  
     */
    public double getDiscountValue()
    {
        return discountValue;
    }
    
    /**
     * This method is used to set Discount description.  
     */
    public void setDiscountDescription(String discountDescription1)
    {
        discountDescription = discountDescription1;
    }
    
    /**
     * This method is used to set Discount name.  
     */
    public void setDiscountName(String discountName1)
    {
        discountName = discountName1;
    }
    
    /**
     * This method is used to set Discount value.  
     */
    public void setDiscountValue(double discountValue1)
    {
        discountValue = discountValue1;
    }
}
