
/**
 * UI class is boundary class it has display methods to display menus and it is called in controller class.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class UI
{
    // instance variables - replace the example below with your own

    /**
     * Default Constructor for objects of class UI
     */
    public UI()
    {
        // initialise instance variables

    }

    /**
     * This method is used to display Owner Menu.  
     */
    public void menuOwner()
    {
        System.out.println("\n");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println(" #              PRIME EVENTS             # ");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println("                                           ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("          1. Manage Discounts              ");
        System.out.println("          2. Manage Halls                  ");
        System.out.println("          3. Manage Booking                ");
        System.out.println("          4. Manage Payment                ");
        System.out.println("          5. Logout                        ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("                                           ");
        System.out.println(" Please Press number to select option (1-5)");
    }

    /**
     * This method is used to display Homepage.  
     */
    public void homePage()
    {
        System.out.println("\n");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println(" #              PRIME EVENTS             # ");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println("                                           ");
        System.out.println(" _________________________________________ ");
        System.out.println(" Please Press 1 For Login to Account");
        System.out.println(" Please Press 2 For Register an Account");
        System.out.println(" Please Press 3 For Search Hall");
        System.out.println(" ----------------------------------------- ");
        System.out.println("                                           ");
        
    }

    /**
     * This method is used to display Customer Menu.  
     */
    public void menuCustomer()
    {
        System.out.println("\n");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println(" #              PRIME EVENTS             # ");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println("                                           ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("          1. View Halls                    ");
        System.out.println("          2. Search                        ");
        System.out.println("          3. Book Hall                     ");
        System.out.println("          4. Manage a Booking              ");
        System.out.println("          5. Write a Review                ");
        System.out.println("          6. Logout                        ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("                                           ");
        System.out.println(" Please Press number to select option (1-6)");
    }

    /**
     * This method is used to display Admin Menu.  
     */
    public void menuAdmin()
    {
        System.out.println("\n");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println(" #              PRIME EVENTS             # ");
        System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
        System.out.println("                                           ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("          1. Manage Users                  ");
        System.out.println("          2. Manage Discounts              ");
        System.out.println("          3. Search Halls                  ");
        System.out.println("          4. Logout                        ");
        System.out.println(" ----------------------------------------- ");
        System.out.println("   ");
        System.out.println(" Please Press number to select option (1-4)");
    }
}
