
/* Each table you wish to access in your database requires a model class, like this example: */
public class Author
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int AuthorID;
    public String FirstName;
    public String SecondName;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Author(int AuthorID, String FirstName, String SecondName)
    {
        this.AuthorID = AuthorID;
        this.FirstName = FirstName;
        this.SecondName = SecondName;

    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return "Author ID: "  + AuthorID +  " First Name: "  + FirstName+ " Second Name: " + SecondName;
    }

    //methods - getters and setters
    public int getAuthorID() { return AuthorID;}

    public String getFirstName() {return FirstName;}

    public String getSecondName() {return SecondName;}

    public void setAuthorID (int AuthorID){  this.AuthorID = AuthorID;    }

    public void setFirstName(String FirstName){  this.FirstName = FirstName;    }

    public void setSecondName(String SecondName){  this.SecondName = SecondName;    }

}