// **********************************************************************************
// Title: OnlineQuizPlatform
// Author: Demetri Jamison
// Course Section: CMIS201-ONL1 (Seidel) Spring 2023
// File: LoginScreen.java
// Description: The LoginScreen extends the Application class. It overrides the start method, which sets up a GUI for a login screen.
// The GUI contains input fields for a username and password, along with buttons for logging in and clearing the input fields.
// The login button has an event handler that checks if the input fields are valid and authenticates the user's credentials against a database.
// If the user is authenticated, it closes the login screen and opens a new StartQuizScreen. The main method calls the launch method to start the application.
// **********************************************************************************
package com.example.javafxonlinequizplatform;

import com.example.javafxonlinequizplatform.model.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Database.readUsers();

        Text userName =new Text("User Name: ");
        TextField txtUser=new TextField();
        Text psw=new Text("Password: ");
        PasswordField txtPass=new PasswordField();

        Button btnLogin=new Button("Login");
        Button btnClear=new Button("Clear");
        Text txtError=new Text("");

        //create a GridPane
        GridPane gridPane=new GridPane();
        gridPane.setMinSize(500,400);
        gridPane.setPadding(new Insets(5,5,5,5));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        //setStyles
        userName.setStyle("-fx-font: normal bold 15px 'serif' ");
        psw.setStyle("-fx-font: normal bold 15px 'serif' ");
        txtError.setStyle("-fx-font: normal bold 10px 'serif' ");
        txtError.setFill(Color.RED);
        btnLogin.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btnClear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        //add components into gridPane
        gridPane.add(userName,0,0);
        gridPane.add(txtUser,1,0);
        gridPane.add(psw,0,1);
        gridPane.add(txtPass,1,1);
        gridPane.add(btnLogin,0,3);
        gridPane.add(btnClear,2,3);
        gridPane.add(txtError,1,4);


        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=txtUser.getText();
                String ps=txtPass.getText();

                if(name==null || ps==null || name.equalsIgnoreCase("") || ps.equalsIgnoreCase(""))
                {
                    txtError.setText("Please Enter a Valid User Name and Password");
                    return;
                }

                if(Database.authenticateUser(name,ps)!=null)
                {
                    stage.close();
                    new StartQuizScreen(Database.authenticateUser(name,ps));
                }
                else
                    txtError.setText("Incorrect User Name or Password");

            }
        });


        Scene scene = new Scene(gridPane);
        stage.setTitle("Quiz System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
