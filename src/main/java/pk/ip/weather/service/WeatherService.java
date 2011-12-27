package pk.ip.weather.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pk.ip.weather.core.DateInterval;
import pk.ip.weather.domain.City;
import pk.ip.weather.domain.Forecast;
import pk.ip.weather.domain.Observation;

public interface WeatherService
{
    public void insertObservation(Observation observation);
    public void insertObservations(Set<Observation> observations);
    public Date findTheEarliestHistoryDate();
    public List<City> findCities();
    public City findCity(Long id);
    public Date findTheNewestHistoryDate();
    public void replaceForecast(Set<Forecast> forecasts);
    public Map<String, Float> findHistoricalData(City city, DateInterval interval, StatisticsType type, Period period);
    public List<Forecast> findForecast(City city);
}
