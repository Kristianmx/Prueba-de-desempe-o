package Entity;

import Model.Estado;

public class Contratacion {
    private int id;
    private int idVacante;
    private int idCoder;
    private String fechaAplicacion;
    private Estado estado;
    private double salario;

    public Contratacion() {
    }

    public Contratacion(int id, int idVacante, int idCoder, String fechaAplicacion, Estado estado, double salario) {
        this.id = id;
        this.idVacante = idVacante;
        this.idCoder = idCoder;
        this.fechaAplicacion = fechaAplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public int getIdCoder() {
        return idCoder;
    }

    public void setIdCoder(int idCoder) {
        this.idCoder = idCoder;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Contratacion (" +
                " id= " + id +
                " / idVacante= " + idVacante +
                " / idCoder= " + idCoder +
                " / fechaAplicación= '" + fechaAplicacion + '\'' +
                " / estado= " + estado +
                " / salario= " + salario +
                " )";
    }
}
