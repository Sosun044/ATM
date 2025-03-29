JavaFX ATM Projesi

Bu proje, JavaFX kullanarak geliştirilmiş bir ATM simülasyonudur. Kullanıcıların giriş yapmasını, para işlemleri gerçekleştirmesini ve KDV hesaplamalarını yapmasını sağlar.

Özellikler (Features)

Kullanıcı kaydı ve giriş sistemi (User Registration & Login System)

H2 Database ile veritabanı yönetimi (H2 Database Management)

Kullanıcı ve KDV işlemleri için DAO katmanı (DAO Layer for User & KDV Operations)

Şifre güvenliği için BCrypt kullanımı (Password Security with BCrypt)

JavaFX tabanlı görsel arayüz (JavaFX-based GUI)

Kullanıcı rollerine göre yetkilendirme (Role-based Authorization)

Kullanılan Teknolojiler (Technologies Used)

Java 17+

JavaFX (FXML ile arayüz tasarımı)

H2 Database (Hafif veritabanı yönetimi)

BCrypt (Şifre güvenliği için)

Maven (Bağımlılık yönetimi)

Singleton Pattern (Veritabanı bağlantısı yönetimi için)

Kurulum (Installation)

Projeyi klonlayın:

git clone https://github.com/username/atm-javafx.git

Maven bağımlılıklarını yükleyin:

mvn clean install

Uygulamayı çalıştırın:

mvn javafx:run

Veritabanı Yapılandırması (Database Configuration)

Proje H2 Database kullanmaktadır.

h2db_data.sql dosyası, veritabanının başlangıç verilerini içerir.

Varsayılan bağlantı bilgileri:

JDBC URL: jdbc:h2:~/atm_db
Kullanıcı Adı: admin
Şifre: admin

Veritabanı dosyaları database klasöründe bulunmaktadır.
