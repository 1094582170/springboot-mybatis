package com.kfit.spring_boot_mybatis.service.impl;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kfit.spring_boot_mybatis.component.WechatComponent;
import com.kfit.spring_boot_mybatis.service.IWechatService;
import com.kfit.spring_boot_mybatis.utils.EnvironmentAwareUtils;


@Service
public class WechatServiceImpl implements IWechatService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	static ConcurrentMap<String, String> openIdtimeStamp = null;

	static {
		openIdtimeStamp = new ConcurrentHashMap<>();
	}


	@SuppressWarnings("unchecked")
	@Override
	public String wechatServerCallback(HttpServletRequest request) throws Exception {
		Map<String, Object> wechatMap = xmlToMap(request); // 微信服务器返回的xml数据包
		if (wechatMap != null && wechatMap.get("MsgType") != null) {
			String msgType = wechatMap.get("MsgType").toString();
			if ("image".equals(msgType)) { // 图片
				{
					logger.info("微信用户  - {} 发送了一张图片, 但是该用户没有扫描二维码");
					String content = "您还没有扫描二维码";
					return WechatComponent.createXMLString(wechatMap.get("FromUserName").toString(), EnvironmentAwareUtils.getProperties(".weixin.weixin_id"), content);
				}

			} else {

				return "SUCCESS";
			}
		}

		// 微信重发
		String content = "没有收到您的请求，请重新操作";
		return WechatComponent.createXMLString(wechatMap.get("FromUserName").toString(), EnvironmentAwareUtils.getProperties(".weixin.weixin_id"), content);
	}


	@Override
	public Map<String, String> getQrCode(HttpServletRequest request) throws Exception {
		Map<String, String> retMap = new HashMap<>();
		
		logger.info("刷新 access token count - {}", WechatComponent.tokenCount);
		return retMap;
	}

	@Override
	public boolean checkName(String signature, String timestamp, String nonce) {
		logger.info("--------------------> 微信服务器回调验证 start");
		boolean isOk = WechatComponent.checkSignature(signature, timestamp, nonce);
		if (isOk) {
			logger.info("--------------------> 微信服务器回调验证  successfully");
		} else {
			logger.info("--------------------> 微信服务器回调验证  failed");
		}
		return isOk;
	}

	

	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> xmlToMap(HttpServletRequest request) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputStream stream = request.getInputStream();
		org.w3c.dom.Document doc = documentBuilder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		for (int idx = 0; idx < nodeList.getLength(); ++idx) {
			Node node = nodeList.item(idx);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				data.put(element.getNodeName(), element.getTextContent());
			}
		}
		try {
			stream.close();
		} catch (Exception ex) {

		}
		return data;
	}




}
