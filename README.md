# Laboratorium 3

## Require properties:
```
RestAPI:

mongo_uri
spring.rabbitmq.host
spring.rabbitmq.port
spring.rabbitmq.username
spring.rabbitmq.password
spring.security.oauth2.client.registration.azure.client-id
spring.security.oauth2.client.registration.azure.client-secret
spring.security.oauth2.client.registration.azure.scope
spring.security.oauth2.client.provider.azure.issuer-uri
spring.security.oauth2.resourceserver.jwt.jwk-set-uri


EmailService:
email_host
email_port
email_username
email_password


ConsoleApp:
In the ApiConsumer.getBodyFromTokenEndpoint().body require AzureAD details (scope, client_id, client_secret, grant_type)


```
