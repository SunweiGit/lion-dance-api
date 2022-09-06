package cn.liondance.liondanceapi.common;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch.core.ScrollRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.core.TimeValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunwei
 */
@Slf4j
@ApiModel
@Data
public class BasicSearchModel {
  private Integer size = 30;
  private String scrollId;
  private String orderBy;
  private String exclude;
  private String include;

  public SearchRequest.Builder searchRequest() {
    SearchRequest.Builder searchRequest = new SearchRequest.Builder();
    if (StringUtils.isNotEmpty(exclude)) {
      searchRequest.source(s -> s.filter(f -> f.excludes(List.of(exclude.split(",")))));
    }
    if (StringUtils.isNotEmpty(include)) {
      searchRequest.source(s -> s.filter(f -> f.includes(List.of(include.split(",")))));
    }
    searchRequest.from(0).size(size);

    searchRequest.scroll(Time.of(o -> o.time(new TimeValue(5, TimeUnit.MINUTES).toString())));

    if (StringUtils.isNotEmpty(orderBy)) {
      Arrays.stream(orderBy.split(";"))
          .forEach(
              o -> {
                String[] sort = o.split(",");
                if (SortOrder.Asc.name().equalsIgnoreCase(sort[1])) {
                  searchRequest.sort(s -> s.field(f -> f.field(sort[0]).order(SortOrder.Asc)));
                } else {
                  searchRequest.sort(s -> s.field(f -> f.field(sort[0]).order(SortOrder.Desc)));
                }
              });
    }
    return searchRequest;
  }

  public ScrollRequest.Builder scrollRequest() {
    if (StringUtils.isNotEmpty(scrollId)) {
      ScrollRequest.Builder scrollRequest = new ScrollRequest.Builder();
      scrollRequest.scrollId(scrollId);
      return scrollRequest;
    } else {
      throw new RuntimeException("scrollId isEmpty");
    }
  }

  @SneakyThrows
  public ResponseBody<JSONObject> search(
      ElasticsearchClient elasticsearchClient, SearchRequest.Builder searchRequest) {
    if (StringUtils.isNotEmpty(scrollId)) {
      try {
        return elasticsearchClient.scroll(
            this.scrollRequest()
                .scroll(Time.of(o -> o.time(new TimeValue(5, TimeUnit.MINUTES).toString())))
                .scrollId(scrollId)
                .build(),
            JSONObject.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return elasticsearchClient.search(searchRequest.build(), JSONObject.class);
  }

  @SneakyThrows
  public JSONObject getResult(ResponseBody<JSONObject> searchResponse, Class<?> clazz) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(
        "scrollId",
        StringUtils.isEmpty(searchResponse.scrollId()) ? "" : searchResponse.scrollId());
    ArrayList list = new ArrayList();
    HitsMetadata<JSONObject> hitsMetadata = searchResponse.hits();
    hitsMetadata.hits().forEach(o -> list.add(o.source().toJavaObject(clazz)));
    jsonObject.put("metadata", list);
    jsonObject.put("totalValue", hitsMetadata.total().value());
    jsonObject.put("totalRelation", hitsMetadata.total().relation().jsonValue());
    return jsonObject;
  }
}
