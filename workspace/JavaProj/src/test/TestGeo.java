package test;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TestGeo {

	public static void SearchPaces(double latitude, double longitude) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		GeoQuery query = null;
		query = new GeoQuery(new GeoLocation(latitude, longitude));
		ResponseList<Place> places = twitter.searchPlaces(query);
		if (places.size() == 0) {
			System.out.println("no place found");
			return; 
		}
		for (Place place : places) {
			System.out.println("-id: " + place.getId());
			System.out.println("-name: " + place.getFullName());
			Place[] containedWithInArray = place.getContainedWithIn();
			for (Place place2 : containedWithInArray) {
				System.out.println("--id: " + place2.getId());
				System.out.println("--name: " + place2.getFullName());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("@start");
		/*
		 * Geo coordinate for Boston
		 * 42.3581 N, 71.0636 W
		 * */
		SearchPaces(42.3581, -71.0636);
//		SearchPaces(71.0636, 42.3581);
		System.out.println("@end");
	}
}
