/*				
 * To change this license header, choose License Headers in Project Properties.				
 * To change this template file, choose Tools | Templates				
 * and open the template in the editor.				
 */				
package paquetenuevoparatextarea;				
				
import programahistorialsoluciones.*;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;				
import org.hibernate.Session;				
				
/**				
 *				
 * @author Nicolas				
 */				
public class FXMLControllerParaTextArea implements Initializable {				
	
    @FXML
    TextArea area = new TextArea();
    
    FXMLDocumentController link = new FXMLDocumentController();
    
    ArrayList<String> listaQueGuardaLosDatosParaEnviarALaClaseDelTextArea = new ArrayList<>();
    
    String textoParaTextArea;
    
    public void agregarTextoAArea(){
    
   FXMLDocumentController.traerDatosDeArrayList(listaQueGuardaLosDatosParaEnviarALaClaseDelTextArea);
        
   textoParaTextArea = listaQueGuardaLosDatosParaEnviarALaClaseDelTextArea.get(0);
    
        area.appendText(textoParaTextArea);
    
    }
   				
    @Override				
    public void initialize(URL url, ResourceBundle rb) {
        
        area.setWrapText(true);
        
        agregarTextoAArea();
       
    }				
				
}				
