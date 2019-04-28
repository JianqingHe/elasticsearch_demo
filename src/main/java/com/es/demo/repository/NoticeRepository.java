package com.es.demo.repository;

import com.es.demo.entity.Notice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调用Elasticsearch方法构造查询语句
 *
 * @author hejq
 * @date 2019/4/26 14:51
 */
@Repository
public interface NoticeRepository extends ElasticsearchRepository<Notice, Long> {

    /**
     * 通过名称模糊搜索
     *
     * @param title 名称
     * @return 查询结果
     */
    List<Notice> findByTitleLike(String title);
}
