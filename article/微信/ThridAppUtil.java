package com.ningpai.util;

import com.jfinal.weixin.sdk.encrypt.AesException;
import com.jfinal.weixin.sdk.encrypt.WXBizMsgCrypt;
import com.ningpai.system.bean.WxAuthInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 微信开放平台工具类
 */
public class ThridAppUtil {

    //第三方平台配置
     final static String Token = "XXXXXXXXXXXxx";
     final static String encodingAesKey = "XXXXXXXXXXXXX";
     public final static String AppID = "XXXXXXXXXXXXXXX";
     final static String AppSecret = "XXXXXXXXXXXXXXx";


    //存在过期时间的token，优先考虑存放在redis中
    static RedisTemplate redisTemplate;
    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        ThridAppUtil.redisTemplate = redisTemplate;
    }


    //redis中各个token的key
    final static String Component_Verify_Ticket_REDIS_KEY = "WX_Component_Verify_Ticket";//每隔10分钟定时推送component_verify_ticket，不需要手动刷新
    final static String Component_Access_Token_REDIS_KEY = "WX_Component_Access_Token";//第三方平台component_access_token是第三方平台的下文中接口的调用凭据，也叫做令牌（component_access_token）。每个令牌是存在有效期（2小时）的，且令牌的调用不是无限制的，请第三方平台做好令牌的管理，在令牌快过期时（比如1小时50分）再进行刷新。
    final static String Pre_Auth_Code_REDIS_KEY = "WX_Pre_Auth_Code";//预授权码用于公众号或小程序授权时的第三方平台方安全验证


    //各个接口的url
    final static String Component_Access_Token_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    final static String Pre_Auth_Code_Token_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=COMPONENT_ACCESS_TOKEN";
    final static String To_Auth_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=COMPONENT_APPID&pre_auth_code=PRE_AUTH_CODE&redirect_uri=REDIRECT_URI&auth_type=AUTH_TYPE";

    final static String REDIRECT_URL ="http://测试机器的域名/wxThirdAuth/redirect.htm";


    /**
     * 获取component_access_token
     * @return
     */
    static String component_access_token() throws IOException {
        String component_access_token = null;
        component_access_token = getRedis(Component_Access_Token_REDIS_KEY);
        if(StringUtils.isBlank(component_access_token)){
            String ticket = (String) redisTemplate.opsForValue().get(Component_Verify_Ticket_REDIS_KEY);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("component_appid",AppID);
            jsonobj.put("component_appsecret",AppSecret);
            jsonobj.put("component_verify_ticket",ticket);
            String result = Request.Post(Component_Access_Token_URL).bodyByteArray(jsonobj.toString().getBytes(StandardCharsets.UTF_8)).execute().returnContent().asString();
            JSONObject jsonResult = JSONObject.fromObject(result);
            component_access_token = (String)jsonResult.get("component_access_token");
            Long expire_in = Long.valueOf((Integer)jsonResult.get("expires_in"));
            setRedis(Component_Access_Token_REDIS_KEY,component_access_token,expire_in);
        }
        return component_access_token;
    }

    /**
     * 获取预授权码--一次性用品，不需要缓存起来
     * @return
     * @throws IOException
     */
    static String getPreAuthCode() throws IOException {
        String preAuthCode = null;
//        preAuthCode = getRedis(Pre_Auth_Code_REDIS_KEY);
//        if(StringUtils.isBlank(preAuthCode)){
            String component_access_token = component_access_token();
            String url = Pre_Auth_Code_Token_URL.replace("COMPONENT_ACCESS_TOKEN",component_access_token);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("component_appid",AppID);
            String result = Request.Post(url).bodyByteArray(jsonobj.toString().getBytes(StandardCharsets.UTF_8)).execute().returnContent().asString();
            JSONObject jsonResult = JSONObject.fromObject(result);
            preAuthCode = (String)jsonResult.get("pre_auth_code");
//            Long expire_in = Long.valueOf((Integer)jsonResult.get("expires_in"));
//            setRedis(Pre_Auth_Code_REDIS_KEY,component_access_token,expire_in);
//        }
        return preAuthCode;
    }

    /**
     * 商户跳转到扫一扫授权页面链接
     * @return
     */
    public static String toAuthScanPage() throws IOException {
        String PRE_AUTH_CODE = getPreAuthCode();
        String redirectURL = URLEncoder.encode(REDIRECT_URL,StandardCharsets.UTF_8.name());
        String url = To_Auth_URL.replace("COMPONENT_APPID", AppID).replace("PRE_AUTH_CODE", PRE_AUTH_CODE).replace("REDIRECT_URI", redirectURL).replace("AUTH_TYPE", "1");
        return url;
    }

    /**
     * 获取解密ticket的对象
     * @return
     * @throws AesException
     */
    public static WXBizMsgCrypt decryptComponentVerfyTicket() throws AesException {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(ThridAppUtil.Token, ThridAppUtil.encodingAesKey, ThridAppUtil.AppID);
        return pc;
    }

    /**
     * 将Component_Verify_Ticket 存储到redis中
     * @param ticket
     */
    public static void putComponentVerfyTicketRedis(String ticket){
        setRedis(Component_Verify_Ticket_REDIS_KEY,ticket,1000L);
    }


    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
     */
    final static String Get_Authorization_Info_URL ="https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=COMPONENT_ACCESS_TOKEN";

    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息 包含access_token refresh_token
     * @param auth_code
     * @return
     * @throws IOException
     */
    public static JSONObject redirectAuthInfo(String auth_code) throws IOException {
        String component_access_token = component_access_token();
        String url = Get_Authorization_Info_URL.replace("COMPONENT_ACCESS_TOKEN",component_access_token);
        JSONObject param = new JSONObject();
        param.put("component_appid",AppID);
        param.put("authorization_code",auth_code);
        String result = Request.Post(url).bodyByteArray(param.toString().getBytes(StandardCharsets.UTF_8)).execute().returnContent().asString();
        JSONObject jsonResult = JSONObject.fromObject(result);
        return jsonResult;
    }

    /**
     * ==================授权前后的分割线=======================================================================================================================================================================
     */

    static final String GET_Auth_Access_Token_URL = "https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
    //5、获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
    public static String getAuthAccessToken(WxAuthInfo info) throws IOException {
        String token = null;
        String key = info.getCC()+WxAuthInfo.auth_access_token_key;
        token = getRedis(key);
        if(StringUtils.isBlank(token)){
            String component_access_token = component_access_token();
            String url =GET_Auth_Access_Token_URL.replace("COMPONENT_ACCESS_TOKEN",component_access_token);
            JSONObject param = new JSONObject();
            param.put("component_appid",AppID);
            param.put("authorizer_appid",info.getAuthorizer_appid());
            param.put("authorizer_refresh_token",info.getAuthorizer_refresh_token());
            String result = Request.Post(url).bodyByteArray(param.toString().getBytes(StandardCharsets.UTF_8)).execute().returnContent().asString();
            JSONObject jsonResult = JSONObject.fromObject(result);
            token =  (String)jsonResult.get("authorizer_access_token");
            Long expire_in = Long.valueOf((Integer)jsonResult.get("expires_in"));
            setRedis(key,token,expire_in);//缓存到redis中
        }
        return token;
    }




    /**
     * ==================redis操作=======================================================================================================================================================================
     */

    /**
     * 操作redis  get操作
     * @param key
     * @return
     */
    static String getRedis(String key){
        String value = (String) redisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 操作redis set操作
     * @param key
     * @param value
     */
    static void setRedis(String key,String value) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyArr = key.getBytes();
                byte[] valueArr = value.getBytes();
                connection.set(keyArr, valueArr);
                return true;
            }
        });
    }
    /**
     * 操作redis set操作
     * @param key
     * @param value
     */
    static void setRedis(String key,String value,Long expire) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyArr = key.getBytes();
                byte[] valueArr = value.getBytes();
                connection.setEx(keyArr,expire, valueArr);
                return true;
            }
        });
    }

}
