package cn.liondance.liondanceapi.business.service.impl;

import cn.liondance.liondanceapi.business.service.LionDanceRecommendService;
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
 * The type Topic service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class LionDanceRecommendServiceImpl implements LionDanceRecommendService {

    private final ElasticsearchClient elasticsearchClient;


    @Override
    public ResponseBody<JSONObject> search(BasicSearchModel basicSearchModel, String searchContent) {
        SearchRequest.Builder searchRequest = basicSearchModel.searchRequest();
        if (StringUtils.isNotEmpty(searchContent)) {
            List<Query> list = new ArrayList<>();
            list.add(new Query.Builder()
                    .wildcard(t -> t
                            .field("name.keyword")
                            .value("*" + searchContent + "*")
                    )
                    .build());
            list.add(new Query.Builder()
                    .wildcard(t -> t
                            .field("description.keyword")
                            .value("*" + searchContent + "*")
                    )
                    .build());
            searchRequest.query(q -> q.bool(b1 -> b1
                    .should(list)));
        }
        searchRequest.index(IndexVariable.lion_dance_recommend);
        return basicSearchModel.search(elasticsearchClient, searchRequest);
    }
}
