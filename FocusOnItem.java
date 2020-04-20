package com.example.schoolbox;

public class FocusOnItem {
    private String Letters;
    private String State;
    private String colorLetters;
    private int stateInt;

    public FocusOnItem(String letters, String state, int stateInt, String colorLetters){
        this.Letters=letters;
        this.State=state;
        this.colorLetters=colorLetters;
        this.stateInt=stateInt;
    }

    public String getItemLetters(){return Letters;}
    public String getItemState(){return State;}
    public String getItemColorLetters(){return colorLetters;}
    public int getItemStateInt(){return stateInt;}

}
