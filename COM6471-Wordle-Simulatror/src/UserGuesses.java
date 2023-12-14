import java.util.Arrays;
import java.util.List;

import sheffield.*;

/*
     Author: Zhiyi Chen
     Date: December 2023
     NAME: UserGuesses.java

     Class that Computer selects a word at random from a list that is stored in a text file,
     and the user has six attempts to guess the chosen word.
 */

public class UserGuesses extends BaseGame {
    private final String[] usedWords;     //store previous attempt words
    boolean found;                        //check if user managed to get the correct answer

    public UserGuesses(EasyReader k, EasyWriter s){
        super(k,s);
        s.println("Constructing a new game with user guesses");
        this.usedWords = new String[6];
        this.found = false;
    }

    //Overwrite the abstract in BaseGame.java
    public void run(){
        EasyWriter writer = getS();
        //Count the number of attempts
        int counter = 0;
        int total = 6;
        boolean valid = false;

        //Get the original input list
        List<String> wordList = getInputWordList();

        //store the randomly select word
        String selectedWord = wordList.get(getRandomIndex(wordList));

        //Print current selected word from the list
        writer.println(selectedWord);

        writer.println("The computer has selected a word, and you have 6 attempts to guess it. \n" +
                "The computer will respond with:\n" +
                "+ to denote a correct character in the right location.\n" +
                "* to denote a correct character.\n" +
                "X to denote an incorrect character.\n");

        //while loop will terminate once the user attempts 6 times, or user find the correct word
        while(counter<total && !found){
            char[] available = getAvailableLetter();
            boolean retry = false;
            StringBuilder response = new StringBuilder();

            writer.println("\nAttempt of "+(counter+1)+" of "+total);
            System.out.println("Available letters: "+ Arrays.toString(available));

            while(!retry){
                String guessWord = getK().readString("\nPlease enter a word, 5 character long: ");
                int wordSize = guessWord.length();
                boolean used;     //decide whether attempt has been used

                writer.println("Checking -"+guessWord+"- is valid");

                //Input word is valid if its length equals to 5
                //And word contains only character from a-z
                if( wordSize == 5 ){
                    for(int i = 0; i<wordSize;i++){
                        char c = guessWord.charAt(i);
                        if (!(c >= 'a' && c <= 'z') ) {
                            valid = false;
                            break;
                        }else{
                            valid = true;
                        }
                    }
                    if(valid){
                        //first, check if current attempt word has been used
                        used = checkUsedWord(guessWord,total);

                        if(!used){
                            System.out.println("Word -"+guessWord+"- contains a-z only");
                            System.out.println("Word -"+guessWord+"- is valid");
                            usedWords[counter] = guessWord;     //assign current attempt word
                            retry = true;

                            response = getResponse(selectedWord,guessWord,available);

                            if(response.toString().equals("+++++")){
                                //user find the correct answer
                                found = true;
                            }
                        }
                    }else{
                        //length is correct, but word has character other than a->z
                        System.out.println("Word -"+guessWord+"- contains incorrect characters, use a-z only");
                        System.out.println("Word is not valid - try again");
                    }
                }else {
                    //invalid length
                    System.out.println("Word -"+guessWord+"- is not the right length"+"\n"+"Word is not valid - try again");
                }
            }
            counter++;
        }
        if (found){
            System.out.println("You have solved the puzzle "+"\n");
        }else{
            System.out.println("You have not managed to guess the word this time."+
                    "\nThe correct answer is "+ selectedWord);
        }
    }

    //This method is to check if current guess word has been used before
    public boolean checkUsedWord(String currentWord, int totalAttempt){
        boolean flag = false;
        for (int i = 0; i < usedWords.length; i++) {
            if(currentWord.equals(usedWords[i])){
                System.out.println("Word -"+currentWord+"- used in the "+(i+1)+"/"+totalAttempt+" attempt, try another word"+
                        "Word is not valid - try again");
                flag = true;
            }
        }
        return flag;
    }

    //This method is to get the response of user's guess word
    public StringBuilder getResponse(String selectedWord, String guessWord,char[] available){
        StringBuilder response = new StringBuilder();
        for (int j = 0; j < selectedWord.length(); j++) {
            char charCorrect = selectedWord.charAt(j);
            char charGuessed = guessWord.charAt(j);

            if (charCorrect == charGuessed) {
                response.append('+');
            } else {
                // Check if the guessed letter is present in the correct word
                if (selectedWord.indexOf(charGuessed) != -1) {
                    response.append('*');
                } else {
                    response.append('X');
                    makeAsUsed(available,charGuessed);
                }
            }
        }
        System.out.println("Your Guess: "+guessWord+
                "\nResponse: "+response);
        return response;
    }

    //This method is to replace those word that are not in word with underscore "_"
    public void makeAsUsed(char[] available, char letter){
        for (int i = 0; i < available.length; i++) {
            if(available[i] == letter){
                available[i] = '_';
                break;
            }
        }
    }
}
