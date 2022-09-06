package cn.liondance.liondanceapi.business.controller;

import cn.liondance.liondanceapi.business.entity.LionDancePoetry;
import cn.liondance.liondanceapi.business.service.LionDancePoetryService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Lion dance book controller.
 *
 * @author sunwei
 */
@Api(tags = "book")
@RestController
@AllArgsConstructor
@RequestMapping(value = "lionDnacePoetry")
public class LionDancePoetryController {
  private final LionDancePoetryService lionDnacePoetryService;

  /**
   * New book response entity.
   *
   * @param body the body
   * @return the response entity
   */
  @ApiOperation(value = "新增book")
  @PostMapping(value = "newBook")
  @ApiImplicitParam(
      name = "body",
      example =
          "{\n"
              + "  \"author\": \"\",\n"
              + "  \"name\": \"\",\n"
              + "  \"summary\": \"\",\n"
              + "  \"tag\": \"\"\n"
              + "}")
  public ResponseEntity<LionDancePoetry> newBook(@RequestBody String body) {
    return ResponseEntity.ok(
        lionDnacePoetryService.newPoetry(JSON.parseObject(body, LionDancePoetry.class)));
  }

  /**
   * New chapter response entity.
   *
   * @param body the body
   * @return the response entity
   */
  @ApiOperation(value = "newPoetryList")
  @PostMapping(value = "newPoetryList")
  public ResponseEntity<String> newPoetryList(@RequestBody String body) {
    JSON.parseArray(body)
        .toJavaList(LionDancePoetry.class)
        .forEach(lionDnacePoetryService::newPoetry);
    return ResponseEntity.ok("ok");
  }
}
