package com.es.demo.service.impl;

import cn.hutool.core.date.format.FastDateFormat;
import com.es.demo.entity.Notice;
import com.es.demo.repository.NoticeRepository;
import com.es.demo.service.NoticeService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 消息方法实现
 *
 * @author hejq
 * @date 2019/4/26 15:03
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    /**
     * 保存消息内容
     *
     * @param id    id
     * @param title 标题
     */
    @Override
    public Notice save(Long id, String title) {
        Notice article = new Notice();
        FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
        article.setId(id);
        article.setTitle(title);
        article.setTime(dateFormat.format(new Date()));
        article.setText("springboot整合elasticsearch");
        return noticeRepository.save(article);
    }

    /**
     * 通过标题和分页信息搜索消息
     *
     * @param keyword    关键词
     * @param pageable 分页信息
     * @return 搜索结果
     */
    @Override
    public Page<Notice> searchPageByKeyword(String keyword, Pageable pageable) {
        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("title", keyword))
                .withPageable(pageable)
                .build();
        log.info("sql-> {}", new Gson().toJson(searchQuery));
        return noticeRepository.search(searchQuery);
    }

    /**
     * 通过id查询消息数据
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Override
    public Notice getById(Long id) {
        Optional<Notice> optionalNotice = noticeRepository.findById(id);
        return optionalNotice.orElse(new Notice());
    }

    /**
     * 查询所有数据
     *
     * @return 消息集合
     */
    @Override
    public List<Notice> getAll() {
        return Lists.newArrayList(noticeRepository.findAll());
    }

    /**
     * 通过标题分页搜索消息信息
     *
     * @param title 标题
     * @return 查询结果
     */
    @Override
    public List<Notice> searchByTitle(String title) {
        return noticeRepository.findByTitleLike(title);
    }
}
