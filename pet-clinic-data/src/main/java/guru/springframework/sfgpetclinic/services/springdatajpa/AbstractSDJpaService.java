package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSDJpaService<T extends BaseEntity, R extends CrudRepository<T, Long>> implements CrudService<T , Long> {
	protected R repository;

	public AbstractSDJpaService(R repository) {
		this.repository = repository;
	}

	@Override
	public Set<T> findAll() {
		Set<T> set = new HashSet<T>();
		repository.findAll().forEach(set::add);
		return set;
	}

	@Override
	public T findById(Long aLong) {
		return repository.findById(aLong).orElse(null);
	}

	@Override
	public T save(T object) {
		return repository.save(object);
	}

	@Override
	public void delete(T object) {
		repository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		repository.deleteById(aLong);
	}
}
