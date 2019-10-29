import java.io.*;
import java.util.*;

/**
 * FileIO class read from the file and write into files.
 *
 * @author Shrey
 * @version 20/10/2019
 */
public class FileIO
{
    // instance variables - replace the example below with your own

    /**
     * Default Constructor for objects of class FileIO
     */
    public FileIO()
    {
        // initialise instance variables

    }

    /**
     * read file content which is defined in the argument.
     */
    public String[] readFile(String file)
    {
        StringBuffer buffer = new StringBuffer();
        FileReader filename = null;
        String[] eachuserDetail = new String[10];
        try
        {
            filename = new FileReader(file);
            try
            {

                Scanner readFileDetails = new Scanner(filename);
                while (readFileDetails.hasNextLine())
                {
                    String userDetail = readFileDetails.nextLine();
                    eachuserDetail = userDetail.split(",");
                }

            }
            finally
            {
                filename.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected IO Error has occured");
        }
        return  eachuserDetail;
    }

    /**
     * Read content from the file by passing file user and pass parameters
     */
    public String[] readUserFile(String file, String user, String pass)
    {
        StringBuffer buffer = new StringBuffer();
        FileReader filename = null;
        String[] eachuserDetail = new String[10];
        try
        {
            filename = new FileReader(file);
            try
            {

                Scanner readFileDetails = new Scanner(filename);
                while (readFileDetails.hasNextLine())
                {
                    String userDetail = readFileDetails.nextLine();
                    eachuserDetail = userDetail.split(",");
                    if (eachuserDetail[1].equals(user) && eachuserDetail[2].equals(pass))
                    {
                        break;
                    }
                }

            }
            finally
            {
                filename.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected IO Error has occured");
        }
        return  eachuserDetail;
    }

    /**
     * This method is used to read file and check validations.  
     */
    public String[] readFileForUserValidations(String file, String user)
    {
        StringBuffer buffer = new StringBuffer();
        FileReader filename = null;
        String[] eachuserDetail = new String[10];

        //String ans="";
        try
        {
            filename = new FileReader(file);
            try
            {

                Scanner readFileDetails = new Scanner(filename);
                while (readFileDetails.hasNextLine())
                {
                    String userDetail = readFileDetails.nextLine();
                    eachuserDetail = userDetail.split(",");
                    if (eachuserDetail[1].equals(user))
                        break;
                    ///else if (eachuserDetail[1].equals(user))
                    // break;
                }

            }
            finally
            {
                filename.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected IO Error has occured");
        }
        return  eachuserDetail;
    }

    /**
     * This method is used to read password and check validations.  
     */
    public String[] readFileForPasswordValidations(String file, String user, String ans)
    {
        StringBuffer buffer = new StringBuffer();
        FileReader filename = null;
        String[] eachuserDetail = new String[10];

        //String ans="";
        try
        {
            filename = new FileReader(file);
            try
            {

                Scanner readFileDetails = new Scanner(filename);
                while (readFileDetails.hasNextLine())
                {
                    String userDetail = readFileDetails.nextLine();
                    eachuserDetail = userDetail.split(",");
                    if (eachuserDetail[1].equals(user) && ans.equals(eachuserDetail[6]))
                        break;

                }

            }
            finally
            {
                filename.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected IO Error has occured");
        }
        return  eachuserDetail;
    }

    /**
     * This method is used to read hall from file.  
     */
    public String readHallFile(String fileName)
    {
        // put your code here
        String data1 = "";
        int i=1;
        try{
            FileReader file = new FileReader(fileName);
            //BufferedReader reader = new BufferedReader(file);
            Scanner sca = new Scanner(file);
            while(sca.hasNextLine()){
                String line = sca.nextLine();
                //System.out.println(i + " " + line);
                data1 += line + '\n';
                i++;
            }
        }catch(Exception e){
            System.out.println("read failed");
        }
        return data1;
    }

    /**
     * This method is used to write hall into file.  
     */
    public void writeHallFile(String words,String FileName)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(FileName);
            String[] lines = words.split("\n");
            for(String line : lines)
                writer.println(line);
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error!");
        }
    }

    /**
     * This method is used to write into file using data and file name as arguments.  
     */
    public void writeFile(String newOutput, String fileName)
    {
        File file = new File(fileName);
        BufferedWriter buffer = null;
        try 
        {
            FileWriter fileWriter = new FileWriter(file,true);
            buffer = new BufferedWriter(fileWriter);
            buffer.write(newOutput);
            buffer.newLine();
            buffer.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        }

        catch (IOException e) 
        {
            System.out.println("File cannot be read");
        }

        finally
        {
            try
            {
                buffer.close();
            }

            catch (IOException e)
            {
                System.out.println("Cannot close file");
            }

            catch(NullPointerException e)
            {

            }
        }
    }
}
