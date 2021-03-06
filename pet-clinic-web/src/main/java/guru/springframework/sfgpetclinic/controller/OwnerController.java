package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	private final OwnerService ownerService;
	private final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

//	@RequestMapping({"", "/", "/index", "/index.html"})
//	public String listOwners(Model model){
//
//		model.addAttribute("owners", ownerService.findAll());
//		return "owners/index";
//	}

	@RequestMapping({"/find"})
	public String findOwners(Model model){
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}

	@GetMapping({"/", ""})
	public String processFindForm(Owner owner, BindingResult result, Model model){
		if(owner.getLastName() == null){
			owner.setLastName("");
		}

		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

		if(results.isEmpty()){
			result.rejectValue("lastName", "notFound", "not found");;
			return "owners/findOwners";
		}else if(results.size() == 1){
			owner = results.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}else{
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId){
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
		return mav;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model){
		model.addAttribute("owner", new Owner());
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Validated Owner owner, BindingResult result){
		if(result.hasErrors()){
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}else{
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){
		model.addAttribute("owner", ownerService.findById(ownerId));
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@PathVariable Long ownerId, @Validated Owner owner, BindingResult result){
		if(result.hasErrors()){
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}else{
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
}
