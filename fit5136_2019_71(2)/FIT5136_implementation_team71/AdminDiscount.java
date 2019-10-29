
/**
 * Write a description of class AdminDiscount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdminDiscount extends Discount
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Default Constructor for objects of class AdminDiscount
     */
    public AdminDiscount()
    {
        // initialise instance variables
        discountDescription = "";
        discountName = "";
        discountValue = 0;
    }

    /**
     * Parameterized Constructor for objects of class AdminDiscount
     */
    public AdminDiscount(String discountDescription1,String discountName1, double discountValue1)
    {
        // initialise instance variables
        discountDescription = discountDescription1;
        discountName = discountName1;
        discountValue = discountValue1;
    }
}
