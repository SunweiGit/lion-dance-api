package cn.liondance.liondanceapi.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.elasticsearch.common.util.set.Sets;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.SmartForest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The type Lion dance book controller.
 *
 * @author sunwei
 */
@Slf4j
@Api(tags = "中文分词")
@RestController
@AllArgsConstructor
@RequestMapping(value = "nlp")
public class NlpController {

    /**
     * New book response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @ApiOperation(value = "nlpAnalysis")
    @PostMapping(value = "nlpAnalysis")
    @ApiImplicitParam(name = "body", example = "洁面仪配合洁面深层清洁毛孔")
    public ResponseEntity<HashSet<String>> nlpAnalysis(@RequestBody String body) {
        HashSet<String> set = Sets.newHashSet();
        Forest forest = new Forest();
        forest.add(new SmartForest(1));
        NlpAnalysis.parse(body, forest).forEach(o -> set.add(o.getName()));
        return ResponseEntity.ok(set);
    }

    @ApiOperation(value = "dicAnalysis")
    @PostMapping(value = "dicAnalysis")
    @ApiImplicitParam(name = "body", example = "洁面仪配合洁面深层清洁毛孔")
    public ResponseEntity<HashSet<String>> dicAnalysis(@RequestBody String body) {
        String key = "购物,卡,私聊,福利,红包,现金,返点,提成,返利,好处费,奖励,中奖,提现,回扣,购物卡,商务宴请,贿赂";
        Arrays.stream(key.split(",")).forEach(o -> DicLibrary.insert(DicLibrary.DEFAULT, o));
        log.error("{}", DicLibrary.get(DicLibrary.DEFAULT).branches);
        HashSet<String> set = Sets.newHashSet();
        DicAnalysis.parse(body).forEach(o -> set.add(o.getName()));
        return ResponseEntity.ok(set);
    }
}
