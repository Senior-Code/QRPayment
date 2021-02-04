package com.emv.qrpayment.model.mpm;

import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.UnreservedTemplate;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;
import com.emv.qrpayment.core.CRC;
import com.emv.qrpayment.core.model.TagLengthString;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import lombok.Getter;

@Getter
public class MerchantPresentedMode implements Serializable {

    private static final long serialVersionUID = 485352878727448583L;

    // Merchant Account Information
    private final Map<String, com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate> merchantAccountInformation = new LinkedHashMap<>();
    // RFU for EMVCo
    private final Map<String, com.emv.qrpayment.core.model.TagLengthString> rFUforEMVCo = new LinkedHashMap<>();
    // Unreserved Templates
    private final Map<String, com.emv.qrcode.model.mpm.UnreservedTemplate> unreserveds = new LinkedHashMap<>();
    // Payload Format Indicator
    private com.emv.qrpayment.core.model.TagLengthString payloadFormatIndicator;
    // Point of Initiation Method
    private com.emv.qrpayment.core.model.TagLengthString pointOfInitiationMethod;
    // Merchant Category Code
    private com.emv.qrpayment.core.model.TagLengthString merchantCategoryCode;
    // Transaction Currency
    private com.emv.qrpayment.core.model.TagLengthString transactionCurrency;
    // Transaction Amount
    private com.emv.qrpayment.core.model.TagLengthString transactionAmount;
    // Tip or Convenience Indicator
    private com.emv.qrpayment.core.model.TagLengthString tipOrConvenienceIndicator;
    // Value of Convenience Fee Fixed
    private com.emv.qrpayment.core.model.TagLengthString valueOfConvenienceFeeFixed;
    // Value of Convenience Fee Percentage
    private com.emv.qrpayment.core.model.TagLengthString valueOfConvenienceFeePercentage;
    // Country Code
    private com.emv.qrpayment.core.model.TagLengthString countryCode;
    // Merchant Name
    private com.emv.qrpayment.core.model.TagLengthString merchantName;

    // Additional Data Field Template
    private AdditionalDataFieldTemplate additionalDataField;
    // Merchant City
    private com.emv.qrpayment.core.model.TagLengthString merchantCity;

    // Merchant Information - Language Template
    private MerchantInformationLanguageTemplate merchantInformationLanguage;
    // Postal Code
    private com.emv.qrpayment.core.model.TagLengthString postalCode;
    // CRC
    private com.emv.qrpayment.core.model.TagLengthString cRC;

    public void setAdditionalDataField(final AdditionalDataFieldTemplate additionalDataField) {
        this.additionalDataField = additionalDataField;
    }

    public void setMerchantInformationLanguage(final MerchantInformationLanguageTemplate merchantInformationLanguage) {
        this.merchantInformationLanguage = merchantInformationLanguage;
    }

    public final void setPayloadFormatIndicator(final String payloadFormatIndicator) {
        this.payloadFormatIndicator = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR, payloadFormatIndicator);
    }

    public final void setPointOfInitiationMethod(final String pointOfInitiationMethod) {
        this.pointOfInitiationMethod = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD, pointOfInitiationMethod);
    }

    public final void setMerchantCategoryCode(final String merchantCategoryCode) {
        this.merchantCategoryCode = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE, merchantCategoryCode);
    }

    public final void setTransactionCurrency(final String transactionCurrency) {
        this.transactionCurrency = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY, transactionCurrency);
    }

    public final void setTransactionAmount(final String transactionAmount) {
        this.transactionAmount = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT, transactionAmount);
    }

    public final void setTipOrConvenienceIndicator(final String tipOrConvenienceIndicator) {
        this.tipOrConvenienceIndicator = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, tipOrConvenienceIndicator);
  }

  public final void setValueOfConvenienceFeeFixed(final String valueOfConvenienceFeeFixed) {
      this.valueOfConvenienceFeeFixed = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, valueOfConvenienceFeeFixed);
  }

  public final void setValueOfConvenienceFeePercentage(final String valueOfConvenienceFeePercentage) {
      this.valueOfConvenienceFeePercentage = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, valueOfConvenienceFeePercentage);
  }

  public final void setCountryCode(final String countryCode) {
      this.countryCode = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_COUNTRY_CODE, countryCode);
  }

    public final void setMerchantName(final String merchantName) {
        this.merchantName = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_NAME, merchantName);
    }

    public final void setMerchantCity(final String merchantCity) {
        this.merchantCity = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CITY, merchantCity);
    }

    public final void setPostalCode(final String postalCode) {
        this.postalCode = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_POSTAL_CODE, postalCode);
    }

    public final void setCRC(final String cRC) {
        this.cRC = new com.emv.qrpayment.core.model.TagLengthString(MerchantPresentedModeCodes.ID_CRC, cRC);
    }

    public final void addUnreserved(final com.emv.qrcode.model.mpm.UnreservedTemplate unreserved) {
        this.unreserveds.put(unreserved.getTag(), unreserved);
    }

    public final void addMerchantAccountInformation(final com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate merchantAccountInformation) {
        this.merchantAccountInformation.put(merchantAccountInformation.getTag(), merchantAccountInformation);
    }

    public final void addRFUforEMVCo(final com.emv.qrpayment.core.model.TagLengthString rFUforEMVCo) {
        this.rFUforEMVCo.put(rFUforEMVCo.getTag(), rFUforEMVCo);
    }

    public String toHex() {
        return Hex.encodeHexString(toString().getBytes(StandardCharsets.UTF_8), false);
    }

    public String toBase64() {
        return Base64.encodeBase64String(toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder(this.toStringWithoutCrc16());

        final String string = sb.toString();

        if (StringUtils.isBlank(string)) {
            return StringUtils.EMPTY;
    }

    final int crc16 = CRC.crc16(sb.toString().getBytes(StandardCharsets.UTF_8));
    
    sb.append(String.format("%04X", crc16));

    return sb.toString();
  }
  
  public String toStringWithoutCrc16() {
    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(payloadFormatIndicator).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(pointOfInitiationMethod).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, MerchantAccountInformationTemplate> entry : merchantAccountInformation.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    Optional.ofNullable(merchantCategoryCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(transactionCurrency).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(transactionAmount).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(tipOrConvenienceIndicator).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(valueOfConvenienceFeeFixed).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(valueOfConvenienceFeePercentage).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(countryCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(postalCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(additionalDataField).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantInformationLanguage).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final Entry<String, UnreservedTemplate> entry : unreserveds.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    sb.append(String.format("%s%s", MerchantPresentedModeCodes.ID_CRC, "04"));
    
    return sb.toString();
  }
}
