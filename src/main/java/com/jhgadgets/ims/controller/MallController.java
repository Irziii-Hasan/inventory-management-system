package com.jhgadgets.ims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhgadgets.ims.dto.MallResponseDTO;
import com.jhgadgets.ims.model.Mall;
import com.jhgadgets.ims.service.MallService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/malls")
public class MallController {

	private final MallService mallService;

	public MallController(MallService mallService) {
		super();
		this.mallService = mallService;
	}
	
	@PostMapping
	public ResponseEntity<MallResponseDTO> createMall(@Valid @RequestBody Mall mall){
		MallResponseDTO savedMall = mallService.createMall(mall);
		return new ResponseEntity<>(savedMall,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<MallResponseDTO>> getMalls(){
		List<MallResponseDTO> malls = mallService.getAllMalls();
		return new ResponseEntity<List<MallResponseDTO>>(malls,HttpStatus.OK);
	}
	
	@GetMapping("/{mallId}")
	public ResponseEntity<MallResponseDTO> getMallById(@PathVariable Long mallId){
		MallResponseDTO mall = mallService.getMallById(mallId);
		return new ResponseEntity<>(mall, HttpStatus.OK);
	}
	
	@DeleteMapping("/{mallId}")
	public ResponseEntity<Void> deleteMallById(@PathVariable Long mallId){
		mallService.deleteMallById(mallId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
