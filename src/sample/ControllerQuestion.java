package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.Cleaner;
import Connection.ConnectionClass;

import java.util.*;

public class ControllerQuestion {
    private int questionCount = 0;

    @FXML
    public TextField answerField;
    public TextField questionField;
    public TextField timerDisplay;

    public void questionDisplay(int gameId, String username) { //helene: fiks at spørsmålene vises riktig
        Connection connection = null;
        Statement statement = null;
        Cleaner cleaner = new Cleaner();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            statement = connection.createStatement();

            String sqlGetText = "SELECT questionText FROM Game JOIN Question ON questionId = question";
            String sqlGetQuestion = "FROM Game WHERE gameId = '" + gameId + "';";      //sql for question

            if(questionCount == 0) {
                /*ResultSet rsQuestionOne = statement.executeQuery("SELECT question1" + sqlGetQuestion);
                rsQuestionOne.next();
                String questionOne = rsQuestionOne.getString("question1"); //lagrer spørsmålet*/
                ResultSet rsQuestionOneText = statement.executeQuery(sqlGetText + "1;");
                rsQuestionOneText.next();
                String qOneText = rsQuestionOneText.getString("questionText");
                questionField.setText(qOneText);                         //printer ut spørsmålet

            }
            else if(questionCount == 1) {
                /*ResultSet rsQuestionTwo = statement.executeQuery("SELECT question2" + sqlGetQuestion);
                rsQuestionTwo.next();
                String questionTwo = rsQuestionTwo.getString("question2");
                questionField.setText(questionTwo);*/
                ResultSet rsQuestionTwoText = statement.executeQuery(sqlGetText + "2;");
                rsQuestionTwoText.next();
                String qTwoText = rsQuestionTwoText.getString("questionText");
                questionField.setText(qTwoText);
            }
            else if(questionCount == 2) {
                /*ResultSet rsQuestionThree = statement.executeQuery("SELECT question3" + sqlGetQuestion);
                rsQuestionThree.next();
                String questionThree = rsQuestionThree.getString("question3");
                questionField.setText(questionThree);*/
                ResultSet rsQuestionThreeText = statement.executeQuery(sqlGetText + "3;");
                rsQuestionThreeText.next();
                String qThreeText = rsQuestionThreeText.getString("questionText");
                questionField.setText(qThreeText);
            }
            questionCount++;


        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cleaner.close(statement, null, connection);
        }
    }
}
