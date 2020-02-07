package com.example.webservices.controller;

import com.example.webservices.entity.Version;
import com.example.webservices.entity.VersionsComparisonResult;
import com.example.webservices.service.CheckVersionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CheckVersionsController.class)
public class CheckVersionsControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CheckVersionsService service;

  @Test
  public void checkVersion_shouldReturnBefore_whenFirstIsLess() throws Exception {
    VersionsComparisonResult versionsComparisonResult = VersionsComparisonResult
        .fromMessageAndResult("1.2.3.4 is \"before\" 1.02.5.4", -1);
    given(service.compareVersions(Version.fromString("1.2.3.4"), Version.fromString("1.02.5.4")))
        .willReturn(versionsComparisonResult);
    mvc.perform(
        post("/check-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"version1\" : \"1.2.3.4\", \"version2\" : \"1.02.5.4\"}"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is("1.2.3.4 is \"before\" 1.02.5.4")));
  }

  @Test
  public void checkVersions_shouldReturnAfter_WhenFirstIsGreater() throws Exception {
    VersionsComparisonResult versionsComparisonResult = VersionsComparisonResult
        .fromMessageAndResult("1.2.3.7 is \"after\" 1.02.3.4", 1);
    given(service.compareVersions(Version.fromString("1.2.3.7"), Version.fromString("1.02.3.4")))
        .willReturn(versionsComparisonResult);
    mvc
        .perform(
            post("/check-versions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"version1\" : \"1.2.3.7\", \"version2\" : \"1.02.3.4\"}"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is("1.2.3.7 is \"after\" 1.02.3.4")));
  }

  @Test
  public void checkVersions_shouldReturnEqual_WhenVersionsAreEqual() throws Exception {
    VersionsComparisonResult versionsComparisonResult = VersionsComparisonResult
        .fromMessageAndResult("1.2.3.4 is \"equal\" 1.02.3.4", 0);
    given(service.compareVersions(Version.fromString("1.2.3.4"), Version.fromString("1.02.3.4")))
        .willReturn(versionsComparisonResult);
    mvc.perform(
        post("/check-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"version1\" : \"1.2.3.4\", \"version2\" : \"1.02.3.4\"}"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is("1.2.3.4 is \"equal\" 1.02.3.4")));
  }

  @Test
  public void checkVersions_shouldReturnBadRequest_WhenInputIsInvalid() throws Exception {
    mvc.perform(
        post("/check-versions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"version1\" : \"1.2.3.W\", \"version2\" : \"1.02.3.4\"}"))
        .andExpect(status().isBadRequest());
  }
}
