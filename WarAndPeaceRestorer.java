/*
Name: Daniel Leon
Program: WarAndPeaceRestorer
Course: COSC 237
Assignment: Program 1 – War and Peace Restoration Program
Description:
Reads the War and Peace text file, lets the user repeatedly enter a sequence
of characters to replace and the replacement string, reports the number of
replacements for each operation, and finally prints the TOTAL number of
replacements made.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WarAndPeaceRestorer
{
    public static void main(String[] args)
    {
        // DECLARATIONS
        Scanner cin;
        String sFQFN;
        String sText;
        String sFind;
        String sReplace;
        int iReplacementsThisRound;
        int iTotalReplacements;
        boolean bReadOK;

        // INITIALIZE
        cin = new Scanner(System.in);
        iTotalReplacements = 0;
        sText = "";
        bReadOK = false;

        // PROMPT FOR FILE (loop until a readable path is entered)
        while (bReadOK == false)
        {
            System.out.print("Enter the Fully Qualified File Name of the text file: ");
            sFQFN = cin.nextLine();

            try
            {
                sText = fsReadEntireFile(sFQFN); // throws on failure
                bReadOK = true;
            }
            catch (IOException e)
            {
                System.out.println("Error: Could not read the file. Please check the path and try again.");
                // loop repeats
            }
        }

        // MAIN LOOP: multiple replacement operations until empty <ENTER>
        while (true)
        {
            System.out.print("Enter the sequence of characters to replace (Hit <ENTER> when done): ");
            sFind = cin.nextLine();

            if (sFind.length() == 0)
            {
                break; // user finished
            }

            System.out.print("Enter the replacement string: ");
            sReplace = cin.nextLine();

            // Count occurrences *before* replacing
            iReplacementsThisRound = fiCountOccurrences(sText, sFind);

            // Replace in memory
            sText = sText.replace(sFind, sReplace);

            System.out.println(iReplacementsThisRound + " replacements made");

            // Update running total
            iTotalReplacements = iTotalReplacements + iReplacementsThisRound;
        }

        // FINAL REPORT
        System.out.println("Total Number of Replacements Made: " + iTotalReplacements);

        cin.close();
    }

    // Methods

    public static String fsReadEntireFile(String sFQFN) throws IOException
    {
        // DECLARATIONS
        BufferedReader br;
        String sLine;
        String sText;

        // INITIALIZE
        br = new BufferedReader(new FileReader(sFQFN));
        sText = "";

        try
        {
            sLine = br.readLine();
            while (sLine != null)
            {
                sText = sText + sLine + "\n";
                sLine = br.readLine();
            }
        }
        finally
        {
            br.close();
        }

        return sText;
    }

    public static int fiCountOccurrences(String sText, String sFind)
    {
        // DECLARATIONS
        int iCount;
        int iFromIndex;
        int iIndex;

        // INITIALIZE
        iCount = 0;
        iFromIndex = 0;

        if (sFind.length() == 0)
        {
            return 0;
        }

        while (true)
        {
            iIndex = sText.indexOf(sFind, iFromIndex);
            if (iIndex == -1)
            {
                break;
            }
            iCount = iCount + 1;
            iFromIndex = iIndex + sFind.length();
        }

        return iCount;
    }
}