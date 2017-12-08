package controle;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.Main2;
import application.MainCadastrarMarcaCel;
import application.MainCadastroServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class Menu implements Initializable {

    @FXML
    private MenuItem cadastroCliente;
    
    @FXML
    private MenuItem cadastroMarcaCel;
    
    @FXML
    private MenuItem cadastroServico;
    
    
    @FXML
    void cadastroClienteOnAction(ActionEvent event) 
    {
 	   System.out.println("aqui");
 	  //cadastroCliente.setOnAction(ActionEvent -> Platform.exit());
		Main2 m = new Main2();
		m.start(new Stage());
		
    }
    
    
    @FXML
    void cadastroMarcaCelOnAction(ActionEvent event) 
    {
  	   System.out.println("Cadastro marca");
  	  //cadastroCliente.setOnAction(ActionEvent -> Platform.exit());
 		MainCadastrarMarcaCel m = new MainCadastrarMarcaCel();
 		m.start(new Stage());
 		
     }
     
    
    @FXML
    void cadastroServicoOnAction(ActionEvent event) 
    {
  	   System.out.println("cadastrar servico");
  	  //cadastroCliente.setOnAction(ActionEvent -> Platform.exit());
 		MainCadastroServico m = new MainCadastroServico();
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
