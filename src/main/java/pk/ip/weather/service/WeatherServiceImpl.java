package pk.ip.weather.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pk.ip.weather.core.DateInterval;
import pk.ip.weather.domain.City;
import pk.ip.weather.domain.Forecast;
import pk.ip.weather.domain.Observation;

public class WeatherServiceImpl implements WeatherService
{
    private final static String INSERT_OBSERVATION_QUERY = "insert into observation values(:city.id, :date, :year, :month, :dayOfMonth, :type.value, :windSpeed, :temperature, :windchillTemperature, :humidity, :visibility, :pressure, :fog, :rain, :snow, :hail, :thunder, :tornado)";
    private final static String INSERT_FORECAST_QUERY = "insert into forecast values(:city.id, :date, :maxTemperature, :minTemperature, :windSpeed, :humidity, :snowAll, :snowDay, :snowNight, :rainAll, :rainDay, :rainNight)";
    
    private NamedParameterJdbcTemplate template;    

    public WeatherServiceImpl(DataSource dataSource)
    {
        template = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public void insertObservation(Observation observation)
    {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(observation);;

        try
        {
            template.update(INSERT_OBSERVATION_QUERY, parameters);
        }
        catch(DuplicateKeyException e)
        {
            //duplikacja, ignoruj
        }
    }
    
    @Override
    public void insertObservations(Set<Observation> observations)
    {
        for(Observation observation : observations)
        {
            insertObservation(observation);
        }
    }
    
    @Override
    public Date findTheEarliestHistoryDate()
    {
        Date earliestDate = template.getJdbcOperations().queryForObject("SELECT MIN(date) from observation", Date.class);
        
        if(earliestDate == null)
        {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            
            return calendar.getTime();
        }
        else
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(earliestDate);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            
            return calendar.getTime();
        }
    }
    
    @Override
    public Date findTheNewestHistoryDate()
    {
        Date newestDate = template.getJdbcOperations().queryForObject("SELECT MAX(date) from observation", Date.class);
        
        if(newestDate == null)
        {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            
            return calendar.getTime();
        }
        else
        {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(newestDate);
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            
            return calendar.getTime();
        }
    }

    @Override
    public List<City> findCities()
    {
        return template.getJdbcOperations().query("select id, name from city", new CityMapper());
    }

    private static class CityMapper implements RowMapper<City>
    {
        @Override
        public City mapRow(ResultSet rs, int i) throws SQLException
        {
            City city = new City();
            city.setId(rs.getLong("id"));
            city.setName(rs.getString("name"));
            
            return city;
        }
    }
    
    @Override
    public City findCity(Long id)
    {
        return template.getJdbcOperations().queryForObject("select id, name from city where id=?", new Object[] { id }, new CityMapper());
    }
    
    @Override
    public void replaceForecast(Set<Forecast> forecasts)
    {
        template.getJdbcOperations().update("TRUNCATE forecast");
        
        for(Forecast forecast : forecasts)
        {
            try
            {
                SqlParameterSource parameters = new BeanPropertySqlParameterSource(forecast);
                template.update(INSERT_FORECAST_QUERY, parameters);
            }
            catch(DuplicateKeyException e)
            {
                //duplikacja, ignoruj
            }
        }
    }
          
    @Override
    public Map<String, Float> findHistoricalData(City city, DateInterval interval, StatisticsType type, Period period)
    {
        String query = "SELECT "+buildSelect(type, period)+" FROM `observation` WHERE `type`=? AND `cityId`=? AND `"+type.getProperty()+"` IS NOT NULL AND `date` BETWEEN ? AND ? GROUP BY "+period.getProperty()+" ORDER BY `date`";
        Object[] parameters = new Object[] { Observation.Type.SUMMARY.toString(), city.getId(), interval.getStart(), interval.getEnd() };
        
        return template.getJdbcOperations().query(query, parameters, new ResultSetExtractor<Map<String, Float>>(){

            @Override
            public Map<String, Float> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                Map<String, Float> map = new LinkedHashMap<String, Float>();
                
                while(rs.next())
                {
                    map.put(rs.getString("key"), rs.getFloat("value"));
                }
                
                return map;
            }
        });
    }
    
    private String buildSelect(StatisticsType type, Period period)
    {
        String operation = type.getOperation().toString();
        String property = type.getProperty();
        
        return operation+"(`"+property+"`) AS `value`, "+period.getProperty()+" AS `key`";
    }
    
    @Override
    public List<Forecast> findForecast(City city)
    {
        return template.getJdbcOperations().query("select * from forecast where cityId=? order by `date`", new Object[] { city.getId() }, new ForecastMapper(city));
    }
    
    private static class ForecastMapper implements RowMapper<Forecast>
    {
        private City city;
        
        ForecastMapper(City city)
        {
            this.city = city;
        }
        @Override
        public Forecast mapRow(ResultSet rs, int i) throws SQLException
        {
            Forecast forecast = new Forecast();
            forecast.setDate(rs.getDate("date"));
            forecast.setHumidity(rs.getFloat("humidity"));
            forecast.setMaxTemperature(rs.getFloat("maxTemperature"));
            forecast.setMinTemperature(rs.getFloat("minTemperature"));
            forecast.setRainAll(rs.getFloat("rainAll"));
            forecast.setRainDay(rs.getFloat("rainDay"));
            forecast.setRainNight(rs.getFloat("rainNight"));
            forecast.setSnowAll(rs.getFloat("snowAll"));
            forecast.setSnowDay(rs.getFloat("snowDay"));
            forecast.setSnowNight(rs.getFloat("snowNight"));
            forecast.setWindSpeed(rs.getFloat("windSpeed"));
            forecast.setCity(city);
            
            return forecast;
        }
    }
}
