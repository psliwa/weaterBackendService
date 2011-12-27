package pk.ip.weather.core;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

public class JsonMessageParser implements MessageParser
{
    private ObjectMapper mapper;
    
    public JsonMessageParser(ObjectMapper mapper)
    {
        mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
        this.mapper = mapper;
    }

    @Override
    public <T> T parseMessage(String message, Class<T> cls)
    {
        try
        {
            return mapper.readValue(message, cls);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String createMessage(Object object)
    {
        try
        {
            return mapper.writeValueAsString(object);
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
