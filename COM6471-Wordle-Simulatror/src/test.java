import sheffield.EasyReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kevinyi
 * @date 2023-12-05 1:03
 * @NAME: test
 * @PROJECT_NAME: COM6471-Wordle-Simulatror
 */
public class test {
    public static void main(String[] args) {
//        String correctWord = "value";
//        String guessedWord = "every";
//        // Track available letters using an array
//        char[] availableLetters = createAvailableLetters(correctWord);
//        System.out.println("Available Letters: " + Arrays.toString(availableLetters));
//
//        String response = compareWords(correctWord, guessedWord,availableLetters);
//        System.out.println(response);
//
////        char[] availableLetters = createAvailableLetters(correctWord);
////        System.out.println("Available Letters: " + Arrays.toString(availableLetters));
//        System.out.println("Available Letters: " + Arrays.toString(availableLetters));

        String myString = "Hello, World!";
        char myChar = 'o';

        int index = myString.indexOf(myChar);

        if (index != -1) {
            System.out.println("String contains the character '" + myChar + "' at index " + index);
        } else {
            System.out.println("String does not contain the character '" + myChar + "'");
        }

//        List<String> words = new ArrayList<>();
//        //open a file
//        EasyReader fileReader = new EasyReader("FiveLetterWords.txt");
//
//        while(!fileReader.eof()){
//            String word = fileReader.readString();
//            //add each word into the list
//            words.add(word);
//        }
//        createCompleteList(words,'X');
    }

    public static void createCompleteList(List previousList, char noChar){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < previousList.size(); i++) {
            System.out.println(previousList.get(i));
        }
    }

    private static String compareWords(String correctWord, String guessedWord, char[] availableLetters) {
        StringBuilder response = new StringBuilder();


        for (int i = 0; i < Math.min(correctWord.length(), guessedWord.length()); i++) {
            char charCorrect = correctWord.charAt(i);
            char charGuessed = guessedWord.charAt(i);

            if (charCorrect == charGuessed) {
                response.append('+');
                // Mark the letter as used by replacing it with an underscore
               // markLetterAsUsed(availableLetters, charCorrect);
            } else {
                // Check if the guessed letter is present in the correct word
                if (correctWord.indexOf(charGuessed) != -1) {
                    response.append('*');
                    // Mark the letter as used by replacing it with an underscore
                    //markLetterAsUsed(availableLetters, charGuessed);
                } else {
                    response.append('X');
                    markLetterAsUsed(availableLetters, charGuessed);
                }
            }
        }

        // If correctWord is longer than guessedWord, append 'X' for the remaining characters
        for (int i = Math.min(correctWord.length(), guessedWord.length()); i < correctWord.length(); i++) {
            response.append('X');
        }

        return response.toString();
    }

    private static char[] createAvailableLetters(String word) {
        char[] availableLetters = new char[26];

        for (int i = 0; i < availableLetters.length; i++) {
            availableLetters[i] = (char) ('a' + i);
        }
        return availableLetters;
    }

    private static void markLetterAsUsed(char[] availableLetters, char letter) {
        int index = letter - 'a';
        if (index >= 0 && index < availableLetters.length) {
            availableLetters[index] = '_';
        }
    }
}
