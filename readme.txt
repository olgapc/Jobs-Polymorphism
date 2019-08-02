Fichero properties configuración de BD:

spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/it_project?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

Dependencias añadidas a build.gradle:

compile 'mysql:mysql-connector-java'
implementation 'com.google.code.gson:gson:2.8.5'

Carpeta src/main/resources:

DB_script: script para crear la BD
populate_db: script para poblar la BD