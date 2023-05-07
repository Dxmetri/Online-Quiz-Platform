// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: TrueFalseQuiz.java
// Description: The TrueFalseQuiz class is a child class of Quiz and represents a quiz with a true or false answer.
// It has an additional boolean field correctAnswer to store the correct answer for the quiz.
// **********************************************************************************
package com.example.javafxonlinequizplatform.model;

//Child class of Quiz
public class TrueFalseQuiz extends Quiz {

    private boolean correctAnswer;


    public TrueFalseQuiz(String question, String category, boolean correctAnswer) {
        super(question, category);
        this.correctAnswer = correctAnswer;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}