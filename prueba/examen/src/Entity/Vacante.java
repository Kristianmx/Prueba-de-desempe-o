package Entity;

import Model.Estado;

public class Vacante {
    private int id;
    private int id_empresa;
    private String titulo;
    private String Descripcion;
    private String duracion;
    private Estado estado;
    private String tecnologia;

    public Vacante() {
    }

    public Vacante(int id, int id_empresa, String titulo, String descripcion, String duracion, Estado estado, String tecnologia) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.titulo = titulo;
        Descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public String toString() {
        return "Vacante (" +
                " id= " + id +
                " / id_empresa= " + id_empresa +
                " / titulo= '" + titulo + '\'' +
                " / Descripcion= '" + Descripcion + '\'' +
                " / duracion= '" + duracion + '\'' +
                " / estado= " + estado +
                " / tecnologia= '" + tecnologia + '\'' +
                " )";
    }
}
