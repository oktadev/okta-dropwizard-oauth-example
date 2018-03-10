# Demo

How to start the Demo application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/demo-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Configuration
---

You must add the following properties to your `config.yml` before running this example

```yaml
signInWidget:
  baseUrl: your-okta-org    # https://dev-123456.oktapreview.com
  clientId: your-client-id-goes-here
  issuer: your-okta-issuer  # https://dev-123456.oktapreview.com/oauth2/default
```