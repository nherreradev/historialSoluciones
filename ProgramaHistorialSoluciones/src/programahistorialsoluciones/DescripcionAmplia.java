/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programahistorialsoluciones;

import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nicolas
 */

@Table(name = "valorsiempreactualizado")
public class DescripcionAmplia {

	private StringProperty descripcionAmplia = new SimpleStringProperty();
	private IntegerProperty idSolucion = new SimpleIntegerProperty();

	public DescripcionAmplia() {
	}

	public DescripcionAmplia(String descripcionAmplia, int idSolucion) {

		this.descripcionAmplia = new SimpleStringProperty(descripcionAmplia);
		this.idSolucion = new SimpleIntegerProperty(idSolucion);
	}

	public String getDescripcionAmplia() {
		return descripcionAmplia.get();
	}

	public void setDescripcionAmplia(String descripcionAmplia) {
		this.descripcionAmplia.set(descripcionAmplia);
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
