package controle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import com.sun.glass.events.MouseEvent;

import application.Main;
import application.Main2;
import dao.TipoBancoDAO;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.TipoBanco;

public class Menu implements Initializable {

    @FXML
    private MenuItem cadastroCliente;
    
    @FXML
    void cadastroClienteOnAction(ActionEvent event) {
 	   System.out.println("aqui");
 	  //cadastroCliente.setOnAction(ActionEvent -> Platform.exit());
		Main2 m = new Main2();
		m.start(new Stage());
		
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    //	cadastroCliente.setOnAction((MouseEvent e)->{
    		
    //	});
    	
		//Main m = new Main();
		//m.start(new Stage());
	}
	
}
