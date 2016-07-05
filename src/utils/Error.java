package utils;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import main.Configuration;

public class Error
{
    private Error(){}

    public static void popUpInfo(String title, String message)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        ButtonType button_Ok = new ButtonType("Ok", ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(button_Ok);

        alert.setTitle(title);
        alert.setHeaderText(message);

        alert.showAndWait();
    }
    
    public static void popUpInfo(String title, String message, String info)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        ButtonType button_Ok = new ButtonType("Ok", ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(button_Ok);

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        alert.showAndWait();
    }

    public static boolean popUpWarning(String title, String message, String info)
    {
        Alert alert = new Alert(AlertType.WARNING);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        ButtonType button_Ok = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType button_Can = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(button_Ok, button_Can);

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        Optional<ButtonType> input = alert.showAndWait();

        if (input.get().getButtonData() == ButtonData.OK_DONE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean popUpWarningDetailed(String title, String message, String info, String details)
    {
        Alert alert = new Alert(AlertType.WARNING);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        TextArea textArea = new TextArea(details);
        GridPane gridPane = new GridPane();
        ButtonType button_Ok = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType button_Can = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(button_Ok, button_Can);

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(gridPane);

        Optional<ButtonType> input = alert.showAndWait();

        if (input.get().getButtonData() == ButtonData.OK_DONE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void popUpError(String title, String message, String info)
    {
        Alert alert = new Alert(AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        alert.setOnCloseRequest(new EventHandler<DialogEvent>()
        {
            @Override
            public void handle(DialogEvent arg0)
            {
                System.exit(0);
            }
        });

        alert.showAndWait();
    }
    
    public static void popUpError(String title, String message, String info, EventHandler<DialogEvent> onCloseRequest)
    {
        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        alert.setOnCloseRequest(onCloseRequest);

        alert.showAndWait();
    }
    
    public static void popUpErrorDetailed(String title, String message, String info, String details)
    {
        Alert alert = new Alert(AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        TextArea textArea = new TextArea(details);
        GridPane gridPane = new GridPane();

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(gridPane);

        alert.setOnCloseRequest(new EventHandler<DialogEvent>()
        {
            @Override
            public void handle(DialogEvent arg0)
            {
                System.exit(0);
            }
        });

        alert.showAndWait();
    }
    
    public static void popUpErrorDetailed(String title, String message, String info, String details, EventHandler<DialogEvent> onCloseRequest)
    {
        Alert alert = new Alert(AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(Configuration.icon);
        TextArea textArea = new TextArea(details);
        GridPane gridPane = new GridPane();

        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(info);

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(gridPane);

        alert.setOnCloseRequest(onCloseRequest);

        alert.showAndWait();
    }
}
