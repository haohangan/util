package lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public enum LuceneApi {

	INSTANCE;

	private String docsPath = "D://lucene//";

	private Directory directory; // 索引库目录
	private Analyzer analyzer; // 分词器

	private IndexWriter writer;
	private IndexReader reader;

	public void init() throws IOException {
		Path docDir = check();
		directory = FSDirectory.open(docDir);
		analyzer = new StandardAnalyzer();
		if (writer == null) {
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			writer = new IndexWriter(directory, iwc);
		}
		hoke();
	}

	private Path check() {
		if (docsPath == null) {
			System.err.println("Usage: docsPath is null");
			System.exit(1);
		}
		Path docDir = Paths.get(docsPath);
		if (!Files.isReadable(docDir)) {
			System.out.println("Document directory '" + docDir.toAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}
		return docDir;
	}

	private void hoke() {
		// 指定一段代码，会在JVM退出之前执行。
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					close();
					System.out.println("=== 已经关闭 IndexWriter ===");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}


	public void write(String name, long id) {
		Document doc = doc(name,id);
		try {
			writer.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Document doc(String name, long id) {
		 Document doc = new Document();
		 Field nameField = new StringField("name", name, Field.Store.YES);
		 doc.add(nameField); 
		 doc.add(new SortedNumericDocValuesField("id", id));
		return doc;
	}
	
	
	public void read(String name) throws IOException, ParseException{
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(docsPath)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    QueryParser parser = new QueryParser("name", analyzer);
	    Query query = parser.parse(name);
	    
	    int count = searcher.count(query);
	    TopDocs results = searcher.search(query, 5);
	    System.out.println("count:"+count);
	    System.out.println("results:"+results.totalHits);
	    System.out.println("results.scoreDocs.length:"+results.scoreDocs.length);
	    for(ScoreDoc sd:results.scoreDocs){
	    	Document doc = searcher.doc(sd.doc);
	    	System.out.println(doc.get("name")+"\t"+doc.getField("id"));
	    }
	}

	

	public void close() {
		if(reader!=null){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(writer!=null){
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		analyzer.close();
	}

	public static void main(String[] args) throws IOException, ParseException {
		LuceneApi la = LuceneApi.INSTANCE;
		la.init();
		
		
		
//	write(la);
		
			la.read("EFGH");

	}
	
	static void write(LuceneApi la){
		la.write("ABCD", 1234L);
		la.write("EFGH", 5678L);
		la.write("IJKL", 9101112L);
	}
}
