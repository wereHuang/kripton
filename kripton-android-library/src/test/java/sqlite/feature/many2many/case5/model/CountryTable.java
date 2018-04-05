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
package sqlite.feature.many2many.case5.model;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Country</code> is associated to table <code>country</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Country
 */
public class CountryTable implements SQLiteTable {
  
  /** Costant represents typeName of table country. */
  public static final String TABLE_NAME = "country";

  /** <p> DDL to create table country </p>  <pre>CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, code TEXT UNIQUE NOT NULL, calling_code TEXT NOT NULL, region TEXT, name TEXT NOT NULL, translated_name BLOB);</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, code TEXT UNIQUE NOT NULL, calling_code TEXT NOT NULL, region TEXT, name TEXT NOT NULL, translated_name BLOB);";

  /** <p> DDL to drop table country </p>  <pre>DROP TABLE IF EXISTS country;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS country;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Country#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>area</code> is associated to table column <code>area</code>. This costant represents column name.
   *
   *  @see Country#area
   */
  public static final String COLUMN_AREA = "area";

  /**
   * Entity's property <code>code</code> is associated to table column <code>code</code>. This costant represents column name.
   *
   *  @see Country#code
   */
  public static final String COLUMN_CODE = "code";

  /**
   * Entity's property <code>callingCode</code> is associated to table column <code>calling_code</code>. This costant represents column name.
   *
   *  @see Country#callingCode
   */
  public static final String COLUMN_CALLING_CODE = "calling_code";

  /**
   * Entity's property <code>region</code> is associated to table column <code>region</code>. This costant represents column name.
   *
   *  @see Country#region
   */
  public static final String COLUMN_REGION = "region";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Country#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>translatedName</code> is associated to table column <code>translated_name</code>. This costant represents column name.
   *
   *  @see Country#translatedName
   */
  public static final String COLUMN_TRANSLATED_NAME = "translated_name";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_AREA, COLUMN_CODE, COLUMN_CALLING_CODE, COLUMN_REGION, COLUMN_NAME, COLUMN_TRANSLATED_NAME};

  /**
   * for attribute translatedName serialization.
   *
   * @param value the value
   * @return the byte[]
   */
  public static byte[] serializeTranslatedName(Map<Translation, String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Translation, String> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeStringField("value", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute translatedName parsing.
   *
   * @param input the input
   * @return the map
   */
  public static Map<Translation, String> parseTranslatedName(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Map<Translation, String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<Translation, String> collection=new HashMap<>();
        Translation key=null;
        String value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
           {
            String tempEnum=jacksonParser.getText();
            key=StringUtils.hasText(tempEnum)?Translation.valueOf(tempEnum):null;
          }
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getText();
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * Columns array.
   *
   * @return the string[]
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name.
   *
   * @return the string
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
