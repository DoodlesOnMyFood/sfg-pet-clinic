package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;


	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}



	@Override
	public void run(String... args) throws Exception {
		if(petTypeService.findAll().size() == 0) {
			loadData();
		}
	}

	private void loadData(){
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);

		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");

		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");

		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedRadiology = specialtyService.save(radiology);
		Specialty savedSurgery = specialtyService.save(surgery);
		Specialty savedDentisty = specialtyService.save(dentistry);

		Owner owner1 = new Owner();
		owner1.setFirstName("Bob");
		owner1.setLastName("Black");
		owner1.setAddress("123 Moon Drive");
		owner1.setCity("Hong Kong");
		owner1.setTelephone("123456789");
		Pet mikesPet = new Pet();
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);

		System.out.println(savedCatPetType.getId());
		System.out.println(cat.getId());
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Jill");
		owner2.setLastName("White");
		owner2.setAddress("123 Moon Drive");
		owner2.setCity("Hong Kong");
		owner2.setTelephone("123456789");
		Pet jillsPet = new Pet();
		jillsPet.setPetType(savedCatPetType);
		jillsPet.setName("Jingle");
		jillsPet.setBirthDate(LocalDate.now());
		jillsPet.setOwner(owner2);
		owner2.getPets().add(jillsPet);
		ownerService.save(owner2);
		Visit catVisit = new Visit();
		catVisit.setPet(jillsPet);
		catVisit.setDescription("Sneezy");
		catVisit.setDate(LocalDate.now());
		visitService.save(catVisit);
		System.out.println("Loaded Owners....");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Peters");
		vet1.getSpecialties().add(savedRadiology);
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Vet");
		vet2.setLastName("2");
		vet2.getSpecialties().add(savedSurgery);
		vetService.save(vet2);
		System.out.println("Loaded Vets....");

		System.out.println(vetService.findAll());
	}



}


