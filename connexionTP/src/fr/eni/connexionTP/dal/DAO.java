package fr.eni.connexionTP.dal;

import java.util.List;

public interface DAO <T>{
	public void insert(T art) throws DALException;
	public T selectById(int id) throws DALException ;
	public List<T> selectAll() throws DALException ;
	public void update(T data) throws DALException ;
	public void delete(int id) throws DALException ;
	
}
