module com.darkstudio.messenger_v {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.management;
    requires jdk.management;
    requires json.simple;
    requires jaco.mp3.player;
    requires org.apache.commons.io;
    requires annotations;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.mail;
    requires java.desktop;

    opens com.darkstudio.messenger_v5 to javafx.fxml;
    exports com.darkstudio.messenger_v5;
}