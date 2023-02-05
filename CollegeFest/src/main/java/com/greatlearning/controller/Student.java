package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.service.StudentService;

@Controller
public class Student {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated
				(1, "firstName", "lastName","Course","Country", model);
	}
	
	private String findPaginated
			(int i, String string, String string2, String string3, String string4, Model model) {
		return null;
	}

	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		com.greatlearning.model.Student student = new com.greatlearning.model.Student();
		model.addAttribute("student", student);
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") com.greatlearning.model.Student student) {
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		com.greatlearning.model.Student student = new com.greatlearning.model.Student();
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id) {
		this.studentService.deleteStudentById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
				int pageSize = 5;
				Page<com.greatlearning.model.Student> page = studentService.findPaginated
						(pageNo, pageSize, sortField, sortDir);
				List<com.greatlearning.model.Student> listStudent = page.getContent();

				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("sortField", sortField);
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
				model.addAttribute("listStudent", listStudent);
				return "index";
			}
}