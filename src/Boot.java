import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Boot {

    public static void main(String[] args) {
        int ramBlocks = 0;
        int choose=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество блоков в RAM: ");
        try {
            exit_loop:
            ramBlocks = scanner.nextInt();
        }
        catch (InputMismatchException exception)
        {
            System.out.println("Thing what you had just wrote is not a number");
            return;
        }
        int  [] ram =  new int [ramBlocks];
        String[] planner = new String [ramBlocks];
        ArrayList <Operation> operation =  new ArrayList<>();
        try {
            for (int i = 0; i < ramBlocks; i++) {
                System.out.println("Введите количество памяти для блока " + i + " в Кb");
                ram[i] = scanner.nextInt();
                planner[i] = "Пусто";
            }
        }catch (InputMismatchException exception)
        {
            System.out.println("Thing what you had just wrote is not a number");
            return;
        }
        while(true)
        {
            try{
                System.out.println("1-Create new Process"+'\n'+"2-Delete/Stop Process"+'\n'+
                        "3-Show ram status"+'\n'+"4-Exit Programm");
                choose = scanner.nextInt();
            }
            catch (InputMismatchException exception)
            {
                System.out.println("Thing what you had just wrote is not a number");

            }
            boolean DobbieIsFree= true;
            switch (choose)
            {
                case 1:
                    int counter = 0,max=0;
                    Operation example = new Operation();
                    System.out.println("Name your operation: ");
                    String name = scanner.next();
                    example.setName(name);
                    int size = 0;
                    try {
                        System.out.println("What size does your operation have?: ");
                         size= scanner.nextInt();
                    }
                    catch (InputMismatchException exception)
                    {
                        System.out.println("Thing what you had just wrote is not a number");
                        return;
                    }
                    example.setSize(size);

                    //Worse Fit Algorithm
                    for(int i=0;i<ramBlocks;i++)
                    {
                        if(planner[i].equals("Пусто") && ram[i]>max)
                        {
                            max=ram[i];
                            counter=i;
                        }
                    }
                    if(ram[counter]>example.getSize()){
                        example.setNumber(counter);
                        operation.add(example);
                        planner[counter]=example.getName();
                    }
                    else System.out.println("Oh, your operation is too big!!");
                    break;
                case 3:
                    System.out.println("Номер операции|Название операции|Размер блока RAM");
                    for(int i = 0;i<ramBlocks;i++)
                    {
                        System.out.println("       "+i+"      |      "+planner[i]+"       |         "+ram[i]);
                    }
                    break;
                case 2:
                    System.out.println("Выберите номер программы, которую нужно убить: ");
                    int kill=scanner.nextInt();
                    String temp=planner[kill];
                    for(int i = 0;i<operation.size();i++)
                    {
                        if(operation.get(i).getName().equals(temp))
                        {
                            operation.remove(i);
                        }
                    }
                    planner[kill]="Пусто";
                    break;
                case 4:
                    DobbieIsFree=false;
                    break;

            }
            if(DobbieIsFree==false)
            {
                break;
            }
        }
    }
}
