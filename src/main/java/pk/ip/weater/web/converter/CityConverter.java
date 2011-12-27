package pk.ip.weater.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import pk.ip.weater.domain.City;
import pk.ip.weater.service.WeaterService;

public class CityConverter implements Converter<String, City>
{
    private WeaterService service;
    
    public CityConverter(WeaterService service)
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
