package com.example.webservices.payload;

import com.example.webservices.entity.Version;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * Request with two versions to compare
 */
public class CheckVersionsRequest {

  @Valid
  @Pattern(regexp = Version.VALIDATION_REGEX, message = "{version.number.pattern}")
  private String version1;
  @Valid
  @Pattern(regexp = Version.VALIDATION_REGEX, message = "{version.number.pattern}")
  private String version2;

  public String getVersion1() {
    return version1;
  }

  public void setVersion1(String version1) {
    this.version1 = version1;
  }

  public String getVersion2() {
    return version2;
  }

  public void setVersion2(String version2) {
    this.version2 = version2;
  }
}

