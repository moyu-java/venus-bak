package com.junmoyu.venus.example.wx.miniapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.junmoyu.venus.starter.core.model.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序测试接口
 *
 * @author moyu.jun
 * @date 2022/3/27
 */
@Slf4j
@RestController
@RequestMapping("wx")
@RequiredArgsConstructor
public class WeChatController {

    private final WxMaService wxMaService;

    @GetMapping("/token")
    public Response<String> getAccessToken() {
        String accessToken = null;
        try {
            accessToken = wxMaService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return Response.success(accessToken);
    }

}
