/*****************************************************************************
 * Class name: ChevelleHelper
 * Description: To store the authentication for the different database, edge nodes and other servers, 
 *              for the different developing, testing and production environments (DEV, TST, CRT and PRD)
 * Author: D. Grebenisan
 * History: April ... - Created
 *          May   ... - Added the RSA key for ........ EdgeNodes
 *****************************************************************************/

package chevelle;

public class ChevelleHelper 
{
    // private static String CHEVELLE_ENV = "PRD";
    private String CHEVELLE_ENV = "PRD";
    
    private static final String dev_SomeDB_username = "..............";
    private static final String dev_SomeDB_password = ".......................";   
            
    private static final String tst_SomeDB_username = "..............";
    private static final String tst_SomeDB_password = ".......................";      
    
    private static final String crt_SomeDB_username = "..............";
    private static final String crt_SomeDB_password = ".......................";      
    
    private static final String prd_SomeDB_username = "..............";
    private static final String prd_SomeDB_password = ".......................";                   
    
    private static final String dev_EdgenodeRSAKey = "-----BEGIN RSA PRIVATE KEY-----\n"
        + ".............\n"
        + "..............\n"
        + "-----END RSA PRIVATE KEY-----";

    private static final String tst_EdgenodeRSAKey = "-----BEGIN RSA PRIVATE KEY-----\n"
        + "...............\n"
        + "....................\n"
        + ".........................\n"
        + "-----END RSA PRIVATE KEY-----";    
    
    private static final String crt_EdgenodeRSAKey = "-----BEGIN RSA PRIVATE KEY-----\n"
        + ".....................\n"
        + ".......................\n"
        + ".....................\n"
        + "-----END RSA PRIVATE KEY-----";        
    
    private static final String prd_EdgenodeRSAKey = "-----BEGIN RSA PRIVATE KEY-----\n"
        + ".....................\n"
        + "........................\n"
        + "..........................\n"
        + "-----END RSA PRIVATE KEY-----";        
    
    
    private static final String dev_auth_code = "...........................";

    
    private static final String tst_auth_code = "........................";

    private static final String crt_auth_code = "............................";

    
    private static final String prd_auth_code = "...........................";
    
    
    public void setEnvironment(String env)
    {
        if(env.equals("DEV"))
            CHEVELLE_ENV = "DEV";
        else if(env.equals("TST"))
            CHEVELLE_ENV = "TST";
        else if(env.equals("CRT"))
            CHEVELLE_ENV = "CRT";
        else if(env.equals("PRD"))
            CHEVELLE_ENV = "PRD";        
        else
            CHEVELLE_ENV = "PRD"; // the default
    }
    
    public String getEnvironment()
    {
        return CHEVELLE_ENV;
    }    
    
    public String getSomeDBUsername()
    {
        String cur_SomeDBUsername = "";
        
        if(CHEVELLE_ENV.equals("DEV"))
            cur_SomeDBUsername = dev_SomeDB_username;
        else if (CHEVELLE_ENV.equals("TST"))
            cur_SomeDBUsername = tst_SomeDB_username;
        else if (CHEVELLE_ENV.equals("CRT"))
            cur_SomeDBUsername = crt_SomeDB_username;        
        else if (CHEVELLE_ENV.equals("PRD"))
            cur_SomeDBUsername = prd_SomeDB_username;                
        else
            cur_SomeDBUsername = prd_SomeDB_username;
        
        return cur_SomeDBUsername;        
    }
    
    public String getSomeDBPassword()
    {
        String cur_SomeDBPassword = "";
        
        if(CHEVELLE_ENV.equals("DEV"))
            cur_SomeDBPassword = dev_SomeDB_password;
        else if (CHEVELLE_ENV.equals("TST"))
            cur_SomeDBPassword = tst_SomeDB_password;
        else if (CHEVELLE_ENV.equals("CRT"))
            cur_SomeDBPassword = crt_SomeDB_password;        
        else if (CHEVELLE_ENV.equals("PRD"))
            cur_SomeDBPassword = prd_SomeDB_password;                
        else
            cur_SomeDBPassword = prd_SomeDB_password;
        
        return cur_SomeDBPassword;        
    }    
        
    public String getEdgenodeUsername()
    {
        String cur_EdgenodeUsername = "";
        
        if(CHEVELLE_ENV.equals("DEV"))
            cur_EdgenodeUsername = ".............";
        else if (CHEVELLE_ENV.equals("TST"))
            cur_EdgenodeUsername = "...............";
        else if (CHEVELLE_ENV.equals("CRT"))
            cur_EdgenodeUsername = "................";        
        else if (CHEVELLE_ENV.equals("PRD"))
            cur_EdgenodeUsername = "................";                
        else
            cur_EdgenodeUsername = "..............";        
        
        return cur_EdgenodeUsername;
    }
    
    public String getEdgenodeRSAKey()
    {
        String cur_EdgenodeRSAKey = "";

        if(CHEVELLE_ENV.equals("DEV"))        
            cur_EdgenodeRSAKey = dev_EdgenodeRSAKey;
        
        else if (CHEVELLE_ENV.equals("TST"))    
            cur_EdgenodeRSAKey = tst_EdgenodeRSAKey;
        
        else if (CHEVELLE_ENV.equals("CRT"))    
            cur_EdgenodeRSAKey = crt_EdgenodeRSAKey;

        else if (CHEVELLE_ENV.equals("PRD"))
            cur_EdgenodeRSAKey = prd_EdgenodeRSAKey;
        
        else
            cur_EdgenodeRSAKey = prd_EdgenodeRSAKey;
        
        return cur_EdgenodeRSAKey;
    }
    
    public String getAuthCode(String cur_sel_env)
    {
        String cur_auth_code = "";

        if(cur_sel_env.equals("DEV"))        
            cur_auth_code = dev_auth_code;
        
        else if (cur_sel_env.equals("TST"))    
            cur_auth_code = tst_auth_code;
        
        else if (cur_sel_env.equals("CRT"))    
            cur_auth_code = crt_auth_code;

        else if (cur_sel_env.equals("PRD"))
            cur_auth_code = prd_auth_code;
        
        else
            cur_auth_code = prd_auth_code;
        
        return cur_auth_code;
    }
    
}
