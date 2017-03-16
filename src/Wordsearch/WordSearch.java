
package Wordsearch;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Nathan Fry 200343236
 */
public class WordSearch {

       private char [][] wordSearchBoard;
       private String [] userInputWords;
       private int numberOfWords;
   
    /**
     * Constructor to start up the game
     */
    public WordSearch()
    {
        System.out.println("Please enter a number between 5-20 for how many words ");
        numberOfWords = checkNumber();
        wordSearchBoard = new char[numberOfWords][numberOfWords];
        userInputWords = new String[numberOfWords];

    }
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) 
    {
        WordSearch newGame = new WordSearch();
        newGame.userWords();
        newGame.randomLetters();
        newGame.wordsTo2dArray();
        newGame.printPuzzle();
        newGame.printWord();
        
    } 
    
    /**
     * This method checks the number the userInputs for WordSearch for Validation
     * @param userNumber
     * @return 
     */ 
    public int checkNumber()
    {
        Scanner keyboard = new Scanner(System.in);
        
        do
        { System.out.println("You can only have a number between 5 and 20? ");
            try
            {
                numberOfWords = keyboard.nextInt();   
            }
            catch(InputMismatchException mismatchException)
            {
                System.out.println("Sorry only numbers are allowed.");
                numberOfWords = 100;
                keyboard.nextLine();
            }
        }while(numberOfWords < 5 || numberOfWords >20);
        return numberOfWords;
    }
   /**
    * This Array gets the words inputed by the user and stores it
   */
   public void userWords()
   {
     Scanner keyboard = new Scanner(System.in);
     for (int i = 0; i < userInputWords.length; i++)
     { 
       do
        {
         System.out.println("What words would you like to use in the puzzle? ");
         try
         {
             userInputWords[i] = wordValidation();
         }
         catch(IllegalArgumentException argumentException)
         {
             System.out.println(argumentException.getMessage());
         }
     }while(userInputWords[i] == null);
    }
   Arrays.sort(userInputWords);
   
   }
   /**
    *  This method vaildates the word the user inputs and sees if its accetable
    */
   public String wordValidation()
   {
      Scanner keyboard = new Scanner(System.in);
      String wordAproval = keyboard.nextLine();
       for (int i = 0; i < numberOfWords; i++)
      {
          if (wordAproval.equalsIgnoreCase(userInputWords [i]))
          {
              throw new IllegalArgumentException("You can not have the same word Twice! ");
          }
      }
      if(wordAproval.contains(" "))
      {
          throw new IllegalArgumentException("Spaces are not allowed! ");
      }
      else if (wordAproval.length() <2)
      {
          throw new IllegalArgumentException("Words have to be longer then 1 letter! ");
      }
      else if(wordAproval.length() >numberOfWords)
      {
           throw new IllegalArgumentException("Words cannot be longer then the board it's self! ");
      }
   return wordAproval.toUpperCase();
   } 

/**
 * This method will return userInputWord and print it out to the screen in to a string.
 */
public void printWord()
    {
        System.out.println("These are the words you are looking for!");
        for(int i = 0; i < userInputWords.length; i++)
        {
            System.out.println(userInputWords[i]);
        }
    }
/**
 * This method will userInputWords into a 2d array with for loops
*/
public void wordsTo2dArray()
    {   
         for(int col = 0; col < wordSearchBoard.length; col++)
        {
            for(int row = 0; row < wordSearchBoard[col].length; row++)
            {
                if(row < userInputWords[col].length())
                wordSearchBoard[row][col] = userInputWords[col].charAt(row);
            }
        }
        
    }
/**
 * This method prints the puzzle to the output screen
 */
public void printPuzzle()
    {
        for(int col = 0; col < wordSearchBoard.length; col++)
                    {
            for(int row = 0; row < wordSearchBoard[col].length; row++)
            {
                System.out.print(wordSearchBoard[col][row] + " ");
            }
            System.out.println();
        }
    }
/**
 * This method adds random letters to the puzzle thats on the output screen
 */
public void randomLetters()
{
    Random rng = new Random();
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    for(int col = 0; col < wordSearchBoard.length; col++)
       {
        for(int row = 0; row < wordSearchBoard[col].length; row++)
            {
                int random = rng.nextInt(alphabet.length);
            wordSearchBoard[row][col] = alphabet[random];
            }
        }
}
}