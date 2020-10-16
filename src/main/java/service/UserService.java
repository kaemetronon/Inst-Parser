package service;

import model.Acc;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {


    public Set<String> getNicknames(String fileName) {
        Scanner scanner = scanFile(fileName);
        Set<String> res = new LinkedHashSet<>();
        while (scanner.hasNext()) {
            res.add(scanner.next());
        }
        scanner.close();
        return res;
    }

    private Scanner scanFile(String fileName) {
        try {
            return new Scanner(Paths.get("src/main/resources/".concat(fileName)), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Instagram4j establishConnection() throws IOException {
        Scanner scanner = scanFile("accInfo.txt");
        Instagram4j instagram4j = Instagram4j.builder()
                .username(scanner.next())
                .password(scanner.next())
                .build();
        instagram4j.setup();
        scanner.close();
        InstagramLoginResult instagramLoginResult = instagram4j.login();
        if (Objects.equals(instagramLoginResult.getStatus(), "ok")) {
            System.out.println("login success");
            return instagram4j;
        } else {
            if (Objects.equals(instagramLoginResult.getError_type(), "checkpoint_challenge_required")) {
                // Challenge required

                // Get challenge URL
                String challengeUrl = instagramLoginResult.getChallenge().getApi_path().substring(1);

                // Reset challenge
                String resetChallengeUrl = challengeUrl.replace("challenge", "challenge/reset");
                InstagramGetChallengeResult getChallengeResult = instagram4j
                        .sendRequest(new InstagramResetChallengeRequest(resetChallengeUrl));

                // If action is close
                if (Objects.equals(getChallengeResult.getAction(), "close")) {
                    // Get challenge
                    getChallengeResult = instagram4j
                            .sendRequest(new InstagramGetChallengeRequest(challengeUrl));
                }

                if (Objects.equals(getChallengeResult.getStep_name(), "select_verify_method")) {

                    // Get security code
                    InstagramSelectVerifyMethodResult postChallengeResult;
                    postChallengeResult = instagram4j
                            .sendRequest(new InstagramSelectVerifyMethodRequest(challengeUrl,
                                    getChallengeResult.getStep_data().getChoice()));

                    System.out.println("input security code");
                    String securityCode = null;
                    try (Scanner sc = new Scanner(System.in)) {
                        securityCode = sc.nextLine();
                    }

                    // Send security code
                    InstagramLoginResult securityCodeInstagramLoginResult = instagram4j
                            .sendRequest(new InstagramSendSecurityCodeRequest(challengeUrl, securityCode));

                    if (Objects.equals(securityCodeInstagramLoginResult.getStatus(), "ok")) {
                        System.out.println("login success");
                    } else {
                        System.out.println("login failed");
                    }
                }
            }
            return null;
        }
    }

    public void test(Instagram4j instagram4j) throws IOException {
        long id = 1308776818L;
        instagram4j = establishConnection();
        InstagramFeedResult feed;
        List<Long> items = new LinkedList<>();
        String next = "";
        do {
            feed = instagram4j.sendRequest(new InstagramUserFeedRequest(id, next, 0L, 9999999999999999L));
            items.addAll(feed.getItems().stream().map(o -> o.getPk()).collect(Collectors.toList()));
            next = feed.getNext_max_id();

            int count = 0;
            for (int i = 0; i < feed.getItems().size(); i++) {
                if (feed.getItems().get(i).getImage_versions2() == null)
                    count++;
                else System.err.println(feed.getItems().get(i).getImage_versions2().getCandidates().get(0).url);
            }
            System.err.println(count);
        } while (next != null);
        System.err.println(feed.getItems().get(0).user.getUsername());
        if (items.size() > 500) {
            System.err.println(items.size() + " posts. It will be too long. Enjoy.");
        }
        for (Long post : items) {

        }
    }
}
