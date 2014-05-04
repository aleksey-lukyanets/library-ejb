<p>Библиотека на основе Enterprise JavaBeans и JavaServer Faces,
    так же как на Spring MVC, позволяет хранить, просматривать,
    изменять списки авторов и книг в базе данных.</p>
<p>Для асинхронного обращения к серверу в этой реализации применён встроенный в JSF
    AJAX-компонент <code>f:ajax</code>. По этой причине отличается стратегия обновления
    пользовательского интерфейса: в ответ на действие пользователя у сервера
    запрашиваются изменившиеся элемента данных — вместо изменения элементов интерфейса
    средствами JavaScript, как это было в случае приложения на Spring. Соответственно,
    функции JavaScript здесь невелики и немногочисленны.</p>

<h4 class="modal-title">Технологии</h4>

<ul class="discharged">
    <li>сервлет: EJB 3, JSF 2.2;</li>
    <li>доступ к данным: JPA, Hibernate;</li>
    <li>веб-сервис: JAX-RS, RESTEasy;</li>
    <li>веб-интерфейс: jQuery, Bootstrap;</li>
    <li>база данных: PostgreSQL;</li>
    <li>серверы приложений: JBoss, GlassFish.</li>
</ul>

<h4 class="modal-title">Веб-сервис REST</h4>

<p>Веб-сервис реализован средствами Java API for RESTful Web Services (JAX-RS),
    он позволяет запрашивать и изменять предоставленные приложением ресурсы
    в форматах JSON и XML. Веб-сервис доступен по пути <code>/rest/</code>,
    адреса ресурсов и ответы в целом аналогичны Spring-реализации.</p>
<p>Пример добавления автора запросом к ресурсу: <code>POST /rest/authors</code> в формате XML:</p>
<pre style="width: 60%;">заголовок:   Content-Type: application/xml<br/>             Accept: application/xml<br/>тело:        &lt;author&gt;<br/>                 &lt;id&gt;0&lt;/id&gt;<br/>                 &lt;country&gt;Россия&lt;/country&gt;<br/>                 &lt;name&gt;Лев Толстой&lt;/name&gt;<br/>             &lt;/author&gt;</pre>
<p>Ответ приложения:</p>
<pre style="width: 100%;">заголовок:   Status Code: 201 Created<br/>             Content-Type: application/xml<br/>             Location: http://library.jelasticloud.com/library/rest/authors/11<br/>тело:        &lt;author&gt;<br/>                 &lt;id&gt;11&lt;/id&gt;<br/>                 &lt;country&gt;Россия&lt;/country&gt;<br/>                 &lt;name&gt;Лев Толстой&lt;/name&gt;<br/>             &lt;/author&gt;</pre>
