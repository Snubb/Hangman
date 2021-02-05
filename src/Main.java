import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String word = randomWord().toLowerCase();
        int gameOver = 0; //increases by one per wrong letter, game over if 10
        String visibleWord = "_ ".repeat(word.length()); //the visible word showed to the player
        char guessLetter;
        StringBuilder guessedLetters = new StringBuilder(); //contains all the letters the player has already guessed

        //May look completely fucked but it's the hanging man I promise.
        String[] image = {"          \n" + "          \n" + "          \n" + "          \n" + "          \n" + "          \n" + "__________\n",
                          "          \n" + "          \n" + "          \n" + "          \n" + "          \n" + "   ___     \n" + "_/___\\____\n",
                          "          \n" + "     |      \n" + "     |      \n" + "     |      \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |      \n" + "     |      \n" + "     |      \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |      \n" + "     |      \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |      \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |     |  \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |    /|  \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |    /|\\ \n" + "     |      \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |    /|\\ \n" + "     |    /   \n" + "   _|_     \n" + "_/___\\____\n",
                          "    _____  \n" + "     |     |  \n" + "     |    O  \n" + "     |    /|\\ \n" + "     |    / \\ \n" + "   _|_     \n" + "_/___\\____\n"};

        while (gameOver < 10) {
            if (!visibleWord.contains("_")) {
                break; //breaks out of loop if player has won
            }
            String guessString = JOptionPane.showInputDialog(visibleWord + "\n" + image[gameOver] + "\nGuessed letters:" + guessedLetters + "\nGuess a letter").toLowerCase();
            guessLetter = guessString.charAt(0);
            if (word.contains(guessString)) { //checks if the word contains the guessed letter.
                visibleWord = newWord(word, guessLetter, visibleWord);
            } else {
                gameOver++;
                guessedLetters.append(guessLetter).append(" "); //adds the letter to the list of guessed letters.
            }
        }
        //checks if it's a win or a loss
        if (!visibleWord.contains("_")) {
            JOptionPane.showMessageDialog(null, "You have won. Congratulations!\nThe word was \"" + word + "\"");
        } else {
            JOptionPane.showMessageDialog(null, "You have failed.\n" + image[10] + "\nYou killed him. This is your fault.\n\n" + "The word was \"" + word + "\"");
        }
    }

    //replaces visibleWord with a new one where the guessed letters are revealed.
    private static String newWord(String word, char guessLetter, String visibleWord) {
        char[] a = visibleWord.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            //compares every letter of the word to the guessed letter.
            if (Character.toString(guessLetter).equals(Character.toString(word.charAt(i)))) {
                a[i*2] = guessLetter;
            }
        }
        return String.valueOf(a);
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
