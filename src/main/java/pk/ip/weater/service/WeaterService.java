package pk.ip.weater.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pk.ip.weater.core.DateInterval;
import pk.ip.weater.domain.City;
import pk.ip.weater.domain.Forecast;
import pk.ip.weater.domain.Observation;

public interface WeaterService
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
