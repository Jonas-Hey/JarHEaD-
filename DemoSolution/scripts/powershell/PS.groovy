package powershell;

import com.fluidops.iwb.annotation.CallableFromWidget;
import com.fluidops.util.Exec;
import com.fluidops.util.Exec.ExecResult;

/**
 * wrapper class which allows calling powershell scripts from the wiki
 */
public class PS
{
    @CallableFromWidget
    public static String powershell()
    {
        // calls this PS script:
        // Write-Host 'Hello World!'
        ExecResult res = Exec.run((String[])["powershell", "-inputformat", "none", "-File", "scripts/powershell/hi.ps1"], null, 0L);
        if (res.exit == 0 && res.err.equals(""))
            return res.out;
        else
            return "command failed with code " + res.exit + ": " + res.err;
    }
    
    @CallableFromWidget
    public static String powershellWithUserInput(String firstname, String lastname)
    {
        // calls this PS script:
        // Write-Host 'Hello '+firstname+' '+lastname
        ExecResult res = Exec.run((String[])["powershell", "-inputformat", "none", "-File", "scripts/powershell/scriptWithUserInput.ps1", firstname, lastname], null, 0L);
        if (res.exit == 0 && res.err.equals(""))
            return res.out;
        else
            return "command failed with code " + res.exit + ": " + res.err;
    }
    
}
