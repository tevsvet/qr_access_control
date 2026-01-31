package com.program.qraccess.mapper;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.dto.UpdateMemberRequest;
import com.program.qraccess.mapper.util.StringMapper;
import com.program.qraccess.model.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = StringMapper.class)
public interface MemberMapper {

    MemberResponse toResponse(MemberEntity member);

    @Mapping(target = "qrCode", ignore = true)
    MemberEntity toEntity(MemberRequest request);

    @Mapping(target = "middleName",
            source = "middleName",
            qualifiedByName = "blankToNull")
    void updateMember(@MappingTarget MemberEntity member, UpdateMemberRequest request);
}
