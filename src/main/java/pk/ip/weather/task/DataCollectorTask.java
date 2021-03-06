package pk.ip.weather.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import pk.ip.weather.api.wunderground.WundergroundService;
import pk.ip.weather.api.wunderground.model.forecast.ForecastDay;
import pk.ip.weather.api.wunderground.model.forecast.SimpleForecast;
import pk.ip.weather.api.wunderground.model.history.History;
import pk.ip.weather.api.wunderground.model.history.Observation;
import pk.ip.weather.api.wunderground.model.history.SummaryObservation;
import pk.ip.weather.domain.City;
import pk.ip.weather.domain.Forecast;
import pk.ip.weather.domain.Observation.Type;
import pk.ip.weather.service.WeatherService;

public class DataCollectorTask
{
    private Logger logger = Logger.getLogger(DataCollectorTask.class);
    
    private WundergroundService service;
    private WeatherService weaterService;
    
    public DataCollectorTask(WeatherService weaterService, WundergroundService service)
    {
        this.service = service;
        this.weaterService = weaterService;
    }
    
    public void collestYesterdayHistory()
    {
        logger.debug("Rozpoczęto zadanie collestYesterdayHistory");
               
        Date date = weaterService.findTheNewestHistoryDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        date = calendar.getTime();
        
        Date today = new Date();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        today = calendar.getTime();
        
        if(today.after(date))
        {
            collectWeatherHistory(date);
        }
        else
        {
            logger.debug("Dane historyczne wczorajsze zostały już wcześniej zaktualizowane");
        }
        
        logger.debug("Zakończono zadanie collestYesterdayHistory");
    }
    
    public void collectWeatherHistory(Date date)
    {
        logger.debug("Rozpoczęto zbieranie danych historycznych dla daty: "+date);
        
        List<City> cities = weaterService.findCities();
        
        Set<pk.ip.weather.domain.Observation> observations = findObservations(date, cities);
        
        logger.debug("Aktualizacja bazy danych");
        weaterService.insertObservations(observations);
        
        logger.debug("Zakończono zbieranie danych historycznych");
    }
    
    public void collectWeatherHistory()
    {
        Date date = weaterService.findTheEarliestHistoryDate();
        collectWeatherHistory(date);
    }
    
    private Set<pk.ip.weather.domain.Observation> findObservations(Date date, List<City> cities)
    {
        Set<pk.ip.weather.domain.Observation> observations = new HashSet<pk.ip.weather.domain.Observation>();
        for(City city : cities)
        {
            History history = service.findHistory(date, city.getName());
            
            for(Observation observation : history.observations)
            {
                pk.ip.weather.domain.Observation domainObservation = createObservation(observation, city, pk.ip.weather.domain.Observation.Type.DETAIL);
                observations.add(domainObservation);
            }
            
            for(SummaryObservation observation : history.dailysummary)
            {
                pk.ip.weather.domain.Observation domainObservation = createObservation(observation, city, Type.SUMMARY, date);
                observations.add(domainObservation);
            }
            
            logger.debug("Pobrano dane historyczne dla miasta: "+city);
        }
        
        return observations;
    }
    
    private pk.ip.weather.domain.Observation createObservation(Observation observation, City city, pk.ip.weather.domain.Observation.Type type)
    {
        pk.ip.weather.domain.Observation domainObservation = new pk.ip.weather.domain.Observation();

        domainObservation.setType(type);
        domainObservation.setCity(city);
        domainObservation.setFog(observation.fog);
        domainObservation.setHail(observation.hail);
        domainObservation.setHumidity(observation.hum);
        domainObservation.setPressure(observation.pressurem);
        domainObservation.setRain(observation.rain);
        domainObservation.setSnow(observation.snow);
        domainObservation.setTemperature(observation.tempm);
        domainObservation.setThunder(observation.thunder);
        domainObservation.setTornado(observation.tornado);
        domainObservation.setVisibility(observation.vism);
        domainObservation.setWindSpeed(observation.wspdm);
        domainObservation.setWindchillTemperature(observation.windchillm);

        Date date = createDate(observation.date.year, observation.date.mon, observation.date.mday, observation.date.hour, observation.date.min);
        
        domainObservation.setDate(date);
        
        return domainObservation;
    }
    
    private Date createDate(String year, String month, String day, String hour, String minute)
    {
        return createDate(getInteger(year), getInteger(month) - 1, getInteger(day), getInteger(hour), getInteger(minute), 0);
    }
    
    private Date createDate(int year, int month, int day, int hour, int minute, int seconds)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day, hour, minute, seconds);
        
        return calendar.getTime();
    }
    
    private int getInteger(String s)
    {
        s = s.trim();
        if(s.charAt(0) == '0' && s.length() > 1)
        {
            s = s.substring(1, s.length());
        }
        
        return Integer.parseInt(s);
    }
    
    private pk.ip.weather.domain.Observation createObservation(SummaryObservation observation, City city, pk.ip.weather.domain.Observation.Type type, Date date)
    {
        pk.ip.weather.domain.Observation domainObservation = new pk.ip.weather.domain.Observation();

        domainObservation.setType(type);
        domainObservation.setCity(city);
        domainObservation.setFog(observation.fog);
        domainObservation.setHail(observation.hail);
        domainObservation.setHumidity(observation.humidity);
        domainObservation.setPressure(observation.meanpressurem);
        domainObservation.setRain(observation.rain);
        domainObservation.setSnow(observation.snow);
        domainObservation.setTemperature(observation.meantempm);
        domainObservation.setThunder(observation.thunder);
        domainObservation.setTornado(observation.tornado);
        domainObservation.setVisibility(observation.meanvism);
        domainObservation.setWindSpeed(observation.meanwindspdm);

        domainObservation.setDate(date);
        
        return domainObservation;
    }
    
    public void collectForecast()
    {
        logger.debug("Rozpoczęto zadanie collectForecast");
        
        List<City> cities = weaterService.findCities();
        Set<Forecast> forecasts = new HashSet<Forecast>();
        
        for(City city : cities)
        {
            logger.debug("Pobieranie prognozy dla miasta: "+city);
            SimpleForecast simpleForecast = service.findForecast(city.getName());
            
            for(ForecastDay forecastDay : simpleForecast.forecastday)
            {
                Forecast forecast = createForecast(forecastDay, city);
                forecasts.add(forecast);
            }
        }
        
        logger.debug("Aktualizacja bazy danych");
        weaterService.replaceForecast(forecasts);
        
        logger.debug("Zakończono zadanie collectForecast");
    }

    private Forecast createForecast(ForecastDay forecastDay, City city)
    {
        Forecast forecast = new Forecast();
        
        forecast.setCity(city);
        
        Date date = createDate(forecastDay.date.year, forecastDay.date.month, forecastDay.date.day, forecastDay.date.hour, forecastDay.date.min);
        forecast.setDate(date);
        
        forecast.setHumidity(forecastDay.avehumidity);
        forecast.setMaxTemperature(forecastDay.high.celsius);
        forecast.setMinTemperature(forecastDay.low.celsius);
        
        forecast.setRainAll(forecastDay.qpf_allday.mm);
        forecast.setRainDay(forecastDay.qpf_day.mm);
        forecast.setRainNight(forecastDay.qpf_night.mm);
        
        forecast.setSnowAll(forecastDay.snow_allday.cm);
        forecast.setSnowDay(forecastDay.snow_day.cm);
        forecast.setSnowNight(forecastDay.snow_night.cm);
        
        forecast.setWindSpeed(forecastDay.avewind.kph);
        
        return forecast;
    }
}
