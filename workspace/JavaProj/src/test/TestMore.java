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
	 * search tweets from a user by it's name
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
	
	public static void getUserTimeLine(){
		Twitter twitter = new TwitterFactory().getInstance();
        try {
            List<Status> statuses;
            String user = "BeijingAir";
//            if (args.length == 1) {
//                user = args[0];
                statuses = twitter.getUserTimeline(user);
//            } else {
//                user = twitter.verifyCredentials().getScreenName();
//                statuses = twitter.getUserTimeline();
//            }
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
//            System.exit(-1);
        }
	}
	
	
	public static void main(String[] args) throws Exception{
//		gettingTimeline();
//		searchTweets();
		getUserTimeLine();
		System.out.println("@end");
		
	}
}
