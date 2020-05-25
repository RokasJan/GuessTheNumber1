package com.example.guessthenumber;

public class PersonData {
    private String name;
    private String difficulty;
    private int number;
    private String result;

    public PersonData (String name,String difficulty, int number, String result){
        this.name = name;
        this.difficulty = difficulty;
        this.number = number;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
