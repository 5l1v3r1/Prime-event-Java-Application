
import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.*;
/**
 * PrimeEvent is our main controller class. it has every methods for the shown menu and it can use all the classe's getter setter methods by creating 
 * object of the class. also it has all the attributes and methods required to run basic functionalities.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class PrimeEvent 
{
    // instance variables - replace the example below with your own
    private ArrayList<Owner> owners;
    private ArrayList<Customer> customers;
    Customer newCustomer = new Customer();
    Scanner console = new Scanner(System.in);
    Owner newOwner = new Owner();
    String loggedUser = "";
    UI ui = new UI();
    private ArrayList<Hall> halls;
    private Admin admin;
    /**
     * Default Constructor for objects of class PrimeEvent
     */
    public PrimeEvent()
    {
        owners = new ArrayList<Owner>();
        halls = new ArrayList<Hall>();
        customers = new ArrayList<Customer>();
        admin = new Admin();
        owners.add(newOwner);
        setAllHalls();
        setAllOwners();
        setAllCustomers();
        setAdmin();
    }

    /**
     * This method is used to Get owner details from array using owner class.  
     */
    public ArrayList<Owner> getOwners()
    {
        return owners;
    }

    /**
     * This method is used to Set owner details in array using owner class's.  
     */
    public void setOwners(ArrayList<Owner> newOwners)
    {
        owners = newOwners;
    }

    /**
     * This method is used to Set admin details in array using admin class.  
     */
    private void setAdmin()
    {
        FileIO file = new FileIO();
        String content = file.readHallFile("adminDetails.txt");
        String[] words = content.split(",");
        admin = new Admin(words[1],words[2],words[5],words[6]);
    }

    /**
     * This method is used to Create a Hall.  
     */
    private void createHall(int index)
    {
        String hallDescription = "";
        String hallLocation = "";
        String hallName = "";
        String hallOwner = "";
        int hallID = 0;
        boolean flag = true;
        do{
            System.out.println("========Creating Hall========"); 
            System.out.println('\n');
            System.out.println("1. Create Hall");
            System.out.println("2. go back");
            int option = checkNumber(">Please input the number to choose",1,2);
            if(option == 1)
            {
                hallName = createUI("hall name");
                hallLocation = createUI("hall location");
                hallDescription = createUI("events that can be organised");
                hallOwner = owners.get(index).getOwnerEmail();
                hallID = index+1 *1000 + halls.size()+1;
                flag = displayHallDetail(hallDescription,hallLocation,hallName,hallOwner,">The new hall has been created.");
                if(!flag)
                {
                    Hall creatingHall = new Hall(hallDescription,hallLocation,hallName,hallOwner,hallID);
                    owners.get(index).getHalls().add(creatingHall);
                    halls.add(creatingHall);
                    rewriteHalls();
                }
            }
            else
                break;
        }while(flag);

    }

    /**
     * This method is used to Edit a Hall.  
     */
    private void editHall(int index)
    {
        boolean flag = true;
        System.out.println("========Editing Hall========");
        System.out.println('\n');
        System.out.println("1. Edit Hall");
        System.out.println("2. go back");
        int options = checkNumber(">Please input the number to choose",1,2);
        if(options == 1)
        {
            int choice = listHalls(index);
            if(choice!=-1)
            {
                int result = findHall(index,choice);
                Hall editingHall = new Hall(owners.get(index).getHalls().get(choice).getHallDescription(),owners.get(index).getHalls().get(choice).getHallLocation(),
                                    owners.get(index).getHalls().get(choice).getHallName(),owners.get(index).getHalls().get(choice).getHallOwner(),
                                    owners.get(index).getHalls().get(choice).getHallID());
                System.out.println("1. Hall Name");
                System.out.println("2. Hall Location");
                System.out.println("3. Hall Events type");
                System.out.println("4. Go back");
                int option = checkNumber(">Please input the number to choose",1,4);
                switch(option)
                {
                    case 1: 
                    editingHall.setHallName(createUI("hall name"));
                    break;
                    case 2: 
                    editingHall.setHallLocation(createUI("hall location"));
                    break;
                    case 3: 
                    editingHall.setHallDescription(createUI("events"));
                    break;
                    case 4:
                    flag=false;
                    break;
                }
                flag = displayHallDetail(editingHall.getHallDescription(),editingHall.getHallLocation(),editingHall.getHallName(),editingHall.getHallOwner(),">The change has been saved.");
                if(flag)
                    editHall(index);
                else
                {
                    owners.get(index).getHalls().set(choice,editingHall);
                    halls.set(result,editingHall);
                    rewriteHalls();
                }
            }
        }
    }

    /**
     * This method is used to Find a Hall.  
     */
    private int findHall(int indexOfO,int indexOfH )
    {
        int ID = owners.get(indexOfO).getHalls().get(indexOfH).getHallID();
        int result = -1;
        for(int i=0;i<halls.size();i++)
        {
            if(ID==halls.get(i).getHallID())
            {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * This method is used to Delete a Hall.  
     */
    private void deleteHall(int index)
    {
        boolean flag = true;
        do
        {
            System.out.println("========Deleting Hall========");
            System.out.println('\n');
            System.out.println("1. Delete Hall");
            System.out.println("2. go back");
            int option = checkNumber(">Please input the number to choose",1,2);
            if(option == 1)
            {
                int choice = listHalls(index);
                if(choice !=-1)
                {
                    System.out.println("1. Delete");
                    System.out.println("2. go back");
                    int options = checkNumber(">Do you want to delete the hall?",1,2);
                    if(options == 1)
                    {
                        halls.remove(findHall(index,choice));
                        owners.get(index).getHalls().remove(choice); 
                        System.out.println(">The hall has been deleted.");
                        flag = false;
                    }
                }
                else
                    break;
            }
            else
                break;
        }while(flag);
        rewriteHalls();
    }

    /**
     * This method is used to Manage a Hall.
     * As per option we can have 3 options 1.create a hall  2.edit hall  3.delete hall 
     */
    private void manageHall(int index)
    {
        setOwnerHalls(index);
        while(true)
        {
            System.out.println("========Manage Hall========");
            System.out.println('\n');
            System.out.println("1. View My Hall");
            System.out.println("2. Create Hall");
            System.out.println("3. Edit Hall");
            System.out.println("4. Delete Hall");
            System.out.println("5. go back");
            int options = checkNumber(">Please choose one option",1,5);
            if(options == 5)
            {
                System.out.print('\u000C');
                break;
            }
            if(options == 2)
            {
                System.out.print('\u000C');
                createHall(index);
            }
            else
            {
                if(owners.get(index).getHalls().isEmpty())
                {
                    System.out.println(">You do not have any hall.");
                }
                else
                {
                    if(options==1)
                    {
                        System.out.print('\u000C');
                        viewHalls(owners.get(index).getHalls(),0);
                    }
                    else if(options == 3)
                    {
                        System.out.print('\u000C');
                        editHall(index);
                    }
                    else
                    {
                        System.out.print('\u000C');
                        deleteHall(index);
                    }
                }
            }
        }
    }

    /**
     * This method is used to Set hall to its owner using getter setter method.  
     */
    private void setOwnerHalls(int index)
    {
        ArrayList<Hall> ownerHalls = new ArrayList<Hall>();
        for(int i = 0; i<halls.size(); i++ )
        {
            if(halls.get(i).getHallID()/1000 == index+1)
                ownerHalls.add(halls.get(i));
        }
        owners.get(index).setHalls(ownerHalls);
    }

    /**
     * This method is used to Rewrite the Hall data after edit function.  
     */
    private void rewriteHalls()
    {
        String contents = "";
        for(int i = 0; i < halls.size(); i++)
            contents += halls.get(i).getHallDescription() + ";" + halls.get(i).getHallLocation() + ";" + halls.get(i).getHallName() + ";" +
            halls.get(i).getHallOwner() + ";" +  halls.get(i).getHallID() +'\n';
        FileIO file = new FileIO();
        file.writeHallFile(contents,"hall.txt");
    }

    /**
     * This method is used to Rewrite owner details with new password after forget password method.  
     */
    private void rewriteOwners()
    {
        String contents = "";
        for(int i = 0; i < owners.size(); i++)
            contents += owners.get(i).getOwnerID() + "," + owners.get(i).getOwnerEmail() + "," + owners.get(i).getOwnerPassword() + "," + 
            owners.get(i).getOwnerFname() + "," +
            owners.get(i).getOwnerSname() + "," +  owners.get(i).getOwnerSecurityQuestion() + ","  +
            owners.get(i).getOwnerSecurityAnswer() + "," + 
            owners.get(i).getContactNumber() + '\n';
        FileIO file = new FileIO();
        file.writeHallFile(contents,"ownerDetails.txt");
    }

    /**
     * This method is used to Rewrite customer details with new password after forget password method.  
     */
    private void rewriteCustomers()
    {
        String contents = "";
        for(int i = 0; i < customers.size(); i++)
            contents += customers.get(i).getCustomerID() + "," + customers.get(i).getCustomerEmail() + "," + customers.get(i).getCustomerPassword() + "," + 
            customers.get(i).getCustomerFname() + "," +
            customers.get(i).getCustomerSname() + "," +  customers.get(i).getCustomerSecurityQuestion() + ","  +
            customers.get(i).getCustomerSecurityAnswer() + "," +  
            customers.get(i).getContactNumber() + '\n';

        FileIO file = new FileIO();
        file.writeHallFile(contents,"customerDetails.txt");
    }

    /**
     * This method is used to display UI functions like save and go back.  
     */
    private String createUI(String words)
    {
        System.out.println('\n');
        boolean flag = true;
        String result = "";
        do{
            System.out.println(">Please Enter the " + words +"." ); 
            Scanner enter = new Scanner(System.in);
            result = enter.nextLine();
            System.out.println(">" + words + ": " + result);
            System.out.println("1. save");
            System.out.println("2. go back");
            System.out.println('\n');
            int choice = checkNumber(">Please input the number to choose",1,2);
            if(choice == 1)
            {
                System.out.println(">" + words + " saved.");
                flag = false;
            }
        }while(flag);
        return result;
    }

    /**
     * This method is used to Display hall at the time of managing hall method.  
     */
    private boolean displayHallDetail(String newDescription,String newLocation,String newName,String newOwner, String words)
    {
        boolean flag = true;
        System.out.println("Hall Name: " + newName);
        System.out.println("Hall Location: " + newLocation);
        System.out.println("Hall Owner: " + newOwner);
        System.out.println("Hall Events type: " + newDescription);
        System.out.println("1. confirm");
        System.out.println("2. go back");
        System.out.println('\n');
        int choice = checkNumber(">Please input the number to choose",1,2);
        if(choice == 1)
        {
            System.out.println(words);
            flag = false;
        }
        return flag;
    }

    /**
     * This method is used to list total halls.  
     */
    private int listHalls(int index)
    {
        for(int i = 0; i < owners.get(index).getHalls().size(); i++)
        {
            System.out.println(i+1 + ". Hall Name: " + owners.get(index).getHalls().get(i).getHallName());
        }
        System.out.println(owners.get(index).getHalls().size()+1 + ". go back");
        int choice = checkNumber(">Please choose one to modify.",1,owners.get(index).getHalls().size()+1);
        if(choice<=owners.get(index).getHalls().size())
        {
            choice = choice - 1;
            System.out.println("Hall Name: " + owners.get(index).getHalls().get(choice).getHallName());
            System.out.println("Hall Location: " + owners.get(index).getHalls().get(choice).getHallLocation());
            System.out.println("Hall Owner: " + owners.get(index).getHalls().get(choice).getHallOwner());
            System.out.println("Hall Events type: " + owners.get(index).getHalls().get(choice).getHallDescription());
            System.out.println('\n');
        }
        else
            choice = -1;
        return choice;
    }

    /**
     * This method is used to provide validation for number entered.  
     */
    private int checkNumber(String words, int a, int b)
    {
        int number = 0;
        do
        {
            System.out.println(words);
            Scanner l = new Scanner(System.in);
            String length = l.nextLine();
            if(length.matches("\\d"))
                number = Integer.parseInt(length);
        } while(number < a|| number > b);
        return number;
    }

    /**
     * This method is used to provide feedback.  
     */
    public void UIFeedback()
    {
        String result = checkInput(">Do you want to write the feedback? (y/n)","y","n");
        if(result.equals("y"))
        {
            System.out.println('\n');
            boolean flag = true;
            String feedback = "";
            do
            {
                System.out.println(">Please enter your feed back.");
                Scanner enter = new Scanner(System.in);
                feedback = enter.nextLine();
                System.out.println('\n');
                String choice = checkInput(">Do you want to save the feedback? (y/n)","y","n");
                if(choice.equals("y"))
                {
                    Feedback newFeedback = new Feedback();
                    newFeedback.setFeedback(feedback); 
                    flag = false;
                }
            }while(flag);
            System.out.println('\n');
            System.out.println(">Your feedback has been saved.");
        }
        System.out.println(">Thank you for use Prime Event.");
    }

    /**
     * This method is used to check input validation.  
     */
    private String checkInput(String words,String a, String b)
    {
        String result = "";
        boolean flag = true;
        do
        {
            System.out.println(words);
            Scanner enter = new Scanner(System.in);
            result = enter.nextLine();
            if(result.trim().toLowerCase().equals(a) || result.trim().toLowerCase().equals(b))
                flag = false;
        }while(flag);
        return result;
    }

    private String checkInput2(String words,String a, String b,String c)
    {
        String result = "";
        boolean flag = true;
        do
        {
            System.out.println(words);
            Scanner enter = new Scanner(System.in);
            result = enter.nextLine();
            if(result.trim().toLowerCase().equals(a) || result.trim().toLowerCase().equals(b)|| result.trim().toLowerCase().equals(c))
                flag = false;
        }while(flag);
        return result;
    }

    /**
     * This method is used to display Admin menu and all th functionalities by calling method.  
     */
    public void adminMenu()
    {
        while(true)
        {
            //System.out.print('\u000C');
            ui.menuAdmin();

            //Scanner console = new Scanner(System.in);
            //System.out.print("Please enter your choice from above 1 to 4 ");
            String menuOption1 = console.nextLine();
            try{
                int menuOption = Integer.parseInt(menuOption1);
                if(menuOption == 1)
                {
                    System.out.println(" Press 1 for Suspend User ");
                    System.out.println(" Press 2 to Go Back");

                    int adminMenuOption = Integer.parseInt(console.nextLine());
                    if (adminMenuOption == 1)
                    {

                        System.out.println("-------------------");
                        System.out.println("    Suspend User   ");
                        System.out.println("-------------------");
                        System.out.println("                   ");
                        System.out.println("Work In Progress");
                        System.out.println("Press Enter to Continue");
                        console.nextLine();
                        System.out.print('\u000C');
                    }
                    else if (adminMenuOption == 2)
                        System.out.print('\u000C');

                    else 
                        System.out.println(" Please Enter Value 1 or 2");

                }else if (menuOption == 2)
                {
                    System.out.println(" Press 1 for Add Discount ");
                    System.out.println(" Press 2 for Remove Discount ");
                    System.out.println(" Press 3 to Go Back");

                    int adminMenuOption2 = Integer.parseInt(console.nextLine());
                    if (adminMenuOption2 == 1)
                    {

                        System.out.println("-------------------");
                        System.out.println("    Add Discount   ");
                        System.out.println("-------------------");
                        System.out.println("                   ");
                        System.out.println("Work In Progress");
                        System.out.println("Press Enter to Continue");
                        console.nextLine();
                        System.out.print('\u000C');
                    }else if (adminMenuOption2 == 2)
                    {

                        System.out.println("-------------------");
                        System.out.println("    Remove Discount   ");
                        System.out.println("-------------------");
                        System.out.println("                   ");
                        System.out.println("Work In Progress");
                        System.out.println("Press Enter to Continue");
                        console.nextLine();
                        System.out.print('\u000C');
                    }
                    else if (adminMenuOption2 == 3)
                        System.out.print('\u000C');

                    else 
                    {
                        System.out.print('\u000C');
                        System.out.println(" Please Enter Value between 1 to 3");
                    }

                }else if (menuOption == 3)
                {
                    searchHall(0);
                }
                else if (menuOption == 4)
                {
                    System.out.print('\u000C');
                    System.out.println("Logged out");
                    loggedUser = null;
                    home();
                }
            }catch(Exception e){
                System.out.print('\u000C');
                System.out.println("Please Enter integers only");
            }
        }
    }

    /**
     * This method is used to book hall with provided two functionalities.
     * 1.Request Quotation 2.Pay deposit & confirm booking. 
     */
    public void bookHall()
    {
        while (true)
        {
            System.out.print('\u000C');
            System.out.println("");
            System.out.println("1. Request Quotation                 ");
            System.out.println("2. Pay Deposit & Confirm Booking              ");
            System.out.println("3. Go Back ");
            System.out.print("Please enter your choice from above (1 to 3) ");
            FileIO fileIO = new FileIO();
            String bookOption1 = console.nextLine();
            try{
                int bookOption = Integer.parseInt(bookOption1);
                if(bookOption == 1)
                {

                    //System.out.println(" Please enter keyword ");
                    //search method will be calling here
                    //read hall file searching keyword
                    // ask for id of hall ex. 1
                    //read hall file again and check for id and get name
                    System.out.println("Note - Select the hall for which you want to send quotation using search option");
                    searchHall(1);

                }
                else if (bookOption == 2)
                {
                    String hallsConfirmed="";
                    String[] data = new String[10];
                    String[] content = new String[10];
                    FileReader file = new FileReader("sendQuotation.txt");
                    boolean flagAvailableBooking = false;
                    Scanner sca = new Scanner(file);
                    int index = 0;
                    int index1 = 0;
                    String data1[] = new String[10];
                    int payOption;
                    //String 
                    while(true)
                    {
                        System.out.println("Please press 1 & Pay your initial Amount to confirm Booking");
                        System.out.println("Please press 2 to Go Back");
                        payOption = Integer.parseInt(console.nextLine());
                        if(payOption == 1 || payOption == 2)
                            break;
                        else
                            System.out.println("Please press 1 or 2 only");
                    }
                    if (payOption == 1)
                    {
                        while (sca.hasNextLine())
                        {

                            data[index] = sca.nextLine();
                            content = data[index].split(",");
                            if (content[4].equals("true") && content[2].equals(loggedUser))
                            {
                                System.out.println(" Hall index   --> " + index1);
                                System.out.println(" Hall Name    --> " + content[0]);
                                System.out.println(" Hall Price   --> " + content[5]);
                                System.out.println(" Booking Date --> " + content[1]);
                                System.out.println("");
                                flagAvailableBooking = true;
                                data1[index1]=data[index];
                                index1++;
                            }
                            else
                            {
                                flagAvailableBooking = false;
                            }

                            index++;
                        }
                        if(index1 == 0)
                        {
                            System.out.print('\u000C');
                            System.out.print("No confirmed quotation Found!!");
                            break;
                        }
                    }
                    else if(payOption == 2)
                        System.out.print('\u000C');
                    else
                    {
                        System.out.print('\u000C');
                        System.out.println("Please check and select option again");
                    }

                    System.out.println("Please enter the hall number to pay initial amount and confirm booking");
                    System.out.println("");
                    System.out.println("Initial Amount is 20% of total amount");
                    int hallOption= Integer.parseInt(console.nextLine());;
                    while(true){
                        if(hallOption < data1.length)
                        {

                            fileIO.writeFile(data1[hallOption], "bookingEntry.txt");
                            System.out.println("CONGRATULATIONS!! YOUR BOOKING HAS BEEN CONFIRMED");
                            System.out.println("");
                            System.out.println("");
                            String tempBookingData[] = data1[hallOption].split(",");
                            System.out.println("---------------------Receipt-------------------------");
                            System.out.println("Hall Name     -"+tempBookingData[0]);
                            System.out.println("Book Date     -"+tempBookingData[1]);
                            System.out.println("Total Amount  -"+tempBookingData[5]);
                            System.out.println("Initial Amount (20% of total) has been paid");
                            System.out.println("-----------------------------------------------------");

                            break;
                        }
                        else
                        {
                            System.out.println("Please enter the correct option again");
                            hallOption= Integer.parseInt(console.nextLine());
                        }
                    }
                    
                }
                else if (bookOption == 3)
                {
                    System.out.print('\u000C');
                    break;
                }
                else 
                    System.out.println("Please Check Options and select it again");
            }catch(IOException e){
                System.out.println("Unable to read from file");
            } catch(Exception e){
                System.out.println("Please Enter an integer/Unknown ERROR try it again");
            }
            System.out.println("");
            System.out.println("Press Enter to Continue");
            console.nextLine();
        }
    }

    /**
     * This method is used to display Owner menu and all th functionalities by calling method.  
     */
    public void ownerMenu()
    {
        while(true){
            try{
                ui.menuOwner();
                int ownerOption = Integer.parseInt(console.nextLine());
                if(ownerOption == 1)
                {
                    System.out.println("-------------------");
                    System.out.println("  Manage Discount   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    System.out.println(" Press 1 for Add Discount ");
                    System.out.println(" Press 2 for Remove Discount ");
                    System.out.println(" Press 3 to Go Back");

                    int ownerOption2 = Integer.parseInt(console.nextLine());
                    if (ownerOption2 == 1)
                    {

                        System.out.println("-------------------");
                        System.out.println("    Add Discount   ");
                        System.out.println("-------------------");
                        System.out.println("                   ");
                        System.out.println("Work In Progress");
                        System.out.println("Press Enter to Continue");
                        console.nextLine();
                        System.out.print('\u000C');
                    }else if (ownerOption2 == 2)
                    {

                        System.out.println("-------------------");
                        System.out.println("  Remove Discount   ");
                        System.out.println("-------------------");
                        System.out.println("                   ");
                        System.out.println("Work In Progress");
                        System.out.println("Press Enter to Continue");
                        console.nextLine();
                        System.out.print('\u000C');
                    }
                    else if (ownerOption2 == 3)
                        System.out.print('\u000C');

                    else 
                    {
                        System.out.print('\u000C');
                        System.out.println(" Please Enter Value between 1 to 3");
                    }
                }
                else if(ownerOption == 2)
                {
                    System.out.println("-------------------");
                    System.out.println("  Manage Halls   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    int index = findUser(loggedUser);
                    manageHall(index);
                }
                else if(ownerOption == 3)
                {
                    System.out.println("-------------------");
                    System.out.println("  Manage Booking   ");
                    System.out.println("-------------------");
                    System.out.println(" Press 1 to check quotation and send it back to customer");
                    System.out.println(" Press 2 to Go back");
                    int optionManageBooking = Integer.parseInt(console.nextLine());
                    if(optionManageBooking == 1)
                    {
                        {
                            System.out.print('\u000C');
                            checkQuotation();
                        }
                    }
                    else if (optionManageBooking == 2)
                    {

                        System.out.print('\u000C');
                        ownerMenu();
                    }
                    else
                    {
                        System.out.print('\u000C');
                        System.out.println(" Please press 1 or 2 only");
                    }
                }
                else if(ownerOption == 4)
                {
                    System.out.println("-------------------");
                    System.out.println("  Manage Payment   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    System.out.println("Work In Progress");
                    System.out.println(" Press Enter to continue....");
                    console.nextLine();
                    System.out.print('\u000C');
                }
                else if(ownerOption == 5)
                {
                    System.out.print('\u000C');
                    System.out.print("Logged out");
                    loggedUser = null;
                    home();               
                }
                else
                {
                    System.out.print('\u000C');
                    System.out.println("Please Enter choice from between 1 to 5");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.print('\u000C');
                System.out.println("Please Enter choice from between 1 to 5");
            }
            catch (Exception e)
            {
                System.out.print('\u000C');
                System.out.println("Errors: " + e);
                System.out.println("Please check and try again");
            }
        }
    }

    /**
     * This method is used to check the requested quotation from the customers and send quotation back to customer.  
     */
    public void checkQuotation()
    {
        while(true)
        {
            try{
                System.out.println("Press 1 to check all the quotation request for halls");
                System.out.println("Press 2 to Go Back");
                int quotationAcceptOption = Integer.parseInt(console.nextLine());
                if(quotationAcceptOption == 1){
                    FileReader file = new FileReader("quotation.txt");
                    System.out.println(" Fetching Details");
                    Scanner sca = new Scanner(file);
                    String data = "";
                    String content[] = new String[10];
                    FileIO fileIO = new FileIO();
                    String myHall ="";
                    int i =1;
                    List<String> acceptedHalls = new ArrayList<>();
                    String hallToExcept = "";
                    while (sca.hasNextLine())
                    {
                        data = sca.nextLine();
                        content = data.split(",");
                        if(content[3].equals(loggedUser))
                        {
                            //System.out.println("Request "+ i + ". " + content[0]+);
                            System.out.println("Hall id               : " + i);
                            System.out.println("Hall Name             : " + content[0]);
                            System.out.println("Book date             : " + content[1]);
                            System.out.println("Customer Email ID     : " + content[2]);
                            System.out.println("");
                            hallToExcept = content[0] +","+ content[1] +","+ content[2] +","+content[3]+","+ "true" ;

                            acceptedHalls.add(hallToExcept);
                            i++;
                        }

                    }

                    int j = i-1;
                    if(acceptedHalls.size() == 0)
                    {
                        System.out.println(" No received quotation found!!");
                        break;
                    }
                    System.out.println("Please select to whom you want to send quotation. (Please Enter number from the given choices )");
                    int acceptOwner = Integer.parseInt(console.nextLine());
                    int num = acceptOwner-1;
                    String temp = acceptedHalls.get(num);
                    String tempData[] = temp.split(",");

                    System.out.println("Hall Name             : " + tempData[0]);
                    System.out.println("Book date             : " + tempData[1]);
                    System.out.println("Customer Email ID     : " + tempData[2]);
                    System.out.println("");
                    System.out.println("");

                    //System.out.println(temp);
                    System.out.println("Please Enter Amount: ");

                    double amount = Double.parseDouble(console.nextLine());
                    System.out.println(amount);

                    fileIO.writeFile(temp + "," + amount, "sendQuotation.txt");
                }else if(quotationAcceptOption ==2)
                {
                    System.out.print('\u000C');
                    break;
                }
                else
                {
                    System.out.print('\u000C');
                    System.out.println("Please Enter option 1 or 2 only");
                }
            }catch(Exception e)
            {
                System.out.print('\u000C');
                System.out.println("Error: "+e);
                System.out.println("Please try again");
            }
        }
    }

    /**
     * This method is used to display Customer menu and all th functionalities by calling method.  
     */
    public void customerMenu()
    {

        boolean isInteger = false;
        String userInput="";
        int customerOption = 0;
        do
        {
            try
            {
                ui.menuCustomer();

                userInput = console.nextLine();
                customerOption = Integer.parseInt(userInput);
                //isInteger = false;
                if(customerOption == 1)
                {
                    System.out.println("-------------------");
                    System.out.println("  View halls   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    viewHalls();
                    System.out.println("                   ");
                    System.out.println("Please Enter to continue");
                    console.nextLine();
                    System.out.print('\u000C');
                }
                else if(customerOption == 2)
                {
                    System.out.println("-------------------");
                    System.out.println("  Search   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    searchHall(0);

                }
                else if(customerOption == 3)
                {
                    System.out.println("-------------------");
                    System.out.println("  Book Hall   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    bookHall();
                }
                else if(customerOption == 4)
                {
                    System.out.println("-------------------");
                    System.out.println("  Manage a Booking   ");
                    System.out.println("-------------------");
                    System.out.println("                   ");
                    System.out.println(" Press 1 to add catering");
                    System.out.println("Work In Progress");
                    System.out.println(" Press Enter to continue....");
                    console.nextLine();

                }
                else if(customerOption == 5)
                {
                    System.out.println("-------------------");
                    System.out.println("  Write a review   ");
                    System.out.println("-------------------");
                    System.out.println("                      ");
                    UIFeedback();
                }
                else if(customerOption == 6)
                {
                    System.out.print('\u000C');
                    System.out.println("Logged out");
                    loggedUser = null;
                    home();
                }
                else
                {
                    System.out.print('\u000C');
                    System.out.println("Please Enter values between 1 to 6 only");
                }
            }
            catch (NumberFormatException e)
            {
                //isInteger = false;
                System.out.print('\u000C');
                System.out.println("Please Enter values between 1 to 6 only");
            }
            catch (Exception e)
            {
                //isInteger = false;
                System.out.print('\u000C');
                System.out.println("Error:"+e);
                System.out.println("Please check it and Try again");
            }

        }while (true);
    }

    /**
     * This method is used to display Home page and run all the functionalities using calling methods.  
     */
    public void home()
    {

        //our Text base UI design

        FileIO fileio = new FileIO();
        //boolean loop = true;
        //while(loop)
        //{
        boolean isInteger = false;
        String userInput="";
        int option = 0;
        do
        {
            ui.homePage();
            try
            {
                userInput = console.nextLine();
                option = Integer.parseInt(userInput);
                isInteger = true;

            }
            catch (Exception e)
            {
                isInteger = false;
            }
            finally
            {
                if (isInteger == false)
                {
                    System.out.print('\u000C');
                    System.out.println("\n" + "Please enter Integers between 1 to 3");
                    home();
                }
            }

        }
        while (!(isInteger == true));
        if (option == 1)
            login();
        else if (option == 2)
            register();
        else if (option == 3)
        { searchHall(0); 
            home();
        }

        else
        {
            System.out.print('\u000C');
            System.out.println("\n" + "Wrong option, Please try again");
            home();
        }
        //System.out.print('\u000C');
    }

    /**
     * This method is used to Search on the basis of name, location, event type and general search..  
     */
    public void searchHall(int z)
    {
        boolean flag = false;
        String length ="";
        //ArrayList<Hall> newHalls = new ArrayList<Hall>();

        while(true)
        {
            try{
                //boolean flag = false;
                //System.out.print('\u000C');
                System.out.println("Press 1 to General Search");
                System.out.println("Press 2 to search on the basis of HAll Name.");
                System.out.println("Press 3 to search on the basis of Location.");
                System.out.println("Press 4 to search on the basis of Event type/Description.");
                System.out.println("Press 5 to Go back");
                int searchOption = Integer.parseInt(console.nextLine());
                if(searchOption < 5 && searchOption >=1)
                {
                    do
                    {
                        System.out.println(">Please enter key word to search.");
                        Scanner l = new Scanner(System.in);
                        length = l.nextLine();
                        if(length.trim().isEmpty())
                            flag=true;
                        else
                            flag=false;
                    }while(flag);
                    ArrayList<Integer> index = new ArrayList<Integer>();
                    int num =0;
                    ArrayList<Hall> newHalls = new ArrayList<Hall>();
                    if(searchOption==1)
                    {
                        for(int i = 0; i<halls.size();i++)
                        {
                            if(halls.get(i).getHallName().toLowerCase().contains(length.toLowerCase()))
                            {    
                                newHalls.add(halls.get(i));
                                index.add(i);
                            }
                            else
                            {
                                if(halls.get(i).getHallOwner().toLowerCase().contains(length.toLowerCase()))
                                {    
                                    newHalls.add(halls.get(i));
                                    index.add(i);
                                }
                                else
                                {
                                    if(halls.get(i).getHallLocation().toLowerCase().contains(length.toLowerCase()))
                                    {    
                                        newHalls.add(halls.get(i));
                                        index.add(i);
                                    }
                                    else
                                    {
                                        if(halls.get(i).getHallDescription().toLowerCase(
                                        ).contains(length.toLowerCase()))
                                        {    
                                            newHalls.add(halls.get(i));
                                            index.add(i);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (searchOption == 2)
                    {
                        for(int i = 0; i<halls.size();i++)
                        {
                            if(halls.get(i).getHallName().toLowerCase().contains(length.toLowerCase()))
                            {    
                                newHalls.add(halls.get(i));
                                index.add(i);
                            }
                        }
                    }

                    else if (searchOption == 3)
                    {
                        for(int i = 0; i<halls.size();i++)
                        {
                            if(halls.get(i).getHallLocation().toLowerCase().contains(length.toLowerCase()))
                            {    
                                newHalls.add(halls.get(i));
                                index.add(i);
                            }
                        }
                    }

                    else if (searchOption == 4)
                    {
                        for(int i = 0; i<halls.size();i++)
                        {
                            if(halls.get(i).getHallDescription().toLowerCase().contains(length.toLowerCase()))
                            {    
                                newHalls.add(halls.get(i));
                                index.add(i);
                            }
                        }
                    }

                    if(newHalls.isEmpty())
                    {
                        System.out.print('\u000C');
                        System.out.println(">Result not found!");

                    }
                    else

                    {   //viewHalls(newHalls,0,index);
                        int i = 0;
                        boolean flag1 = true;
                        System.out.print('\u000C');
                        while(i<newHalls.size() && flag1)
                        {

                            System.out.println("Hall id         : " + i);
                            System.out.println("Hall Name       : " + newHalls.get(i).getHallName());
                            System.out.println("Hall Location   : " + newHalls.get(i).getHallLocation());
                            System.out.println("Hall Owner      : " + newHalls.get(i).getHallOwner());
                            System.out.println("Hall Events type: " + newHalls.get(i).getHallDescription());
                            System.out.println("\n");
                            i=i+1;
                            if(i%5==0)
                                flag1=false;
                        }
                    }

                    if (z == 1)
                    {
                        gettingHalls(newHalls,z);
                    }
                }

                else if (searchOption == 5)
                {
                    System.out.print('\u000C');
                    break;

                }
                else 
                    System.out.println("please enter appropriate option (1~5 only)");

            }catch(NumberFormatException e)
            {
                System.out.print('\u000C');
                System.out.println("Please Enter 1 to 5 only Try Again!");

            }
            catch(Exception e)
            {
                System.out.print('\u000C');
                System.out.println("ERROR: "+e);
                System.out.println("Please check and try again");

            }
        }

    }

    /**
     * This method is used to get halls after owner sends the quotation and pay initial amount and confirm booking.  
     */
    public String gettingHalls(ArrayList<Hall> newHalls, int z)
    {

        int i=0;
        boolean flag1 = true;
        String quotationData="";
        //ArrayList<Hall> resultHalls = new ArrayList<Hall>();
        //resultHalls = newHalls;
        //Booking newBook = new Booking();
        ArrayList<Booking> quotation = new ArrayList<Booking>();
        while(i<newHalls.size() && flag1)
        {
            // System.out.println(order+".");
            try{
                if (z == 1)
                {
                    System.out.println("please enter Hall id to send quotation from the above");
                    int quotationOption2 = Integer.parseInt(console.nextLine());
                    int index = quotationOption2;

                    //quotation.sadd(newHalls.get(index).getHallName());
                    String date;
                    Date dateToBookHall =null;
                    String dateToBook;
                    while(true)
                    {
                        System.out.println("Please Enter a Date");
                        System.out.println("Enter the Date in format dd/MM/yyyy(ex. 01/01/2001) ");
                        date = console.next();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Date date1 = new Date();

                        dateToBookHall = dateFormat.parse(date);
                        dateToBook = dateFormat.format(dateToBookHall);
                        System.out.println("Entered Date: "+dateToBook);
                        System.out.println("Today's Date: "+dateFormat.format(date1));

                        if(dateToBookHall.after(date1))
                            break;
                        else
                            System.out.println("Please Enter date in future after "+dateFormat.format(date1));
                    }
                    console.nextLine();
                    Booking newBook = new Booking(newHalls.get(index).getHallName(), dateToBook, loggedUser, newHalls.get(index).getHallOwner());
                    quotationData = newHalls.get(index).getHallName() +","+ dateToBook+","+loggedUser+","+ newHalls.get(index).getHallOwner()+","+ "false";

                    System.out.println("Press 1 to confirm & send Quotation");
                    System.out.println("Press 2 to Go back");
                    int quotationOption = Integer.parseInt(console.nextLine());
                    if(quotationOption == 1)
                    {
                        System.out.println("Sending Quotation.......");

                        FileIO fileIO = new FileIO();
                        fileIO.writeFile(quotationData,"quotation.txt");

                        System.out.println("Quotation has been sent");
                        customerMenu();
                    }
                    else if (quotationOption ==2)
                        bookHall();
                    else
                        System.out.println("Press Press 1 or 2");
                }
            }catch (ParseException e) {

                System.out.println("Error: " +e);
                System.out.println("Please try again");
            }catch(Exception e)
            {
                System.out.println("Error: " +e);
                System.out.println("Please try again");
            }
        }
        return quotationData;
    }

    /**
     * This method is used to set all halls by passing into constructor.  
     */
    private void setAllHalls()
    {
        FileIO file = new FileIO();
        String content = file.readHallFile("hall.txt");
        String[] lines = content.split("\n");
        ArrayList<Hall> newHalls = new ArrayList<Hall>();
        for(String line: lines)
        {
            String[] words = line.split(";");
            Hall hall = new Hall(words[0], words[1],words[2],words[3], Integer.parseInt(words[4]));
            newHalls.add(hall);
        }
        halls = newHalls;
    }

    /**
     * This method is used to set all owners by passing into constructor.  
     */
    private void setAllOwners()
    {
        FileIO file = new FileIO();
        String content = file.readHallFile("ownerDetails.txt");
        String[] lines = content.split("\n");
        ArrayList<Owner> newOwners = new ArrayList<Owner>();
        for(String line: lines)
        {
            String[] words = line.split(",");
            Owner newOwner = new Owner(Integer.parseInt(words[0]),words[1], words[2],words[3],words[4],words[5],words[6],words[7] );
            newOwners.add(newOwner);
        }
        owners = newOwners;
    }

    /**
     * This method is used to set all Customers by passing into constructor.  
     */
    private void setAllCustomers()
    {
        FileIO file = new FileIO();
        String content = file.readHallFile("customerDetails.txt");
        String[] lines = content.split("\n");
        ArrayList<Customer> newCustomers = new ArrayList<Customer>();
        for(String line: lines)
        {
            String[] words = line.split(",");
            Customer newCustomer = new Customer(Integer.parseInt(words[0]),words[1], words[2],words[3],words[4],words[5],words[6],words[7] );
            newCustomers.add(newCustomer);
        }
        customers = newCustomers;
    }

    /**
     * This method is used to view all halls by using getter methods.  
     */
    public void viewHalls()
    {
        int i =0;

        while(i<halls.size())
        {
            System.out.println(i+1+".");
            System.out.println("Hall Name       : " + halls.get(i).getHallName());
            System.out.println("Hall Location   : " + halls.get(i).getHallLocation());
            System.out.println("Hall Owner      : " + halls.get(i).getHallOwner());
            System.out.println("Hall Events type: " + halls.get(i).getHallDescription());
            i=i+1;

        }
    }

    /**
     * This method is used to set all halls with given list and index using getter method.  
     */
    private void viewHalls(ArrayList<Hall> hall,int i)
    {
        String options = "";
        boolean flag = true;
        int result = -1;
        while(i<hall.size() && flag)
        {
            System.out.println(i+1+".");
            System.out.println("Hall Name: " + hall.get(i).getHallName());
            System.out.println("Hall Location: " + hall.get(i).getHallLocation());
            System.out.println("Hall Owner: " + hall.get(i).getHallOwner());
            System.out.println("Hall Events type: " + hall.get(i).getHallDescription());
            i=i+1;

            if(i%5==0)
                flag=false;
        }
        System.out.println('\n');
        options = checkInput2(">Next Page please press n,Privous Page please press p, Go back please press b","n","p","b");
        if(options.equals("n"))
        {
            if(i>=hall.size())
            {
                System.out.println("This is the last page.");
                result = 0;
            }
            else
                viewHalls(hall,i);
        }
        else
        {
            if(options.equals("p"))
            {
                if(i>5)
                {
                    viewHalls(hall,i-6);
                }
                else
                {
                    System.out.println("This is the first page.");
                    result = 0;
                }
            }
            else
            if(options.equals("b"))
                result = 0;
        }

    }

    /**
     * This method is used to find logged user.  
     */
    private int findUser(String loggedOwner)
    {
        int index = 0;
        for(int i =0; i<owners.size();i++)
        {
            if(owners.get(i).getOwnerEmail().equals(loggedOwner))
            {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * This method is used to do login with all different options.  
     */
    public void login()
    {
        FileIO fileio = new FileIO();
        try
        {
            String loginOption = "";
            int LoginOption=0;
            boolean isInteger = false;
            do
            {
                //System.out.print('\u000C');
                System.out.println(" ************************************** ");
                System.out.println(" Welcome to Prime Events ");
                System.out.println(" ************************************** ");

                System.out.println(" _________________________________________ ");
                System.out.println(" Please Press 1 For Administrator Login ");
                System.out.println(" Please Press 2 For Owner Login ");
                System.out.println(" Please Press 3 For Customer Login ");
                System.out.println(" Please Press 4 to Go Back ");

                System.out.println(" _________________________________________ ");

                try
                {
                    loginOption = console.nextLine();
                    LoginOption = Integer.parseInt(loginOption);
                    isInteger = true;
                }
                catch (Exception e)
                {
                    isInteger = false;
                }
                finally
                {
                    if (isInteger == false)
                    {
                        System.out.print('\u000C');
                        System.out.println("\n" + "Please enter Integers 1 to 4 only");
                        login();
                    }
                }
                if (LoginOption < 1 || LoginOption > 4)
                {
                    System.out.print('\u000C');
                    System.out.println("\n" + "Wrong option, Please try again");
                    login();
                }

            }
            while (!(isInteger == true));

            if(LoginOption >= 1 && LoginOption <= 3)
            {
                System.out.print('\u000C');
                System.out.println(" ************************************** ");
                System.out.println(" Hello! Please Enter your login details ");
                System.out.println(" ************************************** ");
                System.out.println("  ");
                System.out.println("  ");
                //console.nextLine();
                System.out.print(" Note: Username is your EmailID ");
                System.out.println("  ");
                System.out.println("  ");
                System.out.print(" Username: ");
                String user = console.nextLine();
                System.out.print(" Password: ");
                String pass = console.nextLine();
                System.out.println(" ************************************** ");
                if(LoginOption == 1){
                    String content[] = fileio.readUserFile("adminDetails.txt",user,pass);

                    //String[] content = data.split(",");
                    System.out.println(content[1] + " " + content[2] + "read data");
                    System.out.println(user + pass + "entered data");
                    if (content[1].equals(user) && content[2].equals(pass))
                    {
                        System.out.print('\u000C');
                        System.out.println("Hello " + content[3] + " " + content[4] + "! welcome to prime events");
                        loggedUser = user;
                        //System.out.println(loggedUser);
                        //System.out.print('\u000C');
                        adminMenu();
                    }
                    else
                    {
                        System.out.print('\u000C');
                        System.out.println("\n" + "You have entered incorrect login credential");
                        System.out.println("\n" + "Please login again.." + "\n" +
                            " If you are not registered in the Prime Events then Please Register!!" + "\n");
                        //home();
                        forgotPassword(1);
                    }
                }
                else if(LoginOption == 2)
                {
                    String content[] = fileio.readUserFile("ownerDetails.txt",user,pass);

                    //String[] content = data.split(",");
                    System.out.println(content[1] + content[2] + "read data");
                    System.out.println(user + pass + "entered data");
                    if (content[1].equals(user) && content[2].equals(pass))
                    {
                        System.out.print('\u000C');
                        System.out.println("Hello " + content[3] + " " + content[4] + "! welcome to prime events");
                        loggedUser = user;

                        //System.out.println(loggedUser);
                        //System.out.print('\u000C');
                        ownerMenu();
                    }
                    else
                    {
                        System.out.print('\u000C');
                        System.out.println("\n" + "You have entered incorrect login credential");
                        System.out.println("\n" + "Please login again.." + "\n" +
                            " If you are not registered in the Prime Events then Please Register!!" + "\n");
                        forgotPassword(2);    
                        // home();
                    }

                }
                else if(LoginOption == 3)
                {
                    String content[] = fileio.readUserFile("customerDetails.txt",user,pass);

                    //String[] content = data.split(",");
                    System.out.println(content[1] + content[2] + "read data");
                    System.out.println(user + pass + "entered data");
                    if (content[1].equals(user) && content[2].equals(pass))
                    {
                        System.out.print('\u000C');
                        System.out.println("Hello " + content[3] + " " + content[4] + "! welcome to prime events");
                        loggedUser = user;
                        //System.out.println(loggedUser);
                        //System.out.print('\u000C');
                        customerMenu();
                    }
                    else
                    {
                        System.out.print('\u000C');
                        System.out.println("\n" + "You have entered incorrect login credential");
                        System.out.println("\n" + "Please login again.." + "\n" +
                            " If you are not registered in the Prime Events then Please Register!!" + "\n");
                        forgotPassword(3); 
                        //home();
                    }
                }
                else
                {
                    System.out.print('\u000C');
                    System.out.println("\n" + "Wrong option, Please try again");
                    login();
                }
            }
            else if(LoginOption == 4 )
            {
                System.out.print('\u000C');
                home();
            }

            else
            {
                System.out.print('\u000C');
                System.out.println("\n" + "Wrong option, Please try again");
                login();
            }
        }
        catch(Exception e){
            System.out.println("Please Enter an integer/Unknown ERROR try it again");
            //login();
        }
    }

    /**
     * This method is used to check if user forget password and then reset password using resetPassword method.  
     */
    public void forgotPassword(int userType)
    {
        FileIO fileio = new FileIO();
        String userInput = "";
        int option=0;
        String sq="";
        boolean isInteger = false;
        String ans="";
        String file ="";
        do
        {
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println(" #              PRIME EVENTS             # ");
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println("                                           ");
            System.out.println(" _________________________________________ ");
            System.out.println(" Please Press 1 For Forgot password");
            System.out.println(" Please Press 2 For Home Page");
            System.out.println(" ----------------------------------------- ");
            System.out.println("                                           ");
            try
            {
                userInput = console.nextLine();
                option = Integer.parseInt(userInput);
                isInteger = true;
            }
            catch (Exception e)
            {
                isInteger = false;
            }
            finally
            {
                if (isInteger == false)
                {
                    System.out.print('\u000C');
                    System.out.println("\n" + "Please enter Integers 1 or 2 or 3 only");
                    home();
                }
            }

        }
        while (!(isInteger == true));
        if (option == 1 )
        {
            //write the flow of the program here
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println(" #              PRIME EVENTS             # ");
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println("                                           ");
            System.out.println(" _________________________________________ ");
            System.out.print(" Username: ");
            String user = console.nextLine();
            if (userType == 1)
            {
                file = "adminDetails.txt";

            }
            else if (userType == 2)
            {
                file = "ownerDetails.txt";
            }

            else
            {
                file = "customerDetails.txt";
            }
            String content[] = fileio.readFileForUserValidations(file,user);

            if (content[1].equals(user))
            {
                System.out.print('\u000C');
                System.out.println("Please answer the security question to get back to your account" + "\n");
                System.out.println(content[5] + " ?");
                ans = console.nextLine();
                content = fileio.readFileForPasswordValidations(file,user,ans);
                if (content[1].equals(user) && content[6].equals(ans))
                {

                    loggedUser=user;
                    int index = findUser(loggedUser);
                    resetPassword(index, userType);
                    login();
                }

                else
                {    System.out.print('\u000C');
                    System.out.println("\n" + "Details entered are incorrect");
                    System.out.println("\n" + "Please login again.." + "\n" +
                        " If you are not registered in the Prime Events then Please Register!!" + "\n");
                    home(); 
                }
            }
            else
            {
                System.out.print('\u000C');
                System.out.println("\n" + "Details entered are incorrect");
                System.out.println("\n" + "Please login again.." + "\n" +
                    " If you are not registered in the Prime Events then Please Register!!" + "\n");
                home(); 
            }
            // customerMenu();

        }else if (option == 2)
        {
            System.out.print('\u000C');
            home();
        }
        else
        {
            System.out.print('\u000C');
            System.out.println("Wrong option, redirecting to the home page ");
            home();
        }

    }

    /**
     * This method is used to reset password by answering security question.  
     */
    public void resetPassword(int index, int userType)
    {

        //boolean flag = true;
        System.out.println("========Reset Password========");
        //int choice = listHalls(index);
        //int result = findHall(index,choice);
        if (userType == 2)
        {
            System.out.println("Your Email: " + owners.get(index).getOwnerEmail());
        }
        else if (userType ==3)
        {
            System.out.println("Your Email: " + customers.get(index).getCustomerEmail());
        }

        String pass = passwordValidation();
        System.out.println("\n");
        System.out.print(" Retype Password: ");

        String pass1 = console.nextLine();
        while (!pass.equals(pass1))
        {
            System.out.println("The password doesnt match");                  
            pass = passwordValidation();
            System.out.println("\n");
            System.out.print(" Retype Password: ");
            pass1 = console.nextLine(); 
        }
        if (userType == 2)
        {
            owners.get(index).setOwnerPassword(pass1);
            System.out.println("Password has been reset successfully ");
            rewriteOwners();
        }
        else if (userType ==3)
        {
            customers.get(index).setCustomerPassword(pass1);
            System.out.println("Password has been reset successfully ");
            rewriteCustomers();
        }
        System.out.println("Please login again");

    }

    /**
     * This method is used to register owner or customer in the prime event application.  
     */
    public void register()
    {
        String regOption = "";
        int RegOption=0;
        boolean isInteger = false;
        do
        {
            //System.out.print('\u000C');
            System.out.println(" ************************************** ");
            System.out.println("  Hello! Welcome to Prime Events ");
            System.out.println(" ************************************** ");
            System.out.println("  ");
            System.out.println("  ");
            System.out.println(" _________________________________________ ");
            System.out.println(" Please Press 1 For Owner Registration ");
            System.out.println(" Please Press 2 For Customer Registration ");
            System.out.println(" Please Press 3 to Go Back");
            System.out.println(" _________________________________________ ");

            try
            {
                regOption = console.nextLine();
                RegOption = Integer.parseInt(regOption);
                isInteger = true;
            }
            catch (Exception e)
            {
                isInteger = false;
            }
            finally
            {
                if (isInteger == false)
                {
                    System.out.print('\u000C');
                    System.out.println("\n" + "Please enter Integers 1 to 3 only");
                    register();
                }
            }

        }
        while (!(isInteger == true));

        if (RegOption == 1)
        {
            System.out.print('\u000C');
            registerUser(1);

        }
        else if (RegOption == 2)
        {
            System.out.print('\u000C');
            registerUser(2);

        }else if (RegOption == 3)
        {
            System.out.print('\u000C');
            home();

        }

        else
        {
            System.out.print('\u000C');
            System.out.println("Wrong option, Please try again"); 
            register();
        }
    }

    /**
     * This method is used to check whether email id exists or not if it does then ask again new email id.  
     */
    public boolean findSameEmail(String input)
    {
        boolean result = false;
        if(admin.getEmail().equals(input))
            result = true;
        for(int i = 0; i<customers.size();i++)
        {
            if(customers.get(i).getCustomerEmail().equals(input))
            {
                result = true;

            }
        }
        for(int i = 0; i<owners.size();i++)
        {
            if(owners.get(i).getOwnerEmail().equals(input))
            {
                result = true;

            }
        }
        return result;
    }

    /**
     * This method is used to register by asking all the details and validations.  
     */
    public void registerUser(int userType)
    {
        try
        {System.out.println(" Please Enter your details to register ");
            System.out.println(" ------------------------------------- ");
            System.out.println("                                       ");
            //console.nextLine();
            boolean nullFlag = true;
            boolean nullFlag1 = true;
            String fname="";
            String sname="";
            while (nullFlag)
            {
                System.out.print(" Enter First Name: ");
                fname = console.nextLine();
                if (!fname.trim().equals(""))
                {
                    nullFlag = false; 
                }
                else
                    System.out.println("(No input provided) Please Enter First Name ");
            }

            while (nullFlag1)
            {
                System.out.print(" Enter Last Name: ");
                sname = console.nextLine();
                if (!sname.trim().equals(""))
                {
                    nullFlag1 = false; 
                }
                else
                    System.out.println("(No input provided) Please Enter Last Name ");
            }
            System.out.print(" Enter Email ID: ");
            String email = console.nextLine(); 
            while (!(email.contains("@") && email.contains(".")))
            {

                System.out.println("\n");
                System.out.println("Please enter the Email Id in the format xxx@example.com");
                System.out.print(" Enter Email ID: ");
                email = console.nextLine();

            }
            while(findSameEmail(email))
            {

                System.out.println("The Email ID has existed, please enter another one");
                System.out.print(" Enter Email ID: ");
                email = console.nextLine();

            }
            //Display message regarding the password validations

            String pass = passwordValidation();
            System.out.println("\n");
            System.out.print(" Retype Password: ");

            String pass1 = console.nextLine();
            while (!pass.equals(pass1))
            {
                System.out.println("The password doesnt match");                  
                pass = passwordValidation();
                System.out.println("\n");
                System.out.print(" Retype Password: ");
                pass1 = console.nextLine(); 
            }

            System.out.println("\n" + " Select a security question" + "\n");
            System.out.println("\n" + " 1. Favourite color" );
            System.out.println("\n" + " 2. Place of birth" );
            System.out.println("\n" + " 3. Best Friend" );
            System.out.print("\n" + "Enter 1 or 2 or 3 ");
            String sq = console.nextLine();
            String quest="";
            String ans="";
            while(!((sq.equals("1")) || (sq.equals("2")) || (sq.equals("3"))))
            {

                System.out.println("\n" + " Please enter 1 or 2 or 3 only ");
                System.out.println("\n" + " Select a security question" + "\n");
                System.out.println("\n" + " 1. Favourite color" );
                System.out.println("\n" + " 2. Place of birth" );
                System.out.println("\n" + " 3. Best Friend" );
                System.out.print("\n" + "Enter 1 or 2 or 3 ");
                sq = console.nextLine();
            }
            if (sq.equals("1"))
            {
                quest="Favourite color";
            }
            else if (sq.equals("2"))
            {
                quest="Place of birth ";
            }
            else if (sq.equals("3"))
            {
                quest="Best Friend";
            }

            System.out.print("\n" + "Please provide your answer for " + quest + " : ");
            ans = console.nextLine();
            System.out.print("\n" + "Enter your contact number : ");
            String phone = console.nextLine();
            if (userType == 2)
            {
                int ID = customers.size()+1;
                Customer newCust = new Customer(ID,email,pass,fname,sname,quest,ans,phone);
                newCustomer=newCust;
                customers.add(newCustomer);
                writeToCustomerFile();
            }
            else if (userType == 1)
            {
                int ID= owners.size()+1;
                Owner newOwn = new Owner(ID,email,pass,fname,sname,quest,ans,phone);
                newOwner=newOwn;
                owners.add(newOwner);
                writeToOwnerFile();
            }

            System.out.print('\u000C');
            System.out.println(" ************************************** ");
            System.out.println("  Hello! Welcome to Prime Events ");
            System.out.println(" ************************************** ");
            System.out.println("  ");
            System.out.println("\n" + "Congratulations you have been registered");
            System.out.println("\n");
            login();
            /*
            System.out.println(" ************************************** ");
            System.out.println(" Hello! Please Enter your login details ");
            System.out.println(" ************************************** ");
            System.out.println("  ");
            System.out.println("  ");
            //console.nextLine();
            System.out.print(" Username: ");
            String user = console.nextLine();
            System.out.print(" Password: ");

            String password = console.nextLine();

            System.out.println(" ************************************** ");
            //give validations from and to =---textfile
            //validateOwner(user,password);
            System.out.print('\u000C');
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println(" #              PRIME EVENTS             # ");
            System.out.println(" #=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=# ");
            System.out.println("                                           ");
            System.out.println(" ************************************** ");
            //give validations from and to =---textfile
            System.out.print('\u000C');*/
        }catch(Exception e){
            System.out.println("Please Enter an integer/Unknown ERROR try it again");

        }
    }

    /**
     * This method is used to write details into customer file.  
     */
    public void writeToCustomerFile()
    {
        String fileContent = "";
        FileIO io = new FileIO();
        String details[]=io.readFile("customerDetails.txt");

        int userID = Integer.parseInt(details[0]);
        userID++;

        fileContent=  userID + "," + newCustomer.getUserEmail() + "," + newCustomer.getUserPassword() + "," +
        newCustomer.getFname() + ","+ newCustomer.getSname()+ "," + newCustomer.getUserSecurityQuestion() + "," +
        newCustomer.getUserSecurityAnswer() + "," + newCustomer.getContactNumber();
        io.writeFile(fileContent,"customerDetails.txt");
    }

    /**
     * This method is used to write details into Owner file.  
     */
    public void writeToOwnerFile()
    {
        String fileContent = "";
        FileIO io = new FileIO();
        String details[] =io.readFile("ownerDetails.txt");

        int userID = Integer.parseInt(details[0]);
        userID++;

        fileContent=  userID + "," + newOwner.getUserEmail() + "," + newOwner.getUserPassword() + "," +
        newOwner.getFname() + ","+ newOwner.getSname()+ "," + newOwner.getUserSecurityQuestion() + "," +
        newOwner.getUserSecurityAnswer() + "," + newOwner.getContactNumber();
        io.writeFile(fileContent,"ownerDetails.txt");
    }

    /**
     * This method is used to validate input whether it is integer or not.  
     */
    private void checkForInteger(boolean isInteger)
    {
        if (isInteger == false)
        {
            System.out.println("\n" + "Please enter an integer value only ");
        }
    }

    /**
     * This method is used to check password validation by calling method.  
     */
    public String passwordValidation()
    {

        System.out.println("Please note : 1. Password must have atleast 8 characters");
        System.out.println("              2. Password must have atleast 1 digit     ");
        System.out.println("              3. Password must have atleast 1 upper case letter and lower case letter");
        System.out.println("              4. Password must have atleast 1 special  character");
        System.out.println("\n");
        System.out.print("Enter Password : ");
        String pass = console.nextLine();
        boolean flag = passwordAdditionalValidation(pass);

        while (flag == false)
        {
            System.out.println("\n");

            System.out.println("Please note : 1. Password must have atleast 8 characters");
            System.out.println("              2. Password must have atleast 1 digit     ");
            System.out.println("              3. Password must have atleast 1 upper case letter and lower case letter");
            System.out.println("              4. Password must have atleast 1 special  character");
            System.out.print(" Enter Password: ");
            pass = console.nextLine();
            flag = passwordAdditionalValidation(pass);
        }

        return pass;  
    }

    /**
     * This method is used to check password validation.  
     */
    public boolean passwordAdditionalValidation(String pass)
    {
        boolean flag = true;
        int lowCount = 0;
        int digCount = 0;
        int uppCount = 0;
        int totalCount = 0;
        for (int i = 0; i < pass.length(); i++) 
        {

            char passChar = pass.charAt(i);

            if (passChar >= '0' && passChar <= '9') 
                digCount++;
            if (passChar >= 'A' && passChar <= 'Z') 
                uppCount++;   
            if (passChar >= 'a' && passChar <= 'z') 
                lowCount++;  

        }  
        totalCount = uppCount + digCount + lowCount;
        if(totalCount == pass.length())
        {
            System.out.println("The password must be atleast 8 character long ");
            flag = false;
        }
        if(uppCount==0)
        {
            System.out.println("The password must have atleast one upper case letter "); 
            flag = false;
        }
        if(lowCount==0)
        {
            System.out.println("The password must have atleast one lower case letter ");
            flag = false;
        }
        if(digCount==0)
        {
            System.out.println("The password must contain atleast 1 digit ");
            flag = false;
        }
        if(totalCount == pass.length())
        {
            System.out.println("The password must contain atleast one special character");
            flag = false;
        }
        return flag;
    } 

    /**
     * This method is our main UI which includes everything.  
     */
    public void UI()
    {
        System.out.print('\u000C');
        home();
    }
}
