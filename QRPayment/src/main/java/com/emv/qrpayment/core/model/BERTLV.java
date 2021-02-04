package com.emv.qrpayment.core.model;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;

@SuppressWarnings("java:S1214")
public interface BERTLV<T, V> extends TLV<T, V> {

    byte[] EMPTY_BYTES = new byte[0];

    byte[] getBytes() throws IOException;

    default String toHex() throws IOException {
        return Hex.encodeHexString(getBytes(), false);
    }

}
