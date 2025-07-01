package api;

import io.restassured.response.Response;
import models.*;

import static io.restassured.RestAssured.given;
import static specs.ApiSpec.*;

public class BookAPI {

  public static void deleteAllBooks(String token, String userId) {
    given(request)
      .header("Authorization", "Bearer " + token)
      .queryParam("UserId", userId)
      .when()
      .delete("/BookStore/v1/Books")
      .then()
      .spec(responseSpec(204));
  }

  public static AddBookResponseModel addBook(String token, String userId, String isbn) {
    AddBookRequestModel requestModel = new AddBookRequestModel(userId, isbn);

    Response response = given(request)
      .header("Authorization", "Bearer " + token)
      .body(requestModel)
      .when()
      .post("/BookStore/v1/Books")
      .then()
      .spec(responseSpec(201))
      .extract().response();

    return response.as(AddBookResponseModel.class);
  }

  public static void deleteBook(String token, String userId, String isbn) {
    DeleteBookRequestModel requestModel = new DeleteBookRequestModel(isbn, userId);

    given(request)
      .header("Authorization", "Bearer " + token)
      .body(requestModel)
      .when()
      .delete("/BookStore/v1/Book")
      .then()
      .spec(responseSpec(204));
  }


  public static UserBookResponseModel getUserBooks(String token, String userId) {
    Response response = given(request)
      .header("Authorization", "Bearer " + token)
      .when()
      .get("/Account/v1/User/" + userId)
      .then()
      .spec(responseSpec(200))
      .extract().response();

    return response.as(UserBookResponseModel.class);
  }
}