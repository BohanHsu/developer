package test;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TestMore {
	
	/**
	 * show my twitters
	 * */
	public static void gettingTimeline() throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	
	/**
	 * fetch tweets from a user by it's name
	 * fetch approximately 10+ tweets once
	 * the user name as the argument to construct Query object
	 * */
	public static void searchTweets() throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
//	    Query query = new Query("source:twitter4j yusukey");
		Query query = new Query("BeijingAir");
	    QueryResult result = twitter.search(query);
	    for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }
	}
	
	
	public static void main(String[] args) throws Exception{
//		gettingTimeline();
		searchTweets();
		
		System.out.println("@end");
		
	}
}
