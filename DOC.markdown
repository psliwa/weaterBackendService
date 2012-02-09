Aplikacja jest częścią projektu akademickiego

Wprowadzenie
============

Aplikacja ta pełni funkcję serwera dostarczającego statystycznych danych pogodowych w formacje json swoim klientom. Dane pogodowe są zbierane z zewnętrznej usługi dostarczanej przez serwis wunderground.com.

Zbieranie danych pogodowych
===========================

Klasa pk.ip.weather.api.wunderground.WundergroundService jest fasadą usługi dostarczanej przez serwis wunderground.com. Dwie publiczne metody tej klasy to findHistory (znalezienie danych historycznych dla danej lokalizacji i daty) oraz findForecast (znalezienie prognozy pogody dla danej lokalizacji).

Dane pobrane z wunderground są zbierane przez zadanie w osobnym wątku (klasa pk.ip.weather.task.DataCollectorTask) oraz zapisywane do bazy danych. Klasa która zapisuje te dane do bazy to pk.ip.weather.service.WeatherServiceImpl.

Udostępnianie danych pogodowych
===============================

Dane pogodowe są udostępniane na zewnątrz w formacie json, do tego zadania został wykorzystany Spring MVC. Kontroler (pk.ip.weather.web.controller.WeatherController) pobiera żądanie przez klienta dane z bazy danych (klasa pk.ip.weather.service.WeatherServiceImpl) i umieszcza je w widoku. Widokiem w naszym przypadku jest klasa MappingJacksonJsonView. Do konwersji obiektów na dokument json została wykorzystana biblioteka jackson.

Technologia
===========

Aplikacja została napisana w Javie EE, framework Spring oraz kilka innych narzędziowych bibliotek.

Opis api
========

Z usługi można wyciągnąć dane historyczne oraz prognozę pogody.

Żądanie o dane historyczne:
---------------------------

http://adres/uslugi/history/{dateStart}/{dateEnd}/{cityId}/{type}/{period}

* dateStart - data początkowa w formacie ISO - YYYY-MM-DD
* dateEnd - data końcowa
* cityId - id miasta jako liczba całkowita
* type - typ danych statystycznych: TEMPERATURE, PRESSURE, WIND_SPEED, HUMIDITY, VISIBILITY, FOG, RAIN, SNOW, THUNDER (wielkość znaków ma znaczenie)
* period - rodzaj grupowania: DAY, MONTH, YEAR

**Przykład**: http://adres/uslugi/history/2011-01-01/2011-12-31/1/TEMPERATURE/MONTH

Żądanie o prognozę pogody:
--------------------------

http://adres/uslugi/forecast/{cityId}

Żądanie o dostępną listę miast:
-------------------------------

http://adres/uslugi/cities

Instalacja
==========

Aplikację należy uruchomić na dowolnym serwerze www (np. Tomcat, czy GlassFish). Musi być włączona baza danych mysql o nazwie weater, użytkownik weater, hasło pk2011. Do bazy należy wgrać schemat który znajduje się w pliku src/main/resources/schema.sql oraz dane z pliku src/main/resources/data.sql. Projekt jest projektem mavenowym, czyli należy pobrać wszystkie zależności.
