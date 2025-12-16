package org.eletra.energy.frontend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.eletra.energy.frontend.models.dtos.LineMeterDTO;

import java.io.IOException;
import java.util.List;

public class ApiLineMeterService {

    private OkHttpClient client;

    public List<LineMeterDTO> getLineMeters(String endpoint) throws IOException {

        Request request = new Request.Builder().url("http://localhost:8080/api" + endpoint).build();

        try (Response response = client.newCall(request).execute()) {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response.body().string(), new TypeReference<List<LineMeterDTO>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
