package com.student.logisticscompany.controller;

import com.student.logisticscompany.dto.UserDTO;
import com.student.logisticscompany.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientsController {
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping
    public String loadClientsView(Model model) {
        List<UserDTO> clients = userService.findAllClients()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

        model.addAttribute("clients", clients);
        return "clients/clients";
    }
}
