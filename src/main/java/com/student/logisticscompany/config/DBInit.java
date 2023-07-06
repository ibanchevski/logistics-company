package com.student.logisticscompany.config;

import com.student.logisticscompany.entity.CompanyEntity;
import com.student.logisticscompany.entity.RoleEntity;
import com.student.logisticscompany.entity.UserEntity;
import com.student.logisticscompany.repository.CompanyRepository;
import com.student.logisticscompany.repository.RoleRepository;
import com.student.logisticscompany.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class DBInit implements CommandLineRunner {
    private RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;


    @Override
    public void run(String ...args) throws Exception {
        RoleEntity adminRole = new RoleEntity();
        adminRole.setAuthority("ADMIN");

        RoleEntity userRole = new RoleEntity();
        userRole.setAuthority("USER");

        // Create default admin
        UserEntity admin = new UserEntity();
        admin.setName("Administrator Administrator");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("pass1"));
        admin.setAuthorities(Set.of(adminRole));

        long usersCount = this.userRepository.count();
        if (usersCount == 0) {
            this.userRepository.save(admin);
        }

        CompanyEntity company = new CompanyEntity();
        company.setRevenue(0);
        company.setName("My company");

        if (this.companyRepository.count() == 0) {
            companyRepository.save(company);
        }

    }

}
