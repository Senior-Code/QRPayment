package com.emv.qrcode.core.model;

import java.io.Serializable;
import java.util.Optional;

public interface TLV<T, V> extends Serializable {

  T getTag();

  V getValue();

  default Integer getLength() {
    return Optional.ofNullable(getValue()).map(V::toString).map(String::length).orElse(0);
  }

}
