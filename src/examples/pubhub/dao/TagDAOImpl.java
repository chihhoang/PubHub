package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {

	Connection connection;
	PreparedStatement stmt;
	PreparedStatement stmt2;

	@Override
	public boolean addTag(String tag_name) {
		try	{
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO TAGS VALUES (?)";
			stmt = connection.prepareStatement(sql);

			// add to Tags table
			stmt.setString(1, tag_name);

			if(stmt.executeUpdate() != 0)	{
				System.out.println("Tags table updated");
				return true;
			}
			else	{
				return false;
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
			return false;
		}
		finally	{
			closeResources();	// close statement and connection
		}
	}

	@Override
	public boolean addTag(Tag tag, Book book) {

		// the tag doesn't exist
		if(!containsTag(tag.getTag_name()))	{
			// populate the new tag in TAGS table
			addTag(tag.getTag_name());
		}
		else	{
			System.out.println("existed");
		}

		// insert to BOOK_TAGS
		try	{
			connection = DAOUtilities.getConnection();

			// add tag to BookTags
			String sql = "INSERT INTO BOOK_TAGS VALUES (?,?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag.getTag_name());

			if(stmt.executeUpdate() != 0)	{
				System.out.println("Book_Tags table updated");
				// Update the book's tags collection
				List<Tag> tags = retrieveTags(book);
				//				for (Tag t : tags) {
				//					System.out.println(t.getTag_name());
				//				}

				book.setTags(tags);	// useless


				return true;
			}
			else	{
				return false;
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
			return false;
		}
		finally	{
			closeResources();	// close statement and connection
		}
	}


	@Override
	public boolean removeTag(Tag tag, Book book) {

		try	{
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM BOOK_TAGS WHERE ISBN_13=? AND TAG_NAME=?";
			String sql2 = "DELETE FROM TAGS WHERE TAG_NAME=?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag.getTag_name());

			stmt2 = connection.prepareStatement(sql2);

			stmt2.setString(1, tag.getTag_name());

			if(stmt.executeUpdate() != 0 && stmt2.executeUpdate() != 0)	{
				System.out.println("Book_Tags and Tags table updated");
				return true;
			}
			else	{
				System.out.println("Book_Tags and Tag table failed to update");
				return false;
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
			return false;
		}
		finally	{
			closeResources();	// close statement and connection
		}

	}

	@Override
	public boolean containsTag(String tag_name) {
		// check if the tag already exists
		try	{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT FROM TAGS WHERE TAG_NAME=?";
			stmt = connection.prepareStatement(sql);

			// add to Tags table
			stmt.setString(1, tag_name);
			ResultSet rs = stmt.executeQuery();

			if(rs.next())	{
				return true;
			}
			else	{
				return false;
			}

		}
		catch(SQLException e)	{
			e.printStackTrace();
			return false;
		}
		finally	{
			closeResources();	// close statement and connection
		}
	}

	@Override
	public Tag getTagByTagName(String tag_name) {
		Tag tag = new Tag();

		try	{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT TAG_NAME FROM TAGS WHERE TAG_NAME=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tag_name);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				tag.setTag_name(rs.getString("tag_name"));
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
		}
		finally	{
			closeResources();	// close statement and connection
		}

		return tag;
	}

	@Override
	public List<Tag> retrieveTags(Book book) {
		List<Tag> tags = new ArrayList<>();
		try	{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT TAG_NAME FROM BOOK_TAGS WHERE ISBN_13=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getIsbn13());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Tag tag = new Tag();
				tag.setTag_name(rs.getString("tag_name"));

				tags.add(tag);
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
		}
		finally	{
			closeResources();	// close statement and connection
		}

		return tags;
	}

	@Override
	public List<Book> retrieveBooks(Tag tag) {
		List<Book> books = new ArrayList<>();

		try	{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT ISBN_13 FROM BOOK_TAGS WHERE TAG_NAME=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tag.getTag_name());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Book book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));

				books.add(book);
			}
		}
		catch(SQLException e)	{
			e.printStackTrace();
		}
		finally	{
			closeResources();	// close statement and connection
		}

		return books;
	}

	// Closing all resources is important, to prevent memory leaks.
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


}
