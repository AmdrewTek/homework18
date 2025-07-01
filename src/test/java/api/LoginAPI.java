package api;

import models.LoginRequestModel;
import models.LoginResponseModel;
import specs.ApiSpec;
import tests.TestData;

import static io.restassured.RestAssured.given;

public class LoginAPI {
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
}
