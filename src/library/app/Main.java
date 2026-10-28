package library.app;

import java.util.Scanner;

/**
 * TODO: read the script file named in args[0], run each command,
 * and print the results. See "Main" in README.md.
 * Do NOT call System.exit.
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner("01_basic.txt");

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            Library.execute(line);
        }
    }
}
