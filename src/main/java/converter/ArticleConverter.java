package converter;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

import org.docx4j.dml.CTTableCellBorderStyle;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;


/**
 * Created by vova on 13.08.16.
 */
public class ArticleConverter {

    /*
    public void convert1() {
    try{

        // Document loading (required)
        WordprocessingMLPackage wordMLPackage;

        wordMLPackage = Docx4J.load(new File("/home/vova/Project BZ/trash/docx/a.docx"));

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

        htmlSettings.setImageDirPath("/home/vova/Project BZ/trash/docx/target/imgs");
        htmlSettings.setImageTargetUri("/home/vova/Project BZ/trash/docx/target/imgs1");
        htmlSettings.setWmlPackage(wordMLPackage);



        // output to an OutputStream.
        OutputStream os;
        os = new FileOutputStream("/home/vova/Project BZ/trash/docx/target/a.html");

        // If you want XHTML output
        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        //Don't care what type of exporter you use
		Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_NONE);
        //Prefer the exporter, that uses a xsl transformation
 //       Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        //Prefer the exporter, that doesn't use a xsl transformation (= uses a visitor)
        //Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_NONXSL);

        }catch(Exception e) {
            e.printStackTrace();

        }

    }*/

    public void convert(){
        try {
            InputStream s = new FileInputStream("/home/vova/Project BZ/trash/docx/d.docx");
            XWPFDocument document = new XWPFDocument(s);
            File outFile = new File("/home/vova/Project BZ/trash/docx/target/d.html");
            outFile.getParentFile().mkdirs();
            List<XWPFTable> tables = document.getTables();

            for(XWPFTable t : tables){

                CTTblPr tblpro = t.getCTTbl().addNewTblPr();

                CTTblBorders borders = tblpro.addNewTblBorders();
                borders.addNewBottom().setVal(STBorder.SINGLE);
                CTBorder b = borders.getBottom();

                b.setSz(BigInteger.valueOf(8));
                borders.addNewLeft().setVal(STBorder.SINGLE);
                b = borders.getLeft();
                b.setSz(BigInteger.valueOf(8));
                borders.addNewRight().setVal(STBorder.SINGLE);
                b = borders.getRight();
                b.setSz(BigInteger.valueOf(8));
                borders.addNewTop().setVal(STBorder.SINGLE);
                b = borders.getTop();
                b.setSz(BigInteger.valueOf(8));

                borders.addNewInsideV().setVal(STBorder.SINGLE);

                tblpro.setTblBorders(borders);
                t.getCTTbl().setTblPr(tblpro);
            }


            OutputStream out = new FileOutputStream(outFile);
            XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("/home/vova/Project BZ/trash/docx/target")));
            options.setExtractor(new FileImageExtractor(new File("/home/vova/Project BZ/trash/docx/target")));

            XHTMLConverter.getInstance().convert(document,out,options);
        }
        catch (  Throwable e) {
            e.printStackTrace();
        }

    }
    /*
    public void convert(){
        try {
            File docFile = new File("/home/vova/Project BZ/trash/docx/c.docx");
            File file = new File("/home/vova/Project BZ/trash/docx/target/c.htm");
            FileInputStream finStream = new FileInputStream(docFile.getAbsolutePath());
            HWPFDocument doc=new HWPFDocument(finStream);
            WordExtractor wordExtract = new WordExtractor(doc);
            Document newDocument = DocumentBuilderFactory.newInstance() .newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(newDocument) ;
            wordToHtmlConverter.processDocument(doc);

            StringWriter stringWriter = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();

            transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
            transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
            transformer.setOutputProperty( OutputKeys.METHOD, "html" );
            transformer.transform(new DOMSource(wordToHtmlConverter.getDocument()), new StreamResult( stringWriter ) );

            String html = stringWriter.toString();

            FileOutputStream fos;
            DataOutputStream dos;
            System.out.println(html);
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));

                out.write(html);
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (  Throwable e) {
            e.printStackTrace();
        }

    }*/
}
