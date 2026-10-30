package library.app;

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
        Scanner scanner = new Scanner(new File("tests/01_basic.txt"));
        Library library = new Library();
        while(scanner.hasNext()){
            
            String line = scanner.nextLine();
            try {
                String output = library.execute(line);
                System.out.println(output);
            } catch (LibraryException e) {
                e.printStackTrace();
            }
        }
    }
}
