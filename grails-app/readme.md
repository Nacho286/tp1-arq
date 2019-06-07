Para ejecutar esta app se debe levantar una base de datos MySQL. Dejo los comandos>

mysql> CREATE DATABASE arquiweb_db;

mysql> INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('arquiweb-ds', 'localhost', PASSWORD('password'),'','','');

mysql> GRANT ALL PRIVILEGES ON arquiweb_db.* to 'arquiweb_ds'@localhost;

mysql> FLUSH PRIVILEGES;


