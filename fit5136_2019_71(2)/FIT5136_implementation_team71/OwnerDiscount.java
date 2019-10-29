
/**
 * Write a description of class OwnerDiscount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OwnerDiscount extends Discount
{
    // instance variables - replace the example below with your own
    

    /**
     * Default Constructor for objects of class OwnerDiscount
     */
    public OwnerDiscount()
    {
        // initialise instance variables
        discountDescription = "";
        discountName = "";
        discountValue = 0;
    }

    /**
     * Parameterized Constructor for objects of class OwnerDiscount
     */
    public OwnerDiscount(String discountDescription1,String discountName1, double discountValue1)
    {
        // initialise instance variables
        discountDescription = discountDescription1;
        discountName = discountName1;
        discountValue = discountValue1;
    }
}
