package com.emv.qrpayment.model.mpm;

import com.emv.qrcode.model.mpm.constants.AdditionalDataFieldCodes;
import com.emv.qrpayment.core.model.TagLengthString;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import lombok.Getter;

@Getter
public class AdditionalDataField implements Serializable {

    private static final long serialVersionUID = -6651622119486438559L;

    // RFU for EMVCo
    private final Map<String, com.emv.qrpayment.core.model.TagLengthString> rFUforEMVCo = new LinkedHashMap<>();
    // Payment System specific templates
    private final Map<String, PaymentSystemSpecificTemplate> paymentSystemSpecific = new LinkedHashMap<>();
    // Bill Number
    private com.emv.qrpayment.core.model.TagLengthString billNumber;
    // Country Code
    private com.emv.qrpayment.core.model.TagLengthString mobileNumber;
    // Store Label
    private com.emv.qrpayment.core.model.TagLengthString storeLabel;
    // Loyalty Number
    private com.emv.qrpayment.core.model.TagLengthString loyaltyNumber;
    // Reference Label
    private com.emv.qrpayment.core.model.TagLengthString referenceLabel;
    // Customer Label
    private com.emv.qrpayment.core.model.TagLengthString customerLabel;
    // Terminal Label
    private com.emv.qrpayment.core.model.TagLengthString terminalLabel;
    // Purpose of Transaction
    private com.emv.qrpayment.core.model.TagLengthString purposeTransaction;
    // Additional TagLengthString Data Request
    private com.emv.qrpayment.core.model.TagLengthString additionalConsumerDataRequest;

    public final void setBillNumber(final String billNumber) {
        this.billNumber = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_BILL_NUMBER, billNumber);
    }

    public final void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_MOBILE_NUMBER, mobileNumber);
    }

    public final void setStoreLabel(final String storeLabel) {
        this.storeLabel = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_STORE_LABEL, storeLabel);
    }

    public final void setLoyaltyNumber(final String loyaltyNumber) {
        this.loyaltyNumber = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_LOYALTY_NUMBER, loyaltyNumber);
    }

  public final void setReferenceLabel(final String referenceLabel) {
      this.referenceLabel = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_REFERENCE_LABEL, referenceLabel);
  }

    public final void setCustomerLabel(final String customerLabel) {
        this.customerLabel = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_CUSTOMER_LABEL, customerLabel);
    }

    public final void setTerminalLabel(final String terminalLabel) {
        this.terminalLabel = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_TERMINAL_LABEL, terminalLabel);
    }

    public final void setPurposeTransaction(final String purposeTransaction) {
        this.purposeTransaction = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_PURPOSE_TRANSACTION, purposeTransaction);
    }

    public final void setAdditionalConsumerDataRequest(final String additionalConsumerDataRequest) {
        this.additionalConsumerDataRequest = new com.emv.qrpayment.core.model.TagLengthString(AdditionalDataFieldCodes.ID_ADDITIONAL_CONSUMER_DATA_REQUEST, additionalConsumerDataRequest);
    }

    public final void addRFUforEMVCo(final com.emv.qrpayment.core.model.TagLengthString rFUforEMVCo) {
        this.rFUforEMVCo.put(rFUforEMVCo.getTag(), rFUforEMVCo);
    }

    public final void addPaymentSystemSpecific(final PaymentSystemSpecificTemplate paymentSystemSpecific) {
        this.paymentSystemSpecific.put(paymentSystemSpecific.getTag(), paymentSystemSpecific);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Optional.ofNullable(billNumber).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(mobileNumber).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(storeLabel).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(loyaltyNumber).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(referenceLabel).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(customerLabel).ifPresent(tlv -> sb.append(tlv.toString()));
        Optional.ofNullable(terminalLabel).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(purposeTransaction).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(additionalConsumerDataRequest).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final Entry<String, PaymentSystemSpecificTemplate> entry : paymentSystemSpecific.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
