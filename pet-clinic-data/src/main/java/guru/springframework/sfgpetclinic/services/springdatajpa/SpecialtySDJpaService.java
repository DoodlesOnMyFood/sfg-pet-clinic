package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;

public class SpecialtySDJpaService extends AbstractSDJpaService<Specialty, SpecialtyRepository> implements SpecialtyService {
	public SpecialtySDJpaService(SpecialtyRepository repository) {
		super(repository);
	}
}
