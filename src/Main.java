import dao.DBAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("CSGO Progress Tracker"); //Placeholder title
        primaryStage.setScene(new Scene(root, 860, 640));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        DBAccess db = new DBAccess();
        db.close();
        System.out.println("stop() got called");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
