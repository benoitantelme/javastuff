package com.java.stuff.leetcode2023.heap_priority_queue;

import java.util.*;

public class Twitter {
    Map<Integer, LinkedList<Tweet>> usersTweets;
    Map<Integer, Set<Integer>> following;
    int time = 0;


    public Twitter() {
        usersTweets = new HashMap<>();
        following = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        time++;
        usersTweets.putIfAbsent(userId, new LinkedList<>());

        LinkedList<Tweet> l = usersTweets.get(userId);
        if (l.size() == 10)
            l.removeFirst();

        l.add(new Tweet(tweetId, time));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> heap = new PriorityQueue<>((x, y) -> y.time - x.time);
        if (usersTweets.containsKey(userId))
            heap.addAll(usersTweets.get(userId));

        if (following.containsKey(userId))
            for (int n : following.get(userId))
                if (usersTweets.containsKey(n))
                    heap.addAll(usersTweets.get(n));

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            if (heap.peek() != null)
                result.add(heap.poll().id);

        return result;
    }

    public void follow(int followerId, int followeeId) {
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).remove(followeeId);
    }

    class Tweet {
        int id;
        int time;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Tweet{" +
                    "id=" + id +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1
        // is no longer following user 2.
    }

}
