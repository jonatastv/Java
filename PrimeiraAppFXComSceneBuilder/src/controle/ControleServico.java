
package controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import com.sun.glass.events.MouseEvent;

import dao.ServicoDAO;

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

import modelo.Servico;


public class ControleServico  implements Initializable {

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescricao;
    

    
    @FXML
    private TextField txtBusca;

    @FXML
    private Button busca;
    
    @FXML
    private MenuItem cadastroCliente;
    

    
    @FXML
    private TextField txtValor;
    
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
  		   util.Util.mensagemErro("Informe o serviço");
  	   } else {
  			
		    ServicoDAO serDAO = new ServicoDAO();
		    Servico ser = new Servico();
  		  
  			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
  				ser.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
  			ser.setSt_pv_descricao(this.txtDescricao.getText());
  			ser.setDb_pv_valor(Double.parseDouble(this.txtValor.getText()));
  			ser.setSt_pv_cor(this.txtCor.getText());
  			ser.setIn_pv_ret( serDAO.alterar(ser));
			
			if (ser.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 0)
				util.Util.mensagemErro(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
    }
   }
   @FXML
    void btnExcluirOnAction(ActionEvent event) throws NumberFormatException, SQLException {
	   if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
		   util.Util.mensagemErro("Informe do id banco de dados");
	   } else {
		   
		   ServicoDAO serDAO = new ServicoDAO();
		    Servico ser = new Servico();

			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				ser.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
			
			ser.setSt_pv_descricao(this.txtDescricao.getText());
			
			ser.setIn_pv_ret( serDAO.excluirTipoID(ser));
			
			if (ser.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 0)
				util.Util.mensagemErro(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			 
		
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
		
		    ServicoDAO serDAO = new ServicoDAO();
		    Servico ser = new Servico();
		   

			if (!util.Util.stringVaziaOuNula(this.txtBusca.getText()))
				ser.setIn_pv_codigo(Integer.parseInt(this.txtBusca.getText()));

			ser =	serDAO.buscaTipoBancoPorId(Integer.parseInt(this.txtBusca.getText()));
	 
			 this.txtCodigo.setText(Integer.toString(ser.getIn_pv_codigo()));
			 this.txtDescricao.setText(ser.getSt_pv_descricao());
			 this.txtValor.setText(Double.toString(ser.getDb_pv_valor()));
			 
			 this.txtCor.setText(ser.getSt_pv_cor());
			 
			 
	   }	
   }

    @FXML
    void btnIncluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtDescricao.getText())) {
			util.Util.mensagemErro("Informe A Descrição do serviço");
		
		}
		
		else if ( util.Util.validaConteudoDouble(this.txtValor.getText())) {
			util.Util.mensagemErro("Valor não valido");
			
		}
		
		else if ( util.Util.stringVaziaOuNula(this.txtCor.getText())) {
			util.Util.mensagemErro("Informe o telefone do cliente");
		}
		
		else
		{
			  
		    ServicoDAO serDAO = new ServicoDAO();
		    Servico ser = new Servico();
	
			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				ser.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
			ser.setSt_pv_descricao(this.txtDescricao.getText());
			ser.setDb_pv_valor(Double.parseDouble(this.txtValor.getText()));
			ser.setSt_pv_cor(this.txtCor.getText());
			ser.setIn_pv_codigo( serDAO.incluiTipoBanco(ser));
			
			if (ser.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 0)
				util.Util.mensagemErro(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
			if (ser.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(ser.getSt_pv_msg()+" "+ser.getIn_pv_ret());
	   }	
		this.limpaTela();
		
    	

    }
    @FXML
    void btnLimparOnAction(ActionEvent event) {
    	this.limpaTela();
    	this.txtCodigo.setText("");
		this.txtDescricao.setText("");
		this.txtValor.setText("");
		this.txtCor.setText("");
    	
    }
    
    public void limpaTela()
	{
		this.txtCodigo.setText("");
		this.txtDescricao.setText("");
		this.txtValor.setText("");
		this.txtCor.setText("");
	}

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("erro aqui");
	  

		
	    txtDescricao.focusedProperty().addListener((v, vv, nv) -> {
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
