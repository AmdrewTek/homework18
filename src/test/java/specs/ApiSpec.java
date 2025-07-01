package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.notNullValue;

public class ApiSpec {
  public static RequestSpecification request = with()
    .filter(withCustomTemplates())
    .contentType("application/json")
    .log().all();

  public static ResponseSpecification response = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .expectBody("token", notNullValue())
    .log(LogDetail.ALL)
    .build();

  public static ResponseSpecification responseSpec(int statusCode) {
    return new ResponseSpecBuilder()
      .expectStatusCode(statusCode)
      .log(LogDetail.ALL)
      .build();
  }
}

