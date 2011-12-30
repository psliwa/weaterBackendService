package pk.ip.weather.core;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;

public class InvalidIntegerAsNullDeserializer extends StdDeserializer<Integer>
{
    InvalidIntegerAsNullDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public Integer deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException
    {
        String value = jp.getText();
        
        try
        {
            return Integer.valueOf(value);
        }
        catch(NumberFormatException e)
        {
            return null;
        }
    }
}
