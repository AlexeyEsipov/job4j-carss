![github actions](https://github.com/AlexeyEsipov/job4j-carss/actions/workflows/maven.yml/badge.svg)

# job4j_cars
Это приложение для создания площадки продажи машин.
## Техническое задание на проект содержит такие требования:
1. Основная страница - таблица со всеми объявлениям машин на продажу.
2. На странице должна быть кнопка - добавить новое объявление.
3. Переходить на страницу добавления.
4. Должны быть категории машины, марка, тип кузова и тд. Пример с сайта auto.ru.
5. Можно добавлять фото.
6. Объявление имеет статус продано или нет.
7. Должны существовать пользователи. Пользователь, подавший объявление,
   только он может менять статус.
8. 
## Используемые технологии:
![java](https://img.shields.io/badge/Java--17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot--2.7.3-F2F4F9?style=for-the-badge&logo=spring-boot)
![Bootstrap](https://img.shields.io/badge/Bootstrap--5.2.2-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL--14-316192?style=for-the-badge&logo=postgresql&logoColor=white)
[![Hibernate](https://img.shields.io/badge/Hibernate--5.6.11.Final-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.0.RELEASE-blue?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Liquibase](https://img.shields.io/badge/Liquibase-4.9.1-red?style=for-the-badge&logo=liquibase&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18.24-green?style=for-the-badge&logo=lombok&logoColor=white)

Перед запуском установите:
- PostgreSQL 14
- Java 17
- Apache Maven 3.x

## Запуск приложения

1. Создайте базу данных carrs:
```sql
create database carrs;
```

2. Запуск приложения производится с использованием maven.
   Перейдите в корневой каталог проекта и в командной строке
   выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```
### Описание:
Перед использованием приложения надо пройти регистрацию
![index page](images/Registration.JPG)
Пользователь заполняет поля
![index page](images/RegistrationData.JPG)
и попадает на страницу авторизации,
![index page](images/LoginEmpty.JPG)
где вводит свои регистрационные данные.
![index page](images/LoginData.JPG)
После регистрации пользователю доступны 
объявления, которые находятся в системе.
![index page](images/OtherAuto.JPG)
Можно отобрать объявления по их статусу,
![index page](images/Status.JPG)
типу автомобилей,
![index page](images/Type.JPG)
пробегу,
![index page](images/MileAge.JPG)
настроить свои фильтры для отображения.
![index page](images/Filter.JPG)
Можно разместить свое объявление.
![index page](images/MyAds.JPG)
И посмотреть список объявлений после добавления
![index page](images/AfterAdd.JPG)
При нажатии на фотографию машины можно посмотреть детали объявления.
![index page](images/Details.JPG)

Связаться со мной можно по электронной почте a_esipov_it@list.ru
или в телеграм  @Alex46volokno


<div id="socials" align="center">
    <!-- <a href="linkedin-url">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a> -->

  <a href="https://t.me/alex46volokno">
    <img src="https://img.shields.io/badge/Telegram-blue?style=for-the-badge&logo=telegram&logoColor=white" alt="Telegram"/>
  </a>
</div>







