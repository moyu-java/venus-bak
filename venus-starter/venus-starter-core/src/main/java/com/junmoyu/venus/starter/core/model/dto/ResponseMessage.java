package com.junmoyu.venus.starter.core.model.dto;

/**
 * 响应消息
 *
 * @author moyu.jun
 * @date 2022/3/13
 */
public class ResponseMessage {

    public static final String SUCCESS = "success";

    public static final String FAILED = "failed";

    public static final String CREATE_SUCCESS = "create success";

    public static final String DELETE_SUCCESS = "delete success";

    public static final String UPDATE_SUCCESS = "update success";

    public static final String QUERY_SUCCESS = "query success";

    public static final String QUERY_FAILED = "query failed";

    public static final String DETAIL_SUCCESS = "detail success";

    public static final String DETAIL_FAILED = "detail failed";

    public static final String ENABLE_SUCCESS = "enable success";

    public static final String SYNC_SUCCESS = "sync success";

    public static final String SYNC_FAIL = "sync fail";

    public static final String ROLE_CREATE_ERROR = "can not create super role";

    public static final String DASHBOARD_QUERY_ERROR = "user info is empty";

    public static final String DASHBOARD_CREATE_USER_ERROR = "empty user info, please confirm";

    public static final String PLATFORM_LOGIN_SUCCESS = "login dashboard user success";

    public static final String PLATFORM_LOGIN_ERROR = "username or password error";

    public static final String LOGIN_USER_DISABLE_ERROR = "the user is disabled";

    public static final String PARAMETER_ERROR = "parameter error";

    public static final String UNIQUE_INDEX_CONFLICT_ERROR = "unique index conflict, please enter again";

    public static final String APP_KEY_NOT_EXIST_ERROR = "the appKey passed in does not exist";

    public static final String TOKEN_IS_ERROR = "token is error";

    public static final String TOKEN_HAS_NO_PERMISSION = "token has no permission";

    public static final String MENU_SUCCESS = "get menu and permission success";

    public static final String MENU_FAILED = "get menu and permission failed";

    public static final String SAVE_SUCCESS = "save success";

    public static final String SAVE_FAILED = "save failed";

    public static final String NOT_FOUND_EXCEPTION = "not found exception";
}
