package com.emazon.microservicio_usuario.adapters.driving.controller;

import com.emazon.microservicio_usuario.adapters.driving.dto.request.AddRoleRequest;
import com.emazon.microservicio_usuario.adapters.driving.dto.response.RoleResponse;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IRoleRequestMapper;
import com.emazon.microservicio_usuario.adapters.driving.mapper.IRoleResponseMapper;
import com.emazon.microservicio_usuario.domain.api.IRoleServicePort;
import com.emazon.microservicio_usuario.domain.model.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleRestController {
    private final IRoleServicePort roleServicePort;
    private final IRoleRequestMapper roleRequestMapper;
    private final IRoleResponseMapper roleResponseMapper;

    @PostMapping()
    public ResponseEntity<RoleResponse> addRole(@Valid @RequestBody AddRoleRequest request) {
        Role role = roleRequestMapper.addRequestToRole(request);
        roleServicePort.saveRole(role);

        RoleResponse response = roleResponseMapper.toRoleResponse(role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
