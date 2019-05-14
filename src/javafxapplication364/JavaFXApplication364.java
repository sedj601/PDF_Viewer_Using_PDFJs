/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication364;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author blj0011
 */
public class JavaFXApplication364 extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        String url = getClass().getResource("web/viewer.html").toExternalForm();
        engine.setUserStyleSheetLocation(getClass().getResource("web/viewer.css").toExternalForm());
        engine.setJavaScriptEnabled(true);
        engine.load(url);

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            try {
                byte[] data = Files.readAllBytes(new File("LearnJava.pdf").toPath());
                String base64 = Base64.getEncoder().encodeToString(data);
                engine.executeScript("openFileFromBase64('" + base64 + "')");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(webView, btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
