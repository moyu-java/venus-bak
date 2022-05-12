package com.junmoyu.venus.example.single.boot.controller;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.model.dto.Response;
import com.junmoyu.venus.starter.redis.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisControllerTest {

    @Mock
    private SingleBootTableService mockSingleBootTableService;
    @Mock
    private RedisService mockRedisService;

    private RedisController redisControllerUnderTest;

    @BeforeEach
    void setUp() {
        redisControllerUnderTest = new RedisController(mockSingleBootTableService, mockRedisService);
    }

    @Test
    void testGetObject() {
        // Setup
        final Response<SingleBootTable> expectedResult = new Response<>(0, "message",
                new SingleBootTable(0L, "name", "code", "secret", "description", "icon", "banner", 0, false,
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()));

        // Configure RedisService.getCacheMapValue(...).
        final SingleBootTable singleBootTable = new SingleBootTable(0L, "name", "code", "secret", "description", "icon",
                "banner", 0, false, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockRedisService.getCacheMapValue("venus:example:single-boot", "hKey")).thenReturn(singleBootTable);

        // Configure SingleBootTableService.getObject(...).
        final SingleBootTable singleBootTable1 = new SingleBootTable(0L, "name", "code", "secret", "description",
                "icon", "banner", 0, false, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockSingleBootTableService.getObject(0L)).thenReturn(singleBootTable1);

        // Run the test
        final Response<SingleBootTable> result = redisControllerUnderTest.getObject(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockRedisService).setCacheMapValue("venus:example:single-boot", "hKey",
                new SingleBootTable(0L, "name", "code", "secret", "description", "icon", "banner", 0, false,
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()));
    }
}
