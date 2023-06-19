package com.iSully.TagGame;

import com.google.gson.Gson;
import okhttp3.*;
import lombok.extern.slf4j.Slf4j;
import io.socket.client.Socket;
import net.runelite.api.Client;
import net.runelite.api.Player;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Singleton
public class ConnectionManager {
    private final String apiUrlBase = "http://localhost:4001";  // TODO: Change this
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Client client;

    public Socket socketServer;

    private OkHttpClient okHttpClient;

    private Gson gson;

    public ConnectionManager(Client client, Gson gson) {
        this.client = client;
        this.gson = gson;
        this.okHttpClient = new OkHttpClient();
    }

    public void startGame() {
        try {
            // Create a Url object from the URL string
            URL url = new URL("http://localhost:4001/tag/startGame");

            // Create a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request type to POST
            conn.setRequestMethod("POST");

            // Enable input and output streams
            conn.setDoOutput(true);

            // Set the content type to application/json
            conn.setRequestProperty("Content-Type", "application/json; utf-8");

            // Create the JSON data
            String jsonInputString = this.getActivePlayerUsername();

            // Write the JSON data to the output stream
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    protected void createNewGame(Player player) {
//            String username = urlifyString(player.getName());
            String url = apiUrlBase.concat("/tag/createPartyGame");
            GamePlayer gamePlayer = createGamePlayerObject(player);

            try
            {
                Request r = new Request.Builder()
                        .url(url)
//                        .post(RequestBody.create(JSON, gson.toJson(player)))
                        .post(RequestBody.create(JSON, gson.toJson(gamePlayer)))
                        .build();

                okHttpClient.newCall(r).enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {
                        log.debug("Error sending post data", e);
                    }

                    @Override
                    public void onResponse(Call call, Response response)
                    {
                        if (response.isSuccessful())
                        {
                            System.out.println("Successfully sent prop hunt data");
                            System.out.println(response.body().);
                            System.out.println(call);
                            response.close();
                        }
                        else
                        {
                            log.debug("Post request unsuccessful");
                            response.close();
                        }
                    }
                });
            }
            catch (IllegalArgumentException e)
            {
                log.error("Bad URL given: " + e.getLocalizedMessage());
            }
        }

    private GamePlayer createGamePlayerObject(Player player) {
        // TODO: Upate any necessary properties in the future
        return new GamePlayer(player.getName(), "123");
    }

    public String getActivePlayerUsername() {

        Player localPlayer = client.getLocalPlayer();
        if (localPlayer != null) {
            return localPlayer.getName();
        } else {
            return null;
        }
    }

    private String urlifyString(String str) {
        return str.trim().replaceAll("\\s", "%20");
    }
}
