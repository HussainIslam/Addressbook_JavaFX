package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.RandomAccessFile;

public class Main extends Application {
    final int TOKEN_LENGTH = 20;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating RandomAccessFile object with the file name
        File file = new File("addressBook.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

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
        addButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        addButton.setOnAction(e -> {
            try{
                //throws an Exception if anyone of the fields is empty
                if( inFirstName.getText().equals("") || inLastName.getText().equals("") ||
                    tCity.getText().equals("") || tProvince.getValue().toString().equals("") ||
                    tPostalCode.getText().equals("")){
                    throw new Exception();
                }
                //retrieving data from the input fields
                String paddedFirstName = String.format("%-"+TOKEN_LENGTH+"s", inFirstName.getText());
                String paddedLastName = String.format("%-"+TOKEN_LENGTH+"s", inLastName.getText());
                String paddedCity = String.format("%-"+TOKEN_LENGTH+"s", tCity.getText());
                String paddedProvince = String.format("%-"+TOKEN_LENGTH+"s", tProvince.getValue().toString());
                String paddedPostalCode = String.format("%-"+TOKEN_LENGTH+"s", tPostalCode.getText());
                long length = raf.length();
                raf.setLength(length + 1);
                raf.seek(length);

                raf.writeChars(paddedFirstName);
                raf.writeChars(paddedLastName);
                raf.writeChars(paddedCity);
                raf.writeChars(paddedProvince);
                raf.writeChars(paddedPostalCode);
                raf.writeChar('\n');



                this.showAlert(Alert.AlertType.CONFIRMATION, "Successful", "Operation Successful", "Data was successfully written to file");
            }
            catch (Exception ex){
                this.showAlert(Alert.AlertType.ERROR, "Error", "Error parsing user input", "Please check the data set and try again.");
            }


        });

        Button firstButton = new Button("First");
        firstButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        firstButton.setOnAction(e -> {
            System.out.println("WOrking from first button");
        });


        Button nextButton = new Button("Next");
        nextButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        nextButton.setOnAction(e -> {
            System.out.println("Working from next button");
        });


        Button previousButton = new Button("Previous");
        previousButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        previousButton.setOnAction(event -> {
            System.out.println("Working from previous button");
        });


        Button lastButton = new Button("Last");
        lastButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        lastButton.setOnAction(event -> {
            System.out.println("Working from last button");
        });


        Button updateButton = new Button("Update");
        updateButton.prefWidthProperty().bind(pane.widthProperty().divide(6));
        updateButton.setOnAction(event -> {
            System.out.println("Working from update button");
        });


        HBox buttons = new HBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(addButton, firstButton, nextButton, previousButton, lastButton, updateButton);



        pane.getChildren().addAll(firstNamePane, lastNamePane, address, buttons);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
