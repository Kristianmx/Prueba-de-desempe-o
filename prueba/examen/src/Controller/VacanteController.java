package Controller;

import Entity.Empresa;
import Entity.Vacante;
import Model.EmpresaModel;
import Model.Estado;
import Model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {
    public void MenuVacante(){

        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF VACANT:
                   1). Insert .
                   2). List .
                   3). Update .
                   4). Delete .
                   5). Search...
                   6). Return the Menu Principal.
                                   
                   Choose a option:
                   """);
            switch (option) {
                case "1":
                    Create();
                    break;
                case "2":
                    getAll();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    String optSearch ="";
                    do {


                        optSearch = JOptionPane.showInputDialog("""
                                Search
                                1). Search by cohorte.
                                2). Search by Technology.
                                3). Return...
                                                        
                                choose a option:
                                """);
                        switch (optSearch) {
                            case "1":
                                getAllTitle();
                                break;
                            case "2":
                                getAllTechnology();
                                break;

                            case "3":
                                JOptionPane.showMessageDialog(null, "Return the menu");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not valid...");
                                break;
                        }
                    }while (!optSearch.equals("3"));

                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        }while (!option.equals("6"));
    }
    public static void Create(){
        VacanteModel objModel = new VacanteModel();
        EmpresaModel objEmpresaModel = new EmpresaModel();
        List<Object> listEmpresas = objEmpresaModel.findAll();
        String[] choices = new String[listEmpresas.size()];
        int index =0;
        for (Object i: listEmpresas){
            Empresa empresa = (Empresa) i;
            choices[index]=empresa.getNombre();
            index++;
        }

        String input = (String) JOptionPane.showInputDialog(null, "\n Select a company...", "Company available:", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

       int id_empresa =0;
       for (Object i:listEmpresas){
           Empresa empresa = (Empresa) i;
           String nombre = empresa.getNombre();
           if (nombre.equals(input))id_empresa = empresa.getId();
       }

        String input2 = JOptionPane.showInputDialog("Insert the title by vacant:");
        String input3 = JOptionPane.showInputDialog("Insert the description of the vacant:");
        String input4 = JOptionPane.showInputDialog("Insert the duration of the vacant:");
            String[] lago= new  String[2];
            lago[0]= "Activo";
            lago[1]= "Inactivo";
        String input5 = (String) JOptionPane.showInputDialog(null, "\n Select the state of the vacant...", "State available:", JOptionPane.QUESTION_MESSAGE, null, lago, lago[0]);

        String input6 = JOptionPane.showInputDialog("Insert the technology of the vacant:");

        Vacante objCreate = new Vacante();
        objCreate.setId_empresa(id_empresa);
        objCreate.setTitulo(input2);
        objCreate.setDescripcion(input3);
        objCreate.setDuracion(input4);
        Estado estafo = Estado.Activo;
        if (!input5.equals("Activo")){
            estafo=Estado.Inactivo;
        }
        objCreate.setEstado(estafo);
        objCreate.setTecnologia(input6);
        objCreate =(Vacante) objModel.insert(objCreate);
        JOptionPane.showMessageDialog(null,objCreate.toString());

    }
    public static  void getAll(){
        VacanteModel objModel = new VacanteModel();
        String listString = "Vacant list\n";
        for (Object i: objModel.findAll()){
            Vacante objList = (Vacante) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static  void getAllTitle(){
        String imput = JOptionPane.showInputDialog("\"Enter the title to search");
        VacanteModel objModel = new VacanteModel();
        String listString = "Vacant list\n";
        for (Object i: objModel.SearchByTitle(imput)){
            Vacante objList = (Vacante) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static  void getAllTechnology(){
        String imput = JOptionPane.showInputDialog("\"Enter the technology to search");
        VacanteModel objModel = new VacanteModel();
        String listString = "Vacant list\n";
        for (Object i: objModel.SearchByTechnology(imput)){
            Vacante objList = (Vacante) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static String getAllString(){
        VacanteModel objModel = new VacanteModel();
        String listString = "Vacant list\n";
        for (Object i: objModel.findAll()){
            Vacante objList = (Vacante) i;
            listString+=objList.toString()+"\n";
        }
        return listString;
    }
    public static  void  update(){
        VacanteModel objModel = new VacanteModel();
        String listString =getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listString+"\n Enter the Id of the vacant to update:"));
        Vacante objUpdate = (Vacante) objModel.SearchById(id);

        if (objUpdate==null){
            JOptionPane.showMessageDialog(null,"vacant not found");
        }else {
            EmpresaModel objEmpresaModel = new EmpresaModel();
            List<Object> listEmpresas = objEmpresaModel.findAll();
            String[] choices = new String[listEmpresas.size()];
            int index =0;
            for (Object i: listEmpresas){
                Empresa empresa = (Empresa) i;
                choices[index]=empresa.getNombre();
                index++;
            }

            String input = (String) JOptionPane.showInputDialog(null, "\n Select a company...", "Company available:", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

            int id_empresa =0;
            for (Object i:listEmpresas){
                Empresa empresa = (Empresa) i;
                String nombre = empresa.getNombre();
                if (nombre.equals(input))id_empresa = empresa.getId();
            }

            String input2 = JOptionPane.showInputDialog("Insert the title by vacant:",objUpdate.getTitulo());
            String input3 = JOptionPane.showInputDialog("Insert the description of the vacant:",objUpdate.getDescripcion());
            String input4 = JOptionPane.showInputDialog("Insert the duration of the vacant:",objUpdate.getDuracion());
            String[] lago= new  String[2];
            lago[0]= "Activo";
            lago[1]= "Inactivo";
            String input5 = (String) JOptionPane.showInputDialog(null, "\n Select the state of the vacant...", "State available:", JOptionPane.QUESTION_MESSAGE, null, lago, lago[0]);

            String input6 = JOptionPane.showInputDialog("Insert the technology of the vacant:",objUpdate.getTecnologia());

            objUpdate.setId_empresa(id_empresa);
            objUpdate.setTitulo(input2);
            objUpdate.setDescripcion(input3);
            objUpdate.setDuracion(input4);
            Estado estafo = Estado.Activo;
            if (!input5.equals("Activo")){
                estafo=Estado.Inactivo;
            }
            objUpdate.setEstado(estafo);
            objUpdate.setTecnologia(input6);

            objModel.update(objUpdate);
        }

    }
    public static void delete() {
        VacanteModel objModel = new VacanteModel();
        String listAviones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Enter the Id the plane to delete"));

        Vacante objDelete = (Vacante) objModel.SearchById(idDelete);

        int Confirm = 1;
        if (objDelete == null) {
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the specialty?\n" + objDelete.toString());
            if (Confirm == 0) objModel.delete(objDelete);
        }
    }
}
