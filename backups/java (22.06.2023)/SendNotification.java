package com.darkstudio.messenger_v5;

import java.awt.*;

public class SendNotification {
    String message, caption;
    ShowExceptions showExceptions = new ShowExceptions();

    TrayIcon.MessageType messageType;
    TrayIcon trayIcon;

    public SendNotification(String message, String caption, TrayIcon.MessageType messageType) {
        this.message = message;
        this.caption = caption;
        this.messageType = messageType;
    }

    public void display(){
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

            trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);

            trayIcon.displayMessage(caption, message, messageType);
        }catch (Exception ex){
            showExceptions.showException(ex);
        }
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }
}
