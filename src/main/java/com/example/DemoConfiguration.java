package com.example;

import com.example.models.OktaOAuthConfig;
import io.dropwizard.Configuration;

public class DemoConfiguration extends Configuration {

    public OktaOAuthConfig oktaOAuth = new OktaOAuthConfig();
}
