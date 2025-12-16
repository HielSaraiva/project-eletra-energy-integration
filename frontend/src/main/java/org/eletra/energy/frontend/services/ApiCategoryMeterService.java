package org.eletra.energy.frontend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.eletra.energy.frontend.models.dtos.CategoryMeterDTO;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;

public class ApiCategoryMeterService {

    private OkHttpClient client;

    public List<CategoryMeterDTO> getCategories(String endpoint) throws IOException {

        Request request = new Request.Builder().url("https://localhost:8080/api" + endpoint).build();

        try (Response response = client.newCall(request).execute()) {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response.body().string(), new TypeReference<List<CategoryMeterDTO>>() {
            });

        } catch (InterruptedIOException e) {
            throw new RuntimeException(e);
        }
    }
}
