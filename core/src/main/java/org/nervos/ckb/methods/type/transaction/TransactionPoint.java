package org.nervos.ckb.methods.type.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class TransactionPoint {
  @JsonProperty("block_number")
  public String blockNumber;

  @JsonProperty("tx_hash")
  public String txHash;

  public String index;
}
