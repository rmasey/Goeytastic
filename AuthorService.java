import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class AuthorService
{
    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Author> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT AuthorID, FirstName, SecondName FROM Authors ORDER BY AuthorID"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Main.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Author(results.getInt("AuthorID"), results.getString("FirstName"),results.getString("SecondName")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }
    }

    public static void deleteById(int AuthorID)
    {
        try{

            PreparedStatement statement = Main.database.newStatement("DELETE FROM Authors WHERE AuthorID = ?");             
            statement.setInt(1, AuthorID);

            if (statement != null){
                Main.database.executeUpdate(statement);
            }
        } catch (SQLException resultsexception){
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }

    public static void save(Author author)
    {
        PreparedStatement statement;

        try{
            statement = Main.database.newStatement("INSERT INTO Authors (FirstName, SecondName) VALUES (?, ?)");             
            statement.setString(1, author.FirstName);    
            statement.setString(2, author.SecondName);    

            if (statement != null) {
                Main.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }

}
