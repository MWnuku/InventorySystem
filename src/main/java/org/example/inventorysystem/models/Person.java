package org.example.inventorysystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "person_id")
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String unit;

	@OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JsonIdentityReference(alwaysAsId = true)
	private List<InventoryField> inventoryFieldList;
	@Enumerated(EnumType.STRING)
	private Role role = Role.User;

	public void addInventoryField(InventoryField inventoryField) {
		if (inventoryField != null) {
			inventoryField.setPerson(this);
			inventoryFieldList.add(inventoryField);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
