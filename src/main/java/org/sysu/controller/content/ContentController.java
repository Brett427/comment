package org.sysu.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sysu.constant.PageCodeEnum;
import org.sysu.dto.AdDto;
import org.sysu.service.AdService;

@Controller
@RequestMapping("/ad")
public class ContentController {

	@Autowired
	private AdService adService;


	@RequestMapping("/initList")
	public String initList() {
		return "/content/adList";
	}

	@RequestMapping(value = "/addad",method = RequestMethod.GET)
	public String addad()
	{
		return "/content/adAdd";
	}


	@RequestMapping(value = "/addad",method = RequestMethod.POST)
	public String adadd(AdDto adDto,Model model)
	{
		if(adService.insertad(adDto))
		{
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
		}
		else
		{
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
		}
		//带返回码的返回页面,在jsp中埋得值接收到了,接收到后,前端js在页面加载前先判断hidden是否为空.
		return "/content/adAdd";
	}

	@RequestMapping("/initModify")
	public String initModify() { 
		return "/content/modify";
	}
}