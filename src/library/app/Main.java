package library.app;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import library.model.LibraryException;

/**
 * TODO: read the script file named in args[0], run each command,
 * and print the results. See "Main" in README.md.
 * Do NOT call System.exit.
 */
public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<File> filelist = new ArrayList<File>();
        filelist.add(new File("tests/01_basic.txt"));
        filelist.add(new File("tests/02_late_fees.txt"));
        filelist.add(new File("tests/03_errors.txt"));
        filelist.add(new File("tests/04_report.txt"));
        filelist.add(new File("tests/05_people_and_clock.txt"));
        filelist.add(new File("tests/does_not_exist.txt"));

        //for(File file: filelist) {
            try{
                Scanner scanner = new Scanner(new File("tests/does_not_exist.txt"));
                Library library = new Library();
                while(scanner.hasNext()){
                    String line = scanner.nextLine();
                    if(line.charAt(0) == '#') line = scanner.nextLine();

                    try {
                        String output = library.execute(line);
                        System.out.println(output);
                    } 
                    catch (LibraryException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch(FileNotFoundException e){
                System.out.println("Error: could not open script file tests/does_not_exist.txt.");
            }
            finally{
            }
        //}
    }
}
