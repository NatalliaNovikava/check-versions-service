package com.example.webservices.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.webservices.entity.Version;
import com.example.webservices.entity.VersionsComparisonResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CheckVersionServiceTest {

  private CheckVersionsService checkVersionsService;

  @BeforeEach
  public void beforeEach() {
    checkVersionsService = new CheckVersionsService();
  }

  @Test
  public void compareVersion_shouldReturnEqual_whenFirstIsEqualSecond() {
    VersionsComparisonResult expected
        = VersionsComparisonResult.fromMessageAndResult("1.2.3.4 is \"equal\" 1.02.3.4", 0);
    VersionsComparisonResult actual = checkVersionsService
        .compareVersions(Version.fromString("1.2.3.4"), Version.fromString("1.02.3.4"));
    assertThat(actual).isEqualToComparingFieldByField(expected);
  }

  @Test
  public void compareVersion_shouldReturnBefore_whenFirstIsLess() {
    VersionsComparisonResult expected =
        VersionsComparisonResult.fromMessageAndResult("1.2.3.4 is \"before\" 1.5", -1);
    VersionsComparisonResult actual = checkVersionsService
        .compareVersions(Version.fromString("1.2.3.4"), Version.fromString("1.5"));
    assertThat(actual).isEqualToComparingFieldByField(expected);
  }

  @Test
  public void compareVersion_shouldReturnAfter_whenFirstIsGreater() {
    VersionsComparisonResult expected
        = VersionsComparisonResult.fromMessageAndResult("2.09.3.4 is \"after\" 1.2.3.4", 1);
    VersionsComparisonResult actual = checkVersionsService
        .compareVersions(Version.fromString("2.09.3.4"), Version.fromString("1.2.3.4"));
    assertThat(actual).isEqualToComparingFieldByField(expected);
  }
}
