package com.darkstudio.messenger_v5;

import java.sql.*;

public class Database extends DatabaseConstants{
    ShowExceptions showExceptions;

    public Database(){
        showExceptions = new ShowExceptions();
    }

    private Connection getConnection() throws Exception{
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:DB/users.db");
    }

    public String getElementFromColumn(String column, String columnUserID, String userID){
        String value = null;
        try {
            String getCommand = "SELECT " + column + " FROM " + TABLE_NAME + " WHERE " + columnUserID + "= '" + userID + "'";

            ResultSet resultSet;
            PreparedStatement preparedStatement = getConnection().prepareStatement(getCommand);

            resultSet = preparedStatement.executeQuery();
            value = resultSet.getString(1);
            resultSet.close();
        }catch (Exception e){
            showExceptions.showException(e);
        }

        return value;
    }

    public void changeValue(String columnName, String newValue, String userIdColumn, String userId){
        try {
            String changeCommand = "UPDATE " + TABLE_NAME + " SET " + columnName + " = '" + newValue + "' WHERE " + userIdColumn + "= '" + userId + "'";

            PreparedStatement preparedStatement = getConnection().prepareStatement(changeCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    public void setNull(String columnName, String userIdColumn, String userId){
        try {
            String changeCommand = "UPDATE " + TABLE_NAME + " SET " + columnName + " = null WHERE " + userIdColumn + "= '" + userId + "'";

            PreparedStatement preparedStatement = getConnection().prepareStatement(changeCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    public boolean userIsExistsAlready(String login, String password){
        boolean found;

        try {
            String select = "SELECT * FROM "+TABLE_NAME+" WHERE "+USER_NAME+"=? AND "+USER_PASSWORD+"=? AND "+USER_ID+"=?";

            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            ResultSet resultSet;

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, String.valueOf(login.hashCode()*password.hashCode()));

            resultSet = preparedStatement.executeQuery();

            int aint = 0;

            while (resultSet.next()){
                aint++;
            }

            found = aint >= 1;

            resultSet.close();
            preparedStatement.close();

            return found;
        }catch (Exception ex){
            showExceptions.showException(ex);
        }

        return false;
    }

    public void addUser(String login, String password, String city, String userID, String userRegDate){
        try {
            String addCmd = "INSERT INTO "+TABLE_NAME+" ("+USER_NAME+", "+USER_PASSWORD+", "+USER_CITY+", "+USER_ID+", "+USER_DATE_OF_REG+") VALUES(?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = getConnection().prepareStatement(addCmd);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, userID);
            preparedStatement.setString(5, userRegDate);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("User "+login+" added!");
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    public void deleteUser(String userID){
        try{
            String deleteCMD = "DELETE FROM "+TABLE_NAME+" WHERE "+USER_ID+"= '"+userID+"'";

            PreparedStatement preparedStatement = getConnection().prepareStatement(deleteCMD);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }
}
