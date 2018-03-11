/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Eduardo DAW
 */
public class Carrito
{
    private int id;
    private int idObjeto;
    private String n_per;
    private Date fecha;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIdObjeto()
    {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto)
    {
        this.idObjeto = idObjeto;
    }

    public String getN_per()
    {
        return n_per;
    }

    public void setN_per(String n_per)
    {
        this.n_per = n_per;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
}
