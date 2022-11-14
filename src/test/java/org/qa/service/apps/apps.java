package org.qa.service.apps;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.qa.service.properties.NupanoProperties;

public class apps {
    public HttpResponse<JsonNode> listapps() {
        return Unirest.get(NupanoProperties.url.replace("store.", "apps.") + "/api/v0/apps")
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    private HttpResponse<JsonNode> listappversions(String appID) {
        return Unirest.get(NupanoProperties.url.replace("store.", "apps.") + "/api/v0/apps/" + appID)
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    private HttpResponse<JsonNode> removeapp(String appID, String appverID) {
        return Unirest.delete(NupanoProperties.url.replace("store.", "apps.") + "/api/v0/apps/" + appID + "/versions/" + appverID)
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    public void deleteapp(JSONArray apps) {
        System.out.println("Total apps found " + apps.length());

        for (int index=0; index < apps.length(); index++) {
            JSONObject app = apps.getJSONObject(index);

            HttpResponse<JsonNode> appversions = listappversions(app.get("id").toString());
            JSONObject getAppVersionList = appversions.getBody().getObject();

            System.out.println("Total app version for " + app.get("name") + " found " + getAppVersionList.getJSONArray("versions").length());
            if(!(app.get("name").toString().equalsIgnoreCase("test1 app") || app.get("name").toString().equalsIgnoreCase("test2 app")))
            {
                for (int indexver = 0; indexver < getAppVersionList.getJSONArray("versions").length(); indexver++) {
                    JSONObject appversion = getAppVersionList.getJSONArray("versions").getJSONObject(indexver);

                    System.out.println("Deleting app " + app.get("name") + " for version " + appversion.get("versionId"));
                    removeapp(app.get("id").toString(), appversion.get("versionId").toString());
                }
            }
        }
    }
}
