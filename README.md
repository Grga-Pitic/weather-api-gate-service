Weather REST Service
====================
Веб-сервис, позволяющий централизованно и в едином формате получать данные о погоде из различных открытых API. 
Взаимодействие с сервисом происходит через REST API. Кроме того, имеется веб-интерфейс.

Предварительные условия
-----------------------

Для запуска проекта вам понадобятся:
- Docker
- Apache Maven

Быстрый старт
-------------

Сконфигурируйте файл weather-api-gate-service/src/main/resources/application.properties

Для запуска из docker-контейнера можно использовать следующую конфигурацию:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.dbcp2.driver-class-name= com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://172.19.0.1:3306/test?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.mvc.view.suffix=.html
```


После чего выполните в консоли следующие команды:

Для ОС Windows:
```
mvnw.cmd package -DskipTests
docker-compose build
docker-compose up
```

Для GNU/Linux:
```
mvnw package -DskipTests
docker-compose build
docker-compose up
```

Открыть веб-интерфейс можно по адресу:
```
http://localhost:8080/
```