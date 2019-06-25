package com.xjl.utils;

import com.xjl.vo.user.OAuthInfo;
import com.xjl.vo.user.OUserInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方授权
 * @author huanglian
 *
 */
public class OAuthUtils {

	private static final Logger logger = LoggerFactory.getLogger(OAuthUtils.class);

	/**
	 * 微信授权配置
	 */
	/*public static final String PC_WX_APPID = "wx348300047de24185";
	public static final String PC_WX_APPID_SECRET = "4865e521e791a0a0c386db21cd413b2b";*/
//	public static final String PC_WX_APPID = "wx8ebd60331fdb855b";
//	public static final String PC_WX_APPID_SECRET = "abed7cff9352b019edb8208e727f25c9";


	private static final String PC_WX_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String PC_WX_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 微信公众平台授权配置
	 */
	public static final String GZ_WX_APPID = "wxa5891bebe7891bfe";
	public static final String GZ_WX_APPID_SECRET = "4de660d6433d2484a33b3eeff603b9ee";
	public static final String GZ_WX_ACTIVITY_APPID = "wxaeb531511e374491";
	public static final String GZ_WX_APPID_ACTIVITY_SECRET = "ec6f7d39f82e4c1f0049ec849e7e14a0";
	private static final String GZ_WX_TOKEN = "https://api.weixin.qq.com/sns/jscode2session";
	private static final String XCX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	/**
	 * QQ授权配置
	 */
	public static final String PC_QQ_APPID = "101357832";
	public static final String PC_QQ_APPKEY = "9130999a82347e4d87af0a5edd0dd15e";
	public static final String PC_QQ_LOGIN_REDIRECT_URL = "http://www.icoachu.cn/icoachu-student-cms/login/login.html?provider=qq";
	public static final String PC_QQ_BIND_REDIRECT_URL = "http://www.icoachu.cn/icoachucms/userInfo/userInfo.html?provider=qq";
	private static final String PC_QQ_GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	private static final String PC_QQ_GET_OPENID = "https://graph.qq.com/oauth2.0/me";
	private static final String PC_QQ_GET_USERINFO = "https://graph.qq.com/user/get_user_info";
	private static final String PC_QQ_GET_UNIONID = "https://graph.qq.com/oauth2.0/me";

	/**
	 * 微博授权配置
	 */
	public static final String PC_WEIBO_CLIENTID = "2004997714";
	public static final String PC_WEIBO_CLIENTID_SECRET = "0c265731e67e0f946628c052d1ae424b";
	public static final String PC_WEIBO_LOGIN_REDIRECT_URI = "http://www.icoachu.cn/icoachu-student-cms/login/login.html?provider=weibo";
	public static final String PC_WEIBO_BIND_REDIRECT_URI = "http://www.icoachu.cn/icoachucms/userInfo/userInfo.html?provider=weibo";
	private static final String PC_WEIBO_GET_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token";
	private static final String PC_WEIBO_GET_USERINFO = "https://api.weibo.com/2/users/show.json";

	/**
	 * 获取网页版微信access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static OAuthInfo getWXOAuthInfo(String appid, String secret, String code) {
		logger.info("-----微信网页授权获取code:"+code);
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("appid", appid);
		queryParas.put("secret", secret);
		queryParas.put("code", code);
		queryParas.put("grant_type", "authorization_code");
		String jsonStr = "";
		try {
			jsonStr = HttpUtils.get(PC_WX_GET_ACCESS_TOKEN, queryParas);
		}catch(Exception e){
			return null;
		}
		logger.info("获取网页版微信access_token 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		if(jsonObj.containsKey("errcode")) {
			logger.error("获取网页版微信access_token失败 errcode:{} errmsg:{}", jsonObj.getInt("errcode"), jsonObj.getString("errmsg"));
			return null;
		}
		OAuthInfo oauth = new OAuthInfo();
		oauth.setExpiresIn(jsonObj.getString("expires_in"));
		oauth.setAccessToken(jsonObj.getString("access_token"));
		oauth.setOpenId(jsonObj.getString("openid"));
		oauth.setScope(jsonObj.getString("scope"));
		oauth.setUnionid(jsonObj.getString("unionid"));
		return oauth;
	}

	/**
	 * 微信拉取用户信息(需scope为 snsapi_userinfo)
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static OUserInfo getWXUserInfo(String accessToken, String openId, String unionid) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("access_token", accessToken);
		queryParas.put("openid", openId);
		queryParas.put("lang", "zh_CN");
		String jsonStr = HttpUtils.get(PC_WX_GET_USERINFO, queryParas);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		logger.info("微信拉取用户信息(需scope为 snsapi_userinfo) 返回" + jsonStr);
		if(jsonObj.containsKey("errcode")) {
			logger.error("网页授权获取用户信息失败 errcode:{} errmsg:{}", jsonObj.getInt("errcode"), jsonObj.getString("errmsg"));
			return null;
		}
		OUserInfo userInfo = new OUserInfo();
		userInfo.setOpenid(openId);
		userInfo.setNickname(jsonObj.getString("nickname"));
		Integer sex = jsonObj.getInt("sex");
		userInfo.setSex(1 == sex?1:0);
		userInfo.setHeadimgurl(jsonObj.getString("headimgurl"));
		userInfo.setCountry(jsonObj.getString("country"));
		userInfo.setProvince(jsonObj.getString("province"));
		userInfo.setCity(jsonObj.getString("city"));
		userInfo.setUnionid(unionid);
		return userInfo;
	}

	/**
	 * 获取网页版微信access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static OAuthInfo getWXGZOAuthInfo(String appid, String secret, String code) {
		logger.info("-----微信小程序授权获取code:"+code);
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("appid", appid);
		queryParas.put("secret", secret);
		queryParas.put("js_code", code);
		queryParas.put("grant_type", "authorization_code");
		String jsonStr = "";
		try {
			jsonStr = HttpUtils.get(GZ_WX_TOKEN, queryParas);
		}catch(Exception e){
			return null;
		}
		logger.info("获取微信小程序session_key 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		if(jsonObj.containsKey("errcode")) {
			logger.error("获取微信小程序access_token失败 errcode:{} errmsg:{}", jsonObj.getInt("errcode"), jsonObj.getString("errmsg"));
			return null;
		}
		OAuthInfo oauth = new OAuthInfo();
		oauth.setSessionKey(jsonObj.getString("session_key"));
		return oauth;
	}

	/*public static void main(String[] args) {
		getWXGZOAuthInfo(OAuthKit.PC_WX_APPID,OAuthKit.PC_WX_APPID_SECRET,"001MIZ7i1xa1gx0Qho7i1q038i1MIZ7J");
	}*/

	/**
	 * 获取微信小程序用户信息
	 * @param encryptedData
	 * @param sessionKey
	 * @param iv
	 * @return
	 */
	public static OUserInfo getWXGZUserInfo(String encryptedData, String sessionKey, String iv){
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");

				JSONObject jsonObj = JSONObject.fromObject(result);
				if(null != jsonObj.getString("unionId")){
					OUserInfo userInfo = new OUserInfo();
					userInfo.setOpenid(jsonObj.getString("openId"));
					userInfo.setNickname(jsonObj.getString("nickName"));
					String sex = jsonObj.getString("gender");
					userInfo.setSex("1".equals(sex)?1:0);
					userInfo.setHeadimgurl(jsonObj.getString("avatarUrl"));
					userInfo.setUnionid(jsonObj.getString("unionId"));
					return userInfo;
				}

			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidParameterSpecException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage(), e);
		} catch (BadPaddingException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取网页版QQ access_token
	 * @param appid
	 * @param appkey
	 * @param code
	 * @return
	 */
	public static OAuthInfo getQQOAuthInfo(String appid, String appkey, String code, String url) {
		logger.info("-----QQ网页授权获取code:"+code);
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("client_id", appid);
		queryParas.put("client_secret", appkey);
		queryParas.put("code", code);
		queryParas.put("redirect_uri", url);
		queryParas.put("grant_type", "authorization_code");
		String jsonStr = "";
		try{
			jsonStr = HttpUtils.get(PC_QQ_GET_ACCESS_TOKEN, queryParas);
		}catch(Exception e){
			return null;
		}
		logger.info("获取网页版QQ access_token 返回：" +  jsonStr);
		JSONObject jsonObj = transResp2Json(jsonStr);
		OAuthInfo oauth = null;
		if(null != jsonObj.get("expires_in")){
			oauth = new OAuthInfo();
			oauth.setExpiresIn(jsonObj.getString("expires_in"));
			oauth.setAccessToken(jsonObj.getString("access_token"));
		}
		return oauth;
	}

	/**
	 * 获取网页版的unionid和openid
	 * @return
	 */
	public static OUserInfo getUnionIdAndOpenId(String accessToken){
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("access_token", accessToken);
		queryParas.put("unionid", "1");
		String jsonStr = HttpUtils.get(PC_QQ_GET_UNIONID, queryParas);
		logger.info("获取网页版的unionid和openid："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr.replace("callback(", "").replace(")", "").replace(";",""));
		OUserInfo userInfo = null;
		if(null != jsonObj.get("openid")){
			userInfo = new OUserInfo();
			userInfo.setOpenid(jsonObj.getString("openid"));
			userInfo.setUnionid(jsonObj.getString("unionid"));
			return userInfo;
		}else{
			return userInfo;
		}
	}

	/**
	 * 获取网页版QQ openId
	 * @param accessToken
	 * @return
	 */
	public static String getQQOpenId(String accessToken) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("access_token", accessToken);
		String jsonStr = HttpUtils.get(PC_QQ_GET_OPENID, queryParas);
		logger.info("获取网页版QQ openId 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr.replace("callback(", "").replace(")", "").replace(";",""));
		if(null != jsonObj.get("openid")){
			return jsonObj.getString("openid");
		}else{
			return null;
		}

	}

	/**
	 * 获取网页版QQ用户信息
	 * @param accessToken
	 * @param openid
	 * @param appid
	 * @return
	 */
	public static OUserInfo getQQUserInfo(String accessToken, String appid, String openid) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("access_token", accessToken);
		queryParas.put("oauth_consumer_key", appid);
		queryParas.put("openid", openid);
		String jsonStr = HttpUtils.get(PC_QQ_GET_USERINFO, queryParas);
		logger.info("获取网页版QQ用户信息 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		OUserInfo userInfo = null;
		if(null != jsonObj.get("nickname")){
			userInfo = new OUserInfo();
			userInfo.setOpenid(openid);
			userInfo.setNickname(jsonObj.getString("nickname"));
			String gender = jsonObj.getString("gender");
			if(StringUtils.isNotBlank(gender)){
				userInfo.setSex("男".equals(gender)? 1:0);
			}
			userInfo.setHeadimgurl(jsonObj.getString("figureurl_qq_1"));
		}
		return userInfo;
	}

	/**
	 * 获取网页版微博access_token
	 * @param clientid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static OAuthInfo getWeiboOAuthInfo(String clientid, String secret, String code , String url) {
		logger.info("-----微博网页授权获取code:"+code);
		String date = "client_id="+clientid+"&client_secret="+secret+"&grant_type=authorization_code&code="+code+"&redirect_uri="+url;
		String jsonStr = "";
		try {
			jsonStr = HttpUtils.post(PC_WEIBO_GET_ACCESS_TOKEN, date);
		}catch(Exception e){
			return null;
		}
		logger.info("获取网页版微博access_token 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		OAuthInfo oauth = null;
		if(null != jsonObj.getString("access_token")){
			oauth = new OAuthInfo();
			oauth.setExpiresIn(jsonObj.getString("expires_in"));
			oauth.setAccessToken(jsonObj.getString("access_token"));
			oauth.setOpenId(jsonObj.getString("uid"));
			oauth.setUnionid(jsonObj.getString("uid"));
		}
		return oauth;
	}

	/**
	 * 获取网页版微博用户信息
	 * @param accessToken
	 * @param unionid
	 * @return
	 */
	public static OUserInfo getWeiboUserInfo(String accessToken, String unionid) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("access_token", accessToken);
		queryParas.put("uid", unionid);
		String jsonStr = HttpUtils.get(PC_WEIBO_GET_USERINFO, queryParas);
		logger.info("获取网页版微博用户信息 返回："+jsonStr);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		OUserInfo userInfo = null;
		if(null != jsonObj.get("screen_name")){
			userInfo = new OUserInfo();
			userInfo.setOpenid(unionid);
			userInfo.setUnionid(unionid);
			userInfo.setNickname(jsonObj.getString("screen_name"));
			String gender = jsonObj.getString("gender");
			if(StringUtils.isNotBlank(gender)){
				userInfo.setSex("m".equals(gender)? 1:0);
			}
			userInfo.setHeadimgurl(jsonObj.getString("profile_image_url"));
		}
		return userInfo;
	}


	/**
	 * 微信小程序获取access_token
	 * @return
	 */
	public static String getWXAccessToken(String appid, String secret) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("grant_type", "client_credential");
		queryParas.put("appid", appid);
		queryParas.put("secret", secret);
		String jsonStr = HttpUtils.get(XCX_ACCESS_TOKEN_URL, queryParas);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		logger.info("微信小程序获取access_token 返回" + jsonStr);
		if(jsonObj.containsKey("errcode")) {
			logger.error("微信小程序获取access_token失败 errcode:{} errmsg:{}", jsonObj.getInt("errcode"), jsonObj.getString("errmsg"));
			return null;
		}
		String accessToken = jsonObj.getString("access_token");

		return accessToken;
	}

	/**
	 * 微信小程序获取二维码
	 * @return
	 */
	public static InputStream getWXXcxImg(String accessToken, String width, String page, String scene) {
		Map<String,String> queryParas = new HashMap<String,String>();
		queryParas.put("scene", scene);
		queryParas.put("page", page);
		queryParas.put("width", width);
		JSONObject jsStr = JSONObject.fromObject(queryParas);
		InputStream jsonObj = HttpUtils.doJsonPostIO("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken, jsStr);
		return jsonObj;
	}

	public static JSONObject transResp2Json(String str){
		Map map = new HashedMap();
		String[] strs = str.split("&");
		for(String s:strs){
			String[] sList = s.split("=");
			if(sList.length==2){
				map.put(sList[0],sList[1]);
			}
		}
		return JSONObject.fromObject(map);
	}


}
