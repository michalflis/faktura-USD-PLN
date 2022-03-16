package faktura.USD.PLN.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Data
@Service
@AllArgsConstructor
public class SaveJsonFromWeb {

    public String saveJsonToString(String date) throws Exception {

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest =
                HttpRequest.newBuilder(new URI("http://api.nbp.pl/api/exchangerates/rates/a/usd/" + date + "/?format=json/"))
                        .GET()
                        .build();

        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }
}

