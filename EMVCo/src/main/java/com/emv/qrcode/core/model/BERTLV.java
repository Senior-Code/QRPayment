package com.emv.qrcode.core.model;

import java.io.IOException;

import org.apache.commons.codec.binary.Hex;

@SuppressWarnings("java:S1214")
public interface BERTLV<T, V> extends TLV<T, V> {

  byte[] EMPTY_BYTES = new byte[0];

  byte[] getBytes() throws IOException;

  default String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
