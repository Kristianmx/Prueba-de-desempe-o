package Controller;

import Entity.Coder;
import Model.CoderModel;

import javax.swing.*;

public class CoderController {
    public void MenuCoder(){

        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF CODER:
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
                                2). Search by clan.
                                3). Search by Technology.
                                4). Return...
                                                        
                                choose a option:
                                """);
                        switch (optSearch) {
                            case "1":
                                getAllCohorte();
                                break;
                            case "2":
                                getAllClan();
                                break;
                            case "3":
                                getAllTechnology();
                                break;
                            case "4":
                                JOptionPane.showMessageDialog(null, "Return the menu");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Option not valid...");
                                break;
                        }
                    }while (!optSearch.equals("4"));
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
        CoderModel objModel = new CoderModel();
        String input = JOptionPane.showInputDialog("Insert the Name of the coder:");
        String input2 = JOptionPane.showInputDialog("Insert the surnames of the coder:");
        String input3 = JOptionPane.showInputDialog("Insert the document of the coder:");
        int input4 = Integer.parseInt( JOptionPane.showInputDialog("Insert the cohorte of the coder:"));
        String input5 = JOptionPane.showInputDialog("Insert the CV of the coder:");
        String input6 = JOptionPane.showInputDialog("Insert the Clan of the coder:");
        Coder objCreate = new Coder();
        objCreate.setNombre(input);
        objCreate.setApellidos(input2);
        objCreate.setDocumento(input3);
        objCreate.setCohorte(input4);
        objCreate.setCv(input5);
        objCreate.setClan(input6);

        objCreate =(Coder) objModel.insert(objCreate);
        JOptionPane.showMessageDialog(null,objCreate.toString());

    }
    public static  void getAll(){
        CoderModel objModel = new CoderModel();
        String listString = "💻List Coder:\n";
        for (Object i: objModel.findAll()){
            Coder objList = (Coder) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static  void getAllCohorte(){
        int idSearch = Integer.parseInt(JOptionPane.showInputDialog("Enter the numen of the cohorte: "));
        CoderModel objModel = new CoderModel();
        String listString = "💻List Coder:\n";
        for (Object i: objModel.SearchByCohorte(idSearch)){
            Coder objList = (Coder) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static  void getAllTechnology(){
        String Search = JOptionPane.showInputDialog("Enter the Technology to search:");
        CoderModel objModel = new CoderModel();
        String listString = "💻List Coder:\n";
        for (Object i: objModel.SearchByTechnology(Search)){
            Coder objList = (Coder) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static  void getAllClan(){
        String clan = JOptionPane.showInputDialog("Enter the clan to search:");
        CoderModel objModel = new CoderModel();
        String listString = "💻List Coder:\n";
        for (Object i: objModel.SearchByClan(clan)){
            Coder objList = (Coder) i;
            listString+=objList.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listString);
    }
    public static String getAllString(){
        CoderModel objModel = new CoderModel();
        String listString = "💻List Coder:\n";
        for (Object i: objModel.findAll()){
            Coder objList = (Coder) i;
            listString+=objList.toString()+"\n";
        }
        return listString;
    }
    public static  void  update(){
        CoderModel objModel = new CoderModel();
        String listString =getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, listString+"\n Enter the Id of the Coder to update:"));
        Coder objUpdate = (Coder) objModel.SearchById(id);

        if (objUpdate==null){
            JOptionPane.showMessageDialog(null,"Coder not found");
        }else {
            String input = JOptionPane.showInputDialog("Insert the Name of the coder:",objUpdate.getNombre());
            String input2 = JOptionPane.showInputDialog("Insert the surnames of the coder:",objUpdate.getApellidos());
            String input3 = JOptionPane.showInputDialog("Insert the document of the coder:",objUpdate.getDocumento());
            int input4 = Integer.parseInt( JOptionPane.showInputDialog("Insert the cohorte of the coder:",objUpdate.getCohorte()));
            String input5 = JOptionPane.showInputDialog("Insert the CV of the coder:",objUpdate.getCv());
            String input6 = JOptionPane.showInputDialog("Insert the Clan of the coder:",objUpdate.getClan());
            objUpdate.setNombre(input);
            objUpdate.setApellidos(input2);
            objUpdate.setDocumento(input3);
            objUpdate.setCohorte(input4);
            objUpdate.setCv(input5);
            objUpdate.setClan(input6);

            objModel.update(objUpdate);
        }
    }
    public static void delete() {
        CoderModel objModel = new CoderModel();
        String list = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(list + "\n Enter the Id the Coder to delete"));

        Coder objDelete = (Coder) objModel.SearchById(idDelete);

        int Confirm = 1;
        if (objDelete == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            Confirm = JOptionPane.showConfirmDialog(null, "Are you suer want to delete the Coder?\n" + objDelete.toString());
            if (Confirm == 0) objModel.delete(objDelete);
        }
    }
}
