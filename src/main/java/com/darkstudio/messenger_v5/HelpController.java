package com.darkstudio.messenger_v5;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelpController {
    @FXML
    private TabPane contentTabPane;
    
    @FXML
    public Label descriptionTextLabel, helpTitleLabel;

    @FXML
    private Hyperlink radminVPNLink, systemReqForJavaFX, resolvingProblemsWithWeather;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView minimizeWindowButton, closeWindowButton, goHomeIV, openSearchButtonIV;

    @FXML
    private VBox searchVBox, searchContentVbox;

    @FXML
    private HBox headerHBox;

    private double x, y;

    ShowExceptions showExceptions = new ShowExceptions();
    PropertiesClass propertiesClass = new PropertiesClass();

    public HelpController() throws Exception {
    }

    @FXML
    void initialize() {
        initSearch();
        setActionsOnNodes();
    }

    private void setActionsOnNodes() {
        headerHBox.setOnMousePressed(event ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        headerHBox.setOnMouseDragged(event ->{
            headerHBox.getScene().getWindow().setX(event.getScreenX()-x);
            headerHBox.getScene().getWindow().setY(event.getScreenY()-y);
        });

        closeWindowButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                ((Stage) headerHBox.getScene().getWindow()).close();
            };
        });

        minimizeWindowButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) ((Stage) headerHBox.getScene().getWindow()).setIconified(true);
        });
        
        String descriptionText = "Чат \"Messenger v.5\" создан для общения пользователей между собой. Приложение включает в себя множество функций.\n" +
                "Вы можете без проблем общаться с другими пользователями, используя предусмотренные в Messenger v.5 инструменты. \n" +
                "\n" +
                "Информация: \n" +
                "Версия: " + propertiesClass.getProperty("app_version") + "\n" +
                "Сборка: " + propertiesClass.getProperty("app_build") + "\n" +
                "Разработчик: " + propertiesClass.getProperty("app_developer") + "\n" +
                "\n" +
                "Чтобы начать знакомство с приложением, перейдите во вкладку \"Начало работы\". ";
        descriptionTextLabel.setText(descriptionText);

        radminVPNLink.setOnAction(event ->{
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.radmin-vpn.com/ru/"));
                } catch (Exception e) {
                    showExceptions.showException(e);
                }
            }else showExceptions.showException(new Exception("Desktop is not supported"));
        });

        systemReqForJavaFX.setOnAction(actionEvent -> {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://docs.oracle.com/javafx/2/system_requirements_2-2-5/jfxpub-system_requirements_2-2-5.htm"));
                } catch (Exception e) {
                    showExceptions.showException(e);
                }
            }else showExceptions.showException(new Exception("Desktop is not supported"));
        });

        resolvingProblemsWithWeather.setOnAction(actionEvent -> {
            SingleSelectionModel<Tab> singleSelectionModel = contentTabPane.getSelectionModel();
            singleSelectionModel.select(4);
        });

        openSearchButtonIV.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton()== MouseButton.PRIMARY){
                searchVBox.setVisible(!searchVBox.isVisible());
            }
        });

        goHomeIV.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton()== MouseButton.PRIMARY){
                SingleSelectionModel<Tab> singleSelectionModel = contentTabPane.getSelectionModel();
                singleSelectionModel.select(0);
            }
        });
    }

    private HBox createSearchTile(String title, int hash) {
        HBox searchHboxTile = new HBox();

        try {
            searchHboxTile.setStyle("-fx-background-color: #46454d; -fx-background-radius: 13; -fx-border-color: white;" +
                    "-fx-border-radius: 13; -fx-cursor: hand");
            searchHboxTile.setSpacing(10);
            searchHboxTile.setAlignment(Pos.CENTER);
            searchHboxTile.getStyleClass().add("searchTile");

            ImageView imageView = new ImageView(getSearchImage(hash));
            imageView.setFitWidth(50);
            imageView.setFitHeight(44);
            searchHboxTile.getChildren().add(imageView);

            Label label = new Label(title);
            label.setStyle("-fx-text-fill: white");
            searchHboxTile.getChildren().add(label);

            setActionOnSearchTile(searchHboxTile, hash);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }

        return searchHboxTile;
    }

    private void setActionOnSearchTile(HBox searchHboxTile, int hash) {
        SingleSelectionModel<Tab> singleSelectionModel = contentTabPane.getSelectionModel();

        searchHboxTile.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton()==MouseButton.PRIMARY){
                if(hash==SearchConstants.GETTING_START){
                    singleSelectionModel.select(1);
                }else if(hash==SearchConstants.DESCRIPTION){
                    singleSelectionModel.select(0);
                }else if(hash==SearchConstants.SYS_REQ){
                    singleSelectionModel.select(2);
                }else if(hash==SearchConstants.WEATHER){
                    singleSelectionModel.select(3);
                }else if(hash==SearchConstants.WEATHER_TROUBLES){
                    singleSelectionModel.select(4);
                }else if(hash==SearchConstants.CONNECTION_TROUBLES){
                    singleSelectionModel.select(5);
                }
            }
        });
    }

    private Image getSearchImage(int hash) throws Exception {
        Image image = null;

        if(hash==SearchConstants.DESCRIPTION){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/about.png")).openStream());
        }else if(hash==SearchConstants.CONNECTION_TROUBLES){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/connectionError.png")).openStream());
        }else if(hash==SearchConstants.GETTING_START){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/radminVPN.png")).openStream());
        }else if(hash==SearchConstants.SYS_REQ){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/ram.png")).openStream());
        }else if(hash==SearchConstants.WEATHER){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/weather.png")).openStream());
        }else if(hash==SearchConstants.WEATHER_TROUBLES){
            image = new Image(Objects.requireNonNull(getClass().getResource("Help/images/404.png")).openStream());
        }

        return image;
    }

    private void initSearch() {
        String[] requests = {"Описание", "Начало работы", "Решение проблем с погодой", "Решение проблем с подключением к чату", "Системные требования", "Погода"};

        searchTextField.setOnKeyTyped(keyEvent -> {
            searchContentVbox.getChildren().clear();

            if(!searchTextField.getText().trim().isEmpty()){
                if(searchTextField.getText().trim().equalsIgnoreCase("Все")){
                    for(int i = 0; i!=requests.length; i++){
                        HBox searchTile = new HBox();
                        try {
                            searchTile = createSearchTile(requests[i], requests[i].hashCode());
                        } catch (Exception e) {
                            showExceptions.showException(e);
                        }
                        searchContentVbox.getChildren().add(searchTile);
                    }
                }else{
                    for(int i = 0; i!=requests.length; i++){
                        if(requests[i].toLowerCase().contains(searchTextField.getText().trim().toLowerCase())){
                            HBox searchTile = new HBox();
                            try {
                                searchTile = createSearchTile(requests[i], requests[i].hashCode());
                            } catch (Exception e) {
                                showExceptions.showException(e);
                            }
                            searchContentVbox.getChildren().add(searchTile);
                        }
                    }
                }
            }
        });
    }

    static class SearchConstants{
        public static final int DESCRIPTION = "Описание".hashCode();
        public static final int GETTING_START = "Начало работы".hashCode();
        public static final int SYS_REQ = "Системные требования".hashCode();
        public static final int WEATHER = "Погода".hashCode();
        public static final int WEATHER_TROUBLES = "Решение проблем с погодой".hashCode();
        public static final int CONNECTION_TROUBLES = "Решение проблем с подключением к чату".hashCode();
    }

}
