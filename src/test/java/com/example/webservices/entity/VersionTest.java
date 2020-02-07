package com.example.webservices.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VersionTest {

  private Version version;

  @BeforeEach
  public void beforeEach() {
    version = Version.fromString("1.2.0.3.4");
  }

  @Test
  public void equals_shouldReturnTrue_WhenFirstVersionIsEqualSecond() {

    Version version1 = Version.fromString("1.2.0.3.4.000.0.0");
    assertThat(version).isEqualTo(version1);
    assertThat(version.hashCode() == version1.hashCode());
  }

  @Test
  public void equals_shouldReturnFalse_WhenFirstVersionIsNotEqualSecond() {
    Version version1 = Version.fromString("1.2.0.3.4.6");
    assertThat(!version.equals(version1));
    assertThat(version.hashCode() != version1.hashCode());
  }

  @Test
  public void compareTo_shouldReturnOne_whenFirstVersionIsGreater() {
    Version version1 = Version.fromString("1.2.0.2");
    assertThat(version.compareTo(version1)).isEqualTo(1);
  }

  @Test
  public void compareTo_shouldReturnMinusOne_whenFirstVersionIsLess() {
    Version version1 = Version.fromString("1.2.3.5");
    assertThat(version.compareTo(version1)).isEqualTo(-1);
  }

  @Test
  public void compareTo_shouldReturnZero_whenFirstVersionIsEqualSecond() {
    Version version1 = Version.fromString("1.2.0.3.4");
    assertThat(version.compareTo(version1)).isEqualTo(0);
  }

  @Test
  public void fromString_shouldThrowIllegalArgumentException_whenInputIsInvalid() {
    assertThatThrownBy(() -> Version.fromString("1_1.2.7"))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Version.fromString("1..1.2.7"))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Version.fromString("1.3.44.5.ab"))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Version.fromString(".12"))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> Version.fromString(""))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
