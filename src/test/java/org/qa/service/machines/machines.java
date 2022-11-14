package org.qa.service.machines;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.qa.service.properties.NupanoProperties;

public class machines {
    public HttpResponse<JsonNode> listmachines() {
        return Unirest.get(NupanoProperties.url.replace("store.", "machines.") + "/api/v0/machines")
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    private HttpResponse<JsonNode> removemachine(String machineID) {
        return Unirest.delete(NupanoProperties.url.replace("store.", "machines.") + "/api/v0/machines/" + machineID)
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    public void deletemachine(JSONArray machines) {
        System.out.println("Total machines found " + machines.length());

        for (int index=0; index < machines.length(); index++) {
            JSONObject machine = machines.getJSONObject(index);

            System.out.println("Deleting machine " + machine.get("name"));
            removemachine(machine.get("id").toString());
        }
    }
}
