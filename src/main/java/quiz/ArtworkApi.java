package quiz;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ArtworkApi {

    private static final String BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1";
    private static final String SEARCH_URL = "https://collectionapi.metmuseum.org/public/collection/v1.1/search";
    private final Gson gson = new Gson();
    private final Random random = new Random();

    public static class Artwork {
        public String title;
        public String artistDisplayName;
        public String primaryImageSmall;
    }

    private static class SearchResponse {
        public int[] objectIDs;
    }

    public Artwork getRandomArtwork() {
        try {
            String searchUrl = SEARCH_URL + "?hasImages=true&q=Painting&limit=100&offset=0";
            String searchJson = makeGetRequest(searchUrl);
            SearchResponse searchResult = gson.fromJson(searchJson, SearchResponse.class);

            if (searchResult == null || searchResult.objectIDs == null || searchResult.objectIDs.length == 0) {
                System.out.println("API не вернул ни одной картины.");
                return null;
            }

            int randomIndex = random.nextInt(searchResult.objectIDs.length);
            int objectId = searchResult.objectIDs[randomIndex];

            String objectUrl = BASE_URL + "/objects/" + objectId;
            String objectJson = makeGetRequest(objectUrl);
            Artwork artwork = gson.fromJson(objectJson, Artwork.class);

            if (artwork == null || artwork.primaryImageSmall == null || artwork.primaryImageSmall.isEmpty()) {
                return null;
            }
            return artwork;

        } catch (Exception e) {
            System.out.println("=== ОШИБКА API ===");
            e.printStackTrace();
            return null;
        }
    }

    public Artwork parseArtworkJson(String json) {
        return gson.fromJson(json, Artwork.class);
    }

    private String makeGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP ошибка: " + connection.getResponseCode());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        connection.disconnect();
        return response.toString();
    }
}