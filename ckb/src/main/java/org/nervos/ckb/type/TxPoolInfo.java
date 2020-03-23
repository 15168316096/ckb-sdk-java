package org.nervos.ckb.type;

import com.google.gson.annotations.SerializedName;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class TxPoolInfo {

  public String pending;
  public String staging;
  public String orphan;

  @SerializedName("last_txs_updated_at")
  public String lastTxsUpdatedAt;

  @SerializedName("total_tx_cycles")
  public String totalTxCycles;

  @SerializedName("total_tx_size")
  public String totalTxSize;

  @SerializedName("min_fee_rate")
  public String minFeeRate;
}
