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
public class MerchantAccountInformation implements Serializable {

    private static final long serialVersionUID = 3394308551644415429L;

    // Payment network specific
    private final Map<String, com.emv.qrpayment.core.model.TagLengthString> paymentNetworkSpecific = new LinkedHashMap<>();
    // Globally Unique Identifier
    private com.emv.qrpayment.core.model.TagLengthString globallyUniqueIdentifier;

    public MerchantAccountInformation() {
        super();
    }

    public MerchantAccountInformation(final String globallyUniqueIdentifier) {
        this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    }

    public MerchantAccountInformation(final String globallyUniqueIdentifier, final String tag, final String value) {
        this.setGloballyUniqueIdentifier(globallyUniqueIdentifier, tag, value);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
        this.globallyUniqueIdentifier = new com.emv.qrpayment.core.model.TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    }

    public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag, final String value) {
        this.globallyUniqueIdentifier = new com.emv.qrpayment.core.model.TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
        this.addPaymentNetworkSpecific(tag, value);
    }

    public void addPaymentNetworkSpecific(final com.emv.qrpayment.core.model.TagLengthString tagLengthString) {
        paymentNetworkSpecific.put(tagLengthString.getTag(), tagLengthString);
    }

    public void addPaymentNetworkSpecific(final String tag, final String value) {
        paymentNetworkSpecific.put(tag, new com.emv.qrpayment.core.model.TagLengthString(tag, value));
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));

        for (final Entry<String, TagLengthString> entry : paymentNetworkSpecific.entrySet()) {
            Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
        }

        final String string = sb.toString();

        if (StringUtils.isBlank(string)) {
            return StringUtils.EMPTY;
        }

        return string;
    }

}
