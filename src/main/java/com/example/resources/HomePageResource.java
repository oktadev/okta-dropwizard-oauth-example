package com.example.resources;

import com.example.auth.AccessTokenPrincipal;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/message")
public class HomePageResource {

    @GET
    public String handleGetRequest(@Auth AccessTokenPrincipal tokenPrincipal) {
        return "Hello! We'll be contacting you at: " + tokenPrincipal.getName();
    }
}