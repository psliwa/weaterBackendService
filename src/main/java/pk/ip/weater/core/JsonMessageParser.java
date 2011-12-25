package pk.ip.weater.core;

import com.google.gson.Gson;

public class JsonMessageParser implements MessageParser
{
    private Gson gson;
    
    public JsonMessageParser(Gson gson)
    {
        this.gson = gson;
    }

    @Override
    public <T> T parseMessage(String message, Class<T> cls)
    {
        return gson.fromJson(message, cls);
    }

    @Override
    public String createMessage(Object object)
    {
        return gson.toJson(object);
    }
}
