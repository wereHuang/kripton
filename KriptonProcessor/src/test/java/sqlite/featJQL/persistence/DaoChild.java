package sqlite.featJQL.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.featJQL.entities.Child;

@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

	@BindSqlSelect(jql = "select * from Child where parentId in (select id from Person where id=1)")
	public List<Child> selectByParent();

	// @BindSqlSelect(where = "id=${id}")
	// public List<Child> selectAll(long id);

}