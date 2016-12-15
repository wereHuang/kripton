package sqlite.kripton58.list;

import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.AbstractJacksonContext;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Short;
import java.lang.String;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Entity <code>ShortBean</code> is associated to table <code>short_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see ShortBean
 */
public class ShortBeanTable {
  /**
   * Costant represents name of table short_bean
   */
  public static final String TABLE_NAME = "short_bean";

  /**
   * <p>
   * DDL to create table short_bean
   * </p>
   *
   * <pre>CREATE TABLE short_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE short_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table short_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS short_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS short_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see ShortBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see ShortBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see ShortBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";

  /**
   * write
   */
  public static byte[] serializeValue(List<Short> value) {
    if (value==null) {
      return null;
    }
    AbstractJacksonContext context=KriptonBinder.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Short item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
   * parse
   */
  public static List<Short> parseValue(byte[] input) {
    if (input==null) {
      return null;
    }
    AbstractJacksonContext context=KriptonBinder.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Short> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Short> collection=new ArrayList<>();
        Short item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getShortValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeValue2(LinkedList<Short> value) {
    if (value==null) {
      return null;
    }
    AbstractJacksonContext context=KriptonBinder.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Short item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
   * parse
   */
  public static LinkedList<Short> parseValue2(byte[] input) {
    if (input==null) {
      return null;
    }
    AbstractJacksonContext context=KriptonBinder.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Short> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Short> collection=new LinkedList<>();
        Short item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getShortValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
