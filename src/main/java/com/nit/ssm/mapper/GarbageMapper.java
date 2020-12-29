package com.nit.ssm.mapper;



import com.nit.ssm.entity.GarbageEntity;
import com.nit.ssm.dto.GarbageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public  interface  GarbageMapper {
    /**
     * 查询所有垃圾信息
     *
     * @return List
     */
    @Select("SELECT *, garbage_id AS `key` FROM garbage")
    List<GarbageDTO> listAllGarbage() throws Exception;

    /**
     * 查询垃圾信息总数
     * @return List
     */
    @Select({"<script> SELECT COUNT(*) FROM garbage " +
            "WHERE TRUE " +
            "<if test = 'garbageName != null'>AND garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'sortId != null'>AND sort_id = #{sortId} </if>" +
            "</script>"})
    Long count4Table(@Param("garbageName")String garbageName,
                     @Param("sortId")String sortId) throws Exception;

    /**
     * 查询垃圾信息封装便于前端展示
     * @return List
     */
    @Select({"<script> SELECT *, garbage_id AS `key` FROM garbage g " +
            "JOIN sort s ON g.sort_id = s.sort_id " +
            "WHERE TRUE " +
            "<if test = 'garbageName != null'>AND garbage_name LIKE CONCAT('%', #{garbageName}, '%') </if>" +
            "<if test = 'sortId != null'>AND sort_id = #{sortId} </if>" +
            "ORDER BY " +
            "<if test = 'sortField != null'>${sortField} ${sortOrder}, </if>" +
            "garbage_id ASC " +
            "LIMIT #{start}, #{length}" +
            "</script>"})
    List<GarbageDTO> list4Table(
            @Param("garbageName")String garbageName,
            @Param("sortId")String sortId,
            @Param("start") Integer start,
            @Param("length") Integer length,
            @Param("sortField")String sortField,
            @Param("sortOrder")String sortOrder) throws Exception;

    /**
     * 查询一条垃圾信息
     */
    @Select("SELECT *, garbage_id AS `key` FROM garbage " +
            "WHERE garbage_id = #{garbageId} LIMIT 1")
    GarbageDTO getGarbageById(@Param("garbageId")Integer garbageId) throws Exception;

    /**
     * 插入一条垃圾信息
     * @return Integer
     */
    @Insert("INSERT INTO garbage (" +
            "image_url, sort_id, garbage_name, gmt_create) " +
            "VALUES (#{entity.imageUrl}, #{entity.sortId}, " +
            "#{entity.garbageName}, #{entity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "garbageId", keyColumn = "garbage_id")
    Integer add(@Param("entity")GarbageEntity garbageEntity) throws Exception;

    /**
     * 更新一条垃圾信息
     * @return Integer
     */
    @Update("UPDATE garbage " +
            "SET image_url = #{entity.imageUrl}, sort_id = #{entity.sortId}, " +
            "garbage_name = #{entity.garbageName}" +
            "WHERE garbage_id = #{entity.garbageId}")
    Integer update(@Param("entity")GarbageEntity garbageEntity) throws Exception;

    /**
     * 删除一条垃圾信息
     * @return Integer
     */
    @Delete("DELETE FROM garbage " +
            "WHERE garbage_id = #{garbageId}")
    Integer remove(@Param("garbageId")Long garbageId) throws Exception;

    /**
     * 获取一条随机垃圾信息
     */
    @Select("SELECT garbage_id, garbage_name, image_url " +
            "FROM garbage AS g1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(garbage_id) FROM garbage)) AS id) AS g2 " +
            "WHERE g1.garbage_id >= g2.id LIMIT 1")
    GarbageDTO getRandom() throws Exception;

    /**
     * 获取垃圾类别和处理方式
     */
    @Select("SELECT sort_name, sort_info FROM sort " +
            "WHERE sort_id = #{sortId} LIMIT 1")
    GarbageDTO getSort(@Param("sortId")Integer sortId) throws Exception;
}
