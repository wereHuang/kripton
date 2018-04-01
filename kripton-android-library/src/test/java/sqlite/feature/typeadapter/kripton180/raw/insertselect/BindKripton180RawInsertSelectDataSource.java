package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.subjects.PublishSubject;
import java.util.List;
import sqlite.feature.typeadapter.kripton180.EmployeeTable;

/**
 * <p>
 * Represents implementation of datasource Kripton180RawInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180RawInsertSelectDataSource
 * @see BindKripton180RawInsertSelectDaoFactory
 * @see EmployeeRawInsertSelectDao
 * @see EmployeeRawInsertSelectDaoImpl
 * @see Employee
 */
public class BindKripton180RawInsertSelectDataSource extends AbstractDataSource implements BindKripton180RawInsertSelectDaoFactory, Kripton180RawInsertSelectDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static volatile BindKripton180RawInsertSelectDataSource instance;

  /**
   * <p>Mutex to manage multithread access to instance</p>
   */
  private static final Object mutex = new Object();

  /**
   * Unique identifier for Dao EmployeeRawInsertSelectDao
   */
  public static final int EMPLOYEE_RAW_INSERT_SELECT_DAO_UID = 0;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new EmployeeTable()};

  /**
   * <p>dao instance</p>
   */
  protected EmployeeRawInsertSelectDaoImpl employeeRawInsertSelectDao = new EmployeeRawInsertSelectDaoImpl(context);

  protected Scheduler globalSubscribeOn;

  protected Scheduler globalObserveOn;

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindKripton180RawInsertSelectDataSource(DataSourceOptions options) {
    super("kripton180.db", 1, options);
  }

  @Override
  public EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao() {
    return employeeRawInsertSelectDao;
  }

  public BindKripton180RawInsertSelectDataSource globalSubscribeOn(Scheduler scheduler) {
    this.globalSubscribeOn=scheduler;
    return this;
  }

  public BindKripton180RawInsertSelectDataSource globalObserveOn(Scheduler scheduler) {
    this.globalObserveOn=scheduler;
    return this;
  }

  public <T> Observable<T> execute(final ObservableTransaction<T> transaction) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode();
        boolean success=false;
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
        currentDaoFactory.onSessionOpened();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(currentDaoFactory, emitter)) {
            connection.setTransactionSuccessful();
            success=true;
          }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
          currentDaoFactory.onSessionClear();
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
          if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> execute(final SingleTransaction<T> transaction) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode();
        boolean success=false;
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
        currentDaoFactory.onSessionOpened();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(currentDaoFactory, emitter)) {
            connection.setTransactionSuccessful();
            success=true;
          }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
          currentDaoFactory.onSessionClear();
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
          if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> execute(final FlowableTransaction<T> transaction) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode();
        boolean success=false;
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
        currentDaoFactory.onSessionOpened();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(currentDaoFactory, emitter)) {
            connection.setTransactionSuccessful();
            success=true;
          }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
          currentDaoFactory.onSessionClear();
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
          if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> execute(final MaybeTransaction<T> transaction) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        boolean needToOpened=!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode();
        boolean success=false;
        @SuppressWarnings("resource")
        SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
        DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
        currentDaoFactory.onSessionOpened();
        try {
          connection.beginTransaction();
          if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(currentDaoFactory, emitter)) {
            connection.setTransactionSuccessful();
            success=true;
          }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
          currentDaoFactory.onSessionClear();
        } finally {
          try {
            connection.endTransaction();
          } catch(Throwable e) {
          }
          if (needToOpened) { close(); }
          if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final ObservableBatch<T> batch, final boolean writeMode) {
    ObservableOnSubscribe<T> emitter=new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180RawInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
        currentDaoFactory.onSessionOpened();
        try {
          if (batch != null) { batch.onExecute(currentDaoFactory, emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
          currentDaoFactory.onSessionClosed();
        }
        return;
      }
    };
    Observable<T> result=Observable.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Observable<T> executeBatch(final ObservableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Single<T> executeBatch(final SingleBatch<T> batch, final boolean writeMode) {
    SingleOnSubscribe<T> emitter=new SingleOnSubscribe<T>() {
      @Override
      public void subscribe(SingleEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180RawInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
        currentDaoFactory.onSessionOpened();
        try {
          if (batch != null) { batch.onExecute(currentDaoFactory, emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
          currentDaoFactory.onSessionClosed();
        }
        return;
      }
    };
    Single<T> result=Single.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Single<T> executeBatch(final SingleBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Flowable<T> executeBatch(final FlowableBatch<T> batch, final boolean writeMode) {
    FlowableOnSubscribe<T> emitter=new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180RawInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
        currentDaoFactory.onSessionOpened();
        try {
          if (batch != null) { batch.onExecute(currentDaoFactory, emitter); }
          emitter.onComplete();
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
          currentDaoFactory.onSessionClosed();
        }
        return;
      }
    };
    Flowable<T> result=Flowable.create(emitter, BackpressureStrategy.BUFFER);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Flowable<T> executeBatch(final FlowableBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public <T> Maybe<T> executeBatch(final MaybeBatch<T> batch, final boolean writeMode) {
    MaybeOnSubscribe<T> emitter=new MaybeOnSubscribe<T>() {
      @Override
      public void subscribe(MaybeEmitter<T> emitter) {
        boolean needToOpened=writeMode?!BindKripton180RawInsertSelectDataSource.this.isOpenInWriteMode(): !BindKripton180RawInsertSelectDataSource.this.isOpen();
        if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
        DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
        currentDaoFactory.onSessionOpened();
        try {
          if (batch != null) { batch.onExecute(currentDaoFactory, emitter); }
          // no onComplete;
        } catch(Throwable e) {
          Logger.error(e.getMessage());
          e.printStackTrace();
          emitter.onError(e);
        } finally {
          if (needToOpened) { close(); }
          currentDaoFactory.onSessionClosed();
        }
        return;
      }
    };
    Maybe<T> result=Maybe.create(emitter);
    if (globalSubscribeOn!=null) result.subscribeOn(globalSubscribeOn);
    if (globalObserveOn!=null) result.observeOn(globalObserveOn);
    return result;
  }

  public <T> Maybe<T> executeBatch(final MaybeBatch<T> batch) {
    return executeBatch(batch, false);
  }

  public PublishSubject<SQLiteEvent> employeeSubject() {
    return employeeRawInsertSelectDao.subject();
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    execute(transaction, onErrorListener);
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   * @param onErrorListener
   * 	error listener
   */
  public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
    boolean needToOpened=!this.isOpenInWriteMode();
    boolean success=false;
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
    currentDaoFactory.onSessionOpened();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(currentDaoFactory)) {
        connection.setTransactionSuccessful();
        success=true;
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (onErrorListener!=null) onErrorListener.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      if (needToOpened) { close(); }
      if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
    }
  }

  /**
   * <p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>
   *
   * @param commands
   * 	batch to execute
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
   */
  public <T> T executeBatch(Batch<T> commands, boolean writeMode) {
    boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen();
    if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
    DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
    currentDaoFactory.onSessionOpened();
    try {
      if (commands!=null) {
        return commands.onExecute(currentDaoFactory);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      if (needToOpened) { close(); }
      currentDaoFactory.onSessionClosed();
    }
    return null;
  }

  /**
   * <p>Retrieve instance.</p>
   */
  public static BindKripton180RawInsertSelectDataSource instance() {
    BindKripton180RawInsertSelectDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindKripton180RawInsertSelectDataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
          }
        }
      }
    }
    return result;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindKripton180RawInsertSelectDataSource open() {
    BindKripton180RawInsertSelectDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindKripton180RawInsertSelectDataSource openReadOnly() {
    BindKripton180RawInsertSelectDataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    // log section BEGIN
    if (this.logEnabled) {
      if (options.inMemory) {
        Logger.info("Create database in memory");
      } else {
        Logger.info("Create database '%s' version %s",this.name, this.version);
      }
    }
    // log section END
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    }
    // log section END
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("Begin update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        task.execute(database, previousVersion, previousVersion+1);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        previousVersion++;
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  public void clearCompiledStatements() {
    EmployeeRawInsertSelectDaoImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   */
  public static BindKripton180RawInsertSelectDataSource build(DataSourceOptions options) {
    BindKripton180RawInsertSelectDataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindKripton180RawInsertSelectDataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
          }
        } else {
          throw new KriptonRuntimeException("Datasource BindKripton180RawInsertSelectDataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindKripton180RawInsertSelectDataSource is already builded");
    }
    return result;
  }

  /**
   * List of tables compose datasource:
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  public interface ObservableBatch<T> {
    void onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory,
        ObservableEmitter<T> emitter);
  }

  public interface ObservableTransaction<T> {
    TransactionResult onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory,
        ObservableEmitter<T> emitter);
  }

  public interface SingleBatch<T> {
    void onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory, SingleEmitter<T> emitter);
  }

  public interface SingleTransaction<T> {
    TransactionResult onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory,
        SingleEmitter<T> emitter);
  }

  public interface FlowableBatch<T> {
    void onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory, FlowableEmitter<T> emitter);
  }

  public interface FlowableTransaction<T> {
    TransactionResult onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory,
        FlowableEmitter<T> emitter);
  }

  public interface MaybeBatch<T> {
    void onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory, MaybeEmitter<T> emitter);
  }

  public interface MaybeTransaction<T> {
    TransactionResult onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory,
        MaybeEmitter<T> emitter);
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindKripton180RawInsertSelectDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   */
  public interface Batch<T> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindKripton180RawInsertSelectDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindKripton180RawInsertSelectDaoFactory {
    private SQLContextInSessionImpl _context;

    protected EmployeeRawInsertSelectDaoImpl _employeeRawInsertSelectDao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindKripton180RawInsertSelectDataSource.this);
    }

    /**
     *
     * retrieve dao EmployeeRawInsertSelectDao
     */
    public EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao() {
      if (_employeeRawInsertSelectDao==null) {
        _employeeRawInsertSelectDao=new EmployeeRawInsertSelectDaoImpl(_context);
      }
      return _employeeRawInsertSelectDao;
    }

    protected void onSessionOpened() {
    }

    protected void onSessionClear() {
    }

    protected void onSessionClosed() {
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}
