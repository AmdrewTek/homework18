package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBookRequestModel {
  private String isbn;
  private String userId;
}
