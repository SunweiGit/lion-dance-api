package cn.liondance.liondanceapi.business.repository;

import cn.liondance.liondanceapi.business.entity.LionDanceBookChapter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 内部基础架构中有个根据方法名的查询生成器机制，对于在存储 库的实体上构建约束查询很有用。该机制方法的前缀有find…By、
 * read…By、query…By、count…By和get…By
 * The interface User repository.
 *
 * @author sunwei
 */
public interface LionDanceBookChapterRepository
    extends JpaRepository<LionDanceBookChapter, String> {
  long countByBookId(String bookId);


  
  
}
