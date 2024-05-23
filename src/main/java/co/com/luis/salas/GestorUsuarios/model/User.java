package co.com.luis.salas.GestorUsuarios.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @Column(name = "cedula")
    @ApiModelProperty("Cédula del usuario")
    private int cedula;


    @Column(name = "nombre", nullable = false)
    @ApiModelProperty("Nombre del usuario")
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    @ApiModelProperty("Email del usuario")
    private String email;

    @Column(name = "telefono", nullable = false)
    @ApiModelProperty("Teléfono del usuario")
    private String telefono;

    // Getters y setters

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
