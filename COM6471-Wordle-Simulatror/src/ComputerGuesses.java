import sheffield.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
     Author: Zhiyi Chen
     Date: December 2023
     NAME: UserGuesses.java

     Class that User selects a word from a list that is stored in a text file, and the computer has six attempts
     to guess the chosen word.
 */

public class ComputerGuesses extends BaseGame{
    private final String[] attempts;      //store all the attempts that computer used

    public ComputerGuesses(EasyReader k, EasyWriter s) {
        super(k, s);
        s.println("Constructing a new game with Computer guesses");
        attempts = new String[6];
    }

    public void run() {
        int counter = 0;
        int total = 6;
        boolean found = false;
        String correctWord;
        Random random = new Random();

        //Get input list
        List<String> inputWordList = getInputWordList();
        EasyReader reader = getK();
        int selectedIndex = reader.readInt("Select a word by choosing a number between 0 and " + (inputWordList.size() - 2) + " : ");

        correctWord = inputWordList.get(selectedIndex +1);
        System.out.println("Correct word user choose is: "+correctWord);

        while(counter<total && !found){
//            //Set the range of the random word in the list
//            int max = inputWordList.size()-1;
//            int min = 1;
//            int num = random.nextInt(max-min+1)+min;

            //store the randomly select word
            int num = getRandomIndex(inputWordList);
            String guessWord = inputWordList.get(num);
            attempts[counter] = guessWord;

            //first attempt
            if(counter+1 == 1){

                System.out.println("\nAttempt "+(counter+1)+" of "+total+
                        "\nGuess is : "+guessWord);
            }else{
                System.out.println("\nAttempt "+(counter+1)+" of "+total);
                System.out.println("Correct word : "+correctWord+" , previous guess : "+attempts[counter-1]);

                //total possible number of words, since index 0 is always number, we need to decrease 1
                int options = inputWordList.size()-1;
                System.out.println("Total number of possible words is : "+options);

                System.out.println("Selecting a random word number : "+num+"/"+options);
                //select random word of total words in list

                //guess
                System.out.println("Guess is : "+guessWord);
            }

            //check the response computer's guess
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < correctWord.length(); i++) {
                char charCorrect = correctWord.charAt(i);
                char charGuessed = guessWord.charAt(i);

                if (charCorrect == charGuessed) {
                    response.append('+');
                } else {
                    // Check if the guessed letter is present in the correct word
                    if (correctWord.indexOf(charGuessed) != -1) {
                        response.append('*');
                    } else {
                        response.append('X');
                        //construct a new complete list each time
                        inputWordList = createCompleteList(inputWordList,charGuessed);
                    }
                }
            }
            System.out.println("Response : "+response);

            //check if the response is correct skip the remaining attempts
            if (response.toString().equals("+++++")){
                found = true;
            }
            counter++;
        }
    }

    //This method is to create new complete list for computer to guess word
    public List<String> createCompleteList(List<String> previousList, char noChar){
        List<String> newList = new ArrayList<>();
        for (String s : previousList) {
            if (s.indexOf(noChar) == -1) {
                //This word does not contain 'X', it can be put into the new complete list
                newList.add(s);
            }
        }
        System.out.println(newList);
        return newList;
    }
}
