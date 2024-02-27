package com.jhonny.proyectojpa.springbootjpa;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jhonny.proyectojpa.springbootjpa.dto.PersonDto;
import com.jhonny.proyectojpa.springbootjpa.entities.Person;
import com.jhonny.proyectojpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner{
	@Autowired
	private PersonRepository pRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		update();
	}
	@Transactional(readOnly = true)
	public	void	whereIn(){
 
		List<Person> listPersons = pRepository.getListWhere(Arrays.asList(1L,2L,3L,4L));

		listPersons.forEach(System.out::print);
	}
	


	@Transactional(readOnly = true)
	public	void	subQuery(){
		System.out.println("================================= consulta por el nombre mas corto y la longitus===========================");
		List<Object[]> regs = pRepository.getMinLeghtNameandName();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println( "Name:	"+name+"	length:	"+length); 
		});
		System.out.println("================================= consulta por el nombre mas largo y la longitus===========================");
		List<Object[]> regsmax = pRepository.getMaxLeghtNameandName();
		regsmax.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println( "Name:	"+name+"	length:	"+length); 
		});

	}

	@Transactional(readOnly = true)
	public	void	queriesFunctionAggretation(){
		Long min = pRepository.minId();
		System.out.println( " minimo :" + min);

		Long max = pRepository.maxId();
		System.out.println( " maximo :" + max);

		Long count = pRepository.totalPerson();
		System.out.println( " Total :" + count);

		List<Object[]> regs = pRepository.getPersonNameLength();

		regs.forEach(p->{

			String name = (String) p[0];
			int leght = (int) p[1];
			
			System.out.println(" el nombre es :"+ name + " la longitud es :"+ leght);

		});

		
		int minLeghtName = pRepository.getMinLeghtName();
		System.out.println( " El nombre con menor caracter :" + minLeghtName);

		int maxLeghtName = pRepository.getMaxLeghtName();
		System.out.println( " El nombre con mayor caracter :" + maxLeghtName);

		
		System.out.println( " ==========Consulta  resumen de funciones de agregacion min , max sum, avg, count ============");
		Object[]	resumenRegObjects	=	(Object[])	pRepository.getResumeAggregationFunction();

		System.out.println("	minimo:	"+ resumenRegObjects[0]+ "	max:	"+resumenRegObjects[1]+"	sum:	"+resumenRegObjects[2]+"	avg:	"+resumenRegObjects[3]+"	count:	"+resumenRegObjects[4]);
	}


	@Transactional(readOnly = true)
	public	void	personalizedQueriesOrderBy(){
		List<Person> fullName = pRepository.findAllByOrderByNameDesc();
		fullName.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public	void	personalizedQueriesBetweendCaracter(){
		System.out.println("============================Consultas del nombre Betweend============== ");
		Scanner scanner = new Scanner(System.in);
 
		System.out.println("===================Consulta del  nombre por Id=============");
		System.out.println("Ingrese el cartacter 1");
		String	caracter1	=	scanner.next();	
		System.out.println("Ingrese el cartacter 2");
		String	caracter2	=	scanner.next();	

		List<Person> personlist = pRepository.findAllBetweenLetra(caracter1,caracter2);

		personlist.forEach(System.out::print);
		scanner.close();
	}
  
	@Transactional(readOnly = true)
	public	void	personalizedQueriesBetweend(){
		System.out.println("============================Consultas del nombre Betweend============== ");
		List<Person> fullName = pRepository.findAllBetween();
		fullName.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public	void	personalizedQueriesUpperCaseLower(){

		System.out.println("============================Consultas del nombre concatenado y con UpperCase Mayusculas============== ");
		List<String> fullName = pRepository.findAllFullNameUpper();
		fullName.forEach(System.out::println);
	
		
		System.out.println("============================Consultas del nombre concatenado y con LowerCase Minusculas============== ");
		List<String> fullName2 = pRepository.findAllFullNameLower();
		fullName2.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public	void	personalizedQueriesConCat(){

		System.out.println("============================Consultas del nombre concatenado============== ");
		List<String> fullName = pRepository.findAllFullNameConcat2();
		fullName.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public	void	personalizedQueriesDistinc(){
		System.out.println("Consultas con nombres de personas");
		List<String> name = pRepository.findAllName();
		name.forEach(System.out::println);


		System.out.println("Consultas el lenguaje de programacion ");
		List<String> programminglanguage = pRepository.findAllProgrammingLanguage();
		programminglanguage.forEach(System.out::println);
		

	}



	@Transactional(readOnly = true)
	public	void	personalizedQueries2(){
		 
		System.out.println("===================Consulta por el objeto person y lenguaje de programacion=============");
		 List<Object[]> personRegs = pRepository.findAllMixPersonDataList();
		 personRegs.forEach(reg->{
			 
		 	System.out.println("programmingLanguage "+reg[1] + " person = "+reg[0] );
		 });

		 System.out.println("===================Consulta que puebla y devulve el objeto entity de un instancia personalizada=============");
		 List<Person> persons = pRepository.findAllClassPersons();
		 
		 persons.forEach(System.out::println);
		 
		 System.out.println("===================Consulta que puebla y devuelve el objeto DTO de un DTO personalizada=============");
		 List<PersonDto> person = pRepository.findAllClassPersonsDto();
		 
		 person.forEach(System.out::println);
		 

	}

	@Transactional(readOnly = true)
	public	void	personalizedQueries(){
		Scanner scanner = new Scanner(System.in);


		
		System.out.println("===================Consulta del  nombre por Id=============");
		System.out.println("Ingrese el Id de la persona");
		Long	id	=	scanner.nextLong();	

		String name = pRepository.getNameById(id);
		System.out.println("el nombre de la persona es "+ name);


		
		System.out.println("===================Consulta del  nombre por Id=============");
	 	String fullname = pRepository.getFullNameById(id);
		System.out.println("el nombre de la persona es "+ fullname);
		scanner.close();
	}


	@Transactional
	public	void delete2(){
		pRepository.findAll().forEach(System.out::println);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona");
		Long	id	=	scanner.nextLong();	

		Optional<Person> personOptional = pRepository.findById(id);
		personOptional.ifPresentOrElse(pRepository::delete,	()->	System.out.println("No se encontro al usuario"));
		 
		pRepository.findAll().forEach(System.out::println);
		scanner.close();
	}


	@Transactional
	public	void delete(){
		pRepository.findAll().forEach(System.out::println);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona");
		Long	id	=	scanner.nextLong();	
		pRepository.deleteById(id);	
		 
		pRepository.findAll().forEach(System.out::println);
		scanner.close();
	}


	@Transactional
	public	void	update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona");
		Long	id	=	scanner.nextLong();
		Optional<Person> optionaPerson=	pRepository.findById(id);

		// Actualizar 
		optionaPerson.ifPresent(p->{
				System.out.println(p);
				System.out.println("Ingrese el Lenguaje de programacion:");
				String programmingLanguage = scanner.next();
				p.setProgrammingLanguage(programmingLanguage);
				Person personDb = pRepository.save(p);
				System.out.println(personDb);
		});
		scanner.close();
	}

	@Transactional
	public void create(){

		// Person person = new Person(null, "Antonio","Bautista","Angular");
		// Person personNew = pRepository.save(person);
		// System.out.println(personNew);
		//mejora de codigo 
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingrese el nombre");
		String name = scanner.next();
		
		System.out.println("Ingrese el apellido");
		String lastname = scanner.next();
		
		System.out.println("Ingrese el lenguaje");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name,lastname,programmingLanguage);

		 Person person2 = pRepository.save(person);
		 System.out.println(person2);

		 pRepository.findById(person2.getId().longValue()).ifPresent( System.out::println);
	}
	@Transactional(readOnly = true)
	public void	findOne(){
		// Person	person=	null ; 
		// Optional<Person> optionPerson= pRepository.findById(1L);
		// if(optionPerson.isPresent()){
		// 	person = optionPerson.get();
		// }
		// // pRepository.findById(1L).orElseThrow();
		// System.out.println(person);

		///Simplificado
		// pRepository.findById(1L).ifPresent(System.out::println);
		// pRepository.findOne(1L).ifPresent(System.out::println);
		 pRepository.findByNameContaining("jho").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list(){
		// List<Person> persons = (List<Person>) pRepository.findAll();
		// List<Person> persons = (List<Person>) pRepository.buscarByProgrammingLanguage("JAVA","Jhonny");
		List<Person> persons = (List<Person>) pRepository.findByProgrammingLanguageAndName("JAVA","Jhonny");
		persons.stream().forEach(person->{
			System.err.println(person);
		});

		List<Object[]> personsValues= pRepository.obtenerPersonData("Bruno");
		personsValues.stream().forEach(person->{
			System.err.println(person[0]+" es experto en: "+ person[1]);
			System.err.println(person);
		});
	}

}
