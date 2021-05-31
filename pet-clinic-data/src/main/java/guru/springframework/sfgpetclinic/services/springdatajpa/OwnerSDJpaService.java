package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService extends AbstractSDJpaService<Owner, OwnerRepository> implements OwnerService {
	public OwnerSDJpaService(OwnerRepository ownerRepository) {
		super(ownerRepository);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return repository.findByLastName(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastname) {
		return repository.findAllByLastNameLike(lastname);
	}
}
