package com.example.webservices.service;

import com.example.webservices.entity.Version;
import com.example.webservices.entity.VersionsComparisonResult;
import org.springframework.stereotype.Service;

@Service
public class CheckVersionsService {

  /***
   * Represents integer comparison result as text message
   * @param result integer comparison result
   * @return text message result
   */
  private String comparisonResultAsString(int result) {
    if (result > 0) {
      return "after";
    } else if (result < 0) {
      return "before";
    } else {
      return "equal";
    }
  }

  /***
   * Compares two versions and returns result
   * @param version1 first version to compare
   * @param version2 second version to compare
   * @return versions comparison result
   */
  public VersionsComparisonResult compareVersions(Version version1, Version version2) {
    int result = version1.compareTo(version2);
    String resultString = comparisonResultAsString(result);
    String message = String.format("%s is \"%s\" %s",
        version1.getNumber(), resultString, version2.getNumber());
    return VersionsComparisonResult.fromMessageAndResult(message, result);
  }
}
