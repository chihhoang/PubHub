package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {
	
	public boolean addTag(String tag_name);	// to Tags table
	public boolean addTag(Tag tag, Book book); // to BOOK_TAGS table
	public boolean removeTag(Tag tag, Book book);  // from BOOK_TAGS table
	public boolean containsTag(String tag_name);
	public Tag getTagByTagName(String tag_name);
	public List<Tag> retrieveTags(Book book);
	public List<Book> retrieveBooks(Tag tag);

}
