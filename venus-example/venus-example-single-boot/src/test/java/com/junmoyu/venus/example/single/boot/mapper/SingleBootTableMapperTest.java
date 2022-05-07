package com.junmoyu.venus.example.single.boot.mapper;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * SingleBootTable DAO testcase.
 * 不依赖 Spring 环境，直接连接 MySQL 进行测试，速度非常快
 *
 * @author moyu
 * @date 2022/5/7
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SingleBootTableMapperTest {

    private static SingleBootTableMapper mapper;

    @BeforeAll
    public static void setUpMybatisDatabase() {
        // SqlSessionFactoryBuilder 需要修改为 MybatisSqlSessionFactoryBuilder，否则无法测试 MyBatisPlus 自带的方法
        SqlSessionFactory builder = new MybatisSqlSessionFactoryBuilder().build(SingleBootTableMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/SingleBootTableMapperTestConfiguration.xml"));

        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        builder.getConfiguration().addInterceptor(interceptor);

        // you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(SingleBootTableMapper.class, builder.openSession(false));
    }

    @Test
    @Order(1)
    public void testSelectById(){
        SingleBootTable singleBootTable = mapper.selectById(1L);
        assertNotNull(singleBootTable);
        assertEquals(1L, (long) singleBootTable.getId());
    }

    @Test
    @Order(1)
    public void testBatchInsert() {
        List<SingleBootTable> vols = new ArrayList<>();
        SingleBootTable singleBootTable = new SingleBootTable();
        singleBootTable.setName("test");
        singleBootTable.setCode("test");
        singleBootTable.setSecret("test");
        singleBootTable.setDescription("test");
        singleBootTable.setIcon("");
        singleBootTable.setBanner("");
        singleBootTable.setSort(0);
        singleBootTable.setDeleted(false);
        singleBootTable.setCreateTime(new Date());
        singleBootTable.setUpdateTime(new Date());
        vols.add(singleBootTable);
        assertEquals(1, mapper.batchInsert(vols));
    }

    @Test
    @Order(2)
    public void testDeleteById(){
        assertEquals(1, mapper.deleteById(1L));
    }
}
