# Демопроект автоматизации тестирования API сервиса для приёма платежей [ЮKassa](https://yookassa.ru/)

## :open_book: Содержание:

- [Технологии и инструменты](#gear-технологии-и-инструменты)
- [Тест кейсы](#heavy_check_mark-Тест-кейсы)
- [Запуск тестов](#computer-запуск-тестов-из-терминала)
- [Примеры использования](#примеры-использования)
- [Запуск тестов в Jenkins](#-запуск-тестов-из-jenkins)
- [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-Allure-report)

## :gear: Технологии и инструменты

<p align="left">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50" alt="Java" title="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50" alt="Github" title="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50" alt="JUnit 5" title="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50" alt="Gradle" title="Gradle"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/RestAssured.svg" width="50" height="50" alt="RestAssured" title="RestAssured"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50" alt="Allure" title="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50" alt="Jenkins" title="Jenkins"/></a>

</p>

В данном проекте автотесты написаны на **Java** . Для сборки проекта в среде **IntelliJ IDEA** используется **Gradle**.
**JUnit5** задействован в качестве фреймворка модульного тестирования. 

**Allure Report** и **Telegram Bot** используются для визуализации результатов тестирования.

## :heavy_check_mark: Позитивные тест кейсы оплаты банковской карты

- Проверка подтверждения успешной оплаты
- Проверка отклонения отплаты
- Проверка запроса информации по оплате

## :heavy_check_mark: Негативные тест кейсы оплаты банковской карты

- Проверка карты с истекшим сроком

## :heavy_check_mark: Аутентификация:
Для аутентификации запросов необходимо использовать HTTP Basic Auth.
Ссылка на документацию: https://yookassa.ru/developers/using-api/interaction-format#auth
```
static String originalInput = "Идентификатор магазина:Секретный ключ";
static String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes(UTF_8));
```

## :computer: Запуск тестов из терминала

### :house_with_garden:	Локальный запуск тестов

```bash
gradle clean test
```
### :earth_asia: Запуск smoke тестов
```bash
gradle clean smoke
```
### :earth_asia: Запуск позитивных тестов
```bash
gradle clean positive
```
