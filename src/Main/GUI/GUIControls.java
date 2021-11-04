package Main.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class GUIControls {

    static String font_style = "Arcade Classic";

    //Preset method for button
    static Button getButton(String name,int font_size, double H, double W, double yPos, double xPos, EventHandler<ActionEvent> eventHandler){
        Button button = new Button(name);
        button.setFont(Font.font(font_style, font_size));
        button.setPrefSize(W,H);
        button.setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        button.setLayoutY(yPos);
        button.setLayoutX(xPos);
        button.setTextFill(Color.BLACK);
        return button;
    }

    static Label getLabel(String name, int font_size, Color color, double H, double W, double yPos, double xPos){
        Label label = new Label(name);
        label.setFont(Font.font(font_style, font_size));
        label.setTextFill(color);
        label.setPrefSize(W, H);
        label.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        label.setLayoutX(xPos);
        label.setLayoutY(yPos);
        return label;
    }

    static TextField getTextField(String hint, int font_size, double width, double height, double x_pos, double y_pos){
        TextField textField = new TextField();
        textField.setPromptText(hint);
        textField.setFont(Font.font(font_style, font_size));
        textField.setPrefSize(width, height);
        textField.setLayoutX(x_pos);
        textField.setLayoutY(y_pos);
        return textField;
    }


}