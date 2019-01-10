package pl.edu.wat.wcy.invoice.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import pl.edu.wat.wcy.invoice.model.Invoice;
import pl.edu.wat.wcy.invoice.model.InvoicePosition;
import pl.edu.wat.wcy.invoice.model.InvoiceVat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PdfGenerator {

    public ByteArrayInputStream generate(Invoice invoice) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            BaseFont helvetica_bold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font helvetica9 = new Font(helvetica,9);
            Font helvetica_bold18 = new Font(helvetica_bold,18);
            Font helvetica_bold14 = new Font(helvetica_bold,14);
            Font helvetica_bold12 = new Font(helvetica_bold,12);
            Font helvetica14 = new Font(helvetica,14);
            Font helvetica12 = new Font(helvetica,12);


            PdfPTable mainTable = new PdfPTable(1);
            mainTable.setWidthPercentage(100);
            mainTable.setWidths(new int[]{1});

            PdfPTable headTable = new PdfPTable(3);
            headTable.setWidths(new int[]{1, 1, 1});

            PdfPCell companySealCell = new PdfPCell(new Phrase("pieczęć firmy", helvetica9));
            companySealCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            companySealCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            companySealCell.setMinimumHeight(120);
            headTable.addCell(companySealCell);

            PdfPCell invoiceTitleCell = new PdfPCell(new Phrase("Faktura", helvetica_bold18));
            invoiceTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceTitleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            invoiceTitleCell.setMinimumHeight(120);
            headTable.addCell(invoiceTitleCell);

            PdfPTable datesTable = new PdfPTable(1);

            PdfPCell invoiceNumberCell = new PdfPCell(new Phrase("Nr: " + Chunk.NEWLINE  + invoice.getInvoiceNumber(), helvetica14));
            invoiceNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceNumberCell.setMinimumHeight(40);
            datesTable.addCell(invoiceNumberCell);

            PdfPCell createDateCell = new PdfPCell(new Phrase("Data wystawienia: " + Chunk.NEWLINE + invoice.getCreateDate().format(df), helvetica14));
            createDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            createDateCell.setMinimumHeight(40);
            datesTable.addCell(createDateCell);

            PdfPCell saleDateCell = new PdfPCell(new Phrase("Data sprzedaży: " + Chunk.NEWLINE + invoice.getSaleDate().format(df), helvetica14));
            saleDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            saleDateCell.setMinimumHeight(40);
            datesTable.addCell(saleDateCell);

            headTable.addCell(datesTable);

            PdfPTable informationTable = new PdfPTable(2);

            PdfPCell userCell = new PdfPCell(new Phrase(
                    "Sprzedawca: " + invoice.getUser().getName() + Chunk.NEWLINE +
                    "Adres: " + invoice.getUser().getStreet() + ", " + invoice.getUser().getPostcode() + " " + invoice.getUser().getCity() + Chunk.NEWLINE +
                    "NIP: " + invoice.getUser().getNip() + Chunk.NEWLINE +
                    "Numer telefonu: " + invoice.getUser().getPhone() + Chunk.NEWLINE
                    , helvetica12));
            informationTable.addCell(userCell);

            PdfPCell clientCell = new PdfPCell(new Phrase(
                    "Nabywca: " + invoice.getClient().getName() + Chunk.NEWLINE +
                            "Adres: " + invoice.getClient().getStreet() + ", " + invoice.getClient().getPostcode() + " " + invoice.getClient().getCity() + Chunk.NEWLINE +
                            "NIP: " + invoice.getClient().getNip() + Chunk.NEWLINE
            , helvetica12));
            informationTable.addCell(clientCell);

            PdfPCell paymentTypeCell = new PdfPCell(new Phrase("Sposób płatności: " + invoice.getPaymentType().getName(), helvetica12));
            informationTable.addCell(paymentTypeCell);

            PdfPCell paymentDateCell = new PdfPCell(new Phrase("Termin płatności: " + invoice.getPaymentDate().format(df), helvetica12));
            informationTable.addCell(paymentDateCell);


            PdfPTable positionsTable = new PdfPTable(9);
            positionsTable.setWidths(new int[]{1, 3, 2, 1, 2, 2, 2, 2, 2});

            PdfPCell headerPositionCell;

            headerPositionCell = new PdfPCell(new Phrase("Lp.", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Nazwa", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Ilość", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Jm", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Cena netto", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Wartość netto", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Stawka VAT", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Kwota VAT", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Wartość brutto", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            int idx = 1;
            for(InvoicePosition position : invoice.getInvoicePositions()) {
                PdfPCell positionCell;

                positionCell = new PdfPCell(new Phrase(""+idx, helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getName(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getQuantity().toString(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getUnit(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getNetPrice().toString(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getNetValue().toString(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getVatType().getName(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getVatValue().toString(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                positionsTable.addCell(positionCell);

                positionCell = new PdfPCell(new Phrase(position.getGrossValue().toString(), helvetica12));
                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                positionsTable.addCell(positionCell);

                idx++;
            }

            PdfPTable summaryMainTable = new PdfPTable(2);
            summaryMainTable.setWidths(new int[]{7, 10});

            PdfPTable summaryTable = new PdfPTable(5);
            summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.setWidths(new int[]{1, 1, 1, 1, 1});

            PdfPCell summaryAllCell = new PdfPCell(new Phrase("Razem:", helvetica_bold12));
            summaryAllCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryAllCell);

            PdfPCell summaryNetValueCell = new PdfPCell(new Phrase(invoice.getNetAmount().toString(), helvetica_bold12));
            summaryNetValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryNetValueCell);

            PdfPCell summaryVatTypeCell = new PdfPCell(new Phrase("X", helvetica_bold12));
            summaryVatTypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryTable.addCell(summaryVatTypeCell);

            PdfPCell summaryVatValueCell = new PdfPCell(new Phrase(invoice.getVatAmount().toString(), helvetica_bold12));
            summaryVatValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryVatValueCell);

            PdfPCell summaryGrossValueCell = new PdfPCell(new Phrase(invoice.getGrossAmount().toString(), helvetica_bold12));
            summaryGrossValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryGrossValueCell);

            PdfPCell summaryIncludeCell = new PdfPCell(new Phrase("W tym:", helvetica_bold12));
            summaryIncludeCell.setRowspan(invoice.getInvoiceVats().size());
            summaryIncludeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryIncludeCell.setVerticalAlignment(Element.ALIGN_TOP);
            summaryTable.addCell(summaryIncludeCell);

            for(InvoiceVat vat : invoice.getInvoiceVats()) {
                PdfPCell vatCell;

                vatCell = new PdfPCell(new Phrase(vat.getNetValue().toString(), helvetica12));
                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                summaryTable.addCell(vatCell);

                vatCell = new PdfPCell(new Phrase(vat.getVatType().getName(), helvetica12));
                vatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                summaryTable.addCell(vatCell);

                vatCell = new PdfPCell(new Phrase(vat.getVatValue().toString(), helvetica12));
                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                summaryTable.addCell(vatCell);

                vatCell = new PdfPCell(new Phrase(vat.getGrossValue().toString(), helvetica12));
                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                summaryTable.addCell(vatCell);
            }

            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            summaryMainTable.addCell(emptyCell);
            summaryMainTable.addCell(summaryTable);

            PdfPCell toPayCell = new PdfPCell(new Phrase("Razem do zapłaty: " + invoice.getGrossAmount() + " PLN", helvetica_bold14));

            PdfPTable signatureTable = new PdfPTable(2);

            PdfPCell clientSignatureCell = new PdfPCell(new Phrase("Imię i nazwisko osoby upoważnionej do odebrania dokumentu", helvetica9));
            clientSignatureCell.setPaddingLeft(10);
            clientSignatureCell.setPaddingRight(10);
            clientSignatureCell.setMinimumHeight(100);
            clientSignatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            clientSignatureCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            signatureTable.addCell(clientSignatureCell);

            PdfPCell userSignatureCell = new PdfPCell(new Phrase("Imię i nazwisko osoby upoważnionej do wystawienia dokumentu", helvetica9));
            clientSignatureCell.setPaddingLeft(10);
            clientSignatureCell.setPaddingRight(10);
            userSignatureCell.setMinimumHeight(100);
            userSignatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            userSignatureCell.setVerticalAlignment(Element.ALIGN_BOTTOM);

            signatureTable.addCell(userSignatureCell);


            mainTable.addCell(headTable);
            mainTable.addCell(informationTable);
            mainTable.addCell(positionsTable);
            mainTable.addCell(summaryMainTable);
            mainTable.addCell(toPayCell);
            mainTable.addCell(signatureTable);


            PdfWriter.getInstance(document, out);
            document.open();
            document.add(mainTable);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

