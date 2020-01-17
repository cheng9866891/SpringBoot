package com.wyg.cheng.provider;

import com.alibaba.fastjson.JSON;
import com.wyg.cheng.dto.AccessTokenDTO;
import com.wyg.cheng.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {

        }
        return null;
    }

    public GithubUserDTO getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(result, GithubUserDTO.class);
            return githubUserDTO;
        } catch (IOException e) {

        }
        return null;
    }
}
