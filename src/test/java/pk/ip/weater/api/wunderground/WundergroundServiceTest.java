package pk.ip.weater.api.wunderground;

import org.codehaus.jackson.map.ObjectMapper;
import pk.ip.weater.core.MessageParser;
import org.junit.*;
import pk.ip.weater.core.JsonMessageParser;
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
