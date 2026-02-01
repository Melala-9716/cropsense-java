package com.cropsense.main;

import com.cropsense.gui.view.RoleSelectionView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Set window icon
        stage.getIcons().add(new Image("wheat.png"));

        // Show the RoleSelectionView (this sets up its own Scene with buttons)
        new RoleSelectionView().show(stage);

        // Grab the existing scene and apply light blue background
        Scene scene = stage.getScene();
        if (scene != null) {
            scene.setFill(Color.LIGHTBLUE);
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

