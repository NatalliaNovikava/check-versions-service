package com.example.webservices.entity;

/**
 * Holder for version comparison result as a text message and integer result
 */
public class VersionsComparisonResult {

  private final String message;
  private final int result;

  private VersionsComparisonResult(String message, int result) {
    this.message = message;
    this.result = result;
  }

  /***
   * Creates versions comparison result from message and result by calling private constructor
   * @param message contains text comparison result of two versions
   * @param result contains integer comparison result of two versions
   * @return result versions comparison result
   */
  public static VersionsComparisonResult fromMessageAndResult(String message, int result) {
    return new VersionsComparisonResult(message, result);
  }

  public String getMessage() {
    return message;
  }

  public int getResult() {
    return result;
  }
}
