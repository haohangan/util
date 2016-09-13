package path;
/**
 * finds resources in all the jar files in the given directory.
 *
 * @author Satyendra Gurjar
 */
import java.io.*;

import java.util.*;


public class jwhich {
	static final String version = "@(#) $Id$";

	static final String usage = version + "\n" +
"finds resources in all the jar files in the given directory"+ "\n" +
"\nusage: java -jar jwhich.jar [-j jarcmd] [-k] [-n] [-q] path resource"+ "\n" +
"\noptions:"+ "\n" +
"	-j path to jar executable"+ "\n" +
"	-k keep searching for more after found a match"+ "\n" +
"	-n do not search subdirs (non-recursive)"+ "\n" +
"	-q quite"+ "\n" +
"\nexample:"+ "\n" +
"	java -jar jwhich.jar . Cipher"+ "\n" +
"	finds all the jar files that has at least one class whoes name contains Cipher"+ "\n" +
"\n" +
"	java -jar jwhich.jar -j c:/java/bin/jar c:/temp Cipher"+ "\n" +
"	use c:/java/bin/jar command, find in c:/temp dir"+ "\n";

	static final PrintStream log = System.err;

    @SuppressWarnings("rawtypes")
	public static void main(String[] argv) {
		Object[] objs = handle_cmd_line(argv);
		HashMap opts = (HashMap)objs[0];
		ArrayList args = (ArrayList)objs[1];
		try{
			jwhich jw = new jwhich(opts, args, System.out);
			jw.find();
		}catch(_error e){
			log.println(e.getMessage());
			log.println(usage);
			System.exit(1);
		}catch(IOException e){
			e.printStackTrace(log);
		}
	}

    static final String JAR_CMD = "jar";

    @SuppressWarnings({ "rawtypes", "unchecked" })
	static Object[] handle_cmd_line(String[] argv) {
        HashMap opts = new HashMap();
        ArrayList args = new ArrayList();

        for (int i = 0; i < argv.length; i++) {
            String a = argv[i];

            switch (a.charAt(0)) {
            case '-':

                if (a.length() > 1) {
                    String[] nv = a.substring(1).split("="); // leave -
                    String n = (nv.length >= 1) ? nv[0] : null;
                    String v = (nv.length >= 2) ? nv[1] : "";

                    if (n != null) {
                        opts.put(n, v);
                    }
                }

                break;

            default:
                args.add(a);
            }
        }

        return new Object[] { opts, args };
    }

	private File	basedir;
    private String	resource_name;
    private String	jar_cmd;
    private boolean verbose;
    private boolean recursive;
    private boolean keep_searching;

	private PrintStream out;

	private final FileFilter jar_file_filter = new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory() ||
                pathname.getName().endsWith(".jar") ||
                pathname.getName().endsWith(".zip");
            }
        };

    @SuppressWarnings("rawtypes")
	jwhich(HashMap opts, ArrayList args, PrintStream out) {

		if (args.size() < 2) {
            throw new _error("required parameter is missing: dir or resource");
        }

        this.basedir = new File((String) args.get(0));

        if (!this.basedir.exists()) {
            throw new _error("dir does not exists: " + basedir);
        }

        if (!this.basedir.isDirectory()) {
            throw new _error("\"" + basedir + "\" is not a directory");
        }

        this.resource_name = (String) args.get(1);
        this.resource_name = this.resource_name.replace('.', '/');

        this.jar_cmd = (String) opts.get("j");

        if ((this.jar_cmd == null) || (this.jar_cmd.length() == 0)) {
            this.jar_cmd = JAR_CMD;
        }

        this.verbose        = !  opts.containsKey("q");
        this.recursive      = !  opts.containsKey("n");
        this.keep_searching = opts.containsKey("k");
		this.out			= out;
    }

    void find() throws IOException {
		if(this.verbose){
			log.println("looking for " + this.resource_name 
				+ " in " + this.basedir );
			log.println("recursive="+this.recursive+", keep_searching="+this.keep_searching);
		}
        _find(this.resource_name, this.basedir);
    }

    boolean _find(String resource_name, File basedir) throws IOException {
        File[] children = basedir.listFiles(this.jar_file_filter);

        if (children == null) {
            return false;
        }

        for (int i = 0; i < children.length; i++) {
            File f = children[i];

            if (f.isDirectory()) {
                if (this.recursive && _find(resource_name, f)) {
                    if (!this.keep_searching) {
                        return true;
                    }
                }
            } else {
                if (grep(resource_name, f)) {
                    if (!this.keep_searching) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean grep(String resource_name, File f) throws IOException {
		if(this.verbose) log.println(f);
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(jar_cmd + " -tf " + f.getAbsolutePath());
        InputStream proc_out = proc.getInputStream();
        BufferedReader reader = null;
        boolean found = false;

        try {
            reader = new BufferedReader(new InputStreamReader(proc_out));

            for (String line = reader.readLine(); line != null;
                    line = reader.readLine()) {
                if (line.indexOf(resource_name) != -1) {
                    found = true;
                    out.println(line.trim() + "\t" + f);
                }
            }

            return found;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    @SuppressWarnings("serial")
	static class _error extends RuntimeException {
        _error(String msg) {
            super("ERROR: " + msg);
        }
    }
}
