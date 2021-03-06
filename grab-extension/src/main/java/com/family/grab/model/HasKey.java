package com.family.grab.model;

import com.family.grab.utils.Experimental;

/**
 * Interface to be implemented by page mode.<br>
 * Can be used to identify a page com.family.grabserver.model, or be used as name of file storing the object.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
@Experimental
public interface HasKey {

  /**
   * @return key
   */
  public String key();
}
