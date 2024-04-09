package Controller;

import Entity.Empresa;
import Model.EmpresaModel;

import javax.swing.*;

public class EmpresaController {
    public void MenuEmpresa(){

        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF COMPANY:
                   1). List .
                   2). Return the Menu Principal.
                                   
                   Choose a option:
                   """);
            switch (option) {
                case "1":
                    getAll();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        }while (!option.equals("2"));
    }

    public static void Create(){
        EmpresaModel objModel = new EmpresaModel();
        String input = JOptionPane.showInputDialog("Insert the Name of the company:");
        String input2 = JOptionPane.showInputDialog("Insert the sector of the company:");
        String input3 = JOptionPane.showInputDialog("Insert the location of the company:");
        String input4 = JOptionPane.showInputDialog("Insert the contact of the company:");

        Empresa objCreate = new Empresa();
        objCreate.setNombre(input);
        objCreate.setSector(input2);
        objCreate.setUbicacion(input3);
        objCreate.setContacto(input4);

        objCreate =(Empresa) objModel.insert(objCreate);
        JOptionPane.showMessageDialog(null,objCreate.toString());

    }
    public static  void getAll(){
        EmpresaModel objModel = new EmpresaModel();
        String listString = "🏢 List Company\n";
        for (Object i: objModel.findAll()){
            Empresa objList = (Empresa) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static String getAllString(){
        EmpresaModel objModel = new EmpresaModel();
        String listString = "🏢 List Company\n";
        for (Object i: objModel.findAll()){
            Empresa objList = (Empresa) i;
            listString+=objList.toString()+"\n";
        }
        return listString;
    }
    public static  void  update(){
        EmpresaModel objModel = new EmpresaModel();
        String listString =getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listString+"\n Enter the Id of the company to update:"));
        Empresa objUpdate = (Empresa) objModel.SearchById(id);

        if (objUpdate==null){
            JOptionPane.showMessageDialog(null,"Plane not found");
        }else {
            String input = JOptionPane.showInputDialog("Insert the Name of the company:", objUpdate.getNombre());
            String input2 = JOptionPane.showInputDialog("Insert the sector of the company:",objUpdate.getSector());
            String input3 = JOptionPane.showInputDialog("Insert the location of the company:",objUpdate.getUbicacion());
            String input4 = JOptionPane.showInputDialog("Insert the contact of the company:",objUpdate.getContacto());

            objUpdate.setNombre(input);
            objUpdate.setSector(input2);
            objUpdate.setUbicacion(input3);
            objUpdate.setContacto(input4);
            objModel.update(objUpdate);
        }

    }
    public static void delete() {
        EmpresaModel objModel = new EmpresaModel();
        String listString = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listString + "\n Enter the Id the company to delete"));

        Empresa objDelete = (Empresa) objModel.SearchById(idDelete);

        int Confirm = 1;
        if (objDelete == null) {
            JOptionPane.showMessageDialog(null, "company not found");
        } else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the company?\n" + objDelete.toString());
            if (Confirm == 0) objModel.delete(objDelete);
        }
    }
}
