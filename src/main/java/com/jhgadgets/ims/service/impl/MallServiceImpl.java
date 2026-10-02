package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.MallResponseDTO;
import com.jhgadgets.ims.dto.MallUpdateRequest;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.MallMapper;
import com.jhgadgets.ims.model.Mall;
import com.jhgadgets.ims.repository.MallRepository;
import com.jhgadgets.ims.service.MallService;

@Service

public class MallServiceImpl implements MallService {

	private final MallRepository mallRepository;
	private final MallMapper mallMapper;
	private static final Logger logger = LoggerFactory.getLogger(MallServiceImpl.class);

	public MallServiceImpl(MallRepository mallRepository, MallMapper mallMapper) {
		super();
		this.mallRepository = mallRepository;
		this.mallMapper = mallMapper;
	}

	@Override
	public MallResponseDTO createMall(Mall mall) {
		Mall savedMall = mallRepository.save(mall);
		logger.info("Mall created with id: {}",savedMall.getId());
		return mallMapper.toDto(savedMall);
	}

	@Override
	public MallResponseDTO getMallById(Long mallId) {
		return mallMapper.toDto(mallRepository.findById(mallId)
				.orElseThrow(()-> new ResourceNotFoundException("Mall", "id", mallId)));
	}

	@Override
	public List<MallResponseDTO> getAllMalls() {
		return mallMapper.toDtoList(mallRepository.findAll());
	}

	@Override
	public void deleteMallById(Long mallId) {
		Mall mall = mallRepository.findById(mallId)
				.orElseThrow(()-> new ResourceNotFoundException("Mall", "id", mallId));
		mallRepository.deleteById(mall.getId());
	}

	@Override
	public MallResponseDTO updateMall(Long mallId, MallUpdateRequest request) {
	    Mall existingMall = mallRepository.findById(mallId)
	            .orElseThrow(() -> new ResourceNotFoundException("Mall", "id", mallId));
	    
	    mallMapper.updateMallFromRequest(request, existingMall);
	    
	    Mall updatedMall = mallRepository.save(existingMall);
	    logger.info("Mall updated with id: {}", updatedMall.getId());
	    return mallMapper.toDto(updatedMall);
	}

}
