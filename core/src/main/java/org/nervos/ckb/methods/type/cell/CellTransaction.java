package org.nervos.ckb.methods.type.cell;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.nervos.ckb.methods.type.transaction.TransactionPoint;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CellTransaction {

  @JsonProperty("created_by")
  public TransactionPoint createdBy;

  @JsonProperty("consumed_by")
  public TransactionPoint consumedBy;
}
