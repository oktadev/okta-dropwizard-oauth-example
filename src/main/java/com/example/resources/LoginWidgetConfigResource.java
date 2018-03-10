package com.example.resources;

import com.example.models.OktaSignInWidgetConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/signInConfig")
@Produces("application/json")
public class LoginWidgetConfigResource {

    private final OktaSignInWidgetConfig config;

    public LoginWidgetConfigResource(OktaSignInWidgetConfig config) {
        this.config = config;
    }

    public LoginWidgetConfigResource(String baseUrl, String clientId, String issuer) {
        this.config = new OktaSignInWidgetConfig(baseUrl, clientId, issuer);
    }

    @GET
    public OktaSignInWidgetConfig getConfig() {
        return config;
    }
}