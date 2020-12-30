package com.nit.ssm.mapper;

import com.nit.ssm.dto.ExamDTO;
import com.nit.ssm.entity.ExamEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * list答题信息
     * @return list
     */
    @Select("<script>select *, e.exam_id AS `key`, u.user_name AS `userName`, g.garbage_name AS `garbageName` " +
            "from exam e " +
            "Join user u on e.user_id = u.user_id " +
            "Join garbage g on e.garbage_id = g.garbage_id " +
            "where true " +
            "<if test = 'userName != null'>AND u.user_name LIKE CONCAT('%', #{userName}, '%') </if>" +
            "<if test = 'garbageName != null'>AND g.garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'examSn != null'>AND e.exam_sn LIKE CONCAT('%', #{examSn}, '%') </if>" +
            "ORDER BY " +
            "<if test = 'sortField != null'>${sortField} ${sortOrder}, </if>" +
            "exam_sn ASC " +
            "LIMIT #{start}, #{length}" +
            "</script>")
    List<ExamDTO> list4Table(
            @Param("userName")String userName,
            @Param("garbageName")String garbageName,
            @Param("examSn")String examSn,
            @Param("start") Integer start,
            @Param("length") Integer length,
            @Param("sortField")String sortField,
            @Param("sortOrder")String sortOrder) throws Exception;
    @Select("<script>select count(*) from exam e " +
            "Join user u on e.user_id = u.user_id " +
            "Join garbage g on e.garbage_id = g.garbage_id " +
            "where true " +
            "<if test = 'userName != null'>AND u.user_name LIKE CONCAT('%', #{userName}, '%') </if>" +
            "<if test = 'garbageName != null'>AND g.garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'examSn != null'>AND e.exam_sn LIKE CONCAT('%', #{examSn}, '%') </if>" +
            "</script>")
    Long count4Table(@Param("userName")String userName,
                     @Param("garbageName")String garbageName,
                     @Param("examSn")String examSn) throws Exception;
}