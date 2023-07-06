package com.student.logisticscompany.controller;


import com.student.logisticscompany.service.EmployeeService;
import com.student.logisticscompany.service.OfficeService;
import com.student.logisticscompany.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final EmployeeService employeeService;
    private final OfficeService officeService;
    private final ParcelService parcelService;

    @GetMapping
    public String companyView(Model model) {
//        model.addAttribute("employeesCount", employeeService.getCount());
//        model.addAttribute("officesCount", officeService.getCount());
//        model.addAttribute("parcelsCount", parcelService.getCount());

        return "company/company";
    }
}
