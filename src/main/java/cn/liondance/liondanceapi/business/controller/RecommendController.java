package cn.liondance.liondanceapi.business.controller;

import cn.liondance.liondanceapi.business.entity.LionDanceRecommend;
import cn.liondance.liondanceapi.business.service.LionDanceRecommendService;
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


/**
 * The type Recommend controller.
 *
 * @author sunwei
 */
@Api(tags = "推荐 API")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("recommend")
public class RecommendController {
    private final MessageSource             messageSource;
    private final LionDanceRecommendService lionDanceRecommendService;


    @ApiOperation(value = "search")
    @GetMapping("/search")
    public ResponseEntity<JSONObject> search(BasicSearchModel basicSearchModel, String searchContent) {
        ResponseBody<JSONObject> searchResponse = lionDanceRecommendService.search(basicSearchModel, searchContent);
        return ResponseEntity.ok(basicSearchModel.getResult(searchResponse, LionDanceRecommend.class));
    }


}
