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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Database.readUsers();

        Label lblTitle = new Label("Quiz Platform");

        Text userName =new Text("User Name: ");
        TextField txtUser=new TextField();
        Text psw=new Text("Password: ");
        PasswordField txtPass=new PasswordField();

        Button btnLogin=new Button("Login");
        Button btnNewUser=new Button("New User");
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
        btnNewUser.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));


        //add components into gridPane
        gridPane.add(lblTitle, 0, 0, 2, 1);
        gridPane.add(userName, 0, 1);
        gridPane.add(txtUser, 1, 1);
        gridPane.add(psw, 0, 2);
        gridPane.add(txtPass, 1, 2);
        gridPane.add(btnLogin, 0, 4);
        gridPane.add(btnNewUser, 2, 4);
        gridPane.add(txtError, 1, 5);

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
        stage.setTitle("Quiz Platform");
        stage.setScene(scene);
        stage.show();

        // Added a new button so user can now create an account to log in with.

        btnNewUser.setOnAction(event -> {
            // Create a new stage for the new user screen
            Stage newUserStage = new Stage();

            // Create the UI components for new user registration
            Text lblNewUser = new Text("New User Registration");
            Text lblUsername = new Text("Username:");
            Text lblPassword = new Text("Password:");
            TextField txtUsername = new TextField();
            PasswordField txtPassword = new PasswordField();
            Button btnRegister = new Button("Register");

            // Create a grid pane for the new user registration screen
            GridPane newUserPane = new GridPane();
            newUserPane.setMinSize(300, 200);
            newUserPane.setPadding(new Insets(10, 10, 10, 10));
            newUserPane.setVgap(10);
            newUserPane.setHgap(10);
            newUserPane.setAlignment(Pos.CENTER);

            // Add components into the new user registration grid pane
            newUserPane.add(lblNewUser, 0, 0, 2, 1);
            newUserPane.add(lblUsername, 0, 1);
            newUserPane.add(txtUsername, 1, 1);
            newUserPane.add(lblPassword, 0, 2);
            newUserPane.add(txtPassword, 1, 2);
            newUserPane.add(btnRegister, 1, 3);

            // Set the action for the register button
            btnRegister.setOnAction(e -> {
                String newUsername = txtUsername.getText();
                String newPassword = txtPassword.getText();

                if (newUsername.isEmpty() || newPassword.isEmpty()) {
                    // Display an error message if the fields are empty
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a username and password.");
                    alert.showAndWait();
                } else {
                    // Store the new user in the users.txt file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                        writer.write(newUsername + "," + newPassword);
                        writer.newLine();
                        writer.flush(); // Flush the buffer to ensure data is immediately written
                    } catch (IOException ex) {
                        // Display an error message if there was an issue writing to the file
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("An error occurred while registering the user.");
                        alert.showAndWait();
                    }

                    // Close the new user registration screen
                    newUserStage.close();
                }
            });


            // Create a scene and set it on the stage
            Scene newUserScene = new Scene(newUserPane);
            newUserStage.setTitle("New User Registration");
            newUserStage.setScene(newUserScene);
            newUserStage.show();
        });

    }


    public static void main(String[] args) {
        launch();
    }
}
