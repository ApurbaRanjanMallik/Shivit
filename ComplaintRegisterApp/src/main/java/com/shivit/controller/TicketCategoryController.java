package com.shivit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shivit.entity.TicketCategory;
import com.shivit.service.TicketCategoryService;

@RestController
@RequestMapping("/ticketcategory")
public class TicketCategoryController {

	@Autowired
	private TicketCategoryService ticketCategoryService;

	@GetMapping("/list")
	public ModelAndView getAllTicketCategories(ModelMap model) {
		List<TicketCategory> ticketCategories = ticketCategoryService.getAllTicketCategories();
		model.addAttribute("ticketCategories", ticketCategories);
		return new ModelAndView("viewTicketCategoryList", model);
	}

	@GetMapping("/{id}")
	public ModelAndView getTicketCategoryById(@PathVariable Long id, ModelMap model) {
		TicketCategory ticketCategory = ticketCategoryService.getTicketCategoryById(id);
		model.addAttribute("ticketCategory", ticketCategory);
		return new ModelAndView("viewTicketCategory", model);
	}

	@PostMapping("/add")
	public ModelAndView addTicketCategory(@ModelAttribute TicketCategory ticketCategory, ModelMap model) {
		ticketCategoryService.addTicketCategory(ticketCategory);
		return new ModelAndView("redirect:/ticketcategory/list");
	}

	@GetMapping("/addform")
	public ModelAndView showAddTicketCategoryForm(ModelMap model) {
		model.addAttribute("ticketCategory", new TicketCategory());
		return new ModelAndView("addTicketCategory", model);
	}
	@GetMapping("/delete/{id}")
	public ModelAndView deleteTicketCategory(@PathVariable Long id) {
	    ticketCategoryService.deleteTicketCategory(id);
	    return new ModelAndView("redirect:/ticketcategory/list");
	}
}
