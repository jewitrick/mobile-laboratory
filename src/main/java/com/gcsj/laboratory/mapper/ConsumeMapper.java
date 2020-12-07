package com.gcsj.laboratory.mapper;

import com.gcsj.laboratory.pojo.Consume;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Jewitrick
 * @version 1.0.0
 * @ClassName ConsumeMapper.java
 * @Description TODO
 * @createTime 2020/12/5,21:41
 */
public interface ConsumeMapper extends Mapper<Consume> {
    @Update("update consume set stock=stock-#{cost_count} where id = #{consume_id}")
    void updateStock(@Param("consume_id") Long consume_id, @Param("cost_count") Double cost_count);

    @Select("select * from consume where id = #{id}")
    Consume findConsumeByConsumeId(long id);
}
