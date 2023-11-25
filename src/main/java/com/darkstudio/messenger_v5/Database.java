package com.darkstudio.messenger_v5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database extends DatabaseConstants{
    ShowExceptions showExceptions;

    public Database(){
        showExceptions = new ShowExceptions();
    }

    public Connection getDBconnection()  {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:DB/users.db");
        }catch (Exception e){
            showExceptions.showException(e);
        }

        return null;
    }

    public String getElementFromColumn(String column, String columnUserID, String userID){
        try {
            String get = "SELECT " + column + " FROM " + TABLE_NAME + " WHERE " + columnUserID + "=" + "'" + userID + "'";

            String elementValue;
            ResultSet resultSet;

            PreparedStatement preparedStatement = getDBconnection().prepareStatement(get);

            resultSet = preparedStatement.executeQuery();
            elementValue = resultSet.getString(1);
            resultSet.close();

            return elementValue;
        }catch (Exception e){
            showExceptions.showException(e);
        }

        return null;
    }

    public void changeValue(String columnName, String newValue, String userIdColumn, String userId){
        try {
            String change = "UPDATE " + TABLE_NAME + " set " + columnName + "=" + "'" + newValue + "'" + " WHERE " + userIdColumn + "=" + "'" + userId + "'";

            PreparedStatement preparedStatement = getDBconnection().prepareStatement(change);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void setNull(String columnName, String userIdColumn, String userId){
        try {
            String changeCommand = "UPDATE " + TABLE_NAME + " SET " + columnName + " = null WHERE " + userIdColumn + "= '" + userId + "'";
            PreparedStatement preparedStatement = getDBconnection().prepareStatement(changeCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public boolean userIsExistsAlready(String login, String password){
        boolean user_is_found = false;
        try {
            String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_NAME + "=? AND " + USER_PASSWORD + "=?";

            PreparedStatement preparedStatement = getDBconnection().prepareStatement(select);
            ResultSet resultSet;

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            int counter = 0;

            while (resultSet.next()) {
                counter++;
            }

            user_is_found = counter >= 1;

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            showExceptions.showException(e);
        }

        return user_is_found;
    }

    public void addUser(String login, String password, String city, String userID, String userRegDate){
        try {
            String city_col = "city";
            String write = "INSERT INTO "+TABLE_NAME+ " (" + USER_NAME + ", "+ USER_PASSWORD + ", " + USER_ID +", "+ city_col +", "+ USER_DATE_OF_REG +") values(?,?,?,?,?)";

            PreparedStatement preparedStatement = getDBconnection().prepareStatement(write);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userID);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, userRegDate);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void deleteUser(String userID){
        try {
            String delete = "DELETE FROM " + TABLE_NAME + " WHERE " + USER_ID +"="+"'"+userID+"'";

            PreparedStatement preparedStatement = getDBconnection().prepareStatement(delete);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (Exception e){
            showExceptions.showException(e);
        }
    }
}
