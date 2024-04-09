import Controller.CoderController;
import Controller.ContratacionController;
import Controller.EmpresaController;
import Controller.VacanteController;
import DataBase.ConnectionDB;
import Model.EmpresaModel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmpresaController objEmpresaController= new EmpresaController();
        CoderController objCoderController = new CoderController();
        VacanteController objVacanteController = new VacanteController();
        ContratacionController objContratacionContoller = new ContratacionController();
        String option = "";
    do{
        option = JOptionPane.showInputDialog(null, """
                MAIN MENU:
                1). Company Menu.
                2). Coder Menu.
                3). Vacant Menu.
                4). Hiring Menu.
                5). Exit of menu.
                    
                Choose a option:
                """);

        switch (option){
            case "1":
                objEmpresaController.MenuEmpresa();
                break;
            case "2":
                objCoderController.MenuCoder();
                break;
            case "3":
                objVacanteController.MenuVacante();
                break;
            case "4":
                objContratacionContoller.MenuContratacion();
                break;
            case "5":
                JOptionPane.showMessageDialog(null, """
                            Menu Closed.
                            Good Bye...
                            """);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Option not valid...");
                break;
        }
    }while (!option.equals("5"));


    }
}