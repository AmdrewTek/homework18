package tests;

public class TestData {
  public static final String USERNAME = System.getProperty("USERNAME", System.getenv("USER_NAME"));
  public static final String PASSWORD = System.getProperty("PASSWORD", System.getenv("USER_PASSWORD"));
  public static final String isbn = "9781491904244";

  static {
    if (USERNAME == null || USERNAME.isBlank() || PASSWORD == null || PASSWORD.isBlank()) {
      throw new IllegalStateException("Username and password must be provided via -DUSERNAME/-DPASSWORD or environment variables USER_NAME/USER_PASSWORD");
    }
  }
}
