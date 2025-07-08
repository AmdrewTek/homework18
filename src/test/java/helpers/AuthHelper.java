package helpers;

import api.AccountApiSteps;
import models.LoginResponseModel;

public class AuthHelper {

  private static LoginResponseModel currentAuth;

  public static String getToken() {
    if (currentAuth == null) {
      refreshToken();
    }
    return currentAuth.getToken();
  }

  public static String getUserId() {
    if (currentAuth == null) {
      refreshToken();
    }
    return currentAuth.getUserId();
  }

  public static void refreshToken() {
    currentAuth = AccountApiSteps.login();
  }

  public static LoginResponseModel getLoginResponse() {
    if (currentAuth == null) {
      refreshToken();
    }
    return currentAuth;
  }
}
