package org.nervos.ckb.transaction;

import java.math.BigInteger;
import java.util.List;
import org.nervos.ckb.methods.type.cell.CellInput;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CollectedCells {
  List<CellInput> inputs;
  BigInteger capacity;
  String privateKey;

  CollectedCells(List<CellInput> inputs, BigInteger capacity) {
    this.inputs = inputs;
    this.capacity = capacity;
  }

  CollectedCells(List<CellInput> inputs, BigInteger capacity, String privateKey) {
    this.inputs = inputs;
    this.capacity = capacity;
    this.privateKey = privateKey;
  }
}
