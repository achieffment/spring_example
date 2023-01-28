<h1>Пример работы с Java Spring (thymeleaf) и Hibbernate</h1>
<p>База данных строится на основе подключения файла конфигурации (postgresql) - <br>\src\main\java\com\vyatsu\task14\hibernate.cfg.xml и \src\main\resources\application.properties<br>
и создается на основе сущности - <br>\src\main\java\com\vyatsu\task14\entities\Product.java</p>
<p>Управление сущностью производится с помощью сервиса - \src\main\java\com\vyatsu\task14\services\ProductsService.java<br>
Который взаимодействует с репозиторием (объектом, который предоставляет возможности управления сущностью в базе данных) - <br>
\src\main\java\com\vyatsu\task14\repositories\ProductRepository.java</p>
<p>Страницы отображаются в браузере и выдача их управляется контроллером, данные отправляются посредством шаблонизатора thymeleaf - <br>\src\main\java\com\vyatsu\task14\controllers\ProductsController.java</p>
<p>Адрес проекта в браузере (сначала запустить) - http://localhost:8189/</p>