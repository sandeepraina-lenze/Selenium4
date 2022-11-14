package org.qa.service.apps;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.qa.service.properties.NupanoProperties;

public class userapps {
    public HttpResponse<JsonNode> listuserapps() {
        return Unirest.get(NupanoProperties.url.replace("store.", "apps.") + "/api/v0/users/apps")
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    private HttpResponse<JsonNode> removeuserapp(String appID, String appverID) {
        return Unirest.delete(NupanoProperties.url.replace("store.", "apps.") + "/api/v0/apps/" + appID + "/versions/" + appverID)
                .header("Authorization", "Bearer " + NupanoProperties.access_token)
                .header("User-Agent", "Apache")
                .asJson();
    }

    public void deleteuserapp(JSONArray apps) {
        System.out.println("Total user apps found " + apps.length());

        for (int index=0; index < apps.length(); index++) {
            JSONObject app = apps.getJSONObject(index);

            JSONArray getAppVersionList = app.getJSONArray("versions");

            System.out.println("Total user app version for " + app.get("name") + " found " + getAppVersionList.length());
            if(!(app.get("name").toString().equalsIgnoreCase("test1 app") || app.get("name").toString().equalsIgnoreCase("test2 app")))
            {
                for (int indexver = 0; indexver < getAppVersionList.length(); indexver++) {
                    JSONObject appversion = getAppVersionList.getJSONObject(indexver);

                    System.out.println("Deleting user app " + app.get("name") + " for version " + appversion.get("versionId"));
                    removeuserapp(app.get("id").toString(), appversion.get("versionId").toString());
                }
            }
        }
    }
}
