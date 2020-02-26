package searchouse.db;

import java.sql.SQLException;

import searchouse.bean.Bean;

public interface DAO {
	public void Insert(Bean newBean) throws SQLException;

	public void Delete(Bean beanToRemove) throws SQLException;

	public boolean Update(Bean beanToUpdate) throws SQLException;

	public Bean GetOne(Bean beanToGet) throws SQLException;
}
