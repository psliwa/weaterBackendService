package pk.ip.weater.service;

public enum StatisticsType
{       
    TEMPERATURE("temperature", Operation.AVG), 
    WINDCHILL_TEMPERATURE("windchillTemperature", Operation.AVG), 
    PRESSURE("pressure", Operation.AVG), 
    WIND_SPEED("windSpeed", Operation.AVG), 
    HUMIDITY("humidity", Operation.AVG), 
    VISIBILITY("visibility", Operation.AVG), 
    FOG("fog", Operation.SUM), 
    RAIN("rain", Operation.SUM), 
    SNOW("show", Operation.SUM), 
    THUNDER("thunder", Operation.SUM);

    static enum Operation
    {
        AVG, SUM;
    }

    private String property;
    private Operation operation;

    private StatisticsType(String property, Operation operation)
    {
        this.property = property;
        this.operation = operation;
    }

    String getProperty()
    {
        return property;
    }

    Operation getOperation()
    {
        return operation;
    }
}