/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programahistorialsoluciones;

import java.io.IOException;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nicolas
 */
public class FirstPreLoader extends Preloader {

    
    Stage stage;
    //ProgressBar pb;
    
   

    private Scene createPreloaderScene() throws IOException {
        String url = getClass().getResource("codeIsFunChico.jpg").toExternalForm();
        ImageView progress = new ImageView(url);

       //pb = new ProgressBar();
        
        VBox root = new VBox();
        root.setSpacing(10.0);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(progress);

        BorderPane pane = new BorderPane(root);

       //pane.setTop(pb);

        return new Scene(pane, 640, 361, Color.TRANSPARENT);
    }

    @Override
    public void start(Stage stage) throws Exception {
      

        this.stage = stage;

        stage.initStyle(StageStyle.UTILITY);
        
        
        
        stage.setScene(createPreloaderScene());
        stage.show();

    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        //pb.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
