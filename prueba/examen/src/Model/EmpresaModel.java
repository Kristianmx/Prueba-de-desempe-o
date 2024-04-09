package Model;

import DataBase.CRUD;
import DataBase.ConnectionDB;
import Entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnectio = ConnectionDB.openConnection();
        Empresa objInsert = (Empresa) obj;
        try{
            String sql = "INSERT INTO empresa ( nombre_empresa , sector , ubicacion, contacto) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnectio.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objInsert.getNombre());
            objPrepare.setString(2,objInsert.getSector());
            objPrepare.setString(3,objInsert.getUbicacion());
            objPrepare.setString(4,objInsert.getContacto());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
              objInsert.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Reservation Inserted");
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
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Empresa objList =new Empresa();
                objList.setId(objResult.getInt("id_empresa"));
                objList.setNombre(objResult.getNString("nombre_empresa"));
                objList.setSector(objResult.getNString("sector"));
                objList.setUbicacion(objResult.getString("ubicacion"));
                objList.setContacto(objResult.getNString("contacto"));
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
        Empresa objUpdate = (Empresa) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE empresa SET nombre_empresa =?, sector =?,ubicacion =?,contacto=? WHERE id_empresa =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setString(1,objUpdate.getNombre());
            objPrepare.setString(2,objUpdate.getSector());
            objPrepare.setString(3,objUpdate.getUbicacion());
            objPrepare.setString(4,objUpdate.getContacto());
            objPrepare.setInt(5,objUpdate.getId());
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
        Empresa objDelete = (Empresa) obj;
        boolean isDeleted=false;
        try {
            String sql ="DELETE FROM empresa WHERE id_empresa = ?;";

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
        Empresa objSearch = null;

        try {
            String sql = "SELECT * FROM empresa WHERE id_empresa =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objSearch = new Empresa();
                objSearch.setId(objResult.getInt("id_empresa"));
                objSearch.setNombre(objResult.getNString("nombre_empresa"));
                objSearch.setSector(objResult.getNString("sector"));
                objSearch.setUbicacion(objResult.getString("ubicacion"));
                objSearch.setContacto(objResult.getNString("contacto"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConnectionDB.closeConnection();
        return objSearch;
    }
}
