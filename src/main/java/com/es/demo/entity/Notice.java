package com.es.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 公告
 *
 * @author hejq
 * @date 2019/4/26 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "notice_info", type = "doc")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title = "";

    /**
     * 公告发布时间
     */
    private String time;

    /**
     * 内容
     */
    private String text = "";
}
