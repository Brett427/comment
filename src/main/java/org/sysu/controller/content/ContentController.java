package org.sysu.controller.content;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sysu.bean.Ad;
import org.sysu.constant.PageCodeEnum;
import org.sysu.dto.AdDto;
import org.sysu.service.AdService;

import java.util.List;

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

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(AdDto adDto,Model model,@RequestParam(required = false, defaultValue = "1") int page)
	{
		List<Ad> ads =adService.selectAd(adDto,page,5);
		PageInfo<Ad> pageInfo =new PageInfo<>(ads);
		System.out.println(pageInfo.toString());
		model.addAttribute("pageInfo", pageInfo);

		model.addAttribute("list",ads);
		return "/content/adList";
	}


	@RequestMapping(value = "/modifyInit",method = RequestMethod.GET)
	public String initModify(@RequestParam(value = "id")Long id,Model model) {
		System.out.println(id);
		AdDto adDto =new AdDto();
		adDto = adService.getById(id);
		model.addAttribute("modifyObj",adDto);
		return "/content/adModify";
	}

	@RequestMapping(value = "/modifyInit1",method = RequestMethod.POST)
	public String modfy(AdDto adDto,Model model)
	{
		adService.modify(adDto);
		//model.addAttribute("modifyObj",adDto);
		return "redirect:/ad/list";
	}

	@RequestMapping("/remove")
	public String remove(@RequestParam("id")Long id)
	{
		adService.remove(id);
		return "redirect:/ad/list";
	}
}
