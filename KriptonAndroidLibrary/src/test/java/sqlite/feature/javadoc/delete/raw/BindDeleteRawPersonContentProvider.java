package sqlite.feature.javadoc.delete.raw;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;

/**
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * </table>
 *
 * <h2>Supported delete operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td>content://sqlite.feature.javadoc.bean/persons/${id}</td><td>{@link DeleteRawPersonDaoImpl#deleteOneBean0}</td></tr>
 * <tr><td>content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore</td><td>{@link DeleteRawPersonDaoImpl#deleteBeanDynamicWithArgs4}</td></tr>
 * <tr><td>content://sqlite.feature.javadoc.bean/persons/${surname}/${name}</td><td>{@link DeleteRawPersonDaoImpl#deleteFromSelectAllBeansJQL1}</td></tr>
 * <tr><td>content://sqlite.feature.javadoc.bean/persons/single/${id}</td><td>{@link DeleteRawPersonDaoImpl#deleteRaw2}</td></tr>
 * <tr><td>content://sqlite.feature.javadoc.bean/persons/single2/${id}</td><td>{@link DeleteRawPersonDaoImpl#deleteRawDynamic3}</td></tr>
 * </table>
 */
public class BindDeleteRawPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI. Example:</p>
   * <pre>content://sqlite.contentprovider.kripton35</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /**
   * <p>datasource singleton</p>
   */
  private static BindDeleteRawPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  public static final String PATH_PERSON_1 = "persons/#";

  public static final String PATH_PERSON_2 = "persons/#/moreAndMore";

  public static final String PATH_PERSON_3 = "persons/*/*";

  public static final String PATH_PERSON_4 = "persons/single/#";

  public static final String PATH_PERSON_5 = "persons/single2/#";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  static final int PATH_PERSON_4_INDEX = 4;

  static final int PATH_PERSON_5_INDEX = 5;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    dataSource = BindDeleteRawPersonDataSource.instance();
    dataSource.openWritableDatabase();
    return true;
  }

  /**
   * <p>Close database.</p>
   *
   * @see android.content.ContentProvider#shutdown()
   */
  @Override
  public void shutdown() {
    super.shutdown();
    dataSource.close();
  }

  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
  }

  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
  }

  /**
   * method DeleteRawPersonDao.deleteOneBean
   * method DeleteRawPersonDao.deleteFromSelectAllBeansJQL
   * method DeleteRawPersonDao.deleteRaw
   * method DeleteRawPersonDao.deleteRawDynamic
   * method DeleteRawPersonDao.deleteBeanDynamicWithArgs
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int returnRowDeleted=-1;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteOneBean0(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${id}/moreAndMore
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteBeanDynamicWithArgs4(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${surname}/${name}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteFromSelectAllBeansJQL1(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/single/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteRaw2(uri, selection, selectionArgs);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/single2/${id}
        returnRowDeleted=dataSource.getDeleteRawPersonDao().deleteRawDynamic3(uri, selection, selectionArgs);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
      }
    }
    Logger.info("Changes are notified for URI %s", uri);
    getContext().getContentResolver().notifyChange(uri, null);
    return returnRowDeleted;
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}