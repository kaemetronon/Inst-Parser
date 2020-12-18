package service;

import com.github.instagram4j.instagram4j.IGClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserService {

    IGClient client;

    public UserService() throws IOException {
        establishConnection();
    }

//    public InstagramSearchUsernameResult getAccByName(String name) throws IOException {
//        return instagram4j.sendRequest(new InstagramSearchUsernameRequest(name));
//    }

//    public List<Long> follPKs(long userPK) throws IOException {
//        InstagramGetUserFollowersResult users;
//        List<Long> ids = new LinkedList<>();
//        String next = "";
//        do { // get following
//            users = instagram4j.sendRequest(new InstagramGetUserFollowingRequest(userPK, next));
//            ids.addAll(users.getUsers().stream().map(o -> o.getPk()).collect(Collectors.toList()));
//            next = users.getNext_max_id();
//        } while (next != null);
//
//        next = "";
//        do { // get followers
//            users = instagram4j.sendRequest(new InstagramGetUserFollowersRequest(userPK, next));
//            List<Long> followersIds = users.getUsers().stream().map(o -> o.getPk()).collect(Collectors.toList());
//            for (Long followersId : followersIds) {
//                if (!ids.contains(followersId))
//                    ids.add(followersId);
//            }
//            next = users.getNext_max_id();
//        } while (next != null);
//        if (ids.size() > 500) {
//            System.err.println(ids.size() + " users. It will be too long. Enjoy.");
//        }
//        return ids;
//    }

//    public List<String> feedIds(long userPK) throws IOException {
//        InstagramFeedResult feed;
//        List<String> items = new LinkedList<>();
//        List<String> captions = new LinkedList<>();
//        String next = "";
//        do {
//            feed = instagram4j.sendRequest(new InstagramUserFeedRequest(userPK, next, 0L, 99999999999999999L));
//            items.addAll(feed.getItems().stream().map(o -> o.getId()).collect(Collectors.toList()));
//            captions.addAll(feed.getItems().stream().map(o -> o.getCaption().getText()).collect(Collectors.toList()));
//            next = feed.getNext_max_id();
//
//            int count = 0;
//            for (int i = 0; i < feed.getItems().size(); i++) {
//                if (feed.getItems().get(i).getImage_versions2() == null)
//                    count++;
//                else System.err.println(feed.getItems().get(i).getImage_versions2().getCandidates().get(0).url);
//            }
//            System.err.println("lost: " + count);
//        } while (next != null);
//        System.err.println(feed.getItems().get(0).user.getUsername());
//        if (items.size() > 500) {
//            System.err.println(items.size() + " posts. It will be too long. Enjoy.");
//        }
//        items.add("id_ends_next_captions");
//        items.addAll(captions);
//        return items;
//    }

//    public InstagramGetMediaCommentsResult getPost(String id) throws IOException {
//        InstagramGetMediaCommentsResult post;
//        String next = "";
//        do {
//            post = instagram4j.sendRequest(new InstagramGetMediaCommentsRequest(id, next));
//            next = post.getNext_max_id();
//        } while (next != null);
//        return post;
//    }

//    public Set<String> nicknamesFromFile(String fileName) {
//        Scanner scanner = scanFile(fileName);
//        Set<String> res = new LinkedHashSet<>();
//        while (scanner.hasNext()) {
//            res.add(scanner.next());
//        }
//        scanner.close();
//        return res;
//    }

    private Scanner scanFile(String fileName) {
        try {
            return new Scanner(Paths.get("src/main/resources/".concat(fileName)), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void establishConnection() throws IOException {
        Scanner scanner = scanFile("accInfo.txt");

        client = IGClient.builder()
                .username(scanner.next())
                .password(scanner.next())
                .login();
    }
}
