package Controller;

import Entity.Coder;
import Entity.Contratacion;
import Entity.Empresa;
import Entity.Vacante;
import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionController {
    public void MenuContratacion(){

        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF HIRING:
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
                    // Buscador
                    JOptionPane.showMessageDialog(null, "There are no Search Engines currently available");
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
        ContratacionModel objModel = new ContratacionModel();
        VacanteModel vacantesModel = new VacanteModel();
        CoderModel coderModel = new CoderModel();

        List<Object> listVacante = vacantesModel.findAll();
        List<Object> listCoder = coderModel.findAll();

        String[] choiceCoder = new String[listCoder.size()];

        List<Object> listActivos = new ArrayList<>();
        int indexV  =0;
        for ( Object i : listVacante){
            Vacante vacante = (Vacante) i;
            if (vacante.getEstado()== Estado.Activo){
                listActivos.add(vacante);
            }
        }
        String[] choiceVacantes = new String[listActivos.size()];
        for (Object i : listVacante){
            Vacante vacante = (Vacante) i;
            if (vacante.getEstado()== Estado.Activo){
                choiceVacantes[indexV]= "Titulo: "+ vacante.getTitulo()+ " / "+ "Descripción: "+ vacante.getDescripcion();
                indexV++;
            }
        }
        String inputVacantes = (String) JOptionPane.showInputDialog(null, "\n Select a vacant...", "Active vacancies:", JOptionPane.QUESTION_MESSAGE, null, choiceVacantes, choiceVacantes[0]);

        int idVacantes=0;
        for(Object i: listVacante){
            Vacante vacante = (Vacante) i;
            String comparar = "Titulo: "+ vacante.getTitulo()+ " / "+ "Descripción: "+ vacante.getDescripcion();
            if (comparar.equals(inputVacantes)) idVacantes=vacante.getId();
        }

     int rei= 0;
        int idCoder=0;

        while (rei!=1){
            int indexC =0;
            for (Object i : listCoder){
                Coder coder = (Coder) i;
                choiceCoder[indexC]= "nombre Completo : "+coder.getNombre() +" "+ coder.getApellidos() + " / " + "especialidad: " +coder.getCv();
                indexC++;

            }
            String inputcoder = (String) JOptionPane.showInputDialog(null, "\n Select a coder...", "Active Coders:", JOptionPane.QUESTION_MESSAGE, null, choiceCoder, choiceCoder[0]);


            for(Object i: listCoder){
                Coder coder = (Coder) i;
                String comparar = "nombre Completo : "+coder.getNombre() +" "+ coder.getApellidos() + " / " + "especialidad: " +coder.getCv();
                if (comparar.equals(inputcoder)) idCoder=coder.getId();
            }
            Vacante objVacante = (Vacante) vacantesModel.SearchById(idVacantes);
            Coder objCoder = (Coder) coderModel.SearchById(idCoder);
            if (objCoder.getCv().equals(objVacante.getTecnologia())){
                rei=1;
        }else {
                JOptionPane.showMessageDialog(null, """
                        The selected coder does not have the technology for this vacancy.
                        Please select another coder...
                          """);
            }
    }
        String[] lago= new  String[2];
        lago[0]= "Activo";
        lago[1]= "Inactivo";
        String input5 = (String) JOptionPane.showInputDialog(null, "\n Select the state of the vacant...", "State available:", JOptionPane.QUESTION_MESSAGE, null, lago, lago[0]);

        double input = Double.parseDouble( JOptionPane.showInputDialog("Insert the salary of coder"));
        Contratacion objCreate = new Contratacion();

        objCreate.setIdVacante(idVacantes);
        objCreate.setIdCoder(idCoder);
        Estado estafo = Estado.Activo;
        if (!input5.equals("Activo")){
            estafo=Estado.Inactivo;
        }
        objCreate.setEstado(estafo);
        objCreate.setSalario(input);

        objCreate =(Contratacion) objModel.insert(objCreate);
        EmpresaModel objEmpresaModel = new EmpresaModel();
        CoderModel objCoderModel = new CoderModel();
        Vacante objVacante = (Vacante) vacantesModel.SearchById(idVacantes);
        Empresa objEmpresa = ( Empresa)   objEmpresaModel.SearchById(objVacante.getId_empresa());
        Coder objCoder = (Coder) objCoderModel.SearchById(idCoder);
        objVacante.setEstado(Estado.Inactivo);
        objModel.updateState(objVacante);
        JOptionPane.showMessageDialog(null,"Vacante: ( Titulo: "+ objVacante.getTitulo()+ ",  Descrpción: "+objVacante.getDescripcion()+ ")\n Empresa ( Nombre: "+objEmpresa.getNombre() +  "  Ubicación: "+objEmpresa.getUbicacion() + ")\n Coder ( Nombre: "+ objCoder.getNombre() +"   Apellidos: "+objCoder.getApellidos() +"  Documento: "+objCoder.getDocumento() + "   Tecnología: " + objCoder.getCv() +"  Salario: " + objCreate.getSalario() +" )");


    }
    public static  void getAll(){
        ContratacionModel objModel = new ContratacionModel();
        String listString = "hiring list\n";
        for (Object i: objModel.findAll()){
            Contratacion objList = (Contratacion) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static String getAllString(){
        ContratacionModel objModel = new ContratacionModel();
        String listString = "✈hiring list\n";
        for (Object i: objModel.findAll()){
            Contratacion objList = (Contratacion) i;
            listString+=objList.toString()+"\n";
        }
        return listString;
    }
    public static  void  update(){
        ContratacionModel objModel = new ContratacionModel();
        String listString =getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listString+"\n Enter the Id of the hiring to update:"));
        Contratacion objUpdate = (Contratacion) objModel.SearchById(id);

        if (objUpdate==null){
            JOptionPane.showMessageDialog(null,"hiring not found");
        }else {
            VacanteModel vacantesModel = new VacanteModel();
            CoderModel coderModel = new CoderModel();

            List<Object> listCoder = coderModel.findAll();

            String[] choiceCoder = new String[listCoder.size()];



            int rei= 0;
            int idCoder=0;
            int indexV =0;
            while (rei!=1){

                for (Object i : listCoder){
                    Coder coder = (Coder) i;
                    choiceCoder[indexV]= "nombre Completo : "+coder.getNombre() +" "+ coder.getApellidos() + " / " + "especialidad: " +coder.getCv();
                    indexV++;

                }
                String inputcoder = (String) JOptionPane.showInputDialog(null, "\n Select a coder...", "Active Coders:", JOptionPane.QUESTION_MESSAGE, null, choiceCoder, choiceCoder[0]);


                for(Object i: listCoder){
                    Coder coder = (Coder) i;
                    String comparar = "nombre Completo : "+coder.getNombre() +" "+ coder.getApellidos() + " / " + "especialidad: " +coder.getCv();
                    if (comparar.equals(inputcoder)) idCoder=coder.getId();
                }
                Vacante objVacante = (Vacante) vacantesModel.SearchById(objUpdate.getIdVacante());
                Coder objCoder = (Coder) coderModel.SearchById(idCoder);
                if (objCoder.getCv().equals(objVacante.getTecnologia())){
                    rei=1;
                }else {
                    JOptionPane.showMessageDialog(null, """
                        The selected coder does not have the technology for this vacancy.
                        Please select another coder...
                          """);
                }
            }
            String[] lago= new  String[2];
            lago[0]= "Activo";
            lago[1]= "Inactivo";
            String input5 = (String) JOptionPane.showInputDialog(null, "\n Select the state of the vacant...", "State available:", JOptionPane.QUESTION_MESSAGE, null, lago, lago[0]);

            double input = Double.parseDouble( JOptionPane.showInputDialog("Insert the salary of coder" ,objUpdate.getSalario()));
            Contratacion objCreate = new Contratacion();

            objUpdate.setIdVacante(objUpdate.getIdVacante());
            objUpdate.setIdCoder(idCoder);
            Estado estafo = Estado.Activo;
            if (!input5.equals("Activo")){
                estafo=Estado.Inactivo;
            }
            objUpdate.setEstado(estafo);
            objUpdate.setSalario(input);

            objModel.update(objUpdate);
        }

    }
    public static void delete() {
        ContratacionModel objModel = new ContratacionModel();
        String listString = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listString + "\n Enter the Id the hiring to delete"));

        Contratacion objDelete = (Contratacion) objModel.SearchById(idDelete);

        int Confirm = 1;
        if (objDelete == null) {
            JOptionPane.showMessageDialog(null, "hiring not found");
        } else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the hiring?\n" + objDelete.toString());
            if (Confirm == 0) objModel.delete(objDelete);
        }
    }
}
