package com.junmoyu.venus.example.single.boot.service;

import com.junmoyu.venus.example.single.boot.mapper.SingleBootTableMapper;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.impl.SingleBootTableServiceImpl;
import com.junmoyu.venus.starter.core.exception.VenusException;
import com.junmoyu.venus.starter.core.model.dto.ResponseMessage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author moyu
 * @date 2022/5/7
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SingleBootTableServiceTest {

    @InjectMocks
    private SingleBootTableServiceImpl singleBootTableService;

    @Mock
    private SingleBootTableMapper singleBootTableMapper;

    private final SingleBootTable singleBootTable = buildSingleBootTable();

    @Test
    @Order(1)
    public  void getObject() {


    }

    @Test
    @Order(1)
    public  void addObject() {
        testCreateParameterError();
        testCreateSuccess();
    }

    @Test
    @Order(1)
    public  void delete() {
    }

    @Test
    @Order(1)
    public  void update() {
    }

    @Test
    @Order(1)
    public  void list() {
    }

    @Test
    @Order(1)
    public  void page() {
    }

    private SingleBootTable buildSingleBootTable() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return SingleBootTable.builder()
                .id(1L)
                .name("test")
                .code("test")
                .secret("test")
                .banner("banner")
                .icon("icon")
                .deleted(false)
                .createTime(now)
                .updateTime(now)
                .description("desc")
                .sort(1)
                .build();
    }

    private void testCreateParameterError(){
        singleBootTable.setName(null);
        VenusException venusExceptionName = assertThrows(VenusException.class, () -> {
            singleBootTableService.addObject(singleBootTable);
        });
        assertEquals(ResponseMessage.REQUIRED_PARAM_IS_NULL, venusExceptionName.getMessage());
        singleBootTable.setName("test");
        singleBootTable.setCode(null);
        VenusException venusExceptionCode = assertThrows(VenusException.class, () -> {
            singleBootTableService.addObject(singleBootTable);
        });
        assertEquals(ResponseMessage.REQUIRED_PARAM_IS_NULL, venusExceptionCode.getMessage());
        singleBootTable.setCode("test");
    }

    public void testCreateSuccess(){
        assertDoesNotThrow(()->{
            when(singleBootTableMapper.insert(any())).thenReturn(1);
            int i = singleBootTableService.addObject(singleBootTable);
            assertEquals(1, i);
        });
    }
}