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
package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.internals.MigrationSQLChecker;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.database.sqlite.SQLiteDatabase;

// TODO: Auto-generated Javadoc
/**
 * The Class SQLiteUpdateTestHelper.
 */
public class SQLiteUpdateTestHelper {
	
	/**
	 * Read SQL from file.
	 *
	 * @param sqlDefinitionFile the sql definition file
	 * @return the list
	 */
	public static List<String> readSQLFromFile(String sqlDefinitionFile) {
		try {
			final List<String> executionList = new ArrayList<>();
			File f = new File(sqlDefinitionFile);
			final String ddl = IOUtils.toString(new FileInputStream(f), Charset.forName("utf-8"));

			MigrationSQLChecker.getInstance().analyze(ddl, new JqlBaseListener() {
				public void enterSelect_stmt(Select_stmtContext ctx) {
				};

				public void enterSql_stmt(Sql_stmtContext ctx) {
					;
					int start = ctx.getStart().getStartIndex();
					int stop = ctx.getStop().getStopIndex() + 1;

					if (start == stop)
						return;

					String statement = ddl.substring(start, stop).trim();

					executionList.add(statement);
				};
			});
			return executionList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Execute SQL from file.
	 *
	 * @param database the database
	 * @param sqlDefinitionFile the sql definition file
	 */
	public static void executeSQLFromFile(SQLiteDatabase database, String sqlDefinitionFile) {
		List<String> executionList = readSQLFromFile(sqlDefinitionFile);
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
		}

	}

	/**
	 * Reset instance.
	 *
	 * @param classDataSource the class data source
	 */
	public static void resetInstance(Class<? extends AbstractDataSource> classDataSource) {
		Field threadLocalField;
		try {
			threadLocalField = classDataSource.getDeclaredField("instance");
			threadLocalField.setAccessible(true);

			threadLocalField.set(null, null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {		
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}

	}

}
