
package Objects;


public abstract class Ubicación {
    protected String provincia;
    protected String Canton;
    protected String Distrito;

    public Ubicación(String provincia, String Canton, String Distrito) {
        this.provincia = provincia;
        this.Canton = Canton;
        this.Distrito = Distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String Canton) {
        this.Canton = Canton;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }
    
    
}
