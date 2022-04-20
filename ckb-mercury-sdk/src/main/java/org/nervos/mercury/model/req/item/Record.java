package org.nervos.mercury.model.req.item;

import com.google.common.primitives.Bytes;
import org.nervos.ckb.type.OutPoint;
import org.nervos.ckb.type.Script;
import org.nervos.ckb.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class Record {

  public static final byte[] SCRIPT_TYPE = new byte[]{0x1};

  public static final byte[] ADDRESS_TYPE = new byte[]{0x0};

  public String record;

  public transient OutPoint outPoint;

  public transient Script script;

  public transient String address;

  public Record(OutPoint outPoint, Script script) {
    this(outPoint, script, null);
  }

  public Record(OutPoint outPoint, String address) {
    this(outPoint, null, address);
  }

  public Record(OutPoint outPoint, Script script, String address) {
    this.outPoint = outPoint;
    this.script = script;
    this.address = address;
    this.record = this.toRecordItem();
  }

  public String toRecordItem() {
    byte[] record = Bytes.concat(this.outPoint.txHash, intToByteArray(this.outPoint.index));

    if (Objects.nonNull(this.script)) {
      return Numeric.toHexString(
          Bytes.concat(record, SCRIPT_TYPE, Arrays.copyOfRange(this.script.computeHash(), 0, 20)));
    } else {
      return Numeric.toHexString(
          Bytes.concat(record, ADDRESS_TYPE, this.address.getBytes(StandardCharsets.UTF_8)));
    }
  }

  private byte[] intToByteArray(int value) {
    byte[] byteArray = new byte[4];
    byteArray[3] = (byte) (value & 0xFF);
    byteArray[2] = (byte) (value & 0xFF00);
    byteArray[1] = (byte) (value & 0xFF0000);
    byteArray[0] = (byte) (value & 0xFF000000);
    return byteArray;
  }
}
