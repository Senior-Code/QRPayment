package com.emv.qrpayment.encoder;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.emv.qrcode.core.isos.Country;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;


public class QRPayment {
    private InputStream inputStream;
    private String acquirerID;
    private String merchantID;
    private String categoryCode;
    private String currencyCode;
    private String transactionAmount;
    private String merchantName;
    private String merchantCity;
    private String identityCode;
    private String customerNumber;

    private String QRPayment() {
        final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
        final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
        final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
        String linesplit = "";
        String Fee = "";
        String AquirerID = "aquirerid";
        String MerchantID = "merchantid";
        String Start = "startamount";
        String End = "endamount";
        String FeeFixed = "feeamount";
        String FeePercent = "feepercent";
        String Currency = "currency";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((linesplit = br.readLine()) != null) {
                ArrayList<String> ls = new ArrayList<>();
                HashMap hm = new HashMap<String, String>();
                String[] rows = linesplit.split(";");
                for (int i = 0; i < rows.length; i++) {
                    ls.add(rows[i]);
                }
                hm.put(AquirerID, ls.get(0));
                hm.put(MerchantID, ls.get(1));
                hm.put(Start, ls.get(2));
                hm.put(End, ls.get(3));
                hm.put(FeeFixed, ls.get(4));
                hm.put(FeePercent, ls.get(5));
                hm.put(Currency, ls.get(6));
                String currency = "";
                if (currencyCode == "840") {
                    currency = "USD";
                } else if (currencyCode == "116") {
                    currency = "KHR";
                } else if (currencyCode == "764") {
                    currency = "THB";
                }
                if (hm.containsValue(acquirerID) && hm.containsValue(merchantID) && hm.containsValue(currency)) {
                    int amountStart = Integer.parseInt(hm.get(Start).toString());
                    int amountEnd = Integer.parseInt(hm.get(End).toString());
                    int Amount = Integer.parseInt(transactionAmount);
                    if (Amount >= amountStart && Amount <= amountEnd) {
                        Fee = hm.get(FeeFixed).toString();
                        break;
                    } else if (amountStart == 0 && amountEnd == 0) {
                        Fee = hm.get(FeeFixed).toString();
                        break;
                    } else {
                        Fee = null;
                        break;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        merchantPresentMode.setPayloadFormatIndicator("01");
        merchantPresentMode.setPointOfInitiationMethod("12");
        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
        merchantPresentMode.setMerchantCategoryCode(categoryCode);
        merchantPresentMode.setTransactionCurrency(currencyCode);
        merchantPresentMode.setTransactionAmount(transactionAmount);
        merchantPresentMode.setCountryCode(String.valueOf(Country.KH));
        merchantPresentMode.setMerchantName(merchantName);
        if (merchantCity == "" || merchantCity == null) {
            merchantPresentMode.setMerchantCity("Phnom Penh");
        } else {
            merchantPresentMode.setMerchantCity(merchantCity);
        }
        if (Fee != null && Fee != "") {
            merchantPresentMode.setTipOrConvenienceIndicator("02");
            merchantPresentMode.setValueOfConvenienceFeeFixed(Fee);
        }
        merchantPresentMode.setAdditionalDataField(additionalDataField);
        return merchantPresentMode.toString();
    }

    private MerchantAccountInformationTemplate getMerchanAccountInformation() {
        final TagLengthString paymentNetworkSpecific = new TagLengthString();
        paymentNetworkSpecific.setTag("01");
        paymentNetworkSpecific.setValue(merchantID);
        final TagLengthString partnerID = new TagLengthString();
        partnerID.setTag("03");
        partnerID.setValue("B24");

        final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
        merchantAccountInformationValue.setGloballyUniqueIdentifier(acquirerID);
        merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);
        merchantAccountInformationValue.addPaymentNetworkSpecific(partnerID);


        final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
        merchantAccountInformation.setValue(merchantAccountInformationValue);
        merchantAccountInformation.setTag("29");

        return merchantAccountInformation;
    }

    private AdditionalDataFieldTemplate getAddtionalDataField() {
        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
        additionalDataFieldValue.setBillNumber(identityCode);
        additionalDataFieldValue.setCustomerLabel(customerNumber);


        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);

        return additionalDataField;
    }

    public final void setPayment(
            final String acquirerID,
            final String merchantID,
            final String categoryCode,
            final String currencyCode,
            final String transactionAmount,
            final String identityCode,
            final String customerNumber,
            final String merchantName,
            final String merchantCity
    ) {
        this.acquirerID = acquirerID;
        this.merchantID = merchantID;
        this.categoryCode = categoryCode;
        this.currencyCode = currencyCode;
        this.transactionAmount = transactionAmount;
        this.identityCode = identityCode;
        this.customerNumber = customerNumber;
        this.merchantName = merchantName;
        this.merchantCity = merchantCity;
    }

    public final void generateImage(@NotNull final ImageView imageView, final String EMVCode, final int Width, final int Height) {
        Bitmap result = this.encodeAsBitmap(EMVCode, Width, Height);
        imageView.setImageBitmap(result);
    }

    public String generateText() {
        return QRPayment();
    }

    public void setFilePath(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Nullable
    private final Bitmap encodeAsBitmap(String str, int WIDTH, int HEIGHT) {
        BitMatrix result = null;
        try {
            BitMatrix var10000 = null;
            try {
                var10000 = (new MultiFormatWriter()).encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            Intrinsics.checkNotNullExpressionValue(var10000, "MultiFormatWriter().enco…EIGHT, null\n            )");
            result = var10000;
        } catch (IllegalArgumentException var13) {
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        int y = 0;

        for (int var9 = height; y < var9; ++y) {
            int offset = y * width;
            int x = 0;

            for (int var12 = width; x < var12; ++x) {
                pixels[offset + x] = result.get(x, y) ? -16777216 : -1;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}

