package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookResponseModel {
  private List<BookModel> books;
}
