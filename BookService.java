import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class BookService
{

//     /* Different models will require different read and write methods. Here is an example 'loadAll' method 
//      * which is passed the target list object to populate. */
//     public static void readAll(List<Book> list)
//     {
//         list.clear();       // Clear the target list first.
// 
//         /* Create a new prepared statement object with the desired SQL query. */
//         PreparedStatement statement = Main.database.newStatement("SELECT BookID, Title, AuthorID FROM Books ORDER BY BookID"); 
// 
//         if (statement != null)      // Assuming the statement correctly initated...
//         {
//             ResultSet results =  Main.database.runQuery(statement);       // ...run the query!
// 
//             if (results != null)        // If some results are returned from the query...
//             {
//                 try {                               // ...add each one to the list.
//                     while (results.next()) {                                               
//                         list.add( new Book(results.getInt("BookID"), results.getString("Title"), results.getInt("AuthorID")));
//                     }
//                 }
//                 catch (SQLException resultsexception)       // Catch any error processing the results.
//                 {
//                     System.out.println("Database result processing error: " + resultsexception.getMessage());
//                 }
//             }
//         }
// 
//     }
    
     public static void readAll(List<Book> list, int AuthorID)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT BookID, Title, AuthorID FROM Books WHERE AuthorID = " + AuthorID + " ORDER BY BookID"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results =  Main.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Book(results.getInt("BookID"), results.getString("Title"), results.getInt("AuthorID")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Book getById(int BookID)
    {
        Book book = null;

        PreparedStatement statement = Main.database.newStatement("SELECT BookID, Title, AuthorID FROM Books WHERE BookID = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, BookID);
                ResultSet results = Main.database.runQuery(statement);

                if (results != null)
                {
                    book = new Book(results.getInt("BookID"), results.getString("Title"), results.getInt("AuthorID"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return book;
    }

    public static void deleteById(int BookID)
    {
        try{
            PreparedStatement statement = Main.database.newStatement("DELETE FROM Books WHERE BookID = ?");             
            statement.setInt(1, BookID);

            if (statement != null){
                Main.database.executeUpdate(statement);
            }
        } catch (SQLException resultsexception){
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }

    public static void save(Book book)
    {
        try
        {
            PreparedStatement statement = Main.database.newStatement("INSERT INTO Books (Title, AuthorID) VALUES (?, ?)");             
            statement.setString(1, book.Title);
            statement.setInt(2, book.AuthorID);         

            if (statement != null) {
                Main.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }
}
