package com.kfit.spring_boot_mybatis.component;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kfit.spring_boot_mybatis.utils.EnvironmentAwareUtils;
import com.linkingmed.base.util.inputstream.InputStreamUtils;
import com.linkingmed.base.util.json.JSONUtils;
import com.linkingmed.base.util.request.HttpRequestUtils;
import com.linkingmed.base.util.time.TimeUtils;

import net.sf.json.JSONObject;

public class WechatComponent {

	static final Logger logger = LoggerFactory.getLogger(WechatComponent.class);

	/**
	 * 刷新access token的次数
	 */
	public static Integer tokenCount = 0;

	/**
	 * 获取access token 锁
	 */
	static final Lock tokenLock = new ReentrantLock();

	/**
	 * access token 缓存map
	 * 缓存access_token和token获取时间
	 */
	static ConcurrentMap<String, String> tokenMap = null;

	static {
		tokenMap = new ConcurrentHashMap<>(2);
		tokenMap.put(WechatConstant.ACCESS_TOKEN_CREATE_TIME, "0");
		tokenMap.put(WechatConstant.ACCESS_TOKEN, "");
	}

	/**
	 * 发起get请求 返回一个stream
	 *
	 */
	public static byte[] sendGet(String url) {
		InputStream in = null;
		HttpURLConnection connection = null;
		byte[] bis = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();

			in = connection.getInputStream();
			bis = InputStreamUtils.InputStreamTOByte(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
		return bis;
	}

	public static Map<String, Object> sendGetInputStream(String url) {

		HttpURLConnection connection = null;

		Map<String, Object> retMap = new HashMap<>();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			logger.info("connection map - {}", map);

			if (map != null && map.get("Content-Type") != null && map.get("Content-Type").contains("image/jpeg")) {
				retMap.put("head", map);
				// 遍历所有的响应头字段
				for (String key : map.keySet()) {
					logger.info(key + "--->" + map.get(key));
				}
				InputStream in = connection.getInputStream();
				byte[] bis = InputStreamUtils.InputStreamTOByte(in);

				retMap.put("body", bis);
			} else {
				logger.info("sendGetInputStream get content type - {}", map.get("Content-Type"));
				retMap.put("errcode", "linking40013"); // 自定义错误码
			}

		} catch (Exception e) {
			e.printStackTrace();
			retMap = new HashMap<>();
			retMap.put("errcode", "linking40013"); // 自定义错误码
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return retMap;
	}

	public static String contentType2Type(String contentType) {
		logger.info("当前图片 content type - {}", contentType);
		if (contentType == null)
			contentType = "";// 防止此对象为null

		if (contentType.equals("image/bmp")) {
			return "bmp";
		}
		if (contentType.equals("image/gif")) {
			return "gif";
		}
		if (contentType.equals("image/jpeg") || contentType.equals("image/jpg")) {
			return "jpg";
		}
		return "jpg";
	}

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { WechatConstant.WX_TOKEN, timestamp, nonce };

		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	public static String getAccessToken(boolean isForce) throws Exception {
		Integer currentTime = TimeUtils.getInstantCurrentSecondDatetimestamp();

		// 如果当前还没有数据（应用第一次获取token）
		// 微信API有个巨坑，说好的7200秒，它偏不，它自己在7200秒之内默默刷新了，刷新间隔还不确定
		Integer timeDiff = currentTime - Integer.valueOf(tokenMap.get(WechatConstant.ACCESS_TOKEN_CREATE_TIME));
		logger.info("当前时间差 - {}", timeDiff);

		if (StringUtils.isBlank(tokenMap.get(WechatConstant.ACCESS_TOKEN)) || timeDiff >= 3600 || isForce) {

			try {
				tokenLock.lock();

				if (!(timeDiff < 60 && StringUtils.isNoneBlank(tokenMap.get(WechatConstant.ACCESS_TOKEN)))) {
					String urlFormat = WechatConstant.WX_URL_GET_ACCESS_TOKEN;
					String getAccessTokenUrl = urlFormat.replace("APP_ID", EnvironmentAwareUtils.getProperties("weixin.app_id")).replace("SECRET", EnvironmentAwareUtils.getProperties("weixin.app_key"));
					JSONObject ret = HttpRequestUtils.httpGet(getAccessTokenUrl);

					WechatComponent.tokenCount++;

					if (ret == null) {
						logger.info("获取access_token异常，返回  Null ");
						tokenMap.put(WechatConstant.ACCESS_TOKEN, StringUtils.EMPTY);
						tokenMap.put(WechatConstant.ACCESS_TOKEN_CREATE_TIME, "0");
						// return StringUtils.EMPTY;
					}

					logger.info("获取access_token异常，返回  - {} ", JSONUtils.object2Json(ret));

					// 请求access_token成功
					if (ret.containsKey("access_token")) {
						tokenMap.put(WechatConstant.ACCESS_TOKEN, ret.getString("access_token"));
						tokenMap.put(WechatConstant.ACCESS_TOKEN_CREATE_TIME, String.valueOf(TimeUtils.getInstantCurrentSecondDatetimestamp()));

					} else {
						tokenMap.put(WechatConstant.ACCESS_TOKEN, StringUtils.EMPTY);
						tokenMap.put(WechatConstant.ACCESS_TOKEN_CREATE_TIME, "0");
					}
				}
			} finally {
				tokenLock.unlock();
			}

			return tokenMap.get(WechatConstant.ACCESS_TOKEN).toString();
		} else {
			// 返回之前获取的access_token
			return tokenMap.get(WechatConstant.ACCESS_TOKEN).toString();
		}
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	/**
	 * 生成回复文本消息的xml字符串
	 * 
	 * @param toUser
	 * @param fromUser
	 * @param content
	 * @return
	 */
	public static String createXMLString(String toUser, String fromUser, String content) {
		StringBuffer rBuilder = new StringBuffer();
		rBuilder.append("<xml>");
		rBuilder.append("<ToUserName><![CDATA[").append(toUser).append("]]></ToUserName>");
		rBuilder.append("<FromUserName><![CDATA[").append(fromUser).append("]]></FromUserName>");
		rBuilder.append("<CreateTime><![CDATA[").append(System.currentTimeMillis()).append("]]></CreateTime>");
		rBuilder.append("<MsgType><![CDATA[text]]></MsgType>");
		rBuilder.append("<Content><![CDATA[").append(content).append("]]></Content>");
		rBuilder.append("</xml>");
		return rBuilder.toString();

	}
}
