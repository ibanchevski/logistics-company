package com.student.logisticscompany.controller;

import com.student.logisticscompany.dto.AddOfficeDTO;
import com.student.logisticscompany.dto.OfficeDTO;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.service.OfficeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/offices")
public class OfficeController {
    private final ModelMapper modelMapper;
    private final OfficeService officeService;

    @GetMapping
    public String getOfficesView(Model model) {
        List<OfficeDTO> offices = officeService.getAll()
                .stream()
                .map(officeEntity -> modelMapper.map(officeEntity, OfficeDTO.class))
                .toList();

        model.addAttribute("offices", offices);
        return "offices/offices";
    }

    @GetMapping("/new")
    public String getNewOfficeView(Model model) {
        model.addAttribute("office", new AddOfficeDTO());
        return "offices/new";
    }

    @PostMapping("/new")
    public String registerNewOffice(Model model, Authentication authentication, @ModelAttribute("office") AddOfficeDTO office) {
        office.setAddedBy((UserEntity) authentication.getPrincipal());
        this.officeService.create(office);
        model.addAttribute("success", true);
        return "offices/new";
    }

    @GetMapping("/delete/{officeId}")
    public String deleteOffice(Model model, @PathVariable Long officeId) {
        officeService.delete(officeId);
        return "redirect:/offices";
    }
}
