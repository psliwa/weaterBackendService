package pk.ip.weather.api.wunderground;

import pk.ip.weather.api.wunderground.WundergroundService;
import org.codehaus.jackson.map.ObjectMapper;
import pk.ip.weather.core.MessageParser;
import org.junit.*;
import pk.ip.weather.core.JsonMessageParser;
import static org.junit.Assert.*;

public class WundergroundServiceTest
{
    final String apiKey = "abc";
    WundergroundService service;
    
    @Before()
    public void init()
    {
        MessageParser messageParser = new JsonMessageParser(new ObjectMapper());
        
        service = new WundergroundService(messageParser, apiKey);
    }
    
    @Test()
    public void formatApiUrl()
    {
        String feature = "feature";
        String query = "query";
        String url = service.formatApiUrl(feature, query);
        
        String expectedUrl = WundergroundService.API_URL+"/"+apiKey+"/"+feature+"/q/"+query+"."+service.format;
        
        assertEquals(expectedUrl, url);
    }
}
