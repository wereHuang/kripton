/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package shared.feature.typeadapter;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

import shared.AbstractBindSharedPreferenceProcessorTest;
import shared.feature.typeadapter.case4.Set2ListTypeAdapter;
import shared.feature.typeadapter.case4.App4Preferences;
import shared.feature.typeadapter.case4.App4WithErrPreferences;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPreferenceTypeAdapter4Compile.
 */
@RunWith(JUnit4.class)
public class TestPreferenceTypeAdapter4Compile extends AbstractBindSharedPreferenceProcessorTest {

	/**
	 * Test compile with error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompileWithError() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonProcessorException.class);
		buildSharedPreferencesProcessorTest(App4WithErrPreferences.class, Set2ListTypeAdapter.class);
	}
	
	/**
	 * Test compile.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(App4Preferences.class, Set2ListTypeAdapter.class);
	}

}
