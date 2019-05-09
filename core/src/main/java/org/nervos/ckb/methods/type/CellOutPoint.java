package org.nervos.ckb.methods.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Created by duanyytop on 2019-05-08. Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CellOutPoint {

  @JsonProperty("tx_hash")
  public String txHash;

  public int index;

  public CellOutPoint() {}

  public CellOutPoint(String txHash, int index) {
    this.txHash = txHash;
    this.index = index;
  }
}
