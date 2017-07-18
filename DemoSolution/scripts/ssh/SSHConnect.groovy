package ssh;

import com.fluidops.iwb.annotation.CallableFromWidget;
import com.fluidops.util.Exec;
import com.fluidops.util.Exec.ExecResult;
import com.fluidops.util.SSH;

/**
 * wrapper class which enables SSH from the wiki
 */
public class SSHConnect
{
	@CallableFromWidget
	public static String exec( String host, String user, String password, String command )
	{
		SSH ssh = new SSH();
		ssh.login(host, user, password);
		ExecResult res = ssh.exec(command);
		ssh.logout();
		return res.out;
	}
}
