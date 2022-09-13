package cn.liondance.liondanceapi.business.service.impl;

import cn.liondance.liondanceapi.business.entity.LionDanceBook;
import cn.liondance.liondanceapi.business.entity.LionDanceBookChapter;
import cn.liondance.liondanceapi.business.repository.LionDanceBookChapterRepository;
import cn.liondance.liondanceapi.business.repository.LionDanceBookRepository;
import cn.liondance.liondanceapi.business.service.LionDanceBookService;
import cn.liondance.liondanceapi.common.BasicSearchModel;
import cn.liondance.liondanceapi.utils.LionDanceIdGenerater;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sunwei
 */
@Slf4j
@Service
@AllArgsConstructor
public class LionDanceBookServiceImpl implements LionDanceBookService {
    private final LionDanceBookRepository        lionDanceBookRepository;
    private final LionDanceBookChapterRepository lionDanceBookChapterRepository;

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public LionDanceBook newBook(LionDanceBook entity) {
        entity.setId(LionDanceIdGenerater.randomBase64UUID());
        return lionDanceBookRepository.saveAndFlush(entity);
    }

    @Override
    public LionDanceBookChapter newChapter(LionDanceBookChapter entity) {
        entity.setId(LionDanceIdGenerater.randomBase64UUID());
        entity.setSort(lionDanceBookChapterRepository.countByBookId(entity.getBookId()));
        return lionDanceBookChapterRepository.saveAndFlush(entity);
    }

    @Override
    @SneakyThrows
    public ResponseBody<JSONObject> searchBook(BasicSearchModel basicSearchModel) {
        SearchRequest.Builder searchRequest = basicSearchModel.searchRequest();
        searchRequest.index("lion_dance_book");
        return basicSearchModel.search(elasticsearchClient, searchRequest);
    }
}
