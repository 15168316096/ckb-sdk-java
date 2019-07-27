package org.nervos.ckb.methods.response;

import org.nervos.ckb.methods.Response;
import org.nervos.ckb.methods.type.LockHashIndexState;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CkbLockHashIndexState extends Response<LockHashIndexState> {

  public LockHashIndexState getLockHashIndexState() {
    return result;
  }
}
