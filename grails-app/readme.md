Para ejecutar esta app se debe levantar una base de datos MySQL. Dejo los comandos>

mysql> CREATE DATABASE arquiweb_db;

mysql> INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('arquiweb_ds', 'localhost', PASSWORD('password'),'','','');

mysql> GRANT ALL PRIVILEGES ON arquiweb_db.* to 'arquiweb_ds'@localhost;

mysql> FLUSH PRIVILEGES;


-- Para la version productiva...

mysql> CREATE DATABASE arquiweb_db_prod;

mysql> INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('arquiweb_ds', 'localhost', PASSWORD('password'),'','','');
mysql> INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('arquiweb_ds', '35.231.83.74', PASSWORD('password'),'','','');

mysql> GRANT ALL PRIVILEGES ON arquiweb_db_prod.* to 'arquiweb_ds'@localhost;

mysql> FLUSH PRIVILEGES;


