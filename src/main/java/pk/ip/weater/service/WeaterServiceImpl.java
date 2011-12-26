package pk.ip.weater.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pk.ip.weater.domain.City;
import pk.ip.weater.domain.Observation;

public class WeaterServiceImpl implements WeaterService
{
    private final static String INSERT_QUERY = "insert into observation values(:city.id, :date, :type.value, :windSpeed, :temperature, :windchillTemperature, :humidity, :visibility, :pressure, :fog, :rain, :snow, :hail, :thunder, :tornado)";
    
    private NamedParameterJdbcTemplate template;    

    public WeaterServiceImpl(DataSource dataSource)
    {
        template = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public void insertObservation(Observation observation)
    {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(observation);;

        try
        {
            template.update(INSERT_QUERY, parameters);
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
}
