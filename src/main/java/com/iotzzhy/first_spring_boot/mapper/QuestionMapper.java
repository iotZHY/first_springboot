package com.iotzzhy.first_spring_boot.mapper;

import com.iotzzhy.first_spring_boot.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creater,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creater},#{tag})")
    void create(Question question);
}
