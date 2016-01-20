package dao;
import model.Game;

import java.sql.*;
import java.util.List;

//TODO: Player table to track rank/overall stats

public class DBAccess {
    private static Connection conn;

    private static boolean connected = false;

    /*
     * Connect to TB
     * TODO: Change to embedded mode
     */
    public void connect() throws SQLException,ClassNotFoundException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        Statement test = conn.createStatement();
        connected = true;
        //Create Table
        test.execute("CREATE TABLE IF NOT EXISTS GAME \n" +
                "    (ID INT NOT NULL, KILLS INT, DEATHS INT, RW INT, RL INT, MVP INT, ASSISTS INT, \n" +
                "    PRIMARY KEY (ID))");
    }

    /*
     * Initialize the database
     */
    public void init()  {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            Statement test = conn.createStatement();
            //Create Table
            test.execute("CREATE TABLE IF NOT EXISTS GAME \n" +
                    "    (ID INT NOT NULL, KILLS INT, DEATHS INT, RW INT, RL INT, MVP INT, \n" +
                    "    PRIMARY KEY (ID))");
            connected = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Close connection
     */
    public void close() throws SQLException {
        if(connected) {
            connected = false;
            conn.close();
        }
    }

    /*
     * Add new game to DB
     */
    public void addGame(Game toAdd) throws SQLException {
        if(!connected)
            System.out.println("TODO: Connect");//connect
        if(connected) {
            Statement test = conn.createStatement();
            test.execute("INSERT INTO GAME" +
                    "\n VALUES" + toAdd.getValues());
        }
    }

    /*
     * Fill List with game objects from DB
     */
    public void fillList(List<Game> current) throws SQLException {
        if(!connected) {
            try {
                //Establish a connection
                connect();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("Connect fill");
        }
        if(connected) {
            Statement test = conn.createStatement();
            //Create Table
            ResultSet rs = test.executeQuery("SELECT * FROM GAME ORDER BY ID DESC");
            while(rs.next()) {
                current.add(new Game(rs.getInt("ID"), rs.getInt("KILLS"), rs.getInt("DEATHS"), rs.getInt("ASSISTS"), rs.getInt("RW"), rs.getInt("RL"), rs.getInt("MVP")));
            }
        }

    }
}
