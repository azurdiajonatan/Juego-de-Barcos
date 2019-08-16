
package proyectolfp;



public class Barco {

String ID;
String Nombre;
int Tamano;
String Color;
String Tipo;

public Barco(String ID, String Nombre, int Tamano, String Color, String Tipo){
    this.ID = ID;
    this.Nombre = Nombre;
    this.Tamano = Tamano;
    this.Color = Color;
    this.Tipo = Tipo;
}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getTamano() {
        return Tamano;
    }

    public void setTamano(int Tamano) {
        this.Tamano = Tamano;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }




}
