//não esquecer:
//01. verificar a conexão com o banco para efetuar login
//02. inserir label nos campos do login...

package application;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	
	public Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			//setUserAgentStylesheet(STYLESHEET_MODENA);
			//AquaFx.style();
			//stage = primaryStage;
			AnchorPane root = new AnchorPane();
    		root =   FXMLLoader.load(getClass().getResource("/View/menu.fxml"));

			//javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root);//,screenBounds.getWidth(),screenBounds.getHeight());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu");
			primaryStage.show();
			
			
			stage = new Stage();
			//Parent rootx = FXMLLoader.load(getClass().getResource("/View/Cadastro01.fxml"));
			Parent rootx= FXMLLoader.load(getClass().getResource("/View/Cadastro01.fxml"));
			//Parent page = FXMLLoader.<Parent>load(Main.class.getResource("View/Cadastro01.fxml").toExternalForm());
			Scene scenex = new Scene(rootx);
			primaryStage = new Stage();
			primaryStage.setScene(scenex);
			primaryStage.setTitle("Nova Janela");
			stage.initModality(Modality.APPLICATION_MODAL);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start1(Stage primaryStage) {
		try {
			//setUserAgentStylesheet(STYLESHEET_MODENA);
			//AquaFx.style();
			//stage = primaryStage;
			AnchorPane root = new AnchorPane();
    		root =   FXMLLoader.load(getClass().getResource("/View/menu.fxml"));

			//javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root);//,screenBounds.getWidth(),screenBounds.getHeight());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu");
			primaryStage.show();
			
			
			stage = new Stage();
			//Parent rootx = FXMLLoader.load(getClass().getResource("/View/Cadastro01.fxml"));
			Parent rootx= FXMLLoader.load(getClass().getResource("/View/Cadastro01.fxml"));
			//Parent page = FXMLLoader.<Parent>load(Main.class.getResource("View/Cadastro01.fxml").toExternalForm());
			Scene scenex = new Scene(rootx);
			primaryStage = new Stage();
			primaryStage.setScene(scenex);
			primaryStage.setTitle("Nova Janela");
			stage.initModality(Modality.APPLICATION_MODAL);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
