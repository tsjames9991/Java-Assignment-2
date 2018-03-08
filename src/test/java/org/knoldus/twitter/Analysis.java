package org.knoldus.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;


public class Analysis {
    final private String userHandle = "bruceschneier";
    private final long convertToDay = 1000 * 60 * 60 * 24;
    private Twitter twitter;

    Analysis() {
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Configuration.CONSUMER_KEY)
                .setOAuthConsumerSecret(Configuration.CONSUMER_SECRET)
                .setOAuthAccessToken(Configuration.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Configuration.ACCESS_TOKEN_SECRET);
        TwitterFactory tweetFactory = new TwitterFactory(configBuilder.build());
        this.twitter = tweetFactory.getInstance();
    }

    /**
     * @param hashTag : Example #LFC, # followed by the term you.
     *                are looking for
     * @return: returns tweets with the # you are looking for
     */
    public CompletableFuture<List<Status>>
    getTweetsWithHastag(final String hashTag) {
        return supplyAsync(
                () -> {
                    List<Status> tweets = Collections.emptyList();
                    try {
                        tweets = this.twitter.getUserTimeline(userHandle)
                                .stream()
                                .filter(tweet -> tweet.getText().contains(hashTag))
                                .collect(Collectors.toList());

                    } catch (TwitterException ex) {
                        System.out.println("Error Occured:" + ex.getMessage());
                    }
                    return tweets;
                }
        );
    }

    /**
     * @return: Gets total number of tweets by a account.
     */
    public CompletableFuture<Integer> findNumberOfTweets() {
        return supplyAsync(
                () -> {
                    int count = 0;
                    try {
                        count = this.twitter.showUser(userHandle).getStatusesCount();
                    } catch (TwitterException ex) {
                        System.out.println("Error Occured: " + ex.getCause());
                    }
                    return count;
                }
        );
    }

    /**
     * @return : Return average tweets per day by LFC.
     */
    public CompletableFuture<Double> findAverageTweetsPerDay() {
        return supplyAsync(
                () -> {
                    double avg = 0.0;
                    try {
                        Date createdAt = this.twitter.showUser(userHandle).getCreatedAt();
                        Date now = new Date();
                        long days = (now.getTime() - createdAt.getTime()) / convertToDay;
                        int tweetCount = this.twitter.showUser(userHandle).getStatusesCount();
                        avg = tweetCount / days;
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                    return avg;
                }
        );
    }

    /**
     * @return: Returns average likes per post.
     * average retweets per post.
     */
    public CompletableFuture<String> findAverageLikesAndRetweets() {
        return supplyAsync(
                () -> {
                    String message;
                    try {
                        double likes = 0.0;
                        double retweets = 0.0;
                        int tweetCount = this.twitter.getUserTimeline(userHandle, new Paging(1, 100)).size();
                        for (Status tweet : this.twitter.getUserTimeline(userHandle)) {
                            likes += tweet.getFavoriteCount();
                            retweets += tweet.getRetweetCount();
                        }
                        message = "Average Likes: " + (likes / tweetCount) + "\nAverage Retweets: " + (retweets / tweetCount);
                    } catch (TwitterException te) {
                        System.out.println(te.getMessage());
                        message = "Average Likes: 0.0 " + "\nAverage Retweets: 0.0";
                    }
                    return message;
                }
        );
    }
}
