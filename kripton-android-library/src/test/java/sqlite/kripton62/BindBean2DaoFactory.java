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
package sqlite.kripton62;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for Bean2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean2DataSource
 * @see BeanDao2
 * @see BeanDao2Impl
 * @see Bean2
 */
public interface BindBean2DaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao BeanDao2.
   *
   * @return the bean dao 2
   */
  BeanDao2Impl getBeanDao2();
}
