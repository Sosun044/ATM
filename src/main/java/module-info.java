module com.muhammedsosun.atm {
    requires javafx.controls;
    requires javafx.fxml;
  //  requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
  //  requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.apache.poi.poi;
    
    opens com.muhammedsosun.atm to javafx.fxml;
    exports com.muhammedsosun.atm;
}
/*

    // Controller sınıfları FXML tarafından kullanılacağı için açılması gerekiyor.
    opens com.muhammedsosun.atm.controller to javafx.fxml;

    // DAO (Data Access Object) sınıfları, SQL bağlantısı kullandığı için açılıyor.
    opens com.muhammedsosun.atm.dao to java.sql;

    // Veritabanı bağlantısı sağlayan sınıfların da SQL modülüne açık olması gerekiyor.
    opens com.muhammedsosun.atm.database to java.sql;



 // #####################################################################
    // DAO sınıflarını dışarıya açıyoruz. Böylece başka modüller veritabanı işlemlerini çağırabilir.
    exports com.muhammedsosun.atm.dao;

    // // Veritabanı bağlantı paketini dış dünyaya açıyoruz. Diğer modüller DB bağlantısını kullanabilir.
    exports com.muhammedsosun.atm.database;
}*/



/*Default*/
/*module com.muhammedsosun.atm {
        requires javafx.controls;
        requires javafx.fxml;
        //requires javafx.web;

        requires org.controlsfx.controls;
        requires com.dlsc.formsfx;
        requires net.synedra.validatorfx;
        requires org.kordamp.ikonli.javafx;
        requires org.kordamp.bootstrapfx.core;
        requires static lombok;
        requires java.sql;
        requires java.desktop;
        //requires eu.hansolo.tilesfx;

        opens com.muhammedsosun.atm to javafx.fxml;
        exports com.muhammedsosun.atm;
        }*/