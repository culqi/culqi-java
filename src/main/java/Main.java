/**
 * Created by culqi on 12/21/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Tokens;
import model.Secure;
import model.Token;



public class Main {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Secure secure = new Secure();
        secure.setCOD_ECOMERCE("live_ewm1DV7CsseD");

        Token token = new Token();
        token.setCard_number("4111111111111111");
        token.setCurrency_code("PEN");
        token.setFingerprint("q352454534");
        token.setCvv("123");
        token.setEmail("waguirre@me.com");
        token.setExpiration_month(9);
        token.setExpiration_year(2020);
        token.setLast_name("Aguirre");
        token.setFirst_name("Willy");

        Tokens tokens = new Tokens();
        String jsonData = mapper.writeValueAsString(token);
        String message = tokens.create(secure,jsonData);
        System.out.println(message);

    }

}
