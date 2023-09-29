package com.restful.steps.api.restful;

import com.restful.http.HttpResponseMessage;
import com.restful.pojo.restful.request.CreateObjectRequest;
import com.restful.pojo.restful.request.Data;
import com.restful.pojo.restful.response.CreateObjectResponse;
import com.restful.pojo.restful.response.DeleteObjectIdResponse;
import com.restful.pojo.restful.response.GetObjectByIdResponse;
import com.restful.pojo.restful.response.ObjectsListResponse;
import com.restful.steps.AbstractApiBase;
import com.restful.util.HttpAssertHelper;
import com.restful.util.JsonObject;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.restful.util.JsonObject.jsonToObject;
import static org.assertj.core.api.Assertions.assertThat;

public class RestfulApiSteps extends AbstractApiBase {

    private HttpResponseMessage httpResponseMessage;
    private String name;
    private int year;
    private double price;
    private String cpuModel;
    private String hardDiskSize;


    @When("^user creates an item with below details$")
    public void userCreatesAnItemWithBelowDetails(DataTable dataTable) {
        dataTable.getPickleRows().stream().skip(1)
                .forEach(dataTableRow -> {
                    name = dataTableRow.getCells().get(0).getValue();
                    year = Integer.parseInt(dataTableRow.getCells().get(1).getValue());
                    price = Double.parseDouble(dataTableRow.getCells().get(2).getValue());
                    cpuModel = dataTableRow.getCells().get(3).getValue();
                    hardDiskSize = dataTableRow.getCells().get(4).getValue();

                    CreateObjectRequest createObjectRequest = new CreateObjectRequest();
                    createObjectRequest.setName(name);
                    Data data = new Data();
                    data.setYear(year);
                    data.setPrice(price);
                    data.setCPUModel(cpuModel);
                    data.setHardDiskSize(hardDiskSize);
                    createObjectRequest.setData(data);
                    httpResponseMessage = restfulApiRestClient.createRestfulObject(createObjectRequest);
                    HttpAssertHelper.assertHttpStatusAsOk(httpResponseMessage);
                    commonData.createObjectResponse = jsonToObject(
                            httpResponseMessage.getPayload(), CreateObjectResponse.class);
                    commonData.objectId = commonData.createObjectResponse.getId();
                });
    }

    @Then("^user verifies the item created response$")
    public void userVerifiesTheItemCreatedResponse() {
        assertThat(commonData.createObjectResponse.getId())
                .as("Object id is null!")
                .isNotNull();
        assertThat(commonData.createObjectResponse.getName())
                .as("Object name doesn't match!")
                .isEqualTo(name);
        assertThat(commonData.createObjectResponse.getData().getYear())
                .as("Object year doesn't match!")
                .isEqualTo(year);
        assertThat(commonData.createObjectResponse.getData().getPrice())
                .as("Object price doesn't match!")
                .isEqualTo(price);
        assertThat(commonData.createObjectResponse.getData().getCPUModel())
                .as("Object CPU model doesn't match!")
                .isEqualTo(cpuModel);
        assertThat(commonData.createObjectResponse.getData().getHardDiskSize())
                .as("Object hard disk size doesn't match!")
                .isEqualTo(hardDiskSize);
        assertThat(commonData.createObjectResponse.getCreatedAt())
                .as("Object createdAt date is null!")
                .isNotNull();
    }

    @When("^user get all the objects list$")
    public void userGetAllTheObjectsList() {
        httpResponseMessage = restfulApiRestClient.getAllRestfulObjects();
        HttpAssertHelper.assertHttpStatusAsOk(httpResponseMessage);
        commonData.objectsListResponse = JsonObject.jsonToList(
                httpResponseMessage.getPayload(), ObjectsListResponse.class);
    }

    @Then("^user verifies the list of objects response$")
    public void userVerifiesTheListOfObjectsResponse() {
        assertThat(commonData.objectsListResponse.size())
                .as("Object list is empty!")
                .isGreaterThan(0);
    }

    @When("^user retrieves one item by (.*)$")
    public void userRetrievesOneItemByID(String id) {
        httpResponseMessage = restfulApiRestClient.getRestfulObjectById(id);
        HttpAssertHelper.assertHttpStatusAsOk(httpResponseMessage);
        commonData.getObjectByIdResponse = jsonToObject(
                httpResponseMessage.getPayload(), GetObjectByIdResponse.class);
    }

    @Then("^user verifies the individual item details$")
    public void userVerifiesTheIndividualItemDetails(DataTable dataTable) {
        dataTable.getPickleRows().stream().skip(1)
                .forEach(dataTableRow -> {
                    var id = dataTableRow.getCells().get(0).getValue();
                    var name = dataTableRow.getCells().get(1).getValue();
                    var year = Integer.parseInt(dataTableRow.getCells().get(2).getValue());
                    var price = Double.parseDouble(dataTableRow.getCells().get(3).getValue());
                    var cpuModel = dataTableRow.getCells().get(4).getValue();
                    var hardDiskSize = dataTableRow.getCells().get(5).getValue();

                    assertThat(commonData.getObjectByIdResponse.getId())
                            .as("Single Object id doesn't match!")
                            .isEqualTo(id);
                    assertThat(commonData.getObjectByIdResponse.getName())
                            .as("Single Object name doesn't match!")
                            .isEqualTo(name);
                    assertThat(commonData.getObjectByIdResponse.getData().getYear())
                            .as("Single Object year doesn't match!")
                            .isEqualTo(year);
                    assertThat(commonData.getObjectByIdResponse.getData().getPrice())
                            .as("Single Object price doesn't match!")
                            .isEqualTo(price);
                    assertThat(commonData.getObjectByIdResponse.getData().getCPUModel())
                            .as("Single Object cpu model doesn't match!")
                            .isEqualTo(cpuModel);
                    assertThat(commonData.getObjectByIdResponse.getData().getHardDiskSize())
                            .as("Single Object hard disk size doesn't match!")
                            .isEqualTo(hardDiskSize);

                });
    }

    @When("^user deletes the created item$")
    public void userDeletesAnItemByID() {
        httpResponseMessage = restfulApiRestClient.deleteRestfulObjectById(commonData.objectId);
        HttpAssertHelper.assertHttpStatusAsOk(httpResponseMessage);
        commonData.deleteObjectIdResponse = jsonToObject(
                httpResponseMessage.getPayload(), DeleteObjectIdResponse.class);
    }

    @Then("^user verifies the created item deleted response$")
    public void userVerifiesTheItemDeletedResponse() {
        assertThat(commonData.deleteObjectIdResponse.getMessage())
                .as("Delete object message doesn't match!")
                .isEqualTo("Object with id = " + commonData.objectId + " has been deleted.");
    }
}
