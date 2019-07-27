package org.nervos.ckb.methods.response;

import java.util.List;
import org.nervos.ckb.methods.Response;
import org.nervos.ckb.methods.type.cell.CellTransaction;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CkbCellTransactions extends Response<List<CellTransaction>> {
  public List<CellTransaction> getCellTransactions() {
    return result;
  }
}
