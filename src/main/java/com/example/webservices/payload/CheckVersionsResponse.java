package com.example.webservices.payload;

/***
 * Response with result of versions check
 */
public class CheckVersionsResponse {

  private final String result;

  private CheckVersionsResponse(String result) {
    this.result = result;
  }

  /***
   * Creates check versions response from comparison result
   * @param result comparison result
   * @return response check versions response
   */
  public static CheckVersionsResponse fromResult(String result) {
    return new CheckVersionsResponse(result);
  }

  public String getResult() {
    return result;
  }
}
