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
     * 查看所有信息
     * @return Integer
     */
    @Select("<script>SELECT COUNT(*) FROM tb_exam e " +
            "JOIN tb_user u ON e.user_id = u.user_id " +
            "JOIN garbage g ON e.garbage_id = g.garbage_id " +
            "WHERE TRUE " +
            "<if test = 'userName != null'>AND u.user_name LIKE CONCAT('%', #{userName}, '%') </if>" +
            "<if test = 'garbageName != null'>AND g.garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'examSn != null'>AND e.exam_sn LIKE CONCAT('%', #{examSn}, '%') </if>" +
            "</script>")
    Long count4Table(@Param("userName")String userName,
                     @Param("garbageName")String garbageName,
                     @Param("examSn")String examSn) throws Exception;

    /**
     * list答题信息
     * @return list
     */
    @Select("<script>SELECT *, e.exam_id AS `key`, u.user_name AS `userName`, g.garbage_name AS `garbageName` " +
            "FROM tb_exam e " +
            "JOIN tb_user u ON e.user_id = u.user_id " +
            "JOIN garbage g ON e.garbage_id = g.garbage_id " +
            "WHERE TRUE " +
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

    @Select("<script>SELECT COUNT(*) FROM " +
            "(SELECT (CASE WHEN answer_id IS NULL THEN 0 WHEN answer_id = e.sort_id THEN 1 ELSE 2 END) " +
            "AS `answerState` FROM tb_exam e " +
            "JOIN garbage g ON e.garbage_id = g.garbage_id " +
            "AND user_id = #{userId} " +
            "<if test = 'garbageName != null'>AND g.garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'examSn != null'>AND e.exam_sn LIKE CONCAT('%', #{examSn}, '%') </if>" +
            ") n WHERE TRUE " +
            "<if test = 'answerState != null'>AND answerState = #{answerState} </if>" +
            "</script>")
    Long countUserTable(
            @Param("userId")Integer userId,
            @Param("answerState")String answerState,
            @Param("garbageName")String garbageName,
            @Param("examSn")String examSn) throws Exception;

    /**
     * list答题信息
     * @return list
     */
    @Select("<script>SELECT * FROM (" +
            "SELECT e.garbage_id, e.sort_id, e.answer_id, e.exam_id AS `key`, " +
            "exam_sn, g.garbage_name AS `garbageName`, " +
            "(CASE WHEN answer_id IS NULL THEN 0 WHEN answer_id = e.sort_id THEN 1 ELSE 2 END) AS `answerState` " +
            "FROM tb_exam e " +
            "JOIN garbage g ON e.garbage_id = g.garbage_id " +
            "WHERE e.user_id = #{userId} " +
            "<if test = 'garbageName != null'>AND g.garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'examSn != null'>AND e.exam_sn LIKE CONCAT('%', #{examSn}, '%') </if>" +
            ") n WHERE TRUE " +
            "<if test = 'answerState != null'>AND answerState = #{answerState} </if>" +
            "ORDER BY " +
            "<if test = 'sortField != null'>${sortField} ${sortOrder}, </if>" +
            "exam_sn ASC " +
            "LIMIT #{start}, #{length}" +
            "</script>")
    List<ExamDTO> listUserTable(
            @Param("userId")Integer userId,
            @Param("answerState")String answerState,
            @Param("garbageName")String garbageName,
            @Param("examSn")String examSn,
            @Param("start") Integer start,
            @Param("length") Integer length,
            @Param("sortField")String sortField,
            @Param("sortOrder")String sortOrder) throws Exception;
}
