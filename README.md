# bankapp
Приложение написано за 3 дня.
Функционально выполняет требования. Фронт примитивен. Чуть хромает валидация.
Использованы следующие технологии
Java 8, Spring Boot 2.0, Hibernate 5.0, Thymeleaf, Bootstrap.

Требования по заданию

Написать веб-приложение, выполняющее функции банка. Пусть это будет банк в городе N насчитывающем около 50 жителей.
Нужно хранить данные о:
клиентах банка (идентификатор, имя — адрес, возраст);
счетах (идентификатор, идентификатор владельца, количество денег);
транзакциях — переводах денег между счетами или их поступлениях / списаниях.

Приложение должно содержать 3 страницы и дополнительные формы:
список клиентов банка, при щелчке по имени открываются счета выбранного клиента (плюс внизу форма для добавления нового клиента);
список счетов заданного клиента (плюс внизу форма для добавления нового счёта);
форма для перевода денег между счетами (и для пополнения / списания денег: это могут быть отдельные формы, на ваш вкус);
страница со списком транзакций (сверху форма фильтра, чтобы можно было выбрать за период и/или по заданному пользователю);
дополнения — на ваш вкус, например, в списке клиентов можно сумму на всех счетах у каждого выводить и т. п.

Задание должно быть выполнено с применением технологий, инструментов и библиотек:
Spring-boot
база данных MySQL

Везде в формы указываются id цифровые.

MySql user=root; password=root; 
И еще создать в БД MySql scheme под названием bankapp.

Для запуска приложения необходимо сделать git pull проекта.
Открыть cmd строку и в ней перейти в директорию где находится проект.
В коммандной строке выполнить mvn compile.
После этого выполнить команду mvnw spring-boot:run
После этого на адресе http://localhost:8080/index (можно без /index)
будет запущено приложение.


