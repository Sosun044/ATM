package com.muhammedsosun.atm.utils;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ResourceBundle;
/**
 * Uygulamada çok dilli destek (i18n) sağlamak amacıyla kullanılan yardımcı sınıftır.
 *
 * Bu sınıf, JavaFX'in Property ve Binding altyapısını kullanarak,
 * uygulamanın farklı noktalarındaki metinleri merkezi bir dil dosyasından (ResourceBundle)
 * dinamik olarak almaya ve takip etmeye olanak tanır.
 *
 * - İçerisinde ObjectProperty türünde bir ResourceBundle tutulur.
 * - UI bileşenleri (Label, Button, vs.) bu sınıftan alınan StringBinding nesnelerine bağlanır.
 * - setResources(...) metodu ile seçilen dil değiştirildiğinde,
 *   bağlı olan tüm bileşenler otomatik olarak güncellenir.
 *
 * Örnek kullanım:
 * Label bir etiket:
 *   label.textProperty().bind(observableResourceFactory.getStringBinding("greeting"));
 *
 * Daha sonra uygulama içerisinde:
 *   observableResourceFactory.setResources(ResourceBundle.getBundle("messages", new Locale("en")));
 * çağrıldığında etiketin metni otomatik olarak İngilizceye döner.
 */

public class ObservableResourceFactory {

    // ResourceBundle'ı tutan property (değiştirilebilir ve izlenebilir)
    private final ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();


    public ResourceBundle getResources() {
        return resources.get();
    }


    public void setResources(ResourceBundle bundle) {
        resources.set(bundle);
    }

    // property erişimi (JavaFX'in bind() için)
    public ObjectProperty<ResourceBundle> resourcesProperty() {
        return resources;
    }

    // Belirli bir anahtar için binding oluşturur
    public StringBinding getStringBinding(String key) {
        return new StringBinding() {
            {
                // Burada anonymous subclass'ın içinde bind() çağrabilirsin
                bind(resources);
            }

            @Override
            protected String computeValue() {
                ResourceBundle bundle = getResources();
                if (bundle != null && bundle.containsKey(key)) {
                    return bundle.getString(key);
                } else {
                    return "???" + key + "???";
                }
            }
        };
    }

}
