// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: QuizScreen.java
// Description: This is a Java class for a quiz screen that allows users to take a quiz. The class contains GUI components,
// answer options, and a timer. It also retrieves quiz questions from a database and allows users to select their answers.
// The user's score is updated in real-time, and the class handles both true/false and multiple-choice quiz formats.
// **********************************************************************************
package com.example.javafxonlinequizplatform;

import com.example.javafxonlinequizplatform.datastructure.QuizHashMap;
import com.example.javafxonlinequizplatform.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class QuizScreen {

    private int questionCount = 0;
    private String questionType;
    private User currentUser;
    private Text labelScore;
    private Text txtScore;
    private Text question;
    private RadioButton option1, option2, option3, option4;
    private volatile boolean running = false;
    private volatile long elapsedTime = 300000L;

    private QuizHashMap<Quiz, String> quizHashMap = new QuizHashMap<>(10);


    private ArrayList<Quiz> quizArrayList = new ArrayList<>();

    public QuizScreen(String questionType, User user) {

        this.questionType = questionType;
        this.currentUser = user;
        //read Questions from database

        // Added a new quiz "Sport MQC"
        if (questionType.equalsIgnoreCase("Math True False")) {
            quizArrayList = Database.readTrueFalseQuiz();
        } else if (questionType.equalsIgnoreCase("Math MCQs")) {
            quizArrayList = Database.readMCQs();
        } else if (questionType.equalsIgnoreCase("Sports MCQs")) {
            quizArrayList = Database.readSportsMCQs();
        }


        //create GUI components
        Text txtTimer = new Text();
        txtTimer.setStyle("-fx-font: normal bold 13px 'serif' ");
        txtTimer.setFill(Color.RED);


        labelScore = new Text("Score: ");
        txtScore = new Text("0");
        //create a HBox and add above components into it
        HBox hBoxScore = new HBox();
        hBoxScore.getChildren().addAll(labelScore, txtScore);


        labelScore.setFont(new Font("verdana", 15.0));
        txtScore.setFont(new Font("verdana", 15.0));
        txtScore.setFill(Color.RED);

        question = new Text(questionCount + ": This is a Question hkfgjdfgffgf: ");
        question.setStyle("-fx-font: normal bold 14px 'serif' ");

        //create radio buttons for the options

        ToggleGroup toggleGroup = new ToggleGroup();
        option1 = new RadioButton("option1");
        option2 = new RadioButton("option2");
        option3 = new RadioButton("option3");
        option4 = new RadioButton("option4");

        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        option4.setToggleGroup(toggleGroup);
        //create a GridPane to add these RadioButtons
        GridPane radioPane = new GridPane();

        radioPane.setVgap(20);
        radioPane.setHgap(20);
        radioPane.setPadding(new Insets(5, 5, 5, 5));
        radioPane.add(option1, 0, 0);
        radioPane.add(option2, 0, 1);
        if (!questionType.equalsIgnoreCase("True False")) {
            radioPane.add(option3, 0, 2);
            radioPane.add(option4, 0, 3);
        }

        //Next Button
        Button btnNext = new Button("Next");
        btnNext.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");


        //Create a GridPane

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(5, 5, 5, 5));


        gridPane.add(question, 0, 1);
        gridPane.add(radioPane, 0, 3);


        gridPane.setMinSize(600, 350);

        updateNextQuestion();


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setMinSize(500, 100);
        hBox.getChildren().addAll(btnNext);
        VBox vBox = new VBox(10);

        vBox.getChildren().addAll(txtTimer, hBoxScore, gridPane, hBox);

        Scene scene = new Scene(vBox);

        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();


        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (quizArrayList.get(questionCount) instanceof TrueFalseQuiz) {
                    TrueFalseQuiz quiz = (TrueFalseQuiz) quizArrayList.get(questionCount);
                    RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();

                    if (radioButton == option1) {

                        if (quiz.getCorrectAnswer() == true) {
                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");
                        }

                        quizHashMap.put(quizArrayList.get(questionCount), option1.getText());

                    } else {
                        if (quiz.getCorrectAnswer() == false) {
                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");
                        }
                        quizHashMap.put(quizArrayList.get(questionCount), option2.getText());
                    }
                } else {
                    MultipleChoiceQuiz quiz = (MultipleChoiceQuiz) quizArrayList.get(questionCount);
                    RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();

                    if (radioButton == option1) {
                        if (quiz.getAnswer() == 1) {

                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");

                        }
                        quizHashMap.put(quizArrayList.get(questionCount), option1.getText());


                    } else if (radioButton == option2) {
                        if (quiz.getAnswer() == 2) {

                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");

                        }
                        quizHashMap.put(quizArrayList.get(questionCount), option2.getText());
                    } else if (radioButton == option3) {
                        if (quiz.getAnswer() == 3) {

                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");

                        }
                        quizHashMap.put(quizArrayList.get(questionCount), option3.getText());
                    } else if (radioButton == option4) {

                        if (quiz.getAnswer() == 4) {

                            currentUser.setScore((currentUser.getScore()) + 1);
                            txtScore.setText(currentUser.getScore() + "");

                        }
                        quizHashMap.put(quizArrayList.get(questionCount), option4.getText());
                    }

                }
                //Fixed error where if you got a perfect score it would display "You got 11 out of 10" Instead of "10 out of 10"
                if (questionCount == 9) {
                    finishQuiz();
                    stage.close();

                } else {
                    questionCount++;
                    updateNextQuestion();
                }
            }
        });

        //start timer Thread (MultiThreading)
        startTimerThread(txtTimer);


    }
    private void updateNextQuestion() {
        if (quizArrayList.get(questionCount) instanceof TrueFalseQuiz) {
            TrueFalseQuiz quiz = (TrueFalseQuiz) quizArrayList.get(questionCount);
            question.setText(quiz.getQuestion());
            option1.setText("true");
            option2.setText("false");

        } else {
            MultipleChoiceQuiz quiz = (MultipleChoiceQuiz) quizArrayList.get(questionCount);
            question.setText(quiz.getQuestion());
            option1.setText(quiz.getOption1());
            option2.setText(quiz.getOption2());
            option3.setText(quiz.getOption3());
            option4.setText(quiz.getOption4());

        }


    }

//Made the improvement to change the timer from a 2-minute countdown to a 5-minute countdown based on feed back that 2 minutes was not enough time to complete the quiz.
    public void startTimerThread(Text timeLabel) {
        if (!running) {
            running = true;
            new Thread(() -> {
                while (running) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    elapsedTime -= 1000;
                    Platform.runLater(() -> {
                        long seconds = elapsedTime / 1000;
                        long minutes = seconds / 60;
                        long hours = minutes / 60;
                        timeLabel.setText(String.format("%02d:%02d:%02d", hours % 24, minutes % 60, seconds % 60));

                    });

                    if (elapsedTime <= 0) {
                        try {
                            finishTimer();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }
            }).start();
        }
    }

    private void finishTimer() throws InterruptedException {
        Thread.sleep(1000);
        Platform.runLater(() -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Time's up");
            dialog.setHeaderText("You got " + currentUser.getScore() + " out of 10");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    System.exit(0);
                }
                return null;
            });
            dialog.show();

        });
    }

    private void finishQuiz() {
        new FinishScreen(currentUser);

    }

}

