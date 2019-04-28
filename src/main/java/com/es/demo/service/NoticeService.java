package com.es.demo.service;

import com.es.demo.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 消息接口
 *
 * @author hejq
 * @date 2019/4/26 15:03
 */
public interface NoticeService {

    /**
     * 保存消息内容
     *
     * @param id id
     * @param title 标题
     * @return 存储后返回信息
     */
    Notice save(Long id, String title);

    /**
     * 通过标题和分页信息搜索消息
     *
     * @param keyword 标题
     * @param pageable 分页信息
     * @return 搜索结果
     */
    Page<Notice> searchPageByKeyword(String keyword, Pageable pageable);

    /**
     * 通过id查询消息数据
     *
     * @param id 主键id
     * @return 查询结果
     */
    Notice getById(Long id);

    /**
     * 查询所有数据
     *
     * @return 消息集合
     */
    List<Notice> getAll();

    /**
     * 通过标题分页搜索消息信息
     *
     * @param title 标题
     * @return 查询结果
     */
    List<Notice> searchByTitle(String title);
}
