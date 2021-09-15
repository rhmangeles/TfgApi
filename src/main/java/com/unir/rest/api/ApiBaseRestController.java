package com.unir.rest.api;

public class ApiBaseRestController {

    private final String baseUrlApi = "/api/v1";

    protected String getUrlBaseApi() {
        return this.baseUrlApi;
    }

}
