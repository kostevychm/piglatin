package me.kostevych;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String sentence;

        System.out.println("Pig Latin Translator, for exit enter 'N'");
        while (true) {
            System.out.println("Enter a line to be translated: ");
            sentence = scan.nextLine();
            if(sentence.equalsIgnoreCase("N"))
                break;
            System.out.println(PigLatinTranslator.convert(sentence)); // calling the main method to convert sentence to piglatin.
            System.out.println("");
        }
        System.out.println("Goodbye!");
        scan.close();
    }
}
