package com.nit.ssm.mapper;

import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.entity.SortEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SortMapper {
    /**
     * @Description: 获取表格数据
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @Select({"<script>SELECT " +
            "sort_id, sort_name, sort_info " +
            "FROM tb_sort " +
            "WHERE sort_name LIKE CONCAT('%', #{queryText}, '%') OR sort_info LIKE CONCAT('%', #{queryText}, '%') " +
            "ORDER BY " +
            "sort_id desc " +
            "LIMIT #{start}, #{length} " +
            "</script>"})
    List<SortDTO> list4Table(@Param("start") Integer start,
                             @Param("length") Integer length,
                             @Param("queryText") String queryText) throws Exception;

    /**
     * @Description: 获取表格数据记录的总条数
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @Select({"<script>SELECT COUNT(*) " +
            "FROM tb_sort " +
            "WHERE sort_name LIKE CONCAT('%', #{queryText}, '%') OR sort_info LIKE CONCAT('%', #{queryText}, '%') " +
            "</script>"})
    Integer count4Table(@Param("queryText") String queryText) throws Exception;

    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @Insert("INSERT INTO tb_sort ( " +
            "sort_name, sort_info) " +
            "VALUES (#{sortEntity.sortName}, #{sortEntity.sortInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "sortId", keyColumn = "sort_id")
    Integer add(@Param("sortEntity") SortEntity sortEntity) throws Exception;

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @Update("UPDATE tb_sort " +
            "SET sort_name = #{sortEntity.sortName}, " +
            "sort_info = #{sortEntity.sortInfo} " +
            "WHERE sort_id = #{sortEntity.sortId}")
    Integer edit(@Param("sortEntity") SortEntity sortEntity) throws Exception;


    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @Update({"DELETE FROM tb_sort WHERE sort_id = #{sortId}"})
    Integer remove(@Param("sortId") Integer sortId) throws Exception;

}
