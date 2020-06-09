/*				
 * To change this license header, choose License Headers in Project Properties.				
 * To change this template file, choose Tools | Templates				
 * and open the template in the editor.				
 */
package programahistorialsoluciones;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import paquetenuevoparatextarea.FXMLControllerParaTextArea;
import static programahistorialsoluciones.FXMLDocumentController.posicionSeleccionada;

/**
 *
 * @author Nicolas
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private Label label;

	@FXML
	TextField campoFiltro = new TextField();

	@FXML
	Button botonAgregarSolucion;

	@FXML
	Button agregarInformacion;

	@FXML
	Button eliminarInformacion;

	@FXML
	Button modificarInformacion;

	@FXML
	TextField campoTitulo;

	@FXML
	TextField campoBreve;

	@FXML
	TextArea campoAmpliada = new TextArea();

	/*-----------------Lista Observable y tableView---------------*/
	@FXML
	TableView<ProblemaConformado> tabla = new TableView<>(); // ok

	@FXML
	ObservableList<ProblemaConformado> listaObservableMaster; // ok falta atributos privados

	@FXML
	ObservableList<ProblemaConformado> listaObservableFiltro; // ok falta atributos privados

	@FXML
	TableColumn<ProblemaConformado, String> colTitulo = new TableColumn<>();

	@FXML
	TableColumn<ProblemaConformado, String> colBreve = new TableColumn<>();

	@FXML
	TableColumn<ProblemaConformado, Integer> colIdSolucion = new TableColumn<>();

	@FXML
	private void handleButtonAction(ActionEvent event) {
		agregarSolucion();
	}

	private void cargarTabla() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(ProblemaConformado.class);

		List<ProblemaConformado> listaCriteria = criteria.list();

		for (ProblemaConformado problemaConformadoi : listaCriteria) {
			listaObservableMaster.add(new ProblemaConformado(problemaConformadoi.getIdSolucion(),
					problemaConformadoi.getTituloProblema(), problemaConformadoi.getDescripcionBreve()));
		}

		listaObservableFiltro.addAll(listaObservableMaster);

		listaObservableMaster.addListener(new ListChangeListener<ProblemaConformado>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends ProblemaConformado> c) {

			}
		});

	} // ok

	public void agregarSolucion() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		ProblemaConformado problemaConformado = new ProblemaConformado(campoTitulo.getText(), campoBreve.getText(),
				campoAmpliada.getText());

		listaObservableMaster.add(problemaConformado);

		Object problemaConformadoObjeto = session.save(problemaConformado);

		session.getTransaction().commit();

		campoTitulo.setText("");
		campoBreve.setText("");
		campoAmpliada.setText("");

		actualizarDatosListaFiltrada();

	}

	private void reOrdenarLaTablaEnOrden() {
		ArrayList<TableColumn<ProblemaConformado, ?>> sortOrder = new ArrayList<>(tabla.getSortOrder());
		tabla.getSortOrder().clear();
		tabla.getSortOrder().addAll(sortOrder);
	} // ok

	private void actualizarDatosListaFiltrada() {

		listaObservableFiltro.clear();

		for (ProblemaConformado problemaConformado : listaObservableMaster) {
			if (coincidenciasFiltradas(problemaConformado)) {
				listaObservableFiltro.add(problemaConformado);
			}
		}

		reOrdenarLaTablaEnOrden();
	}

	public boolean coincidenciasFiltradas(ProblemaConformado problema) {

		String textoAFiltrar = campoFiltro.getText();

		if (textoAFiltrar == null || textoAFiltrar.isEmpty()) {

			return true;

		}

		String textoAFiltrarEnMinuscula = textoAFiltrar.toLowerCase();

		if (problema.getTituloProblema().toLowerCase().contains(textoAFiltrarEnMinuscula)) {
			return true;
		} else if (problema.getDescripcionBreve().toLowerCase().contains(textoAFiltrarEnMinuscula)) {
			return true;
		}

		return false;

	}

	static int posicionSeleccionada;
	int posicionSeleccionadaNoStatic;

	public static void traerDatosDeArrayList(ArrayList<String> listaQueGuardaLosDatosParaEnviarALaClaseDelTextArea) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(ProblemaConformado.class);

		List<ProblemaConformado> listaIdSolucionDeBaseDatos = criteria.list();

		for (ProblemaConformado listaIdSolucionDeBaseDatosi : listaIdSolucionDeBaseDatos) {

			if (idSolucionTabla == listaIdSolucionDeBaseDatosi.getIdSolucion()) {

				listaQueGuardaLosDatosParaEnviarALaClaseDelTextArea
						.add(listaIdSolucionDeBaseDatosi.getDescripcionAmplia());

			}

		}
	}

	public static int idSolucionTabla;

	@FXML
	public void agregarDescripcionAmpliaATextArea(ActionEvent event) throws IOException {

		idSolucionTabla = tabla.getSelectionModel().getSelectedItems().get(posicionSeleccionada).getIdSolucion();

		abrirVentanaDescripcionAmpliada();

	}

	@FXML
	public void eliminarSolucion(ActionEvent event) throws IOException {

		idSolucionTabla = tabla.getSelectionModel().getSelectedItems().get(posicionSeleccionada).getIdSolucion();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(ProblemaConformado.class);

		List<ProblemaConformado> listaParaEliminarSolucion = criteria.list();

		for (ProblemaConformado problemaConformadoi : listaParaEliminarSolucion) {

			if (idSolucionTabla == problemaConformadoi.getIdSolucion()) {

				session.delete(problemaConformadoi);

			}

		}

		session.getTransaction().commit();

		ProblemaConformado selectedItem = tabla.getSelectionModel().getSelectedItem();
		tabla.getItems().remove(selectedItem);

		refrescarTabla();

	}

	public void refrescarTabla() {

		tabla.getItems().clear();

		listaObservableMaster.removeAll(listaObservableMaster);

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(ProblemaConformado.class);

		List<ProblemaConformado> listaParaReLlenarElTableView = criteria.list();

		for (ProblemaConformado problemaConformadoi : listaParaReLlenarElTableView) {

			listaObservableMaster.add(new ProblemaConformado(problemaConformadoi.getIdSolucion(),
					problemaConformadoi.getTituloProblema(), problemaConformadoi.getDescripcionBreve()));

			tabla.getItems().add(new ProblemaConformado(problemaConformadoi.getIdSolucion(),
					problemaConformadoi.getTituloProblema(), problemaConformadoi.getDescripcionBreve()));

		}

		session.getTransaction().commit();

	}

	@FXML
	public void modificarSolucion(ActionEvent event) {

		idSolucionTabla = tabla.getSelectionModel().getSelectedItems().get(posicionSeleccionada).getIdSolucion();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(ProblemaConformado.class);

		List<ProblemaConformado> listaParaEliminarSolucion = criteria.list();

		for (ProblemaConformado problemaConformadoi : listaParaEliminarSolucion) {

			if (idSolucionTabla == problemaConformadoi.getIdSolucion()) {

				String dataTitulo = campoTitulo.getText().trim();// read contents of text area into 'data'
				if (!dataTitulo.equals("")) {
					problemaConformadoi.setTituloProblema(dataTitulo);
				}

				String dataBreve = campoBreve.getText().trim();// read contents of text area into 'data'
				if (!dataBreve.equals("")) {
					problemaConformadoi.setDescripcionBreve(dataBreve);
				}

				String dataAmpliada = campoAmpliada.getText().trim();// read contents of text area into 'data'
				if (!dataAmpliada.equals("")) {
					problemaConformadoi.setDescripcionAmplia(dataAmpliada);
				}

				session.update(problemaConformadoi);

			}

		}

		session.getTransaction().commit();

		/* Funciona recordar aplicar try catch y ver que actualice la lista */
		refrescarTabla();

		campoTitulo.setText("");
		campoBreve.setText("");
		campoAmpliada.setText("");
	}

	public void abrirVentanaDescripcionAmpliada() throws IOException {

		Parent root = FXMLLoader.load(FXMLControllerParaTextArea.class.getResource("FXMLPantallaAmpliada.fxml"));

		Scene scene = new Scene(root);

		Stage stage = new Stage();

		stage.setScene(scene);

		stage.setResizable(false);

		stage.show();

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		campoAmpliada.setWrapText(true);

		listaObservableMaster = FXCollections.observableArrayList();
		listaObservableFiltro = FXCollections.observableArrayList();

		tabla.setItems(listaObservableMaster);
		tabla.setItems(listaObservableFiltro);
		tabla.setEditable(true);

		campoFiltro.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				actualizarDatosListaFiltrada();
			}
		});

		colTitulo.setCellValueFactory(new PropertyValueFactory<>("tituloProblema"));

		colBreve.setCellValueFactory(new PropertyValueFactory<>("descripcionBreve"));

		colIdSolucion.setCellValueFactory(new PropertyValueFactory<>("idSolucion"));

		cargarTabla();

	}

}
