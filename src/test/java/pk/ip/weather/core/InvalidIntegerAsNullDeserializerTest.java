package pk.ip.weather.core;

import java.io.IOException;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.*;
import static org.junit.Assert.*;

public class InvalidIntegerAsNullDeserializerTest
{
    private ObjectMapper mapper;
    
    @Before()
    public void init()
    {
        SimpleModule module = new SimpleModule("InvalidIntegerAsNullDeserializer", Version.unknownVersion());
        module.addDeserializer(Integer.class, new InvalidIntegerAsNullDeserializer(Integer.class));
        
        mapper = new ObjectMapper().withModule(module);
    }
    
    @Test()
    public void convertInvalidIntegerValueToNull() throws IOException
    {
        String json = "{ \"value\": 123 }";
        
        TestBean bean = mapper.readValue(json, TestBean.class);
        
        assertEquals(Integer.valueOf(123), bean.value);
        
        json = "{ \"value\": \"N/A\" }";
        
        bean = mapper.readValue(json, TestBean.class);
        
        assertNull(bean.value);
    }
    
    public static class TestBean
    {
        public Integer value;
    }
}
