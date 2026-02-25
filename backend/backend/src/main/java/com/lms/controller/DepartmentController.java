package com.lms.controller;

import com.lms.dto.response.RespDepartmentDto;
import com.lms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * [학과 조회]
     * GET http://localhost:8080/api/departments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespDepartmentDto> getDepartmentInfo(@PathVariable int id) {

        RespDepartmentDto response = departmentService.findDepartmentById(id);

        return ResponseEntity.ok(response);
    }
}
