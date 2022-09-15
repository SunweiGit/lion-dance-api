package cn.liondance.liondanceapi.business.service;

import cn.liondance.liondanceapi.common.BasicSearchModel;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;

/**
 * The interface Topic service.
 */
public interface LionDanceRecommendService {

    ResponseBody<JSONObject> search(BasicSearchModel basicSearchModel, String searchContent);
}
