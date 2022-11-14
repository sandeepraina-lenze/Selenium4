package org.qa.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import org.qa.service.authentication.authentication;
import org.qa.service.machines.machines;
import org.qa.service.machines.machinemodels;
import org.qa.service.apps.apps;
import org.qa.service.apps.userapps;
import org.qa.service.properties.NupanoProperties;

public class nupano {
    public void clean(String username, String password) {
        authentication auth = new authentication();
        machines mach = new machines();
        machinemodels machmodel = new machinemodels();
        apps app = new apps();
        userapps userapp = new userapps();

        HttpResponse<JsonNode> resp = auth.oauth2(username, password);
        NupanoProperties.access_token = resp.getBody().getObject().get("access_token").toString();

        HttpResponse<JsonNode> listmachines = mach.listmachines();

        JSONArray getMachineList = listmachines.getBody().getArray();
        mach.deletemachine(getMachineList);

        HttpResponse<JsonNode> listmachinemodels = machmodel.listmachinemodels();

        JSONArray getMachineModelList = listmachinemodels.getBody().getArray();
        machmodel.deletemachinemodel(getMachineModelList);

        HttpResponse<JsonNode> listapps = app.listapps();

        JSONArray getApplList = listapps.getBody().getArray();
        app.deleteapp(getApplList);

        HttpResponse<JsonNode> listuserapps = userapp.listuserapps();

        JSONArray getUserApplList = listuserapps.getBody().getArray();
        userapp.deleteuserapp(getUserApplList);
    }
}
