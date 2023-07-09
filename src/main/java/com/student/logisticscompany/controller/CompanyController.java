package com.student.logisticscompany.controller;


import com.student.logisticscompany.dto.CompanyDTO;
import com.student.logisticscompany.dto.CompanyEditDTO;
import com.student.logisticscompany.entity.CompanyEntity;
import com.student.logisticscompany.service.CompanyService;
import com.student.logisticscompany.service.EmployeeService;
import com.student.logisticscompany.service.OfficeService;
import com.student.logisticscompany.service.ParcelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final EmployeeService employeeService;
    private final OfficeService officeService;
    private final ParcelService parcelService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String companyView(Model model) {
        model.addAttribute("employeesCount", employeeService.getCount());
        model.addAttribute("officesCount", officeService.getCount());
        model.addAttribute("parcelsCount", parcelService.getCount());
        model.addAttribute("company", modelMapper.map(companyService.getCompany(), CompanyDTO.class));

        return "company/company";
    }

    @GetMapping("/revenue")
    public String companyRevenueView(Model model, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        model.addAttribute("employeesCount", employeeService.getCount());
        model.addAttribute("officesCount", officeService.getCount());
        model.addAttribute("parcelsCount", parcelService.getCount());
        model.addAttribute("company", modelMapper.map(companyService.getCompany(), CompanyDTO.class));

        double companyPeriodRevenue = companyService.calculateRveneuForPriod(LocalDate.parse(startDate), LocalDate.parse(endDate));

        model.addAttribute("periodRevenue", companyPeriodRevenue);
        return "company/company";
    }

    @GetMapping("/edit")
    public String editCompanyView(Model model) {
        model.addAttribute("company", modelMapper.map(companyService.getCompany(), CompanyEditDTO.class));
        return "company/edit";
    }

    @PostMapping("/edit")
    public String editCompany(Model model, @ModelAttribute CompanyEditDTO companyEditDTO) {
        model.addAttribute("company", modelMapper.map(companyService.getCompany(), CompanyEditDTO.class));
//        companyService.editCompany(modelMapper.map(companyEditDTO , CompanyEntity.class));
        return "company/edit";
    }
}
