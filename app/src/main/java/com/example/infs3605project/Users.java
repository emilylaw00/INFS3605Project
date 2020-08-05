package com.example.infs3605project;

public class Users {

    //not used for anything

    private String email;
    private String firstName;
    private String lastName;
    private String yearOfBirth; //to find age for data analytics
    private String degree;
    private int coinScore;
    private String howDidYouHear;


    public Users(String email, String firstName, String lastName, String yearOfBirth, String degree, int coinScore, String howDidYouHear) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.degree = degree;
        this.coinScore = coinScore;
        this.howDidYouHear = howDidYouHear;
    }

    public Users() {
  
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getPurityScore() {
        return coinScore;
    }

    public void setPurityScore(int purityScore) {
        this.coinScore = purityScore;
    }



    public String getHowDidYouHear() {
        return howDidYouHear;
    }

    public void setHowDidYouHear(String howDidYouHear) {
        this.howDidYouHear = howDidYouHear;
    }

}
