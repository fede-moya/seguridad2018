/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.*;
import java.text.SimpleDateFormat;
//import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ComputadoraCasa
 */
public class Consulta {

    public static String getSentencia(int switcher, String strEstado, String strEspecie, String strVeterinaria) {
        String sentenciaSQL = new String();
        switch (switcher)
        {
            case 1: 
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and denuncia.idVeterinaria = " 
                    + strVeterinaria + "\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            case 2:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and especie.idEspecie = " 
                    + strEspecie + "\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            case 3:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and especie.idEspecie = " 
                    + strEspecie + " and denuncia.idVeterinaria = " + strVeterinaria + "\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            case 4:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and denuncia.estdenuncia = '" 
                    + strEstado + "'\n"
                    + "ORDER BY idDenuncia ASC;";
                break;    
            case 5:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and denuncia.estdenuncia = '" 
                    + strEstado + "' and denuncia.idveterinaria = " + strVeterinaria +"\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            case 6:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and denuncia.estdenuncia = '" 
                    + strEstado + "' and especie.idespecie = " + strEspecie +"\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            case 7:
                sentenciaSQL = "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie and denuncia.estdenuncia = '" 
                    + strEstado + "' and especie.idespecie = " + strEspecie + " and veterinaria.idveterinaria = " + strVeterinaria + "\n"
                    + "ORDER BY idDenuncia ASC;";
                break;
            default:
                break;
        }
        return sentenciaSQL;
    }

    public static ResultSet cargaInicialDenuncia() {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL + "SELECT iddenuncia, fecdenuncia, estdenuncia, mascota.idchip, nommascota, nomraza, nomespecie, nomveterinaria\n"
                    + "FROM obligatorio.denuncia, obligatorio.veterinaria, obligatorio.mascota, obligatorio.especie, obligatorio.raza\n"
                    + "WHERE denuncia.idVeterinaria = veterinaria.idVeterinaria and denuncia.idChip = mascota.idchip and\n"
                    + "mascota.idespecie = especie.idespecie and mascota.idraza = raza.idraza and raza.idespecie = especie.idespecie \n"
                    + "ORDER BY idDenuncia ASC;";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }

    public static ResultSet actualizarDenuncias(boolean estado, int idEspecie, int idVeterinaria) throws SQLException {

        try {
            String strEstado = new String();
            String strEspecie = new String();
            String strVeterinaria = new String();
            String sentenciaSQL = new String();

            if ((!estado) && (idEspecie == 0) && (idVeterinaria == 0)) {
                ResultSet rs = cargaInicialDenuncia();
                return rs;
            } else {
                Conector.abrirConexion();
                if ((!estado) && (idEspecie == 0) && (idVeterinaria != 0)) {
                    strVeterinaria = String.valueOf(idVeterinaria);
                    sentenciaSQL = getSentencia(1, strEstado, strEspecie, strVeterinaria);
                    Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                    return Conector.resultado;
                } else {
                    if ((!estado) && (idEspecie != 0) && (idVeterinaria == 0)) {
                        strEspecie = String.valueOf(idEspecie);
                        sentenciaSQL = getSentencia(2, strEstado, strEspecie, strVeterinaria);
                        Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                        return Conector.resultado;
                    } else {
                        if ((!estado) && (idEspecie != 0) && (idVeterinaria != 0)) {
                            strEspecie = String.valueOf(idEspecie);
                            strVeterinaria = String.valueOf(idVeterinaria);
                            sentenciaSQL = getSentencia(3, strEstado, strEspecie, strVeterinaria);
                            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                            return Conector.resultado;
                        } else {
                            if ((estado) && (idEspecie == 0) && (idVeterinaria == 0)) {
                                strEstado = "P";
                                sentenciaSQL = getSentencia(4, strEstado, strEspecie, strVeterinaria);
                                Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                                return Conector.resultado;
                            } else {
                                if ((estado) && (idEspecie == 0) && (idVeterinaria != 0)) {
                                    strEstado = "P";
                                    strVeterinaria = String.valueOf(idVeterinaria);
                                    sentenciaSQL = getSentencia(5, strEstado, strEspecie, strVeterinaria);
                                    Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                                    return Conector.resultado;
                                } else {
                                    if ((estado) && (idEspecie != 0) && (idVeterinaria == 0)) {
                                        strEstado = "P";
                                        strEspecie = String.valueOf(idEspecie);
                                        sentenciaSQL = getSentencia(6, strEstado, strEspecie, strVeterinaria);
                                        Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                                        return Conector.resultado;
                                    } else {
                                        strEstado = "P";
                                        strEspecie = String.valueOf(idEspecie);
                                        strVeterinaria = String.valueOf(idVeterinaria);
                                        sentenciaSQL = getSentencia(7, strEstado, strEspecie, strVeterinaria);
                                        Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
                                        return Conector.resultado;
                                    }
                                }

                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("Error en la actualización.");
            return null;
        }

    }

    public static ResultSet cargaDenuncia(int idDenuncia) {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = sentenciaSQL + "SELECT idDenuncia, fecDenuncia, obsDenuncia, estDenuncia, nomMascota, nomVeterinaria "
                    + "FROM obligatorio.DENUNCIA, obligatorio.MASCOTA, obligatorio.VETERINARIA "
                    + "WHERE DENUNCIA.idDenuncia = '" + String.valueOf(idDenuncia) + "' and DENUNCIA.idChip = MASCOTA.idChip and DENUNCIA.idVeterinaria = VETERINARIA.idVeterinaria;";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }

    public static ResultSet cargaInicialColaboraciones(int idDenuncia, int idChip) {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = "SELECT idColaboracion, fecColaboracion, obsColaboracion, nomPersona FROM obligatorio.COLABORACION, obligatorio.PERSONA ";
            sentenciaSQL = sentenciaSQL + "WHERE COLABORACION.idDenuncia = " + String.valueOf(idDenuncia) + " and COLABORACION.idChip = " + String.valueOf(idChip)
                    + " and COLABORACION.docPersona = PERSONA.docPersona ORDER BY (idColaboracion) ASC;";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error en la selección de colaboraciones.");
            return null;
        }

    }

    public static ResultSet cargaInicialEspecies() {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = "SELECT idEspecie, nomEspecie FROM obligatorio.ESPECIE;";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error en la selección de especies.");
            return null;
        }
    }

    public static ResultSet cargaInicialVeterinarias() {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = "SELECT idVeterinaria, nomVeterinaria FROM obligatorio.VETERINARIA;";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error en la selección de veterinarias.");
            return null;
        }
    }
    
    public static ResultSet chequearPersona(String documento) {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = "SELECT nomPersona FROM obligatorio.PERSONA WHERE PERSONA.docPersona = '" + documento + "';";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error en la selección de persona.");
            return null;
        }
    }
    
    public static ResultSet buscarColaboracion(int idCol, int idDen, int idChip) {
        try {
            Conector.abrirConexion();
            String sentenciaSQL = new String();
            sentenciaSQL = "SELECT * FROM obligatorio.COLABORACION WHERE COLABORACION.idColaboracion = " + String.valueOf(idCol) 
                         + " and COLABORACION.idDenuncia = " + String.valueOf(idDen) + " and COLABORACION.idChip = " + String.valueOf(idChip) + ";";
            Conector.resultado = Conector.sentencia.executeQuery(sentenciaSQL);
            return Conector.resultado;
        } catch (Exception ex) {
            System.out.println("Error en la selección de colaboracion.");
            return null;
        }
    }

}
