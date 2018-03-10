package com.example.models;

public class OktaSignInWidgetConfig {

    private String baseUrl;
    private String clientId;
    private String issuer;
    private String audience;

    public OktaSignInWidgetConfig() {}

    public OktaSignInWidgetConfig(String baseUrl, String clientId, String issuer) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.issuer = issuer;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public OktaSignInWidgetConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public OktaSignInWidgetConfig setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public OktaSignInWidgetConfig setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public String getAudience() {
        return audience;
    }

    public OktaSignInWidgetConfig setAudience(String audience) {
        this.audience = audience;
        return this;
    }
}
