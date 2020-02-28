package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookupLink("connOracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleVO> selectAllArticles(){
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select level, articleNO, parentNO, "
					+ " title, content, id, writeDate from t_board"
					+ " start with parentNO=0 connect by "
					+ " prior articleNO=parentNO "
					+ " order siblings by articleNO desc";
			// 쿼리가 + 로 이어져있을경우 ' '공백을 시작으로하면 안심
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");

				ArticleVO article = new ArticleVO();
				
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				
				articlesList.add(article);
			}
			con.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
	}
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		int section = (Integer)pagingMap.get("section");
		int pageNum = (Integer)pagingMap.get("pageNum");
		try {
			con = dataFactory.getConnection();
			String query = "select * from (select rowNum as recNum, LVL, "
					+ " articleNO, parentNO, title, content, id, writeDate "
					+ " from (select LEVEL as LVL, articleNO, parentNO, title, "
					+ " content, id, writeDate from t_board"
					+ " start with parentNO=0 "
					+ " connect by prior articleNO=parentNO "
					+ " order siblings by articleNO desc)) "
					+ " where recNum between (?-1)*10+1 and ?*10"; 
			ps = con.prepareStatement(query);
			ps.setInt(1, pageNum);
			ps.setInt(2, pageNum);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int level = rs.getInt("lvl");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");

				ArticleVO article = new ArticleVO();
				
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				
				articlesList.add(article);
			}
			con.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
	}

	public void insertNewArticle(ArticleVO article) {
		try {
			int articleNO = getNewArticleNum();
			article.setArticleNO(articleNO);
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			String id = article.getId();
			
			con = dataFactory.getConnection();
			String query = "insert into t_board values(?,?,?,?,?,sysdate,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, articleNO);
			ps.setInt(2, parentNO);
			ps.setString(3, title);
			ps.setString(4, content);
			ps.setString(5, imageFileName);
			ps.setString(6, id);
			ps.executeUpdate();
			
			con.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNewArticleNum() {
		int result = 0;
		try {
			con = dataFactory.getConnection();
			String query = "select max(articleNO) from t_board";
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = (rs.getInt(1)+1);
				// 안되면 여기 return 으로
			}
			con.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArticleVO selectArticle(int articleNumber) {
		ArticleVO article = new ArticleVO();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_board where articleNO=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, articleNumber);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			int articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = rs.getString("imageFileName");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			
			article.setArticleNO(articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			
			con.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return article;
	}

	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			con = dataFactory.getConnection();
			String query = "update t_board set title=?, content=?";
			
			if(imageFileName!=null && imageFileName.length()!=0) {
				query += ",imageFileName=? ";
			}
			query += " where articleNO=?";
			
			ps = con.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, content);
			if(imageFileName!=null && imageFileName.length()!=0) {
				ps.setString(3, imageFileName);
				ps.setInt(4, articleNO);
			}else {
				ps.setInt(3, articleNO);
			}
			
			ps.executeUpdate();
			con.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			con = dataFactory.getConnection();
			String sql = "select articleNO from t_board ";
			sql += " start with articleNO=?";
			sql += " connect by prior articleNO=parentNO";
			ps = con.prepareStatement(sql);
			ps.setInt(1, articleNO);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			ps.close();
			con.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return articleNOList;
	}

	public void deleteArticle(int articleNO) {
		try {
			con = dataFactory.getConnection();
			String sql = "delete from t_board"
					+ " where articleNO in ("
					+ " select articleNO from t_board "
					+ " start with articleNO=?"
					+ " connect by prior articleNO=parentNO)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, articleNO);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int selectTotArticles() {
		int result = 0;
		try {
			con = dataFactory.getConnection();
			String query = "select count(articleNO) from t_board";
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			con.close();
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
	public void insertTestArticles100() {
		for(int i=1; i<=100; i++) {
			insertTestArticle(i);
		}
	}
	public void insertTestArticle(int num) {
		try {
			String numStr = Integer.toString(num);
			int articleNO = getNewArticleNum();
			int parentNO = 0;
			String title = numStr;
			String content = numStr;
			String imageFileName = numStr;
			String id = "Lee";
			
			con = dataFactory.getConnection();
			String query = "insert into t_board values(?,?,?,?,?,sysdate,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, articleNO);
			ps.setInt(2, parentNO);
			ps.setString(3, title);
			ps.setString(4, content);
			ps.setString(5, imageFileName);
			ps.setString(6, id);
			ps.executeUpdate();
			
			con.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
