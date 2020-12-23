package com.hc.mybatis.spring.dao;

import com.hc.mybatis.spring.entity.OrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年12月21日
 */
@Mapper
public interface OrgMapper {

    void batchInsert(List<OrgEntity> orgEntities);

}
