package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Game;
import dao.DBAccess;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for main UI screen
 */
public class MainControl implements Initializable {
    @FXML
    private ListView listView;

    List<Game> gameList;

    DBAccess db;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        db = new DBAccess();
        gameList = new ArrayList<>();
        //Get all games in DB and add to list
        try {
            db.fillList(gameList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        listView.setItems(FXCollections.observableList(gameList));
    }
}
