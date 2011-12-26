package pk.ip.weater.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import pk.ip.weater.api.wunderground.model.Response;
import pk.ip.weater.api.wunderground.model.history.History;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryResponse
{
    public Response response;
    public History history;
}
