package cn.liondance.liondanceapi.business.service.impl;

import cn.liondance.liondanceapi.business.service.LionDanceChineseCharacterService;
import cn.liondance.liondanceapi.common.BasicSearchModel;
import cn.liondance.liondanceapi.config.IndexVariable;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Chinese character service.
 *
 * @author sunwei
 */
@Slf4j
@Service
@AllArgsConstructor
public class LionDanceChineseCharacterServiceImpl implements LionDanceChineseCharacterService {

    private final ElasticsearchClient elasticsearchClient;


    @Override
    public ResponseBody<JSONObject> search(BasicSearchModel basicSearchModel, String tag, String searchContent) {
        SearchRequest.Builder searchRequest = basicSearchModel.searchRequest();
        if (StringUtils.isNotEmpty(tag)) {
            searchRequest.query(q -> q.term(t1 -> t1.field("tag.keyword").value(v1 -> v1.stringValue(tag))));
        }
        if (StringUtils.isNotEmpty(searchContent)) {
            List<Query> list = new ArrayList<>();
            list.add(new Query.Builder()
                    .wildcard(t -> t
                            .field("word.keyword")
                            .value("*" + searchContent + "*")
                    )
                    .build());
            list.add(new Query.Builder()
                    .wildcard(t -> t
                            .field("explanation.keyword")
                            .value("*" + searchContent + "*")
                    )
                    .build());
            searchRequest.query(q -> q.bool(b1 -> b1
                    .should(list)));
        }
        searchRequest.index(IndexVariable.lion_dance_chinese_character);
        return basicSearchModel.search(elasticsearchClient, searchRequest);
    }
}
