package tests;

import api.BookStoreApiSteps;
import api.AccountApiSteps;
import com.codeborne.selenide.Selenide;
import helpers.AuthHelper;
import helpers.WithLogin;
import models.BookModel;
import models.LoginResponseModel;
import models.UserBookResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.USERNAME;
import static tests.TestData.isbn;



public class BookShopTest extends TestBase {
  @Test
  @WithLogin
  @DisplayName("Удаление книг из профиля")
  void deleteBookTest() {
    LoginResponseModel auth = step("Авторизация через API", AuthHelper::getLoginResponse);
    String token = auth.getToken();
    String userId = auth.getUserId();

    step("Удаление коллекции", () ->
      BookStoreApiSteps.deleteAllBooks(token, userId)
    );

    step("Добавление книги через API", () ->
      BookStoreApiSteps.addBook(token, userId, isbn)
    );

    step("Проверка добавления книги через API", () -> {
      UserBookResponseModel booksResp = AccountApiSteps.getUserBooks(token, userId);
      List<BookModel> userBooks = booksResp.getBooks();
      assertThat(userBooks).extracting(BookModel::getIsbn).contains(isbn);
    });

    step("Переход на страницу профиля", () -> {
      open("/profile");
      $("#userName-value").shouldHave(text(USERNAME));
    });

    step("Удаление книги через API", () ->
      BookStoreApiSteps.deleteBook(token, userId, isbn)
    );

    step("Проверка удаления книги", () -> {
      UserBookResponseModel booksResp = AccountApiSteps.getUserBooks(token, userId);
      assertThat(booksResp.getBooks()).noneMatch(b -> b.getIsbn().equals(isbn));
    });

    step("Обновление страницы и проверка UI", () -> {
      Selenide.refresh();
      $(".rt-noData").shouldBe(visible).shouldHave(text("No rows found"));
    });
  }
}
