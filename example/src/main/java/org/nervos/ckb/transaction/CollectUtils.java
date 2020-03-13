package org.nervos.ckb.transaction;

import io.reactivex.annotations.NonNull;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.nervos.ckb.service.Api;
import org.nervos.ckb.type.cell.CellOutput;
import org.nervos.ckb.type.cell.CellWithStatus;
import org.nervos.ckb.type.fixed.UInt128;
import org.nervos.ckb.type.transaction.Transaction;
import org.nervos.ckb.utils.Numeric;
import org.nervos.ckb.utils.Strings;
import org.nervos.ckb.utils.address.AddressParseResult;
import org.nervos.ckb.utils.address.AddressParser;

/** Copyright © 2019 Nervos Foundation. All rights reserved. */
public class CollectUtils {

  private Api api;

  public CollectUtils(Api api) {
    this.api = api;
  }

  public CollectResult collectInputs(
      List<String> addresses,
      Transaction transaction,
      BigInteger feeRate,
      int initialLength,
      boolean skipDataAndType,
      long fromBlockNumber,
      String typeHash)
      throws IOException {
    return new CellCollector(api)
        .collectInputs(
            addresses,
            transaction,
            feeRate,
            initialLength,
            new CellBlockIterator(api, addresses, skipDataAndType, fromBlockNumber, typeHash));
  }

  public CollectResult collectInputs(
      List<String> addresses, Transaction transaction, BigInteger feeRate, int initialLength)
      throws IOException {
    return collectInputs(addresses, transaction, feeRate, initialLength, true, 0, null);
  }

  public CollectResult collectInputsWithIndexer(
      List<String> addresses,
      Transaction transaction,
      BigInteger feeRate,
      int initialLength,
      boolean skipDataAndType)
      throws IOException {
    return new CellCollector(api)
        .collectInputs(
            addresses,
            transaction,
            feeRate,
            initialLength,
            new CellIndexerIterator(api, addresses, skipDataAndType));
  }

  public CollectResult collectInputsWithIndexer(
      List<String> addresses, Transaction transaction, BigInteger feeRate, int initialLength)
      throws IOException {
    return collectInputsWithIndexer(addresses, transaction, feeRate, initialLength, true);
  }

  public BigInteger getCapacityWithAddress(String address, boolean withIndexer) {
    BigInteger capacity = BigInteger.ZERO;
    Iterator<TransactionInput> cellIterator =
        withIndexer
            ? new CellIndexerIterator(api, Collections.singletonList(address))
            : new CellBlockIterator(api, Collections.singletonList(address), true, 0, null);
    while (cellIterator.hasNext()) {
      TransactionInput transactionInput = cellIterator.next();
      if (transactionInput == null) break;
      capacity = capacity.add(transactionInput.capacity);
    }
    return capacity;
  }

  public BigInteger getCapacityWithAddress(String address) {
    return getCapacityWithAddress(address, false);
  }

  public BigInteger getUdtBalanceWithAddress(String address, @NonNull String typeHash)
      throws IOException {
    BigInteger amount = BigInteger.ZERO;
    Iterator<TransactionInput> cellIterator =
        new CellBlockIterator(api, Collections.singletonList(address), false, 0, typeHash);
    while (cellIterator.hasNext()) {
      TransactionInput transactionInput = cellIterator.next();
      if (transactionInput == null) break;
      CellWithStatus cellWithStatus = api.getLiveCell(transactionInput.input.previousOutput, true);
      String outputsData = cellWithStatus.cell.data.content;
      if (Strings.isEmpty(outputsData)) break;
      amount = amount.add(new UInt128(outputsData).getValue());
    }
    return amount;
  }

  public List<CellOutput> generateOutputs(List<Receiver> receivers, String changeAddress) {
    List<CellOutput> cellOutputs = new ArrayList<>();
    for (Receiver receiver : receivers) {
      AddressParseResult addressParseResult = AddressParser.parse(receiver.address);
      cellOutputs.add(
          new CellOutput(
              Numeric.toHexStringWithPrefix(receiver.capacity), addressParseResult.script));
    }
    AddressParseResult addressParseResult = AddressParser.parse(changeAddress);
    cellOutputs.add(new CellOutput("0x0", addressParseResult.script));
    return cellOutputs;
  }
}
