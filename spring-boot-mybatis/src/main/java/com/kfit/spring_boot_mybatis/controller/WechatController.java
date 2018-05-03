package com.kfit.spring_boot_mybatis.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kfit.spring_boot_mybatis.component.CodeResult;
import com.kfit.spring_boot_mybatis.component.JSONResult;
import com.kfit.spring_boot_mybatis.component.MsgResult;
import com.kfit.spring_boot_mybatis.component.WechatComponent;
import com.kfit.spring_boot_mybatis.component.WechatConstant;
import com.kfit.spring_boot_mybatis.pojo.vo.WxUser;
import com.kfit.spring_boot_mybatis.service.IWechatService;
import com.linkingmed.base.util.time.TimeUtils;
import com.linkingmed.base.util.time.TimeUtils.TimeFormat;

import net.sf.json.JSONObject;

/**
 * 
 * WXRapidUploadController 微信快速上传
 *
 * Created by zhang.yang on 20170920.
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

	@Autowired(required = false)
	public IWechatService wxService;

	/**
	 * 确认请求来自微信服务器
	 */
	@RequestMapping(value = "/server/callback", method = RequestMethod.GET)
	public String checkName(@RequestParam(name = "signature") String signature,
			@RequestParam(name = "timestamp") String timestamp, @RequestParam(name = "nonce") String nonce,
			@RequestParam(name = "echostr") String echostr) {

		return this.wxService.checkName(signature, timestamp, nonce) ? echostr : "";

	}

	/**
	 * 微信服务器的回调
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/server/callback", method = RequestMethod.POST)
	public String wechatServicePost(HttpServletRequest request) throws Exception {
		// 接受wechat服务器发来的xml数据包
		return this.wxService.wechatServerCallback(request);
	}

	/**
	 * 获取带参数的二维码 二维码是登录用户uid的字符串
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rapid/qr")
	public JSONResult showQR(HttpServletRequest request) throws Exception {
		Map<String, String> ret = null;
		ret = wxService.getQrCode(request);
		return new JSONResult(CodeResult.CODE_SUCCESS, MsgResult.ACCESSOK, ret);

	}

	@GetMapping("/user")
	public JSONResult user(HttpServletRequest request) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue()[0]);
		}
		String code = request.getParameter("code");
		RestTemplate restTemplate = new RestTemplate();
		String url = WechatConstant.WX_OPENID.replaceAll("CODE", code);
		 String jsonStr = restTemplate.getForObject(url, String.class);
		 System.out.println(jsonStr);
		 JSONObject json = JSONObject.fromObject(jsonStr);
		 String openid = json.getString("openid");
		 String accessToken = json.getString("access_token");
		 getWxUser(openid,accessToken);
		 Map<String,String> map2 = new HashMap<String,String>();
		 map2.put("openid", openid);
		try {
			// send(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONResult(CodeResult.CODE_SUCCESS, MsgResult.ACCESSOK, map2);
	}

	public static JSONResult send(String openid) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, T> data = new HashMap<String, T>();

		map.put("touser", openid);
		map.put("template_id", "gMxE_hQaOfT4OIrSiQO8n7j74bpY_4fK0K3hJTCZySI");
		map.put("url", "www.baidu.com");
		map.put("data", data);
		data.put("consultant", new T("李先生"));
		data.put("content", new T("登录成功"));
		data.put("date", new T(TimeUtils.getLocalDateTimeCurrentDatetimestamp(TimeFormat.FORMAT_Y_M_D_H_M)));
		data.put("details", new T("fasdf"));

		HttpHeaders headers = new HttpHeaders();
		String accessToken = WechatComponent.getAccessToken(false);
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map> entity = new HttpEntity<Map>(map, headers);
		restTemplate.exchange(url, HttpMethod.POST, entity, Map.class, map);
		// restTemplate.
		// String jsonStr = restTemplate.getForObject(url, String.class);
		return new JSONResult(CodeResult.CODE_SUCCESS, MsgResult.ACCESSOK, "成功");
	}

	public static void main(String[] args) throws Exception {
		send("");
	}

	public static WxUser getWxUser(String openid, String accessToken) {
		WxUser wxUser = new WxUser();
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid
				+ "&lang=zh_CN";
		RestTemplate restTemplate = new RestTemplate();
		String str = restTemplate.getForObject(url, String.class);
		return wxUser;
	}
}

class T {
	private String value;
	private String color;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public T(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}

	public T() {
		super();
	}

	public T(String value) {
		super();
		this.value = value;
		this.color = "#173177";
	}

}
