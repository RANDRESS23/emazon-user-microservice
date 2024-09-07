package com.emazon.microservicio_usuario;

import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.PermissionEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IPermissionRepository;
import com.emazon.microservicio_usuario.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.microservicio_usuario.configuration.Constants;
import com.emazon.microservicio_usuario.domain.enums.PermissionEnum;
import com.emazon.microservicio_usuario.domain.enums.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class MicroservicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUsuarioApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
		return args -> {
			/* Create PERMISSIONS */
			PermissionEntity createPermission = PermissionEntity.builder()
					.name(PermissionEnum.CREATE)
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name(PermissionEnum.READ)
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name(PermissionEnum.UPDATE)
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name(PermissionEnum.DELETE)
					.build();

			/* Create ROLES */
			RoleEntity roleAdmin = RoleEntity.builder()
					.name(RoleEnum.ADMIN)
					.description(Constants.DESCRIPTION_ROLE_ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleAuxBodega = RoleEntity.builder()
					.name(RoleEnum.AUX_BODEGA)
					.description(Constants.DESCRIPTION_ROLE_AUX_BODEGA)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleCliente = RoleEntity.builder()
					.name(RoleEnum.CLIENTE)
					.description(Constants.DESCRIPTION_ROLE_CLIENTE)
					.permissionList(Set.of(readPermission))
					.build();

			if (!permissionRepository.findByName(createPermission.getName()).isPresent()) {
				permissionRepository.save(createPermission);
			}

			if (!permissionRepository.findByName(readPermission.getName()).isPresent()) {
				permissionRepository.save(readPermission);
			}

			if (!permissionRepository.findByName(updatePermission.getName()).isPresent()) {
				permissionRepository.save(updatePermission);
			}

			if (!permissionRepository.findByName(deletePermission.getName()).isPresent()) {
				permissionRepository.save(deletePermission);
			}

			if (!roleRepository.findByName(roleAdmin.getName()).isPresent()) {
				roleRepository.save(roleAdmin);
			}

			if (!roleRepository.findByName(roleAuxBodega.getName()).isPresent()) {
				roleRepository.save(roleAuxBodega);
			}

			if (!roleRepository.findByName(roleCliente.getName()).isPresent()) {
				roleRepository.save(roleCliente);
			}
		};
	}
}
