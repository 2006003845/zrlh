/**
 * Copyright © 2013 CreditEase. All rights reserved.
 */


/**
 * 
 * @author noah.zheng
 * 
 */
public final class WSConfig {

    private WSConfig() {
        // No public constructor
    }

    public static final float SIGN_LIMIT = 500f;

    public static final int IS_DEBUG = 0;// 是否为发布状态，0：发布， 1：测试

    public static final String DESKEY = "H05JDz46sV2SZ7l5LBHHC2DO";
    // dsnvvwwxxicaphrgqxlknesdvierckvfgmtt

    public static final String HTTP_TAG = "http://";

    // 1qaz
    // crediteasemvc.com:8090//http://zz.yixin.com/
    // 10.105.74.125:8080
    // 192.168.1.105:8080
    // 115.29.41.217:8070
    // 115.28.158.2:8080
    // 114.215.141.245:8070
    // 正式
    public static final String ROOT_URL = "http://crediteasemvc.com:8090/smallpos";
    public static final String IMAGE_ROOT_URL = "http://crediteasemvc.com:8090";
    public static final String GAME_ROOT_URL = "http://114.215.150.204";

    // 测试
    // public static final String ROOT_URL =
    // "http://115.28.158.2:8080/smallpos";
    // public static final String IMAGE_ROOT_URL = "http://115.28.158.2:8080";
    // public static final String GAME_ROOT_URL = "http://115.28.158.2:8014";

    // 电脑
    // public static final String ROOT_URL =
    // "http://192.168.108.108:8080//smallpos";
    // public static final String IMAGE_ROOT_URL =
    // "http://192.168.108.108:8080";
    // public static final String GAME_ROOT_URL = "http://115.28.158.2:8014";

    // 渠道
    // public static final String ROOT_URL =
    // "http://114.215.141.245:8090/smallpos";
    // public static final String IMAGE_ROOT_URL =
    // "http://114.215.141.245:8090";
    // public static final String GAME_ROOT_URL = "http://115.28.158.2:8014";

    // 卫正
//    public static final String ROOT_URL = "http://10.106.40.49:8080/smallpos";
//    public static final String IMAGE_ROOT_URL = "http://10.106.40.49:8080";
//    public static final String GAME_ROOT_URL = "http://115.28.158.2:8014";

    /**
     * method : sendVerifyNumForReg key : phone_number value : 13488782626
     * 
     * 返回： 成功 ： {ret: "success", msg:"232v42"} 失败 ： {ret: "failed",
     * msg:"用户已经存在"}
     */
    public static final String WS_USERS_GET_VERIFY_CODE_URL = ROOT_URL + "/sendVerifyNum";

    /***
     * method : userRegister key : userjson value : user对象的json格式
     * 
     * 返回： 成功 ： 用户json对象 失败 ： {ret: "failed", msg:"用户注册失败"}
     */
    public static final String WS_USERS_USER_REGISTER_URL = ROOT_URL + "/userRegister";

    /**
     * 需要用户手机号，密码 method : userLogin key : userjson value : user对象的json格式
     * 
     * 返回： 成功 ： 用户json对象 失败 ： {ret: "failed", msg:"密码不正确"}；{ret: "failed",
     * msg:"密码不正确"}
     */
    public static final String WS_USERS_USER_LOGIN_URL = ROOT_URL + "/userLogin";

    public static final String WS_USERS_USER_LOGOUT_URL = ROOT_URL + "/userLoginOut";

    /**
     * method : saveTrade key : tradejson value : 交易对象Trade的json格式
     */
    public static final String WS_TRADES_USER_TRADE_URL = ROOT_URL + "/saveTrade";

    /**
     * 根据trade_id冲正交易
     */
    public static final String WS_TRADES_USER_TRADE_REVERSE_URL = ROOT_URL + "/tradeReverse";

    /**
     * trade_num
     */
    public static final String WS_TRADES_GET_TRADE_BY_TRADE_NUM = ROOT_URL + "/selectTradeByTradeNum";

    /**
     * 
     */
    public static final String WS_TRADES_UPDATE_TRADE_STATUS = ROOT_URL + "/updateTradeStatus";

    /**
     * 上传用户签名 需要：tradejson里的trade_id，与签名图片 method : uploadUserSign key :
     * trade_id:12121， user_sign value : 交易对象Trade的json格式，签名图片
     * 
     * 注：保存交易进行支付中需要上传图片，需要添加：enctype="multipart/form-data" 示例： <form
     * action="http://localhost:8080/smallpos/saveTrade" method="post"
     * enctype="multipart/form-data">
     * 
     * 返回： 成功 ： {ret: "success", msg:"保存成功"} 失败 ： {ret: "failed", msg:"保存失败"}
     */
    public static final String WS_TRADES_UPLOAD_USER_SIGN_URL = ROOT_URL + "/uploadUserSign";

    /**
     * 更换商户手机号 需要用户手机号，密码，新手机号 method : changePhoneNum key : json value :
     * user对象的json格式
     * 
     * 返回： 成功 ： {ret: "success", msg:"变更成功"} 失败 ： {ret: "failed",
     * msg:"新用户名已存在"};{ret: "failed", msg:"密码不正确"};{ret: "failed",
     * msg:"变更失败"};{ret: "failed", msg:"用户不存在"};
     */
    public static final String WS_USERS_CHANGE_PHONE_NUMBER_URL = ROOT_URL + "/changePhoneNum";

    /**
     * 变更商户银行卡号 需要新的银行卡号， 用户手机号，密码 method : changeBankCard key : userjson value
     * : user对象的json格式
     * 
     * 返回： 成功 ： {ret: "success", msg:"变更成功"} 失败 ： {ret: "failed",
     * msg:"密码不正确"};{ret: "failed", msg:"变更失败"};{ret: "failed", msg:"用户不存在"}
     */
    public static final String WS_USERS_CHANGE_BANK_CARD_URL = ROOT_URL + "/changeBankCard";

    /**
     * 找回密码 需要用户手机号，新密码 method : findBackPassWord key : userjson value :
     * user对象的json格式
     * 
     * 返回： 成功 ： {ret: "success", msg:"变更成功"} 失败 ： {ret: "failed",
     * msg:"变更失败"};{ret: "failed", msg:"用户不存在"}
     */
    public static final String WS_USERS_CHANGE_PASSWORD_URL = ROOT_URL + "/changePassword";

    public static final String WS_USERS_UPLOAD_PORTRAIT_URL = ROOT_URL + "/uploadUserHeadPic";

    public static final String WS_USERS_FEEDBACK_URL = ROOT_URL + "/saveFeedBack";

    /**
     * 发送交易数据到用户手机 需要：用户手机号，交易流水号 method : sendTradeInfo key :
     * phone_number\trade_num value : '13488783626'\'11111111111'
     * 
     * 返回： 成功： : "{ret: \"success\", msg:\"交易信息发送成功\"}"; 失败： :
     * "{ret: \"success\", msg:\"交易信息发送失败\"}"
     * ;"{ret: \"success\", msg:\"该笔交易不存在\"}"
     * ;"{ret: \"success\", msg:\"用户不存在\"}";
     */
    public static final String WS_USERS_SEND_RECEIPT_URL = ROOT_URL + "/sendTradeInfo";

    /*
     * 
     * 6. Get trades by modify time url: /selectTradeByLastTime params:
     * {modify_time:} method:post
     */
    public static final String WS_TRADES_GET_BY_MODIFY_TIME_URL = ROOT_URL + "/selectTradeByLastTime";

    /**
     * 根据前台20个最新的交易ID，与后台20个最新的交易ID进行匹配，如果前台的ID与后台ID不同，返回缺失的ID的交易列表
     */
    public static final String WS_TRADES_GET_CHECKOUT_URL = ROOT_URL + "/selectMaxId20ForTrade";

    public static final String WS_DATA_GET_BANK_INFO_URL = ROOT_URL + "/selectBankInfo";

    public static final String WS_DATA_GET_INITIAL_KEY_URL = ROOT_URL + "/tradeInit";

    public static final String WS_DATA_GET_LAST_WORK_KEY_URL = ROOT_URL + "/getKey";

    public static final String WS_INCOME_REFUSES_GET_BY_MODIFY_TIME_URL = ROOT_URL + "/selectRefundByLastTime";

    public static final String WS_RABTES_REGULAR_GET_BY_TIME_URL = ROOT_URL + "/getReBateByVenderId";

    /*
     * 
     * 获取最新数据（参数需要加密） method : selectDataByLastTime key :
     * dataVoJson(vender_id,tradeModifyTime
     * ,inAccountModifyTime,commentModifyTime,messageModifyTime)
     */
    public static final String WS_DATA_GET_DATA_URL = ROOT_URL + "/selectDataByLastTime";

    public static final String WS_USER_SAVE_LOAN_URL = ROOT_URL + "/saveLoan";

    public static final String WS_USER_SAVE_FINANCING_URL = ROOT_URL + "/saveFinancing";

    public static final String WS_USERS_UPDATE_TUI_ID_URL = ROOT_URL + "/updateUser";

    public static final String WS_MESSAGES_UPDATE_STATUS_URL = ROOT_URL + "/updateMessageStatus";

    public static final String WS_SELECT_MAX_BY_NUMBER_URL = ROOT_URL + "/selectMaxByNumber";

    public static final String WS_SAVE_AUTO_QUOTA_URL = ROOT_URL + "/saveAutoQuota";

    /**
     * 添加客户
     */
    public static final String WS_ADD_CUSTOMER_URL = ROOT_URL + "/saveVenderCustomer";
    /**
     * 更新客户
     */
    public static final String WS_UPDATE_CUSTOMER_URL = ROOT_URL + "/updateVenderCustomer";
    /**
     * 根据商户ID，查询商户-客户关系列表信息
     */
    public static final String WS_GET_CUSTOMER_LIST_BY_VID_URL = ROOT_URL + "/selectVenderCustomerByVID";

    /**
     * 根据商户ID，客户ID，查询关系商户-客户信息/
     */
    public static final String WS_GET_CUSTOMER_INFO_URL = ROOT_URL + "/selectVenderCustomer";

    /**
     * 根据用户批量查询推广
     */
    public static final String WS_GET_POPULARIZE_LIST_URL = ROOT_URL + "/getListSpread";

    /**
     * 根据推广ID查询评论
     */
    public static final String WS_GET_POPULARIZE_LIST_COMMENT_URL = ROOT_URL + "/getListSpread_commentBySpreadId";

    /**
     * 获取绑卡信息
     */
    public static final String WS_GET_BANK_INFO_URL = ROOT_URL + "/selectBindCard";

    /**
     * 根据推广ID查询分享链接
     */
    public static final String WS_GET_POPULARIZE_FLAT_LIST_URL = ROOT_URL + "/getListSpread_flatBySpread";

    /**
     * 根据用户批量查询推广
     */
    public static final String WS_GET_OPENCOUNT_URL = ROOT_URL + "/getOpenCountByFlat";
    /**
     * 删除推广
     */
    public static final String WS_DELETE_POPULARIZE_URL = ROOT_URL + "/deleteSpread";

    /**
     * 批量删除推广
     */
    public static final String WS_BATCH_DELETE_POPULARIZE_URL = ROOT_URL + "/batchDeleteSpread";
    /**
     * 保存推广
     */
    public static final String WS_SAVE_POPULARIZE_URL = ROOT_URL + "/saveSpread";

    /**
     * 更新推广
     */
    public static final String WS_UPDATE_POPULARIZE_URL = ROOT_URL + "/updateSpread";

    /**
     * 更新推广
     */
    public static final String WS_UPLOADIMGS_POPULARIZE_URL = ROOT_URL + "/imgFileUpload";

    /**
     * 拉取推广gameinfo信息
     * **/
    public static final String WS_GET_POPULARIZE_GAMEINFO_URL = ROOT_URL + "/getGameInfoBySpreadId";

    /**
     * 修改店铺名称
     */
    public static final String WS_ALTER_VENDERNAME_URL = ROOT_URL + "/updateShopName";

    public static final String WS_POSTS_WRITE_SUCCESS = "contactlist://success";

    public static final String WS_POSTS_WRITE_FAIL = "contactlist://fail";

    public static final String WS_REQUEST_TYPE_EMAIL = "email";

    public static final String WS_REQUEST_TYPE_PASSWORD = "password";

    public static final String WS_REQUEST_TYPE_MODIFY_TIME = "modifyTime";

    public static final String WS_REQUEST_TYPE_PHONE_NUBER = "phone_number";

    public static final String WS_REQUEST_TYPE_DEVICE_ID = "deviceName";

    public static final String WS_REQUEST_TYPE_GENDER = "gender";

    public static final String WS_REQUEST_TYPE_CREATE_TIME = "createTime";

    public static final String WS_REQUEST_TYPE_BANK_CARD_NUMBER = "bank_card";

    public static final String WS_REQUEST_TYPE_BANK_NAME = "bank_name";

    public static final String WS_REQUEST_TYPE_SUB_BANK_NAME = "subbank_name";

    public static final String WS_REQUEST_TYPE_ID_CARD = "id_card";

    public static final String WS_REQUEST_TYPE_USER_DESCRIPTION = "desc";

    public static final String WS_REQUEST_TYPE_ID = "id";

    public static final String WS_REQUEST_TYPE_USER_ID = "vender_id";

    public static final String WS_REQUEST_TYPE_AUTO_CREDIT = "autoQuotaJson";

    public static final String WS_REQUEST_TYPE_USER_NAME = "name";

    public static final String WS_REQUEST_TYPE_USER_FEEDBACK = "feedback";

    public static final String WS_INTENT_PARAM_POST_DETAIL_URL = "post_detail_url";

    public static final String WS_REQUEST_TYPE_USER_JSON = "userjson";

    public static final String WS_REQUEST_TYPE_TRADE_JSON = "tradejson";

    public static final String WS_REQUEST_TYPE_TRADE_ID = "trade_id";

    public static final String WS_REQUEST_TYPE_TRADE_NUM = "trade_num";

    public static final String WS_REQUEST_TYPE_LAST_UPDATE_TIME = "last_update_time";

    public static final String WS_REQUEST_TYPE_ENVELOPE_MODIFY_TIME = "envelope_modify_time";

    public static final String WS_REQUEST_TYPE_MUSIC_MODIFY_TIME = "music_modify_time";

    public static final String WS_REQUEST_TYPE_PAGE_MODIFY_TIME = "page_modify_time";

    public static final String WS_REQUEST_TYPE_SEND_MODE_MODIFY_TIME = "send_way_modify_time";

    public static final String WS_REQUEST_TYPE_GOLD_ACT_MODIFY_TIME = "gold_act_modify_time";

    public static final String WS_REQUEST_TYPE_LOAN_JSON = "loanJson";

    public static final String WS_REQUEST_TYPE_FINANCE_JSON = "financingJson";

    public static final String WS_REQUEST_TYPE_STAMP = "stamp";

    public static final String WS_REQUEST_TYPE_PHOTO = "photo";

    public static final String WS_REQUEST_TYPE_RECEIVER = "receive";

    public static final String WS_REQUEST_TYPE_SENDER = "sender";

    public static final String WS_REQUEST_TYPE_WORD = "wish_word";

    public static final String WS_REQUEST_TYPE_CHANNEL = "channel";

    public static final String WS_REQUEST_TYPE_CURRENT_VERSION_NUMBER = "version_number";

    public static final String WS_REQUEST_TYPE_VERSION_TYPE = "version_type";

    public static final String WS_REQUEST_TYPE_SAVED_ACTION = "save_action";

    public static final String WS_REQUEST_TYPE_FEEDBACK = "fbjson";

    public static final String WS_REQUEST_TYPE_PHONE_NUMBER = "phone_number";

    public static final String WS_REQUEST_TYPE_KSN = "ksnNo";

    public static final String WS_REQUEST_TYPE_WSHSNO = "WSHSNO";

    public static final String WS_REQUEST_TYPE_SHOPNAME = "shopName";

    public static final String WS_REQUEST_TYPE_FROM_TYPE = "from_type";

    public static final String WS_REQUEST_TYPE_DATAVOJSON = "dataVoJson";

    public static final String WS_REQUEST_TYPE_CODE_TYPE = "type";
}
