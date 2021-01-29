import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String word = randomWord().toLowerCase();
        int gameOver = 0; //increases by one per wrong letter, game over if 9
        String visibleWord = "_".repeat(word.length()); //the visible word showed to the player
        char guessLetter;
        StringBuilder guessedLetters = new StringBuilder(); //contains all the letters the player has already guessed

        //May look completely fucked but it's the hanging man I promise.
        String[] image = {"----------\n" + "----------\n" + "----------\n" + "----------\n" + "----------\n" + "--___-----\n" + "_/___\\____\n",
                          "----------\n" + "---|------\n" + "---|------\n" + "---|------\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|------\n" + "---|------\n" + "---|------\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|------\n" + "---|------\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|------\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|---|--\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|--/|--\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|--/|\\-\n" + "---|------\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|--/|\\-\n" + "---|--/---\n" + "--_|_-----\n" + "_/___\\____\n",
                          "---_____--\n" + "---|---|--\n" + "---|---O--\n" + "---|--/|\\-\n" + "---|--/-\\-\n" + "--_|_-----\n" + "_/___\\____\n"};

        while (gameOver < 9) {
            if (visibleWord.equals(word)) {
                break; //breaks out of loop if player has won
            }
            System.out.println(image[gameOver] + " \n" + visibleWord + "\nGuessed letters: " + guessedLetters + "\n"); //show the player their progress.
            String guessString = JOptionPane.showInputDialog("Guess a letter").toLowerCase();
            guessLetter = guessString.charAt(0);
            if (guessedLetters.toString().contains(guessString)) { //checks if the player has already guessed that letter.
                System.out.println("You have already guessed that letter.");
            } else if (word.contains(guessString)) { //checks if the word contains the guessed letter.
                visibleWord = newWord(word, guessLetter, visibleWord); //
                guessedLetters.append(guessLetter).append(" "); //adds the letter to the list of guessed letters.
            } else {
                System.out.println("Try again");
                gameOver++;
                guessedLetters.append(guessLetter).append(" "); //adds the letter to the list of guessed letters.
            }
        }
        //checks if it's a win or a loss
        if (visibleWord.equals(word)) {
            System.out.println("Congratulations! You have won.\nThe word was " + word + ".");
        } else {
            System.out.println(image[gameOver] + "\n\nYou killed him. This is your fault.\nThe word was " + word + ".");
        }
    }

    //replaces visibleWord with a new one where the guessed letters are revealed.
    private static String newWord(String word, char guessLetter, String visibleWord) {
        String guess = String.valueOf(guessLetter);
        String wordChar = visibleWord;
        char[] a = wordChar.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (guess.equals(Character.toString(word.charAt(i)))) {
                a[i] = guessLetter;
            }
        }
        wordChar = String.valueOf(a);
        return wordChar;
    }

    //selects a random word from a list of 50
    private static String randomWord() {
        Scanner in = null;
        try {
            in = new Scanner(new File("wordList"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] wordList = new String[50];

        int count = 0;
        while (in.hasNext()) {
            wordList[count] = in.nextLine();
            count++;
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, wordList.length + 1);

        return wordList[randomNum];
    }


}
