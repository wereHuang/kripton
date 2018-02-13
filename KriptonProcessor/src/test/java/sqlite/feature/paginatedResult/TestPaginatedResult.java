/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.paginatedResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.paginatedResult.error1.Err1Person;
import sqlite.feature.paginatedResult.error1.Err1PersonDAO;
import sqlite.feature.paginatedResult.error1.Err1PersonDataSource;
import sqlite.feature.paginatedResult.error2.Err2Person;
import sqlite.feature.paginatedResult.error2.Err2PersonDAO;
import sqlite.feature.paginatedResult.error2.Err2PersonDataSource;
import sqlite.feature.paginatedResult.error3.Err3Person;
import sqlite.feature.paginatedResult.error3.Err3PersonDAO;
import sqlite.feature.paginatedResult.error3.Err3PersonDataSource;
import sqlite.feature.paginatedResult.error4.Err4Person;
import sqlite.feature.paginatedResult.error4.Err4PersonDAO;
import sqlite.feature.paginatedResult.error4.Err4PersonDataSource;
import sqlite.feature.paginatedResult.error5.Err5PersonDAO;
import sqlite.feature.paginatedResult.error5.Err5PersonDataSource;
import sqlite.feature.paginatedResult.model.Person;

@RunWith(JUnit4.class)
public class TestPaginatedResult extends AbstractBindSQLiteProcessorTest {

	@Test
	public void test1() throws Throwable {
		buildDataSourceProcessorTest(Person1DataSource.class, Dao1Person.class, Person.class);
	}
	
	@Test
	public void test2() throws Throwable {
		buildDataSourceProcessorTest(Person2DataSource.class, Dao2Person.class, Person.class);
	}

	
	@Test
	public void test3() throws Throwable {
		buildDataSourceProcessorTest(Person3DataSource.class, Dao3Person.class, Person.class);
	}

	
	@Test
	public void test4() throws Throwable {
		buildDataSourceProcessorTest(Person4DataSource.class, Dao4Person.class, Person.class);
	}


	/**
	 * pageSize is a String
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr1() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err1PersonDataSource.class, Err1PersonDAO.class, Err1Person.class);
	}

	/**
	 * Twice pageSize parameter
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr2() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err2PersonDataSource.class, Err2PersonDAO.class, Err2Person.class);
	}

	/**
	 * Both pageSize in annotation and as parameter
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr3() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err3PersonDataSource.class, Err3PersonDAO.class, Err3Person.class);
	}

	/**
	 * pageSize in annotation negative
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr4() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err4PersonDataSource.class, Err4PersonDAO.class, Err4Person.class);
	}

	/**
	 * pageSize in annotation negative
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testErr5() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Err5PersonDataSource.class, Err5PersonDAO.class, Person.class);
	}

}
