//n�o esquecer:
//01. verificar a conex�o com o banco para efetuar login
//02. inserir label nos campos do login...

package application;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainCadastroServico extends Application {
	
	//public Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			//setUserAgentStylesheet(STYLESHEET_MODENA);
			//AquaFx.style();
			//stage = primaryStage;
			AnchorPane root = new AnchorPane();
  		root =   FXMLLoader.load(getClass().getResource("/view/CadastroServico.fxml"));

			//javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root);//,screenBounds.getWidth(),screenBounds.getHeight());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro de Servi�o");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
