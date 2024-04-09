package Model;

import DataBase.CRUD;
import DataBase.ConnectionDB;
import Entity.Contratacion;
import Entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {

    public Object insert(Object obj) {
        Connection objConnectio = ConnectionDB.openConnection();
        Contratacion objInsert = (Contratacion) obj;
        try{
            String sql = "INSERT INTO contratacion (vacante_id_fk ,coder_id_fk,estado, salario) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnectio.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objInsert.getIdVacante());
            objPrepare.setInt(2,objInsert.getIdCoder());

            String estado = "";
            if (objInsert.getEstado() == Estado.Activo){
                estado= "Activo";
            }else {
                estado ="Inactivo";
            }
            objPrepare.setString(3,estado );

            objPrepare.setDouble(4,objInsert.getSalario());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                System.out.println("entro" + objInsert.toString());
                objInsert.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Hiring Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert:"+error.getMessage() );
        }
        ConnectionDB.closeConnection();
        return objInsert;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listContratacion = new ArrayList<>();
        Connection objConnection = ConnectionDB.openConnection();
        try {
            String sql = "SELECT * FROM contratacion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Contratacion objList =new Contratacion();
                objList.setId(objResult.getInt("id"));
                objList.setIdVacante(objResult.getInt("vacante_id_fk"));
                objList.setIdCoder(objResult.getInt("coder_id_fk"));
                objList.setFechaAplicacion(objResult.getString("fecha_aplicacion"));
                Estado estado =Estado.Activo;
                if (objResult.getString("estado").equals("Activo")){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objList.setEstado(estado);

                objList.setSalario(objResult.getDouble("salario"));
                listContratacion.add(objList);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "ERROR List: "+error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listContratacion;
    }

    @Override
    public boolean update(Object obj){
        Connection objCOnnection = ConnectionDB.openConnection();
        Contratacion objUpdate = (Contratacion) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE contratacion SET coder_id_fk =?,estado =?,salario=? WHERE id =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setInt(1,objUpdate.getIdCoder());

            String estado = "";
            if (objUpdate.getEstado() == Estado.Activo){
                estado= "Activo";
            }else {
                estado ="Inactivo";
            }
            objPrepare.setString(2,estado );

            objPrepare.setDouble(3,objUpdate.getSalario());
            objPrepare.setInt(4,objUpdate.getId());
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

    public boolean updateState(Object obj){
        Connection objCOnnection = ConnectionDB.openConnection();
        Vacante objUpdate = (Vacante) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE vacante SET estado =? WHERE id_vacante =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);


            String estado = "";
            if (objUpdate.getEstado() == Estado.Activo){
                estado= "Activo";
            }else {
                estado ="Inactivo";
            }
            objPrepare.setString(1,estado );

            objPrepare.setInt(2,objUpdate.getId());
            int totalRowAffect = objPrepare.executeUpdate();
            if (totalRowAffect>0){
                isUpdate=true;
                JOptionPane.showMessageDialog(null, "Status updated to inactive... ");
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
        Contratacion objDelete = (Contratacion) obj;
        boolean isDeleted=false;
        try {
            String sql ="DELETE FROM contratacion WHERE id = ?;";

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
        Contratacion objSearch = null;

        try {
            String sql = "SELECT * FROM contratacion WHERE id =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Contratacion();
                objSearch.setId(objResult.getInt("id"));
                objSearch.setIdVacante(objResult.getInt("vacante_id_fk"));
                objSearch.setIdCoder(objResult.getInt("coder_id_fk"));
                objSearch.setFechaAplicacion(objResult.getString("fecha_aplicacion"));
                Estado estado =Estado.Activo;
                if (objResult.getString("estado")=="Activo"){
                    estado=Estado.Activo;
                }else {
                    estado=Estado.Inactivo;
                }
                objSearch.setEstado(estado);

                objSearch.setSalario(objResult.getDouble("salario"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return objSearch;
    }
}
