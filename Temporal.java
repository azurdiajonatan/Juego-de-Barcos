package proyectolfp;


public class Temporal {

private String lexema;
private String token;
    
public Temporal(String lexema,String token){
    this.lexema = lexema;
    this.token = token;
}

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
