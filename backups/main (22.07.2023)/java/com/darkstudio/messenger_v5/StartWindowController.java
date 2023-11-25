package com.darkstudio.messenger_v5;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.darkstudio.messenger_v5.StartWindowController.Constants.*;
import static java.lang.Thread.sleep;

public class StartWindowController extends SetErrorViewOnTextFields{
    @FXML
    private Pane closeConfirmPane, additionPasswordPane, logInPane, registrationPane;

    @FXML
    private Label licenceTextLabel, errorLabelInLogIn, errorLabelInRegistration, getBackInAdditionalPassword, regGeneratePasswordAndLoginLabel, noAccountCreateInNowLabel, title, blockTitleLabel, developerLabel, errorLabel;

    @FXML
    private ImageView windowIconImageView, closeWindowButtonImageView, hideWindowButtonImageView;

    @FXML
    private Button continueButtonCloseConfirmPane, rejectButtonCloseConfirmPane, backToLicenceButton, continueButtonInLicence, nextButtonToEnterAddPassword, continueButton, regContinueButton;

    @FXML
    private HBox licenceControlsHbox, controlBarHbox;
    @FXML
    private TextField loginField;

    @FXML
    private VBox licenceNotAgreeVBox, mainVBox;

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

    @FXML
    private ScrollPane licenceTextScrollPane;

    private double x, y;
    private boolean confirmBeforeCloseISOpen = false;
    String rememeberedUserDataFileName = "rememberedUserData.json";
    ShowExceptions showExceptions;
    Database database = new Database();
    Time time = new Time();
    JSONFuncs jsonFuncs = new JSONFuncs();
    PropertiesClass propertiesClass;
    Animations animations;

    public StartWindowController() throws Exception {
        showExceptions = new ShowExceptions();
        animations = new Animations(true);
        propertiesClass = new PropertiesClass();
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

    private void setAnimation(Node[] nodes){
        for(int i = 0; i!=nodes.length; i++){
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), nodes[i]);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), nodes[i]);
            translateTransition.setFromY(-15);
            translateTransition.setToY(0);
            translateTransition.play();
        }
    }

    private String readInfo(URL source) {
        try {
            Scanner scanner = new Scanner(source.openStream());
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            return stringBuilder.toString();
        }catch (Exception e){
            showExceptions.showException(e);
        }
        return null;
    }


    private void displayContent(int i) {
        try {
            if (i == LICENCE_VIEW) {
                logInAnchorPane.setVisible(false);
                licencePane.setVisible(true);

                licenceTextLabel.setText(readInfo(getClass().getResource("licence.txt")));

                setAnimation(new Node[]{licencePane});
            } else if (i == LOGIN_AND_LOG_UP_VIEW) {
                logInAnchorPane.setVisible(true);
                licencePane.setVisible(false);

                setAnimation(new Node[]{logInAnchorPane});
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setStyleForMenuItem(@NotNull MenuItem[] menuItems){
        for(int i = 0; i!=menuItems.length; i++) {
            menuItems[i].setStyle("-fx-text-fill: white; -fx-font-family: 'Arial Black'");
        }
    }

    private void showError(int where, Exception exception){
        try {
            Label label = null;

            if (where == LOGIN_VIEW) {
                label = errorLabelInLogIn;
            } else if (where == REGISTRATION_VIEW) {
                label = errorLabelInRegistration;
            }

            Label finalLabel = label;
            new Thread(() -> {
                Platform.runLater(() -> finalLabel.setVisible(true));
                Platform.runLater(() -> finalLabel.setText(exception.toString()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    showExceptions.showException(e);
                }
                Platform.runLater(() -> finalLabel.setVisible(false));
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void openConfirmBeforeClose(){
        if(!confirmBeforeCloseISOpen) {
            confirmBeforeCloseISOpen = true;
            closeConfirmPane.setVisible(true);
            animations.setAnimationForSettings(closeConfirmPane, 100);

            rejectButtonCloseConfirmPane.setOnAction(event -> {
                new Thread(() -> {
                    Platform.runLater(() -> animations.playAnimToTileInDesktop(closeConfirmPane));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        showExceptions.showException(e);
                    }
                    Platform.runLater(() -> closeConfirmPane.setVisible(false));
                }).start();
            });

            continueButtonCloseConfirmPane.setOnAction(event -> closeApp());

            final int[] time = {60};

            new Thread(() -> {
                while (time[0] != 0) {
                    Platform.runLater(() -> rejectButtonCloseConfirmPane.setText("Отмена (" + time[0] + ")"));

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    time[0]--;
                }

                new Thread(() -> {
                    Platform.runLater(() -> animations.playAnimToTileInDesktop(closeConfirmPane));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        showExceptions.showException(e);
                    }
                    Platform.runLater(() -> closeConfirmPane.setVisible(false));
                }).start();
            }).start();
        }else {
            confirmBeforeCloseISOpen = false;

            new Thread(() -> {
                animations.playAnimToTileInDesktop(closeConfirmPane);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    showExceptions.showException(e);
                }

                closeConfirmPane.setVisible(false);
            }).start();
        }
    }

    private void initContextMenu() {
        try {
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
                menuItemPinOnTop.setText(getStage().isAlwaysOnTop() ? "Открепить" : "Закрепить");
            });

            windowIconImageView.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(windowIconImageView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setActionsOnNodes() {
        try {
            ToggleGroup licenceToggleGroup = new ToggleGroup();
            imAgreeWithLicenceRadioButton.setToggleGroup(licenceToggleGroup);
            imNotAgreeWithLicenceRadioButton.setToggleGroup(licenceToggleGroup);

            imAgreeWithLicenceRadioButton.setSelected(true);

            continueButtonInLicence.setOnAction(actionEvent -> {
                if(licenceToggleGroup.getSelectedToggle() == imAgreeWithLicenceRadioButton){
                    try {
                        displayContent(LOGIN_AND_LOG_UP_VIEW);
                        propertiesClass.changeValue("licence_is_agreed", "true");
                    }catch (Exception e){
                        showExceptions.showException(e);
                    }

                }else{
                    try {
                        licenceTextScrollPane.setVisible(false);
                        licenceControlsHbox.setVisible(false);

                        licenceNotAgreeVBox.setVisible(true);
                        propertiesClass.changeValue("licence_is_agreed", "false");
                    }catch (Exception e){
                        showExceptions.showException(e);
                    }
                }
            });

            backToLicenceButton.setOnAction(actionEvent -> {
                licenceTextScrollPane.setVisible(true);
                licenceControlsHbox.setVisible(true);

                licenceNotAgreeVBox.setVisible(false);
            });

            continueButton.setOnAction(event -> {
                try {
                    if (loginField.getText().trim().length() >= LOGIN_LIMIT & passwordField.getText().trim().length() >= PASSWORD_LIMIT) {
                        logIn(loginField.getText().trim(), passwordField.getText().trim());
                    } else {
                        if (loginField.getText().trim().length() < LOGIN_LIMIT & loginField.getText().trim().length() < PASSWORD_LIMIT) {
                            setErrorViewToAnyField(loginField, null);
                            setErrorViewToAnyField(null, passwordField);

                            showLogInError("Длина логина и пароля не отвечают лимиту");
                        } else if (loginField.getText().trim().length() < LOGIN_LIMIT) {
                            setErrorViewToAnyField(loginField, null);
                            showLogInError("Длина логина не отвечает лимиту");
                        } else if (passwordField.getText().trim().length() < PASSWORD_LIMIT) {
                            setErrorViewToAnyField(null, passwordField);
                            showLogInError("Длина пароля не отвечает лимиту");
                        }
                    }
                } catch (IOException e) {
                    showExceptions.showException(e);
                }
            });

            regContinueButton.setOnAction(event -> logUp(regLoginTF.getText().trim(), regPasswordTF.getText().trim(), regCityTF.getText().trim()));

            regGeneratePasswordAndLoginLabel.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    PasswordGenerator passwordGenerator = new PasswordGenerator();
                    passwordGenerator.generatePassword();

                    regPasswordTF.setText(passwordGenerator.getRecommendedPassword());
                    regLoginTF.setText(getRecommendedLogin());
                    regGeneratePasswordAndLoginLabel.setText("Обновить");
                }
            });

            getBackInAdditionalPassword.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    additionPasswordPane.setVisible(false);
                    logInPane.setVisible(true);
                    noAccountCreateInNowLabel.setVisible(true);
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void logUp(@NotNull String login, @NotNull String password, @NotNull String city){
        try {
            if (password.isEmpty() & login.isEmpty() & city.isEmpty()) {
                setErrorViewToAnyField(regLoginTF, null);
                setErrorViewToAnyField(null, regPasswordTF);
                setErrorViewToAnyField(regCityTF, null);
            } else if (!password.isEmpty() & login.isEmpty() & city.isEmpty()) {
                setErrorViewToAnyField(regLoginTF, null);
                setErrorViewToAnyField(regCityTF, null);
            } else if (password.isEmpty() & !login.isEmpty() & city.isEmpty()) {
                setErrorViewToAnyField(null, regPasswordTF);
                setErrorViewToAnyField(regCityTF, null);
            } else if (password.isEmpty() & login.isEmpty() & !city.isEmpty()) {
                setErrorViewToAnyField(regLoginTF, null);
                setErrorViewToAnyField(null, regPasswordTF);
            } else if (!password.isEmpty() & !login.isEmpty() & city.isEmpty()) {
                setErrorViewToAnyField(regCityTF, null);
            } else {
                if (!database.userIsExistsAlready(login, password)) {
                    database.addUser(login, password, city, String.valueOf(createUserID(login, password)), time.getDate() + " / " + time.getTime());

                    regLoginTF.clear();
                    regPasswordTF.clear();
                    regCityTF.clear();

                    registrationPane.setVisible(false);
                    logInPane.setVisible(true);

                    setAnimation(new Node[]{logInPane});
                } else showError(REGISTRATION_VIEW, new Exception("Такой пользователь уже есть"));
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private int createUserID(@NotNull String login, @NotNull String password) {
        return login.hashCode()*password.hashCode();
    }

    private void showAdditionalPasswordScreen(String userID){
        logInPane.setVisible(false);
        noAccountCreateInNowLabel.setVisible(false);
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

    private void logIn(@NotNull String login, @NotNull String password) throws IOException {
        if(password.isEmpty() & login.isEmpty()) {
            setErrorViewToAnyField(loginField, null);
            setErrorViewToAnyField(null, passwordField);
        }else if(login.isEmpty()) setErrorViewToAnyField(loginField, null);
        else if(password.isEmpty()) setErrorViewToAnyField(null, passwordField);
        else{
            try {
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
            System.out.println("[Debug]: Loading...");
            ((Stage) loginField.getScene().getWindow()).close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow/mainWindowUI.fxml"));
            fxmlLoader.load();

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setTitle(new PropertiesClass().getProperty("app_title"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/icon.png")).openStream()));
            MainWindowController mainWindowController = fxmlLoader.getController();
            mainWindowController.setUserData(login, password, createUserID(login, password));
            mainWindowController.stage = stage;

            Platform.runLater(stage::show);
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
                if (event.getButton() == MouseButton.PRIMARY) openConfirmBeforeClose();
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

    @NotNull
    private String getRecommendedLogin(){
        String login = System.getProperty("user.name");
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
        static final int LOGIN_LIMIT = 4;
        static final int PASSWORD_LIMIT = 7;
        static final int LICENCE_VIEW = 1;
        static final int LOGIN_AND_LOG_UP_VIEW = 0;
    }

}
