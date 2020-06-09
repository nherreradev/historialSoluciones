/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programahistorialsoluciones;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Nicolas
 */
public class ProgramaHistorialSoluciones extends Application {

	@Override
	public void init() throws Exception {

	}

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("FXMLPantallaHistorialSoluciones.fxml"));

		Scene scene = new Scene(root);

		root.setId("pantallaPrincipal");

		String css = ProgramaHistorialSoluciones.class.getResource("EstiloCssProgramaHistorialSoluciones.css")
				.toExternalForm();

		scene.getStylesheets().add(css);

		stage.setScene(scene);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				HibernateUtil.shutdown();
			}
		});

		stage.setResizable(true);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
