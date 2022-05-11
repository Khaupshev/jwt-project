Сервис включающий в себя следующие функции:
- генерация JWT-токенов
- регистрация пользователя
- аутентификация пользователя
- отправка сообщений пользователю

Для того, чтобы развернуть данный сервис локально на докере необходимо скачать проект. 
Далее в корневой папке открыть терминал и запустить maven:
    mvn clean install -DskipTests=true
Данная команда очистит и соберет проект, тесты скипаем. 

Теперь запускаем docer-compose файл:
docker-compose up

После загрузки мы сможем обращаться к докер контейнеру по порту 9090.
ВАЖНО!!! 
Если Вы хотите запустить приложение локально без докера, обращаться на порт 9000.

Ниже представлены curl-запросы:
1.	Регистрируем пользователя с ролью USER:

    curl --location --request POST 'http://localhost:9090/api/v1/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName": "user1",
"firstName": "1",
"lastName": "2",
"email": "1",
"role": "USER",
"password": "1"
}'
2.	Регистрация пользователя с ролью ADMIN:
    
    curl --location --request POST 'http://localhost:9090/api/v1/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName": "user2",
"firstName": "1",
"lastName": "2",
"email": "2",
"role": "ADMIN",
"password": "1"
}'
3.	Авторизация пользователя:
    
    curl --location --request POST 'http://localhost:9090/api/v1/sign-in' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName": "user1",
"password": "1"
}'
4.	Запросы выше возвращают jwt-токен, его необходимо будет скопировать и использовать в следующих запросах вместо <token>
5.	Отправить сообщения пользователю:

    curl --location --request POST 'http://localhost:9090/api/v1/user/message' \
--header 'Authorization: Bearer_\<token\>' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName": "user1",
"messages": [
"1",
"2",
"3",
"4",
"5",
"6",
"10"
]
}'
6.	Получить историю сообщений:

    curl --location --request GET 'http://localhost:9090/api/v1/user/history' \
--header 'Authorization: Bearer_\<token\>' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName": "user1",
"countMessages": 10
}'
7.	Запрос администратора, получить всех пользователей (следовательно необходимо использовать токен, полученный при регистрации пользователя с ролью ADMIN, 2 запрос)

    curl --location --request GET 'http://localhost:9090/api/v1/admin/all' \
--header 'Authorization: Bearer_\<token\>'

