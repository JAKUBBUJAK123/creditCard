package pl.jbujak.ecommerse.catalog.payU;

public class AccessTokenResponse {
    String access_token;

    public String getAccessToken(){
        return this.access_token;
    }

    public AccessTokenResponse setAccessResponse(String access_token){
        this.access_token = access_token;
        return this;
    }

}
