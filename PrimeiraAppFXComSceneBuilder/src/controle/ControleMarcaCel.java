
package controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import com.sun.glass.events.MouseEvent;

import dao.MarcaCelDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.MarcaCel;
import modelo.TipoBanco;

public class ControleMarcaCel  implements Initializable {

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtMarca;
    

    
    @FXML
    private TextField txtBusca;

    @FXML
    private Button busca;
    
    @FXML
    private MenuItem cadastroCliente;
    

    
    @FXML
    private TextField txtModelo;
    
    @FXML
    private TextField txtCor;
    
    @FXML
    private Button btnLimpar;
    
    @FXML
    private TableView<Cliente> tableViewPesquisa;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnCPF;
    
    @FXML
    private TableColumn<Cliente, Integer> tableColumnTelefone;
    
    @FXML
    private TableColumn<Cliente, Integer> tableColumnCodigo;
    

    

  //  private List<String> listClientes;
  //  private ObservableList<String> observableListClientes;
    

   
    
    
    @FXML
    void btnAlterarOnAction(ActionEvent event) throws SQLException {
    	 if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
  		   util.Util.mensagemErro("Informe do id marcaCel");
  	   } else {
  			
		    MarcaCelDAO mclDAO = new MarcaCelDAO();
		    MarcaCel cel = new MarcaCel();
  		  
  			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
  				cel.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
  			cel.setSt_pv_marca(this.txtMarca.getText());
  			cel.setSt_pv_modelo(this.txtModelo.getText());
  			cel.setSt_pv_cor(this.txtCor.getText());
  			cel.setIn_pv_ret( mclDAO.alterar(cel));
			
			if (cel.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 0)
				util.Util.mensagemErro(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
    }
   }
   @FXML
    void btnExcluirOnAction(ActionEvent event) throws NumberFormatException, SQLException {
	   if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
		   util.Util.mensagemErro("Informe do id banco de dados");
	   } else {
		    MarcaCelDAO mclDAO = new MarcaCelDAO();
		    MarcaCel cel = new MarcaCel();

			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				cel.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
			
			cel.setSt_pv_marca(this.txtMarca.getText());
			
			cel.setIn_pv_ret( mclDAO.excluirTipoID(cel));
			
			if (cel.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 0)
				util.Util.mensagemErro(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			 
		
	   		}	
	   this.limpaTela();

			 
	   }	
		   
   @FXML
   void cadastroClienteOnAction(ActionEvent event) {
	   System.out.println("aqui");
   }
    
   
   @FXML
   void btnBuscar(ActionEvent event) throws SQLException {
	   if (util.Util.stringVaziaOuNula(this.txtBusca.getText())) {
		   util.Util.mensagemErro("Informe do codigo da marca");
	   } else {
		
		    MarcaCelDAO mclDAO = new MarcaCelDAO();
		    MarcaCel cel = new MarcaCel();
		   

			if (!util.Util.stringVaziaOuNula(this.txtBusca.getText()))
				cel.setIn_pv_codigo(Integer.parseInt(this.txtBusca.getText()));

			cel =	mclDAO.buscaTipoBancoPorId(Integer.parseInt(this.txtBusca.getText()));
	 
			 this.txtCodigo.setText(Integer.toString(cel.getIn_pv_codigo()));
			 this.txtMarca.setText(cel.getSt_pv_marca());
			 this.txtModelo.setText(cel.getSt_pv_modelo());
			 this.txtCor.setText(cel.getSt_pv_cor());
			 
	   }	
   }

    @FXML
    void btnIncluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtMarca.getText())) {
			util.Util.mensagemErro("Informe o Nome do Cliente");
		
		}
		
		else if ( util.Util.stringVaziaOuNula(this.txtModelo.getText())) {
			util.Util.mensagemErro("CPF invalido ou nulo");
			
		}
		
		else if ( util.Util.stringVaziaOuNula(this.txtCor.getText())) {
			util.Util.mensagemErro("Informe o telefone do cliente");
		}
		
		else
		{
			  
			    MarcaCelDAO mclDAO = new MarcaCelDAO();
			    MarcaCel cel = new MarcaCel();
	
			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				cel.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
			cel.setSt_pv_marca(this.txtMarca.getText());
			cel.setSt_pv_modelo(this.txtModelo.getText());
			cel.setSt_pv_cor(this.txtCor.getText());
			cel.setIn_pv_codigo( mclDAO.incluiTipoBanco(cel));
			
			if (cel.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 0)
				util.Util.mensagemErro(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
			if (cel.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(cel.getSt_pv_msg()+" "+cel.getIn_pv_ret());
	   }	
		this.limpaTela();
		
    	

    }
    @FXML
    void btnLimparOnAction(ActionEvent event) {
    	this.limpaTela();
    	this.txtCodigo.setText("");
		this.txtMarca.setText("");
		this.txtModelo.setText("");
		this.txtCor.setText("");
    	
    }
    
    public void limpaTela()
	{
		this.txtCodigo.setText("");
		this.txtMarca.setText("");
		this.txtModelo.setText("");
		this.txtCor.setText("");
	}

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("erro aqui");
	  

		
	    txtMarca.focusedProperty().addListener((v, vv, nv) -> {
	           if (!nv) 
	           { // perde o foco
	        	 //util.Util.mensagemErro("desfocou");
	           }
	           else
	           {
	        	   //System.out.println("*");
	           }

        });	
	//   alimentaComboTipo();
	
	
	}
  

}
