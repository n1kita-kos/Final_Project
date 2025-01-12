package com.example.prog_j;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

    public class App extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            stage.setTitle("Application");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }