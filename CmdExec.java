import java.io.*;
public class CmdExec {
    String command = "";

    public CmdExec(String cmdline) {
        this.command = cmdline;
    }

    public int exec() {
        int status = 0;
        try {
            String line;
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
            BufferedReader err =
                    new BufferedReader
                            (new InputStreamReader(p.getErrorStream()));
            while ((line = err.readLine()) != null) {
                System.out.println(line);
            }
            err.close();

            status = p.exitValue();

        } catch (Exception err) {
            err.printStackTrace();
            status = 1;
        }
        return status;
    }
}