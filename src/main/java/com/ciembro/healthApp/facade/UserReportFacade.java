package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.UserReportRow;
import com.ciembro.healthApp.domain.UserReportRowDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.mapper.UserReportMapper;
import com.ciembro.healthApp.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReportFacade {

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private UserReportMapper userReportMapper;

    public List<UserReportRowDto> generateReport(String username) throws UserNotFoundException {
        List<UserReportRow> userReport = userReportService
                .generateReport(username);
        return userReportMapper.mapToUserReportDto(userReport);
    }

    public List<UserReportRowDto> filterByDrug(String drugId, String username) throws UserNotFoundException, DrugNotFoundException {
        List<UserReportRow> userReport = userReportService.filterByDrug(Long.parseLong(drugId), username) ;
        return userReportMapper.mapToUserReportDto(userReport);
    }


}
