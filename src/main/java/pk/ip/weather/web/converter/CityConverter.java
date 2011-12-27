package pk.ip.weather.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import pk.ip.weather.domain.City;
import pk.ip.weather.service.WeatherService;

public class CityConverter implements Converter<String, City>
{
    private WeatherService service;
    
    public CityConverter(WeatherService service)
    {
        this.service = service;
    }

    @Override
    public City convert(String id)
    {
        try
        {
            return service.findCity(Long.valueOf(id));
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException(e);
        }
        catch(EmptyResultDataAccessException e)
        {
            throw new IllegalArgumentException(e);
        }
    }
}
