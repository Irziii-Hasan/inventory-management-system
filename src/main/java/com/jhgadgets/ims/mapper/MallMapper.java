package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jhgadgets.ims.dto.MallResponseDTO;
import com.jhgadgets.ims.model.Mall;

@Mapper (componentModel = "spring")
public interface MallMapper {
	
	MallResponseDTO toDto(Mall mall);
	List<MallResponseDTO> toDtoList(List<Mall> malls);

}
