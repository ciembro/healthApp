package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.UserReportRow;
import com.ciembro.healthApp.domain.UserReportRowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserReportMapper {

    @Autowired
    private InsightsMapper insightsMapper;

    @Autowired
    private DrugMapper drugMapper;

    public UserReportRowDto mapToUserReportDto(UserReportRow userReportRow){
        UserReportRowDto userReportRowDto = new UserReportRowDto();
        userReportRowDto.setInsights(insightsMapper
                .mapToCreatedInsightDto(userReportRow.getInsights()));
        userReportRowDto.setDrugs(drugMapper.mapToDrugDtoList(userReportRow.getDrugs()));
        return userReportRowDto;
    }

    public List<UserReportRowDto> mapToUserReportDto(List<UserReportRow> userReportRow){
        return userReportRow.stream()
                .map(this::mapToUserReportDto)
                .collect(Collectors.toList());
    }


}
