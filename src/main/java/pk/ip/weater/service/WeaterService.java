package pk.ip.weater.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import pk.ip.weater.domain.City;
import pk.ip.weater.domain.Forecast;
import pk.ip.weater.domain.Observation;

public interface WeaterService
{
    public void insertObservation(Observation observation);
    public void insertObservations(Set<Observation> observations);
    public Date findTheEarliestHistoryDate();
    public List<City> findCities();
    public Date findTheNewestHistoryDate();
    public void replaceForecast(Set<Forecast> forecasts);
}
