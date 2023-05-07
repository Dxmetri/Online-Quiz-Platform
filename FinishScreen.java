// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: FinishScreen.java
// Description: The class takes a User object as input and displays the user's score out of 10 along with two buttons: Start Again and Exit.
// When the Exit button is clicked, the program terminates. When the Start Again button is clicked, the user's score is added to the database,
// their score is reset to 0, and a new instance of the StartQuizScreen class is created with the same user. The stage is closed after the StartQuizScreen is created.
// **********************************************************************************
package com.example.javafxonlinequizplatform;

import com.example.javafxonlinequizplatform.model.Database;
import com.example.javafxonlinequizplatform.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinishScreen {

    User currentUser;

    public FinishScreen(User currentUser) {
        this.currentUser = currentUser;
        Text txt=new Text("You got "+currentUser.getScore()+" out of 10");
        Button btnStartAgain=new Button("Start Again");
        Button btnClose=new Button("Exit");

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        gridPane.setMinSize(200,150);
        gridPane.add(txt,0,0);
        gridPane.add(btnStartAgain,0,1);
        gridPane.add(btnClose,1,1);

        Scene scene=new Scene(gridPane);
        Stage stage=new Stage();
        stage.setTitle("Results");
        stage.setScene(scene);
        stage.show();



        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        btnStartAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Database.addScores(currentUser.getScore());
                currentUser.setScore(0);
                new StartQuizScreen(currentUser);
                stage.close();
            }
        });
    }




}

