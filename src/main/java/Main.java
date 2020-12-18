import service.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        UserService us = new UserService();

//        Set<String> favs = us.nicknamesFromFile("testMyAcc.txt");
//
//
//        for (String fav : favs) {
//            InstagramSearchUsernameResult user = us.getAccByName(fav);
//            long userId = user.getUser().getPk();
//            System.err.println("--------------User: " + user.getUser().username + "-----------------");
//
//            List<Long> follIds = us.follPKs(userId);
//
//            for (Long childId : follIds) {
//                List<String> idsAndCaptions = us.feedIds(childId);
//                int endOfIds = idsAndCaptions.indexOf("id_ends_next_captions");
//                List<String> captions = idsAndCaptions.subList(endOfIds + 1, idsAndCaptions.size() - 1); // последний элемент считается или нет?
//                List<String> feedIds = idsAndCaptions.subList(0, endOfIds);
//                for (String postId : feedIds) {
//                    InstagramGetMediaCommentsResult post = us.getPost(postId);
//                    post.getComments();
//                }
//            }
//        }


    }
}
