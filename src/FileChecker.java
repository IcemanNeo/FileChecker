import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File checkFile;

        //Check for File Name and Validation
        while(true) {
            System.out.print("Please type the Directory & File Name.. [Example: 'C:\\Documents\\myFile.txt']: ");
            String fileName = sc.next();
            checkFile = new File(fileName);

            //Check for Validation
            if(checkFile.exists()) {
                break;
            } else {
                System.out.println("File doesn't exist...");
            }
        }

        boolean done = false;

        while(!done) {
            //Get the Character to check for occurrences of.
            System.out.print("What is the letter to check? (CASE SENSITIVE): ");
            char letter = sc.next().charAt(0);

            StringBuilder content = getFileContents(checkFile);
            int amountOccur = getOccurrences(letter, content);

            System.out.println("The Amount of Occurrences of " + letter + ": " + amountOccur);

            //Check another letter for occurrences
            while(!done) {
                System.out.print("Would you like to try another letter? [Y/N]:");
                if(sc.next().equalsIgnoreCase("y")) {
                    break;
                } else {
                    done = true;
                }
            }
        }
    }

    private static StringBuilder getFileContents(File fCheck) {
        StringBuilder storage = new StringBuilder();

        try {
            //Put file data into a buffer, then read each line below
            BufferedReader input =  new BufferedReader(new FileReader(fCheck));

            try {
                String line; //Hold each line until append

                //Get all the lines of the file and store it into a StringBuilder
                while((line = input.readLine()) != null){
                    storage.append(line);
                    storage.append(System.getProperty("line.separator")); //Set a new line
                }
            }
            finally {
                input.close(); //Close the Buffered Stream
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return storage;
    }

    private static int getOccurrences(char letter, StringBuilder contents) {
        char[] myLetters = contents.toString().toCharArray();
        int count = 0;

        //Count Occurrences of Letter
        for(char charA: myLetters) {
            if(charA == letter) {
                count++;
            }
        }

        return count;
    }
}