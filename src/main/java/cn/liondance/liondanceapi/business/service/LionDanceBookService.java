package cn.liondance.liondanceapi.business.service;

import cn.liondance.liondanceapi.business.entity.LionDanceBook;
import cn.liondance.liondanceapi.business.entity.LionDanceBookChapter;
import cn.liondance.liondanceapi.common.BasicSearchModel;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

/** The interface Lion dance book service. */
public interface LionDanceBookService {
  /**
   * New book lion dance book.
   *
   * @param book the book
   * @return the lion dance book
   */
  LionDanceBook newBook(LionDanceBook book);

  /**
   * New chapter lion dance book chapter.
   *
   * @param lionDanceBookChapter the lion dance book chapter
   * @return the lion dance book chapter
   */
  LionDanceBookChapter newChapter(LionDanceBookChapter lionDanceBookChapter);

  @SneakyThrows
  ResponseBody<JSONObject> searchBook(BasicSearchModel pageModel);
}
