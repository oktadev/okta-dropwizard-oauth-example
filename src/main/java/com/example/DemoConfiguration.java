package com.example;

import com.example.models.OktaSignInWidgetConfig;
import io.dropwizard.Configuration;

public class DemoConfiguration extends Configuration {

    public OktaSignInWidgetConfig signInWidget = new OktaSignInWidgetConfig();
}
