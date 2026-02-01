package com.cropsense.main;

import com.cropsense.gui.view.RoleSelectionView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image("wheat.png"));
        new RoleSelectionView().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
