package org.nervos.ckb.methods.response;

import org.nervos.ckb.methods.Response;

/** Copyright © 2018 Nervos Foundation. All rights reserved. */
public class CkbBlockHash extends Response<String> {

  public String getBlockHash() {
    return result;
  }
}
