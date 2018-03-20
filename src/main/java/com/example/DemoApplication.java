package com.example;

import com.example.auth.AccessTokenPrincipal;
import com.example.auth.OktaOAuthAuthenticator;
import com.example.models.OktaOAuthConfig;
import com.example.resources.LoginWidgetConfigResource;
import com.okta.jwt.JwtHelper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.resources.HomePageResource;
import org.apache.commons.lang3.StringUtils;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        // we are serving static assets from `/`, our resources are all under /api
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {

        // base url for our resources
        environment.jersey().setUrlPattern("/api/*");

        // configure OAuth
        configureOAuth(configuration, environment);

        // add resources
        environment.jersey().register(new HomePageResource());
        environment.jersey().register(new LoginWidgetConfigResource(configuration.oktaOAuth));
    }

    private void configureOAuth(final DemoConfiguration configuration, final Environment environment) {
        try {
            OktaOAuthConfig widgetConfig = configuration.oktaOAuth;
            // Configure the JWT Validator, it will validate Okta's JWT access tokens
            JwtHelper helper = new JwtHelper()
                    .setIssuerUrl(widgetConfig.issuer)
                    .setClientId(widgetConfig.clientId);

            // set the audience only if set, otherwise the default is: api://default
            String audience = widgetConfig.audience;
            if (StringUtils.isNotEmpty(audience)) {
                helper.setAudience(audience);
            }

            // Dropwizard's OAuth support requires you to roll your own (but at least give you a couple interfaces)
            environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<AccessTokenPrincipal>()
                    .setAuthenticator(new OktaOAuthAuthenticator(helper.build()))
                    .setPrefix("Bearer")
                    .buildAuthFilter()));

            // Bind our custom principal to the @Auth annotation
            environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AccessTokenPrincipal.class));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to configure JwtVerifier", e);
        }
    }
}