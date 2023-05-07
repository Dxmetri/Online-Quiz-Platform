module com.example.javafxonlinequizplatform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxonlinequizplatform to javafx.fxml;
    exports com.example.javafxonlinequizplatform;
}