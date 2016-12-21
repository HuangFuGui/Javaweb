package HighLightSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

class Index {
    // 建立索引
    public void index() {
        IndexWriter indexWriter = null;
        try {
            // 创建Directory
            Directory directory = FSDirectory.open(
            		FileSystems.getDefault().getPath("C:/Users/Administrator/Desktop/LuceneIndex"));
            // 创建IndexWriter
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteAll();//清除以前的index
            
            //要搜索的File路径,路径下全是后缀名为.txt的文档
            File dFile = new File("C:/Users/Administrator/Desktop/File");
            File[] files = dFile.listFiles();
            for (File file : files) {
            	//读取文档中的内容至content字符串中
            	 BufferedReader in = new BufferedReader(new FileReader(file)); 
                 String s;
                 String content = "";
                 while((s = in.readLine()) != null){//readLine()方法每次读取文件的一行直到遇到换行符
                     content += s+"\n";
                 }
                 in.close();
                 
                 File outputFile = new File("C:/Users/Administrator/Desktop/LuceneOutput/"+file.getName());
                 if(!outputFile.exists()){
                	 System.out.print("创建文件:"+file.getName());
                	 outputFile.createNewFile();
                 }
                 
                 //将字符串的内容写入输出文档中
                 BufferedWriter bw = new BufferedWriter(
                         new OutputStreamWriter(
                                 new FileOutputStream("C:/Users/Administrator/Desktop/LuceneOutput/"+file.getName())));
                 bw.write(content);
                 bw.close();

                // 创建Document对象
                Document document = new Document();
                // 为Document添加Field           
                document.add(new Field("content",content, TextField.TYPE_STORED));
                document.add(new Field("filename", file.getName(), TextField.TYPE_STORED));
                document.add(new Field("filepath", file.getAbsolutePath(), TextField.TYPE_STORED));

                // 通过IndexWriter添加文档到索引中
                indexWriter.addDocument(document);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (indexWriter != null) {
                    indexWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


class Search {  
    public void search(String keyWord) {  
        DirectoryReader directoryReader = null;  
        try {  
            // 创建Directory  
            Directory directory = FSDirectory.open(
            		FileSystems.getDefault().getPath("C:/Users/Administrator/Desktop/LuceneIndex"));
            // 创建IndexReader  
            directoryReader = DirectoryReader.open(directory);  
            // 根据IndexReader创建IndexSearch  
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);  

            // 创建搜索的Query  
            Analyzer analyzer = new StandardAnalyzer();  
            // 创建parser来确定要搜索文件的内容，第一个参数为搜索的域  
            QueryParser queryParser = new QueryParser("content", analyzer);  
            // 创建Query表示搜索域为content包含UIMA的文档  
            Query query = queryParser.parse(keyWord);  

            // 根据searcher搜索并且返回TopDocs  
            TopDocs topDocs = indexSearcher.search(query, 1000);  
            System.out.println("查找到的文档总共有："+topDocs.totalHits);

            // 根据TopDocs获取ScoreDoc对象  
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;  
            for (ScoreDoc scoreDoc : scoreDocs) {  

                // 根据searcher和ScoreDoc对象获取具体的Document对象  
                Document document = indexSearcher.doc(scoreDoc.doc);  

                // 根据Document对象获取需要的值  
                System.out.println(document.get("filename") + " " + document.get("filepath")); 
                System.out.println(document.get("content"));//得到搜索结果
                
                String value = toHighlighter(query,document,"content",analyzer);
                System.out.println(value);//得到搜索结果，带有关键词高亮
            }  

        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (directoryReader != null) {  
                    directoryReader.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    //高亮
    public String toHighlighter(Query query,Document doc,String field,Analyzer analyzer){
        try {
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));
            TokenStream tokenStream1 = analyzer.tokenStream("text",new StringReader(doc.get(field)));
            String highlighterStr = highlighter.getBestFragment(tokenStream1, doc.get(field));
            return highlighterStr == null ? doc.get(field):highlighterStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
            return null;
        }
    }
}  

public class HighLightSearch {
	//测试
    public static void main(String[] args) {
        Index newIndex = new Index();
        newIndex.index();
        Search newSearch = new Search();
        newSearch.search("今天总结Lucene");
    }
}