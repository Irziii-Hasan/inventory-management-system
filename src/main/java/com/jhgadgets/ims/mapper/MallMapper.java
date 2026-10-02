package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.MallResponseDTO;
import com.jhgadgets.ims.dto.MallUpdateRequest;
import com.jhgadgets.ims.model.Mall;

@Mapper (componentModel = "spring")
public interface MallMapper {
	
	MallResponseDTO toDto(Mall mall);
	List<MallResponseDTO> toDtoList(List<Mall> malls);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
    void updateMallFromRequest(MallUpdateRequest source, @MappingTarget Mall target);

}
