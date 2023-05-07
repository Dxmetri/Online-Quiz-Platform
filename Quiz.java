// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: Quiz.java
// Description: The Quiz class is an abstract class that represents a general quiz, containing a question and a category.
// **********************************************************************************
package com.example.javafxonlinequizplatform.model;

public abstract class Quiz {
    private String question;
    private String category;


    public Quiz(String question, String category) {
        this.question = question;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

