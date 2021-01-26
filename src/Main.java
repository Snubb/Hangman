import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String word = randomWord().toLowerCase();
        int gameOver = 0; //increases by one per wrong letter. Game over if 9.
        String visibleWord = "_".repeat(word.length());
        char guessLetter;
        System.out.println(word); //TODO: only for testing, remove later.

        while (gameOver < 9) {
            if (visibleWord.equals(word)) {
                break; //breaks out of loop if player has won.
            }
            System.out.println(visibleWord);
            String guessString = JOptionPane.showInputDialog("Guess a letter").toLowerCase();
            guessLetter = guessString.charAt(0);
            if (word.contains(guessString)) {
                visibleWord = newWord(word, guessLetter, visibleWord);
                System.out.println("yay");
            } else {
                System.out.println("nay");
                gameOver++;
            }
        }
        if (visibleWord.equals(word)) {
            System.out.println("Congratulations! You have won.");
        } else {
            System.out.println("You have lost, coward.");
        }
    }

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

    private static String randomWord() {
        Scanner in = null;
        try {
            in = new Scanner(new File("wordList"));//TODO: Make actual words instead of just random letters.
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
