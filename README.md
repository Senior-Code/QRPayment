## QRPayment  [![](https://jitpack.io/v/Senior-Code/QRPayment.svg)](https://jitpack.io/#Senior-Code/QRPayment)
  QRPayment is an Java library for Android to generate QRcode in accordance to EMVco standard as well as KH QRcode standard.
  <!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#function">Function</a>
        <ul>
          <li><a href="#setpayment">setPayment()</a></li>
          <li><a href="#generatetext">generateText()</a></li>
          <li><a href="#generateimage">generateImage()</a></li>
          <li><a href="#setfilepath">setFilePath()</a></li>
        </ul>
    </li>
    <li><a href="#installation">Installation</a></li>
  </ol>
</details>

## Function
  ### SetPayment 
  **setPayment()** this function is used to set payment to the code.
  * ***parameters:*** *acquirerID*, *mercahntID*, *categoryCode*, *currencyCode*, *transactionAmount*, *identityCode*, *customerNumber*, *merchantName*, *merchantCity*
  ### GenerateText
  **generateText()** generateText() is used to generate QR code as EMVco text format.
  * this function is automatically generated from **setPayment()**
  ### GenerateImage
  **generateImage()** is used to generate QR code as QR image.
  * You need to pass some parameters such as *ImageView*, *EMVcode*, *Width*, *Height* into the function.
  * *EMVcode* is the String text which is generated from **generateText()**. You can put the text in another String and call the String in *EMVcode* parameters.
  ```java
  import com.emv.qrpayment.encoder.QRPayment;
  
  QRPayment qrpayment = new QRPayment;
  String emvcode = qrpayment.generateText();
  qrpayment.generateImage(imageview: ImageView, emvcode: emvcode, width: width, height: height);
  ```
  ### SetFilePath
  **setFilePath()** is used to give fee condition to your QR code and EMV code.
  * You need to download [configfee.csv](https://mega.nz/file/dQ8BDIKY#b7FDQu2eRI5qGFMvM1kd_HZ2iQHEWpssY7Q6agp0YTk) into your project resources directory and call InputStream method to pass the csv file into **setFilePath()**.
    * In your android project directory, you need to create a new Android Resources Directory and put its Resource type to raw.
  * After you download the file, simply paste the file into res/raw/ in your project directory.
## Installation
