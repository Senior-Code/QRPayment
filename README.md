## QRPayment  [![](https://jitpack.io/v/Senior-Code/QRPayment.svg)](https://jitpack.io/#Senior-Code/QRPayment)
  QRPayment is an Java library for Android to generate QRcode in accordance to EMVco standard as well as KH QRcode standard.
## Function
  * **setPayment()** this function is used to set payment to the code.
    * ***parameters:*** *acquirerID*, *mercahntID*, *categoryCode*, *currencyCode*, *transactionAmount*, *identityCode*, *customerNumber*, *merchantName*, *merchantCity*
  * **generateText()** generateText() is used to generate QR code as EMVco text format.
    * this function is automatically generated from **setPayment()**
  * **generateImage()** generateImage() is used to generate QR code as QR image.
    * in generateImage() function, you need to pass some parameters such as *ImageView*, *EMVcode*, *Width*, *Height*
    * *EMVcode* is the String text which is generated from **generateText()**. You can put the text in another String and call the String in *EMVcode* parameters
  * **setFilePath()** is used to give fee condition to your QR code and EMV code.
## Installation
