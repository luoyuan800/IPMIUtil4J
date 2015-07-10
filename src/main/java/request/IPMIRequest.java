package request;

import respond.IPMIRespond;
import client.IPMIClient;

public interface IPMIRequest {
public IPMIRespond sendTo(IPMIClient client);
}
