package api;

import io.restassured.response.Response;
import models.LoginRequestModel;
import models.LoginResponseModel;
import models.UserBookResponseModel;
import specs.ApiSpec;
import tests.TestData;

import static io.restassured.RestAssured.given;
import static specs.ApiSpec.request;
import static specs.ApiSpec.responseSpec;

public class AccountApiSteps {
  public static LoginResponseModel login() {
    LoginRequestModel request = new LoginRequestModel(TestData.USERNAME,TestData.PASSWORD);
    return
      given(ApiSpec.request)
        .body(request)
        .when()
        .post("/Account/v1/Login")
        .then()
        .spec(ApiSpec.response)
        .extract().as(LoginResponseModel.class);
  }
  public static UserBookResponseModel getUserBooks(String token, String userId) {
    return
      given(request)
      .header("Authorization", "Bearer " + token)
      .when()
      .get("/Account/v1/User/" + userId)
      .then()
      .spec(responseSpec(200))
      .extract().as(UserBookResponseModel.class);
  }
}
