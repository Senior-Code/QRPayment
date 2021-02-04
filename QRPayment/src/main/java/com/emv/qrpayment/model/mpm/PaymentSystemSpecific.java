package com.emv.qrpayment.model.mpm;

import com.emv.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;
import com.emv.qrpayment.core.model.TagLengthString;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import lombok.Getter;

@Getter
public class PaymentSystemSpecific implements Serializable {

    private static final long serialVersionUID = 6244729218605588349L;

    // Context Specific Data
    private final Map<String, com.emv.qrpayment.core.model.TagLengthString> paymentSystemSpecific = new LinkedHashMap<>();
    // Globally Unique Identifier
    private com.emv.qrpayment.core.model.TagLengthString globallyUniqueIdentifier;

    public PaymentSystemSpecific() {
        super();
    }

    public PaymentSystemSpecific(final String globallyUniqueIdentifier) {
        this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    }

    public PaymentSystemSpecific(final String globallyUniqueIdentifier, final String tag, final String value) {
        this.setGloballyUniqueIdentifier(globallyUniqueIdentifier, tag, value);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
        this.globallyUniqueIdentifier = new com.emv.qrpayment.core.model.TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final com.emv.qrpayment.core.model.TagLengthString paymentSystemSpecific) {
        this.globallyUniqueIdentifier = new com.emv.qrpayment.core.model.TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
        this.addPaymentSystemSpecific(paymentSystemSpecific);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag, final String value) {
        this.globallyUniqueIdentifier = new com.emv.qrpayment.core.model.TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
        this.addPaymentSystemSpecific(tag, value);
    }

    public void addPaymentSystemSpecific(final com.emv.qrpayment.core.model.TagLengthString tagLengthString) {
        paymentSystemSpecific.put(tagLengthString.getTag(), tagLengthString);
    }

    public void addPaymentSystemSpecific(final String tag, final String value) {
        paymentSystemSpecific.put(tag, new com.emv.qrpayment.core.model.TagLengthString(tag, value));
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

        for (final Entry<String, TagLengthString> entry : paymentSystemSpecific.entrySet()) {
            Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
        }

        final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
