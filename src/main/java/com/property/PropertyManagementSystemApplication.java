package com.property;
import org.modelmapper.ModelMapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.property.config.AppConstants;
import com.property.models.Role;
import com.property.repository.RoleRepo;

@SpringBootApplication
public class PropertyManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo  roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(PropertyManagementSystemApplication.class, args);
		System.out.println("server started on port 9090");
	}

    @Bean
   public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.passwordEncoder.encode("anant"));
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setId(AppConstants.CUSTOMER_USER);
			role2.setName("NORMAL_USER");
			
//			Role role3 = new Role();
//			role3.setId(AppConstants.CUSTOMER_USER);
//			role3.setName("OWNER_USER");
//			
			
			List<Role> roles = List.of(role1, role2);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
				
			});
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	
    
}
