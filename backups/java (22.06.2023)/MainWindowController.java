package com.darkstudio.messenger_v5;

import com.darkstudio.messenger_v5.ServerAndClient.ServerTest;
import com.sun.management.OperatingSystemMXBean;
import jaco.mp3.player.MP3Player;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import net.synedra.validatorfx.Check;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.darkstudio.messenger_v5.MainWindowController.SettingsConstants.*;
import static com.darkstudio.messenger_v5.MainWindowController.SettingsConstants.PROFILE_SETTINGS;
import static com.darkstudio.messenger_v5.MainWindowController.WeatherConstants.*;
import static com.darkstudio.messenger_v5.SearchRequests.Constants.*;
import static java.lang.Thread.sleep;

public class MainWindowController {

    @FXML
    private ScrollPane appearanceScrollPane;
    @FXML
    private CheckBox useOneColorCheckBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView standartBG2Button, standartBG1Button, makeScreenshotButton, weatherImageInWeatherWidget, weatherImage, rejectToRemoveAccountIVButton, confirmToRemoveAccountIVButton, cancelToRemovePasswordIV, applyToRemovePasswordIV, applyButtonIVYoResetAllSettings, cancelButtonIVInResetAllSettings, searchButtonImageView, rejectToRemoveButtonRecentChatsIV, applyToRemoveRecentChatsButtonIV, removeAllRecentChats, soundsInSettingsIcon, playRandomMusicButtonMP, playMusicButtonMP, pauseMusicButtonMP, openMyFolderWithMusicButtonMP, openInFolderMusicButtonMP, closeMPButtonMP, blockIconInSettings, addNewMusicButtonMP, getbackInSettingsImageView, turnOffImageView, settingsImageView, iconImageView, hideWindowImageView, hideRecentChatsImageView, closeWindowImageView, MusicImageView;

    @FXML
    private Circle userAvatarCircleInSettings, avatarCircle;

    @FXML
    private VBox disableContentOnToolBarVBox, weatherWidgetVBox, componenetsToChangeSomethingInUserSettings, buttonsInUserSettingsVBox, searchEllementVBox, SearchVBox, chatListVboxInScrollPane, recentChatsListVBox, mainVBox;

    @FXML
    private Label cancelToCreateChatLabel, addressLabelInCreateChatPane, portLabelInCreateChatPane, createAnotherPortLabel, restoreBgImageLabel, splashLabel, opacityValue, ErrorLabelInWeatherWidget, countryNamelabelInWeatherWidget, timeZoneLabelInWeatherWidget, placeNameLabelInWeatherWidget, weatherDescriptionWeatherInWeatherWidget, WindSpeedLabelInWeatherWidget, VisibilityLabelInWeatherWidget, TemperatureLabelInWeatherWidget, TemperatureFeelsLikeLabelInWeatherWidget, PressureLabelInWeatherWidget, MinTemperatureLabelInWeatherWidget, MaxTemperatureLabelInWeatherWidget, HumidityLabelInWeatherWidget, cancelSettingPasswordByTheHandLabel, weatherDescriptionLabel, weatherTempLabel, copyAboutProgramLabel, copyAboutSystemLabel, appDeveloperLabel, appBuildLabel, appVersionLabel, appTitleLabel, thisSystemIsMeetingMinSysReqLabel, sizeScreenLabel, RAMLabel, systemNameLabel, dateOfRegistrationLabelInSettings, usernameInSettingsuserBlock, soundsVolumeValueLabel, welcomeLabel, playingMusicLabel, errorLabelMP, choosedFilePathLabelInAppearence, brightnessValue, blockTitleInSettings, SaturationValue, HueValue, ContrastValue, resetAllLabelInSettings, closeConnectToTheChatPaneLabel, errorLabelConnectToTheChat, titleLabel, recentChatsLabel, userNameLabel;

    @FXML
    private Pane createChatPane, confirmToRemovePasswordPane, loadImageForBGPane, settingsAllContentPane, createYourChatPane, splashSettingsBlockPane, addBlockInSettingsPane, weatherPane, copySomethingPaneInAboutProgramBlock, userWidgetPane, setPasswordByTheHandPane, buttonToSetPasswordPane, aboutProgramPaneInSettings, searchPaneInSettings, changeUserDataPane, userSettingsBlockPane, appearenceInSettingsPane, connectToTheChatTilePane, MPPane, closeConfirmPane, connectToTheChatPane;

    @FXML
    private TextField enterYourSplashTF, newCityNameTextFieldInWeatherWidget, textFieldToSetPasswordByTheHand, newValueTFInUserSettings, currentValueTFInUserSettings, searchField, cityWhereNeedToShowWeatherTextField, chatAddressTF;

    @FXML
    private Button turnOnOrOffAnimationsButton, hideToolsBarButton, continueInCreateChatPaneButton, turnOnOrOffSearchUsingButton, requestConfirmBeforeCloseButton, applyButtonToShowElementsOnToolsBar, displayYourSplashButton, disableSplashesButton, continueButtonToSetYourSplash, continueToApplyNewCityButtonInWeatherWidget, continueButtonInSettingsPasswordByTheHand, cancelToSetPasswordButton, setPasswordByTheHandButton, setPasswordAutomaticButton, setPasswordToAccountButton, clearSearchJournalButton, cancelButtonInUserSettings, continueButtonInUserSettings, changeLoginButton, changePasswordButton, changeCityButton, exitFromAccountInSettingsButton, removeAccountInSettingsButton, updateWeatherDataButtonInSettings, turnOnorOffWeatherButton, turnOnorOffSoundsButton, theBestViewButton, simpleViewButton, resetAppearenceSettingsButton, loadInmageButtonInAppearence, continueButtonCloseConfirmPane, rejectButtonCloseConfirmPane, createChatButton, connectToTheChatButton, continueButtonConnectToTheChat;

    @FXML
    private HBox contentHBox, buttonRemoveAccountAndExutHBox, buttonsToConfirmRemoveAccountHbox, setYourSplashHBoxControls, textHadCopiedLabelHbox, accountBlockInSettings, aboutPropgramBlockInSettings, controlsBarHbox, settingsHBox, toolBarHbox, appearenceBlockInSettings, searchBlockInSettings, additionalBlockInSettings;

    @FXML
    Slider soundVolumeSlider, ContrastSlider, HueSlider, SaturationSlider, opacitySlider, brightnessSlider;

    @FXML
    private ColorPicker mainColorColorPickerInAppearence;

    @FXML
    private RadioButton hideToolsBarAutoRadioButton, showingIconsRequestsInSearchRadioButton, makingSearchJournalRadioButton;

    @FXML
    ProgressBar settingsProgressBar;

    AtomicBoolean widgetDidOpened = new AtomicBoolean(false);

    private double x, y;
    private double soundsVolume = 1;
    private boolean useAnimations = true, settingsWasOpenToday = false, enableConfirmBefore = false, enablePlaySounds = true, splashesWereInitialized = false, changeColors = true, helpWindowIsOpen = false;
    public Stage stage;

    SetErrorViewOnTextFields setErrorViewOnTextFields = new SetErrorViewOnTextFields();
    ShowExceptions showExceptions = new ShowExceptions();
    PropertiesClass propertiesClass = new PropertiesClass();

    public long id = 0, recentChatsCounter = 0;
    public String userName = "", password, city;
    private double saturation, hue, contrast, brightness;
    private Weather weather;
    private File userTracksFolder = new File(System.getProperty("user.home")+"/userTracks");
    private java.util.List<Node> opacityNodes = new java.util.ArrayList<>(java.util.List.of());

    private java.util.List<String> recentChatsAddress = new java.util.ArrayList<>(java.util.List.of());
    private java.util.List<String> recentChatsTime = new java.util.ArrayList<>(java.util.List.of());

    private final Database database = new Database();
    private Time time = new Time();
    private JSONFuncs jsonFuncs = new JSONFuncs();
    ServerTest serverTest;

    int chatPort;

    int aint = 0;

    public MainWindowController() throws Exception {

    }

    private void playClickSound() {
        if(enablePlaySounds) new PlaySounds(PlaySounds.Constants.CLICK_SOUND, (float) soundsVolume).play();
    }

    public void setUserData(String login, String password, long userId){
        try {
            this.userName = login;
            this.password = password;
            this.id = userId;

            welcomeLabel.setText("Добро пожаловать, " + userName);
            userNameLabel.setText(userName);

            loadRecentsChats(login);
            setUserSettings();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setBg(String source){
        BackgroundImage backgroundImage = new BackgroundImage(new Image(new File(source).getAbsolutePath()),
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        mainVBox.setBackground(background);
    }

    private void showProgressBarInSettings(boolean b){
        resetAllLabelInSettings.setVisible(!b);
        if(applyButtonIVYoResetAllSettings.isVisible()) applyButtonIVYoResetAllSettings.setVisible(!b);
        if(cancelButtonIVInResetAllSettings.isVisible()) cancelButtonIVInResetAllSettings.setVisible(!b);
        settingsProgressBar.setVisible(b);
    }

    private void animToolBar(boolean up){
        int fromY = up ? 100 : 0;
        int toY = up ? 0 : 100;

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), toolBarHbox);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        translateTransition.play();

    }

    private void setAutoHideToolBar(){
        AtomicBoolean aboolean = new AtomicBoolean(false);
        mainVBox.setOnMouseMoved(mouseEvent -> {
            if(!settingsHBox.isVisible()) {
                if (mouseEvent.getY() >= 672.0) {
                    toolBarHbox.setVisible(true);

                    if (!aboolean.get()) animToolBar(true);

                    aboolean.set(true);
                } else {
                    if (aboolean.get()) animToolBar(false);

                    aboolean.set(false);
                }
            }
        });

    }

    @FXML
    void initialize() {
        try {
            setBg("background/bg.png");

            initSettings();
            setActionsOnObjects();
            addActionsOnControlsButtons();
            initSearch();
            initMusic();
            initHotKeys();

            opacityNodes.add(createYourChatPane);
            opacityNodes.add(connectToTheChatTilePane);
            opacityNodes.add(MPPane);
            opacityNodes.add(SearchVBox);
            opacityNodes.add(MPPane);
            opacityNodes.add(toolBarHbox);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void initHotKeys() {
        mainVBox.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.T){
                testMethod();
            }

            if(keyEvent.getCode()==KeyCode.ESCAPE){
                if(database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) askBeforeExit(); else closeApp();
            }

            if(keyEvent.isControlDown() & keyEvent.getCode()==KeyCode.S){
                openSearch();
            }
            if(keyEvent.isControlDown() & keyEvent.getCode()==KeyCode.I){
                openSettings();
            }
            if(keyEvent.isControlDown() & keyEvent.getCode()==KeyCode.H){
                openHelpWindow();
            }
        });
    }

    private void setUserSettings() {
        try {
            Image avatarImageDefault = new Image(new File("images/userDefAvatar.png").getAbsolutePath());
            String userAvatarString = database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id));

            assert userAvatarString != null;
            if (new File(userAvatarString).exists()) {
                setAvatarImage(new File(userAvatarString));
            } else {
                avatarCircle.setFill(new ImagePattern(avatarImageDefault));
                userAvatarCircleInSettings.setFill(new ImagePattern(avatarImageDefault));
            }

            usernameInSettingsuserBlock.setText(userName);
            dateOfRegistrationLabelInSettings.setText(database.getElementFromColumn(DatabaseConstants.USER_DATE_OF_REG, DatabaseConstants.USER_ID, String.valueOf(id)));

            city = database.getElementFromColumn(DatabaseConstants.USER_CITY, DatabaseConstants.USER_ID, String.valueOf(id));

            int bgMode = Integer.parseInt(database.getElementFromColumn(DatabaseConstants.BACKGROUND_MODE, DatabaseConstants.USER_ID, String.valueOf(id)));

            if (bgMode == BG_MODE_IMAGE) {
                useOneColorCheckBox.setSelected(false);
                mainColorColorPickerInAppearence.setDisable(true);
                loadImageForBGPane.setDisable(false);
            } else if(bgMode == BG_MODE_COLOR){
                useOneColorCheckBox.setSelected(true);
                mainColorColorPickerInAppearence.setDisable(false);
                loadImageForBGPane.setDisable(true);
            }

            opacitySlider.setValue(Double.parseDouble(database.getElementFromColumn(DatabaseConstants.OPACITY_LEVEL, DatabaseConstants.USER_ID, String.valueOf(id))));
            opacityValue.setText(getPerCents((float) opacitySlider.getValue(), (float) opacitySlider.getMax()) + "%");

            turnOnorOffSoundsButton.setText(database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Выкл. звуки" : "Вкл. звуки");
            soundsVolume = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.SOUNDS_VOLUME, DatabaseConstants.USER_ID, String.valueOf(id)));
            soundVolumeSlider.setValue(soundsVolume);

            soundVolumeSlider.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));
            soundsVolumeValueLabel.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            turnOnorOffWeatherButton.setText(database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Не показывать погоду" : "Показывать погоду");
            updateWeatherDataButtonInSettings.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));
            cityWhereNeedToShowWeatherTextField.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            saturation = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.SATURATION_VALUE, DatabaseConstants.USER_ID, String.valueOf(id)));
            hue = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.HUE_VALUE, DatabaseConstants.USER_ID, String.valueOf(id)));
            contrast = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.CONTRAST_VALUE, DatabaseConstants.USER_ID, String.valueOf(id)));
            brightness = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.BRIGHTNESS_VALUE, DatabaseConstants.USER_ID, String.valueOf(id)));

            setColorSettings(hue, saturation, brightness, contrast);
            brightnessSlider.setValue(brightness);
            ContrastSlider.setValue(contrast);
            HueSlider.setValue(hue);
            SaturationSlider.setValue(saturation);

            brightnessValue.setText(getPerCents((float) brightnessSlider.getValue(), (float) brightnessSlider.getMax()) + "%");
            ContrastValue.setText(getPerCents((float) ContrastSlider.getValue(), (float) ContrastSlider.getMax()) + "%");
            HueValue.setText(getPerCents((float) HueSlider.getValue(), (float) HueSlider.getMax()) + "%");
            SaturationValue.setText(getPerCents((float) SaturationSlider.getValue(), (float) SaturationSlider.getMax()) + "%");

            if (database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) {
                initWeather();
                initWeatherWidget();
            }

            if(database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                initSplashes();
            }else splashLabel.setText(" ");
            disableSplashesButton.setText(database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Отключить сплеши": "Включить сплеши");

            String bgSource = database.getElementFromColumn(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id));
            String bgColor = database.getElementFromColumn(DatabaseConstants.BACKGROUND_COLOR, DatabaseConstants.USER_ID, String.valueOf(id));

            if(bgMode == BG_MODE_IMAGE) {
                if (bgSource != null) {
                    if (new File(bgSource).exists()) {
                        setBg(bgSource);
                        choosedFilePathLabelInAppearence.setText(new File(bgSource).getName());
                    }
                }
            }else if(bgMode == BG_MODE_COLOR){
                setBgColor(Color.valueOf(bgColor));
                mainColorColorPickerInAppearence.setValue(Color.valueOf(bgColor));
            }

            turnOnOrOffSearchUsingButton.setText(database.getElementFromColumn(DatabaseConstants.USE_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Выкл. поиск": "Вкл. поиск");
            setPasswordToAccountButton.setText(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(id)) != null ? "Удалить пароль с аккаунта": "Установить пароль на аккаунт");

            if(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(id)) != null){
                setPasswordToAccountButton.setOnAction(actionEvent -> {
                    playClickSound();
                    removePasswordFromAccount();
                });
            }

            searchButtonImageView.setVisible(database.getElementFromColumn(DatabaseConstants.USE_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            requestConfirmBeforeCloseButton.setText(database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Не спрашивать перед закрытием": "Спрашивать перед закрытием");
            hideToolsBarAutoRadioButton.setSelected(database.getElementFromColumn(DatabaseConstants.AUTO_HIDE_TASKBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            if(database.getElementFromColumn(DatabaseConstants.AUTO_HIDE_TASKBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) setAutoHideToolBar();
            showingIconsRequestsInSearchRadioButton.setSelected(database.getElementFromColumn(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            enablePlaySounds = database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");
            turnOnorOffSoundsButton.setText(enablePlaySounds ? "Выкл. звуки": "Вкл. звуки");

            enableConfirmBefore = database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");

            String lastAddress = database.getElementFromColumn(DatabaseConstants.LAST_CHAT_ADDRESS, DatabaseConstants.USER_ID, String.valueOf(id));

            if(lastAddress != null){
                chatAddressTF.setText(lastAddress);
            }

            turnOffImageView.setVisible(false);
            settingsImageView.setVisible(false);
            MusicImageView.setVisible(false);
            userWidgetPane.setVisible(false);
            weatherPane.setVisible(false);

            char[] itemsToDisplayOnTaskBar = database.getElementFromColumn(DatabaseConstants.WHAT_ITEMS_DISPLAY_ON_TASKBAR, DatabaseConstants.USER_ID, String.valueOf(id)).toCharArray();

            boolean[] whatToShow = new boolean[5];

            for(int i = 0; i!=itemsToDisplayOnTaskBar.length; i++){
                int itemsToDisplayOnTaskBarInteger = Integer.parseInt(String.valueOf(itemsToDisplayOnTaskBar[i]));

                if(itemsToDisplayOnTaskBarInteger == TURN_OFF_BUTTON_CHECKBOX_ID){
                    turnOffImageView.setVisible(true);
                    whatToShow[0] = true;
                }
                if(itemsToDisplayOnTaskBarInteger == MP_BUTTON_CHECKBOX_ID){
                    MusicImageView.setVisible(true);
                    whatToShow[1] = true;
                }
                if(itemsToDisplayOnTaskBarInteger == SETTINGS_BUTTON_CHECKBOX_ID){
                    settingsImageView.setVisible(true);
                    whatToShow[2] = true;
                }
                if(itemsToDisplayOnTaskBarInteger == WEATHER_WIDGET_CHECKBOX_ID){
                    weatherPane.setVisible(true);
                    whatToShow[3] = true;
                }
                if(itemsToDisplayOnTaskBarInteger == USER_WIDGET_CHECKBOX_ID){
                    userWidgetPane.setVisible(true);
                    whatToShow[4] = true;
                }
            }

            if(whatToShow[0]) {
                CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(0);
                checkBox.setSelected(true);
            }

            if(whatToShow[1]) {
                CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(1);
                checkBox.setSelected(true);
            }

            if(whatToShow[2]) {
                CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(2);
                checkBox.setSelected(true);
            }

            if(whatToShow[3]) {
                CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(3);
                checkBox.setSelected(true);
            }

            if(whatToShow[4]) {
                CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(4);
                checkBox.setSelected(true);
            }

            useAnimations = database.getElementFromColumn(DatabaseConstants.USE_ANIMATIONS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");

        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private float convertKelvinsToCelsius(Float temp){
        return Math.round((temp - 273.15));
    }

    private float convertCelsiusToFahrenheit(Float temp){return Math.round((temp * 1.8) + 32);}

    private void showErrorInWeatherWidget(Exception exception){
        new Thread(() ->{
            Platform.runLater(() -> ErrorLabelInWeatherWidget.setText(exception.getMessage()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> ErrorLabelInWeatherWidget.setText(""));
        }).start();

    }

    private void displayWeatherInWeatherWidget(Weather weather){
        try {
            float temp = convertKelvinsToCelsius(Float.parseFloat(weather.getTemperature()));
            float tempMax = convertKelvinsToCelsius(Float.parseFloat(weather.getMaxTemperature()));
            float tempMin = convertKelvinsToCelsius(Float.parseFloat(weather.getMinTemperature()));
            float tempFeelsLike = convertKelvinsToCelsius(Float.parseFloat(weather.getTemperatureFeelsLike()));

            weatherDescriptionWeatherInWeatherWidget.setText("Погода: " + weather.getDescription());
            TemperatureLabelInWeatherWidget.setText("Температура: " + temp + "°C / " + convertCelsiusToFahrenheit(temp) + "°F");
            MaxTemperatureLabelInWeatherWidget.setText("Макс. температура: " + tempMax + "°C / " + convertCelsiusToFahrenheit(tempMax) + "°F");
            MinTemperatureLabelInWeatherWidget.setText("Мин. температура: " + tempMin + "°C / " + convertCelsiusToFahrenheit(tempMin) + "°F");
            TemperatureFeelsLikeLabelInWeatherWidget.setText("Ощущается как: " + tempFeelsLike + "°C / " + convertCelsiusToFahrenheit(tempFeelsLike) + "°F");
            WindSpeedLabelInWeatherWidget.setText("Скорость ветра: " + weather.getWindSpeed() + "м/с");
            HumidityLabelInWeatherWidget.setText("Влажность: " + weather.getHumidity() + "%");
            PressureLabelInWeatherWidget.setText("Давление: " + weather.getPressure() + "мм.рт.ст");
            VisibilityLabelInWeatherWidget.setText("Видимость: " + weather.getVisibility() + "м");
            placeNameLabelInWeatherWidget.setText("Название: "+weather.getPlace());
            countryNamelabelInWeatherWidget.setText("Страна: "+weather.getCountry());
            timeZoneLabelInWeatherWidget.setText("Часовой пояс: "+weather.getTimezone());

            weatherImageInWeatherWidget.setImage(new Image(getClass().getResource(requestsImageForWeather(weather.getMain())).openStream()));
        }catch (Exception ex){
            showErrorInWeatherWidget(ex);
        }
    }

    private void initWeatherWidget(){
        displayWeatherInWeatherWidget(weather);

        newCityNameTextFieldInWeatherWidget.setOnKeyPressed(keyEvent -> {if(keyEvent.getCode()==KeyCode.ENTER) showWeatherInAnotherPlaceInWeatherWidget();});
        continueToApplyNewCityButtonInWeatherWidget.setOnAction(actionEvent -> showWeatherInAnotherPlaceInWeatherWidget());
    }

    private void showWeatherInAnotherPlaceInWeatherWidget() {
        try {
            playClickSound();

            Weather weather1 = new Weather(newCityNameTextFieldInWeatherWidget.getText().trim());
            String result = weather1.init();
            if(!result.equals("1")) showErrorInWeatherWidget(new Exception(result));
            displayWeatherInWeatherWidget(weather1);
            newCityNameTextFieldInWeatherWidget.clear();
        } catch (Exception e) {
            showErrorInWeatherWidget(e);
        }
    }

    private void setColorSettings(double hue, double saturation, double brightness, double contrast){
        try {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(hue);
            colorAdjust.setSaturation(saturation);
            colorAdjust.setBrightness(brightness);
            colorAdjust.setContrast(contrast);

            database.changeValue(DatabaseConstants.HUE_VALUE, String.valueOf(hue), DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.SATURATION_VALUE, String.valueOf(saturation), DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.BRIGHTNESS_VALUE, String.valueOf(brightness), DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.CONTRAST_VALUE, String.valueOf(contrast), DatabaseConstants.USER_ID, String.valueOf(id));

            mainVBox.setEffect(colorAdjust);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void initWeather() throws Exception {
        weather = new Weather(city);
        weather.init();

        displayWeatherInfoOnMainScreen(weather.getTemperature(), weather.getDescription(), weather.getMain());
    }

    private long getMemorySize(){
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();
    }

    private void copyText(String text){
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void initSettings() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        CheckMinimumSysReq checkMinimumSysReq = new CheckMinimumSysReq();
        checkMinimumSysReq.init();

        systemNameLabel.setText("Система: "+System.getProperty("os.name"));
        RAMLabel.setText("RAM: "+Math.round((getMemorySize() / 1024 / 1024) * 0.000977) + " Gb");
        sizeScreenLabel.setText("Размер экрана: "+dimension.width + "x" + dimension.height);

        if(!checkMinimumSysReq.systemIsMeetMinSysReq()){
            thisSystemIsMeetingMinSysReqLabel.setTextFill(Color.RED);
            thisSystemIsMeetingMinSysReqLabel.setText("Эта система не отвечает минимальным требованиям");

            if(checkMinimumSysReq.getCode() == 1){
                systemNameLabel.setTextFill(Color.RED);
                RAMLabel.setTextFill(Color.RED);
                sizeScreenLabel.setTextFill(Color.RED);
            }else if(checkMinimumSysReq.getCode() == 2){
                RAMLabel.setTextFill(Color.RED);
            }else if(checkMinimumSysReq.getCode() == 3){
                systemNameLabel.setTextFill(Color.RED);
            }else if(checkMinimumSysReq.getCode() == 4){
                sizeScreenLabel.setTextFill(Color.RED);
            }
        }

        appTitleLabel.setText("Название: "+propertiesClass.getProperty("app_title"));
        appVersionLabel.setText("Версия: "+propertiesClass.getProperty("app_version"));
        appBuildLabel.setText("Сборка: "+propertiesClass.getProperty("app_build"));
        appDeveloperLabel.setText("Разработчик: "+propertiesClass.getProperty("app_developer"));

        brightnessSlider.setMax(1);
        ContrastSlider.setMax(1);
        HueSlider.setMax(1);
        SaturationSlider.setMax(1);
    }

    private void setStyleForMenuItem(@NotNull MenuItem[] menuItems){
        for(int i = 0; i!=menuItems.length; i++) {
            menuItems[i].setStyle("-fx-text-fill: white; -fx-font-family: 'Arial Black'");
        }
    }

    private void setOpacity(float value){
        for(int i = 0; i!=opacityNodes.size(); i++){
            opacityNodes.get(i).setOpacity(value);
        }
    }

    private void addActionsOnControlsButtons() {
        try {
            controlsBarHbox.setOnMousePressed(event ->{
                x = event.getSceneX();
                y = event.getSceneY();
            });

            controlsBarHbox.setOnMouseDragged(event ->{
                controlsBarHbox.getScene().getWindow().setX(event.getScreenX()-x);
                controlsBarHbox.getScene().getWindow().setY(event.getScreenY()-y);
            });

            closeWindowImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    askBeforeExit();
                }
            });

            hideWindowImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) getStage().setIconified(true);
            });

            soundVolumeSlider.valueProperty().addListener(listener ->{
                soundsVolumeValueLabel.setText(Math.round(((soundVolumeSlider.getValue() * 100) / 6)) + "%");
                soundsVolume = soundVolumeSlider.getValue();
            });

            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13; -fx-border-radius: 13; -fx-border-color: white");
            MenuItem menuItemMinimize = new MenuItem("Свернуть");
            MenuItem menuItemClose = new MenuItem("Закрыть");
            MenuItem menuItemPinOnTop = new MenuItem();

            setStyleForMenuItem(new MenuItem[]{menuItemMinimize, menuItemClose, menuItemPinOnTop});

            contextMenu.getItems().addAll(menuItemMinimize, menuItemPinOnTop, menuItemClose);

            menuItemPinOnTop.setText("Закрепить");

            menuItemMinimize.setOnAction(action -> getStage().setIconified(true));
            menuItemClose.setOnAction(actionEvent -> askBeforeExit());
            menuItemPinOnTop.setOnAction(actionEvent -> {
                getStage().setAlwaysOnTop(!getStage().isAlwaysOnTop());
                menuItemPinOnTop.setText(getStage().isAlwaysOnTop() ? "Открепить": "Закрепить");
            });

            iconImageView.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(iconImageView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    Stage getStage(){
        return stage;
    }

    private void showTextHadCopiedInfo(){
        new Thread(() ->{
            Platform.runLater(() -> copySomethingPaneInAboutProgramBlock.setPrefHeight(86d));
            Platform.runLater(() -> textHadCopiedLabelHbox.setVisible(true));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> copySomethingPaneInAboutProgramBlock.setPrefHeight(65d));
            Platform.runLater(() -> textHadCopiedLabelHbox.setVisible(false));
        }).start();
    }

    float getPerCents(float number, float max){
        return Math.round((number * 100) / max);
    }

    private void setBgColor(Color color){
        BackgroundFill backgroundFill = new BackgroundFill(color, new CornerRadii(1), null);
        mainVBox.setBackground(new Background(backgroundFill));
    }
    
    private void removePasswordFromAccount(){
        confirmToRemovePasswordPane.setVisible(true);
        setPasswordToAccountButton.setVisible(false);
    }

    private void createScreenshot(){
        try {
            String fileName = time.getLocalTime().getHour()+"_"+time.getLocalTime().getMinute()+"_"+time.getLocalTime().getSecond() + "_" + time.getDate() + ".png";

            BufferedImage bufferedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(bufferedImage, "png", new File("userGallery/" + fileName));

            SendNotification sendNotification = new SendNotification("Снимок экрана сохранён как "+fileName + ". \n Нажмите, чтобы открыть", "Ваш снимок экрана сохранён", TrayIcon.MessageType.INFO);
            sendNotification.display();
            sendNotification.getTrayIcon().addActionListener(actionListener ->{
                if(Desktop.isDesktopSupported()){
                    try {
                        Desktop.getDesktop().open(new File("userGallery/"+fileName));
                    } catch (IOException e) {
                        showExceptions.showException(e);
                    }
                }else showExceptions.showException(new Exception("Desktop is not supported"));
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    ProgressBar createProgressBar(){
        return new ProgressBar();
    }

    private void setActionsOnObjects() {
        try {
            standartBG1Button.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    setBg("background/bg.png");
                    database.changeValue(DatabaseConstants.BACKGROUND_IMAGE, "background/bg.png", DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });

            standartBG2Button.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    setBg("background/bg_2.jpg");
                    database.changeValue(DatabaseConstants.BACKGROUND_IMAGE, "background/bg_2.jpg", DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });

            makeScreenshotButton.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    createScreenshot();
                }
            });

            simpleViewButton.setOnAction(actionEvent -> {
                playClickSound();

                setSimpleViewInAppearanceSettings();
            });

            turnOnOrOffAnimationsButton.setOnAction(actionEvent -> {
                playClickSound();

                if(database.getElementFromColumn(DatabaseConstants.USE_ANIMATIONS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    turnOnOrOffAnimationsButton.setText("Вкл. анимации");
                    database.changeValue(DatabaseConstants.USE_ANIMATIONS, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                    useAnimations = false;
                }else{
                    turnOnOrOffAnimationsButton.setText("Выкл. анимации");
                    database.changeValue(DatabaseConstants.USE_ANIMATIONS, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    useAnimations = true;
                }
            });

            resetAppearenceSettingsButton.setOnAction(actionEvent -> {
                playClickSound();

                resetAppearanceSettings();
            });

            hideToolsBarButton.setOnAction(actionEvent -> {
                playClickSound();

                animToolBar(false);
            });

            removeAccountInSettingsButton.setOnAction(actionEvent -> {
                playClickSound();

                buttonRemoveAccountAndExutHBox.setVisible(false);
                buttonsToConfirmRemoveAccountHbox.setVisible(true);
            });

            confirmToRemoveAccountIVButton.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    database.deleteUser(String.valueOf(id));

                    buttonRemoveAccountAndExutHBox.setVisible(true);
                    buttonsToConfirmRemoveAccountHbox.setVisible(false);

                    ((Stage) splashLabel.getScene().getWindow()).close();
                    openStartWindow();
                }
            });

            rejectToRemoveAccountIVButton.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    buttonRemoveAccountAndExutHBox.setVisible(true);
                    buttonsToConfirmRemoveAccountHbox.setVisible(false);
                }
            });

            showingIconsRequestsInSearchRadioButton.setOnAction(actionEvent -> {
                if(database.getElementFromColumn(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    database.changeValue(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                }else database.changeValue(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
            });

            turnOnorOffSoundsButton.setOnAction(actionEvent -> {
                playClickSound();

                if(database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    enablePlaySounds = false;
                    database.changeValue(DatabaseConstants.USE_SOUNDS, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                    turnOnorOffSoundsButton.setText("Вкл. звуки");
                    soundVolumeSlider.setDisable(true);
                }else{
                    enablePlaySounds = true;
                    database.changeValue(DatabaseConstants.USE_SOUNDS, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    turnOnorOffSoundsButton.setText("Выкл. звуки");
                    soundVolumeSlider.setDisable(false);
                }
            });

            applyToRemovePasswordIV.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();
                    database.setNull(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(id));
                    confirmToRemovePasswordPane.setVisible(false);
                    setPasswordToAccountButton.setVisible(true);
                    setPasswordToAccountButton.setText("Установить пароль на аккаунт");

                    setPasswordToAccountButton.setOnAction(actionEvent -> {
                        playClickSound();

                        buttonToSetPasswordPane.setVisible(true);
                        setPasswordToAccountButton.setVisible(false);
                    });
                }
            });

            cancelToRemovePasswordIV.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();
                    
                    confirmToRemovePasswordPane.setVisible(false);
                    setPasswordToAccountButton.setVisible(true);
                }
            });

            turnOnOrOffSearchUsingButton.setOnAction(actionEvent -> {
                playClickSound();

                String enable = database.getElementFromColumn(DatabaseConstants.USE_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id));

                if(enable.equals("1")){
                    database.changeValue(DatabaseConstants.USE_SEARCH, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                    if(SearchVBox.isVisible()) SearchVBox.setVisible(false);
                    searchButtonImageView.setVisible(false);
                    turnOnOrOffSearchUsingButton.setText("Вкл. поиск");
                }else{
                    database.changeValue(DatabaseConstants.USE_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    searchButtonImageView.setVisible(true);
                    turnOnOrOffSearchUsingButton.setText("Выкл. поиск");
                }
            });

            continueButtonInSettingsPasswordByTheHand.setOnAction(actionEvent -> {
                playClickSound();

                if(!textFieldToSetPasswordByTheHand.getText().trim().isEmpty()){
                    if(!(textFieldToSetPasswordByTheHand.getText().trim().length() < 6)) {
                        database.changeValue(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, textFieldToSetPasswordByTheHand.getText().trim(), DatabaseConstants.USER_ID, String.valueOf(id));
                        textFieldToSetPasswordByTheHand.clear();

                        buttonToSetPasswordPane.setVisible(true);
                        setPasswordByTheHandPane.setVisible(false);
                    }else{
                        setErrorViewOnTextFields.setErrorViewToAnyField(textFieldToSetPasswordByTheHand, null);
                    }
                }else{
                    setErrorViewOnTextFields.setErrorViewToAnyField(textFieldToSetPasswordByTheHand, null);
                }
            });

            useOneColorCheckBox.setOnAction(actionEvent -> {
                if(useOneColorCheckBox.isSelected()){
                    mainColorColorPickerInAppearence.setDisable(false);
                    loadImageForBGPane.setDisable(true);

                    database.changeValue(DatabaseConstants.BACKGROUND_MODE, String.valueOf(BG_MODE_COLOR), DatabaseConstants.USER_ID, String.valueOf(id));
                    database.changeValue(DatabaseConstants.BACKGROUND_COLOR, String.valueOf(mainColorColorPickerInAppearence.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
                }else{
                    mainColorColorPickerInAppearence.setDisable(true);
                    loadImageForBGPane.setDisable(false);

                    database.changeValue(DatabaseConstants.BACKGROUND_MODE, String.valueOf(BG_MODE_IMAGE), DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });

            mainColorColorPickerInAppearence.setOnAction(actionEvent -> {
                setBgColor(mainColorColorPickerInAppearence.getValue());
                database.changeValue(DatabaseConstants.BACKGROUND_COLOR, String.valueOf(mainColorColorPickerInAppearence.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
            });

            restoreBgImageLabel.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    resetBg();
                }
            });

            applyButtonToShowElementsOnToolsBar.setOnAction(actionEvent -> {
                try {
                    playClickSound();
                    applyToShowEllementsOnToolBar();
                }catch (Exception ex){
                    showExceptions.showException(ex);
                }
            });

            hideToolsBarAutoRadioButton.setOnAction(actionEvent -> {
                if(hideToolsBarAutoRadioButton.isSelected()){
                    setAutoHideToolBar();
                    animToolBar(false);
                    database.changeValue(DatabaseConstants.AUTO_HIDE_TASKBAR, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                }else{
                    mainVBox.setOnMouseMoved(mouseEvent -> {});
                    animToolBar(true);
                    database.changeValue(DatabaseConstants.AUTO_HIDE_TASKBAR, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });

            loadInmageButtonInAppearence.setOnAction(actionEvent -> {
                playClickSound();

                FileChooser fileChooserMusic = new FileChooser();
                fileChooserMusic.setTitle("\uD835\uDCDE\uD835\uDCF9\uD835\uDCEE\uD835\uDCF7 \uD835\uDCD8\uD835\uDCF6\uD835\uDCEA\uD835\uDCF0\uD835\uDCEE \uD835\uDCD5\uD835\uDCF2\uD835\uDCF5\uD835\uDCEE");
                fileChooserMusic.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Изображения", "*.png*", "*.jpg*", "*.jpeg*"),
                        new FileChooser.ExtensionFilter("Все", "*.*")
                );
                File selectedFile = fileChooserMusic.showOpenDialog(getStage());

                if(selectedFile!=null){
                    setBg(selectedFile.getPath());
                    choosedFilePathLabelInAppearence.setText(selectedFile.getName());
                    try {
                        FileUtils.copyFileToDirectory(selectedFile, new File("background/userBg"));
                        database.changeValue(DatabaseConstants.BACKGROUND_IMAGE, "background/userBg/"+selectedFile.getName(), DatabaseConstants.USER_ID, String.valueOf(id));
                    } catch (IOException e) {
                        showExceptions.showException(e);
                    }
                }
            });
            
            disableSplashesButton.setOnAction(actionEvent -> {
                playClickSound();

                if(database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    database.changeValue(DatabaseConstants.DISPLAY_SPLASHES, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                    splashLabel.setVisible(false);
                    disableSplashesButton.setText("Включить сплеши");
                }else{
                    database.changeValue(DatabaseConstants.DISPLAY_SPLASHES, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    splashLabel.setVisible(true);

                    if(!splashesWereInitialized) initSplashes();

                    setSplash();
                    disableSplashesButton.setText("Отключить сплеши");
                }
            });

            displayYourSplashButton.setOnAction(actionEvent -> {
                playClickSound();

                makeControlsForSetYourSplashVisible();
            });

            opacitySlider.valueProperty().addListener(listener ->{
                opacityValue.setText(getPerCents((float) opacitySlider.getValue(), (float) opacitySlider.getMax()) + "%");
                database.changeValue(DatabaseConstants.OPACITY_LEVEL, String.valueOf(opacitySlider.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
                setOpacity((float) opacitySlider.getValue());
            });

            SaturationSlider.valueProperty().addListener(listener ->{
                SaturationValue.setText(getPerCents((float) SaturationSlider.getValue(), (float) SaturationSlider.getMax()) + "%");
                saturation = SaturationSlider.getValue();
                setColorSettings(hue, saturation, brightness, contrast);
            });

            ContrastSlider.valueProperty().addListener(listener ->{
                ContrastValue.setText(getPerCents((float) ContrastSlider.getValue(), (float) ContrastSlider.getMax()) + "%");
                contrast = ContrastSlider.getValue();
                setColorSettings(hue, saturation, brightness, contrast);
            });

            HueSlider.valueProperty().addListener(listener ->{
                HueValue.setText(getPerCents((float) HueSlider.getValue(), (float) HueSlider.getMax()) + "%");
                hue = HueSlider.getValue();
                setColorSettings(hue, saturation, brightness, contrast);
            });

            brightnessSlider.valueProperty().addListener(listener ->{
                brightnessValue.setText(getPerCents((float) brightnessSlider.getValue(), (float) brightnessSlider.getMax()) + "%");
                brightness = brightnessSlider.getValue();
                setColorSettings(hue, saturation, brightness, contrast);
            });

            weatherPane.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    openWeatherWidget(widgetDidOpened);
                 }
            });

            removeAllRecentChats.setDisable(true);

            removeAllRecentChats.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    removeAllRecentChats.setVisible(false);
                    applyToRemoveRecentChatsButtonIV.setVisible(true);
                    rejectToRemoveButtonRecentChatsIV.setVisible(true);
                }
            });

            rejectToRemoveButtonRecentChatsIV.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    removeAllRecentChats.setVisible(true);
                    applyToRemoveRecentChatsButtonIV.setVisible(false);
                    rejectToRemoveButtonRecentChatsIV.setVisible(false);
                }
            });

            applyToRemoveRecentChatsButtonIV.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    new Thread(() ->{
                        ProgressBar progressBar = createProgressBar();
                        Platform.runLater(() -> progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS));

                        Platform.runLater(() -> chatListVboxInScrollPane.getChildren().removeAll(chatListVboxInScrollPane.getChildren()));
                        recentChatsCounter = 0;
                        Platform.runLater(() -> recentChatsLabel.setText("Недавние чаты (0)"));

                        Platform.runLater(() -> chatListVboxInScrollPane.getChildren().add(0, progressBar));

                        Platform.runLater(() -> removeAllRecentChats.setVisible(true));
                        Platform.runLater(() -> applyToRemoveRecentChatsButtonIV.setVisible(false));
                        Platform.runLater(() -> rejectToRemoveButtonRecentChatsIV.setVisible(false));

                        try {
                            FileUtils.cleanDirectory(new File("users/recentChats/"+userName));
                        } catch (IOException e) {
                            showExceptions.showException(e);
                        }

                        Platform.runLater(() -> chatListVboxInScrollPane.getChildren().remove(0));
                        Platform.runLater(() -> removeAllRecentChats.setDisable(true));
                    }).start();
                }
            });

            createChatButton.setOnAction(event -> {
                try {
                    playClickSound();

                    if(!createChatPane.isVisible()){
                        createChatPane.setVisible(true);
                        setAnimationForSettings(createChatPane, 100);

                        int port  = generatePort();
                        String address = InetAddress.getLocalHost().getHostAddress();

                        addressLabelInCreateChatPane.setText("Адрес: "+address);
                        portLabelInCreateChatPane.setText("Порт: "+port);
                        chatPort = port;
                    }else{
                        new Thread(() ->{
                            playAnimToTileInDesktop(createChatPane);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            createChatPane.setVisible(false);
                        }).start();
                    }

                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            });

            continueInCreateChatPaneButton.setOnAction(actionEvent -> {
                playClickSound();

                try {
                    new Thread(() ->{
                        try {
                            serverTest = new ServerTest(chatPort);
                            serverTest.userName = userName;
                        } catch (Exception e) {
                            showExceptions.showException(e);
                        }
                    }).start();

                    createChat(InetAddress.getLocalHost().getHostAddress()+":"+chatPort);

                    hideCreateChatWindow();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            });

            createAnotherPortLabel.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    int port  = generatePort();
                    portLabelInCreateChatPane.setText("Порт: "+port);
                    chatPort = port;
                }
            });

            cancelToCreateChatLabel.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    hideCreateChatWindow();
                }
            });

            connectToTheChatButton.setOnAction(event -> {
                playClickSound();

                openConnectToTheChatMenu();
            });

            closeConnectToTheChatPaneLabel.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    new Thread(() ->{
                        playAnimToTileInDesktop(connectToTheChatPane);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        connectToTheChatPane.setVisible(false);
                        connectToTheChatTilePane.setDisable(false);
                    }).start();

                }
            });

            chatAddressTF.setOnKeyPressed(key -> {
                if (key.getCode() == KeyCode.ENTER) {
                    if (chatAddressTF.getText().trim().isEmpty()) {
                        setErrorViewOnTextFields.setErrorViewToAnyField(chatAddressTF, null);
                    } else {
                        prepareToConnect();
                    }
                }
            });

            continueButtonConnectToTheChat.setOnAction(event -> {
                playClickSound();

                if (chatAddressTF.getText().trim().isEmpty()) {
                    setErrorViewOnTextFields.setErrorViewToAnyField(chatAddressTF, null);
                } else {
                    prepareToConnect();
                }
            });

            resetAllLabelInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    resetAllLabelInSettings.setVisible(false);
                    applyButtonIVYoResetAllSettings.setVisible(true);
                    cancelButtonIVInResetAllSettings.setVisible(true);
                }
            });

            applyButtonIVYoResetAllSettings.setOnMouseClicked(event ->{
                if(event.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    resetAllLabelInSettings.setVisible(true);
                    applyButtonIVYoResetAllSettings.setVisible(false);
                    cancelButtonIVInResetAllSettings.setVisible(false);

                    restoreAllSettings();
                }
            });

            cancelButtonIVInResetAllSettings.setOnMouseClicked(event ->{
                if(event.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    resetAllLabelInSettings.setVisible(true);
                    applyButtonIVYoResetAllSettings.setVisible(false);
                    cancelButtonIVInResetAllSettings.setVisible(false);
                }
            });

            getbackInSettingsImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    recentChatsListVBox.setVisible(true);
                    toolBarHbox.setVisible(true);
                    settingsHBox.setVisible(false);
                }
            });

            settingsImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    openSettings();
                }
            });

            turnOffImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    askBeforeExit();
                }
            });

            searchButtonImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    openSearch();
                }
            });

            appearenceBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }
            });

            accountBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    displaySettings(PROFILE_SETTINGS);
                }
            });

            searchBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    displaySettings(SettingsConstants.SEARCH_SETTINGS);
                }
            });

            aboutPropgramBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    displaySettings(SettingsConstants.ABOUT_PROGRAM);
                }
            });

            additionalBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    displaySettings(SettingsConstants.ADD_SETTINGS);
                }
            });

            changeLoginButton.setOnAction(event -> {
                playClickSound();

                showControlsToChangeUserData(3);
            });
            changePasswordButton.setOnAction(event -> {
                playClickSound();

                showControlsToChangeUserData(2);
            });
            changeCityButton.setOnAction(event -> {
                playClickSound();

                showControlsToChangeUserData(1);
            });
            cancelButtonInUserSettings.setOnAction(event -> {
                playClickSound();

                hideControlsToChangeUserData();
            });

            copyAboutSystemLabel.setOnMouseClicked(listener ->{
                if(listener.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    String textToCopy = systemNameLabel.getText()+"\n"+RAMLabel.getText()+"\n"+sizeScreenLabel.getText();
                    copyText(textToCopy);
                    showTextHadCopiedInfo();
                }
            });

            copyAboutProgramLabel.setOnMouseClicked(listener ->{
                if(listener.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    String textToCopy = appTitleLabel.getText()+"\n"+appVersionLabel.getText()+"\n"+appBuildLabel.getText()+"\n"+appDeveloperLabel.getText();
                    copyText(textToCopy);
                    showTextHadCopiedInfo();
                }
            });

            cityWhereNeedToShowWeatherTextField.setOnKeyPressed(key ->{
                if(key.getCode()==KeyCode.ENTER){
                    if(!cityWhereNeedToShowWeatherTextField.getText().trim().isEmpty()){
                        try {
                            updateWeather(cityWhereNeedToShowWeatherTextField.getText());
                        } catch (Exception e) {
                            showExceptions.showException(e);
                        }
                        cityWhereNeedToShowWeatherTextField.clear();
                    }else setErrorViewOnTextFields.setErrorViewToAnyField(cityWhereNeedToShowWeatherTextField, null);
                }
            });

            updateWeatherDataButtonInSettings.setOnAction(event ->{
                try {
                    playClickSound();

                    updateWeather(city);
                } catch (Exception e) {
                    showExceptions.showException(e);
                }
            });

            setPasswordToAccountButton.setOnAction(event -> {
                playClickSound();

                buttonToSetPasswordPane.setVisible(true);
                setPasswordToAccountButton.setVisible(false);
            });
            cancelToSetPasswordButton.setOnAction(event -> {
                playClickSound();

                buttonToSetPasswordPane.setVisible(false);
                setPasswordToAccountButton.setVisible(true);
            });

            setPasswordByTheHandButton.setOnAction(event ->{
                playClickSound();

                buttonToSetPasswordPane.setVisible(false);
                setPasswordByTheHandPane.setVisible(true);
            });

            cancelSettingPasswordByTheHandLabel.setOnMouseClicked(event ->{
                if(event.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    buttonToSetPasswordPane.setVisible(true);
                    setPasswordByTheHandPane.setVisible(false);
                }
            });

            userWidgetPane.setOnMouseClicked(event ->{
                if(event.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                }
            });

            mainVBox.setOnMouseMoved(mouseEvent -> {
                //System.out.println(mouseEvent.getScreenX()+"; "+mouseEvent.getScreenY());
            });

            requestConfirmBeforeCloseButton.setOnAction(actionEvent -> {
                playClickSound();

                enableConfirmBefore = !enableConfirmBefore;

                if(enableConfirmBefore) database.changeValue(DatabaseConstants.ASKING_BEFORE_CLOSE, "1", DatabaseConstants.USER_ID, String.valueOf(id)); else
                    database.changeValue(DatabaseConstants.ASKING_BEFORE_CLOSE, "0", DatabaseConstants.USER_ID, String.valueOf(id));

                String text = enableConfirmBefore ? "Не спрашивать перед закрытием": "Спрашивать перед закрытием";
                requestConfirmBeforeCloseButton.setText(text);
            });

            exitFromAccountInSettingsButton.setOnAction(actionEvent -> {
                playClickSound();

                ((Stage) splashLabel.getScene().getWindow()).close();
                openStartWindow();
            });

            turnOnorOffWeatherButton.setOnAction(actionEvent -> {
                playClickSound();

                if(database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    turnOnorOffWeatherButton.setText("Показывать погоду");
                    weatherPane.setVisible(false);
                    if(widgetDidOpened.get()) animateWeatherWidget(false);
                    updateWeatherDataButtonInSettings.setDisable(true);
                    cityWhereNeedToShowWeatherTextField.setDisable(true);
                    database.changeValue(DatabaseConstants.USE_WEATHER, "0", DatabaseConstants.USER_ID, String.valueOf(id));
                }else{
                    turnOnorOffWeatherButton.setText("Не показывать погоду");
                    weatherPane.setVisible(true);
                    updateWeatherDataButtonInSettings.setDisable(false);
                    cityWhereNeedToShowWeatherTextField.setDisable(false);
                    database.changeValue(DatabaseConstants.USE_WEATHER, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    try {
                        updateWeather(city);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void hideCreateChatWindow() {
        new Thread(() ->{
            playAnimToTileInDesktop(createChatPane);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            createChatPane.setVisible(false);
        }).start();
    }

    private int generatePort() {
        return (int) (Math.random() * (9999 - 1000) + 1000);
    }

    private void applyToShowEllementsOnToolBar() {
        try {
            CheckBox[] checkBoxes = new CheckBox[disableContentOnToolBarVBox.getChildren().size()];

            for (int i = 0; i != disableContentOnToolBarVBox.getChildren().size(); i++) {
                if (!disableContentOnToolBarVBox.getChildren().get(i).toString().contains("Применить"))
                    checkBoxes[i] = (CheckBox) disableContentOnToolBarVBox.getChildren().get(i);
            }

            for (int i = 0; i != checkBoxes.length - 1; i++) {
                hideEllementOnToolBar(checkBoxes[i].getText().hashCode(), checkBoxes[i].isSelected());
            }

            writeToDatabaseWhatIconsToShowInToolBar(checkBoxes);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void resetAddSettings(){
        try{
            disableSplashesButton.setText("Отключить сплеши");
            database.changeValue(DatabaseConstants.DISPLAY_SPLASHES, "1", DatabaseConstants.USER_ID, String.valueOf(id));
            splashLabel.setVisible(true);
            initSplashes();

            requestConfirmBeforeCloseButton.setText("Не спрашивать перед закрытием");
            database.changeValue(DatabaseConstants.ASKING_BEFORE_CLOSE, "1", DatabaseConstants.USER_ID, String.valueOf(id));

            hideToolsBarAutoRadioButton.setSelected(false);
            database.changeValue(DatabaseConstants.AUTO_HIDE_TASKBAR, "0", DatabaseConstants.USER_ID, String.valueOf(id));
            animToolBar(true);

            for(int i = 0; i!=disableContentOnToolBarVBox.getChildren().size(); i++){
                if(!disableContentOnToolBarVBox.getChildren().get(i).toString().contains("Применить")){
                    CheckBox checkBox = (CheckBox) disableContentOnToolBarVBox.getChildren().get(i);
                    checkBox.setSelected(true);
                }
            }
            applyToShowEllementsOnToolBar();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setSimpleViewInAppearanceSettings(){
        try {
            opacitySlider.setValue(1f);
            opacityValue.setText(getPerCents((float) opacitySlider.getValue(), (float) opacitySlider.getMax()) + "%");
            database.changeValue(DatabaseConstants.OPACITY_LEVEL, String.valueOf(opacitySlider.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
            setOpacity(1f);

            turnOnorOffSoundsButton.setText("Вкл. звуки");
            soundVolumeSlider.setDisable(true);
            database.changeValue(DatabaseConstants.USE_SOUNDS, "0", DatabaseConstants.USER_ID, String.valueOf(id));
            enablePlaySounds = false;

            setColorSettings(0, 0, 0, 0);
            brightnessSlider.setValue(0);
            brightnessValue.setText(getPerCents((float) brightnessSlider.getValue(), (float) brightnessSlider.getMax()) + "%");

            HueSlider.setValue(0);
            HueValue.setText(getPerCents((float) HueSlider.getValue(), (float) HueSlider.getMax()) + "%");

            SaturationSlider.setValue(0);
            SaturationValue.setText(getPerCents((float) SaturationSlider.getValue(), (float) SaturationSlider.getMax()) + "%");

            ContrastSlider.setValue(0);
            ContrastValue.setText(getPerCents((float) ContrastSlider.getValue(), (float) ContrastSlider.getMax()) + "%");

            turnOnOrOffAnimationsButton.setText("Вкл. анимации");
            useAnimations = false;
            database.changeValue(DatabaseConstants.USE_ANIMATIONS, "0", DatabaseConstants.USER_ID, String.valueOf(id));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void resetAppearanceSettings() {
        try {
            database.changeValue(DatabaseConstants.BACKGROUND_MODE, String.valueOf(BG_MODE_IMAGE), DatabaseConstants.USER_ID, String.valueOf(id));
            resetBg();
            useOneColorCheckBox.setSelected(false);
            mainColorColorPickerInAppearence.setDisable(true);
            loadImageForBGPane.setDisable(false);

            opacitySlider.setValue(0.9);
            opacityValue.setText(getPerCents((float) opacitySlider.getValue(), (float) opacitySlider.getMax()) + "%");
            database.changeValue(DatabaseConstants.OPACITY_LEVEL, String.valueOf(opacitySlider.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
            setOpacity(0.9f);

            turnOnorOffSoundsButton.setText("Выкл. звуки");
            soundVolumeSlider.setValue(4);
            soundVolumeSlider.setDisable(false);
            soundsVolumeValueLabel.setText(getPerCents((float) soundVolumeSlider.getValue(), (float) soundVolumeSlider.getMax()) + "%");
            database.changeValue(DatabaseConstants.SOUNDS_VOLUME, String.valueOf(soundVolumeSlider.getValue()), DatabaseConstants.USER_ID, String.valueOf(id));
            enablePlaySounds = true;

            turnOnorOffWeatherButton.setText("Не показывать погоду");
            weatherPane.setVisible(true);
            updateWeatherDataButtonInSettings.setDisable(false);
            cityWhereNeedToShowWeatherTextField.setDisable(false);
            database.changeValue(DatabaseConstants.USE_WEATHER, "1", DatabaseConstants.USER_ID, String.valueOf(id));
            initWeather();
            try {
                updateWeather(city);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            setColorSettings(0, 0, 0, 0);
            brightnessSlider.setValue(0);
            brightnessValue.setText(getPerCents((float) brightnessSlider.getValue(), (float) brightnessSlider.getMax()) + "%");

            HueSlider.setValue(0);
            HueValue.setText(getPerCents((float) HueSlider.getValue(), (float) HueSlider.getMax()) + "%");

            SaturationSlider.setValue(0);
            SaturationValue.setText(getPerCents((float) SaturationSlider.getValue(), (float) SaturationSlider.getMax()) + "%");

            ContrastSlider.setValue(0);
            ContrastValue.setText(getPerCents((float) ContrastSlider.getValue(), (float) ContrastSlider.getMax()) + "%");

            turnOnOrOffAnimationsButton.setText("Выкл. анимации");
            useAnimations = true;
            database.changeValue(DatabaseConstants.USE_ANIMATIONS, "1", DatabaseConstants.USER_ID, String.valueOf(id));
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void resetBg() {
        try {
            setBg("background/bg.png");
            String oldBgSource = database.getElementFromColumn(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id));
            if (oldBgSource != null) {
                if (new File(oldBgSource).exists()) {
                    if(!new File(oldBgSource).getPath().equals("background/bg.png") & !new File(oldBgSource).getPath().equals("background/bg2.jpg")) new File(oldBgSource).delete();
                }
                database.setNull(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id));
            }
            choosedFilePathLabelInAppearence.setText("Ничего не выбрано");
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void openSearch() {
        if(!SearchVBox.isVisible()){
            SearchVBox.setVisible(true);
            setAnimationForSettings(SearchVBox, 100);
        }else{
            new Thread(() ->{
                playAnimToTileInDesktop(SearchVBox);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SearchVBox.setVisible(false);
            }).start();
        }
    }

    private void openWeatherWidget(AtomicBoolean widgetDidOpened) {
        weatherWidgetVBox.setVisible(true);

        if(!widgetDidOpened.get()) {
            animateWeatherWidget(true);
            widgetDidOpened.set(true);
        }else {
            animateWeatherWidget(false);
            widgetDidOpened.set(false);
        }
    }

    private void openConnectToTheChatMenu() {
        connectToTheChatPane.setVisible(true);
        connectToTheChatTilePane.setDisable(true);

        setAnimationForSettings(connectToTheChatPane, 100);
    }

    private void playAnimToTileInDesktop(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), node);
        translateTransition.setFromY(0);
        translateTransition.setToY(-55);
        translateTransition.play();
    }

    private void testMethod() {
        if(aint == 0){
            setBg("background/bg.png");
        }else if(aint == 1){
            setBg("background/bg_2.png");
        }else if(aint == 2){
            setBg("background/bg_3.jpg");
        }
        aint++;
        if(aint > 2) aint = 0;
    }

    private void animateWeatherWidget(boolean open) {
        try {
            int fromX = open ? 500 : 0;
            int toX = open ? 0 : 500;

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), weatherWidgetVBox);
            translateTransition.setFromX(fromX);
            translateTransition.setToX(toX);
            translateTransition.play();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void hideEllementOnToolBar(int hashCode, boolean b) {
        if(hashCode == turnOffButton){
            turnOffImageView.setVisible(b);
        }
        if(hashCode == MPButton){
            MusicImageView.setVisible(b);
        }
        if(hashCode == SettingsButton){
            settingsImageView.setVisible(b);
        }
        if(hashCode == weatherWidget){
            weatherPane.setVisible(b);
        }
        if(hashCode == userWidget){
            userWidgetPane.setVisible(b);
        }
    }

    private void makeControlsForSetYourSplashVisible() {
        try {
            splashSettingsBlockPane.setPrefHeight(231);
            setYourSplashHBoxControls.setVisible(true);

            displayYourSplashButton.setText("Отмена");
            displayYourSplashButton.setStyle("-fx-background-color: red");

            continueButtonToSetYourSplash.setOnAction(actionEvent -> {
                playClickSound();

                if (!enterYourSplashTF.getText().trim().isEmpty()) {
                    splashLabel.setText(enterYourSplashTF.getText().trim());
                    enterYourSplashTF.clear();
                    cancelToSetYourSplash();
                }else setErrorViewOnTextFields.setErrorViewToAnyField(enterYourSplashTF, null);
            });

            displayYourSplashButton.setOnAction(actionEvent -> {
                playClickSound();

                cancelToSetYourSplash();
            });
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void cancelToSetYourSplash() {
        try {
            displayYourSplashButton.setText("Отобразить свой сплеш");
            displayYourSplashButton.setStyle("-fx-background-color: rgb(79,82,178)");

            splashSettingsBlockPane.setPrefHeight(172);
            setYourSplashHBoxControls.setVisible(false);

            displayYourSplashButton.setOnAction(actionEvent1 -> makeControlsForSetYourSplashVisible());
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void restoreAllSettings() {
        try {
            new Thread(() ->{
                try {
                    showProgressBarInSettings(true);
                    Platform.runLater(() -> settingsProgressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS));

                    Platform.runLater(this::resetAppearanceSettings);
                    Platform.runLater(this::restoreSearchSettings);
                    Platform.runLater(this::resetAddSettings);

                    Platform.runLater(() -> settingsProgressBar.setVisible(false));
                    Platform.runLater(() -> resetAllLabelInSettings.setVisible(true));

                    SendNotification sendNotification = new SendNotification("Ваши настройки были восстановлены, " + userName, propertiesClass.getProperty("app_title"), TrayIcon.MessageType.INFO);
                    sendNotification.display();
                }catch (Exception ex){
                    showExceptions.showException(ex);
                }
            }).start();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void updateWeather(String place) throws Exception {
        Weather weatherToUpdate = new Weather(place);
        weatherToUpdate.init();

        displayWeatherInfoOnMainScreen(weatherToUpdate.getTemperature(), weatherToUpdate.getDescription(), weatherToUpdate.getMain());
        displayWeatherInWeatherWidget(weatherToUpdate);
    }

    private void displayWeatherInfoOnMainScreen(String temp, String description, String main) {
        try {
            float tempKelvins = Float.parseFloat(temp);
            float tempCelsius = (float) (tempKelvins - 273.15);

            weatherImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(requestsImageForWeather(main))).openStream()));

            weatherTempLabel.setText(Math.round(tempCelsius) + "ºC" + " / " + Math.round((tempCelsius * 1.8) + 32) + "ºF");
            weatherDescriptionLabel.setText(description);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void hideAllSettingsBlocks(){
        appearanceScrollPane.setVisible(false);
        userSettingsBlockPane.setVisible(false);
        searchPaneInSettings.setVisible(false);
        aboutProgramPaneInSettings.setVisible(false);
        addBlockInSettingsPane.setVisible(false);
    }

    private void setAnimationForSettings(Node node, int duration){
        if(useAnimations) {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
            translateTransition.setFromY(-15);
            translateTransition.setToY(0);
            translateTransition.play();
        }
    }

    private void displaySettings(int i) {
        try {
            if (i == SettingsConstants.APPEARANCE_SETTINGS) {
                hideAllSettingsBlocks();

                appearanceScrollPane.setVisible(true);

                setAnimationForSettings(appearanceScrollPane, 200);

                blockTitleInSettings.setText("Оформление");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/aooearence2.png")).openStream()));
            } else if (i == PROFILE_SETTINGS) {
                hideAllSettingsBlocks();

                userSettingsBlockPane.setVisible(true);

                setAnimationForSettings(userSettingsBlockPane, 200);

                blockTitleInSettings.setText("Профиль");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/accountSettings.png")).openStream()));
            } else if (i == SettingsConstants.SEARCH_SETTINGS) {
                hideAllSettingsBlocks();

                searchPaneInSettings.setVisible(true);

                setAnimationForSettings(searchPaneInSettings, 200);

                blockTitleInSettings.setText("Поиск");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/searchSettings.png")).openStream()));
            } else if (i == SettingsConstants.ABOUT_PROGRAM) {
                hideAllSettingsBlocks();

                aboutProgramPaneInSettings.setVisible(true);

                setAnimationForSettings(aboutProgramPaneInSettings, 200);

                blockTitleInSettings.setText("О программе");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/about.png")).openStream()));
            }else if (i == SettingsConstants.ADD_SETTINGS) {
                hideAllSettingsBlocks();

                addBlockInSettingsPane.setVisible(true);

                setAnimationForSettings(addBlockInSettingsPane, 200);

                blockTitleInSettings.setText("Дополнительно");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/other.png")).openStream()));
            }

        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void initSearch(){
        Label nothingToShowLabel = new Label("Ничего не найдено");
        nothingToShowLabel.setWrapText(true);
        nothingToShowLabel.setStyle("-fx-text-fill: white");

        ObservableList<SearchRequests> requests = FXCollections.observableArrayList(
                new SearchRequests("Музыка"),
                new SearchRequests("Подключиться к чату"),
                new SearchRequests("Настройки"),
                new SearchRequests("Настройки профиля"),
                new SearchRequests("Создать чат"),
                new SearchRequests("Редактировать панель инструментов"),
                new SearchRequests("Очистить список недавних чатов"),
                new SearchRequests("Список своей музыки"),
                new SearchRequests("Удалить аккаунт"),
                new SearchRequests("Выйти из аккаунта"),
                new SearchRequests("Закрыть мессенджер"),
                new SearchRequests("Оформление"),
                new SearchRequests("Настройки эффекта прозрачности"),
                new SearchRequests("Цвета"),
                new SearchRequests("Звуки"),
                new SearchRequests("Погода"),
                new SearchRequests("Сменить город"),
                new SearchRequests("Сменить пароль"),
                new SearchRequests("Сменить логин"),
                new SearchRequests("Справка"),
                new SearchRequests("О программе"),
                new SearchRequests("Сменить аватар"),
                new SearchRequests("Очистить логи об ошибках"),
                new SearchRequests("Посмотреть логи об ошибках"),
                new SearchRequests("Открыть папку со скриншотами"),
                new SearchRequests("Создать скриншот")
        );
        AtomicBoolean requestsIsFound = new AtomicBoolean(false);

        searchField.setOnKeyTyped(event ->{
            searchEllementVBox.getChildren().clear();

            if(!searchField.getText().trim().isEmpty()) {
                if(searchField.getText().trim().equalsIgnoreCase("Все")){
                    requestsIsFound.set(true);
                    for (int i = 0; i != requests.size(); i++) {
                        HBox searchResult;
                        try {
                            searchResult = createSearchTile(requests.get(i).request, getIcon(requests.get(i).request));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        searchEllementVBox.getChildren().add(searchResult);

                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), searchResult);
                        translateTransition.setFromX(60);
                        translateTransition.setToX(0);
                        translateTransition.play();
                    }
                }else {
                    for (int i = 0; i != requests.size(); i++) {
                        if (requests.get(i).request.toLowerCase().contains(searchField.getText().trim().toLowerCase())) {
                            requestsIsFound.set(true);
                            HBox searchResult;
                            try {
                                searchResult = createSearchTile(requests.get(i).request, getIcon(requests.get(i).request));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            searchEllementVBox.getChildren().add(searchResult);
                        }
                    }
                }
            }else{
                Label typeTextIntoSearchFieldLabel = new Label("Введите запрос в поле поиска");
                typeTextIntoSearchFieldLabel.setWrapText(true);
                typeTextIntoSearchFieldLabel.setTextFill(Color.WHITE);

                searchEllementVBox.getChildren().add(typeTextIntoSearchFieldLabel);
            }
        });

    }

    URL getProjectsFiles(String path){
        return getClass().getResource(path);
    }

    private Image getIcon(String request) throws IOException {
        Image image = null;

        try {
            int inputHashCode = request.hashCode();

            if (inputHashCode == SearchRequests.Constants.MUSIC) {
                image = new Image(getProjectsFiles("MainWindow/images/music.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CREATE_CHAT) {
                image = new Image(getProjectsFiles("MainWindow/images/createChat.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CONNECT_TO_THE_CHAT) {
                image = new Image(getProjectsFiles("MainWindow/images/connectToTheChat.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.SETTINGS) {
                image = new Image(getProjectsFiles("MainWindow/images/settings.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.PROFILE_SETTINGS) {
                image = new Image(getProjectsFiles("MainWindow/images/accountSettings.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.EDIT_TOOLSBAR) {
                image = new Image(getProjectsFiles("MainWindow/images/edit.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CLEAR_LIST_OF_RECENT_CHATS) {
                image = new Image(getProjectsFiles("MainWindow/images/clear.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.YOUR_MUSIC_LIST) {
                image = new Image(getProjectsFiles("MainWindow/images/musicList.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.REMOVE_ACCOUNT) {
                image = new Image(getProjectsFiles("MainWindow/images/removeAccount.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CLOSE_APP) {
                image = new Image(getProjectsFiles("MainWindow/images/turnoff.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.EXIT_FROM_ACCOUNT) {
                image = new Image(getProjectsFiles("MainWindow/images/exitFromAccount.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.APPEARANCE) {
                image = new Image(getProjectsFiles("MainWindow/images/appearence.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.SETTINGS_OF_OPACITY_EFFECT) {
                image = new Image(getProjectsFiles("MainWindow/images/opacity.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.COLORS) {
                image = new Image(getProjectsFiles("MainWindow/images/colorWheel.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.SOUNDS) {
                image = new Image(getProjectsFiles("MainWindow/images/sound.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.WEATHER) {
                image = new Image(getProjectsFiles("MainWindow/images/weather.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CHANGE_CITY) {
                image = new Image(getProjectsFiles("MainWindow/images/changeCity.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CHANGE_PASSWORD) {
                image = new Image(getProjectsFiles("MainWindow/images/password.png").openStream());
            } else if (inputHashCode == SearchRequests.Constants.CHANGE_LOGIN) {
                image = new Image(getProjectsFiles("MainWindow/images/login.png").openStream());
            }else if (inputHashCode == SearchRequests.Constants.GET_HELP) {
                image = new Image(getProjectsFiles("Help/images/help.png").openStream());
            }else if (inputHashCode == SearchRequests.Constants.ABOUT_PROGRAM) {
                image = new Image(getProjectsFiles("MainWindow/images/about.png").openStream());
            }else if (inputHashCode == SearchRequests.Constants.CHANGE_AVATAR) {
                image = new Image(getProjectsFiles("MainWindow/images/avatarPhoto.png").openStream());
            }
        }catch(Exception ex){
            showExceptions.showException(ex);
        }

        return image;
    }

    @NotNull
    private HBox createSearchTile(String title, Image icon){
        HBox searchTileHBox = new HBox();

        try {
            searchTileHBox.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13");
            searchTileHBox.setAlignment(Pos.CENTER_LEFT);
            searchTileHBox.setPrefWidth(262d);
            searchTileHBox.setPrefHeight(64d);
            searchTileHBox.setSpacing(20d);

            ImageView searchIcon = new ImageView();
            searchIcon.setImage(icon);
            searchIcon.setFitWidth(54d);
            searchIcon.setFitHeight(49d);

            Label searchTitle = new Label(title);
            searchTitle.setWrapText(true);
            searchTitle.setTextFill(Color.WHITE);

            if(database.getElementFromColumn(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"))
                searchTileHBox.getChildren().add(searchIcon);

            searchTileHBox.getChildren().add(searchTitle);

            searchTileHBox.getStyleClass().add("userAvatarTile");
            setActionOnClick(searchTileHBox, title);
        }catch (Exception e){
            showExceptions.showException(e);
        }

        return searchTileHBox;
    }

    private void setActionOnClick(@NotNull HBox searchTileHBox, @NotNull String title) {
        int hashCode = title.hashCode();

        searchTileHBox.setOnMouseClicked(event ->{
            if(event.getButton()==MouseButton.PRIMARY){
                if(hashCode==SearchRequests.Constants.APPEARANCE){
                    openSettings();
                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.CHANGE_PASSWORD){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                    showControlsToChangeUserData(2);
                }else if(hashCode==SearchRequests.Constants.CHANGE_CITY){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                    showControlsToChangeUserData(1);
                }else if(hashCode==SearchRequests.Constants.COLORS){
                    openSettings();
                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.PROFILE_SETTINGS){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.CHANGE_LOGIN){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                    showControlsToChangeUserData(3);
                }else if(hashCode==SearchRequests.Constants.CLEAR_LIST_OF_RECENT_CHATS){
                    chatListVboxInScrollPane.getChildren().removeAll(chatListVboxInScrollPane.getChildren());
                    recentChatsCounter = 0;
                    recentChatsLabel.setText("Недавние чаты (0)");
                }else if(hashCode==SearchRequests.Constants.CLOSE_APP){
                    askBeforeExit();
                }else if(hashCode==SearchRequests.Constants.CONNECT_TO_THE_CHAT){
                    openConnectToTheChatMenu();
                }else if(hashCode==SearchRequests.Constants.CREATE_CHAT){
                    try {
                        createChat(InetAddress.getLocalHost().getHostAddress());
                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    }
                }else if(hashCode==SearchRequests.Constants.EDIT_TOOLSBAR){
                    openSettings();
                    displaySettings(SettingsConstants.ADD_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.EXIT_FROM_ACCOUNT){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.MUSIC){
                    MPPane.setVisible(true);
                }else if(hashCode==SearchRequests.Constants.REMOVE_ACCOUNT){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.SETTINGS){
                    openSettings();
                }else if(hashCode==SearchRequests.Constants.SETTINGS_OF_OPACITY_EFFECT){
                    openSettings();
                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.SOUNDS){
                    openSettings();
                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }else if(hashCode==SearchRequests.Constants.WEATHER){
                    openWeatherWidget(widgetDidOpened);
                }else if(hashCode==SearchRequests.Constants.YOUR_MUSIC_LIST){
                    //Under Construction
                }else if(hashCode==SearchRequests.Constants.GET_HELP){
                    openHelpWindow();
                }else if(hashCode==SearchRequests.Constants.ABOUT_PROGRAM){
                    openSettings();
                    displaySettings(SettingsConstants.ABOUT_PROGRAM);
                }else if(hashCode==SearchRequests.Constants.CHANGE_AVATAR){
                    openSettings();
                    displaySettings(PROFILE_SETTINGS);

                    FileChooser fileChooserPhoto = new FileChooser();
                    fileChooserPhoto.setTitle("𝓞𝓹𝓮𝓷 𝓟𝓱𝓸𝓽𝓸 𝓕𝓲𝓵𝓮");
                    fileChooserPhoto.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Музыка", "*.png*", "*.jpg*", "*.bmp*", "*.jpeg*"),
                            new FileChooser.ExtensionFilter("Все", "*.*")
                    );

                    String lastPhotoPath = database.getElementFromColumn(DatabaseConstants.LAST_AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id));
                    if(lastPhotoPath != null){
                        if(new File(lastPhotoPath).exists()){
                            fileChooserPhoto.setInitialDirectory(new File(lastPhotoPath));
                        }
                    }

                    File selectedFile = fileChooserPhoto.showOpenDialog(getStage());
                    if(selectedFile!=null){
                        assert lastPhotoPath != null;
                        if(new File(lastPhotoPath).exists()) new File(lastPhotoPath).delete();

                        setAvatarImage(selectedFile);

                        try {
                            FileUtils.copyFileToDirectory(selectedFile, new File("users/avatars"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        database.changeValue(DatabaseConstants.AVATAR_PATH, new File("users/avatars/"+selectedFile.getName()).getAbsolutePath(), DatabaseConstants.USER_ID, String.valueOf(id));
                    }
                }else if(hashCode==MAKE_SCREENSHOT){
                    createScreenshot();
                }else if(hashCode==OPEN_SCREENSHOTS_FOLDER){
                    if(Desktop.isDesktopSupported()){
                        try {
                            Desktop.getDesktop().open(new File("userGallery"));
                        } catch (IOException e) {
                            showExceptions.showException(e);
                        }
                    }else{
                        showExceptions.showException(new Exception("Desktop is noy supported"));
                    }
                }else if(hashCode==WATH_LOGS_ABOUT_ERRORS){
                    if(Desktop.isDesktopSupported()){
                        try {
                            Desktop.getDesktop().open(new File("errorLogs"));
                        } catch (IOException e) {
                            showExceptions.showException(e);
                        }
                    }else{
                        showExceptions.showException(new Exception("Desktop is noy supported"));
                    }
                }else if(hashCode==CLEAR_LOGS_ABOUT_ERRORS){
                    if(new File("errorLogs").list().length!=0) {
                        new Thread(() -> {
                            try {
                                FileUtils.cleanDirectory(new File("errorLogs"));
                            } catch (IOException e) {
                                showExceptions.showException(e);
                            }
                        }).start();
                    }else{
                        showExceptions.showException(new Exception("The directory is empty"));
                    }
                }
            }
        });
    }

    private void setAvatarImage(File selectedFile) {
        avatarCircle.setFill(new ImagePattern(new Image(selectedFile.getAbsolutePath())));
        userAvatarCircleInSettings.setFill(new ImagePattern(new Image(selectedFile.getAbsolutePath())));
    }

    private void addRecentChat(String address, String timeOfAdd){
        try {
            createRecentChatTile(address, timeOfAdd);

            saveRecentChats(address, timeOfAdd);

            removeAllRecentChats.setDisable(false);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void createRecentChatTile(String address, String timeOfAdd) {
        try {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setStyle("-fx-background-color: #191919; -fx-background-radius: 13; -fx-border-radius: 13; -fx-border-color: white");
            hbox.getStyleClass().add("userAvatarTile");

            VBox vboxChatAddress = new VBox();
            vboxChatAddress.setAlignment(Pos.CENTER);

            VBox vboxChatTime = new VBox();
            vboxChatTime.setAlignment(Pos.BOTTOM_RIGHT);

            Label addressLabel = new Label(address);
            addressLabel.setStyle("-fx-text-fill: white");

            Label addressTime = new Label(timeOfAdd);
            addressTime.setStyle("-fx-text-fill: white; -fx-label-padding: 5; -fx-font-size: 11");

            vboxChatTime.getChildren().add(addressTime);
            vboxChatAddress.getChildren().add(addressLabel);

            hbox.setPrefHeight(78);
            hbox.getChildren().addAll(vboxChatAddress, vboxChatTime);

            chatListVboxInScrollPane.getChildren().add(hbox);

            hbox.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    openConnectToTheChatMenu();
                    chatAddressTF.setText(address);
                }
            });

            recentChatsCounter++;
            recentChatsLabel.setText("Недавние чаты (" + recentChatsCounter + ")");
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void saveRecentChats(String address, String timeOfAdd) {
        try {
            String dirName = userName;
            if(!new File( "users/recentChats/"+dirName).exists()) new File("users/recentChats/"+dirName).mkdir();

            int counter = 0;
            String[] filesList = new File("users/recentChats/"+dirName).list();

            for(int i = 0; i!= Objects.requireNonNull(filesList).length; i++){
                counter = i+1;
            }

            String name = "recentChat_"+(counter+1)+".json";

            jsonFuncs.createJSONObject();
            jsonFuncs.putSmthInJSONObject("address", address);
            jsonFuncs.putSmthInJSONObject("time", timeOfAdd);
            jsonFuncs.createJSONFile("users/recentChats/"+dirName+"/"+name);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }
    
    private void loadRecentsChats(String folderName){
        try {
            if(new File("users/recentChats/"+ folderName).exists()) {
                String[] filesList = new File("users/recentChats/" + folderName +"/").list();

                assert filesList != null;
                if (filesList.length != 0) {
                    for (int i = 0; i != filesList.length; i++) {
                        if (filesList[i].endsWith(".json")) {
                            removeAllRecentChats.setDisable(false);
                            createRecentChatTile(jsonFuncs.getDataFromJSON("users/recentChats/" + folderName + "/" + filesList[i], "address"), jsonFuncs.getDataFromJSON("users/recentChats/" + folderName + "/" + filesList[i], "time"));
                        }
                    }
                }
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void createChat(String address) {
        try {
            addRecentChat(address, time.getDate() + " / " + time.getTime());
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void closeApp(){
        try {
            System.out.println("Closing app...");

            getStage().close();
            Platform.exit();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void askBeforeExit(){
        try {
            if(enableConfirmBefore) {
                closeConfirmPane.setVisible(true);
                rejectButtonCloseConfirmPane.setOnAction(event -> closeConfirmPane.setVisible(false));

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
                    closeConfirmPane.setVisible(false);
                }).start();
            }else closeApp();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }
    private void prepareToConnect(){
        if (chatAddressTF.getText().trim().contains(":")) {
            try {
                String tfText = chatAddressTF.getText().trim();
                StringBuilder address = new StringBuilder();
                StringBuilder port = new StringBuilder();

                char[] tfTextCharsList = tfText.toCharArray();

                int i = 0, ai = 0;
                while (tfTextCharsList[i] != ':') {
                    address.append(tfTextCharsList[i]);
                    i++;
                    ai++;
                }

                ai++;
                while (true) {
                    try {
                        port.append(tfTextCharsList[ai]);
                        ai++;
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        break;
                    }
                }

                int chatPortInteger = Integer.parseInt(port.toString());

                System.out.println(address);
                System.out.println(port);

                database.changeValue(DatabaseConstants.LAST_CHAT_ADDRESS, chatAddressTF.getText().trim(), DatabaseConstants.USER_ID, String.valueOf(id));

                chatAddressTF.clear();
                connect(address, chatPortInteger);
            }catch (Exception ex){
                showErrorInConnectToTheChatMenu(ex.toString());
            }
        }else{
            setErrorViewOnTextFields.setErrorViewToAnyField(chatAddressTF, null);
            showErrorInConnectToTheChatMenu("Некорректный адрес");
        }
    }

    private void connect(StringBuilder address, int port) {
        try {
            addRecentChat(address + ":" + port, time.getDate() + " / " + time.getTime());

            Socket socket = new Socket(address.toString(), port);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void showErrorInConnectToTheChatMenu(String msg){
        try {
            new Thread(() -> {
                Platform.runLater(() -> errorLabelConnectToTheChat.setText(msg));
                Platform.runLater(() -> errorLabelConnectToTheChat.setVisible(true));
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> errorLabelConnectToTheChat.setVisible(false));
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void openSettings(){
        try {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(200), settingsImageView);
            rotateTransition.setByAngle(0);
            rotateTransition.setToAngle(180);
            rotateTransition.setAutoReverse(true);
            rotateTransition.play();

            recentChatsListVBox.setVisible(false);
            toolBarHbox.setVisible(false);
            settingsHBox.setVisible(true);

            if(!settingsWasOpenToday) displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
            settingsWasOpenToday = true;
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    File getRandomFileFromFolder(File folder){
        String[] files = folder.list();
        assert files != null;
        int fileID = (int) (Math.random() * files.length);
        return new File(files[fileID]);
    }

    private void restoreSearchSettings(){
        turnOnOrOffSearchUsingButton.setText("Выкл. поиск");
        database.changeValue(DatabaseConstants.USE_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
        searchButtonImageView.setVisible(true);
        
        showingIconsRequestsInSearchRadioButton.setSelected(true);
        database.changeValue(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
    }

    private void initMusic(){
        try {
            AtomicReference<MP3Player> player = new AtomicReference<>();

            MusicImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    if(!MPPane.isVisible()) {
                        MPPane.setVisible(true);
                        setAnimationForSettings(MPPane, 100);
                    }else{
                        new Thread(() ->{
                            playAnimToTileInDesktop(MPPane);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            MPPane.setVisible(false);
                        }).start();
                    }
                }
            });

            closeMPButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    MPPane.setVisible(false);
                }
            });

            openInFolderMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    try {
                        FileChooser fileChooserMusic = new FileChooser();
                        fileChooserMusic.setTitle("𝓞𝓹𝓮𝓷 𝓜𝓾𝓼𝓲𝓬 𝓕𝓲𝓵𝓮");
                        fileChooserMusic.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("Музыка", "*.mp3*"),
                                new FileChooser.ExtensionFilter("Все", "*.*")
                        );

                        String lastMusicPath = database.getElementFromColumn(DatabaseConstants.LAST_MUSIC_PATH, DatabaseConstants.USER_ID, String.valueOf(id));
                        if(lastMusicPath != null){
                            if(new File(lastMusicPath).exists()){
                                fileChooserMusic.setInitialDirectory(new File(lastMusicPath));
                            }
                        }

                        File selectedFile = fileChooserMusic.showOpenDialog(getStage());

                        if (selectedFile != null) {
                            try {
                                database.changeValue(DatabaseConstants.LAST_MUSIC_PATH, selectedFile.getParent(), DatabaseConstants.USER_ID, String.valueOf(id));
                                if(player.get() != null) player.get().stop();
                                player.set(createPlayer(selectedFile.getAbsolutePath()));
                                new Thread(() -> {
                                    try {
                                        player.get().play();
                                    } catch (Exception e) {
                                        showMPError(e);
                                    }
                                }).start();

                                playingMusicLabel.setText("Играет: " + selectedFile.getName());
                            } catch (Exception e) {
                                showExceptions.showException(e);
                            }
                        }
                    } catch (Exception e) {
                        showExceptions.showException(e);
                    }
                }
            });

            pauseMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    try {
                        playClickSound();

                        player.get().pause();
                    } catch (Exception e) {
                        showMPError(e);
                    }
                }
            });

            playMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    new Thread(() -> {
                        try {
                            player.get().play();
                        } catch (Exception e) {
                            showMPError(e);
                        }
                    }).start();
                }
            });

            openMyFolderWithMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    if (userTracksFolder.exists()) {
                        try {
                            if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(userTracksFolder);
                            else showExceptions.showException(new Exception("Desktop is not supported"));
                        } catch (IOException e) {
                            showExceptions.showException(e);
                        }
                    } else {
                        userTracksFolder.mkdir();
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().open(userTracksFolder);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else showExceptions.showException(new Exception("Desktop is not supported"));
                    }
                }
            });

            addNewMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    try {
                        FileChooser fileChooserMusic = new FileChooser();
                        fileChooserMusic.setTitle("𝓞𝓹𝓮𝓷 𝓜𝓾𝓼𝓲𝓬 𝓕𝓲𝓵𝓮");
                        fileChooserMusic.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("Все", "*.*"),
                                new FileChooser.ExtensionFilter("Музыка", "*.mp3*")
                        );

                        String lastPath = database.getElementFromColumn(DatabaseConstants.LAST_MUSIC_PATH_TO_ADD_NEW_MUSIC, DatabaseConstants.USER_ID, String.valueOf(id));
                        if(lastPath != null){
                            if(new File(lastPath).exists()){
                                fileChooserMusic.setInitialDirectory(new File(lastPath));
                            }
                        }

                        File selectedFile = fileChooserMusic.showOpenDialog(getStage());

                        if (selectedFile != null) {
                            database.changeValue(DatabaseConstants.LAST_MUSIC_PATH_TO_ADD_NEW_MUSIC, selectedFile.getParent(), DatabaseConstants.USER_ID, String.valueOf(id));

                            if (selectedFile.toString().endsWith(".mp3")) {
                                if (userTracksFolder.exists())
                                    FileUtils.copyFileToDirectory(selectedFile, userTracksFolder);
                                else {
                                    userTracksFolder.mkdir();
                                    FileUtils.copyFileToDirectory(selectedFile, userTracksFolder);
                                }
                            } else showExceptions.showException(new Exception("File have incorrect format"));
                        }
                    } catch (Exception e) {
                        showExceptions.showException(e);
                    }
                }
            });

            playRandomMusicButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    if (userTracksFolder.exists()) {
                        if (Objects.requireNonNull(userTracksFolder.list()).length != 0) {
                            try {
                                if (player.get() != null)
                                    if (!player.get().isStopped() | !player.get().isPaused()) player.get().stop();

                                String[] files = userTracksFolder.list();
                                assert files != null;
                                int id = (int) (Math.random() * files.length);
                                player.set(new MP3Player(new File(userTracksFolder + "/" + files[id])));
                                playingMusicLabel.setText("Играет: " + files[id]);
                                player.get().play();
                            }catch (Exception e){
                                showMPError(e);
                            }
                        } else showMPError(new Exception("Directory is clean"));
                    } else {
                        userTracksFolder.mkdir();
                        showMPError(new Exception("Directory has created"));
                    }
                }
            });
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void showMPError(Exception e) {
        new Thread(() ->{
            Platform.runLater(() -> errorLabelMP.setText(e.getLocalizedMessage()));
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            Platform.runLater(() -> errorLabelMP.setText(""));
        }).start();
    }

    private MP3Player createPlayer(String source) throws Exception {
        return new MP3Player(new File(source));
    }

    private void showControlsToChangeUserData(int whatToChange){
        changeUserDataPane.setPrefHeight(337);
        componenetsToChangeSomethingInUserSettings.setVisible(true);

        newValueTFInUserSettings.clear();
        currentValueTFInUserSettings.clear();

        buttonsInUserSettingsVBox.setDisable(true);

        if(whatToChange == 1){
            currentValueTFInUserSettings.setPromptText("Новый город");
            newValueTFInUserSettings.setVisible(false);

            continueButtonInUserSettings.setOnAction(action ->{
                if(!currentValueTFInUserSettings.getText().trim().isEmpty() & !(currentValueTFInUserSettings.getText().trim().length() < 3)){
                    changeCity(currentValueTFInUserSettings.getText().trim());
                    hideControlsToChangeUserData();
                }else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
            });
        }else if(whatToChange == 2){
            currentValueTFInUserSettings.setPromptText("Текущий пароль");
            newValueTFInUserSettings.setPromptText("Новый пароль");

            continueButtonInUserSettings.setOnAction(action ->{
                if(!currentValueTFInUserSettings.getText().trim().isEmpty()){
                    if(!newValueTFInUserSettings.getText().trim().isEmpty() & !(newValueTFInUserSettings.getText().trim().length() < 7)) {
                        if(currentValueTFInUserSettings.getText().trim().equals(password)) {
                            changePassword(newValueTFInUserSettings.getText().trim());
                            hideControlsToChangeUserData();
                        }setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                    }else setErrorViewOnTextFields.setErrorViewToAnyField(newValueTFInUserSettings, null);
                }else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
            });

        }else if(whatToChange == 3){
            currentValueTFInUserSettings.setPromptText("Текущий логин");
            newValueTFInUserSettings.setPromptText("Новый логин");

            continueButtonInUserSettings.setOnAction(action ->{
                if(!currentValueTFInUserSettings.getText().trim().isEmpty()){
                    if(!newValueTFInUserSettings.getText().trim().isEmpty() & !(currentValueTFInUserSettings.getText().trim().length() < 4)) {
                        if(currentValueTFInUserSettings.getText().trim().equals(userName)) {
                            changeLogin(newValueTFInUserSettings.getText().trim());
                            hideControlsToChangeUserData();
                        }else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                    }else setErrorViewOnTextFields.setErrorViewToAnyField(newValueTFInUserSettings, null);
                }else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
            });
        }

    }

    private void changeID(String login, String password){
        int newID = login.hashCode() * password.hashCode();
        System.out.println(newID);

        database.changeValue(DatabaseConstants.USER_ID, String.valueOf(newID), DatabaseConstants.USER_ID, String.valueOf(id));
        id = newID;
    }

    private void changeLogin(String trim) {
        System.out.println(trim);
        userName = trim;
        database.changeValue(DatabaseConstants.USER_NAME, trim, DatabaseConstants.USER_ID, String.valueOf(id));
        changeID(userName, password);
        usernameInSettingsuserBlock.setText(trim);
        userNameLabel.setText(userName);
        welcomeLabel.setText("Добро пожаловать, "+trim);
    }

    private void changePassword(String trim) {
        password = trim;
        database.changeValue(DatabaseConstants.USER_PASSWORD, trim, DatabaseConstants.USER_ID, String.valueOf(id));
        changeID(userName, password);
    }

    private void changeCity(String trim) {
        try {
            city = trim;
            database.changeValue(DatabaseConstants.USER_CITY, trim, DatabaseConstants.USER_ID, String.valueOf(id));
            if (database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) {
                updateWeather(city);
            }
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void hideControlsToChangeUserData() {
        changeUserDataPane.setPrefHeight(189);
        componenetsToChangeSomethingInUserSettings.setVisible(false);
        newValueTFInUserSettings.setVisible(true);

        newValueTFInUserSettings.clear();
        currentValueTFInUserSettings.clear();

        buttonsInUserSettingsVBox.setDisable(false);
    }

    private void openHelpWindow(){
        try {
            if(!helpWindowIsOpen) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Help/helpPage.fxml"));
                fxmlLoader.load();

                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                HelpController helpController = fxmlLoader.getController();
                helpWindowIsOpen = true;

                stage.setOnCloseRequest(event -> helpWindowIsOpen=false);
                stage.showAndWait();
            }
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void initSplashes(){
        setSplash();

        splashLabel.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY) setSplash();
        });

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1.8), splashLabel);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Animation.INDEFINITE);
        scaleTransition.play();

        splashesWereInitialized = true;
    }

    private List<Color> getAllPossibleColors(){
        List<Color> colors = new ArrayList<>();
        int idInArray = 0;
        for(float red = 0; red != 255; red++) {
            for (float green = 0; green != 255; green++) {
                for (float blue = 0; blue != 255; blue++) {
                    Color color = Color.rgb((int) red, (int) green, (int) blue);
                    colors.add(color);
                }
            }
        }
        return colors;
    }

    private void setSplash() {
        try {
            String defaultSplashColor = "#f0f800";
            String[] splashesList = {"Welcome to Messenger v.5!", "Good to see you, " + userName + "!", "What's up?", "Every story has its own ending", "What, if you lost...", " ...friends?",
                    "Try to create chat!", "You must to thinking, before doing something", "What's the problem?", "By Dark Studio", "Uses Java", "...and JavaFX", "It's got better?", "I don't know...",
                    "What did i do?", "Version " + propertiesClass.getProperty("app_version"), "Hello, all countries!", "What is happiness?", "I know 3 wonderful girls", "Herobrine is gone", "Sorry!",
                    "Life is complicated", "I did two mistakes while writing previous phrase", "Available for Windows", "No mobile version...(", "showExceptions.showExceptions(e);", "Everything is fine!",
                    "Welcome!", "A lot of good things is happening..", "If you make mistake - you must to resolve it", "Hello!", "Unity#uck", "Rainbow Splash"};

            int splashId = (int) (Math.random() * splashesList.length);

            if(splashesList[splashId].equals("Rainbow Splash")){
                changeColors=true;
                List<Color> everyColors = getAllPossibleColors();

                new Thread(() -> {
                    while(changeColors){
                        int color = (int) (Math.random() * everyColors.size());

                        splashLabel.setTextFill(everyColors.get(color));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }else {
                splashLabel.setTextFill(Color.web(defaultSplashColor));
                changeColors = false;
            }

            splashLabel.setText(splashesList[splashId]);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void openStartWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartWindow/logInUI.fxml"));
            fxmlLoader.load();

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/icon.png")).openStream()));
            stage.show();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void writeToDatabaseWhatIconsToShowInToolBar(CheckBox[] checkBoxes){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i!=checkBoxes.length-1; i++) {
            if(checkBoxes[i].isSelected()) {
                if (checkBoxes[i].getText().hashCode() == turnOffButton) {
                    stringBuilder.append(TURN_OFF_BUTTON_CHECKBOX_ID);
                }
            }

            if(checkBoxes[i].isSelected()) {
                if (checkBoxes[i].getText().hashCode() == MPButton) {
                    stringBuilder.append(MP_BUTTON_CHECKBOX_ID);
                }
            }

            if(checkBoxes[i].isSelected()) {
                if (checkBoxes[i].getText().hashCode() == SettingsButton) {
                    stringBuilder.append(SETTINGS_BUTTON_CHECKBOX_ID);
                }
            }

            if(checkBoxes[i].isSelected()) {
                if (checkBoxes[i].getText().hashCode() == weatherWidget) {
                    stringBuilder.append(WEATHER_WIDGET_CHECKBOX_ID);
                }
            }

            if(checkBoxes[i].isSelected()) {
                if (checkBoxes[i].getText().hashCode() == userWidget) {
                    stringBuilder.append(USER_WIDGET_CHECKBOX_ID);
                }
            }
        }

        database.changeValue(DatabaseConstants.WHAT_ITEMS_DISPLAY_ON_TASKBAR, stringBuilder.toString(), DatabaseConstants.USER_ID, String.valueOf(id));
    }

    String requestsImageForWeather(String state){
        String image = null;

        if(state.hashCode()==cloudy){
            image = cloudsImage;
        }else if(state.hashCode()==rain){
            image = rainImage;
        }else if(state.hashCode()==snow){
            image = snowImage;
        }else if(state.hashCode()==fog){
            image = fogImage;
        }else if(state.hashCode()==clearSky){
            image = clearSkyImage;
        }

        return image;
    }

    static class SettingsConstants{
        public static final int APPEARANCE_SETTINGS = 1;
        public static final int PROFILE_SETTINGS = 2;
        public static final int SEARCH_SETTINGS = 3;
        public static final int ABOUT_PROGRAM = 4;
        public static final int ADD_SETTINGS = 5;
        public static final int BG_MODE_IMAGE = 1;
        public static final int BG_MODE_COLOR = 0;

        public static final int TURN_OFF_BUTTON_CHECKBOX_ID = 1;
        public static final int MP_BUTTON_CHECKBOX_ID = 2;
        public static final int SETTINGS_BUTTON_CHECKBOX_ID = 3;
        public static final int WEATHER_WIDGET_CHECKBOX_ID = 4;
        public static final int USER_WIDGET_CHECKBOX_ID = 5;

        public static final int turnOffButton = "Кнопка выключения".hashCode();
        public static final int MPButton = "Кнопка музыкального плеера".hashCode();
        public static final int SettingsButton = "Кнопка настроек".hashCode();
        public static final int weatherWidget = "Виджет погоды".hashCode();
        public static final int userWidget = "Виджет пользователя".hashCode();

    }

    static class WeatherConstants{
        public static final int clearSky = "Clear".hashCode();
        public static final int rain = "Rain".hashCode();
        public static final int snow = "Snow".hashCode();
        public static final int cloudy = "Clouds".hashCode();
        public static final int fog = "Fog".hashCode();

        public static final String clearSkyImage = "MainWindow/images/clearSky.png";
        public static final String rainImage = "MainWindow/images/rain.png";
        public static final String snowImage = "MainWindow/images/snow.png";
        public static final String cloudsImage = "MainWindow/images/clouds.png";
        public static final String fogImage = "MainWindow/images/fog.png";
    }

}
