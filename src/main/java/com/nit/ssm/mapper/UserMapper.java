package com.nit.ssm.mapper;


import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper {
    @Select("SELECT *, user_id AS `key` from tb_user ")
    List<UserDTO> listAll() throws Exception;


    /**
     * @Description: 新增记录
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Insert("INSERT INTO tb_user (" +
            "user_name, user_phone, user_pwd, user_card, user_status, user_type, gmt_create) "+
            "VALUES (#{userEntity.userName}, #{userEntity.userPhone},#{userEntity.userPwd}, #{userEntity.userCard}, " +
            "#{userEntity.userStatus}, #{userEntity.userType}, #{userEntity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    Integer add(@Param("userEntity")UserEntity userEntity) throws Exception;

    /**
     * @Description: 更新记录
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Update("Update tb_user " +
            "SET user_name = #{userEntity.userName}, " +
            "user_status = #{userEntity.userStatus}, "+
            "user_type = #{userEntity.userType}, "+
            "gmt_create = #{userEntity.gmtCreate}, "+
            "WHERE user_id = #{userEntity.userId}")
    Integer update(@Param("userEntity") UserEntity userEntity) throws Exception;

    /**
     * @Description: 删除记录
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Delete("DELETE FROM tb_user WHERE user_id = #{userId}")
    Integer remove(@Param("userId") Integer userId) throws Exception;

    /**
     * @Description: 重置密码
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Update({"UPDATE tb_user "+
            "SET user_pwd = #{userPwd} " +
            "WHERE user_id = #{userId}"})
    Integer resetPwd(@Param("userId") Integer userId,
                     @Param("userPwd") String userPwd) throws Exception;

    /**
     * @Description: 根据用户登录名称获取用户信息
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Select("SELECT * " +
            "FROM tb_user " +
            "WHERE user_name = #{userName} LIMIT 1")
    UserDTO getByUserName(@Param("userName") String userName) throws Exception;

    /**
     * @Description: 根据用户ID获取用户信息
     * @Author: JYX
     * @Date: 2020/12/14 19:04
     */
    @Select({"SELECT * " +
            "FROM tb_user " +
            "WHERE user_id = #{userId}"})
    UserDTO getByUserId(@Param("userId") Integer userId) throws Exception;



}
