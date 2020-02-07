package com.example.webservices.controller;

import com.example.webservices.entity.Version;
import com.example.webservices.entity.VersionsComparisonResult;
import com.example.webservices.payload.CheckVersionsRequest;
import com.example.webservices.payload.CheckVersionsResponse;
import com.example.webservices.service.CheckVersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/check-versions")
public class CheckVersionsController {

  @Autowired
  private CheckVersionsService checkVersionsService;

  /***
   * Post method to compare versions
   * @param request contains versions to compare
   * @return result of versions comparison
   */
  @PostMapping
  public CheckVersionsResponse checkVersions(@Valid @RequestBody CheckVersionsRequest request) {
    Version v1 = Version.fromString(request.getVersion1());
    Version v2 = Version.fromString(request.getVersion2());
    VersionsComparisonResult result = checkVersionsService.compareVersions(v1, v2);
    return CheckVersionsResponse.fromResult(result.getMessage());
  }
}
