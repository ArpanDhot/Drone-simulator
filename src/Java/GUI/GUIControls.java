package Java.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GUIControls {

    static String font_style = "Arcade Classic";

    //Preset method for button
    static Button getButton(String name,int font_size, double height, double width, double y_pos, double x_pos, EventHandler<ActionEvent> eventHandler){
        Button button = new Button(name);
        button.setFont(Font.font(font_style, font_size > 22 ? FontWeight.BOLD : FontWeight.NORMAL,font_size));
        button.setPrefSize(width,height);
        button.setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        button.setLayoutY(y_pos);
        button.setLayoutX(x_pos);
        button.setTextFill(Color.BLACK);
        button.setOnAction(eventHandler);
        return button;
    }

    static Label getLabel(String name, Pos align, int font_size, Color color, double height, double width, double yPos, double xPos){
        Label label = new Label(name);
        label.setFont(Font.font(font_style, font_size > 22 ? FontWeight.BOLD : FontWeight.NORMAL,font_size));
        label.setTextFill(color);
        label.setPrefSize(width, height);
        label.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        label.setLayoutX(xPos);
        label.setLayoutY(yPos);
        return label;
    }

    static TextField getTextField(String hint, int font_size, double width, double height, double x_pos, double y_pos){
        TextField textField = new TextField();
        textField.setPromptText(hint);
        textField.setFont(Font.font(font_style, font_size > 22 ? FontWeight.BOLD : FontWeight.NORMAL,font_size));
        textField.setPrefSize(width, height);
        textField.setLayoutX(x_pos);
        textField.setLayoutY(y_pos);
        return textField;
    }

    static HBox getHorizontalBox(Pos align, int spacing, double width, double height , double layout_x, double layout_y){
        HBox box = new HBox();
        box.setAlignment(align);
        box.setSpacing(spacing);
        box.setPrefSize(width, height);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setLayoutX(layout_x);
        box.setLayoutY(layout_y);
        return box;
    }


}
