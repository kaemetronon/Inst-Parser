import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;
import service.UserService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Instagram4j instagram4j;
        UserService us = new UserService();
        instagram4j = us.establishConnection();

        us.test(instagram4j);



        /*
        Set<String> favs = us.getNicknames("testMyAcc.txt");


        for (String fav : favs) {
            InstagramSearchUsernameResult user = instagram4j.sendRequest(new InstagramSearchUsernameRequest(fav));
            long userId = user.getUser().getPk();
            System.err.println("--------------User: " + user.getUser().username + "-----------------");

            InstagramGetUserFollowersResult users;
            List<Long> ids = new LinkedList<>();
            String next = "";
            do { // get following
                users = instagram4j.sendRequest(new InstagramGetUserFollowingRequest(userId, next));
                ids.addAll(users.getUsers().stream().map(o -> o.getPk()).collect(Collectors.toList()));
                next = users.getNext_max_id();
            } while (next != null);

            next = "";
            do { // get followers
                users = instagram4j.sendRequest(new InstagramGetUserFollowersRequest(userId, next));
                List<Long> followersIds = users.getUsers().stream().map(o -> o.getPk()).collect(Collectors.toList());
                for (Long followersId : followersIds) {
                    if (!ids.contains(followersId))
                        ids.add(followersId);
                }
                next = users.getNext_max_id();
            } while (next != null);
            if (ids.size() > 500) {
                System.err.println(ids.size() + " users. It will be too long. Enjoy.");
            }

            for (Long childId : ids) {
                InstagramFeedResult feed;
                List<Long> items = new LinkedList<>();
                next = "";
                do {
                    feed = instagram4j.sendRequest(new InstagramUserFeedRequest(childId, next, 0L, 99999999999999999L));
                    items.addAll(feed.getItems().stream().map(o -> o.getPk()).collect(Collectors.toList()));
                    next = feed.getNext_max_id();
                } while (next != null);
                System.err.println(feed.getItems().get(0).user.getUsername());
                if (items.size() > 500) {
                    System.err.println(items.size() + " posts. It will be too long. Enjoy.");
                }
                for (Long post : items) {

                }
            }
        }
        */

    }
}
