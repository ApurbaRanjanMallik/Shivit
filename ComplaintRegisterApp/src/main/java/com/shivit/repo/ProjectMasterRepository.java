package com.shivit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivit.entity.ProjectMaster;

public interface ProjectMasterRepository extends JpaRepository<ProjectMaster, Long> {

//	@Query("SELECT new com.shivit.dto.CommonDropDownResponse(id, projectName) from ProjectMaster where projectStatus=:status")
//	public List<CommonDropDownResponse> getSelectionList(Status status);
//


}
