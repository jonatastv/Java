package controle;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import com.sun.glass.events.MouseEvent;

import application.Main;
import application.MainCadastroServico;
import dao.FuncionarioDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.TipoBanco;

public class Login implements Initializable {

	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private TextField txtSenha;
	
	@FXML
	private Button btnLogar;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Label usuario;
	
	@FXML
	private Label senha;
	
	private ResourceBundle bundle;  //ele vai apontar para seu arquivo .properties
	private Locale locale; // import java.util.Locale classe responsabel por faver a conversão
	
	@FXML
	private void btnPT(ActionEvent event) {
		loadLang("pt");
	}
	
	@FXML
	private void btnIngles(ActionEvent event) {
		loadLang("en");
	}
	
	
	private void loadLang(String lang) { //metodo responsavel para trazer 
		locale = new Locale(lang);
	    bundle = ResourceBundle.getBundle("controle.lang", locale);
	    usuario.setText(bundle.getString("usuario"));
	    senha.setText(bundle.getString("senha"));
	  //  btnLogar.setText(bundle.getString("Logar"));
		
	}
	 
    @FXML
    void btnExitOnAction(ActionEvent event) throws SQLException {
	
	System.exit(0);
    }
    
    
    @FXML
    void btnLogarOnAction(ActionEvent event) throws SQLException {
	
    	   if (util.Util.stringVaziaOuNula(this.txtUsuario.getText())) {
    		   util.Util.mensagemErro("Informe do codigo banco de dados");
    	   }
    		   else if (util.Util.stringVaziaOuNula(this.txtSenha.getText())) {
        		   util.Util.mensagemErro("Informe do codigo banco de dados");
    	   } else {
    		
    		   FuncionarioDAO funDAO = new FuncionarioDAO();
			    Funcionario fun = new Funcionario();
			    
			    if (!util.Util.stringVaziaOuNula(this.txtUsuario.getText()))
			    	fun.setSt_pv_usuario(this.txtUsuario.getText());
			    
			  if  (funDAO.login(this.txtSenha.getText(), this.txtUsuario.getText()) ) {
				  System.out.println("autenticado");
				  Main m = new Main();
			 		m.start(new Stage());
			 		
			  }
			  else {
				  System.out.println("nao autenticado");
			  }
			  //  if (this.txtUsuario.getText().equals(fun.getSt_pv_usuario())); {
			//		System.out.println("usuario valida");
			//	} 
				// util.Util.mensagemErro((this.txtBusca.getText()));
				 
				
    	
    }
    
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
