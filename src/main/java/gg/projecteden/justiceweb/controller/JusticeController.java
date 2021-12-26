package gg.projecteden.justiceweb.controller;

import gg.projecteden.models.punishments.PunishmentType;
import gg.projecteden.models.punishments.PunishmentsService;
import gg.projecteden.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class JusticeController {

	@GetMapping
	public String home() {
		return "index";
	}

	@GetMapping("history/{player}")
	public String history(@PathVariable("player") String player, Model model) {
		model.addAttribute("history", new PunishmentsService().get(player));
		return "player";
	}

	@GetMapping({"recent", "recent/{type}", "recent/{type}/{page}"})
	public String recent(
			@PathVariable(value = "type", required = false) String type,
			@PathVariable(value = "page", required = false) Integer page,
			Model model
	) {
		if (type.endsWith("s")) type = StringUtils.replaceLast(type, "s", "");
		PunishmentType punishmentType = PunishmentType.valueOf(type.toUpperCase());

		if (page == null || page < 1) page = 1;

		model.addAttribute("type", punishmentType);
		model.addAttribute("punishments", new PunishmentsService().page(punishmentType, page, 10));
		return "recent";
	}

	@GetMapping("error")
	public String error(HttpServletRequest request, Model model) {
		model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		return "error";
	}

}