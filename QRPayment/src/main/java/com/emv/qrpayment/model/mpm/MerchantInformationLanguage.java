package com.emv.qrpayment.model.mpm;

import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;
import com.emv.qrpayment.core.model.TagLengthString;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import lombok.Getter;

@Getter
public class MerchantInformationLanguage implements Serializable {

    private static final long serialVersionUID = 6163271793010568887L;

    // RFU for EMVCo
    private final Map<String, com.emv.qrpayment.core.model.TagLengthString> rFUforEMVCo = new LinkedHashMap<>();
    // Language Preference
    private com.emv.qrpayment.core.model.TagLengthString languagePreference;
    // Merchant Name
    private com.emv.qrpayment.core.model.TagLengthString merchantName;
    // Merchant City
    private com.emv.qrpayment.core.model.TagLengthString merchantCity;

    public final void setLanguagePreference(final String languagePreference) {
        this.languagePreference = new com.emv.qrpayment.core.model.TagLengthString(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, languagePreference);
    }

    public final void setMerchantName(final String merchantName) {
        this.merchantName = new com.emv.qrpayment.core.model.TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, merchantName);
    }

    public final void setMerchantCity(final String merchantCity) {
        this.merchantCity = new com.emv.qrpayment.core.model.TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, merchantCity);
    }

    public final void addRFUforEMVCo(final com.emv.qrpayment.core.model.TagLengthString tagLengthString) {
        rFUforEMVCo.put(tagLengthString.getTag(), tagLengthString);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));

        for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
            Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
        }

        final String string = sb.toString();

        if (StringUtils.isBlank(string)) {
            return StringUtils.EMPTY;
        }

    return string;
  }

}
