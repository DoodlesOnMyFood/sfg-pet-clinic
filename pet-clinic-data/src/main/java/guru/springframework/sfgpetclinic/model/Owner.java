package guru.springframework.sfgpetclinic.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "owners")
public class Owner extends Person{

	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "telephone")
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();

	public Pet getPet(String name){
		return getPet(name, false);
	}

	public Pet getPet(String name, boolean ignoreNew){
		name = name.toLowerCase();
		for(Pet pet : pets){
			if(!ignoreNew || !pet.isNew()){
				String comp = pet.getName().toLowerCase();
				if(comp.equals(name)) return pet;
			}
		}
		return null;
	}

}
