/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programahistorialsoluciones;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nicolas
 */
public class ProblemaConformado {

	private IntegerProperty idSolucion = new SimpleIntegerProperty();
	private StringProperty tituloProblema = new SimpleStringProperty();
	private StringProperty descripcionBreve = new SimpleStringProperty();
	private StringProperty descripcionAmplia = new SimpleStringProperty();

	public ProblemaConformado() {
	}

	public ProblemaConformado(String tituloProblema, String descripcionBreve, String descripcionAmplia) {
		this.tituloProblema = new SimpleStringProperty(tituloProblema);
		this.descripcionBreve = new SimpleStringProperty(descripcionBreve);
		this.descripcionAmplia = new SimpleStringProperty(descripcionAmplia);
	}

	public ProblemaConformado(int idSolucion, String tituloProblema, String descripcionBreve) {
		this.tituloProblema = new SimpleStringProperty(tituloProblema);
		this.descripcionBreve = new SimpleStringProperty(descripcionBreve);
		this.idSolucion = new SimpleIntegerProperty(idSolucion);

	}

	public ProblemaConformado(int idSolucion) {

		this.idSolucion = new SimpleIntegerProperty(idSolucion);

	}

	public String getTituloProblema() {
		return tituloProblema.get();
	}

	public void setTituloProblema(String tituloProblema) {
		this.tituloProblema.set(tituloProblema);
	}

	public String getDescripcionBreve() {
		return descripcionBreve.get();
	}

	public void setDescripcionBreve(String descripcionBreve) {
		this.descripcionBreve.set(descripcionBreve);
	}

	public String getDescripcionAmplia() {
		return descripcionAmplia.get();
	}

	public void setDescripcionAmplia(String descripcionAmplia) {
		this.descripcionAmplia.set(descripcionAmplia);
	}

	public StringProperty getTituloProblemaProperty() {
		return tituloProblema;
	}

	public StringProperty getDescripcionBreveProperty() {
		return descripcionBreve;
	}

	public StringProperty getDescripcionAmpliaProperty() {
		return descripcionAmplia;
	}

	public int getIdSolucion() {
		return idSolucion.get();
	}

	public void setIdSolucion(int idSolucion) {
		this.idSolucion.set(idSolucion);
	}

	public IntegerProperty getIdSolucionProperty() {
		return idSolucion;
	}

}
