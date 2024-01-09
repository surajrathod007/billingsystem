package com.surajrathod.billingsystem

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import javafx.fxml.FXML
import javafx.scene.control.Label
import java.io.*
import java.time.LocalDate
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import java.time.format.DateTimeFormatter


class HelloController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        makeInvoice()
    }

    private fun createTextFileWithContent() {
        val desktopPath = System.getProperty("user.home") + "\\Downloads"
        val filePath = "$desktopPath\\myTextFile.txt"

        val newPath = filePath.replace("\\","/")
        welcomeText.text = "$newPath"
        val file = File(newPath)
        try {
            if (file.createNewFile()) {
                val fileWriter = FileWriter(file)
                val bufferedWriter = BufferedWriter(fileWriter)
                bufferedWriter.write("This is a sample text.")
                bufferedWriter.close()
                println("Text file created at: $filePath")
            } else {
                println("File already exists.")
            }
        } catch (e: IOException) {
            println("An error occurred while creating the file.")
            e.printStackTrace()
        }
    }

    private fun makeInvoice() {

        try {


            val downloadPath = System.getProperty("user.home") + "\\Downloads"

            val newPath = downloadPath.replace("\\","/")

            val file = File(downloadPath, "mann_sign_invoice${System.currentTimeMillis()}.pdf")
            val output = FileOutputStream(file)

            val writer = PdfWriter(file)
            val pdfDocument = PdfDocument(writer)
            val document = Document(pdfDocument)
            //document.setMargins(1f,1f,1f,1f)


            //header
//            val headerImg = this.getDrawable(R.drawable.invoice_header)
//            val bitmap = (headerImg as BitmapDrawable).bitmap
//            val opstream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, opstream)
//            val bitmapdata = opstream.toByteArray()
//
//            val img = ImageDataFactory.create(bitmapdata)
//            val myheader = Image(img)
//            document.add(myheader)
            //first table
            val c: FloatArray = floatArrayOf(220F, 220F, 200F, 180F)
            val table1 = Table(c)
            // row 1
            table1.addCell(Cell().add(Paragraph("Bill to Party ").setFontSize(10.0f)).setBold())
            table1.addCell(Cell().add(Paragraph("Ship to Party ").setFontSize(10.0f)).setBold())
            table1.addCell(Cell().add(Paragraph("Date : ").setFontSize(10.0f)).setBold())
            table1.addCell(
                Cell().add(
                    Paragraph(LocalDate.now().toString()).setFontSize(10.0f).setBold()
                )
            )
            //row 2     //TODO : Fetch user address
            table1.addCell(Cell(4, 0).add(Paragraph("").setFontSize(8.0f)))
            table1.addCell(Cell(4, 0).add(Paragraph("").setFontSize(8.0f)))
            table1.addCell(Cell().add(Paragraph("Invoice No : ").setFontSize(10.0f)))
            table1.addCell(Cell().add(Paragraph("inv1000").setFontSize(10.0f)))

            //row 3
            //table1.addCell(Cell().add(Paragraph("")))
            //table1.addCell(Cell().add(Paragraph("")))
            table1.addCell(Cell().add(Paragraph("Buyer's Order No. :").setFontSize(10.0f)))
            table1.addCell(Cell().add(Paragraph("l")))

            //row 4
            //table1.addCell(Cell().add(Paragraph("")))
            //table1.addCell(Cell().add(Paragraph("")))
            table1.addCell(Cell().add(Paragraph("Order Date : ").setFontSize(10.0f)))
            table1.addCell(
                Cell().add(
                    Paragraph(
                        "l"
                    ).setFontSize(10.0f)
                )
            )

            //row 5
            //table1.addCell(Cell().add(Paragraph("")))
            //table1.addCell(Cell().add(Paragraph("")))
            table1.addCell(Cell().add(Paragraph("State Code : ").setFontSize(10.0f)))
            table1.addCell(Cell().add(Paragraph("24").setFontSize(10.0f)))

            //row 6
            table1.addCell(Cell().add(Paragraph("State Code : ").setFontSize(8.0f).setBold()))
            table1.addCell(Cell().add(Paragraph("State Code : ").setFontSize(8.0f).setBold()))
            table1.addCell(
                Cell(0, 2).add(Paragraph("COMPANY GSTIN NO : ").setFontSize(8.0f).setBold())
                    .setTextAlignment(
                        TextAlignment.CENTER
                    )
            )
            //table1.addCell(Cell().add(Paragraph("")))

            //row 7
            table1.addCell(
                Cell().add(
                    Paragraph("GSTIN NO : <add here>").setFontSize(8.0f).setBold()
                )
            )
            table1.addCell(
                Cell().add(
                    Paragraph("GSTIN NO : <add here>").setFontSize(8.0f).setBold()
                )
            )
            table1.addCell(
                Cell(0, 2).add(Paragraph("24BENPP0006B1Z4").setFontSize(8.0f).setBold())
                    .setTextAlignment(
                        TextAlignment.CENTER
                    )
            )
            //table1.addCell(Cell().add(Paragraph("")))

            document.add(table1)



            document.add(table1)
            document.close()

        } catch (e: Exception) {

        }

    }
}