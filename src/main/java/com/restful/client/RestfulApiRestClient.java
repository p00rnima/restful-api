package com.restful.client;

import com.restful.pojo.restful.request.CreateObjectRequest;
import com.restful.util.JsonObject;
import com.restful.util.PropertyHelper;
import com.restful.http.GenericHttpClient;
import com.restful.http.HttpRequestMessage;
import com.restful.http.HttpResponseMessage;

import static com.restful.util.PropertyHelper.prop;
import static com.restful.util.StringHelper.substitute;

public class RestfulApiRestClient {

    private static final GenericHttpClient httpClient;
    public static final String RESTFUL_API_HOST = PropertyHelper.prop("restApiBaseUrl");
    public static final String CREATE_OBJECT = PropertyHelper.prop("createObject");
    public static final String GET_OBJECTS_LIST = PropertyHelper.prop("getAllObjects");
    public static final String GET_OBJECT_BY_ID = PropertyHelper.prop("getObjectById");
    public static final String DELETE_OBJECT_BY_ID = PropertyHelper.prop("deleteObjectById");
    public static final String OBJECT_ID = "object_id";

    static {
        httpClient =
                new GenericHttpClient(
                        "RestfulApiRestClient",
                        (Integer.parseInt(PropertyHelper.prop("restfulApiRestClientConnectionPoolSize", "10"))));

    }

    private HttpRequestMessage defaultHttpRequestMessage() {
        return new HttpRequestMessage()
                .withJsonContentType();
    }

    public HttpResponseMessage createRestfulObject(CreateObjectRequest createObjectRequest) {
        var url = RESTFUL_API_HOST + CREATE_OBJECT;
        var createHttpRequest = defaultHttpRequestMessage();
        return httpClient.post(createHttpRequest
                .withPayload(JsonObject.objectToJson(createObjectRequest))
                .withUrl(url));
    }

    public HttpResponseMessage getAllRestfulObjects() {
        var url = RESTFUL_API_HOST + GET_OBJECTS_LIST;
        var httpRequestMessage = new HttpRequestMessage().withUrl(url);
        return httpClient.get(httpRequestMessage);
    }

    public HttpResponseMessage getRestfulObjectById(String id) {
        var url = RESTFUL_API_HOST + GET_OBJECT_BY_ID;
        url = substitute(url, OBJECT_ID, id);
        var httpRequestMessage = new HttpRequestMessage().withUrl(url);
        return httpClient.get(httpRequestMessage);
    }

    public HttpResponseMessage deleteRestfulObjectById(String id) {
        var url = RESTFUL_API_HOST + DELETE_OBJECT_BY_ID;
        url = substitute(url, OBJECT_ID, id);
        var httpRequestMessage = new HttpRequestMessage().withUrl(url);
        return httpClient.delete(httpRequestMessage);
    }

}
