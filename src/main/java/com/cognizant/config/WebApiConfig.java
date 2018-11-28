/*package com.cognizant.config;

public static class WebApiConfig
{
    public static void Register(HttpConfiguration config)
    {
        // Web API configuration and services
        var enableCorsAttribute = new EnableCorsAttribute("*",
                                           "Origin, Content-Type, Accept",
                                           "GET, PUT, POST, DELETE, OPTIONS");

        config.EnableCors(enableCorsAttribute);

        config.MapHttpAttributeRoutes();

        config.Routes.MapHttpRoute(
            name: "DefaultApi",
            routeTemplate: "api/{controller}/{id}",
            defaults: new { id = RouteParameter.Optional }
        );
    }
}*/