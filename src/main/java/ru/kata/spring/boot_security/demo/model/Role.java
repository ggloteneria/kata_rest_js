package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
