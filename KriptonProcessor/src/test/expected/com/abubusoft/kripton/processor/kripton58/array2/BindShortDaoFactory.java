package com.abubusoft.kripton.processor.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ShortDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ShortDataSource
 * @see ShortDao
 * @see BindShortDao
 * @see ShortBean
 */
public interface BindShortDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao ShortDao
   */
  BindShortDao getShortDao();
}