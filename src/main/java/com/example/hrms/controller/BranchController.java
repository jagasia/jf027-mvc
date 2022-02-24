package com.example.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hrms.entity.Branch;
import com.example.hrms.service.BranchService;

@Controller
public class BranchController {
	@Autowired
	private BranchService bs;

	@RequestMapping("/")
	public String home(ModelMap model)
	{
		bs.prefill();
		List<Branch> branches = bs.read();
		//how this branches (array list) be available for jsp?
		//put this in session/ put this is model
		model.addAttribute("branches", branches);		//this "branches" is available in jsp
		model.addAttribute("branch", new Branch());
		return "branch";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/branch", params = "add")
	public String create(Branch branch, ModelMap model) {
		bs.create(branch);
		List<Branch> branches = bs.read();
		model.addAttribute("branches", branches);		//this "branches" is available in jsp
		model.addAttribute("branch", new Branch());
		return "branch";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/branch")
	@ResponseBody
	public List<Branch> read() {
		List<Branch> branches = bs.read();
		return branches;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/branch/{bid}")
//	@ResponseBody
	public String read(@PathVariable("bid") String bid, ModelMap model) {
		Branch branch = bs.read(bid);
		if(branch==null)
			branch=new Branch();
		model.addAttribute("branch", branch);
		List<Branch> branches = bs.read();
		model.addAttribute("branches", branches);
		
		return "branch";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/branch", params = "update")
	public String update(Branch branch, ModelMap model) {
		System.out.println("Update method in controller started");
		
		bs.update(branch);
		List<Branch> branches = bs.read();
		model.addAttribute("branches", branches);
		return "branch";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/branch", params = "delete")
	public String delete(String bid, ModelMap model) {
		bs.delete(bid);
		List<Branch> branches = bs.read();
		model.addAttribute("branches", branches);
		model.addAttribute("branch",new Branch());
		return "branch";
	}
	
}
