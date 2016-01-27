package edu.gatech.seclass;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Pranav
 * Date: 1/25/16
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyWeirdString implements MyWeirdStringInterface {
    private String message;

    @Override
    public void setWeirdString(String string) {
        if(string==null)
            throw new IllegalArgumentException("Null value for message");
        this.message = string;
    }

    @Override
    public String getWeirdString() {
        return message;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getEvenCharacters() {
        int length = message.length();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<length;i++){
            if(i%2!=0){
                stringBuilder.append(message.charAt(i));
            }
        }
        return stringBuilder.toString();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getOddCharacters() {
        int length = message.length();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<length;i++){
            if(i%2==0){
                stringBuilder.append(message.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int countDigits() {
        List<String> arrayOfNumbers = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));// Load all numbers
        int length = message.length();
        int numberOfDigits = 0;
        for(int i=0;i<length;i++){
            Character character = message.charAt(i);
            if(arrayOfNumbers.contains(character.toString())){
                numberOfDigits++;
            }
        }
        return numberOfDigits;
    }

    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException {
        int lengthOfMessage = message.length();

        if(startPosition<1){
            throw new MyIndexOutOfBoundsException("startPosition in less than 1.");
        } else if(endPosition>lengthOfMessage){
            throw new MyIndexOutOfBoundsException("endPosition is greater than "+ lengthOfMessage + "the length of message.");
        } else if (startPosition>endPosition){
            throw new IllegalArgumentException("The startPosition is ahead of the endPosition.");
        }

        Map<String,String> mapOfNumbers = new HashMap<String,String>();
        mapOfNumbers.put("0","0");
        mapOfNumbers.put("1","I");
        mapOfNumbers.put("2","II");
        mapOfNumbers.put("3","III");
        mapOfNumbers.put("4","IV");
        mapOfNumbers.put("5","V");
        mapOfNumbers.put("6","VI");
        mapOfNumbers.put("7","VII");
        mapOfNumbers.put("8","VIII");
        mapOfNumbers.put("9","IX");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.substring(0,startPosition-1));

        for(int i=startPosition-1;i<endPosition;i++){
            Character character = message.charAt(i);
            if(mapOfNumbers.containsKey(character.toString())){
                String roman = mapOfNumbers.get(character.toString());
                stringBuilder.append(roman);
            }else {
                stringBuilder.append(character.toString());
            }
        }

        stringBuilder.append(message.substring(endPosition));
        this.message = stringBuilder.toString();
    }
}
