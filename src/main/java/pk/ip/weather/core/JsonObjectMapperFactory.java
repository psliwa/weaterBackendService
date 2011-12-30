package pk.ip.weather.core;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

public class JsonObjectMapperFactory
{
    public ObjectMapper create()
    {
        SimpleModule module = new SimpleModule("InvalidIntegerAsNullDeserializer", Version.unknownVersion());
        module.addDeserializer(Integer.class, new InvalidIntegerAsNullDeserializer(Integer.class));
        
        ObjectMapper mapper = new ObjectMapper().withModule(module);
        
        return mapper;
    }
}
