<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jqk.mapper.UserMapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="com.jqk.vo.User">
        <!--对于字段名和实体类的属性名相同的情况下，可以不写入resultMap中 -->
        <id column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="age" property="age"/>
    </resultMap>

    <select id="getAllStu" resultMap="BaseResultMap">
        select * from user
        <where>
            <if test="id != null">
                id = #{id}
            </if>
        </where>
    </select>

    <select id="selectForeachArray" resultMap="BaseResultMap">
        select * from user
        <if test="array != null and array.length > 0">
            where id in
            <foreach collection="array" open="(" close=")" item="myid" separator=",">
                #{myid}
            </foreach>
        </if>
    </select>


    <select id="selectForeachList2" resultMap="BaseResultMap">
        select * from user
                <if test="list != null and list.size > 0">
                    where id in
                    <foreach collection="list" open="(" close=")" item="use" separator=",">
                       #{use.id}
                    </foreach>
                    and age > 10
                </if>

    </select>



</mapper>
