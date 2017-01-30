package sqlite.indexes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.String;
import java.lang.Throwable;

/**
 * <p>
 * Represents implementation of datasource Err2PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err2PersonDataSource
 * @see BindErr2PersonDaoFactory
 * @see Err2PersonDAO
 * @see Err2PersonDAOImpl
 * @see Err2Person
 */
public class BindErr2PersonDataSource extends AbstractDataSource implements BindErr2PersonDaoFactory, Err2PersonDataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindErr2PersonDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "person.db";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected Err2PersonDAOImpl err2PersonDAO = new Err2PersonDAOImpl(this);

  protected BindErr2PersonDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public Err2PersonDAOImpl getErr2PersonDAO() {
    return err2PersonDAO;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=open();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (transaction!=null) transaction.onError(e);
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindErr2PersonDataSource instance() {
    if (instance==null) {
      instance=new BindErr2PersonDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Err2PersonTable.CREATE_TABLE_SQL);
    database.execSQL(Err2PersonTable.CREATE_TABLE_SQL);
    if (databaseListener != null) {
      databaseListener.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    if (databaseListener != null) {
      databaseListener.onUpdate(database, oldVersion, newVersion, true);
    } else {
      // drop tables
      Logger.info("DDL: %s",Err2PersonTable.DROP_TABLE_SQL);
      database.execSQL(Err2PersonTable.DROP_TABLE_SQL);

      // generate tables
      Logger.info("DDL: %s",Err2PersonTable.CREATE_TABLE_SQL);
      database.execSQL(Err2PersonTable.CREATE_TABLE_SQL);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (databaseListener != null) {
      databaseListener.onConfigure(database);
    }
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindErr2PersonDaoFactory> {
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}