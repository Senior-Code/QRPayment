package com.emv.qrpayment.model.mpm;

import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrpayment.core.model.TLV;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import lombok.Setter;

@Setter
public class PaymentSystemSpecificTemplate implements TLV<String, com.emv.qrcode.model.mpm.PaymentSystemSpecific> {

    private static final long serialVersionUID = -1445641777082739037L;

    private String tag;

    private com.emv.qrcode.model.mpm.PaymentSystemSpecific value;

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public PaymentSystemSpecific getValue() {
        return value;
    }

    @Override
    public String toString() {

        if (Objects.isNull(value)) {
            return StringUtils.EMPTY;
        }

        final String string = value.toString();

        if (StringUtils.isBlank(string)) {
            return StringUtils.EMPTY;
        }

    return String.format("%s%02d%s", tag, string.length(), string);
  }

}
