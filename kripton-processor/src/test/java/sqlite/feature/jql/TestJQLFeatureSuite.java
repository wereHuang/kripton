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
package sqlite.feature.jql;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.feature.jql.err1.TestCompileErr1;
import sqlite.feature.jql.err2.TestCompileErr2;
import sqlite.feature.jql.kripton163.TestCompileKripton163;
import sqlite.feature.jql.kripton164.TestCompileKripton164;

/**
 * The Class TestJQLFeatureSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestJQL.class,
		TestCompileKripton163.class,
		TestCompileKripton164.class,
		TestCompileErr1.class,
		TestCompileErr2.class
		 })
//@formatter:on
public class TestJQLFeatureSuite extends BaseProcessorTest {

}
