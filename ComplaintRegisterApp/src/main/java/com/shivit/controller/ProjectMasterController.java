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

import com.shivit.entity.ProjectMaster;
import com.shivit.service.ProjectMasterService;

@RestController
@RequestMapping("/projectmaster")
public class ProjectMasterController {

	@Autowired
	private ProjectMasterService projectMasterService;

	@GetMapping("/list")
	public ModelAndView getAllProjects(ModelMap model) {
		List<ProjectMaster> projects = projectMasterService.getAllProjects();
		model.addAttribute("projects", projects);
		return new ModelAndView("viewProjectlist", model);
	}

	@GetMapping("/{id}")
	public ModelAndView getProjectById(@PathVariable Long id, ModelMap model) {
		ProjectMaster project = projectMasterService.getProjectById(id);
		model.addAttribute("project", project);
		return new ModelAndView("viewProject", model);
	}

	@PostMapping("/add")
	public ModelAndView addProject(@ModelAttribute ProjectMaster projectMaster, ModelMap model) {
		projectMasterService.addProject(projectMaster);
		return new ModelAndView("redirect:/projectmaster/list");
	}

	@GetMapping("/addform")
	public ModelAndView showAddProjectForm(ModelMap model) {
		model.addAttribute("projectMaster", new ProjectMaster());
		return new ModelAndView("addProject", model);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteProject(@PathVariable Long id) {
	    projectMasterService.deleteProject(id);
	    return new ModelAndView("redirect:/projectmaster/list");
	}


}
