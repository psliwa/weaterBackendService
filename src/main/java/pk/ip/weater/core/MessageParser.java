package pk.ip.weater.core;

public interface MessageParser
{
    public <T extends Object> T parseMessage(String message, Class<T> cls);
    public String createMessage(Object object);
}
