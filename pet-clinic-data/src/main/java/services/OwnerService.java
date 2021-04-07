package services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
	Owner findById(Long id);

	Owner save(Owner owner);

	Owner findByName(String name);

	Set<Owner> findAll();
}
