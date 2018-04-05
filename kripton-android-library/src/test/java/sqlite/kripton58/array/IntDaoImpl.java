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

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>IntBean</code>, based on interface <code>IntDao</code>
 * </p>.
 *
 * @see IntBean
 * @see IntDao
 * @see IntBeanTable
 */
public class IntDaoImpl extends Dao implements IntDao {
  
  /** The Constant SELECT_ONE_SQL1. */
  private static final String SELECT_ONE_SQL1 = "SELECT id, value, value2 FROM int_bean";

  /** The Constant SELECT_ONE_SQL2. */
  private static final String SELECT_ONE_SQL2 = "SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?";

  /** The Constant SELECT_ONE_SQL3. */
  private static final String SELECT_ONE_SQL3 = "SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?";

  /** The Constant SELECT_ONE_SQL4. */
  private static final String SELECT_ONE_SQL4 = "SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?";

  /** The Constant SELECT_LIST_SQL5. */
  private static final String SELECT_LIST_SQL5 = "SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?";

  /** The update one prepared statement 0. */
  private static SQLiteStatement updateOnePreparedStatement0;

  /** The insert prepared statement 1. */
  private static SQLiteStatement insertPreparedStatement1;

  /** The insert prepared statement 2. */
  private static SQLiteStatement insertPreparedStatement2;

  /** The delete prepared statement 3. */
  private static SQLiteStatement deletePreparedStatement3;

  /**
   * Instantiates a new int dao impl.
   *
   * @param context the context
   */
  public IntDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, value, value2 FROM int_bean</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>.
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntBean selectOne() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      IntBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        resultBean=new IntBean();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=IntBeanTable.parseValue(_cursor.getBlob(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.value2=IntBeanTable.parseValue2(_cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param value 	is binded to <code>${value}</code>
   * @param value2 	is binded to <code>${value2}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntBean selectOne(int[] value, Integer[] value2) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      IntBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        resultBean=new IntBean();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=IntBeanTable.parseValue(_cursor.getBlob(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.value2=IntBeanTable.parseValue2(_cursor.getBlob(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param value 	is binded to <code>${value}</code>
   * @param value2 	is binded to <code>${value2}</code>
   * @param listener 	is the IntBean listener
   */
  @Override
  public void selectOne(int[] value, Integer[] value2, OnReadBeanListener<IntBean> listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL3;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      IntBean resultBean=new IntBean();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=IntBeanTable.parseValue(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.value2=IntBeanTable.parseValue2(_cursor.getBlob(index2)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param value 	is binded to <code>${value}</code>
   * @param value2 	is binded to <code>${value2}</code>
   * @param listener 	is the cursor listener
   */
  @Override
  public void selectOne(int[] value, Integer[] value2, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param value 	is binded to <code>${value}</code>
   * @param value2 	is binded to <code>${value2}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<IntBean> selectList(int[] value, Integer[] value2) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<IntBean> resultList=new ArrayList<IntBean>(_cursor.getCount());
      IntBean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value2");

        do
         {
          resultBean=new IntBean();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=IntBeanTable.parseValue(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.value2=IntBeanTable.parseValue2(_cursor.getBlob(index2)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE int_bean SET id=:id WHERE value=${value} and value2=${value2}</pre>
   * 
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param id 	is used as updated field <strong>id</strong>
   * @param value 	is used as where parameter <strong>${value}</strong>
   * @param value2 	is used as where parameter <strong>${value2}</strong>
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, int[] value, Integer[] value2) {
    if (updateOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE int_bean SET id=? WHERE value=? and value2=?";
      updateOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement0);
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE int_bean SET id=:id WHERE value=? and value2=?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO int_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   * 
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is binded to query's parameter <strong>${value2}</strong> and method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param id 	is binded to column value <strong>id</strong>
   * @param value 	is binded to column value <strong>value</strong>
   * @param value2 	is binded to column value <strong>value2</strong>
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, int[] value, Integer[] value2) {
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO int_bean (id, value, value2) VALUES (?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);

    _contentValues.put("id", id);
    _contentValues.put("value", serializer1(value));
    _contentValues.put("value2", serializer2(value2));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO int_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO int_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(IntBean bean) {
    if (insertPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO int_bean (value, value2) VALUES (?, ?)";
      insertPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement2);
    _contentValues.put("value", IntBeanTable.serializeValue(bean.value));
    _contentValues.put("value2", IntBeanTable.serializeValue2(bean.value2));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO int_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement2, _contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   * 
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>.
   *
   * @param value 	is used as where parameter <strong>${value}</strong>
   * @param value2 	is used as where parameter <strong>${value2}</strong>
   * @return number of deleted records
   */
  @Override
  public long delete(int[] value, Integer[] value2) {
    if (deletePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM int_bean WHERE value=? and value2=?";
      deletePreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement3);
    _contentValues.addWhereArgs((value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM int_bean WHERE value=? and value2=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deletePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * for param serializer1 serialization.
   *
   * @param value the value
   * @return the byte[]
   */
  private byte[] serializer1(int[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        int item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing.
   *
   * @param input the input
   * @return the int[]
   */
  private int[] parser1(byte[] input) {
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
      int[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Integer> collection=new ArrayList<>();
        Integer item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getIntValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asIntegerTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer2 serialization.
   *
   * @param value the value
   * @return the byte[]
   */
  private byte[] serializer2(Integer[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Integer item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser2 parsing.
   *
   * @param input the input
   * @return the integer[]
   */
  private Integer[] parser2(byte[] input) {
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
      Integer[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Integer> collection=new ArrayList<>();
        Integer item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getIntValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asIntegerArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (updateOnePreparedStatement0!=null) {
      updateOnePreparedStatement0.close();
      updateOnePreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
    if (insertPreparedStatement2!=null) {
      insertPreparedStatement2.close();
      insertPreparedStatement2=null;
    }
    if (deletePreparedStatement3!=null) {
      deletePreparedStatement3.close();
      deletePreparedStatement3=null;
    }
  }
}
