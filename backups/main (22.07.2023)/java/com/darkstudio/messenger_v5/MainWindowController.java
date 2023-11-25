package com.darkstudio.messenger_v5;

import com.darkstudio.messenger_v5.ServerAndClient.ServerTest;
import com.sun.management.OperatingSystemMXBean;
import jaco.mp3.player.MP3Player;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.darkstudio.messenger_v5.MainWindowController.SettingsConstants.PROFILE_SETTINGS;
import static com.darkstudio.messenger_v5.MainWindowController.SettingsConstants.*;
import static com.darkstudio.messenger_v5.MainWindowController.WeatherConstants.*;
import static com.darkstudio.messenger_v5.SearchRequests.Constants.*;
import static java.lang.Thread.sleep;

public class MainWindowController {
    @FXML
    private ToggleGroup additionalKeysToggleGroup;
    @FXML
    private ScrollPane contentOnToolBarScrollPane, addBlockInSettingsPane, hotKeysScrollPane, searchScrollPane, appearanceScrollPane;
    @FXML
    private CheckBox showBarPolicysCheckBox, useOneColorCheckBox;
    @FXML
    private ImageView standartBG2Button, standartBG1Button, makeScreenshotButton, weatherImageInWeatherWidget, weatherImage, rejectToRemoveAccountIVButton, confirmToRemoveAccountIVButton, cancelToRemovePasswordIV, applyToRemovePasswordIV, applyButtonIVYoResetAllSettings, cancelButtonIVInResetAllSettings, searchButtonImageView, rejectToRemoveButtonRecentChatsIV, applyToRemoveRecentChatsButtonIV, removeAllRecentChats, soundsInSettingsIcon, playRandomMusicButtonMP, playMusicButtonMP, pauseMusicButtonMP, openMyFolderWithMusicButtonMP, openInFolderMusicButtonMP, closeMPButtonMP, blockIconInSettings, addNewMusicButtonMP, getbackInSettingsImageView, turnOffImageView, settingsImageView, iconImageView, hideWindowImageView, closeWindowImageView, MusicImageView;
    @FXML
    private Circle userAvatarCircleInSettings, avatarCircle;
    @FXML
    private VBox weatherBlock, soundsBlock, colorsBlock, opacityBlock, whatNotificationDoYouWantToApplyVBox, disableContentOnToolBarVBox, weatherWidgetVBox, componenetsToChangeSomethingInUserSettings, buttonsInUserSettingsVBox, searchEllementVBox, SearchVBox, chatListVboxInScrollPane, recentChatsListVBox, mainVBox;
    @FXML
    private Label splashAnimationValueLabel, autoInstalledPasswordLabel, copyChatAddressLabel, pressedKeyLabel, hotKeyForCloseApp, hotKeyForCallSearchMenu, hotKeyForCallSettingsMenu, hotKeyForCallHelpMenu, hotKeyForMakeScreenshot, addressLabelInCreateChatPane, portLabelInCreateChatPane, createAnotherPortLabel, restoreBgImageLabel, splashLabel, opacityValue, ErrorLabelInWeatherWidget, countryNamelabelInWeatherWidget, timeZoneLabelInWeatherWidget, placeNameLabelInWeatherWidget, weatherDescriptionWeatherInWeatherWidget, WindSpeedLabelInWeatherWidget, VisibilityLabelInWeatherWidget, TemperatureLabelInWeatherWidget, TemperatureFeelsLikeLabelInWeatherWidget, PressureLabelInWeatherWidget, MinTemperatureLabelInWeatherWidget, MaxTemperatureLabelInWeatherWidget, HumidityLabelInWeatherWidget, cancelSettingPasswordByTheHandLabel, weatherDescriptionLabel, weatherTempLabel, copyAboutProgramLabel, copyAboutSystemLabel, appDeveloperLabel, appBuildLabel, appVersionLabel, appTitleLabel, thisSystemIsMeetingMinSysReqLabel, sizeScreenLabel, RAMLabel, systemNameLabel, dateOfRegistrationLabelInSettings, usernameInSettingsuserBlock, soundsVolumeValueLabel, welcomeLabel, playingMusicLabel, errorLabelMP, choosedFilePathLabelInAppearence, brightnessValue, blockTitleInSettings, SaturationValue, HueValue, ContrastValue, resetAllLabelInSettings, closeConnectToTheChatPaneLabel, errorLabelConnectToTheChat, titleLabel, recentChatsLabel, userNameLabel;
    @FXML
    private Pane userSettingsBlock1, setPasswordInAutoModePane, anotherSettingsInAnottherSettingsBlockPane, hotKeysSettingsBlockInSettings, toolBarSettingsPane, changeKeyPane, createChatPane, confirmToRemovePasswordPane, loadImageForBGPane, settingsAllContentPane, createYourChatPane, splashSettingsBlockPane, weatherPane, copySomethingPaneInAboutProgramBlock, userWidgetPane, setPasswordByTheHandPane, buttonToSetPasswordPane, aboutProgramPaneInSettings, searchPaneInSettings, changeUserDataPane, userSettingsBlockPane, appearenceInSettingsPane, connectToTheChatTilePane, MPPane, closeConfirmPane, connectToTheChatPane;
    @FXML
    private TextField enterYourSplashTF, newCityNameTextFieldInWeatherWidget, textFieldToSetPasswordByTheHand, newValueTFInUserSettings, currentValueTFInUserSettings, searchField, cityWhereNeedToShowWeatherTextField, chatAddressTF;
    @FXML
    private Button cancelButtonInSetPasswordInAutoMode, applyButtonToSendNotifications, okButtonInSetPasswordInAutoMode, cancelToCreateChatButton, getBackInSettingHotKeys, turnOnOrOffAnimationsButton, hideToolsBarButton, continueInCreateChatPaneButton, turnOnOrOffSearchUsingButton, requestConfirmBeforeCloseButton, applyButtonToShowElementsOnToolsBar, displayYourSplashButton, disableSplashesButton, continueButtonToSetYourSplash, continueToApplyNewCityButtonInWeatherWidget, continueButtonInSettingsPasswordByTheHand, cancelToSetPasswordButton, setPasswordByTheHandButton, setPasswordAutomaticButton, setPasswordToAccountButton, clearSearchJournalButton, cancelButtonInUserSettings, continueButtonInUserSettings, changeLoginButton, changePasswordButton, changeCityButton, exitFromAccountInSettingsButton, removeAccountInSettingsButton, updateWeatherDataButtonInSettings, turnOnorOffWeatherButton, turnOnorOffSoundsButton, theBestViewButton, simpleViewButton, resetAppearenceSettingsButton, loadInmageButtonInAppearence, continueButtonCloseConfirmPane, rejectButtonCloseConfirmPane, createChatButton, connectToTheChatButton, continueButtonConnectToTheChat;
    @FXML
    private HBox contentHBox, buttonRemoveAccountAndExutHBox, buttonsToConfirmRemoveAccountHbox, setYourSplashHBoxControls, textHadCopiedLabelHbox, accountBlockInSettings, aboutPropgramBlockInSettings, controlsBarHbox, settingsHBox, toolBarHbox, appearenceBlockInSettings, searchBlockInSettings, additionalBlockInSettings;
    @FXML
    private Slider splashAnimationSpeedSlider, soundVolumeSlider, ContrastSlider, HueSlider, SaturationSlider, opacitySlider, brightnessSlider;
    @FXML
    private ColorPicker mainColorColorPickerInAppearence;
    @FXML
    private RadioButton showNotificationRadioButton, hideToolsBarAutoRadioButton, showingIconsRequestsInSearchRadioButton, makingSearchJournalRadioButton;
    @FXML
    private ProgressBar settingsProgressBar;
    private final AtomicBoolean widgetDidOpened = new AtomicBoolean(false);
    private double x, y;
    private double soundsVolume = 1;
    private boolean enableSendingNotifications = true, confirmBeforeCloseISOpen = false, useAnimations = true, settingsWasOpenToday = false, enableConfirmBefore = false, enablePlaySounds = true, splashesWereInitialized = false, changeColors = true, helpWindowIsOpen = false;
    public Stage stage;
    private final SetErrorViewOnTextFields setErrorViewOnTextFields = new SetErrorViewOnTextFields();
    private final ShowExceptions showExceptions = new ShowExceptions();
    private final PropertiesClass propertiesClass = new PropertiesClass();
    public long id = 0, recentChatsCounter = 0;
    public String userName = "", password, city;
    private double saturation, hue, contrast, brightness;
    private Weather weather;
    private final File userTracksFolder = new File(System.getProperty("user.home")+"/userTracks");
    private final java.util.List<Node> opacityNodes = new java.util.ArrayList<>(java.util.List.of());
    private final java.util.List<ScrollPane> scrollPanes = new java.util.ArrayList<>(java.util.List.of());
    private final Database database = new Database();
    private final Time time = new Time();
    private final JSONFuncs jsonFuncs = new JSONFuncs();
    private boolean[] openedSettingsBooleans = {false, false, false, false, false};
    private int[] openedSettingsIntegers = {0, 0, 0, 0, 0};
    private Animations animations;
    private int chatPort;
    private int aint = 0;
    private String address, passwordForAccountThatGoingToInstall;
    private String[] splashesList;
    private static final String defaultSplashColor = "#f0f800";
    private CheckBox[] enabledNotifications;
    private String enabledNotificationString;
    private float splashAnimationSpeedFloatValue = 600;

    public MainWindowController() throws Exception {
    }

    private void playClickSound() {
        if(enablePlaySounds) new PlaySounds(PlaySounds.Constants.CLICK_SOUND, (float) soundsVolume).play();
    }

    private void changeHotKey(int keyId){
        try {
            changeKeyPane.setVisible(true);
            hotKeysScrollPane.setVisible(false);
            pressedKeyLabel.setText("Нажмите что-нибудь");

            stage.getScene().setOnKeyPressed(key -> {
                String additionalKey = null;
                if(additionalKeysToggleGroup.getSelectedToggle() != null) {
                    if(!additionalKeysToggleGroup.getSelectedToggle().toString().contains("Ни с чем")) {
                        if (additionalKeysToggleGroup.getSelectedToggle().toString().contains("Ctrl")) {
                            additionalKey = "Ctrl";
                        } else if (additionalKeysToggleGroup.getSelectedToggle().toString().contains("Alt")) {
                            additionalKey = "Alt";
                        }
                    }
                }

                String pressedKey = additionalKey == null ? key.getCode().toString() : additionalKey + " + " + key.getCode();
                pressedKeyLabel.setText(pressedKey);

                if (keyId == hotKeyForExit) {
                    database.changeValue(DatabaseConstants.HOT_KEY_FOR_CLOSE_APP, pressedKey, DatabaseConstants.USER_ID, String.valueOf(id));
                } else if (keyId == hotKeyForOpenHelpMenuInt) {
                    database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_HELP_MENU, pressedKey, DatabaseConstants.USER_ID, String.valueOf(id));
                } else if (keyId == hotKeyForOpenSearch) {
                    database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_SEARCH_MENU, pressedKey, DatabaseConstants.USER_ID, String.valueOf(id));
                } else if (keyId == hotKeyForOpenSettings) {
                    database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_SETTINGS, pressedKey, DatabaseConstants.USER_ID, String.valueOf(id));
                } else if (keyId == SettingsConstants.hotKeyForMakeScreenshot) {
                    database.changeValue(DatabaseConstants.HOT_KEY_FOR_MAKE_SCR, pressedKey, DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });
        }catch(Exception e){
            showExceptions.showException(e);
        }
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
        try {
            BackgroundImage backgroundImage = new BackgroundImage(new Image(new File(source).getAbsolutePath()),
                    BackgroundRepeat.ROUND,
                    BackgroundRepeat.ROUND,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            mainVBox.setBackground(background);
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    private void showProgressBarInSettings(boolean b){
        try {
            resetAllLabelInSettings.setVisible(!b);
            if (applyButtonIVYoResetAllSettings.isVisible()) applyButtonIVYoResetAllSettings.setVisible(!b);
            if (cancelButtonIVInResetAllSettings.isVisible()) cancelButtonIVInResetAllSettings.setVisible(!b);
            settingsProgressBar.setVisible(b);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void animToolBar(boolean up){
        try {
            int fromY = up ? 100 : 0;
            int toY = up ? 0 : 100;

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), toolBarHbox);
            translateTransition.setFromY(fromY);
            translateTransition.setToY(toY);
            translateTransition.play();
        }catch (Exception e){
            showExceptions.showException(e);
        }

    }

    private void setAutoHideToolBar(){
        try {
            AtomicBoolean aboolean = new AtomicBoolean(false);
            mainVBox.setOnMouseMoved(mouseEvent -> {
                if (!settingsHBox.isVisible()) {
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
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    @FXML
    void initialize() {
        try {
            initSettings();
            setActionsOnObjects();
            addActionsOnControlsButtons();
            initSearch();
            initMusic();

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

    private void doActionOnHotKey(int i){
        if(i == 1) userWantToCloseWindow();
        else if(i == 2) openSearch();
        else if(i == 3) openHelpWindow();
        else if(i == 4) createScreenshot();
        else if(i == 5) openSettings();
    }

    private void makeHotKeyAction(String hotKey, int whatToDo, KeyEvent keyEvent){
        String anObjectExit = String.valueOf(hotKey.charAt(hotKey.length() - 1));
        if(hotKey.contains("Alt")) {
            if (keyEvent.isAltDown() & keyEvent.getCode().toString().equals(anObjectExit)) {
                doActionOnHotKey(whatToDo);
            }
        }else if(hotKey.contains("Ctrl")) {
            if (keyEvent.isControlDown() & keyEvent.getCode().toString().equals(anObjectExit)) {
                doActionOnHotKey(whatToDo);
            }
        }else{
            if (keyEvent.getCode().toString().equals(hotKey)) {
                doActionOnHotKey(whatToDo);
            }
        }
    }

    private void initHotKeys() {
        try {
            mainVBox.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.T) {
                    testMethod();
                }

                String keyForExit = database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_CLOSE_APP, DatabaseConstants.USER_ID, String.valueOf(id));
                String keyForCallSearch = database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_SEARCH_MENU, DatabaseConstants.USER_ID, String.valueOf(id));
                String keyForCallSettingsMenu = database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_SETTINGS, DatabaseConstants.USER_ID, String.valueOf(id));
                String keyForCallHelpMenu = database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_HELP_MENU, DatabaseConstants.USER_ID, String.valueOf(id));
                String keyForMakeScreenshot = database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_MAKE_SCR, DatabaseConstants.USER_ID, String.valueOf(id));

                int exit = 1, search = 2, help = 3, screenshot = 4, settings = 5;

                makeHotKeyAction(keyForExit, exit, keyEvent);
                makeHotKeyAction(keyForCallSearch, search, keyEvent);
                makeHotKeyAction(keyForCallSettingsMenu, settings, keyEvent);
                makeHotKeyAction(keyForCallHelpMenu, help, keyEvent);
                makeHotKeyAction(keyForMakeScreenshot, screenshot, keyEvent);
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    protected void userWantToCloseWindow() {
        if (database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"))
            askBeforeExit();
        else closeApp();
    }

    private void writeDebug(String text){
        System.out.println("[Debug] ("+ new Time().getTime() + "): " +text);
    }

    private void setUserSettings() {
        try {
            writeDebug("Setting up user settings...");

            writeDebug("Setting up user avatar");
            Image avatarImageDefault = new Image(new File(DEFAULT_USER_AVATAR).getAbsolutePath());
            String userAvatarString = database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id));

            assert userAvatarString != null;
            if (new File(userAvatarString).exists()) {
                setAvatarImage(new File(userAvatarString));
            } else {
                avatarCircle.setFill(new ImagePattern(avatarImageDefault));
                userAvatarCircleInSettings.setFill(new ImagePattern(avatarImageDefault));
            }

            writeDebug("Setting up user name");
            usernameInSettingsuserBlock.setText(userName);
            writeDebug("Setting up user date of registration");
            dateOfRegistrationLabelInSettings.setText(database.getElementFromColumn(DatabaseConstants.USER_DATE_OF_REG, DatabaseConstants.USER_ID, String.valueOf(id)));

            writeDebug("Applying user city");
            city = database.getElementFromColumn(DatabaseConstants.USER_CITY, DatabaseConstants.USER_ID, String.valueOf(id));

            writeDebug("Setting up background mode");
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

            writeDebug("Setting up opacity values in settings");
            opacitySlider.setValue(Double.parseDouble(database.getElementFromColumn(DatabaseConstants.OPACITY_LEVEL, DatabaseConstants.USER_ID, String.valueOf(id))));
            opacityValue.setText(getPerCents((float) opacitySlider.getValue(), (float) opacitySlider.getMax()) + "%");

            writeDebug("Setting up sounds values in settings");
            turnOnorOffSoundsButton.setText(database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Выкл. звуки" : "Вкл. звуки");
            soundsVolume = Double.parseDouble(database.getElementFromColumn(DatabaseConstants.SOUNDS_VOLUME, DatabaseConstants.USER_ID, String.valueOf(id)));
            soundVolumeSlider.setValue(soundsVolume);

            soundVolumeSlider.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));
            soundsVolumeValueLabel.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            writeDebug("Setting up weather values in settings");
            turnOnorOffWeatherButton.setText(database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Не показывать погоду" : "Показывать погоду");
            updateWeatherDataButtonInSettings.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));
            cityWhereNeedToShowWeatherTextField.setDisable(!database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            writeDebug("Setting up color settings");
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

            writeDebug("Setting up weather");
            if (database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) {
                initWeather();
                initWeatherWidget();
                updateWeatherAfterAnyTime();
            }

            writeDebug("Setting up splashes");
            if(database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                initSplashes();
            }else splashLabel.setVisible(false);
            disableSplashesButton.setText(database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Отключить сплеши": "Включить сплеши");

            boolean enableSpls = !database.getElementFromColumn(DatabaseConstants.DISPLAY_SPLASHES, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");

            displayYourSplashButton.setDisable(enableSpls);
            splashAnimationSpeedSlider.setDisable(enableSpls);

            writeDebug("Setting up background");
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

            writeDebug("Setting up search button text");
            turnOnOrOffSearchUsingButton.setText(database.getElementFromColumn(DatabaseConstants.USE_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Выкл. поиск": "Вкл. поиск");

            writeDebug("Setting up add password for account button text");
            setPasswordToAccountButton.setText(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(id)) != null ? "Удалить пароль с аккаунта": "Установить пароль на аккаунт");

            writeDebug("Setting up add password for account button action");
            if(database.getElementFromColumn(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, DatabaseConstants.USER_ID, String.valueOf(id)) != null){
                setPasswordToAccountButton.setOnAction(actionEvent -> {
                    playClickSound();
                    removePasswordFromAccount();
                });
            }

            writeDebug("Setting up search button on toolbar visible");
            searchButtonImageView.setVisible(database.getElementFromColumn(DatabaseConstants.USE_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            writeDebug("Setting up request confirm before close button text");
            requestConfirmBeforeCloseButton.setText(database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1") ? "Не спрашивать перед закрытием": "Спрашивать перед закрытием");

            writeDebug("Setting up auto hide tool bar selected");
            hideToolsBarAutoRadioButton.setSelected(database.getElementFromColumn(DatabaseConstants.AUTO_HIDE_TASKBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            writeDebug("Setting up tool bar auto hide");
            if(database.getElementFromColumn(DatabaseConstants.AUTO_HIDE_TASKBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) setAutoHideToolBar();

            writeDebug("Setting up search images show");
            showingIconsRequestsInSearchRadioButton.setSelected(database.getElementFromColumn(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1"));

            writeDebug("Setting up sound variable");
            enablePlaySounds = database.getElementFromColumn(DatabaseConstants.USE_SOUNDS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");

            writeDebug("Setting up sounds button text");
            turnOnorOffSoundsButton.setText(enablePlaySounds ? "Выкл. звуки": "Вкл. звуки");

            writeDebug("Setting up request before close variable");
            enableConfirmBefore = database.getElementFromColumn(DatabaseConstants.ASKING_BEFORE_CLOSE, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");

            writeDebug("Setting up last user chat address variable");
            String lastAddress = database.getElementFromColumn(DatabaseConstants.LAST_CHAT_ADDRESS, DatabaseConstants.USER_ID, String.valueOf(id));

            writeDebug("Setting up last user chat address text");
            if(lastAddress != null){
                chatAddressTF.setText(lastAddress);
            }

            writeDebug("Hiding all elements on tool bar");
            turnOffImageView.setVisible(false);
            settingsImageView.setVisible(false);
            MusicImageView.setVisible(false);
            userWidgetPane.setVisible(false);
            weatherPane.setVisible(false);

            writeDebug("Showing elements on tool bar");
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

            writeDebug("Setting up elements on tool bar check boxes selected");
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

            writeDebug("Settings up use animations variable");
            useAnimations = database.getElementFromColumn(DatabaseConstants.USE_ANIMATIONS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");
            animations = new Animations(useAnimations);

            writeDebug("Settings up background");
            if(database.getElementFromColumn(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id)) != null){
                if(new File(database.getElementFromColumn(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id))).exists()){
                    setBg(database.getElementFromColumn(DatabaseConstants.BACKGROUND_IMAGE, DatabaseConstants.USER_ID, String.valueOf(id)));
                }else{
                    setBg("background/bg.png");
                }
            }else{
                setBg("background/bg.png");
            }

            writeDebug("Settings up scroll bar policy");
            if(database.getElementFromColumn(DatabaseConstants.SHOW_BAR_POLICY, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                showBarPolicysCheckBox.setSelected(true);

                for(int i = 0; i!=scrollPanes.size(); i++){
                    scrollPanes.get(i).setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                }
            }else{
                showBarPolicysCheckBox.setSelected(false);

                for(int i = 0; i!=scrollPanes.size(); i++){
                    scrollPanes.get(i).setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                }
            }

            if(database.getElementFromColumn(DatabaseConstants.HIDE_TOOLBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                animToolBar(true);
                hideToolsBarButton.setText("Скрывать панель инструментов");

                disableContentOnToolBarVBox.setDisable(false);
                hideToolsBarAutoRadioButton.setDisable(false);
            }else{
                animToolBar(false);
                hideToolsBarButton.setText("Показывать панель инструментов");

                disableContentOnToolBarVBox.setDisable(true);
                hideToolsBarAutoRadioButton.setDisable(true);
            }

            writeDebug("Loading info about hot keys");
            loadInfoAboutHotKeys();

            writeDebug("Initializing hot keys");
            initHotKeys();

            initToolTips();

            boolean enableNotifications = database.getElementFromColumn(DatabaseConstants.SHOW_NOTIFICATIONS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1");
            enableSendingNotifications = enableNotifications;
            showNotificationRadioButton.setSelected(enableNotifications);
            enabledNotifications = new CheckBox[3];

            if(enableNotifications){
                for(int i = 0; i!=whatNotificationDoYouWantToApplyVBox.getChildren().size(); i++){
                    if(!whatNotificationDoYouWantToApplyVBox.getChildren().get(i).toString().contains("Применить")){
                        enabledNotifications[i] = (CheckBox) whatNotificationDoYouWantToApplyVBox.getChildren().get(i);
                    }
                }

                String allowedNotificationsSources = database.getElementFromColumn(DatabaseConstants.ALLOWED_NOTIFICATIONS, DatabaseConstants.USER_ID, String.valueOf(id));
                if(allowedNotificationsSources.contains("1")) enabledNotifications[0].setSelected(true);
                if(allowedNotificationsSources.contains("2")) enabledNotifications[1].setSelected(true);
                if(allowedNotificationsSources.contains("3")) enabledNotifications[2].setSelected(true);
            }else{
                whatNotificationDoYouWantToApplyVBox.setDisable(true);
            }
            
            enabledNotificationString = database.getElementFromColumn(DatabaseConstants.ALLOWED_NOTIFICATIONS, DatabaseConstants.USER_ID, String.valueOf(id));

            splashAnimationSpeedFloatValue = Float.parseFloat(database.getElementFromColumn(DatabaseConstants.SPLASHES_ANIM_SPEED, DatabaseConstants.USER_ID, String.valueOf(id)));
            splashAnimationSpeedSlider.setValue(splashAnimationSpeedFloatValue);

            writeDebug("Done!");
            writeDebug("Loading window...");
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private float convertKelvinsToCelsius(Float temp){
        return Math.round((temp - 273.15));
    }

    private float convertCelsiusToFahrenheit(Float temp){return Math.round((temp * 1.8) + 32);}

    private void showErrorInWeatherWidget(Exception exception){
        try {
            new Thread(() -> {
                Platform.runLater(() -> ErrorLabelInWeatherWidget.setText(exception.getMessage()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> ErrorLabelInWeatherWidget.setText(""));
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
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

            weatherImageInWeatherWidget.setImage(new Image(Objects.requireNonNull(getClass().getResource(requestsImageForWeather(weather.getMain()))).openStream()));
        }catch (Exception ex){
            showErrorInWeatherWidget(ex);
        }
    }

    private void initWeatherWidget(){
        try {
            displayWeatherInWeatherWidget(weather);

            newCityNameTextFieldInWeatherWidget.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if(!newCityNameTextFieldInWeatherWidget.getText().trim().isEmpty()) showWeatherInAnotherPlaceInWeatherWidget(); else{
                        new SetErrorViewOnTextFields().setErrorViewToAnyField(newCityNameTextFieldInWeatherWidget, null);
                    }
                }
            });
            continueToApplyNewCityButtonInWeatherWidget.setOnAction(actionEvent -> {
                if(!newCityNameTextFieldInWeatherWidget.getText().trim().isEmpty()) showWeatherInAnotherPlaceInWeatherWidget(); else{
                    new SetErrorViewOnTextFields().setErrorViewToAnyField(newCityNameTextFieldInWeatherWidget, null);
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
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

    private void initWeather() {
        try {
            weather = new Weather(city);
            weather.init();

            displayWeatherInfoOnMainScreen(weather.getTemperature(), weather.getDescription(), weather.getMain());
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private long getMemorySize(){
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();
    }

    public void copyText(String text){
        try {
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void initSettings() {
        try {
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13; -fx-border-radius: 13; -fx-border-color: white");
            MenuItem menuItemChangePhoto = new MenuItem("Сменить фото");
            MenuItem menuItemRemovePhoto = new MenuItem("Удалить фото");
            setStyleForMenuItem(new MenuItem[]{menuItemChangePhoto,menuItemRemovePhoto});
            contextMenu.getItems().addAll(menuItemChangePhoto,menuItemRemovePhoto);

            menuItemChangePhoto.setOnAction(action -> changeAvatar(false));
            menuItemRemovePhoto.setOnAction(action -> {
                if (new File(database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id))).exists() & !database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id)).equals(DEFAULT_USER_AVATAR)) new File(database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id))).delete();
                setAvatarImage(new File(DEFAULT_USER_AVATAR));

                database.changeValue(DatabaseConstants.AVATAR_PATH, DEFAULT_USER_AVATAR ,DatabaseConstants.USER_ID, String.valueOf(id));
            });

            userAvatarCircleInSettings.setOnMouseClicked(mouseEvent ->{
                if(mouseEvent.getButton() == MouseButton.SECONDARY){
                    contextMenu.show(userAvatarCircleInSettings, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            });

            getBackInSettingHotKeys.setOnAction(actionEvent -> {
                playClickSound();

                hotKeysScrollPane.setVisible(true);
                changeKeyPane.setVisible(false);
                loadInfoAboutHotKeys();
            });

            hotKeyForCloseApp.setOnMouseClicked(mouseEvent -> changeHotKey(hotKeyForExit));

            hotKeyForCallSettingsMenu.setOnMouseClicked(mouseEvent -> changeHotKey(hotKeyForOpenSettings));

            hotKeyForCallSearchMenu.setOnMouseClicked(mouseEvent -> changeHotKey(hotKeyForOpenSearch));

            hotKeyForMakeScreenshot.setOnMouseClicked(mouseEvent -> changeHotKey(SettingsConstants.hotKeyForMakeScreenshot));

            hotKeyForCallHelpMenu.setOnMouseClicked(mouseEvent -> changeHotKey(hotKeyForOpenHelpMenuInt));

            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            CheckMinimumSysReq checkMinimumSysReq = new CheckMinimumSysReq();
            checkMinimumSysReq.init();

            systemNameLabel.setText("Система: " + System.getProperty("os.name"));
            RAMLabel.setText("RAM: " + Math.round((getMemorySize() / 1024 / 1024) * 0.000977) + " Gb");
            sizeScreenLabel.setText("Размер экрана: " + dimension.width + "x" + dimension.height);

            if (!checkMinimumSysReq.systemIsMeetMinSysReq()) {
                thisSystemIsMeetingMinSysReqLabel.setTextFill(Color.RED);
                thisSystemIsMeetingMinSysReqLabel.setText("Эта система не отвечает минимальным требованиям");

                if (checkMinimumSysReq.getCode() == 1) {
                    systemNameLabel.setTextFill(Color.RED);
                    RAMLabel.setTextFill(Color.RED);
                    sizeScreenLabel.setTextFill(Color.RED);
                } else if (checkMinimumSysReq.getCode() == 2) {
                    RAMLabel.setTextFill(Color.RED);
                } else if (checkMinimumSysReq.getCode() == 3) {
                    systemNameLabel.setTextFill(Color.RED);
                } else if (checkMinimumSysReq.getCode() == 4) {
                    sizeScreenLabel.setTextFill(Color.RED);
                }
            }

            appTitleLabel.setText("Название: " + propertiesClass.getProperty("app_title"));
            appVersionLabel.setText("Версия: " + propertiesClass.getProperty("app_version"));
            appBuildLabel.setText("Сборка: " + propertiesClass.getProperty("app_build"));
            appDeveloperLabel.setText("Разработчик: " + propertiesClass.getProperty("app_developer"));

            brightnessSlider.setMax(1);
            ContrastSlider.setMax(1);
            HueSlider.setMax(1);
            SaturationSlider.setMax(1);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void loadInfoAboutHotKeys() {
        try {
            hotKeyForCloseApp.setText(database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_CLOSE_APP, DatabaseConstants.USER_ID, String.valueOf(id)));
            hotKeyForMakeScreenshot.setText(database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_MAKE_SCR, DatabaseConstants.USER_ID, String.valueOf(id)));
            hotKeyForCallSettingsMenu.setText(database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_SETTINGS, DatabaseConstants.USER_ID, String.valueOf(id)));
            hotKeyForCallSearchMenu.setText(database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_SEARCH_MENU, DatabaseConstants.USER_ID, String.valueOf(id)));
            hotKeyForCallHelpMenu.setText(database.getElementFromColumn(DatabaseConstants.HOT_KEY_FOR_OPEN_HELP_MENU, DatabaseConstants.USER_ID, String.valueOf(id)));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    void setStyleForMenuItem(@NotNull MenuItem[] menuItems){
        try {
            for (int i = 0; i != menuItems.length; i++) {
                menuItems[i].setStyle("-fx-text-fill: white; -fx-font-family: 'Arial Black'");
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setOpacity(float value){
        try {
            for (int i = 0; i != opacityNodes.size(); i++) {
                opacityNodes.get(i).setOpacity(value);
            }
        }catch (Exception e){
            showExceptions.showException(e);
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
        try {
            new Thread(() -> {
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
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    float getPerCents(float number, float max){
        return Math.round((number * 100) / max);
    }

    private void setBgColor(Color color){
        try {
            BackgroundFill backgroundFill = new BackgroundFill(color, new CornerRadii(1), null);
            mainVBox.setBackground(new Background(backgroundFill));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }
    
    private void removePasswordFromAccount(){
        try {
            confirmToRemovePasswordPane.setVisible(true);
            setPasswordToAccountButton.setVisible(false);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setSettingSelectedBlock(int i){
        try{
            String defaultBorder = "-fx-border-color: transparent";
            String selectedBorder = "-fx-border-color: white; -fx-border-insets: 10; -fx-border-radius: 13";

            appearenceBlockInSettings.setStyle(defaultBorder);
            accountBlockInSettings.setStyle(defaultBorder);
            searchBlockInSettings.setStyle(defaultBorder);
            additionalBlockInSettings.setStyle(defaultBorder);
            aboutPropgramBlockInSettings.setStyle(defaultBorder);

            if(i == SettingsConstants.APPEARANCE_SETTINGS){
                appearenceBlockInSettings.setStyle(selectedBorder);
            }else if(i == SettingsConstants.PROFILE_SETTINGS){
                accountBlockInSettings.setStyle(selectedBorder);
            }else if(i == SettingsConstants.SEARCH_SETTINGS){
                searchBlockInSettings.setStyle(selectedBorder);
            }else if(i == SettingsConstants.ADD_SETTINGS){
                additionalBlockInSettings.setStyle(selectedBorder);
            }else if(i == SettingsConstants.ABOUT_PROGRAM){
                aboutPropgramBlockInSettings.setStyle(selectedBorder);
            }
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    private void openFile(File file){
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                showExceptions.showException(e);
            }
        } else showExceptions.showException(new Exception("Desktop is not supported"));
    }

    private void createScreenshot(){
        try {
            String fileName = time.getLocalTime().getHour()+"_"+time.getLocalTime().getMinute()+"_"+time.getLocalTime().getSecond() + "_" + time.getDate() + ".png";

            BufferedImage bufferedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(bufferedImage, "png", new File("userGallery/" + fileName));

            if(enabledNotificationString.contains("1") & enableSendingNotifications) {
                SendNotification sendNotification = new SendNotification("Снимок экрана сохранён как " + fileName + ". \n Нажмите, чтобы открыть", "Ваш снимок экрана сохранён", TrayIcon.MessageType.INFO);
                sendNotification.display();
                sendNotification.getTrayIcon().addActionListener(actionListener -> openFile(new File("userGallery/" + fileName)));
            }else{
                openFile(new File("userGallery/" + fileName));
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    @NotNull
    @Contract(" -> new")
    private ProgressBar createProgressBar(){
        return new ProgressBar();
    }

    private void changeSplashAnimationSpeed(float value){
        database.changeValue(DatabaseConstants.SPLASHES_ANIM_SPEED, String.valueOf(value), DatabaseConstants.USER_ID, String.valueOf(id));
        splashAnimationValueLabel.setText(value + " мс");
        splashAnimationSpeedFloatValue = value;
        setSplashAnimation();
    }

    private void setActionsOnObjects() {
        try {
            splashAnimationSpeedSlider.valueProperty().addListener(listener -> changeSplashAnimationSpeed(Math.round(splashAnimationSpeedSlider.getValue())));

            showNotificationRadioButton.setOnAction(actionEvent -> {
                playClickSound();

                if(database.getElementFromColumn(DatabaseConstants.SHOW_NOTIFICATIONS, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    database.changeValue(DatabaseConstants.SHOW_NOTIFICATIONS, "0" ,DatabaseConstants.USER_ID, String.valueOf(id));
                    whatNotificationDoYouWantToApplyVBox.setDisable(true);
                    enableSendingNotifications = false;
                }else{
                    database.changeValue(DatabaseConstants.SHOW_NOTIFICATIONS, "1" ,DatabaseConstants.USER_ID, String.valueOf(id));
                    whatNotificationDoYouWantToApplyVBox.setDisable(false);
                    enableSendingNotifications = true;
                }
            });

            applyButtonToSendNotifications.setOnAction(actionEvent -> {
                playClickSound();

                applyToSendChasedNotifications();
            });

            applyToRemovePasswordIV.setCursor(Cursor.HAND);
            cancelToRemovePasswordIV.setCursor(Cursor.HAND);

            autoInstalledPasswordLabel.setOnMouseClicked(mouse -> {
                if(mouse.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    PasswordGenerator passwordGenerator = new PasswordGenerator();
                    passwordGenerator.generatePassword();

                    passwordForAccountThatGoingToInstall = passwordGenerator.getRecommendedPassword();
                    autoInstalledPasswordLabel.setText("Будет установлен пароль: " + passwordForAccountThatGoingToInstall);
                }
            });

            setPasswordAutomaticButton.setOnAction(actionEvent -> {
                playClickSound();

                buttonToSetPasswordPane.setVisible(false);
                setPasswordInAutoModePane.setVisible(true);

                PasswordGenerator passwordGenerator = new PasswordGenerator();
                passwordGenerator.generatePassword();

                passwordForAccountThatGoingToInstall = passwordGenerator.getRecommendedPassword();
                autoInstalledPasswordLabel.setText("Будет установлен пароль: " + passwordForAccountThatGoingToInstall);
            });

            okButtonInSetPasswordInAutoMode.setOnAction(actionEvent -> {
                playClickSound();

                setPasswordInAutoModePane.setVisible(false);
                setPasswordToAccountButton.setVisible(true);

                database.changeValue(DatabaseConstants.PASSWORD_FOR_ACCOUNT_ENTER, passwordForAccountThatGoingToInstall, DatabaseConstants.USER_ID, String.valueOf(id));
                passwordForAccountThatGoingToInstall = null;

                setPasswordToAccountButton.setText("Удалить пароль с аккаунта");
                setPasswordToAccountButton.setOnAction(actionEvent1 -> {
                    playClickSound();
                    removePasswordFromAccount();
                });
            });

            cancelButtonInSetPasswordInAutoMode.setOnAction(action -> {
                playClickSound();

                setPasswordInAutoModePane.setVisible(false);
                setPasswordToAccountButton.setVisible(true);
                passwordForAccountThatGoingToInstall = null;
            });

            enterYourSplashTF.setOnKeyPressed(key -> {
                if(key.getCode() == KeyCode.ENTER){
                    displayYourSplash();
                }
            });

            copyChatAddressLabel.setOnMouseClicked(mouseEvent -> copyChatAddress());

            scrollPanes.add(appearanceScrollPane);
            scrollPanes.add(searchScrollPane);
            scrollPanes.add(contentOnToolBarScrollPane);
            scrollPanes.add(hotKeysScrollPane);
            scrollPanes.add(addBlockInSettingsPane);

            showBarPolicysCheckBox.setOnAction(actionsEvent ->{
                if(database.getElementFromColumn(DatabaseConstants.SHOW_BAR_POLICY, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    database.changeValue(DatabaseConstants.SHOW_BAR_POLICY, "0", DatabaseConstants.USER_ID, String.valueOf(id));

                    for(int i = 0; i!=scrollPanes.size(); i++){
                        scrollPanes.get(i).setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    }
                }else{
                    database.changeValue(DatabaseConstants.SHOW_BAR_POLICY, "1", DatabaseConstants.USER_ID, String.valueOf(id));

                    for(int i = 0; i!=scrollPanes.size(); i++){
                        scrollPanes.get(i).setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                    }
                }
            });

            standartBG2Button.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    setBg("background/bg.png");
                    database.changeValue(DatabaseConstants.BACKGROUND_IMAGE, "background/bg.png", DatabaseConstants.USER_ID, String.valueOf(id));
                }
            });

            standartBG1Button.setOnMouseClicked(mouseEvent -> {
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

                if(database.getElementFromColumn(DatabaseConstants.HIDE_TOOLBAR, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")){
                    animToolBar(false);
                    database.changeValue(DatabaseConstants.HIDE_TOOLBAR, "0", DatabaseConstants.USER_ID, String.valueOf(id));

                    hideToolsBarButton.setText("Показать панель инструментов");

                    disableContentOnToolBarVBox.setDisable(true);
                    hideToolsBarAutoRadioButton.setDisable(true);
                }else{
                    animToolBar(true);
                    database.changeValue(DatabaseConstants.HIDE_TOOLBAR, "1", DatabaseConstants.USER_ID, String.valueOf(id));

                    hideToolsBarButton.setText("Скрывать панель инструментов");

                    disableContentOnToolBarVBox.setDisable(false);
                    hideToolsBarAutoRadioButton.setDisable(false);
                }
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
                    try{
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
                    }catch(Exception e){
                        showExceptions.showException(e);
                    }
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

                        setPasswordToAccountButton.setText("Удалить пароль с аккаунта");
                        setPasswordToAccountButton.setOnAction(actionEvent1 -> {
                            playClickSound();
                            removePasswordFromAccount();
                        });
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

                    displayYourSplashButton.setDisable(true);
                    splashAnimationSpeedSlider.setDisable(true);
                }else{
                    database.changeValue(DatabaseConstants.DISPLAY_SPLASHES, "1", DatabaseConstants.USER_ID, String.valueOf(id));
                    splashLabel.setVisible(true);

                    if(!splashesWereInitialized) initSplashes();

                    setSplash();
                    disableSplashesButton.setText("Отключить сплеши");

                    displayYourSplashButton.setDisable(false);
                    splashAnimationSpeedSlider.setDisable(false);
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
                        try {
                            Platform.runLater(() -> {
                                ProgressBar progressBar = createProgressBar();
                                progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);

                                chatListVboxInScrollPane.getChildren().removeAll(chatListVboxInScrollPane.getChildren());
                                recentChatsCounter = 0;
                                recentChatsLabel.setText("Недавние чаты ("+recentChatsCounter+")");

                                chatListVboxInScrollPane.getChildren().add(0, progressBar);

                                removeAllRecentChats.setVisible(true);
                                applyToRemoveRecentChatsButtonIV.setVisible(false);
                                rejectToRemoveButtonRecentChatsIV.setVisible(false);

                                try {
                                    FileUtils.cleanDirectory(new File("users/recentChats/" + userName));
                                } catch (IOException e) {
                                    showExceptions.showException(e);
                                }

                                chatListVboxInScrollPane.getChildren().remove(0);
                                removeAllRecentChats.setDisable(true);
                            });
                        }catch (Exception e){
                            showExceptions.showException(e);
                        }
                    }).start();
                }
            });

            createChatButton.setOnAction(event -> {
                try {
                    playClickSound();

                    openCreateChatView();
                } catch (Exception e) {
                    showExceptions.showException(e);
                }
            });

            continueInCreateChatPaneButton.setOnAction(actionEvent -> {
                playClickSound();

                createServer();
            });

            createAnotherPortLabel.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getButton()==MouseButton.PRIMARY){
                    playClickSound();

                    int port  = generatePort();
                    portLabelInCreateChatPane.setText("Порт: "+port);
                    chatPort = port;
                }
            });

            cancelToCreateChatButton.setOnMouseClicked(mouseEvent -> {
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

                    closeConnectToTheChatView();
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

                    clearOpenedSettingsMassiveIntegers();
                    openedSettingsIntegers[0] = 1;
                    displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                }
            });

            accountBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    clearOpenedSettingsMassiveIntegers();
                    openedSettingsIntegers[1] = 1;
                    displaySettings(PROFILE_SETTINGS);
                }
            });

            searchBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    clearOpenedSettingsMassiveIntegers();
                    openedSettingsIntegers[2] = 1;
                    displaySettings(SettingsConstants.SEARCH_SETTINGS);
                }
            });

            aboutPropgramBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    clearOpenedSettingsMassiveIntegers();
                    openedSettingsIntegers[3] = 1;
                    displaySettings(SettingsConstants.ABOUT_PROGRAM);
                }
            });

            additionalBlockInSettings.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    clearOpenedSettingsMassiveIntegers();
                    openedSettingsIntegers[4] = 1;
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
                    updateWeatherAfterAnyTime();
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void applyToSendChasedNotifications() {
        try {
            StringBuilder allowedNotificationStrBuilder = new StringBuilder();
            if (enabledNotifications[0].isSelected()) allowedNotificationStrBuilder.append("1");
            if (enabledNotifications[1].isSelected()) allowedNotificationStrBuilder.append("2");
            if (enabledNotifications[2].isSelected()) allowedNotificationStrBuilder.append("3");

            enabledNotificationString = allowedNotificationStrBuilder.toString();
            database.changeValue(DatabaseConstants.ALLOWED_NOTIFICATIONS, allowedNotificationStrBuilder.toString(), DatabaseConstants.USER_ID, String.valueOf(id));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void copyChatAddress() {
        try {
            new Thread(() -> {
                String astring = addressLabelInCreateChatPane.getText() + "/";
                char[] chars = astring.toCharArray();
                StringBuilder stringBuilder = new StringBuilder();

                int i = 0;
                while (chars[i] != ':') {
                    i++;
                }

                i += 2;

                int ai = i;
                while (chars[ai] != '/') {
                    try {
                        stringBuilder.append(chars[ai]);
                        ai++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }

                String addressString = stringBuilder + ":" + chatPort;
                copyText(addressString);

                Platform.runLater(() -> copyChatAddressLabel.setText("Скопировано ✔"));
                Platform.runLater(() -> copyChatAddressLabel.setTextFill(Color.valueOf("green")));
                Platform.runLater(() -> copyChatAddressLabel.setOnMouseClicked(mouseEvent -> {}));
                Platform.runLater(() -> copyChatAddressLabel.setCursor(javafx.scene.Cursor.DEFAULT));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    showExceptions.showException(e);
                }
                Platform.runLater(() -> copyChatAddressLabel.setText("Копировать"));
                Platform.runLater(() -> copyChatAddressLabel.setTextFill(Color.valueOf("#0077ff")));
                Platform.runLater(() -> copyChatAddressLabel.setOnMouseClicked(mouseEvent -> copyChatAddress()));
                Platform.runLater(() -> copyChatAddressLabel.setCursor(javafx.scene.Cursor.HAND));
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void closeConnectToTheChatView() {
        new Thread(() ->{
            if(useAnimations) animations.playAnimToTileInDesktop(connectToTheChatPane);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            connectToTheChatPane.setVisible(false);
            connectToTheChatTilePane.setDisable(false);
        }).start();
    }

    private void openCreateChatView(){
        try {
            if (!createChatPane.isVisible()) {
                createChatPane.setVisible(true);
                if(useAnimations) animations.setAnimationForSettings(createChatPane, 100);

                int port = generatePort();
                address = InetAddress.getLocalHost().getHostAddress();

                addressLabelInCreateChatPane.setText("Адрес: " + address);
                portLabelInCreateChatPane.setText("Порт: " + port);
                chatPort = port;
            } else {
                new Thread(() -> {
                    if(useAnimations) animations.playAnimToTileInDesktop(createChatPane);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    createChatPane.setVisible(false);
                }).start();
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }


    private void createServer() {
        try {
            new Thread(() ->{
                try {
                    ServerTest serverTest = new ServerTest(chatPort);
                    serverTest.sendNotification = enabledNotificationString.contains("2") & enableSendingNotifications;
                } catch (Exception e) {
                    showExceptions.showException(e);
                }
            }).start();

            String addressString = InetAddress.getLocalHost().getHostAddress()+":"+chatPort;

            hideCreateChatWindow();

            openConnectToTheChatMenu();
            chatAddressTF.setText(addressString);
        } catch (Exception e) {
            showExceptions.showException(e);
        }
    }

    private void hideCreateChatWindow() {
        try {
            new Thread(() -> {
                if(useAnimations) animations.playAnimToTileInDesktop(createChatPane);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                createChatPane.setVisible(false);
            }).start();
        }catch (Exception e){
            showExceptions.showException(e);
        }
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

            displayYourSplashButton.setDisable(false);
            splashAnimationSpeedSlider.setDisable(false);

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

            showNotificationRadioButton.setSelected(true);
            whatNotificationDoYouWantToApplyVBox.setDisable(false);
            for(int i = 0; i!=whatNotificationDoYouWantToApplyVBox.getChildren().size(); i++){
                if(!whatNotificationDoYouWantToApplyVBox.getChildren().get(i).toString().contains("Применить")){
                    CheckBox checkBox = (CheckBox) whatNotificationDoYouWantToApplyVBox.getChildren().get(i);
                    checkBox.setSelected(true);
                }
            }
            applyToSendChasedNotifications();

            database.changeValue(DatabaseConstants.HOT_KEY_FOR_CLOSE_APP, "ESCAPE", DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.HOT_KEY_FOR_MAKE_SCR, "F3", DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_HELP_MENU, "Ctrl + H", DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_SEARCH_MENU, "Ctrl + S", DatabaseConstants.USER_ID, String.valueOf(id));
            database.changeValue(DatabaseConstants.HOT_KEY_FOR_OPEN_SETTINGS, "Ctrl + I", DatabaseConstants.USER_ID, String.valueOf(id));
            loadInfoAboutHotKeys();
            initHotKeys();

            splashAnimationSpeedSlider.setValue(600);
            splashAnimationValueLabel.setText(String.valueOf(splashAnimationSpeedSlider.getValue()));
            splashAnimationSpeedFloatValue = (float) splashAnimationSpeedSlider.getValue();
            database.changeValue(DatabaseConstants.SPLASHES_ANIM_SPEED, String.valueOf(splashAnimationSpeedFloatValue), DatabaseConstants.USER_ID, String.valueOf(id));
            setSplashAnimation();
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

            showBarPolicysCheckBox.setSelected(true);
            database.changeValue(DatabaseConstants.SHOW_BAR_POLICY, "1", DatabaseConstants.USER_ID, String.valueOf(id));

            for(int i = 0; i!=scrollPanes.size(); i++){
                scrollPanes.get(i).setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            }
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
        try {
            if (!SearchVBox.isVisible()) {
                SearchVBox.setVisible(true);
                if(useAnimations) animations.setAnimationForSettings(SearchVBox, 100);
            } else {
                new Thread(() -> {
                    if(useAnimations) animations.playAnimToTileInDesktop(SearchVBox);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SearchVBox.setVisible(false);
                }).start();
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void openWeatherWidget(AtomicBoolean widgetDidOpened) {
        try {
            weatherWidgetVBox.setVisible(true);

            if (!widgetDidOpened.get()) {
                animateWeatherWidget(true);
                widgetDidOpened.set(true);
            } else {
                animateWeatherWidget(false);
                widgetDidOpened.set(false);
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void openConnectToTheChatMenu() {
        try {
            connectToTheChatPane.setVisible(true);
            connectToTheChatTilePane.setDisable(true);

            if(useAnimations) animations.setAnimationForSettings(connectToTheChatPane, 100);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void testMethod() {
        try {
            writeDebug("Starting test method...");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception has occurred");
            alert.setHeaderText("Было найдено исключение");
            try {
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("images/error.png")).openStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DialogPane dialogPane = alert.getDialogPane();
            Label label = new Label("New Label");
            dialogPane.setContent(label);
            alert.setContentText("Context");
            alert.showAndWait();

            writeDebug("Test method stopped");
        }catch (Exception e){
            showExceptions.showException(e);
        }
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
        try {
            if (hashCode == turnOffButton) {
                turnOffImageView.setVisible(b);
            }
            if (hashCode == MPButton) {
                MusicImageView.setVisible(b);
            }
            if (hashCode == SettingsButton) {
                settingsImageView.setVisible(b);
            }
            if (hashCode == weatherWidget) {
                weatherPane.setVisible(b);
            }
            if (hashCode == userWidget) {
                userWidgetPane.setVisible(b);
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void displayYourSplash(){
        try{
            if (!enterYourSplashTF.getText().trim().isEmpty()) {
                splashLabel.setText(enterYourSplashTF.getText().trim());
                enterYourSplashTF.clear();
                cancelToSetYourSplash();
            }else setErrorViewOnTextFields.setErrorViewToAnyField(enterYourSplashTF, null);
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    private void makeControlsForSetYourSplashVisible() {
        try {
            splashSettingsBlockPane.setPrefHeight(270);
            setYourSplashHBoxControls.setVisible(true);

            displayYourSplashButton.setText("Отмена");
            displayYourSplashButton.setStyle("-fx-background-color: red");

            continueButtonToSetYourSplash.setOnAction(actionEvent -> {
                playClickSound();

                displayYourSplash();
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

            splashSettingsBlockPane.setPrefHeight(206);
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

                    if(enabledNotificationString.contains("3") & enableSendingNotifications) {
                        SendNotification sendNotification = new SendNotification("Ваши настройки были восстановлены, " + userName, propertiesClass.getProperty("app_title"), TrayIcon.MessageType.INFO);
                        sendNotification.display();
                    }
                }catch (Exception ex){
                    showExceptions.showException(ex);
                }
            }).start();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void updateWeather(String place) {
        try {
            Weather weatherToUpdate = new Weather(place);
            weatherToUpdate.init();

            displayWeatherInfoOnMainScreen(weatherToUpdate.getTemperature(), weatherToUpdate.getDescription(), weatherToUpdate.getMain());
            displayWeatherInWeatherWidget(weatherToUpdate);
        }catch (Exception e){
            showExceptions.showException(e);
        }
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
        try {
            appearanceScrollPane.setVisible(false);
            userSettingsBlockPane.setVisible(false);
            searchPaneInSettings.setVisible(false);
            aboutProgramPaneInSettings.setVisible(false);
            addBlockInSettingsPane.setVisible(false);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void clearOpenedSettingsMassiveBooleans(){
        openedSettingsBooleans[0] = false;
        openedSettingsBooleans[1] = false;
        openedSettingsBooleans[2] = false;
        openedSettingsBooleans[3] = false;
    }

    private void clearOpenedSettingsMassiveIntegers(){
        openedSettingsIntegers[0] = 0;
        openedSettingsIntegers[1] = 0;
        openedSettingsIntegers[2] = 0;
        openedSettingsIntegers[3] = 0;
        openedSettingsIntegers[4] = 0;
    }

    private void displaySettings(int i) {
        try {
            setSettingSelectedBlock(i);
            
            if (i == SettingsConstants.APPEARANCE_SETTINGS) {
                hideAllSettingsBlocks();

                appearanceScrollPane.setVisible(true);

                if (useAnimations) animations.setAnimationForSettings(appearanceScrollPane, 200);

                blockTitleInSettings.setText("Оформление");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/aooearence2.png")).openStream()));
            } else if (i == PROFILE_SETTINGS) {
                hideAllSettingsBlocks();

                userSettingsBlockPane.setVisible(true);

                if (useAnimations) animations.setAnimationForSettings(userSettingsBlockPane, 200);

                blockTitleInSettings.setText("Профиль");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/accountSettings.png")).openStream()));
            } else if (i == SettingsConstants.SEARCH_SETTINGS) {
                hideAllSettingsBlocks();

                searchPaneInSettings.setVisible(true);

                if(useAnimations) animations.setAnimationForSettings(searchPaneInSettings, 200);

                blockTitleInSettings.setText("Поиск");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/searchSettings.png")).openStream()));
            } else if (i == SettingsConstants.ABOUT_PROGRAM) {
                hideAllSettingsBlocks();

                aboutProgramPaneInSettings.setVisible(true);

                if(useAnimations) animations.setAnimationForSettings(aboutProgramPaneInSettings, 200);

                blockTitleInSettings.setText("О программе");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/about.png")).openStream()));
            }else if (i == SettingsConstants.ADD_SETTINGS) {
                hideAllSettingsBlocks();

                addBlockInSettingsPane.setVisible(true);

                if(useAnimations) animations.setAnimationForSettings(addBlockInSettingsPane, 200);

                blockTitleInSettings.setText("Дополнительно");
                blockIconInSettings.setImage(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/other.png")).openStream()));
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void initToolTips(){
        try {
            Tooltip tooltipAutoInstalledPasswordLabel = new Tooltip("Нажмите, чтобы изменить пароль");
            tooltipAutoInstalledPasswordLabel.setWrapText(true);
            autoInstalledPasswordLabel.setTooltip(tooltipAutoInstalledPasswordLabel);

            initToolTipsInMP();
        }catch (Exception e){
            showExceptions.showException(e);
        }

    }

    private void initToolTipsInMP(){
        String playingMusicLabelText = playingMusicLabel.getText();
        char[] playingMusicLabelTextChars = playingMusicLabelText.toCharArray();

        int a = 0;
        while(playingMusicLabelTextChars[a] != ':'){
            a++;
        }
        a+=2;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = a; i!=playingMusicLabelTextChars.length; i++){
            stringBuilder.append(playingMusicLabelTextChars[i]);
        }

        Tooltip tooltipMusicLabel = new Tooltip(stringBuilder.toString());
        tooltipMusicLabel.setWrapText(true);
        playingMusicLabel.setTooltip(tooltipMusicLabel);

        Tooltip tooltipMPErrorLabel = new Tooltip(errorLabelMP.getText());
        tooltipMPErrorLabel.setWrapText(true);
        errorLabelMP.setTooltip(tooltipMPErrorLabel);
    }

    private void initSearch(){
        try {
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
                    new SearchRequests("Создать скриншот"),
                    new SearchRequests("Управление сплешами"),
                    new SearchRequests("Настройка горячих клавиш"),
                    new SearchRequests("Настройки погоды")
            );
            AtomicBoolean requestsIsFound = new AtomicBoolean(false);

            searchField.setOnKeyTyped(event -> {
                searchEllementVBox.getChildren().clear();

                if (!searchField.getText().trim().isEmpty()) {
                    if (searchField.getText().trim().equalsIgnoreCase("Все")) {
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
                    } else {
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
                } else {
                    Label typeTextIntoSearchFieldLabel = new Label("Введите запрос в поле поиска");
                    typeTextIntoSearchFieldLabel.setWrapText(true);
                    typeTextIntoSearchFieldLabel.setTextFill(Color.WHITE);

                    searchEllementVBox.getChildren().add(typeTextIntoSearchFieldLabel);
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private URL getProjectsFiles(String path){
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
            }else if(inputHashCode == MAKE_SCREENSHOT){
                image = new Image(getProjectsFiles("MainWindow/images/makeScreesnhot.png").openStream());
            }else if(inputHashCode == CLEAR_LOGS_ABOUT_ERRORS){
                image = new Image(getProjectsFiles("MainWindow/images/clearErrorLogs.png").openStream());
            }else if(inputHashCode == WATH_LOGS_ABOUT_ERRORS){
                image = new Image(getProjectsFiles("MainWindow/images/logs.png").openStream());
            }else if(inputHashCode == OPEN_SCREENSHOTS_FOLDER){
                image = new Image(getProjectsFiles("MainWindow/images/screenshots.png").openStream());
            }else if(inputHashCode == MANAGE_HOT_KEYS){
                image = new Image(getProjectsFiles("MainWindow/images/keyboard.png").openStream());
            }else if(inputHashCode == MANAGE_SPLASHES){
                image = new Image(getProjectsFiles("MainWindow/images/text.png").openStream());
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
            searchTileHBox.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13; -fx-padding: 5; -fx-cursor: hand");
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
        try {
            int hashCode = title.hashCode();

            searchTileHBox.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (hashCode == SearchRequests.Constants.APPEARANCE) {
                        openSettings();
                        displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                    } else if (hashCode == SearchRequests.Constants.CHANGE_PASSWORD) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                        showControlsToChangeUserData(2);
                        lookAtMeNowView(changeUserDataPane);
                    } else if (hashCode == SearchRequests.Constants.CHANGE_CITY) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                        showControlsToChangeUserData(1);
                        lookAtMeNowView(changeUserDataPane);
                    } else if (hashCode == SearchRequests.Constants.COLORS) {
                        openSettings();
                        displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                        lookAtMeNowView(colorsBlock);
                        appearanceScrollPane.setVvalue(appearanceScrollPane.getVmax());
                    } else if (hashCode == SearchRequests.Constants.PROFILE_SETTINGS) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                    } else if (hashCode == SearchRequests.Constants.CHANGE_LOGIN) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                        showControlsToChangeUserData(3);
                        lookAtMeNowView(changeUserDataPane);
                    } else if (hashCode == SearchRequests.Constants.CLEAR_LIST_OF_RECENT_CHATS) {
                        chatListVboxInScrollPane.getChildren().removeAll(chatListVboxInScrollPane.getChildren());
                        recentChatsCounter = 0;
                        recentChatsLabel.setText("Недавние чаты (0)");
                    } else if (hashCode == SearchRequests.Constants.CLOSE_APP) {
                        askBeforeExit();
                    } else if (hashCode == SearchRequests.Constants.CONNECT_TO_THE_CHAT) {
                        openConnectToTheChatMenu();
                    } else if (hashCode == SearchRequests.Constants.CREATE_CHAT) {
                       openCreateChatView();
                    } else if (hashCode == SearchRequests.Constants.EDIT_TOOLSBAR) {
                        openSettings();
                        displaySettings(SettingsConstants.ADD_SETTINGS);
                        lookAtMeNowView(toolBarSettingsPane);
                    } else if (hashCode == SearchRequests.Constants.EXIT_FROM_ACCOUNT) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                        lookAtMeNowView(userSettingsBlock1);
                    } else if (hashCode == SearchRequests.Constants.MUSIC) {
                        openMPlayer();
                    } else if (hashCode == SearchRequests.Constants.REMOVE_ACCOUNT) {
                        openSettings();
                        displaySettings(PROFILE_SETTINGS);
                        lookAtMeNowView(userSettingsBlock1);
                    } else if (hashCode == SearchRequests.Constants.SETTINGS) {
                        openSettings();
                    } else if (hashCode == SearchRequests.Constants.SETTINGS_OF_OPACITY_EFFECT) {
                        openSettings();
                        displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                        lookAtMeNowView(opacityBlock);
                    } else if (hashCode == SearchRequests.Constants.SOUNDS) {
                        openSettings();
                        displaySettings(SettingsConstants.APPEARANCE_SETTINGS);
                        appearanceScrollPane.setVvalue(appearanceScrollPane.getVmax());
                        lookAtMeNowView(soundsBlock);
                    } else if (hashCode == SearchRequests.Constants.WEATHER) {
                        openWeatherWidget(widgetDidOpened);
                    } else if (hashCode == SearchRequests.Constants.YOUR_MUSIC_LIST) {
                        if (userTracksFolder.exists()){
                            openFile(userTracksFolder);
                        }else{
                            userTracksFolder.mkdir();
                            showExceptions.showException(new Exception(userName + "'s folder was not found. It was just created. Try again"));
                        }
                    } else if (hashCode == SearchRequests.Constants.GET_HELP) {
                        openHelpWindow();
                    } else if (hashCode == SearchRequests.Constants.ABOUT_PROGRAM) {
                        openSettings();
                        displaySettings(SettingsConstants.ABOUT_PROGRAM);
                    } else if (hashCode == SearchRequests.Constants.CHANGE_AVATAR) {
                        changeAvatar(true);
                    } else if (hashCode == MAKE_SCREENSHOT) {
                        createScreenshot();
                    } else if (hashCode == OPEN_SCREENSHOTS_FOLDER) {
                        openFile(new File("userGallery"));
                    } else if (hashCode == WATH_LOGS_ABOUT_ERRORS) {
                        openFile(new File("errorLogs"));
                    } else if (hashCode == CLEAR_LOGS_ABOUT_ERRORS) {
                        if (Objects.requireNonNull(new File("errorLogs").list()).length != 0) {
                            new Thread(() -> {
                                try {
                                    FileUtils.cleanDirectory(new File("errorLogs"));
                                } catch (IOException e) {
                                    showExceptions.showException(e);
                                }
                            }).start();
                        } else showExceptions.showException(new Exception("The directory is empty"));
                    }else if(hashCode == SearchRequests.Constants.MANAGE_SPLASHES){
                        openSettings();
                        displaySettings(ADD_SETTINGS);

                        lookAtMeNowView(splashSettingsBlockPane);
                    }else if(hashCode == SearchRequests.Constants.MANAGE_HOT_KEYS){
                        openSettings();
                        displaySettings(ADD_SETTINGS);

                        lookAtMeNowView(hotKeysSettingsBlockInSettings);
                    }else if(hashCode == WEATHER_SETTINGS){
                        openSettings();
                        displaySettings(APPEARANCE_SETTINGS);
                        appearanceScrollPane.setVvalue(appearanceScrollPane.getVmin());
                        lookAtMeNowView(weatherBlock);
                    }
                }
            });
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void changeAvatar(boolean b) {
        try {
            if(b) {
                openSettings();
                displaySettings(PROFILE_SETTINGS);
            }

            FileChooser fileChooserPhoto = new FileChooser();
            fileChooserPhoto.setTitle("𝓞𝓹𝓮𝓷 𝓟𝓱𝓸𝓽𝓸 𝓕𝓲𝓵𝓮");
            fileChooserPhoto.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Изображения", "*.png*", "*.jpg*", "*.bmp*", "*.jpeg*"),
                    new FileChooser.ExtensionFilter("Все", "*.*")
            );

            String lastPhotoPath = database.getElementFromColumn(DatabaseConstants.LAST_AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id));
            if (lastPhotoPath != null) {
                if (new File(lastPhotoPath).exists()) {
                    fileChooserPhoto.setInitialDirectory(new File(lastPhotoPath));
                }
            }

            File selectedFile = fileChooserPhoto.showOpenDialog(getStage());
            if (selectedFile != null) {
                assert lastPhotoPath != null;
                if (new File(database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id))).exists() & !database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id)).equals(DEFAULT_USER_AVATAR)) new File(database.getElementFromColumn(DatabaseConstants.AVATAR_PATH, DatabaseConstants.USER_ID, String.valueOf(id))).delete();

                setAvatarImage(selectedFile);

                try {
                    FileUtils.copyFileToDirectory(selectedFile, new File("users/avatars"));
                } catch (IOException e) {
                    showExceptions.showException(e);
                }

                database.changeValue(DatabaseConstants.AVATAR_PATH, new File("users/avatars/" + selectedFile.getName()).getAbsolutePath(), DatabaseConstants.USER_ID, String.valueOf(id));
            }
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    private void setAvatarImage(File selectedFile) {
        try {
            avatarCircle.setFill(new ImagePattern(new Image(selectedFile.getAbsolutePath())));
            userAvatarCircleInSettings.setFill(new ImagePattern(new Image(selectedFile.getAbsolutePath())));
        }catch (Exception e){
            showExceptions.showException(e);
        }
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
            hbox.setStyle("-fx-background-color: #191919; -fx-background-radius: 13; -fx-border-radius: 13; -fx-border-color: white; -fx-cursor: hand");
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

            hbox.setEffect(new DropShadow());

            chatListVboxInScrollPane.getChildren().add(hbox);

            if(useAnimations) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), hbox);
                translateTransition.setFromY(-20);
                translateTransition.setToY(0);
                translateTransition.play();
            }

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

    private void closeApp(){
        try {
            writeDebug("Closing app...");

            Platform.exit();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void askBeforeExit(){
        try {
            if(enableConfirmBefore) {
                if(!confirmBeforeCloseISOpen) {
                    confirmBeforeCloseISOpen = true;
                    closeConfirmPane.setVisible(true);
                    if(useAnimations) animations.setAnimationForSettings(closeConfirmPane, 100);

                    rejectButtonCloseConfirmPane.setOnAction(event -> {
                        playClickSound();

                        new Thread(() -> {
                            if(useAnimations) Platform.runLater(() -> animations.playAnimToTileInDesktop(closeConfirmPane));
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
                            if(useAnimations) Platform.runLater(() -> animations.playAnimToTileInDesktop(closeConfirmPane));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                showExceptions.showException(e);
                            }
                            Platform.runLater(() -> closeConfirmPane.setVisible(false));
                        }).start();
                    }).start();
                }else{
                    confirmBeforeCloseISOpen = false;

                    new Thread(() ->{
                        if(useAnimations) animations.playAnimToTileInDesktop(closeConfirmPane);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            showExceptions.showException(e);
                        }

                        closeConfirmPane.setVisible(false);
                    }).start();
                }
            }else closeApp();
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }
    private void prepareToConnect(){
        try {
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

                    database.changeValue(DatabaseConstants.LAST_CHAT_ADDRESS, chatAddressTF.getText().trim(), DatabaseConstants.USER_ID, String.valueOf(id));

                    connect(address, chatPortInteger);
                } catch (Exception ex) {
                    showErrorInConnectToTheChatMenu(ex.toString());
                }
            } else {
                setErrorViewOnTextFields.setErrorViewToAnyField(chatAddressTF, null);
                showErrorInConnectToTheChatMenu("Некорректный адрес");
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void connect(StringBuilder address, int port) {
        try {
            addRecentChat(address + ":" + port, time.getDate() + " / " + time.getTime());

            closeConnectToTheChatView();
            new Thread(() -> {
                try{
                    Platform.runLater(() -> loadChatWindow(address.toString(), port));
                }catch (Exception e){
                    Platform.runLater(() -> showExceptions.showException(e));
                }
            }).start();
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
        try {
            String[] files = folder.list();
            assert files != null;
            int fileID = (int) (Math.random() * files.length);
            return new File(files[fileID]);
        }catch (Exception e){
            showExceptions.showException(e);
        }
        return null;
    }

    private void lookAtMeNowView(@NotNull Node node){
        final int[] i = {0};

        new Thread(() -> {
            while(i[0] != 5){
                try{
                    Platform.runLater(() -> node.setStyle("-fx-border-color: rgb(115,140,210); -fx-border-width: 3; -fx-border-radius: 13"));
                    Thread.sleep(350);
                    Platform.runLater(() -> node.setStyle("-fx-border-color: white; -fx-border-width: 1.5; -fx-border-radius: 13"));
                    i[0]++;
                    Thread.sleep(1000);
                }catch(Exception e){
                    showExceptions.showException(e);
                }
            }
        }).start();
    }

    private void restoreSearchSettings(){
        try {
            turnOnOrOffSearchUsingButton.setText("Выкл. поиск");
            database.changeValue(DatabaseConstants.USE_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
            searchButtonImageView.setVisible(true);

            showingIconsRequestsInSearchRadioButton.setSelected(true);
            database.changeValue(DatabaseConstants.DISPLAY_IMAGES_IN_SEARCH, "1", DatabaseConstants.USER_ID, String.valueOf(id));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void initMusic(){
        try {
            AtomicReference<MP3Player> player = new AtomicReference<>();

            MusicImageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    openMPlayer();
                }
            });

            closeMPButtonMP.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    playClickSound();

                    new Thread(() ->{
                        if(useAnimations) animations.playAnimToTileInDesktop(MPPane);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            showExceptions.showException(e);
                        }

                        MPPane.setVisible(false);
                    }).start();
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
                                initToolTipsInMP();
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

    private void openMPlayer() {
        try {
            playClickSound();

            if (!MPPane.isVisible()) {
                MPPane.setVisible(true);
                if (useAnimations) animations.setAnimationForSettings(MPPane, 100);
            } else {
                new Thread(() -> {
                    if (useAnimations) animations.playAnimToTileInDesktop(MPPane);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        showExceptions.showException(e);
                    }

                    MPPane.setVisible(false);
                }).start();
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void showMPError(Exception e) {
        try {
            new Thread(() -> {
                Platform.runLater(() -> errorLabelMP.setVisible(true));
                Platform.runLater(() -> errorLabelMP.setText(e.getLocalizedMessage()));
                Platform.runLater(this::initToolTipsInMP);
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    showExceptions.showException(ex);
                }
                Platform.runLater(() -> errorLabelMP.setText(""));
                Platform.runLater(() -> errorLabelMP.setVisible(false));
            }).start();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private MP3Player createPlayer(String source) {
        return new MP3Player(new File(source));
    }

    private void showControlsToChangeUserData(int whatToChange){
        try {
            changeUserDataPane.setPrefHeight(337);
            componenetsToChangeSomethingInUserSettings.setVisible(true);

            newValueTFInUserSettings.clear();
            currentValueTFInUserSettings.clear();

            buttonsInUserSettingsVBox.setDisable(true);

            if (whatToChange == 1) {
                currentValueTFInUserSettings.setPromptText("Новый город");
                newValueTFInUserSettings.setVisible(false);

                continueButtonInUserSettings.setOnAction(action -> {
                    if (!currentValueTFInUserSettings.getText().trim().isEmpty() & !(currentValueTFInUserSettings.getText().trim().length() < 3)) {
                        changeCity(currentValueTFInUserSettings.getText().trim());
                        hideControlsToChangeUserData();
                    } else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                });
            } else if (whatToChange == 2) {
                currentValueTFInUserSettings.setPromptText("Текущий пароль");
                newValueTFInUserSettings.setPromptText("Новый пароль");

                continueButtonInUserSettings.setOnAction(action -> {
                    if (!currentValueTFInUserSettings.getText().trim().isEmpty()) {
                        if (!newValueTFInUserSettings.getText().trim().isEmpty() & !(newValueTFInUserSettings.getText().trim().length() < 7)) {
                            if (currentValueTFInUserSettings.getText().trim().equals(password)) {
                                changePassword(newValueTFInUserSettings.getText().trim());
                                hideControlsToChangeUserData();
                            }
                            setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                        } else setErrorViewOnTextFields.setErrorViewToAnyField(newValueTFInUserSettings, null);
                    } else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                });

            } else if (whatToChange == 3) {
                currentValueTFInUserSettings.setPromptText("Текущий логин");
                newValueTFInUserSettings.setPromptText("Новый логин");

                continueButtonInUserSettings.setOnAction(action -> {
                    if (!currentValueTFInUserSettings.getText().trim().isEmpty()) {
                        if (!newValueTFInUserSettings.getText().trim().isEmpty() & !(currentValueTFInUserSettings.getText().trim().length() < 4)) {
                            if (currentValueTFInUserSettings.getText().trim().equals(userName)) {
                                changeLogin(newValueTFInUserSettings.getText().trim());
                                hideControlsToChangeUserData();
                            } else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                        } else setErrorViewOnTextFields.setErrorViewToAnyField(newValueTFInUserSettings, null);
                    } else setErrorViewOnTextFields.setErrorViewToAnyField(currentValueTFInUserSettings, null);
                });
            }
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void changeID(String login, String password){
        try {
            int newID = login.hashCode() * password.hashCode();
            System.out.println(newID);

            database.changeValue(DatabaseConstants.USER_ID, String.valueOf(newID), DatabaseConstants.USER_ID, String.valueOf(id));
            id = newID;
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void changeLogin(String trim) {
        try {
            userName = trim;
            database.changeValue(DatabaseConstants.USER_NAME, trim, DatabaseConstants.USER_ID, String.valueOf(id));
            changeID(userName, password);
            usernameInSettingsuserBlock.setText(trim);
            userNameLabel.setText(userName);
            welcomeLabel.setText("Добро пожаловать, " + trim);
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void changePassword(String trim) {
        try {
            password = trim;
            database.changeValue(DatabaseConstants.USER_PASSWORD, trim, DatabaseConstants.USER_ID, String.valueOf(id));
            changeID(userName, password);
        }catch (Exception e){
            showExceptions.showException(e);
        }
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
        try {
            changeUserDataPane.setPrefHeight(189);
            componenetsToChangeSomethingInUserSettings.setVisible(false);
            newValueTFInUserSettings.setVisible(true);

            newValueTFInUserSettings.clear();
            currentValueTFInUserSettings.clear();

            buttonsInUserSettingsVBox.setDisable(false);
        }catch (Exception e){
            showExceptions.showException(e);
        }
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
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                HelpController helpController = fxmlLoader.getController();
                helpController.helpTitleLabel.setText("Справка "+propertiesClass.getProperty("app_title"));
                helpWindowIsOpen = true;

                stage.setOnCloseRequest(event -> helpWindowIsOpen=false);
                stage.showAndWait();
            }
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void initSplashes(){
        try {
            setSplash();

            splashLabel.setOnMouseClicked(mouseEvent -> {
                try {
                    ArrayList<String> splashesListArray = new ArrayList<>(List.of(splashesList));
                    int currentID = splashesListArray.indexOf(splashLabel.getText());
                    int maxID = splashesListArray.size() - 1;

                    if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                        if (currentID != maxID) {
                            currentID++;
                        } else {
                            currentID = 0;
                        }
                    }else if(mouseEvent.getButton() == MouseButton.SECONDARY){
                        if (currentID != 0) {
                            currentID--;
                        } else {
                            currentID = maxID;
                        }
                    }

                    splashLabel.setText(splashesListArray.get(currentID));

                    if(splashLabel.getText().length() > "If you make mistake - you must to".length()) {
                        Tooltip splashToolTip = new Tooltip();
                        splashToolTip.setText(splashLabel.getText());
                        splashLabel.setTooltip(splashToolTip);
                    }else splashLabel.setTooltip(null);

                    if(splashesListArray.get(currentID).equals("Rainbow Splash")) makeRainbowSplash(); else makeSplashNormal();
                }catch (Exception e){
                    showExceptions.showException(e);
                }
            });

            setSplashAnimation();

            splashesWereInitialized = true;
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private void setSplashAnimation() {
        try {
            ScaleTransition splashAnimation = new ScaleTransition(Duration.millis(splashAnimationSpeedFloatValue), splashLabel);
            splashAnimation.setFromX(1);
            splashAnimation.setFromY(1);
            splashAnimation.setToX(1.3);
            splashAnimation.setToY(1.3);
            splashAnimation.setAutoReverse(true);
            splashAnimation.setCycleCount(Animation.INDEFINITE);
            splashAnimation.play();
        }catch(Exception e){
            showExceptions.showException(e);
        }
    }

    private void makeSplashNormal() {
        try {
            splashLabel.setTextFill(Color.web(defaultSplashColor));
            changeColors = false;
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    private List<Color> getAllPossibleColors(){
        try {
            List<Color> colors = new ArrayList<>();
            for (int red = 0; red != 255; red++) {
                for (int green = 0; green != 255; green++) {
                    for (int blue = 0; blue != 255; blue++) {
                        Color color = Color.rgb(red, green, blue);
                        colors.add(color);
                    }
                }
            }
            return colors;
        }catch (Exception e){
            showExceptions.showException(e);
        }
        return null;
    }

    private void setSplash() {
        try {
            splashesList = new String[]{"DF, i'm sorry...", "Welcome to Messenger v.5!", "Good to see you, " + userName + "!", "What's up?", "Every story has its own ending", "What, if you lost...", " ...friends?",
                    "Try to create chat!", "You must to thinking, before doing something", "What's the problem?", "By Dark Studio", "Uses Java", "...and JavaFX", "It's got better?", "I don't know...",
                    "What did i do?", "Version " + propertiesClass.getProperty("app_version"), "Hello, all countries!", "What is happiness?", "I know 3 wonderful girls", "Herobrine is gone", "Sorry!",
                    "Life is complicated", "I did two mistakes while writing previous phrase", "Available for Windows", "No mobile version...(", "showExceptions.showExceptions(e);", "Everything is fine!",
                    "Welcome!", "A lot of good things is happening..", "If you make mistake - you must to resolve it", "Hello!", "Unity#uck", "Rainbow Splash"};

            int splashId = (int) (Math.random() * splashesList.length);

            if(splashesList[splashId].equals("Rainbow Splash")){
                makeRainbowSplash();
            }else {
                splashLabel.setTextFill(Color.web(defaultSplashColor));
                changeColors = false;
            }

            splashLabel.setText(splashesList[splashId]);

            if(splashLabel.getText().length() > "If you make mistake - you must to".length()) {
                Tooltip splashToolTip = new Tooltip();
                splashToolTip.setText(splashesList[splashId]);
                splashLabel.setTooltip(splashToolTip);
            }else splashLabel.setTooltip(null);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void makeRainbowSplash() {
        new Thread(() -> {
            try {
                changeColors = true;

                Platform.runLater(() -> splashLabel.setText("Collecting info about colors..."));
                List<Color> everyColors = getAllPossibleColors();
                Platform.runLater(() -> splashLabel.setText("Rainbow splash"));

                while (changeColors) {
                    assert everyColors != null;
                    int color = (int) (Math.random() * everyColors.size());

                    Platform.runLater(() -> splashLabel.setTextFill(everyColors.get(color)));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        showExceptions.showException(e);
                    }
                }
            }catch (Exception e){
                showExceptions.showException(e);
            }
        }).start();
    }

    private void openStartWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartWindow/logInUI.fxml"));
            fxmlLoader.load();

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setTitle(new PropertiesClass().getProperty("app_title"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("MainWindow/images/icon.png")).openStream()));
            stage.show();
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    private void writeToDatabaseWhatIconsToShowInToolBar(CheckBox[] checkBoxes){
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i != checkBoxes.length - 1; i++) {
                if (checkBoxes[i].isSelected()) {
                    if (checkBoxes[i].getText().hashCode() == turnOffButton) {
                        stringBuilder.append(TURN_OFF_BUTTON_CHECKBOX_ID);
                    }
                }

                if (checkBoxes[i].isSelected()) {
                    if (checkBoxes[i].getText().hashCode() == MPButton) {
                        stringBuilder.append(MP_BUTTON_CHECKBOX_ID);
                    }
                }

                if (checkBoxes[i].isSelected()) {
                    if (checkBoxes[i].getText().hashCode() == SettingsButton) {
                        stringBuilder.append(SETTINGS_BUTTON_CHECKBOX_ID);
                    }
                }

                if (checkBoxes[i].isSelected()) {
                    if (checkBoxes[i].getText().hashCode() == weatherWidget) {
                        stringBuilder.append(WEATHER_WIDGET_CHECKBOX_ID);
                    }
                }

                if (checkBoxes[i].isSelected()) {
                    if (checkBoxes[i].getText().hashCode() == userWidget) {
                        stringBuilder.append(USER_WIDGET_CHECKBOX_ID);
                    }
                }
            }

            database.changeValue(DatabaseConstants.WHAT_ITEMS_DISPLAY_ON_TASKBAR, stringBuilder.toString(), DatabaseConstants.USER_ID, String.valueOf(id));
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    String requestsImageForWeather(String state){
        try {
            String image = null;

            if (state.hashCode() == cloudy) {
                image = cloudsImage;
            } else if (state.hashCode() == rain) {
                image = rainImage;
            } else if (state.hashCode() == snow) {
                image = snowImage;
            } else if (state.hashCode() == fog) {
                image = fogImage;
            } else if (state.hashCode() == clearSky) {
                image = clearSkyImage;
            }

            return image;
        }catch (Exception e){
            showExceptions.showException(e);
        }
        return null;
    }

    private void updateWeatherAfterAnyTime(){
        new Thread(() ->{
            while(true){
                try {
                    if(database.getElementFromColumn(DatabaseConstants.USE_WEATHER, DatabaseConstants.USER_ID, String.valueOf(id)).equals("1")) {
                        Thread.sleep(600000);
                        Platform.runLater(() -> updateWeather(city));
                    }else break;
                }catch (Exception e){
                    showExceptions.showException(e);
                    break;
                }
            }
        }).start();
    }

    private void loadChatWindow(String address, int port){
        Stage stage1 = null;

        try{
            int result = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chat/chatUI.fxml"));
            fxmlLoader.load();

            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            stage1 = new Stage();
            stage1.initStyle(StageStyle.TRANSPARENT);
            stage1.setScene(scene);
            ChatController chatController = fxmlLoader.getController();

            try {
                chatController.connectToServer(address, port);
            }catch (Exception e){
                result = 1;
                showExceptions.showException(e);
            }

            stage1.setTitle(userName);
            stage1.setOnCloseRequest(close -> chatController.disconnect());

            chatController.stage = stage1;

            String[] avatarsFiles = new File("images/userDefAvatars/").list();
            assert avatarsFiles != null;
            chatController.hidenTextField.setText(userName + Math.round((Math.random() * avatarsFiles.length)));

            if(result != 1) {
                stage1.show();
            }
        }catch (Exception e){
            showExceptions.showException(e);
            assert stage1 != null;
            stage1.close();
        }
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

        public static final int hotKeyForExit = 1;
        public static final int hotKeyForMakeScreenshot = 2;
        public static final int hotKeyForOpenSearch = 3;
        public static final int hotKeyForOpenSettings = 4;
        public static final int hotKeyForOpenHelpMenuInt = 5;

        static final String DEFAULT_USER_AVATAR = "images/userDefAvatar.png";
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
