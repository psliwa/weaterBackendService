package pk.ip.weater.api.wunderground;

import pk.ip.weater.core.MessageParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        Gson gson = new GsonBuilder().serializeNulls().create();
        MessageParser messageParser = new JsonMessageParser(gson);
        
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
