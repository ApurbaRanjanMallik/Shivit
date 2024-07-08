package com.shivit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivit.entity.ProjectMaster;
import com.shivit.repo.ProjectMasterRepository;

@Service
public class ProjectMasterService {

	@Autowired
	private ProjectMasterRepository projectMasterRepository;

	public List<ProjectMaster> getAllProjects() {
		return projectMasterRepository.findAll();
	}

	public List<ProjectMaster> getActiveProjects() {
		return projectMasterRepository.findAll().stream()
				.filter(project -> project.getProjectStatus() == ProjectMaster.Status.ACTIVE).toList();
	}

	public ProjectMaster getProjectById(Long id) {
		return projectMasterRepository.findById(id).orElse(null);
	}

	public ProjectMaster addProject(ProjectMaster projectMaster) {
		return projectMasterRepository.save(projectMaster);
	}

	public void deleteProject(Long id) {
		projectMasterRepository.deleteById(id);
	}
}
