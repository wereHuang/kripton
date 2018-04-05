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
package sqlite.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for IntDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntDataSource
 * @see IntDao
 * @see IntDaoImpl
 * @see IntBean
 */
public interface BindIntDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao IntDao.
   *
   * @return the int dao
   */
  IntDaoImpl getIntDao();
}
