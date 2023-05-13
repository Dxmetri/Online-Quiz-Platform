// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: StartQuizScreen.java
// Description: The StartQuizScreen class is a JavaFX class that creates a user interface for selecting a quiz category and starting a quiz.
// **********************************************************************************
package com.example.javafxonlinequizplatform;

import com.example.javafxonlinequizplatform.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartQuizScreen  {

    User currentUser;
    public StartQuizScreen(User currentUser) {

        this.currentUser=currentUser;
        // Create the data for the ComboBox
        ObservableList<String> options = FXCollections.observableArrayList(
                "Math True False",
                "Math MCQs", "Sports MCQs"
        );


        ComboBox<String> category=new ComboBox<>(options);
        category.setValue("Math True False");

        Text txtCat=new Text("Category");

        Button btnStartQuiz=new Button("Start!");

        GridPane gridPane=new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));


        gridPane.add(txtCat,0,0);
        gridPane.add(category,1,0);
        gridPane.add(btnStartQuiz,2,2);

        Scene scene=new Scene(gridPane);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

        btnStartQuiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                new QuizScreen(category.getValue(),currentUser);
            }
        });
    }
}
