package Model;

import DataBase.CRUD;
import DataBase.ConnectionDB;
import Entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VacanteModel implements CRUD {

    public Object insert(Object obj) {
        Connection objConnectio = ConnectionDB.openConnection();
        Vacante objInsert = (Vacante) obj;
        try{
            String sql = "INSERT INTO vacante (empresa_id_fk,titulo, descripcion, duracion , estado , tecnologia) VALUES (?,?,?,?,?,?);";

            PreparedStatement objPrepare = objConnectio.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objInsert.getId_empresa());
            objPrepare.setString(2,objInsert.getTitulo());
            objPrepare.setString(3,objInsert.getDescripcion());
            objPrepare.setString(4,objInsert.getDuracion());

            String estado = "";
            if (objInsert.getEstado() == Estado.Activo){
                estado= "Activo";
            }else {
                 estado ="Inactivo";
            }

            objPrepare.setString(5,estado );
            objPrepare.setString(6,objInsert.getTecnologia());
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                objInsert.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"vacant Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert:"+error.getMessage() );
        }
        ConnectionDB.closeConnection();
        return objInsert;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEntity = new ArrayList<>();
        Connection objConnection = ConnectionDB.openConnection();
        try {
            String sql = "SELECT * FROM vacante;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objList =new Vacante();
                objList.setId(objResult.getInt("id_vacante"));
                objList.setId_empresa(objResult.getInt("empresa_id_fk"));
                objList.setTitulo(objResult.getString("titulo"));
                objList.setDescripcion(objResult.getNString("descripcion"));
                objList.setDuracion(objResult.getNString("duracion"));

                Estado estado =Estado.Activo;
                if (objResult.getString("estado").equals("Activo")){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objList.setEstado(estado);
                objList.setTecnologia(objResult.getNString("tecnologia"));
                listEntity.add(objList);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "ERROR List: "+error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listEntity;
    }

    @Override
    public boolean update(Object obj){
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objUpdate = (Vacante) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE vacante SET empresa_id_fk =?, titulo =?,descripcion =?,duracion=? ,estado=? ,tecnologia=? WHERE id_vacante =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setInt(1,objUpdate.getId_empresa());
            objPrepare.setString(2,objUpdate.getTitulo());
            objPrepare.setString(3,objUpdate.getDescripcion());
            objPrepare.setString(4,objUpdate.getDuracion());
            String estado = "";
            if (objUpdate.getEstado() == Estado.Activo){
                estado= "Activo";
            }else {
                estado ="Inactivo";
            }
            objPrepare.setString(5,estado);
            objPrepare.setString(6,objUpdate.getTecnologia());

            objPrepare.setInt(7,objUpdate.getId());

            int totalRowAffect = objPrepare.executeUpdate();
            if (totalRowAffect>0){
                isUpdate=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Update: " + error.getMessage());
        }
        ConnectionDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objDelete = (Vacante) obj;
        boolean isDeleted=false;
        try {
            String sql ="DELETE FROM vacante WHERE id_vacante = ?;";

            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setInt(1,objDelete.getId());
            int totalAffect = objPrepare.executeUpdate();
            if (totalAffect>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null,"The delete was successfull...");
            }
        }catch (Exception error ){
            JOptionPane.showMessageDialog(null,"ERROR Delete: "+error.getMessage());
        }
        ConnectionDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object SearchById(int id) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objSearch = null;

        try {
            String sql = "SELECT * FROM vacante WHERE id_vacante =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Vacante();
                objSearch.setId(objResult.getInt("id_vacante"));
                objSearch.setId_empresa(objResult.getInt("empresa_id_fk"));
                objSearch.setTitulo(objResult.getString("titulo"));
                objSearch.setDescripcion(objResult.getNString("descripcion"));
                objSearch.setDuracion(objResult.getNString("duracion"));

                Estado estado =Estado.Activo;
                if (objResult.getString("estado")=="Activo"){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objSearch.setEstado(estado);
                objSearch.setTecnologia(objResult.getNString("tecnologia"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return objSearch;
    }
    public List<Object> SearchByTitle(String id) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objSearch = null;
        List<Object> listResult = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante WHERE titulo LIKE ?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+id +"%");
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Vacante();
                objSearch.setId(objResult.getInt("id_vacante"));
                objSearch.setId_empresa(objResult.getInt("empresa_id_fk"));
                objSearch.setTitulo(objResult.getString("titulo"));
                objSearch.setDescripcion(objResult.getNString("descripcion"));
                objSearch.setDuracion(objResult.getNString("duracion"));

                Estado estado =Estado.Activo;
                if (objResult.getString("estado")=="Activo"){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objSearch.setEstado(estado);
                objSearch.setTecnologia(objResult.getNString("tecnologia"));

                listResult.add(objSearch);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listResult;
    }
    public List<Object> SearchByTechnology(String id) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objSearch = null;
        List<Object> listResult = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante WHERE tecnologia LIKE ?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+id +"%");
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Vacante();
                objSearch.setId(objResult.getInt("id_vacante"));
                objSearch.setId_empresa(objResult.getInt("empresa_id_fk"));
                objSearch.setTitulo(objResult.getString("titulo"));
                objSearch.setDescripcion(objResult.getNString("descripcion"));
                objSearch.setDuracion(objResult.getNString("duracion"));

                Estado estado =Estado.Activo;
                if (objResult.getString("estado")=="Activo"){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objSearch.setEstado(estado);
                objSearch.setTecnologia(objResult.getNString("tecnologia"));

                listResult.add(objSearch);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listResult;
    }


}
