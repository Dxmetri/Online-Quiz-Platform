// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: MultipleChoiceQuiz.java
// Description: The MultipleChoiceQuiz class is a child class of the Quiz class and represents a multiple choice quiz.
// **********************************************************************************
package com.example.javafxonlinequizplatform.model;

//Child class of Quiz
public class MultipleChoiceQuiz extends Quiz {
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private int answer;

    public MultipleChoiceQuiz(String question, String category, String option1, String option2, String option3, String option4, int answer) {
        super(question, category);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
