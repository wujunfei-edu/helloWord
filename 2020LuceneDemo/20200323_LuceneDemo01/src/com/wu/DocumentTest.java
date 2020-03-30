package com.wu;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;

/**
 * 索引库的增删改操作
 */
public class DocumentTest {
    private IndexWriter indexWriter;
    @Before
    public void init() throws IOException {
        indexWriter = new IndexWriter(FSDirectory.open(new File("D:\\sram_java\\微服务Spring date\\05_Lucene\\index").toPath()),new IndexWriterConfig(new IKAnalyzer()));
    }

    @Test   //添加
    public void addDocument() throws IOException {
        Document document = new Document();

        document.add(new TextField("fileName","笑傲江湖", Field.Store.YES));
        document.add(new TextField("fileContent","金庸武侠小说代表作之一", Field.Store.YES));
        document.add(new StoredField("filePath","D:\\aaa"));

        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test   //删除
    public void deleteDocument() throws IOException {
        //indexWriter.deleteAll();      //删除所有索引库
        indexWriter.deleteDocuments(new Term("fileName", "江湖"));//删除文件名包含“江湖”的文件
        indexWriter.close();
    }

    @Test   //修改
    public void updateDocument() throws IOException {
        Document document = new Document();
        //文档修改的内容
        document.add(new TextField("fileName","神雕侠侣", Field.Store.YES));

        //对索引库中的文档先进行删除，在进行修改       将文件名包含web的文件名修改为神雕侠侣
        indexWriter.updateDocument(new Term("fileName","web"),document);

        indexWriter.close();
    }
}
