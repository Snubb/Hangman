import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String word = randomWord();
        int gameOver = 0; //increases by one per wrong letter. Game over if 10.
    }

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
