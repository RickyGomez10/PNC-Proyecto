package com.uca.capas.utils;

public class CookieData {

    private String username;
    private Integer rol;

    public CookieData(String username, Integer rol) {
        this.username = username;
        this.rol = rol;
    }

    public CookieData(String data){
        String[] datos = data.split(",");
        username = datos[0];
        rol = Integer.parseInt(datos[1]);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return username+","+rol;
    }
}
