package sqlite.stack45184504;

import java.lang.String;

/**
 * <p>
 * Entity <code>FileBean</code> is associated to table <code>files</code>
 * This class represents table associated to entity.
 * </p>
 *  @see FileBean
 */
public class FileBeanTable {
  /**
   * Costant represents typeName of table files
   */
  public static final String TABLE_NAME = "files";

  /**
   * <p>
   * DDL to create table files
   * </p>
   *
   * <pre>CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, content BLOB, content_type TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, content BLOB, content_type TEXT);";

  /**
   * <p>
   * DDL to drop table files
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS files;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS files;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see FileBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see FileBean#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column name.
   *
   *  @see FileBean#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>contentType</code> is associated to table column <code>content_type</code>. This costant represents column name.
   *
   *  @see FileBean#contentType
   */
  public static final String COLUMN_CONTENT_TYPE = "content_type";

  /**
   * for attribute content serialization
   */
  public static byte[] serializeContent(byte[] value) {
    return value;
  }

  /**
   * for attribute content parsing
   */
  public static byte[] parseContent(byte[] input) {
    return input;
  }
}