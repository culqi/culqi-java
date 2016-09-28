package com.culqi.pruebas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

/**
 * Created by admin_InSolutions on 14/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenData {

    private static final Logger log = Logger.getLogger(TokenData.class);

    @JsonProperty
    private String correo_electronico;

    @JsonProperty
    private String nombre;

    @JsonProperty
    private String apellido;

    @JsonProperty
    private String numero;

    @JsonProperty
    private String cvv;

    @JsonProperty
    private String m_exp;

    @JsonProperty
    private String a_exp;

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public TokenData setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TokenData setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public TokenData setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public TokenData setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public TokenData setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public String getM_exp() {
        return m_exp;
    }

    public TokenData setM_exp(String m_exp) {
        this.m_exp = m_exp;
        return this;
    }

    public String getA_exp() {
        return a_exp;
    }

    public TokenData setA_exp(String a_exp) {
        this.a_exp = a_exp;
        return this;
    }

    @Override
    public String toString() {
        return "TokenData{" +
                "correo_electronico='" + correo_electronico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numero='" + numero + '\'' +
                ", cvv='" + cvv + '\'' +
                ", m_exp='" + m_exp + '\'' +
                ", a_exp='" + a_exp + '\'' +
                '}';
    }
}
