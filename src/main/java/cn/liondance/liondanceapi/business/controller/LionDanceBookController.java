package cn.liondance.liondanceapi.business.controller;

import cn.liondance.liondanceapi.business.entity.LionDanceBook;
import cn.liondance.liondanceapi.business.entity.LionDanceBookChapter;
import cn.liondance.liondanceapi.business.service.LionDanceBookService;
import cn.liondance.liondanceapi.business.vo.LionDanceBookVO;
import cn.liondance.liondanceapi.common.BasicSearchModel;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping(value = "lionDanceBook")
public class LionDanceBookController {
    private final LionDanceBookService lionDanceBookService;

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
    public ResponseEntity<LionDanceBook> newBook(@RequestBody String body) {
        return ResponseEntity.ok(
                lionDanceBookService.newBook(JSON.parseObject(body, LionDanceBook.class)));
    }

    /**
     * New chapter lion dance book chapter.
     *
     * @param lionDanceBookChapter the lion dance book chapter
     * @return the lion dance book chapter
     */
    @ApiOperation(value = "新增book的章节")
    @PostMapping(value = "newChapter")
    public ResponseEntity<LionDanceBookChapter> newChapter(
            @RequestBody LionDanceBookChapter lionDanceBookChapter) {
        return ResponseEntity.ok(lionDanceBookService.newChapter(lionDanceBookChapter));
    }

    /**
     * New chapter response entity.
     *
     * @param bookId the book id
     * @param body   the body
     * @return the response entity
     */
    @ApiOperation(value = "新增book的章节List")
    @PostMapping(value = "newChapterList")
    public ResponseEntity<String> newChapter(String bookId, @RequestBody String body) {
        JSON.parseArray(body)
                .toJavaList(LionDanceBookChapter.class)
                .forEach(
                        o -> {
                            o.setBookId(bookId);
                            lionDanceBookService.newChapter(o);
                        });

        return ResponseEntity.ok("ok");
    }

    @ApiOperation(value = "searchBook")
    @PostMapping(value = "searchBook")
    public ResponseEntity<JSONObject> searchBook(BasicSearchModel searchModel) {
        ResponseBody<JSONObject> searchResponse = lionDanceBookService.searchBook(searchModel);
        return ResponseEntity.ok(searchModel.getResult(searchResponse, LionDanceBookVO.class));
    }
}
