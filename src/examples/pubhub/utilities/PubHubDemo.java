package examples.pubhub.utilities;

import java.util.List;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class PubHubDemo {

	public static void main(String[] args) {

		// debugging
		BookDAO bDao = DAOUtilities.getBookDAO();
		Book book = new Book("33333", "A fresh book", "JK", null);
		//		bDao.addBook(book);
		TagDAO tDao = DAOUtilities.getTagDAO();
		//		tDao.addTag(new Tag("tag0"), bDao.getBookByISBN("33333"));
		//		Book book = bDao.getBookByISBN("33333");
		//		book.setPrice(45.00);
		//		System.out.println(bDao.getBookByISBN("33333").getPrice());
		//		tDao.removeTag(tDao.getTagByTagName("tag7"), bDao.getBookByISBN("33333"));
		//		tDao.addTag(new Tag("a_tag"), bDao.getBookByISBN("33333"));
		List<Tag> list = book.getTags();

		//		for (Tag tag : bDao.getBookByISBN("33333").getTags()) {
		//			System.out.println(tag.getTag_name());
		//		}
		if(list != null)	{
			for (Tag tag : list) {
				System.out.println(tag.getTag_name());
			}
		}
		else	{
			System.out.println("list null");
		}


	}

}
