package com.es.demo.controller;

import com.es.demo.core.ResultMap;
import com.es.demo.entity.Notice;
import com.es.demo.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * notice 接口
 *
 * @author hejq
 * @date 2019/4/26 14:51
 */
@RestController
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 保存
     *
     * @param id id
     * @param title 标题
     * @return 保存结果
     */
    @PostMapping("/save")
    public ResultMap save(Long id, String title) {
        log.info("save, 参数： {}， {}", id, title);
        return ResultMap.success(noticeService.save(id, title));
    }

    /**
     * 通过关键字分页搜索Notice信息
     *
     * @param keyword   搜索标题
     * @param pageable page = 第几页参数, value = 每页显示条数
     */
    @GetMapping("/search")
    public ResultMap search(String keyword, @PageableDefault(page = 1) Pageable pageable) {
        Page<Notice> noticePage = noticeService.searchPageByKeyword(keyword, pageable);
        return ResultMap.success(noticePage);
    }

    /**
     * 通过id查询消息信息
     *
     * @param id 主键id
     * @return 查询结果
     */
    @GetMapping("/getById/{id}")
    public ResultMap getById(@PathVariable("id") Long id) {
        return ResultMap.success(noticeService.getById(id));
    }

    /**
     * 通过id查询消息信息
     *
     * @return 查询结果
     */
    @GetMapping("/getAll")
    public ResultMap getAll() {
        return ResultMap.success(noticeService.getAll());
    }

    /**
     * 通过标题模糊查询消息信息
     *
     * @param title   搜索标题
     */
    @GetMapping("/searchByTitle")
    public ResultMap searchByTitle(String title) {
        List<Notice> noticeList = noticeService.searchByTitle(title);
        return ResultMap.success(noticeList);
    }
}
