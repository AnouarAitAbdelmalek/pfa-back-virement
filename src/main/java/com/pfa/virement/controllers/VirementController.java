package com.pfa.virement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.virement.entities.Virement;
import com.pfa.virement.services.VirementService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class VirementController {
	
	
	@Autowired
	VirementService virementService;
	
	
	@GetMapping("/virement")
	@ResponseStatus(HttpStatus.OK)
	public Virement getVirement(@RequestParam(value="id") Long id)
	{
		return virementService.getVirement(id);
	}
	
	
	
	@PostMapping("/virement")
	@ResponseStatus(HttpStatus.CREATED)
	public void EffectuerVirement(@RequestBody Virement virement)
	{
		virementService.EffectuerVirement(virement);
	}
	
	@PutMapping("/virement/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void modififierVirement(@PathVariable(value="id") Long id,@RequestBody Virement virement)
	{
		virementService.modifierVirement(id,virement);
	}
	
	
	@GetMapping("/virement/{id}/confirmation")
	@ResponseStatus(HttpStatus.OK)
	public void confirmerEtSigner(@PathVariable(value="id") Long id)
	{
		virementService.confirmerEtSigner(id);
	}

}
