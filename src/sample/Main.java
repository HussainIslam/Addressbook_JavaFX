package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox pane = new VBox();
        pane.setPadding(new Insets(12));
        pane.setSpacing(10);

        Label lFirstName = new Label("First Name:");
        lFirstName.prefWidthProperty().bind(pane.widthProperty().divide(6));
        TextField inFirstName = new TextField();
        inFirstName.prefWidthProperty().bind(pane.widthProperty().divide(6).multiply(5));
        HBox firstNamePane = new HBox(lFirstName, inFirstName);
        firstNamePane.setSpacing(5);

        Label lLastName = new Label("Last Name:");
        lLastName.prefWidthProperty().bind(pane.widthProperty().divide(6));
        TextField inLastName = new TextField();
        inLastName.prefWidthProperty().bind(pane.widthProperty().divide(6).multiply(5));
        HBox lastNamePane = new HBox(lLastName, inLastName);

        Label lCity = new Label("City:");
        Label lProvince = new Label("Province:");
        Label lPostalCode = new Label("Postal Code:");
        TextField tCity = new TextField();
        final ComboBox tProvince = new ComboBox();
        tProvince.getItems().addAll("Ontario","Manitoba","Quebec");
        TextField tPostalCode = new TextField();
        HBox address = new HBox(lCity, tCity, lProvince, tProvince, lPostalCode, tPostalCode);
        address.setSpacing(5);


        Button addButton = new Button("Add");
        Button firstButton = new Button("First");
        Button nextButton = new Button("Next");
        Button previousButton = new Button("Previous");
        Button lastButton = new Button("Last");
        Button updateButton = new Button("Update");
        addButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        firstButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        nextButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        previousButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        lastButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        updateButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        HBox buttons = new HBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(addButton, firstButton, nextButton, previousButton, lastButton, updateButton);

        pane.getChildren().addAll(firstNamePane, lastNamePane, address, buttons);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
