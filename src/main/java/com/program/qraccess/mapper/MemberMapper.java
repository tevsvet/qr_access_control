package com.program.qraccess.mapper;

import com.program.qraccess.dto.MemberRequest;
import com.program.qraccess.dto.MemberResponse;
import com.program.qraccess.dto.UpdateMemberRequest;
import com.program.qraccess.model.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberResponse toResponse(Member member);
    Member toEntity(MemberRequest request);
}
