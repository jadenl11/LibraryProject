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
        File file = new File("01_basic.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){
            Library library = new Library();
            
            String line = scanner.nextLine();
            try {
                library.execute(line);
            } catch (LibraryException e) {
                e.printStackTrace();
            }
        }
    }
}
