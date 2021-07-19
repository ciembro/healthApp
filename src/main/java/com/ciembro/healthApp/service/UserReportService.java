package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.Insights;
import com.ciembro.healthApp.domain.UserReportRow;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserReportService {

    @Autowired
    private UserService userService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private InsightsService insightsService;

   public List<UserReportRow> generateReport(String username) throws UserNotFoundException {
       List<UserReportRow> userReport = new ArrayList<>();
       User user = userService.findByUsername(username);
       List<Insights> insightsList = insightsService.getAllInsightsByUserId(user.getId());

       for (Insights insights : insightsList){
           UserReportRow row = new UserReportRow();
           row.setInsights(insights);
           row.setDrugs(drugService.findAllByDate(insights.getCreationDate(), username));
           userReport.add(row);
       }
       return userReport;
   }

   public List<UserReportRow> filterByDrug(Drug drug, String username) throws UserNotFoundException {
       List<UserReportRow> report = generateReport(username);
       return report.stream()
               .filter(row -> row.getDrugs().contains(drug))
               .collect(Collectors.toList());
   }
}
