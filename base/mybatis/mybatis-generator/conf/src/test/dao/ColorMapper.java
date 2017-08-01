package test.dao;

import test.model.Color;

public interface ColorMapper {
    int deleteByPrimaryKey(String COLOR_ID);

    int insert(Color record);

    int insertSelective(Color record);

    Color selectByPrimaryKey(String COLOR_ID);

    int updateByPrimaryKeySelective(Color record);

    int updateByPrimaryKey(Color record);
}