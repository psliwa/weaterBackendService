package pk.ip.weater.api.wunderground.model.history;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class History
{
    public DateInformation date;
    public Observation[] observations;
    public SummaryObservation[] dailysummary;
}
