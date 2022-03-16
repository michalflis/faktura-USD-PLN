package faktura.USD.PLN.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class JsonService<T> {

    private final ObjectMapper objectMapper;

    public T convertToObject(String line, Class<T> t) {
        try {
            return objectMapper.readValue(line, t);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

