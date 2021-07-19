package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.UserReportRow;
import com.ciembro.healthApp.domain.UserReportRowDto;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.mapper.UserReportMapper;
import com.ciembro.healthApp.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReportFacade {

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private UserReportMapper userReportMapper;

    @Autowired
    private DrugMapper drugMapper;

    public List<UserReportRowDto> generateReport(String username) throws UserNotFoundException {
        List<UserReportRow> userReport = userReportService
                .generateReport(username);
        return userReportMapper.mapToUserReportDto(userReport);
    }

    public List<UserReportRowDto> filterByDrug(DrugDto drugDto, String username) throws UserNotFoundException {
        Drug drug = drugMapper.mapToDrug(drugDto);
        List<UserReportRow> userReport = userReportService.filterByDrug(drug, username) ;
        return userReportMapper.mapToUserReportDto(userReport);
    }


}
