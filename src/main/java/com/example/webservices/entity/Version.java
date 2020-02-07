package com.example.webservices.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/***
 * This class holds version number as text and as groups of digits
 */
public class Version implements Comparable<Version> {

  public final static String VALIDATION_REGEX = "^([0-9]+)+(\\.[0-9]+)*$";
  private final static Pattern VALIDATION_PATTERN = Pattern.compile(VALIDATION_REGEX);

  private final String number;
  private final int[] digits;

  /***
   * Creates digits version number from text without trailing zeros
   * @param number text version number
   * @return digits version number
   */
  private int[] parseVersionNumber(String number) {
    String[] groups = number.split("\\.");
    List<Integer> list = new ArrayList<>();
    boolean isFirstNotZeroFound = false;
    for (int i = groups.length - 1; i >= 0; i--) {
      int num = Integer.parseInt(groups[i]);
      if (!isFirstNotZeroFound && num == 0) {
        continue;
      }
      isFirstNotZeroFound = true;
      list.add(num);
    }
    int[] result = new int[list.size()];
    int index = 0;
    for (int i = list.size() - 1; i >= 0; i--) {
      result[index] = list.get(i);
      index++;
    }
    return result;
  }

  private Version(String number) {
    this.number = number;
    this.digits = parseVersionNumber(number);
  }

  /***
   * Creates version from valid text number of version, otherwise throws IllegalArgumentException
   * @param number text version number
   * @return version number
   */
  public static Version fromString(String number) {
    if (!VALIDATION_PATTERN.matcher(number).matches()) {
      throw new IllegalArgumentException("Invalid version format: " + number);
    }
    return new Version(number);
  }

  public int[] getDigits() {
    return digits;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return number;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(digits);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    // compare by digits to handle any leading zeroes case e.g. 1.01 and 1.1 are equal
    return Arrays.equals(this.digits, ((Version) object).getDigits());
  }

  @Override
  public int compareTo(Version other) {
    if (Arrays.equals(this.digits, other.digits)) {
      return 0;
    }
    int[] digits1 = this.digits;
    int[] digits2 = other.digits;
    for (int i = 0, j = 0; i < digits1.length || j < digits2.length; i++, j++) {
      if (i < digits1.length && j < digits2.length) {
        if (digits1[i] < digits2[j]) {
          return -1;
        }
        if (digits1[i] > digits2[j]) {
          return 1;
        }
      } else if (i < digits1.length) {
        return 1;
      } else {
        return -1;
      }
    }
    return 0;
  }
}
