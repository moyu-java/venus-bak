package com.junmoyu.venus.example.single.boot.controller;
import java.util.Date;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.model.dto.Response;
import com.junmoyu.venus.starter.core.model.dto.ResponseCode;
import com.junmoyu.venus.starter.core.model.dto.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author moyu
 * @date 2022/5/9
 */
@ExtendWith(MockitoExtension.class)
public class SingleBootControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SingleBootController singleBootController;

    @Mock
    private SingleBootTableService singleBootTableService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(singleBootController).build();
    }

    @Test
    public void list() {

    }

    @Test
    public void page() {
    }

    @Test
    public void getObject() {
        SingleBootTable singleBootTable = new SingleBootTable();
        singleBootTable.setId(1L);
        singleBootTable.setName("test");
        singleBootTable.setCode("test");
        singleBootTable.setDeleted(false);
        singleBootTable.setCreateTime(new Date());
        singleBootTable.setUpdateTime(new Date());

        when(singleBootTableService.getObject(1L)).thenReturn(singleBootTable);

        Response<SingleBootTable> result = singleBootController.getObject(1L);
        assertThat(result.getCode(), is(ResponseCode.SUCCESS));
        assertThat(result.getMessage(), is(ResponseMessage.SUCCESS));
        assertThat(result.getData(), is(singleBootTable));
    }

    @Test
    public void addObject() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}