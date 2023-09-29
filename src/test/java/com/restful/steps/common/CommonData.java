package com.restful.steps.common;

import com.restful.client.RestfulApiRestClient;
import com.restful.pojo.restful.response.CreateObjectResponse;
import com.restful.pojo.restful.response.DeleteObjectIdResponse;
import com.restful.pojo.restful.response.GetObjectByIdResponse;
import com.restful.pojo.restful.response.ObjectsListResponse;
import cucumber.api.Scenario;

import java.util.Collection;
import java.util.List;


public class CommonData {
  
  public Scenario scenario;
  public String objectId;

  public CreateObjectResponse createObjectResponse;
  public List<ObjectsListResponse> objectsListResponse;
  public GetObjectByIdResponse getObjectByIdResponse;
  public DeleteObjectIdResponse deleteObjectIdResponse;

  private RestfulApiRestClient restfulApiRestClient;

  public CommonData() {
  }

  public RestfulApiRestClient getRestfulApiRestClient() {
    if (restfulApiRestClient == null) {
      restfulApiRestClient = new RestfulApiRestClient();
    }
    return restfulApiRestClient;
  }
  
  public Collection<String> scenarioTags() {
    return scenario.getSourceTagNames();
  }
  
}
