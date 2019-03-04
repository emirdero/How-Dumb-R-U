package sample;

import Connection.ConnectionClass;
import Connection.Cleaner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ControllerButton extends ChangeScene{

    @FXML
    public TextField textField;
    public TextField email;
    public TextField password;

    public void sceneInfo(ActionEvent event) throws IOException { //trykker på infoknapp
       super.change(event, "Info.fxml"); //bruker super-metode
    }

    public void sceneHome(ActionEvent event) throws IOException { //hjemknapp
        super.change(event, "Main.fxml"); //bruker super-metode
    }

    public void register(ActionEvent event) throws IOException { //trykker registrer
        super.change(event, "Register.fxml"); //bruker super-metode
    }

    public void feedback(ActionEvent event) throws IOException { //feedback knapp
        super.change(event, "Feedback.fxml"); //bruker super-metode
    }

    public void reg() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        Cleaner cleaner = new Cleaner();

        String sql = "INSERT INTO navn VALUES('" + textField.getText() + "')";
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void playerLogin(ActionEvent event) throws IOException{
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs = null;

		String sql = "SELECT password FROM Player WHERE email ='" + email.getText() + "';";
		try {
			Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            rs.next();

            String realPassword = rs.getString("password");

            if(realPassword.equals(password.getText())){
                rs.close();
                statement.close();
                connection.close();
                super.change(event, "Game.fxml");
            }
            else{
                rs.close();
                statement.close();
                connection.close();
                super.changeVisibility(true, );
            }

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
