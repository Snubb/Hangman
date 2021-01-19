import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String word = randomWord().toLowerCase();
        int gameOver = 0; //increases by one per wrong letter. Game over if 10.
        String visibleWord = "_".repeat(word.length());
        String guessLetter = null;
        boolean containsLetter = false;
        System.out.println(word);

        while (gameOver < 10) {
            if (letterCheck(word, visibleWord, guessLetter, containsLetter)) {
                visibleWord = newWord(word, guessLetter);
            } else {
                gameOver++;
            }
        }
    }

    private static boolean letterCheck(String word, String visibleWord, String guessLetter, boolean containsLetter) {
        System.out.println(visibleWord);
        while (true) {
            guessLetter = JOptionPane.showInputDialog("Guess a letter").toLowerCase();
            if (guessLetter.length() > 1){
                System.out.println("You can only guess a single letter.");
            } else {
                break;
            }
        }
        containsLetter = word.contains(guessLetter);
        return containsLetter;
    }

    private static String newWord(String word, String guessLetter) {
        for (int i = 0; i < word.length(); i++) {

        }
        return null;
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
