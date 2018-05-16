package com.kfit.spring_boot_mybatis.controller;

import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.kfit.spring_boot_mybatis.mapper.DemoMappper;
import com.kfit.spring_boot_mybatis.mapper.GLiveInfoMapper;
import com.kfit.spring_boot_mybatis.pojo.db.Demo;
import com.kfit.spring_boot_mybatis.pojo.db.GLiveInfo;
import com.kfit.spring_boot_mybatis.service.DemoService;

import tk.mybatis.mapper.entity.Example;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;
	@Autowired
	private GLiveInfoMapper liveInfoMapper;
	@Autowired
	private DemoMappper demoMappper;
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping("/likeName")
	public List<Demo> likeName(String name) {
		/*
		 * 第一个参数：第几页; 第二个参数：每页获取的条数.
		 */
		logger.info("likeName");
		PageHelper.startPage(1, 2);
		return demoService.likeName(name);
	}

	@RequestMapping("/live")
	public List<GLiveInfo> live(String name) {
		/*
		 * 第一个参数：第几页; 第二个参数：每页获取的条数.
		 */
		logger.info("live");
		logger.error("live");
		PageHelper.startPage(1, 10);
		List<GLiveInfo> list = liveInfoMapper.selectAll();
		return list;
	}

	@RequestMapping("/save")
	public Demo save() {
		Demo demo = new Demo();
		demo.setName("张三");
		demoService.save(demo);
		return demo;
	}

	@RequestMapping("/ss")
	public List<GLiveInfo> ss() {

		Example e = new Example(GLiveInfo.class);
		liveInfoMapper.selectByExample(e);
		return null;
	}

	@RequestMapping("html")
	public String html(String html) {
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("iframe");
		Iterator<Element> iterator = elements.iterator();
		while(iterator.hasNext()){
			Element element = iterator.next();
			String src = element.attr("src");
			String str = "<video src=\""+src+"\" ></video> ";
			element.after(str);
			element.remove();
		}
		
		System.out.println(doc.toString());
		return doc.body().html();
	}

}
