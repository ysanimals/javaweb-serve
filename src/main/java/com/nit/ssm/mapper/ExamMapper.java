package com.nit.ssm.mapper;

import com.nit.ssm.entity.ExamEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ExamMapper{
    /**
     * 插入一条回答信息
     * @return Integer
     */
    @Insert("INSERT INTO tb_exam (" +
            "exam_sn, garbage_id, user_id, sort_id, answer_id, gmt_create) " +
            "VALUES (#{entity.examSn}, #{entity.garbageId}, #{entity.userId}, " +
            "#{entity.sortId}, #{entity.answerId}, #{entity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "examId", keyColumn = "exam_id")
    Integer add(@Param("entity") ExamEntity examEntity) throws Exception;
}
