package Model;

import DataBase.CRUD;
import DataBase.ConnectionDB;
import Entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    public Object insert(Object obj)  {
        Connection objConnectio = ConnectionDB.openConnection();
        Coder objInsert = (Coder) obj;
        try{
            String sql = "INSERT INTO coder (nombre,apellidos, documento, cohorte , cv ,clan) VALUES (?,?,?,?,?,?);";

            PreparedStatement objPrepare = objConnectio.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objInsert.getNombre());
            objPrepare.setString(2,objInsert.getApellidos());
            objPrepare.setString(3,objInsert.getDocumento());
            objPrepare.setInt(4,objInsert.getCohorte());
            objPrepare.setString(5,objInsert.getCv());
            objPrepare.setString(6,objInsert.getClan());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                objInsert.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Coder Inserted");
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
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Coder objList =new Coder();
                objList.setId(objResult.getInt("id_coder"));
                objList.setNombre(objResult.getString("nombre"));
                objList.setApellidos(objResult.getString("apellidos"));
                objList.setDocumento(objResult.getString("documento"));
                objList.setCohorte(objResult.getInt("cohorte"));
                objList.setCv(objResult.getString("CV"));
                objList.setClan(objResult.getString("clan"));
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
        Coder objUpdate = (Coder) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE coder SET nombre =?, apellidos =?,documento =?,cohorte=?,CV=?,clan=? WHERE id_coder =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);


            objPrepare.setString(1,objUpdate.getNombre());
            objPrepare.setString(2,objUpdate.getApellidos());
            objPrepare.setString(3,objUpdate.getDocumento());
            objPrepare.setInt(4,objUpdate.getCohorte());
            objPrepare.setString(5,objUpdate.getCv());
            objPrepare.setString(6,objUpdate.getClan());
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
        Coder objDelete = (Coder) obj;
        boolean isDeleted=false;
        try {
            String sql ="DELETE FROM coder WHERE id_coder = ?;";

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
        Coder objSearch = null;

        try {
            String sql = "SELECT * FROM coder WHERE id_coder =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Coder();
                objSearch.setId(objResult.getInt("id_coder"));
                objSearch.setNombre(objResult.getString("nombre"));
                objSearch.setApellidos(objResult.getString("apellidos"));
                objSearch.setDocumento(objResult.getString("documento"));
                objSearch.setCohorte(objResult.getInt("cohorte"));
                objSearch.setCv(objResult.getString("CV"));
                objSearch.setClan(objResult.getString("clan"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return objSearch;
    }
    public List<Object> SearchByCohorte(int id) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Coder objSearch = null;
        List<Object> listResult= new ArrayList<>();
        try {
            String sql = "SELECT * FROM coder WHERE cohorte =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Coder();
                objSearch.setId(objResult.getInt("id_coder"));
                objSearch.setNombre(objResult.getString("nombre"));
                objSearch.setApellidos(objResult.getString("apellidos"));
                objSearch.setDocumento(objResult.getString("documento"));
                objSearch.setCohorte(objResult.getInt("cohorte"));
                objSearch.setCv(objResult.getString("CV"));
                objSearch.setClan(objResult.getString("clan"));
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
        Coder objSearch = null;
        List<Object> listResult= new ArrayList<>();
        try {
            String sql = "SELECT * FROM coder WHERE CV LIKE ?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+id+"%");
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Coder();
                objSearch.setId(objResult.getInt("id_coder"));
                objSearch.setNombre(objResult.getString("nombre"));
                objSearch.setApellidos(objResult.getString("apellidos"));
                objSearch.setDocumento(objResult.getString("documento"));
                objSearch.setCohorte(objResult.getInt("cohorte"));
                objSearch.setCv(objResult.getString("CV"));
                objSearch.setClan(objResult.getString("clan"));
                listResult.add(objSearch);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listResult;
    }
    public List<Object> SearchByClan(String id) {
        Connection objCOnnection = ConnectionDB.openConnection();
        Coder objSearch = null;
        List<Object> listResult= new ArrayList<>();
        try {
            String sql = "SELECT * FROM coder WHERE clan LIKE ?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+id+"%");
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Coder();
                objSearch.setId(objResult.getInt("id_coder"));
                objSearch.setNombre(objResult.getString("nombre"));
                objSearch.setApellidos(objResult.getString("apellidos"));
                objSearch.setDocumento(objResult.getString("documento"));
                objSearch.setCohorte(objResult.getInt("cohorte"));
                objSearch.setCv(objResult.getString("CV"));
                objSearch.setClan(objResult.getString("clan"));
                listResult.add(objSearch);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return listResult;
    }
}
