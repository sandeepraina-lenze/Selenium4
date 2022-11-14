package org.qa.service.machines;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.qa.service.properties.NupanoProperties;

public class machinemodels {
    public HttpResponse<JsonNode> listmachinemodels() {
        return Unirest.get(NupanoProperties.url.replace("store.", "machines.") + "/api/v0/machine-models")
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    private HttpResponse<JsonNode> removemachinemodel(String machinemodelID) {
        return Unirest.delete(NupanoProperties.url.replace("store.", "machines.") + "/api/v0/machine-models/" + machinemodelID)
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    public void deletemachinemodel(JSONArray machinemodels) {
        System.out.println("Total machine models found " + machinemodels.length());

        for (int index=0; index < machinemodels.length(); index++) {
            JSONObject machinemodel = machinemodels.getJSONObject(index);

            System.out.println("Deleting machine model " + machinemodel.get("name"));
            removemachinemodel(machinemodel.get("id").toString());

        }

    }
}
