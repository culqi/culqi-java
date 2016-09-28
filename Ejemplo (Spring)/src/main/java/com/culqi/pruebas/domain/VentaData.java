package com.culqi.pruebas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

/**
 * Created by admin_InSolutions on 12/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentaData {

    private static final Logger log = Logger.getLogger(VentaData.class);

    @JsonProperty
    private String token;

    @JsonProperty
    private String codigo_comercio;

    @JsonProperty
    private String moneda;

    @JsonProperty
    private String monto;

    @JsonProperty
    private String descripcion;

    @JsonProperty
    private String pedido;

    @JsonProperty
    private String codigo_pais;

    @JsonProperty
    private String ciudad;

    @JsonProperty
    private String usuario;

    @JsonProperty
    private String direccion;

    @JsonProperty
    private String telefono;

    @JsonProperty
    private String nombres;

    @JsonProperty
    private String apellidos;

    @JsonProperty
    private String correo_electronico;

    public String getToken() {
        return token;
    }

    public VentaData setToken(String token) {
        this.token = token;
        return this;
    }

    public String getCodigo_comercio() {
        return codigo_comercio;
    }

    public VentaData setCodigo_comercio(String codigo_comercio) {
        this.codigo_comercio = codigo_comercio;
        return this;
    }

    public String getMoneda() {
        return moneda;
    }

    public VentaData setMoneda(String moneda) {
        this.moneda = moneda;
        return this;
    }

    public String getMonto() {
        return monto;
    }

    public VentaData setMonto(String monto) {
        this.monto = monto;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public VentaData setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getPedido() {
        return pedido;
    }

    public VentaData setPedido(String pedido) {
        this.pedido = pedido;
        return this;
    }

    public String getCodigo_pais() {
        return codigo_pais;
    }

    public VentaData setCodigo_pais(String codigo_pais) {
        this.codigo_pais = codigo_pais;
        return this;
    }

    public String getCiudad() {
        return ciudad;
    }

    public VentaData setCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public VentaData setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public VentaData setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public VentaData setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getNombres() {
        return nombres;
    }

    public VentaData setNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public VentaData setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public VentaData setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
        return this;
    }

    @Override
    public String toString() {
        return "VentaData{" +
                "token='" + token + '\'' +
                ", codigo_comercio='" + codigo_comercio + '\'' +
                ", moneda='" + moneda + '\'' +
                ", monto='" + monto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pedido='" + pedido + '\'' +
                ", codigo_pais='" + codigo_pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", usuario='" + usuario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo_electronico='" + correo_electronico + '\'' +
                '}';
    }
}
