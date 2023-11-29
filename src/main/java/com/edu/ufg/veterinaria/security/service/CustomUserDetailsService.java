package com.edu.ufg.veterinaria.security.service;

import com.edu.ufg.veterinaria.models.Empleado;
import com.edu.ufg.veterinaria.models.Rol;
import com.edu.ufg.veterinaria.security.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    public CustomUserDetailsService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Loading user by username. {}", userName);
        Empleado empleadoResponse = empleadoRepository.findByCorreoEmpleado(userName);
        if (empleadoResponse != null) {
            log.info("User found by username successfully !!!");
            return new org.springframework.security.core.userdetails.User(empleadoResponse.getCorreoEmpleado(), empleadoResponse.getContrasena(),
                    getGrantedAuthorities(empleadoResponse.getRolList()));
        } else {
            log.error("User not found by username.");
            throw new UsernameNotFoundException(String.format("User with name%s not found.", userName));
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Rol> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol rol : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getNombreRol())));
        }
        return authorities;
    }

}
