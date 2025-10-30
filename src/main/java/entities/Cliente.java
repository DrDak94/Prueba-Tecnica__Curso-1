package entities;

import jakarta.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String nombre;
    @Column(nullable = false, length = 30)
    private String apellido;
    @Column(nullable = false, length = 5)
    private char sexo;
    @Column(nullable = false, length = 50)
    private String ciudad;
    @Column(nullable = false, length = 30)
    private String fechaNacimiento;
    @Column(nullable = false, length = 30)
    private String telefono;
    @Column(nullable = false, length = 50)
    private String correo;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, char sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "id = " + id +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", sexo = " + sexo +
                ", ciudad = '" + ciudad + '\'' +
                ", fechaNacimiento = '" + fechaNacimiento + '\'' +
                ", telefono = '" + telefono + '\'' +
                ", correo = '" + correo + '\'';
    }
}
