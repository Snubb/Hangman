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
        char guessLetter = 'a';
        boolean containsLetter = false;
        System.out.println(word);

        while (gameOver < 10) {
            System.out.println(visibleWord);
            String guessString = JOptionPane.showInputDialog("Guess a letter").toLowerCase();
            guessLetter = guessString.charAt(0);
            if (letterCheck(word, visibleWord, guessLetter, containsLetter, guessString)) {
                visibleWord = newWord(word, guessLetter, visibleWord);
                System.out.println("yay");
            } else {
                System.out.println("nay");
                gameOver++;
            }
        }
    }

    private static boolean letterCheck(String word, String visibleWord, char guessLetter, boolean containsLetter, String guessString) {

        containsLetter = word.contains(guessString);
        return containsLetter;
    }

    private static String newWord(String word, char guessLetter, String visibleWord) {
        String guess = String.valueOf(guessLetter);
        String wordChar = visibleWord;
        char[] a = wordChar.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            System.out.println("Guess:" + guess);
            System.out.println("guessLetter:" + guessLetter);
            System.out.println(word.charAt(0));
            if (guess.equals(Character.toString(word.charAt(i)))) {
                a[i] = guessLetter;
                System.out.println("yep");
            } else {
                System.out.println("nep");
            }
        }
        wordChar = String.valueOf(a);
        System.out.println(wordChar);
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
