/*****************************************************************************
 * Class name: ChevelleEnvironment
 * Description: To store the different database, edgenodes and other server names, 
 *              for the different developing, testing and production environments (DEV, TST, CRT and PRD)
 * Author: D. Grebenisan
 * History: April .... - Created
 *****************************************************************************/
package chevelle;


public class ChevelleEnvironment 
{
    
    private String CHEVELLE_ENV = "PRD";
    
    private static final String dev_MaxisDBUrl = "jdbc:sqlserver://server.domain.com:1433;DatabaseName=someDB"; // DEV environment - someDB URL of the SQL Server
    private static final String tst_MaxisDBUrl = "jdbc:sqlserver://server.domain.com:1433;DatabaseName=someDB"; // TEST environment - someDB URL of the SQL Server
    private static final String crt_MaxisDBUrl = "jdbc:sqlserver://server.domain.com:1433;DatabaseName=someDB"; // CERT (QA) environment - QA someDB URL of the SQL Server
    private static final String prd_MaxisDBUrl = "jdbc:sqlserver://server.domain.com:1433;DatabaseName=someDB"; // PROD environment - ... someDB URL of the ... SQL Server
    // private static final String prd_MaxisDBUrl = "jdbc:sqlserver://server.domain.com :1433;DatabaseName=someDB"; // PROD environment - ... someDB URL of the SQL Server

    
    private static final String dev_EdgeNode_1 = "server001.domain.com"; // this is DEV_1 Edge Node
    private static final String dev_EdgeNode_2 = "server002.domain.com"; // this is DEV_2 Edge Node
    
    private static final String tst_EdgeNode_1 = "server003.domain.com"; // this is TEST_1 Edge Node
    private static final String tst_EdgeNode_2 = "server004.domain.com"; // this is TEST_2 Edge Node    
    
    private static final String crt_EdgeNode_1 = "server005.domain.com"; // this is CERT_1 Edge Node
    private static final String crt_EdgeNode_2 = "server106.domain.com"; // this is CERT_2 Edge Node    
    
    private static final String prd_EdgeNode_1 = "server007.domain.com"; // this is PROD_1 Edge Node
    private static final String prd_EdgeNode_2 = "server008.domain.com"; // this is PROD_2 Edge Node       
    
    private static final String sample_data_shell_directory = "/data/..../chevelle_gui/";
    private static final String sample_data_shell = "chevelle_sample_data.sh";  
    
    private static final String validate_regexp_directory = "/data/.../chev_gui/";		
    private static final String validate_regexp_data_directory = validate_regexp_directory + "user_data/";
    private static final String validate_regexp_agent = "chev_validate_regexp.sh";
    
    private static final int ssh_port = 22;
    
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
            
    public String getMaxisDBUrl()
    {
        String cur_MaxisUrl = "";
        
        if(CHEVELLE_ENV.equals("DEV"))
            cur_MaxisUrl = dev_MaxisDBUrl;
        else if (CHEVELLE_ENV.equals("TST"))
            cur_MaxisUrl = tst_MaxisDBUrl;
        else if (CHEVELLE_ENV.equals("CRT"))
            cur_MaxisUrl = crt_MaxisDBUrl;        
        else if (CHEVELLE_ENV.equals("PRD"))
            cur_MaxisUrl = prd_MaxisDBUrl;                
        else
            cur_MaxisUrl = prd_MaxisDBUrl;
        
        return cur_MaxisUrl;
    }

    public String getEdgeNode(int sub_env)
    {
        String cur_EdgeNode = "";
        
        if(CHEVELLE_ENV.equals("DEV"))
        {
            if(sub_env == 1)
                cur_EdgeNode = dev_EdgeNode_1;
            else if(sub_env == 2)
                cur_EdgeNode = dev_EdgeNode_2;
            else
                cur_EdgeNode = dev_EdgeNode_1;
        }
        
        else if(CHEVELLE_ENV.equals("TST"))
        {
            if(sub_env == 1)
                cur_EdgeNode = tst_EdgeNode_1;
            else if(sub_env == 2)
                cur_EdgeNode = tst_EdgeNode_2;
            else
                cur_EdgeNode = tst_EdgeNode_1;
        }        
        
        else if(CHEVELLE_ENV.equals("CRT"))
        {
            if(sub_env == 1)
                cur_EdgeNode = crt_EdgeNode_1;
            else if(sub_env == 2)
                cur_EdgeNode = crt_EdgeNode_2;
            else
                cur_EdgeNode = crt_EdgeNode_1;
        }                
        
        else if(CHEVELLE_ENV.equals("PRD"))
        {
            if(sub_env == 1)
                cur_EdgeNode = prd_EdgeNode_1;
            else if(sub_env == 2)
                cur_EdgeNode = prd_EdgeNode_2;
            else
                cur_EdgeNode = prd_EdgeNode_1;
        }                        
        
        else
            cur_EdgeNode = prd_EdgeNode_1;
        
        return cur_EdgeNode;
        
    }
    
    public String getSampleDataShellDirectory()
    {
        return sample_data_shell_directory;
    }
    
    public String getSampleDataShell()
    {
        return sample_data_shell;
    }
    
    public int getSshPort()
    {
        return ssh_port;
    }
    
    public String getValidateRegexpDirectory()
    {
        return validate_regexp_directory;
    }
    
    public String getValidateRegexpDataDirectory() 
    {
        return validate_regexp_data_directory;
    }
    
    public String getValidateRegexpAgent()
    {
        return validate_regexp_agent;
    }
    
}
