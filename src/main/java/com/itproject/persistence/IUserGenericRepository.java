package com.itproject.persistence;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.itproject.domain.Student;
import com.itproject.domain.User;
import com.itproject.domain.enums.Sex;

@NoRepositoryBean
public interface IUserGenericRepository<T extends User> extends CrudRepository<T, UUID>{
	
	/* CrudRepository methods summary:
	 * <S extends T> S save(S entity);
	 * <S extends T> Iterable<S> saveAll(Iterable<S> entities);
	 * Optional<T> findById(UUID id);
	 * boolean existsById(UUID id);
	 * Iterable<T> findAll();
	 * Iterable<T> findAllById(Iterable<UUID> ids);
	 * long count();
	 * void deleteById(UUID id);
	 * void delete(T entity);
	 * void deleteAll(Iterable<? extends T> entities);
	 * void deleteAll();
	*/
	
	//public Student findByName_Surnames_Sex_StartDate(String name, String surnames, Sex sex, LocalDate startDate);
	
}
