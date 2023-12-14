/*

BaseGame.java

This skeleton code provides a template for the BaseGame class in the Wordle
simulator.

You must use this template in your submission, and you should add your own
code where needed.

*/

import sheffield.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseGame{

    // instance fields and constants should go here
    private final List<String> inputWordList;
    private char[] availableLetter;
    private EasyReader k;
    private EasyWriter s;

    public BaseGame(EasyReader k, EasyWriter s){
        this.k = k;
        this.s = s;
        this.inputWordList = readWordsFromInputFile("FiveLetterWords.txt");
        storeAvailableLetter();
    }

    // this is an abstract method that should be overridden by
    // methods in the subclasses
    public abstract void run();

    //This method is to read words from text file into a list
    private List<String> readWordsFromInputFile(String file){
        List<String> words = new ArrayList<>();
        //open a file
        EasyReader fileReader = new EasyReader(file);

        while(!fileReader.eof()){
            String word = fileReader.readString();
            //add each word into the list
            words.add(word);
        }
        return words;
    }

    //This method is to store a to z into the char array, based on the ASCII code
    private void storeAvailableLetter(){
        this.availableLetter = new char[26];
        for (int i = 0; i < this.availableLetter.length; i++) {
            this.availableLetter[i] = (char) ('a'+ i);
        }
    }

    //Get a random index of the list so that we can select a word randomly
    public int getRandomIndex(List<String> list){
        Random random = new Random();

        //Set the range of the random word in the list
        int max = list.size()-1;
        int min = 1;
//        int num = random.nextInt(max-min+1)+min;

        return random.nextInt(max-min+1)+min;
    }

    //Getter
    public List<String> getInputWordList() {
        return inputWordList;
    }

    public EasyReader getK() {
        return k;
    }

    public EasyWriter getS() {
        return s;
    }

    public char[] getAvailableLetter() {
        return availableLetter;
    }
}