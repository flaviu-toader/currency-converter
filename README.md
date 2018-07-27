# Currency Converter Backend

This is the REST API endpoint implementation using [Spring Boot](https://projects.spring.io/spring-boot/) and [Swagger 2 with swagger ui](https://swagger.io/tools/swagger-ui/) for the Currency Converter Application.

It uses the [Fixer API](http://fixer.io) to fetch the latest exchange rates, so in order to be able to run it, you will need to supply your access key as an environment variable, `FIXER_API_KEY`:

```
export FIXER_API_KEY="yourkeyhere"
```

After that, you can run it straight from the current dir with maven, via

```
mvn spring-boot:run
```

In order to see/test the backend in your browser, you can access the swagger-ui via `http://localhost:8080/swagger-ui.html`.

The application can be build as an all-in-one jar with

```
mvn package
```

The currency conversion endpoint is `/convert`, and you need to supply it with the following parameters:

* *source* -> the currency symbol for your source currency
* *target* -> the currency symbol for your target currency
* *amount* -> the amount of currency you want to convert

If all is configured correctly, the reply will be a json containing the following fields:

* *sourceSymbol* -> the currency symbol you provided
* *targetSymbol* -> the target symbol you provided
* *value* -> the result of the conversion
* *timestamp* -> the timestamp of when this conversion was performed