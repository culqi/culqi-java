package com.culqi.pruebas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin_InSolutions on 9/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuscripcionData {

    private static final Logger log = Logger.getLogger(SuscripcionData.class);

    @JsonProperty
    private String codigo_comercio;

    @JsonProperty
    private String codigo_pais;

    @JsonProperty
    private String direccion;

    @JsonProperty
    private String ciudad;

    @JsonProperty
    private String telefono;

    @JsonProperty
    private String nombre;

    @JsonProperty
    private String correo_electronico;

    @JsonProperty
    private String apellido;

    @JsonProperty
    private String usuarioId;

    @JsonProperty
    private String plan_id;

    @JsonProperty
    private String token;

    @JsonProperty
    private Boolean cargos_predefinidos;

    private List<CargosData> generarCargos;

    public static Logger getLog() {
        return log;
    }

    public String getCodigo_comercio() {
        return codigo_comercio;
    }

    public SuscripcionData setCodigo_comercio(String codigo_comercio) {
        this.codigo_comercio = codigo_comercio;
        return this;
    }

    public String getCodigo_pais() {
        return codigo_pais;
    }

    public SuscripcionData setCodigo_pais(String codigo_pais) {
        this.codigo_pais = codigo_pais;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public SuscripcionData setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getCiudad() {
        return ciudad;
    }

    public SuscripcionData setCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public SuscripcionData setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public SuscripcionData setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public SuscripcionData setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public SuscripcionData setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public SuscripcionData setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public SuscripcionData setPlan_id(String plan_id) {
        this.plan_id = plan_id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public SuscripcionData setToken(String token) {
        this.token = token;
        return this;
    }

    public Boolean getCargos_predefinidos() {
        return cargos_predefinidos;
    }

    public SuscripcionData setCargos_predefinidos(Boolean cargos_predefinidos) {
        this.cargos_predefinidos = cargos_predefinidos;
        return this;
    }

    public List<CargosData> getGenerarCargos() {
        return generarCargos();
    }

    public SuscripcionData setGenerarCargos(List<CargosData> generarCargos) {
        this.generarCargos = generarCargos;
        return this;
    }

    private static class CargosData {
        private Integer monto;
        private String fecha;

        public Integer getMonto() {
            return monto;
        }

        public CargosData setMonto(Integer monto) {
            this.monto = monto;
            return this;
        }

        public String getFecha() {
            return fecha;
        }

        public CargosData setFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }
    }

    private List<CargosData> generarCargos(){
        List<CargosData> list = new ArrayList<>();
        Calendar fechaCalendar = new GregorianCalendar();
        SimpleDateFormat fechaSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        int m, ms;
        for (int i=0;i<4;i++){
            m = i*10+100;
            ms = i;
            CargosData cargosData = new CargosData();
            fechaCalendar.add(Calendar.MONTH, ms);
            fecha = fechaCalendar.getTime();
            String fechaAleatoria = fechaSimpleFormat.format(fecha);
            cargosData
                    .setMonto(m)
                    .setFecha(fechaAleatoria);
            list.add(cargosData);
            log.info(list.get(i).getFecha());
        }
        return list;
    }
}
