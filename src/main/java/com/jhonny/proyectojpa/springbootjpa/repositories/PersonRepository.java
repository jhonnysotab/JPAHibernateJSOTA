package com.jhonny.proyectojpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jhonny.proyectojpa.springbootjpa.dto.PersonDto;
import com.jhonny.proyectojpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person,Long> {

    @Query("select p from Person p where p.id in ?1")
    List<Person> getListWhere(List<Long> fila);

    @Query("select p from Person p where p.id not in ?1")
    List<Person> getNotListWhere(List<Long> fila);

    @Query("select p.name from Person p where p.id=:id")
    String getNameById(Long id);

    @Query("select concat(p.name,' ',p.lastname) as fullname from Person p where p.id=:id")
    String getFullNameById(Long id);

    List<Person> findByProgrammingLanguage(String programmingLanguage);
    @Query("select p from Person p where p.programmingLanguage =?1 and p.name =?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    //vacio o con parametrp igual realiza la consulta
    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();
    @Query("select p.name, p.programmingLanguage from Person p where p.name = ?1")
    List<Object[]> obtenerPersonData(String name);
    //
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);
    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);
    
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);
    Optional<Person> findByNameContaining(String name);

    Optional<Person> findByName(String name);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllClassPersons();

    @Query("select new com.jhonny.proyectojpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllClassPersonsDto();


    // DISTINC
    @Query("select distinct(p.lastname) from Person p")
     List<String> findAllName();

     @Query("select distinct(p.programmingLanguage) from Person p")
     List<String> findAllProgrammingLanguage();

     //CONCAT 
     @Query("select concat(p.name,' ',p.lastname) as fullname from Person p ")
     List<String> findAllFullNameConcat( );

     @Query("select p.name ||' '|| p.lastname as fullname from Person p ")
     List<String> findAllFullNameConcat2( );
    //UPPER y LOWER
     @Query("select upper(p.name ||' '|| p.lastname) as fullname from Person p ")
     List<String> findAllFullNameUpper( );
     @Query("select lower(concat(p.name,' ',p.lastname)) as fullname from Person p ")
     List<String> findAllFullNameLower( );
    

     // Between
     @Query("select p from Person p where p.id between 1 and 3")
     List<Person> findAllBetween();

     @Query("select p from Person p where p.name between ?1 and ?2")
     List<Person> findAllBetweenLetra(String caracter1, String caracter2);

     //OrderBy
    @Query("select p from Person p order by p.name asc")
     List<Person>getAllOrder();

     List<Person>   findAllByOrderByNameDesc();
    
     List<Person>   findAllByOrderByNameAsc();

     //count max y min

     @Query("select count(p) from Person p")     
     Long totalPerson();

     @Query("select min(p.id) from Person p")     
     Long minId();
     
     @Query("select max(p.id) from Person p")     
     Long maxId();

     //lengh 

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select  min(length(p.name)) from Person p")
    public Integer getMinLeghtName();

    
    @Query("select  max(length(p.name)) from Person p")
    public Integer getMaxLeghtName();
    
    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    // sub consulta sub queryes
    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getMinLeghtNameandName();

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select max(length(p.name)) from Person p)")
    public List<Object[]> getMaxLeghtNameandName();
    }
