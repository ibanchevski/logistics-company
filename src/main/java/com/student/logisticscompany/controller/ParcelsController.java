package com.student.logisticscompany.controller;

import com.student.logisticscompany.dto.CreateParcelDTO;
import com.student.logisticscompany.dto.OfficeDTO;
import com.student.logisticscompany.dto.ParcelDTO;
import com.student.logisticscompany.entity.OfficeEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.EmployeeService;
import com.student.logisticscompany.service.OfficeService;
import com.student.logisticscompany.service.ParcelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("parcels", modelMapper.map(parcelService.getAll(), ParcelDTO.class));
        return "parcels/parcels";
    }

    @GetMapping("/new")
    public String getNewParcelView(Model model, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        CreateParcelDTO  createParcelDTO = new CreateParcelDTO();
        List<OfficeDTO> allOffices = officeService.getAll()
                        .stream()
                        .map(office -> modelMapper.map(office, OfficeDTO.class))
                        .toList();
        model.addAttribute("offices", allOffices);
        OfficeDTO officeDTO = modelMapper.map(employeeService.getOffice(user.getUsername()), OfficeDTO.class);
        model.addAttribute("employeeOffice", officeDTO);
        model.addAttribute("parcel", createParcelDTO);
        return "parcels/new";
    }

    @PostMapping("/new")
    public String addNewParcel(Model model, Authentication authentication, @ModelAttribute("parcel") CreateParcelDTO createParcelDTO) {
        UserEntity employeeUser = (UserEntity) authentication.getPrincipal();
        createParcelDTO.setEmployeeId(employeeUser.getId());
        parcelService.addNew(createParcelDTO);
        return "parcels/parcels";
    }
}
