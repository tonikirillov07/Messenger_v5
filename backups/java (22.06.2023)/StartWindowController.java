package com.darkstudio.messenger_v5;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import static com.darkstudio.messenger_v5.StartWindowController.Constants.LOGIN_VIEW;
import static com.darkstudio.messenger_v5.StartWindowController.Constants.REGISTRATION_VIEW;

public class StartWindowController extends SetErrorViewOnTextFields{

    private static final int LOGIN_LIMIT = 4;
    private static final int PASSWORD_LIMIT = 7, LICENCE_VIEW = 1, LOGIN_AND_LOG_UP_VIEW = 0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane additionPasswordPane, logInPane, registrationPane;

    @FXML
    private Label licenceTextLabel, errorLabelInLogIn, errorLabelInRegistration, getBackInAdditionalPassword, regGeneratePasswordAndLoginLabel, noAccountCreateInNowLabel, title, blockTitleLabel, developerLabel, errorLabel;

    @FXML
    private ImageView windowIconImageView, closeWindowButtonImageView, hideWindowButtonImageView;

    @FXML
    private Button nextButtonToEnterAddPassword, continueButton, regContinueButton;

    @FXML
    private HBox controlBarHbox;
    @FXML
    private TextField loginField;

    @FXML
    private VBox mainVBox;

    @FXML
    private PasswordField addPasswordPF, passwordField, regPasswordTF;

    @FXML
    private TextField regCityTF, regLoginTF;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private RadioButton imAgreeWithLicenceRadioButton, imNotAgreeWithLicenceRadioButton;

    @FXML
    private Pane licencePane;

    @FXML
    private AnchorPane logInAnchorPane;

    double x, y;
    String rememeberedUserDataFileName = "rememberedUserData.json";
    ShowExceptions showExceptions;
    Database database = new Database();
    Time time = new Time();
    JSONFuncs jsonFuncs = new JSONFuncs();
    PropertiesClass propertiesClass = new PropertiesClass();

    public StartWindowController() throws Exception {
        showExceptions = new ShowExceptions();
    }

    @FXML
    void initialize() {
        try {
            initContextMenu();
            addActionsOnControlsButtons();
            setMainVBoxStyle();
            setActionsOnNodes();

            if (new File("users/" + rememeberedUserDataFileName).exists()) {
                try {
                    loginField.setText(jsonFuncs.getDataFromJSON("users/" + rememeberedUserDataFileName, "userName"));
                    passwordField.setText(jsonFuncs.getDataFromJSON("users/" + rememeberedUserDataFileName, "Password"));
                    rememberMeCheckBox.setSelected(true);
                } catch (Exception ex) {
                    showExceptions.showException(ex);
                }
            }

            int whatToDisplayID = Boolean.parseBoolean(propertiesClass.getProperty("licence_is_agreed")) ? LOGIN_AND_LOG_UP_VIEW : LICENCE_VIEW;
            displayContent(whatToDisplayID);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void displayContent(int i) {
        if(i == LICENCE_VIEW){
            logInAnchorPane.setVisible(false);
            licencePane.setVisible(true);
        }else if(i == LOGIN_AND_LOG_UP_VIEW){
            logInAnchorPane.setVisible(true);
            licencePane.setVisible(false);
        }
    }

    private void setStyleForMenuItem(@NotNull MenuItem[] menuItems){
        for(int i = 0; i!=menuItems.length; i++) {
            menuItems[i].setStyle("-fx-text-fill: white; -fx-font-family: 'Arial Black'");
        }
    }

    private void showError(int where, Exception exception){
        Label label = null;

        if(where == LOGIN_VIEW){
            label = errorLabelInLogIn;
        }else if(where == REGISTRATION_VIEW){
            label = errorLabelInRegistration;
        }

        Label finalLabel = label;
        new Thread(() ->{
            Platform.runLater(() -> finalLabel.setVisible(true));
            Platform.runLater(() -> finalLabel.setText(exception.toString()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> finalLabel.setVisible(false));
        }).start();
    }

    private void initContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13; -fx-border-radius: 13; -fx-border-color: white");
        MenuItem menuItemMinimize = new MenuItem("Свернуть");
        MenuItem menuItemClose = new MenuItem("Закрыть");
        MenuItem menuItemPinOnTop = new MenuItem();

        setStyleForMenuItem(new MenuItem[]{menuItemMinimize, menuItemClose, menuItemPinOnTop});

        contextMenu.getItems().addAll(menuItemMinimize, menuItemPinOnTop, menuItemClose);

        menuItemPinOnTop.setText("Закрепить");

        menuItemMinimize.setOnAction(action -> getStage().setIconified(true));
        menuItemClose.setOnAction(actionEvent -> closeApp());
        menuItemPinOnTop.setOnAction(actionEvent -> {
            getStage().setAlwaysOnTop(!getStage().isAlwaysOnTop());
            menuItemPinOnTop.setText(getStage().isAlwaysOnTop() ? "Открепить": "Закрепить");
        });

        windowIconImageView.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(windowIconImageView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
    }

    private void setActionsOnNodes() {
        ToggleGroup licenceToggleGroup = new ToggleGroup();
        imAgreeWithLicenceRadioButton.setToggleGroup(licenceToggleGroup);
        imNotAgreeWithLicenceRadioButton.setToggleGroup(licenceToggleGroup);

        imAgreeWithLicenceRadioButton.setSelected(true);

        continueButton.setOnAction(event ->{
            try {
                if(loginField.getText().trim().length() >= LOGIN_LIMIT & passwordField.getText().trim().length() >= PASSWORD_LIMIT) {
                    logIn(loginField.getText().trim(), passwordField.getText().trim());
                }else{
                    if(loginField.getText().trim().length() < LOGIN_LIMIT & loginField.getText().trim().length() < PASSWORD_LIMIT){
                        setErrorViewToAnyField(loginField, null);
                        setErrorViewToAnyField(null, passwordField);

                        showLogInError("Длина логина и пароля не отвечают лимиту");
                    }else if(loginField.getText().trim().length() < LOGIN_LIMIT){
                        setErrorViewToAnyField(loginField, null);
                        showLogInError("Длина логина не отвечает лимиту");
                    }else if(passwordField.getText().trim().length() < PASSWORD_LIMIT){
                        setErrorViewToAnyField(null, passwordField);
                        showLogInError("Длина пароля не отвечает лимиту");
                    }
                }
            } catch (IOException e) {
                showExceptions.showException(e);
            }
        });

        regContinueButton.setOnAction(event-> logUp(regLoginTF.getText().trim(), regPasswordTF.getText().trim(), regCityTF.getText().trim()));

        regGeneratePasswordAndLoginLabel.setOnMouseClicked(event ->{
            if(event.getButton()==MouseButton.PRIMARY){
                PasswordGenerator passwordGenerator = new PasswordGenerator();
                passwordGenerator.generatePassword();

                regPasswordTF.setText(passwordGenerator.getRecommendedPassword());
                regLoginTF.setText(getRecommendedLogin());
                regGeneratePasswordAndLoginLabel.setText("Обновить");
            }
        });

        getBackInAdditionalPassword.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                additionPasswordPane.setVisible(false);
                logInPane.setVisible(true);
            }
        });

    }

    private void logUp(String login, String password, String city){
        if(password.isEmpty() & login.isEmpty() & city.isEmpty()) {
            setErrorViewToAnyField(regLoginTF, null);
            setErrorViewToAnyField(null, regPasswordTF);
            setErrorViewToAnyField(regCityTF, null);
        }else if(!password.isEmpty() & login.isEmpty() & city.isEmpty()) {
            setErrorViewToAnyField(regLoginTF, null);
            setErrorViewToAnyField(regCityTF, null);
        }else if(password.isEmpty() & !login.isEmpty() & city.isEmpty()) {
            setErrorViewToAnyField(null, regPasswordTF);
            setErrorViewToAnyField(regCityTF, null);
        }else if(password.isEmpty() & login.isEmpty() & !city.isEmpty()) {
            setErrorViewToAnyField(regLoginTF, null);
            setErrorViewToAnyField(null, regPasswordTF);
        }else if(!password.isEmpty() & !login.isEmpty() & city.isEmpty()) {
            setErrorViewToAnyField(regCityTF, null);
        }else{
            if(!database.userIsExistsAlready(login, password)) {
                database.addUser(login, password, city, String.valueOf(createUserID(login, password)), time.getDate() + " / "+ time.getTime());
            }else showError(REGISTRATION_VIEW, new Exception("Такой пользователь уже есть"));
        }
    }

    private int createUserID(String login, String password) {
        return login.hashCode()*password.hashCode();
    }

    private void showAdditionalPasswordScreen(String userID){
        logInPane.setVisible(false);
        additionPasswordPane.setVisible(true);

        nextButtonToEnterAddPassword.setOnAction(actionEvent -> {
            if(!addPasswordPF.getText().trim().isEmpty()){
                if(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, userID).equals(addPasswordPF.getText().trim())){
                    openMainWindow(database.getElementFromColumn(DatabaseConstants.USER_NAME, DatabaseConstants.USER_ID, userID), database.getElementFromColumn(DatabaseConstants.USER_PASSWORD, DatabaseConstants.USER_ID, userID));
                }else setErrorViewToAnyField(null, addPasswordPF);
            }else{
                setErrorViewToAnyField(null, addPasswordPF);
            }
        });
    }

    private void logIn(String login, String password) throws IOException {
        if(password.isEmpty() & login.isEmpty()) {
            setErrorViewToAnyField(loginField, null);
            setErrorViewToAnyField(null, passwordField);
        }else if(login.isEmpty()) setErrorViewToAnyField(loginField, null);
        else if(password.isEmpty()) setErrorViewToAnyField(null, passwordField);
        else{
            try {
                System.out.println(database.userIsExistsAlready(login, password));

                if(rememberMeCheckBox.isSelected()){
                    jsonFuncs.createJSONObject();
                    jsonFuncs.putSmthInJSONObject("userName", login);
                    jsonFuncs.putSmthInJSONObject("Password", password);
                    jsonFuncs.createJSONFile("users/"+rememeberedUserDataFileName);
                }else{
                    if(new File("users/"+rememeberedUserDataFileName).exists()) new File("users/"+rememeberedUserDataFileName).delete();
                }

                if(database.userIsExistsAlready(login, password)) {
                    if(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(createUserID(login, password))) == null) {
                        openMainWindow(login, password);
                    }else{
                        showAdditionalPasswordScreen(String.valueOf(createUserID(login, password)));
                    }
                }else showError(LOGIN_VIEW, new Exception("Введены неверные данные"));
            }catch (Exception e){
                showExceptions.showException(e);
            }
        }
    }

    private void openMainWindow(String login, String password) {
        try {
            ((Stage) loginField.getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow/mainWindowUI.fxml"));
            fxmlLoader.load();

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/icon.png")).openStream()));
            MainWindowController mainWindowController = fxmlLoader.getController();
            mainWindowController.setUserData(login, password, createUserID(login, password));
            mainWindowController.stage = stage;

            stage.showAndWait();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void setMainVBoxStyle(){
        BackgroundImage backgroundImage = new BackgroundImage(new Image(new File("background/bg.png").getAbsolutePath()),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        mainVBox.setBackground(background);

        noAccountCreateInNowLabel.setOnMouseClicked(event ->{
            if(event.getButton()==MouseButton.PRIMARY){
                noAccountCreateInNow();
            }
        });
    }

    private void addActionsOnControlsButtons() {
        try {
            controlBarHbox.setOnMousePressed(event ->{
                x = event.getSceneX();
                y = event.getSceneY();
            });

            controlBarHbox.setOnMouseDragged(event ->{
                controlBarHbox.getScene().getWindow().setX(event.getScreenX()-x);
                controlBarHbox.getScene().getWindow().setY(event.getScreenY()-y);
            });

            closeWindowButtonImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) confirmBeforeClose();
            });

            hideWindowButtonImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) getStage().setIconified(true);
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    Stage getStage(){
        return (Stage) controlBarHbox.getScene().getWindow();
    }

    private void confirmBeforeClose(){
        try {
            Alert closeConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            closeConfirmAlert.setTitle("Подтверждение действия");
            closeConfirmAlert.setHeaderText("Вы действительно хотите выйти?");
            Optional<ButtonType> buttonType = closeConfirmAlert.showAndWait();
            if (buttonType.isPresent() & buttonType.get() == ButtonType.OK) closeApp();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void closeApp(){
        try {
            getStage().close();
            System.exit(0);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public void noAccountCreateInNow(){
        logInPane.setVisible(false);
        registrationPane.setVisible(true);

        blockTitleLabel.setText("Регистрация");
        noAccountCreateInNowLabel.setText("Уже есть аккаунт?");
        noAccountCreateInNowLabel.setOnMouseClicked(event->{
            if(event.getButton()==MouseButton.PRIMARY){
                haveAccountLetsEnter();
            }
        });
    }

    public void haveAccountLetsEnter(){
        logInPane.setVisible(true);
        registrationPane.setVisible(false);

        blockTitleLabel.setText("Вход в аккаунт");
        noAccountCreateInNowLabel.setText("Нет аккаунта? Создайте сейчас!");
        noAccountCreateInNowLabel.setOnMouseClicked(event->{
            if(event.getButton()==MouseButton.PRIMARY){
                noAccountCreateInNow();
            }
        });
    }

    private String getRecommendedLogin(){
        String login = System.getProperty("user.name");;
        StringBuilder addChars = new StringBuilder();
        login = login+"_"+ Math.round(Math.random()*1000);
        List<String> allCharsList = new java.util.ArrayList<>(List.of());

        String chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        char[] charsArray = chars.toCharArray();

        for(int i = 0; i!=charsArray.length; i++){
            allCharsList.add(String.valueOf(charsArray[i]));
        }
        Collections.shuffle(allCharsList);

        for(int i = 0; i!=allCharsList.size()/6; i++){
            addChars.append(allCharsList.get(i));
        }

        login = login+addChars;
        return login;
    }

    private void showLogInError(String error){
        new Thread(() ->{
            Platform.runLater(() -> errorLabel.setText(error));
            Platform.runLater(() -> errorLabel.setVisible(true));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> errorLabel.setVisible(false));
        }).start();
    }

    static class Constants{
        public static int REGISTRATION_VIEW = 1;
        public static int LOGIN_VIEW = 2;
    }

}
