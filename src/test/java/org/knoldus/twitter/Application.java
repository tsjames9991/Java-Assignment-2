package org.knoldus.twitter;

import twitter4j.Status;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This is the entry point of the application.
 */
public final class Application {

    /**
     * Used so that this class will not get instantiated outside the package.
     */
    private Application() {

    }

    /**
     * The main class
     */
    public static void main(final String[] args) throws InterruptedException {
        Analysis reportGenerator = new Analysis();
        System.out.println("Enter the Hash-Tag you want to search: ");
        String hashTag = "#squid";
        CompletableFuture<List<Status>> result = reportGenerator.getTweetsWithHastag(hashTag);
        CompletableFuture<Integer> tweetsCount = reportGenerator.findNumberOfTweets();
        CompletableFuture<Double> averageTweets = reportGenerator.findAverageTweetsPerDay();
        CompletableFuture<String> retweetsAndLikes = reportGenerator.findAverageLikesAndRetweets();
        Thread.sleep(5000);
        System.out.println("\nPrinting tweets with Hash-Tag");
        result.thenAccept(statusList -> statusList
                .forEach(tweet -> System.out.println(tweet.getText())));


        System.out.println("\nTotal number of tweets by Bruce Schneier: ");
        Thread.sleep(5000);
        tweetsCount.thenAccept(count -> System.out.println("Total tweets: " + count));

        System.out.println("\nFinding average tweets by @LFC: ");
        Thread.sleep(5000);
        averageTweets.thenAccept(count -> System.out.println("Average tweets: " + count));

        System.out.println("\nFinding average re-tweets and likes per tweet by Bruce Schneier: ");
        Thread.sleep(5000);
        retweetsAndLikes.thenAccept(System.out::println);
    }
}