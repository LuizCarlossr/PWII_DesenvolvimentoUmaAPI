package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiProdutos {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (scanner.hasNext()) {
                json.append(scanner.nextLine());
            }
            scanner.close();

            ObjectMapper mapper = new ObjectMapper();
            RespostaProdutos resposta = mapper.readValue(json.toString(), RespostaProdutos.class);

            for (Produto p : resposta.getProducts()) {
                System.out.println("   Produto:   " + p.getTitle());
                System.out.println("   Marca:     " + p.getBrand());
                System.out.println("   Preço:   $ " + p.getPrice());
                System.out.println("   Estoque:   " + p.getStock());
                System.out.println("   Categoria: " + p.getCategory());
                System.out.println("   Avaliação: " + p.getRating());
                System.out.println("--------------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
