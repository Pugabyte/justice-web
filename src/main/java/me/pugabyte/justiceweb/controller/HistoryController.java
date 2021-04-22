package me.pugabyte.justiceweb.controller;

import edenapi.models.punishments.PunishmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static me.pugabyte.justiceweb.controller.IndexController.shared;

@Controller
@RequestMapping("history")
public class HistoryController {

	@GetMapping("/{player}")
	public String history(@PathVariable("player") String player, Model model) {
		shared(model);
		model.addAttribute("name", player);
		model.addAttribute("history", new PunishmentsService().get(player));
		return "history/index";
	}

}
