package utils

import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.UnitValue


class PdfHandler() {
    fun createPdfTable(dict: String, headers: List<String>, cells: List<String>) {
        val pdfDoc = PdfDocument(PdfWriter(dict))
        val font = PdfFontFactory.createFont("font.ttf", PdfEncodings.IDENTITY_H)
        val doc = Document(pdfDoc)
        val table = createTable(headers, cells, font)
        doc.add(table)
        doc.close()
    }

    private fun createTable(headers: List<String>, cells: List<String>, font: PdfFont): Table {
        val table = Table(UnitValue.createPercentArray(headers.size))
        table.setFont(font).setFontSize(5F)
        headers.forEach { headerText ->
            val cell = createCell(headerText, 0.1f, 1f)
            table.addCell(cell)
        }
        cells.forEach { cellText ->
            val cell = createCell(cellText)
            table.addCell(cell)
        }
        return table
    }

    private fun createCell(text: String, strokeWidth: Float = 0.1f, borderWidth: Float = 1f): Cell {
        val textCell = Text(text)
            .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
            .setStrokeWidth(strokeWidth)
        val paragraph = Paragraph(textCell)
        paragraph.setPaddingLeft(2f)
        paragraph.setPaddingRight(3f)
        val cell = Cell()
        cell.setBorder(SolidBorder(borderWidth))
        cell.add(paragraph)
        return cell
    }
}