package algorithm.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang3.time.StopWatch;

public class FileSize {
private static final ForkJoinPool pool = new ForkJoinPool();
   
    static class FileSizeFinder extends RecursiveTask<Long>{
		private static final long serialVersionUID = 1L;
		final File file;
		
		public FileSizeFinder(final File file) {
			this.file = file;
		}

		@Override
		protected Long compute() {
			long size = 0;
			if(file.isFile()){
				size = file.length();
			}else{
				final File[] fs = file.listFiles();
				if(fs!=null){
					List<ForkJoinTask<Long>>  list = new ArrayList<ForkJoinTask<Long>>();
					for(final File f:fs){
						if(f.isFile()){
							size +=f.length();
						}else{
							list.add(new FileSizeFinder(f));
						}
					}
					
					for(final ForkJoinTask<Long> fjt:invokeAll(list)){
						 size +=fjt.join();
					}
					
				}
			}
			return size;
		}
    	
    }
    
    static StopWatch sw = new StopWatch();
    
    public static void main(String[] args) {
    	sw.start();
		long sum = pool.invoke(new FileSizeFinder(new File(SizeCounter.path)));
		System.out.println(sum);
		sw.stop();
		System.out.println(sw.getTime());
		
	}
}
