package com.runsystem.datnt.daos;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {

	public PK      add(T entity);
	public void    update(T entity);
	public void    remove(T entity);
	public T       getByKey(PK id);
	public List<T> list();
}
