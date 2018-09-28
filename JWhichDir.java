import java.io.*;
import java.net.URL;

/**
 * Finds the class in more than one jars in a given classpath
 * @author <a href="mailto:kalpehsoni@gmail.com">Kalpeshkumar Soni</a>
 * 18 August 2004
 * @version 1.0
 *
 * @see    JWhich
 */
public class JWhichDir {
    static String JWHICH_CP = ".;C:\\;D:\\;D:\\Apps\\InPATH;"; //classpath where JWhich.class is there
    String parentDir = null;
    String className = null;

    public String getParentDir() {
        return parentDir;
    }

    public void setParentDir(String parentDir) {
        this.parentDir = parentDir;
    }

    public static void main(String args[])
            throws Exception {
        if (args.length < 2) {
            System.out.println("Usage:");
            System.out.println("java JWhichDir <class-name> <dir-of-element>");
            System.out.println("or");
            System.out.println("java -Dverbose JWhichDir <class-name> <dir-of-element>");
            System.out.println("Put the compiled JWhich classes in c:\\ or / only!!");
            return;
        }
        JWhichDir jwhichdir = new JWhichDir();
        jwhichdir.className = args[0];
        jwhichdir.parentDir = args[1];

        System.out.println("*** Class " + args[0] + " found in ... *** \n");
        jwhichdir.recurse();

    }

    public void recurse()
    throws Exception
    {
        recurseFile(new File(parentDir));
	}

    public void recurseFile(File element)
    throws Exception
    {
        if(!element.isDirectory())
        {
            runCmd(element); return;
        }

        File[] elements = getNextCPElement(element);
        for (int i = 0; i < elements.length; i++)
        {
            recurseFile(elements[i]);
		}
	}
	public void runCmd(File element)
    throws IOException
	{
		String command = "java -classpath \""+ JWHICH_CP + element.getCanonicalPath() + "\" JWhichHelper " + this.className;
		if (System.getProperty("verbose") != null)
			System.out.println("Running >" + command);
		CmdExec cmdExec = new CmdExec(command);
		int status = cmdExec.exec();
		if (status == 0) {
			System.out.println("*** " + element.getCanonicalPath());
		}
	}

    public File[] getNextCPElement(File dirFile)
            throws Exception {
        if (!dirFile.exists() && !dirFile.isDirectory())
            throw new Exception(dirFile.getCanonicalPath() + " Not a directory");

        FilenameFilter fnf = new FilenameFilter() {
            public boolean accept(File parent, String name) {
                if (name.endsWith(".jar"))
                    return true;
                File dir = new File(parent,name);
                if (dir.isDirectory())
                    return true;
                return false;
            }
        };

        File[] ret = dirFile.listFiles(fnf);
        return ret;
    }

}



