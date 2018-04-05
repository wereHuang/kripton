/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton58.array;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

// TODO: Auto-generated Javadoc
/**
 * The Interface DoubleDao.
 */
@BindDao(DoubleBean.class)
public interface DoubleDao {
	
	/**
	 * Select one.
	 *
	 * @return the double bean
	 */
	@BindSqlSelect()
	DoubleBean selectOne();	
	
	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param value2 the value 2
	 * @return the double bean
	 */
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	DoubleBean selectOne(double[] value, Double[] value2);

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param value2 the value 2
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	void selectOne(double[] value, Double[] value2, OnReadBeanListener<DoubleBean> listener);	

	/**
	 * Select one.
	 *
	 * @param value the value
	 * @param value2 the value 2
	 * @param listener the listener
	 */
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	void selectOne(double[] value, Double[] value2, OnReadCursorListener listener);
	
	/**
	 * Select list.
	 *
	 * @param value the value
	 * @param value2 the value 2
	 * @return the list
	 */
	@BindSqlSelect(where = "value=${value} and value2=${value2}")
	List<DoubleBean> selectList(double[] value, Double[] value2);
	
	/**
	 * Update one.
	 *
	 * @param id the id
	 * @param value the value
	 * @param value2 the value 2
	 * @return the long
	 */
	@BindSqlUpdate(where = "value=${value} and value2=${value2}")
	long updateOne(long id, double[] value, Double[] value2);
	
	/**
	 * Insert.
	 *
	 * @param id the id
	 * @param value the value
	 * @param value2 the value 2
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(long id, double[] value, Double[] value2);
	
	/**
	 * Insert.
	 *
	 * @param bean the bean
	 * @return the long
	 */
	@BindSqlInsert()
	long insert(DoubleBean bean);
	
	/**
	 * Delete.
	 *
	 * @param value the value
	 * @param value2 the value 2
	 * @return the long
	 */
	@BindSqlDelete(where = "value=${value} and value2=${value2}")
	long delete(double[] value, Double[] value2);
	
}
