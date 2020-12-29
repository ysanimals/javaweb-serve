package com.nit.ssm.mapper;


import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.dto.MenuDTO;
import com.nit.ssm.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper {
    @Select("SELECT *, user_id AS `key` from tb_user ")
    List<UserDTO> listAll() throws Exception;

    /**
     * 查询用户总数
     * @return List
     */
    @Select({"<script>SELECT COUNT(*) FROM tb_user " +
            "WHERE role_id = 2 " +
            "<if test = 'userName != null'>AND user_name LIKE CONCAT('%', #{userName}, '%') </if>" +
            "<if test = 'userType != null'>AND user_type = #{userType} </if>" +
            "<if test = 'userPhone != null'>AND user_phone LIKE CONCAT('%', #{user_phone}, '%') </if>" +
            "</script>"})
    Long count4Table(@Param("userName")String userName,
                     @Param("userType")String userType,
                     @Param("userPhone")String phone) throws Exception;



    /**
     * 查询用户信息封装便于前端展示
     * @return List
     */
    @Select({"<script>SELECT user_id, user_name, user_phone, id_number, user_type, gmt_create, " +
            "user_id AS `key` FROM user " +
            "WHERE role_id = 2 " +
            "<if test = 'userName != null'>AND user_name LIKE CONCAT('%', #{userName}, '%') </if>" +
            "<if test = 'userType != null'>AND user_type = #{userType} </if>" +
            "<if test = 'userPhone != null'>AND user_phone LIKE CONCAT('%', #{user_phone}, '%') </if>" +
            "ORDER BY " +
            "<if test = 'sortField != null'>${sortField} ${sortOrder}, </if>" +
            "user_id DESC " +
            "LIMIT #{start}, #{length}" +
            "</script>"})
    List<UserDTO> list4Table(
            @Param("userName")String userName,
            @Param("userType")String userType,
            @Param("userPhone")String phone,
            @Param("start") Integer start,
            @Param("length") Integer length,
            @Param("sortField")String sortField,
            @Param("sortOrder")String sortOrder) throws Exception;

    @Insert("INSERT INTO user " +
            "(user_name, role_id, user_phone, user_pwd, user_card, user_type, gmt_create) " +
            "VALUES(#{entity.userName}, #{entity.roleId}, #{entity.userPhone}, #{entity.userPwd}, " +
            "#{entity.userCard}, #{entity.userType}, #{entity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    Integer add(@Param("entity") UserEntity entity) throws Exception;



    /**
     * @Description: 根据用户名获取用户信息
     * @Date: 2020/12/22
     */
    @Select({"SELECT *, user_id AS `key` " +
            "FROM tb_user " +
            "WHERE user_name = #{userName} LIMIT 1"
    })
    UserDTO getByUserName(@Param("userName") String userName) throws Exception;

    /**
     * @Description: 根据用户ID获取用户信息
     * @Date: 2020/12/22
     */
    @Select({"SELECT *, user_id AS `key` " +
            "FROM tb_user " +
            "WHERE user_id = #{userId} LIMIT 1 "
    })
    UserDTO getByUserId(@Param("userId") Integer userId) throws Exception;

    /**
     * @Description: 更新用户状态
     * @Date: 2020/12/26
     */
    @Update("UPDATE tb_user " +
            "SET user_type = #{userType} " +
            "WHERE user_id = #{userId} LIMIT 1")
    Integer updateUserType(
            @Param("userId")Integer userId,
            @Param("userType")Integer userType) throws Exception;


    /**
     * 查询用户权限树
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("SELECT a.* FROM menu a JOIN role_menu b " +
            "ON a.menu_id = b.menu_id AND b.role_id = #{roleId} " +
            "ORDER BY a.father_id, a.menu_sort ")
    List<MenuDTO> queryRoleMenu(@Param("roleId") Integer roleId) throws Exception;
}
