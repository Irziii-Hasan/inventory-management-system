package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.MallResponseDTO;
import com.jhgadgets.ims.dto.MallUpdateRequest;
import com.jhgadgets.ims.model.Mall;

public interface MallService {
	
	MallResponseDTO createMall(Mall mall);
	MallResponseDTO getMallById(Long mallId);
	List<MallResponseDTO> getAllMalls();
	MallResponseDTO updateMall(Long mallId, MallUpdateRequest mall);
	void deleteMallById(Long mallId);

}
