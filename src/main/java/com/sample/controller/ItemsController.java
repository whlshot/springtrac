package com.sample.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sample.entity.ItemsCustom;
import com.sample.entity.ItemsQueryVo;
import com.sample.exception.CustomException;
import com.sample.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;

	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {
		Map<String, String> itemTypes = new HashMap<>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}

	@RequestMapping("/getItems")
	public ModelAndView getItems(ItemsQueryVo itemsQueryVo) throws Exception {
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("/items");
		return modelAndView;
	}

	@RequestMapping("/editItem")
	public ModelAndView editItems(Integer id) throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/editItem");
		modelAndView.addObject("itemsCustom", itemsCustom);
		return modelAndView;
	}

	// 在需要校验的pojo前边添加@Validated,在需要校验的pojo后边添加BindingResult
	// bindingResult接受校验出错信息
	// 注意：@Validated XXX xxx, BindingResult bindingResult是配对出现，并且形参的顺序是固定的。
	@RequestMapping("/updateItem")
	public String updateItem(Model model, Integer id, @Validated ItemsCustom itemsCustom, BindingResult bindingResult, @RequestParam("pic_img") MultipartFile file) throws Exception {
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
			model.addAttribute("allErrors", allErrors);
			return "/editItem";
		}
		String originalFilename = file.getOriginalFilename();
		if (file != null && originalFilename != null && originalFilename.length() > 0) {
			String path = "D:\\Java\\Workspaces\\upload\\temp\\";
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(path + newFileName);
			file.transferTo(newFile);
			itemsCustom.setPic(newFileName);
		}
		itemsService.updateItems(id, itemsCustom);
		return "redirect:getItems.action";
	}

	@RequestMapping("/deleteItems")
	public String deleteItems(Integer... ids) throws Exception {
		return "redirect:getItems.action";
	}

	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception {
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("/editItemsQuery");
		return modelAndView;
	}

	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

		return "redirect:getItems.action";
	}

	@RequestMapping("/testException")
	public String testException(Integer id) throws Exception {
		if (id == null) {
			throw new CustomException("id不能为null");
		}
		return "redirect:getItems.action";
	}

	// String
	public String testString(Model model) throws Exception {
		model.addAttribute("list", "data");
		return "success";
	}

	// 重定向
	public String testRedirect() throws Exception {
		return "redirect:getItems.action";// 因为是在同一个类中，所以不用加"/items"
	}

	// 请求转发
	public String testForward(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:getItems.action";
	}

}
