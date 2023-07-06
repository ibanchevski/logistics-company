package com.student.logisticscompany.controller;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.dto.OfficeDTO;
import com.student.logisticscompany.dto.ParcelDTO;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.EmployeeService;
import com.student.logisticscompany.service.OfficeService;
import com.student.logisticscompany.service.ParcelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/parcels")
public class ParcelsController {
    private final EmployeeService employeeService;
    private final ParcelService parcelService;
    private final OfficeService officeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String getParcelsView(Model model) {
        List<ParcelDTO> parcels = parcelService.getAll()
                        .stream()
                        .map(parcelEntity -> modelMapper.map(parcelEntity, ParcelDTO.class))
                        .toList();
        model.addAttribute("parcels", parcels);
        return "parcels/parcels";
    }

    @GetMapping("/new")
    public String getNewParcelView(Model model, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<OfficeDTO> allOffices = officeService.getAll()
                        .stream()
                        .map(office -> modelMapper.map(office, OfficeDTO.class))
                        .toList();
        model.addAttribute("offices", allOffices);
        model.addAttribute("employeeOffice", employeeService.getOffice(user.getUsername()));
        model.addAttribute("parcel", new CreateParcelDTO());
        return "parcels/new";
    }

    @PostMapping("/new")
    public String addNewParcel(Model model, Authentication authentication, @Valid @ModelAttribute("parcel") CreateParcelDTO createParcelDTO, BindingResult bindingResult) {
        UserEntity employeeUser = (UserEntity) authentication.getPrincipal();

        if (bindingResult.hasErrors()) {
            List<OfficeDTO> allOffices = officeService.getAll()
                    .stream()
                    .map(office -> modelMapper.map(office, OfficeDTO.class))
                    .toList();
            model.addAttribute("offices", allOffices);
            model.addAttribute("employeeOffice", employeeService.getOffice(employeeUser.getUsername()));
            return "parcels/new";
        }

        createParcelDTO.setEmployee(employeeUser);
        createParcelDTO.setOfficeSend(employeeService.getOffice(employeeUser.getUsername()));
        parcelService.addNew(createParcelDTO);
        return "redirect:/parcels";
    }

    @GetMapping("/deliver/{id}")
    public String deliverParcel(Model model, Authentication authentication, @PathVariable("id") Long id) {
        UserEntity deliveryEmployee = (UserEntity) authentication.getPrincipal();
        parcelService.deliver(id, deliveryEmployee);
        return "redirect:/parcels";
    }

    @GetMapping("/cancel/{id}")
    public String cancelParcel(Model model, Authentication authentication, @PathVariable("id") Long id) {
        parcelService.cancel(id);
        return "redirect:/parcels";
    }
}
