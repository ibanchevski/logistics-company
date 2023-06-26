package com.student.logisticscompany.controller;

import com.student.logisticscompany.dto.ActivateEmployeeDTO;
import com.student.logisticscompany.dto.AddEmployeeDTO;
import com.student.logisticscompany.dto.RegisterEmployeeDTO;
import com.student.logisticscompany.entity.EmployeeEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.EmployeeService;
import com.student.logisticscompany.service.OfficeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
    private final OfficeService officeService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getEmployeesView(Model model) {
        return "employees/employees";
    }

    @GetMapping("/new")
    public String showEmployeeRegistrationView(Model model) {
        model.addAttribute("employee", new AddEmployeeDTO());
        model.addAttribute("offices", this.officeService.getAll());
        return "employees/new";
    }

    @PostMapping("/new")
    public String registerEmployee(Model model, Authentication authentication, @ModelAttribute("employee") AddEmployeeDTO addEmployeeDTO) {
        addEmployeeDTO.setAddedBy((UserEntity) authentication.getPrincipal());
        this.employeeService.add(addEmployeeDTO);

        model.addAttribute("success", true);
        return "employees/new";
    }

    @GetMapping("/activate")
    public String showEmployeeActivateView(Model model) {
        return "employees/activate";
    }

    @PostMapping("/activate")
    public String activateEmployee(Model model, @ModelAttribute("activateEmployee")ActivateEmployeeDTO activateEmployeeDTO) {
        try {
            EmployeeEntity employeeToActivate = employeeService.activate(activateEmployeeDTO);
            model.addAttribute("employee", modelMapper.map(employeeToActivate, RegisterEmployeeDTO.class));
            return "employees/activate-details";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.addAttribute("error", ex.getMessage());
            return "employees/activate";
        }
    }

    @PostMapping("/activate-details")
    public String activateEmployeeDetails(Model model, @ModelAttribute("employee")RegisterEmployeeDTO registerEmployeeDTO) {
        this.employeeService.register(registerEmployeeDTO);
        return "/login";
    }
}
