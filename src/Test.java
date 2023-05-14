import com.spire.doc.*;
import com.spire.doc.documents.Paragraph;

import com.spire.doc.fields.DocPicture;

public class Test {
    public static void main(String[] args) {
        //加载文档
        Document doc = new Document();
        doc.loadFromFile("D:\\test.docx");
        //获取第二段
        Paragraph para = doc.getSections().get(0).getParagraphs().get(0);
        //添加图片，并设置图片高、宽
        DocPicture picture = para.appendPicture("D:\\233\\233\\测试.jpg");
        picture.setHeight(200);
        picture.setWidth(300);

        //保存文档
        doc.saveToFile("D:\\AddPicture.docx",FileFormat.Docx_2010);
        doc.dispose();
    }

}