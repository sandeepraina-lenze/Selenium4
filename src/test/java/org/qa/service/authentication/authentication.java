package org.qa.service.authentication;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.qa.service.properties.NupanoProperties;

public class authentication {
    public HttpResponse<JsonNode> oauth2(String username, String password) {
        return Unirest.post(  NupanoProperties.url.replace("store.", "auth.") + "/realms/NUPANO/protocol/openid-connect/token")
                .header("User-Agent", "Apache")
                .field("grant_type", "password")
                .field("username", username)
                .field("password", password)
                .field("client_id", "NUPANO")
                .field("redirect_uri", NupanoProperties.url.replace("store.", "machines."))
                .asJson();
    }

}
