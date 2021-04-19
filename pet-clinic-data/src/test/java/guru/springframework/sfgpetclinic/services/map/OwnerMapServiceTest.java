package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lastName = "Smith";

	@BeforeEach
	void setUp() {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		Owner owner = Owner.builder().id(ownerId).lastName(lastName).firstName("Yoo").city("123456").build();
		ownerMapService.save(owner);
	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(ownerSet.size(), 1);
	}

	@Test
	void deleteById() {
		ownerMapService.deleteById(ownerId);
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(ownerSet.size(), 0);
	}

	@Test
	void delete() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(ownerSet.size(), 0);
	}

	@Test
	void saveExistingId() {
		final long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner saved = ownerMapService.save(owner2);
		assertEquals(id, saved.getId());
	}

	@Test
	void saveNoId() {
		Owner saved = ownerMapService.save(Owner.builder().build());
		assertNotNull(saved.getId());
		assertNotNull(saved);
	}

	@Test
	void findById() {
		assertEquals(ownerId, ownerMapService.findById(ownerId).getId());
	}

	@Test
	void findByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
	}

	@Test
	void findByInvalidLastName() {
		Owner owner = ownerMapService.findByLastName("Nobody");
		assertNull(owner);
	}
}
