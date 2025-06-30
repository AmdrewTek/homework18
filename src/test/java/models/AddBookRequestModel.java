package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class AddBookRequestModel {
  private String userId;
  private List<IsbnModel> collectionOfIsbns;

  public AddBookRequestModel(String userId, String isbn) {
    this.userId = userId;
    this.collectionOfIsbns = Collections.singletonList(new IsbnModel(isbn));
  }

  @Data
  @AllArgsConstructor
  public static class IsbnModel {
    private String isbn;
  }
}
