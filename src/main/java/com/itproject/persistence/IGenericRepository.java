package com.itproject.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<T, ID extends Serializable> extends CrudRepository<T, ID>{
	
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
	
}
