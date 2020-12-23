package com.hc.mybatis.spring.entity;

import lombok.Data;

/**
 * @classname OrgEntity
 * @author: Li Xiaobing
 * @date: 2020/12/23 14:05
 */
@Data
public class OrgEntity {
    private Long id;
    private Long parentId;
    private Integer seq;
    private String name;

    public OrgEntity(Long id, Long parentId, Integer seq, String name) {
        this.id = id;
        this.parentId = parentId;
        this.seq = seq;
        this.name = name;
    }
}
