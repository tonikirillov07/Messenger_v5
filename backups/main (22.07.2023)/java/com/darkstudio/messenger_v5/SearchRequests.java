package com.darkstudio.messenger_v5;

public class SearchRequests {
    String request;

    public SearchRequests(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public class Constants{
        public static final int MUSIC = "Музыка".hashCode();
        public static final int CREATE_CHAT = "Создать чат".hashCode();
        public static final int CONNECT_TO_THE_CHAT = "Подключиться к чату".hashCode();
        public static final int SETTINGS = "Настройки".hashCode();
        public static final int PROFILE_SETTINGS = "Настройки профиля".hashCode();
        public static final int EDIT_TOOLSBAR = "Редактировать панель инструментов".hashCode();
        public static final int CLEAR_LIST_OF_RECENT_CHATS = "Очистить список недавних чатов".hashCode();
        public static final int YOUR_MUSIC_LIST = "Список своей музыки".hashCode();
        public static final int REMOVE_ACCOUNT = "Удалить аккаунт".hashCode();
        public static final int CLOSE_APP = "Закрыть мессенджер".hashCode();
        public static final int EXIT_FROM_ACCOUNT = "Выйти из аккаунта".hashCode();
        public static final int APPEARANCE = "Оформление".hashCode();
        public static final int SETTINGS_OF_OPACITY_EFFECT = "Настройки эффекта прозрачности".hashCode();
        public static final int COLORS = "Цвета".hashCode();
        public static final int SOUNDS = "Звуки".hashCode();
        public static final int WEATHER = "Погода".hashCode();
        public static final int CHANGE_CITY = "Сменить город".hashCode();
        public static final int CHANGE_PASSWORD = "Сменить пароль".hashCode();
        public static final int CHANGE_LOGIN = "Сменить логин".hashCode();
        public static final int GET_HELP = "Справка".hashCode();
        public static final int ABOUT_PROGRAM= "О программе".hashCode();
        public static final int CHANGE_AVATAR = "Сменить аватар".hashCode();
        public static final int MAKE_SCREENSHOT = "Создать скриншот".hashCode();
        public static final int OPEN_SCREENSHOTS_FOLDER = "Открыть папку со скриншотами".hashCode();
        public static final int WATH_LOGS_ABOUT_ERRORS = "Посмотреть логи об ошибках".hashCode();
        public static final int CLEAR_LOGS_ABOUT_ERRORS = "Очистить логи об ошибках".hashCode();
        public static final int MANAGE_SPLASHES = "Управление сплешами".hashCode();
        public static final int MANAGE_HOT_KEYS = "Настройка горячих клавиш".hashCode();
        public static final int WEATHER_SETTINGS = "Настройки погоды".hashCode();
    }
}
