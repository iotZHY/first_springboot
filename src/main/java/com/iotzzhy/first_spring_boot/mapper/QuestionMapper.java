package com.iotzzhy.first_spring_boot.mapper;

import com.iotzzhy.first_spring_boot.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creater,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creater},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(int offset,int size);

    @Select("select count(*) from question")
    int count();
}
