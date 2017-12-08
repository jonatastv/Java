package controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import com.sun.glass.events.MouseEvent;

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
import modelo.TipoBanco;

public class Controle implements Initializable {

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtSigla;
    
    @FXML
    private ComboBox<String> comboTipo;
    
    @FXML
    private TextField txtBusca;

    @FXML
    private Button busca;
    
    @FXML
    private MenuItem cadastroCliente;
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtCPF;
    
    @FXML
    private TextField txtTelefone;
    
    @FXML
    private Button btnLimpar;
    
    @FXML
    private TableView<String> tableViewPesquisa;
    
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
    

    private List<String> listClientes;
    private ObservableList<String> observableListClientes;
    

    public void carregarTableViewClientes() {
    	 TipoBancoDAO tbdDAO = new TipoBancoDAO();
    	 this.tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	this.tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	this.tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    	

        listClientes = tbdDAO.testee();

 	  	
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewPesquisa.setItems(observableListClientes);

 	  //	listClientes = tbdDAO.listar();
  	
	   System.out.println(listClientes);
	//   observableListClientes = FXCollections.observableArrayList(listClientes);
	 //  tableViewPesquisa.setItems(observableListClientes);
	   
	   //System.out.println(observableListClientes);

	  	//  this.tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	  //	this.tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

	  //	this.tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		  

	 //  tableViewPesquisa.setPromptText("Qual seu banco de dados ?");
	  
    }
    
    
    @FXML
    void btnAlterarOnAction(ActionEvent event) throws SQLException {
    	 if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
  		   util.Util.mensagemErro("Informe do id banco de dados");
  	   } else {
  			TipoBanco tbd = new TipoBanco();
  		    TipoBancoDAO tbdDAO = new TipoBancoDAO();
  		    Cliente cli = new Cliente();
  		  
  			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
  				cli.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
  			cli.setSt_pv_nome(this.txtSigla.getText());
  			cli.setSt_pv_cpf(this.txtCPF.getText());
  			cli.setIn_pv_telefone(Integer.parseInt(this.txtTelefone.getText()));
  			cli.setIn_pv_ret( tbdDAO.alterar(cli));
			
			if (cli.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
			if (cli.getIn_pv_ret() == 0)
				util.Util.mensagemErro(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
			if (cli.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
    }
   }
   @FXML
    void btnExcluirOnAction(ActionEvent event) throws NumberFormatException, SQLException {
	   if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
		   util.Util.mensagemErro("Informe do id banco de dados");
	   } else {
			TipoBanco tbd = new TipoBanco();
		    TipoBancoDAO tbdDAO = new TipoBancoDAO();

			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				tbd.setTipoBanco_id(Integer.parseInt(this.txtCodigo.getText()));
			
			tbd.setTipoBanco_sigla(this.txtSigla.getText());
			
			tbd.setRet( tbdDAO.excluirTipoID(tbd));
			
			if (tbd.getRet() == 1)
				util.Util.mensagemInformacao(tbd.getMsg()+" "+tbd.getRet());
			if (tbd.getRet() == 0)
				util.Util.mensagemErro(tbd.getMsg()+" "+tbd.getRet());
			if (tbd.getRet() == 2)
				util.Util.mensagemInformacao(tbd.getMsg()+" "+tbd.getRet());
			 
		
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
		   util.Util.mensagemErro("Informe do codigo banco de dados");
	   } else {
		
		   TipoBancoDAO tbdDAO = new TipoBancoDAO();
		   Cliente cli = new Cliente();

			if (!util.Util.stringVaziaOuNula(this.txtBusca.getText()))
				cli.setIn_pv_codigo(Integer.parseInt(this.txtBusca.getText()));

			cli =	tbdDAO.buscaTipoBancoPorId(Integer.parseInt(this.txtBusca.getText()));
			
			// util.Util.mensagemErro((this.txtBusca.getText()));
			 
			 this.txtCodigo.setText(Integer.toString(cli.getIn_pv_codigo()));
			 this.txtSigla.setText(cli.getSt_pv_nome());
			 this.txtCPF.setText(cli.getSt_pv_cpf());
			 this.txtTelefone.setText(Integer.toString(cli.getIn_pv_telefone()));
			 
	   }	
   }

    @FXML
    void btnIncluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtSigla.getText())) {
			util.Util.mensagemErro("Informe o Nome do Cliente");
		
		}
		
		else if ( util.Util.isCPF(this.txtCPF.getText())) {
			util.Util.mensagemErro("CPF invalido ou nulo");
			
		}
		
		else if ( util.Util.stringVaziaOuNula(this.txtTelefone.getText())) {
			util.Util.mensagemErro("Informe o telefone do cliente");
		}
		
		else
		{
			    TipoBancoDAO tbdDAO = new TipoBancoDAO();
			    Cliente cli = new Cliente();
	
			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				cli.setIn_pv_codigo(Integer.parseInt(this.txtCodigo.getText()));
			cli.setSt_pv_nome(this.txtSigla.getText());
			cli.setSt_pv_cpf(this.txtCPF.getText());
			cli.setIn_pv_telefone(Integer.parseInt(this.txtTelefone.getText()));
			cli.setIn_pv_codigo( tbdDAO.incluiTipoBanco(cli));
			
			if (cli.getIn_pv_ret() == 1)
				util.Util.mensagemInformacao(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
			if (cli.getIn_pv_ret() == 0)
				util.Util.mensagemErro(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
			if (cli.getIn_pv_ret() == 2)
				util.Util.mensagemInformacao(cli.getSt_pv_msg()+" "+cli.getIn_pv_ret());
	   }	
		this.limpaTela();
		
    	

    }
    @FXML
    void btnLimparOnAction(ActionEvent event) {
    	this.limpaTela();
    	this.txtCodigo.setText("");
		this.txtSigla.setText("");
		this.txtCPF.setText("");
		this.txtTelefone.setText("");
    	
    }
    
    public void limpaTela()
	{
		this.txtCodigo.setText("");
		this.txtSigla.setText("");
		this.txtCPF.setText("");
		this.txtTelefone.setText("");
	}

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		carregarTableViewClientes() ;
	    comboTipo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()   		
        {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue)
	    	{
	    	    util.Util.mensagemInformacao("Você selecionou o item "+ newvalue);
	    		
	    	}});
	    		
	//	TipoBanco tbd = new TipoBanco();
	//    TipoBancoDAO tbdDAO = new TipoBancoDAO();
	    
	//   listClientes = 	tbdDAO.listar();
	   
	   
	 //  observableListClientes = FXCollections.observableArrayList(listClientes);
//	   comboTipo.setItems(observableListClientes);
		
	    txtSigla.focusedProperty().addListener((v, vv, nv) -> {
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
