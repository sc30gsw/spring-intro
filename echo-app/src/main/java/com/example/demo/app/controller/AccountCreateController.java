package com.example.demo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.form.AccountCreateForm;

@Controller
@RequestMapping("/accounts")
@SessionAttributes(types = AccountCreateForm.class)
public class AccountCreateController {

	@ModelAttribute(value = "accountCreateForm")
	public AccountCreateForm setUpAccountCreateForm() {
		return new AccountCreateForm();
	}

	@PostMapping("/create")
	public String create(@Validated AccountCreateForm form, BindingResult result, @ModelAttribute("password") String password,
			RedirectAttributes rediretAttributes) {
		
		if (result.hasErrors()) {
			return "redirect:/accounts";
		}
		
		return "redirect:/accounts/create?complete";

	}
	
	@GetMapping(path = "/create", params = "complete")
	public String createComplete(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "account/createComplete";
	}

}
