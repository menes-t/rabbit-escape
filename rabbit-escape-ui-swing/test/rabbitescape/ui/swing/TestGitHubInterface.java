package rabbitescape.ui.swing;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.Test;

public class TestGitHubInterface
{

    @Test
    public void TestBacktickMarkdownWorld()
    {
        // strings from github json have actual \n, not newline chars
        String issueBodyText = 
            "Some chat\\n" +
            "```\\n"+
            ":name=Bunny\\n"+
            ":description=Boiler\\n"+
            "#   r  \\n"+
            "#######\\n"+
            "```\\n"+
            "More waffle";
        
        GitHubIssue ghi = new GitHubIssue();
        ghi.setBody( issueBodyText );
        String wrappedWorld = ghi.getWorld( 0 );
        // now newlines
        String expectedWrappedWorld =
            ":name=Bunny\n"+
            ":description=Boiler\n"+
            "#   r  \n"+
            "#######\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
    }
    
    @Test
    public void TestIndentMarkdownWorld()
    {
        // strings from github json have actual \n, not newline chars
        String issueBodyText = 
            "Some chat\\n" +
            "    :name=Bunny\\n"+
            "    :description=Boiler\\n"+
            "    #   r  \\n"+
            "          #\\n"+
            "    #######\\n"+
            "\\n"+
            "More waffle";
        GitHubIssue ghi = new GitHubIssue();
        ghi.setBody( issueBodyText );
        String wrappedWorld = ghi.getWorld( 0 );
        // now newlines
        String expectedWrappedWorld =
            ":name=Bunny\n"+
            ":description=Boiler\n"+
            "#   r  \n"+
            "      #\n"+
            "#######\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
    }
    
    @Test
    public void TestManyWorldMarkdown()
    {
        // 6-space indented. tab indented. backticked.
        String issueBodyText = 
            "Dear sir,\\n" +
            "Herein are some levels\\n" +
            "      :name=Level1\\n"+
            "      # #####\\n"+
            "Here is the next\\n" +
            "      :name=Level2\\n"+
            "      ## ####\\n"+
            "The next\\n"+
            "\\t:name=Level3\\n"+
            "\\t### ###\\n"+
            "And the last\\n"+
            "```\\n"+
            ":name=Level4\\n"+
            "##### #\\n"+
            "```\\n"+
            "Regards\\n";
        GitHubIssue ghi = new GitHubIssue();
        ghi.setBody( issueBodyText );
        String wrappedWorld, expectedWrappedWorld;
        
        for (int i=0; i<4; i++)
        {
            
            System.out.println( "--->\n" + ghi.getWorld( i ) +"<" );
        }
        
        wrappedWorld = ghi.getWorld( 0 );
        expectedWrappedWorld = ":name=Level1\n"+
                               "# #####\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
        
        wrappedWorld = ghi.getWorld( 1 );
        expectedWrappedWorld = ":name=Level2\n"+
                               "## ####\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
        
        wrappedWorld = ghi.getWorld( 2 );
        expectedWrappedWorld = ":name=Level3\n"+
                               "### ###\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
        
        wrappedWorld = ghi.getWorld( 3 );
        expectedWrappedWorld = ":name=Level4\n"+
                               "#### ##\n";
        assertThat( wrappedWorld, equalTo(expectedWrappedWorld) );
    }
}
