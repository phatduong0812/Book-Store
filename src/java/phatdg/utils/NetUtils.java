/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import phatdg.account.AccountDTO;

/**
 *
 * @author Phat
 */
public class NetUtils {
        public static String getToken(final String code) throws IOException {
        String response = Request.Post(MyAppConstants.GoogleServlet.LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", MyAppConstants.GoogleServlet.CLIENT_ID)
                        .add("client_secret", MyAppConstants.GoogleServlet.CLIENT_SECRET)
                        .add("redirect_uri", MyAppConstants.GoogleServlet.REDIRECT_URI).add("code", code)
                        .add("grant_type", MyAppConstants.GoogleServlet.GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;         
    }

    public static AccountDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = MyAppConstants.GoogleServlet.LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        AccountDTO accountDTO = new Gson().fromJson(response, AccountDTO.class);
        return accountDTO;
    }
}
