package api;

import models.AddBookRequestModel;
import models.AddBookResponseModel;
import models.DeleteBookRequestModel;

import static io.restassured.RestAssured.given;
import static specs.ApiSpec.*;

public class BookStoreApiSteps {

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
    return
      given(request)
      .header("Authorization", "Bearer " + token)
      .body(requestModel)
      .when()
      .post("/BookStore/v1/Books")
      .then()
      .spec(responseSpec(201))
      .extract().as(AddBookResponseModel.class);
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


}