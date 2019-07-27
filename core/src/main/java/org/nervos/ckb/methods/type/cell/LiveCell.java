package org.nervos.ckb.methods.type.cell;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.nervos.ckb.methods.type.transaction.TransactionPoint;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class LiveCell {

  @JsonProperty("created_by")
  public TransactionPoint createdBy;

  public CellOutput cellOutput;
}
