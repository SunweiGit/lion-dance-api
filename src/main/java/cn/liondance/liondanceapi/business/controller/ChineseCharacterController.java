package cn.liondance.liondanceapi.business.controller;

import cn.liondance.liondanceapi.business.entity.LionDanceChineseCharacter;
import cn.liondance.liondanceapi.business.service.LionDanceChineseCharacterService;
import cn.liondance.liondanceapi.common.BasicSearchModel;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * The type 18 n controller.
 *
 * @author sunwei
 */
@Api(tags = "中文汉字 rest api")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("chineseCharacter")
public class ChineseCharacterController {
    private final MessageSource                    messageSource;
    private final LionDanceChineseCharacterService lionDanceChineseCharacterService;


    @ApiOperation(value = "search")
    @GetMapping("/search")
    public ResponseEntity<JSONObject> search(HttpServletRequest request, BasicSearchModel basicSearchModel, String tag, String searchContent) {
        ResponseBody<JSONObject> searchResponse = lionDanceChineseCharacterService.search(basicSearchModel, tag, searchContent);
        return ResponseEntity.ok(basicSearchModel.getResult(searchResponse, LionDanceChineseCharacter.class));
    }


}
